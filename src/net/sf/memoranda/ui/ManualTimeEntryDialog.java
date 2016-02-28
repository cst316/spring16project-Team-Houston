package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.MiscTracking;
import net.sf.memoranda.MiscTrackingList;
import net.sf.memoranda.ui.htmleditor.util.Local;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Util;

@SuppressWarnings("serial")
public class ManualTimeEntryDialog extends JDialog {
	private JTextField _titleEntryField;
	private JTextField _timeEntryField;
	private JComboBox<String> _timeTypeBox;
	private JTextArea _descriptionEntryArea;
	
	public ManualTimeEntryDialog() {
		super(App.getFrame(),"Manual Time Entry",true);
		this.init();
		this.pack();
	}
	
	private void init() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel manualPanel = new JPanel();
		manualPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.3F;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4,4,4,4);
		JLabel entryTitleLabel = new JLabel();
		entryTitleLabel.setText(String.format("%s:", Local.getString("Title")));
		entryTitleLabel.setHorizontalAlignment(JLabel.RIGHT);
		manualPanel.add(entryTitleLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.weightx = 0.7F;
		gbc.insets = new Insets(4,0,4,4);
		_titleEntryField = new JTextField();
		manualPanel.add(_titleEntryField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.3F;
		gbc.insets = new Insets(4,4,4,4);
		JLabel timeEntryLabel = new JLabel();
		timeEntryLabel.setText(String.format("%s:", Local.getString("Duration")));
		timeEntryLabel.setHorizontalAlignment(JLabel.RIGHT);
		manualPanel.add(timeEntryLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.4F;
		gbc.insets = new Insets(4,0,4,4);
		_timeEntryField = new JTextField();
		_timeEntryField.setPreferredSize(new Dimension(200, _timeEntryField.getPreferredSize().height));
		manualPanel.add(_timeEntryField, gbc);
		
		gbc.gridx = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0.3F;
		gbc.insets = new Insets(4,0,4,4);
		Vector<String> values = new Vector<String>(Arrays.asList("Hours","Minutes","Seconds"));
		_timeTypeBox = new JComboBox<String>(values);
		manualPanel.add(_timeTypeBox, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipady = 40;
		gbc.weightx = 0.3F;
		gbc.insets = new Insets(4,4,4,4);
		JLabel descriptionEntryLabel = new JLabel();
		descriptionEntryLabel.setText(String.format("%s:", Local.getString("Description")));
		descriptionEntryLabel.setHorizontalAlignment(JLabel.RIGHT);
		manualPanel.add(descriptionEntryLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.weightx = 0.7F;
		gbc.ipady = 40;
		gbc.insets = new Insets(4,0,4,4);
		_descriptionEntryArea = new JTextArea();
		JScrollPane scrollArea = new JScrollPane(_descriptionEntryArea);
		manualPanel.add(scrollArea, gbc);
		
		JPanel controlPanel = new JPanel(new FlowLayout());
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveListener(this));
		controlPanel.add(saveButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				closeFrame();
			}
		});
		controlPanel.add(cancelButton);
		
		mainPanel.add(manualPanel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		setContentPane(mainPanel);
		
		setResizable(false);
		setLocationRelativeTo(App.getFrame());
		setAlwaysOnTop(true);
	}
	
	private void closeFrame() {
		this.dispose();
	}
	
	private boolean validateEntries() {
		try {
			if (!_titleEntryField.getText().isEmpty() && 
				Double.parseDouble(_timeEntryField.getText()) > 0D) {
				return true;
			}
		}
		catch (Exception e) {
			// do nothing
		}
		return false;
	}
	
	private class SaveListener implements ActionListener {
		private JDialog _parent;
		
		public SaveListener(JDialog parent) {
			super();
			_parent = parent;
		}
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			Long conversionFactor = 0L;
			if (validateEntries()) {
				switch ((String) _timeTypeBox.getSelectedItem()) {
					case "Hours":
						conversionFactor = (long) (60 * 60 * 1000);
						break;
					case "Minutes":
						conversionFactor = (long) (60 * 1000);
						break;
					case "Seconds":
						conversionFactor = (long) (1000);
						break;
				}
				Long effortDuration = (long) (Double.parseDouble(_timeEntryField.getText()) *
									  conversionFactor);
				MiscTracking newTrackingObj = new MiscTracking();
				newTrackingObj.setName(_titleEntryField.getText());
				newTrackingObj.setDescription(_descriptionEntryArea.getText());
				newTrackingObj.addActualEffort(TimeUnit.MILLISECONDS.toNanos(effortDuration));
				MiscTrackingList mtl = CurrentProject.getMiscTrackingList();
				mtl.addMiscTrackingObj(newTrackingObj);
				CurrentStorage.get().storeMiscTrackingList(mtl, CurrentProject.get());
				closeFrame();
			}
			else {
				JOptionPane.showMessageDialog(_parent,
						"Attempted to save with incomplete or incorrect information. Please try again.",
						"Save Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
