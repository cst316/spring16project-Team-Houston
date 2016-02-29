package net.sf.memoranda.ui;

/**
 * Classname: SizeAccountingWizard
 * 
 * Description: This class creates a Wizard that allows a user to log the size
 * of their project in Lines of Code (LOC).This information can/will be used for analysis
 * in the Personal Software Process.
 * 
 * Version: 1.0
 * 
 * Date: 2/21/20156 
 * 
 * Referenced Source(s): http://stackoverflow.com/questions/16877625/how-to-check-if-all-the-jtexfields-are-empty-or-not
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.ProjectManager;

@SuppressWarnings("serial")
public class SizeAccountingWizard extends JDialog implements Wizard {

	private int _addedLOC = 0;
	private int _modifiedLOC = 0;
	private int _deletedLOC = 0;
	private String _notes = "";

	JLabel header = new JLabel();
	JLabel headerLabel = new JLabel("", JLabel.CENTER);

	JButton finishB = new JButton();
	JButton cancelB = new JButton();

	JLabel addedLabel = new JLabel("<html>Lines of Code (LOC) added:" + "<font color=\"red\">*</font></html>");
	JLabel modifiedLabel = new JLabel("<html>Lines of Code (LOC) modified:" + "<font color=\"red\">*</font></html>");
	JLabel deletedLabel = new JLabel("<html>Lines of Code (LOC) deleted:" + "<font color=\"red\">*</font></html>");
	JLabel notesLabel = new JLabel("Notes (Optional):");
	JLabel reqFields = new JLabel("<html><font color=\"red\">*</font>" + " <i>Indicates required fields</i></html>");

	JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel mainPanel = new JPanel();
	JPanel bottomPanel = new JPanel(new BorderLayout());
	JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

	JTextArea txt = new JTextArea(10, 31);

	JTextField addedText = new JTextField(5);
	JTextField modifiedText = new JTextField(5);
	JTextField deletedText = new JTextField(5);

	/*
	 * Constructor
	 */
	public SizeAccountingWizard(Frame frame, String title) {
		super(frame, title, true);
		try {
			jbInit();
			pack();

		} catch (Exception ex) {
			new Exception(ex);
		}
	}

	/**
	 * Description: This method is used to build/initialize the Wizard.
	 * 
	 * @throws Exception
	 */
	public void jbInit() throws Exception {

		// set header label and description
		headerLabel.setText("<html><font size=\"6\"> Welcome to the Size Accounting Wizard.</font><br>"
				+ " This Wizard will help you to account the size & work done on your project.</html>");

		// Build text area
		txt.setLineWrap(true);
		JScrollPane pane = new JScrollPane(txt);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Build main window
		this.setResizable(false);
		this.setPreferredSize(new Dimension(800, 600));
		this.setLocation(400, 200);

		// Build headerPanel
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		header.setFont(new java.awt.Font("Dialog", 0, 20));
		header.setForeground(new Color(0, 0, 124));
		header.setText("<html>SIZE" + " ACCT." + "<br>" + "WIZARD</html>");
		header.setIcon(new ImageIcon(net.sf.memoranda.ui.EventDialog.class.getResource(
	            "resources/icons/Data.png")));
		headerPanel.add(header);

		// Build mainPanel
		SpringLayout layout = new SpringLayout();
		mainPanel.setLayout(layout);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(headerLabel);
		mainPanel.add(addedLabel);
		mainPanel.add(addedText);
		mainPanel.add(modifiedLabel);
		mainPanel.add(modifiedText);
		mainPanel.add(deletedLabel);
		mainPanel.add(deletedText);
		mainPanel.add(notesLabel);
		mainPanel.add(pane);
		mainPanel.add(reqFields);

		// Adjust Added LOC Text-Box (Estimated LOC to be Added:)
		layout.putConstraint(SpringLayout.WEST, addedLabel, 60, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, addedLabel, 80, SpringLayout.NORTH, mainPanel);

		layout.putConstraint(SpringLayout.WEST, addedText, 22, SpringLayout.EAST, addedLabel);
		layout.putConstraint(SpringLayout.NORTH, addedText, 80, SpringLayout.NORTH, mainPanel);

		// Adjust Modified LOC Text-Box (Estimated LOC to be Modified:)
		layout.putConstraint(SpringLayout.WEST, modifiedLabel, 60, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, modifiedLabel, 120, SpringLayout.NORTH, mainPanel);

		layout.putConstraint(SpringLayout.WEST, modifiedText, 8, SpringLayout.EAST, modifiedLabel);
		layout.putConstraint(SpringLayout.NORTH, modifiedText, 120, SpringLayout.NORTH, mainPanel);

		// Adjust Deleted LOC Text-Box (Estimated LOC to be Deleted:)
		layout.putConstraint(SpringLayout.WEST, deletedLabel, 60, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, deletedLabel, 160, SpringLayout.NORTH, mainPanel);

		layout.putConstraint(SpringLayout.WEST, deletedText, 15, SpringLayout.EAST, deletedLabel);
		layout.putConstraint(SpringLayout.NORTH, deletedText, 160, SpringLayout.NORTH, mainPanel);

		// Adjust Text Area (Notes:)
		layout.putConstraint(SpringLayout.WEST, notesLabel, 60, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, notesLabel, 200, SpringLayout.NORTH, mainPanel);

		layout.putConstraint(SpringLayout.WEST, pane, 5, SpringLayout.EAST, notesLabel);
		layout.putConstraint(SpringLayout.NORTH, pane, 0, SpringLayout.NORTH, notesLabel);

		// Adjust required fields notice
		layout.putConstraint(SpringLayout.WEST, reqFields, 60, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, reqFields, 500, SpringLayout.NORTH, mainPanel);

		// Build ButtonsPanel
		finishB.setMaximumSize(new Dimension(100, 26));
		finishB.setMinimumSize(new Dimension(100, 26));
		finishB.setPreferredSize(new Dimension(100, 26));
		finishB.setText("Finish");
		finishB.setEnabled(false);
		finishB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishB_actionPerformed(e);
			}
		});
		this.getRootPane().setDefaultButton(finishB);

		cancelB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelB_actionPerformed(e);
			}
		});
		cancelB.setText("Cancel");
		cancelB.setPreferredSize(new Dimension(100, 26));
		cancelB.setMinimumSize(new Dimension(100, 26));
		cancelB.setMaximumSize(new Dimension(100, 26));
		buttonsPanel.add(finishB);
		buttonsPanel.add(cancelB);
		bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);

		this.getRootPane().setDefaultButton(finishB);

		// Build dialog
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		this.getContentPane().add(headerPanel, BorderLayout.WEST);

		// Make sure "LOC added" field is filled out before
		// enabling "Finish" button
		addedText.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
		});

		// Make sure "LOC modified" field is filled out before
		// enabling "Finish" button
		modifiedText.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
		});

		// Make sure "LOC deleted" field is filled out before
		// enabling "Finish" button
		deletedText.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}
		});

	}

	/**
	 * Description: This method is used to check for required fields that are
	 * left empty in the Wizard. User must fill out all required fields before
	 * the Finish button is enabled.
	 */
	public void changed() {
		if (addedText.getText().trim().isEmpty()) {
			finishB.setEnabled(false);
		} else if (modifiedText.getText().trim().isEmpty()) {
			finishB.setEnabled(false);
		} else if (deletedText.getText().trim().isEmpty()) {
			finishB.setEnabled(false);
		} else {
			finishB.setEnabled(true);
		}
	}

	/**
	 * Description This method is used to set the values entered into the Wizard
	 * by the user. Any invalid input will be brought to their attention via a
	 * warning pop-up.
	 * 
	 * @return: boolean, true if setting values was successful, false otherwise
	 */
	public boolean setValues() {
		try {
			_addedLOC = Integer.parseInt(addedText.getText());
			_modifiedLOC = Integer.parseInt(modifiedText.getText());
			_deletedLOC = Integer.parseInt(deletedText.getText());
			_notes = txt.getText();
		} catch (Exception e) {
			final JPanel panel = new JPanel();

			JOptionPane.showMessageDialog(panel,
					"Invalid value entered" + e.toString().substring(e.toString().lastIndexOf(':')), "Warning",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Description: This is a Listener for the Finish button
	 * 
	 * @param e, ActionEvent, Finish button has been hit
	 */
	public void finishB_actionPerformed(ActionEvent e) {

		boolean set = setValues();

		// do not close wizard if correct values are not set
		if (!set) {
			return;
		}

		String id = CurrentProject.get().getID();
		
		ProjectSize projectSizeData = new ProjectSize (_addedLOC, _modifiedLOC, _deletedLOC, _notes);
		Phase phase = ProjectManager.loadPSPData(id);
		
		if(phase == null){
			phase = new Phase();
		}
		
		phase.setProjSize(projectSizeData);
		ProjectManager.savePSPData(CurrentProject.get().getID(), phase);

		// close wizard once correct values are set
		this.dispose();
	}

	/**
	 * Description: This is a Listener for the Cancel button
	 * 
	 * @param e, ActionEvent, Cancel button has been hit
	 */
	public void cancelB_actionPerformed(ActionEvent e) {
		// close wizard if "Cancel" button is hit
		this.dispose();
	}
}