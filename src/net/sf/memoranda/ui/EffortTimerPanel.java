package net.sf.memoranda.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskList;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;

public class EffortTimerPanel extends JPanel {
	
	private static final int PANEL_HEIGHT = 100;
	private static final String DEFAULT_READOUT = "00:00:00";
	private static final String START_TEXT = Local.getString("Start");
	private static final String PAUSE_TEXT = Local.getString("Pause");
	private static final String RESUME_TEXT = Local.getString("Resume");
	private static final String CLEAR_TEXT = Local.getString("Clear");
	private static final String SAVE_TEXT = Local.getString("Save");
	private static final String CLOSE_TEXT = Local.getString("Close");
	
	private JButton toggleButton = new JButton();
	private JButton clearButton = new JButton();
	private JButton saveButton = new JButton();
	private JButton closeButton = new JButton();
	private JButton editButton = new JButton();
	private JComboBox<Task> taskBox;
	private JLabel timeReadout = new JLabel();
	private JPanel buttonPanel = new JPanel(new GridBagLayout());
	private Timer panelTimer = new Timer(10,new LabelIncrementListener());
	private long startTime;
	private long currentEffort;
	private boolean hasUnsavedProgress;
	
	public EffortTimerPanel() {
		super(new GridBagLayout());
		init();
		setUIDefaults();
	}
	
