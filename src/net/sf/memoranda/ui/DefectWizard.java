package net.sf.memoranda.ui;

/**
 * Classname: DefectWizard
 * 
 * Description: This class creates a Wizard that allows a user to track defects
 * injected/removed in their project. This information can/will be used for 
 * analysis in the Personal Software Process.
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class DefectWizard extends JDialog implements Wizard {
	
	private String _phase = "";
	private String _defectType = "";
	private String _defectName = "";
	private String _notes = "";

	JLabel header = new JLabel();
	JLabel headerLabel = new JLabel("", JLabel.CENTER);

	JButton finishB = new JButton();
	JButton cancelB = new JButton();

	JLabel defectTitleLabel = new JLabel("<html>Defect Title:" + "<font color=\"red\">*</font></html>");
	JLabel defectTypeLabel = new JLabel("Defect Type:");
	JLabel phaseLabel = new JLabel("Phase Injected/Removed:");
	JLabel notesLabel = new JLabel("Notes (Optional):");
	JLabel reqFields = new JLabel("<html><font color=\"red\">*</font>" + " <i>Indicates required fields</i></html>");

	JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel mainPanel = new JPanel();
	JPanel bottomPanel = new JPanel(new BorderLayout());
	JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

	DefaultComboBoxModel<String> typeModel = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> phaseModel = new DefaultComboBoxModel<String>();
	JComboBox<String> typeComboBox = new JComboBox<String>(typeModel);
	JComboBox<String> phaseComboBox = new JComboBox<String>(phaseModel);

	JTextArea txt = new JTextArea(10, 31);

	JTextField defectText = new JTextField(32);

	/*
	 * Constructor
	 */
	public DefectWizard(Frame frame, String title) {
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
		headerLabel.setText("<html><font size=\"6\"> Welcome to the Defect Wizard.</font><br>"
				+ " This Wizard will help you to log defects injected/removed in your project.</html>");

		// Build type combo-box
		typeModel.addElement("Injected");
		typeModel.addElement("Removed");

		// Build phase combo-box
		phaseModel.addElement("Planning");
		phaseModel.addElement("Design");
		phaseModel.addElement("Code");
		phaseModel.addElement("Compile");
		phaseModel.addElement("Test");

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
		header.setText("<html>DEFECT" + "<br>" + "WIZARD");
		header.setIcon(new ImageIcon(net.sf.memoranda.ui.EventDialog.class.getResource(
	            "resources/icons/Bug.png")));
		headerPanel.add(header);

		// Build mainPanel
		SpringLayout layout = new SpringLayout();
		mainPanel.setLayout(layout);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(headerLabel);
		mainPanel.add(defectTitleLabel);
		mainPanel.add(defectText);
		mainPanel.add(defectTypeLabel);
		mainPanel.add(typeComboBox);
		mainPanel.add(phaseLabel);
		mainPanel.add(phaseComboBox);
		mainPanel.add(notesLabel);
		mainPanel.add(pane);
		mainPanel.add(reqFields);

		// Adjust Defect Text-Box (Defect Title:)
		layout.putConstraint(SpringLayout.WEST, defectTitleLabel, 60, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, defectTitleLabel, 80, SpringLayout.NORTH, mainPanel);

		layout.putConstraint(SpringLayout.WEST, defectText, 13, SpringLayout.EAST, defectTitleLabel);
		layout.putConstraint(SpringLayout.NORTH, defectText, 80, SpringLayout.NORTH, mainPanel);

		// Adjust Type Combo-box (Defect Type:)
		layout.putConstraint(SpringLayout.WEST, defectTypeLabel, 60, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, defectTypeLabel, 120, SpringLayout.NORTH, mainPanel);

		layout.putConstraint(SpringLayout.WEST, typeComboBox, 11, SpringLayout.EAST, defectTypeLabel);
		layout.putConstraint(SpringLayout.NORTH, typeComboBox, 120, SpringLayout.NORTH, mainPanel);

		// Adjust Phase Combo-box (Phase Injected/Removed:)
		layout.putConstraint(SpringLayout.WEST, phaseLabel, 60, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, phaseLabel, 160, SpringLayout.NORTH, mainPanel);

		layout.putConstraint(SpringLayout.WEST, phaseComboBox, 5, SpringLayout.EAST, phaseLabel);
		layout.putConstraint(SpringLayout.NORTH, phaseComboBox, 160, SpringLayout.NORTH, mainPanel);

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

		// Make sure "Defect Title:" field is filled out before
		// enabling "Finish" button
		defectText.getDocument().addDocumentListener(new DocumentListener() {
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
		if (defectText.getText().trim().isEmpty()) {
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
	public boolean setValues(){
		try{
			_defectName = defectText.getText();
			_defectType = typeComboBox.getSelectedItem().toString();
			_phase = phaseComboBox.getSelectedItem().toString();
			_notes = txt.getText();
		}
		catch(Exception e){
			final JPanel panel = new JPanel();
			
			JOptionPane.showMessageDialog(panel, "Invalid value entered" + e.toString().substring(e.toString().lastIndexOf(':')), "Warning",
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
		
		//do not close wizard if correct values are not set
		if(!set){
			return;
		}
		
		String id = CurrentProject.get().getID();
		
		Defect defectData = new Defect (_phase, _defectType, _defectName, _notes);
		Phase phase = ProjectManager.loadPSPData(id);
		
		if(phase == null){
			phase = new Phase();
		}
		
		phase.addDefect(defectData);
		ProjectManager.savePSPData(CurrentProject.get().getID(), phase);
			
		//close wizard once correct values are set
		this.dispose();
	}

	/**
	 * Description: This is a Listener for the Cancel button
	 * 
	 * @param e, ActionEvent, Cancel button has been hit
	 */
	public void cancelB_actionPerformed(ActionEvent e) {
		this.dispose();
	}

}