	private void init() {
		GridBagConstraints mainGBC = new GridBagConstraints();
		mainGBC.gridx = 0;
		mainGBC.gridy = 0;
		mainGBC.weightx = 0.5F;
		mainGBC.anchor = GridBagConstraints.WEST;		
		timeReadout.setFont(new Font("monospaced",Font.BOLD,20));
		timeReadout.setHorizontalAlignment(SwingConstants.CENTER);
		timeReadout.setPreferredSize(new Dimension(150,PANEL_HEIGHT));
		add(timeReadout, mainGBC);
		
		mainGBC.gridx = 1;
		mainGBC.weightx = 0.5F;
		mainGBC.anchor = GridBagConstraints.EAST;
		mainGBC.insets = new Insets(0,0,0,5);
		buttonPanel.setPreferredSize(new Dimension(200,PANEL_HEIGHT));
		add(buttonPanel, mainGBC);
		
		updateTaskBoxList();
		
		GridBagConstraints bpGBC = new GridBagConstraints();
		bpGBC.gridy = 0;
		bpGBC.gridx = 0;
		buttonPanel.add(taskBox, bpGBC);
		addCBHorizontalScroll(taskBox);
		taskBox.setPreferredSize(new Dimension (100,taskBox.getPreferredSize().height));
		
		bpGBC.gridy = 0;
		bpGBC.gridx = 1;
		bpGBC.ipadx = 40;
		buttonPanel.add(saveButton, bpGBC);
		bpGBC.fill = GridBagConstraints.HORIZONTAL;
		saveButton.setText(SAVE_TEXT);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				saveEffort();
			}
		});
		
		bpGBC.gridy = 1;
		bpGBC.gridx = 0;
		bpGBC.gridwidth = 2;
		toggleButton.addActionListener(new ToggleButtonListener());
		buttonPanel.add(toggleButton, bpGBC);
		
		bpGBC.gridy = 2;
		bpGBC.gridx = 0;
		bpGBC.gridwidth = 2;
		buttonPanel.add(clearButton, bpGBC);
		clearButton.setText(CLEAR_TEXT);
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetCheck();
			}
		});
		
		bpGBC.gridy = 3;
		bpGBC.gridx = 0;
		bpGBC.gridwidth = 2;
		buttonPanel.add(closeButton, bpGBC);
		closeButton.setText(CLOSE_TEXT);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (resetCheck())
					EffortTimerFrame.getInstance().setVisible(false);
			}
		});
	}

	public boolean isTiming() {
		return panelTimer.isRunning();
	}
	
	@Override
	public void updateUI() {
		super.updateUI();
		updateTaskBoxList();
	}
	
	private boolean promptSave() {
		int response = JOptionPane.showConfirmDialog((Component) EffortTimerFrame.getInstance(), 
							"You have not yet saved your tracked effort. Would you like to save now?",
							"Unsaved Time",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE
						);
		if (response == JOptionPane.YES_OPTION){
			saveEffort();
			return true;
		}
		if (response == JOptionPane.NO_OPTION) {
			setUIDefaults();
			return true;
		}
		return false;
	}
	
	private boolean resetCheck() {
		boolean doContinue = true;
		if (hasUnsavedProgress) {
			doContinue = promptSave();
		}
		if (!doContinue) {  // user cancelled, revert change
			taskBox.setSelectedItem(ItemEvent.ITEM_LAST);
		}
		return doContinue;
	}
	
	private void saveEffort() {
		TaskList curTasks = CurrentStorage.get().openTaskList(CurrentProject.get());
		try {
			Task target = (Task) taskBox.getSelectedItem();
			target.setActualEffort(currentEffort + target.getActualEffort());
			setUIDefaults(); // saving should trigger UI reset
		}
		catch (Exception e) {
			System.out.println("Error saving effort to task!");
		}
	}

	private void setUIDefaults() {
		toggleButton.setText(START_TEXT);
		timeReadout.setText(DEFAULT_READOUT);
		saveButton.setEnabled(false);
		clearButton.setEnabled(false);
		editButton.setVisible(false);
		taskBox.setEnabled(true);
		Task curTask = (Task) taskBox.getSelectedItem();
		if (curTask != null)
			curTask.unlock();
		hasUnsavedProgress = false;
		currentEffort = 0L;
	}
	
	private void startTimer() {
		if (taskBox.getSelectedItem() != null) {
			saveButton.setEnabled(false);
			closeButton.setEnabled(false);
			taskBox.setEnabled(false);
			clearButton.setEnabled(false);
			editButton.setEnabled(false);
			if (toggleButton.getText().equals(START_TEXT))
				startTime = System.nanoTime();
			toggleButton.setText(PAUSE_TEXT);
			hasUnsavedProgress = true;
			Task curTask = (Task) taskBox.getSelectedItem();
			curTask.lock();
			panelTimer.start();
		}
	}
	
	private void stopTimer() {
		panelTimer.stop();
		toggleButton.setText(RESUME_TEXT);
		saveButton.setEnabled(true);
		closeButton.setEnabled(true);
		clearButton.setEnabled(true);
		if (!editButton.isVisible())
			editButton.setVisible(true);
		editButton.setEnabled(true);
	}

	private void addCBHorizontalScroll(JComboBox currentBox) {
		for (int i = 0; i < currentBox.getUI().getAccessibleChildrenCount(currentBox); i++) {
			Object child = currentBox.getUI().getAccessibleChild(currentBox, i);
			if (child instanceof JPopupMenu) {
				JPopupMenu dropdown = (JPopupMenu) child;
				JScrollPane scrollPane = (JScrollPane) dropdown.getComponent(0);
				scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			}
		}
	}
	
	private void updateTaskBoxList() {
		Vector<Task> topTasks = (Vector<Task>) CurrentProject.getTaskList().getTopLevelTasks();
		Vector<Task> allTasks = new Vector<Task>();
		for (Task t: topTasks) {
			allTasks.add(t);
			allTasks.addAll(t.getSubTasks());
		}
		if (taskBox == null)
			taskBox = new JComboBox<Task>(allTasks);
		else
			taskBox.setModel(new JComboBox<Task>(allTasks).getModel());
	}

	private class ComboSelectionListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent ie) {
			if (ie.getID() == ItemEvent.SELECTED) {
				resetCheck();
			}
		}
	}
	
	private class LabelIncrementListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent ae) {
				currentEffort = System.nanoTime() - startTime;
 				timeReadout.setText(Util.formatTimeDuration(currentEffort));
			}
	}
	
	private class ToggleButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (!isTiming()) 
				startTimer();
			else
				stopTimer();
		}
	}
}
