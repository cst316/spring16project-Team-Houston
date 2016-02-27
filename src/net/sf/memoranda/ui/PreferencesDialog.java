package net.sf.memoranda.ui;

import java.io.File;
import java.util.*;


import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.util.AgendaGenerator;
import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Task;
import net.sf.memoranda.util.Configuration;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.MimeTypesList;
import net.sf.memoranda.util.Util;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/*$Id: PreferencesDialog.java,v 1.16 2006/06/28 22:58:31 alexeya Exp $*/
public class PreferencesDialog extends JDialog {
	JPanel topPanel = new JPanel(new BorderLayout());
	JTabbedPane tabbedPanel = new JTabbedPane();
	JPanel GeneralPanel = new JPanel(new GridBagLayout());
	GridBagConstraints gbc;
	JLabel jLabel1 = new JLabel();
	ButtonGroup minGroup = new ButtonGroup();
	JRadioButton minTaskbarRB = new JRadioButton();
	JRadioButton minHideRB = new JRadioButton();
	JRadioButton standardTimeRB = new JRadioButton();
	JRadioButton militaryTimeRB = new JRadioButton();
	JRadioButton confirmSwitchRB = new JRadioButton();
	JRadioButton declineSwitchRB = new JRadioButton();
	ButtonGroup timeGroup = new ButtonGroup();
	ButtonGroup projectGroup = new ButtonGroup();
	JLabel jLabel7 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel jLabel9 = new JLabel();
	JLabel jLabel10 = new JLabel();
	JComboBox<String> snoozeVals = new JComboBox<String>(PreferencesDialog.getSnoozeDurations());
	JTextField maxSnoozeAmt = new JTextField();
	ButtonGroup closeGroup = new ButtonGroup();
	JLabel jLabel2 = new JLabel();
	JRadioButton closeExitRB = new JRadioButton();
	JCheckBox askConfirmChB = new JCheckBox();
	JCheckBox askConfirmProjectChB = new JCheckBox();
	JRadioButton closeHideRB = new JRadioButton();
	JLabel jLabel3 = new JLabel();
	ButtonGroup lfGroup = new ButtonGroup();
	JRadioButton lfSystemRB = new JRadioButton();
	JRadioButton lfJavaRB = new JRadioButton();
	JRadioButton lfCustomRB = new JRadioButton();
	JLabel classNameLabel = new JLabel();
	JTextField lfClassName = new JTextField();
	JLabel jLabel4 = new JLabel();
	JCheckBox enSystrayChB = new JCheckBox();
	JCheckBox startMinimizedChB = new JCheckBox();
	JCheckBox enSplashChB = new JCheckBox();
	JCheckBox enL10nChB = new JCheckBox();
	JCheckBox firstdow = new JCheckBox();
	JPanel resourcePanel = new JPanel(new BorderLayout());
	Border rstPanelBorder;
	TitledBorder rsbpBorder;
	JButton okB = new JButton();
	JButton cancelB = new JButton();
	JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
	JLabel lblExit = new JLabel();
	JLabel lblSwitchProj = new JLabel();
	JPanel soundPanel = new JPanel();
	JCheckBox enableSoundCB = new JCheckBox();
	BorderLayout borderLayout1 = new BorderLayout();
	TitledBorder titledBorder1;
	ButtonGroup soundGroup = new ButtonGroup();
	JPanel jPanel2 = new JPanel();
	JButton soundFileBrowseB = new JButton();
	GridLayout gridLayout1 = new GridLayout();
	JPanel jPanel1 = new JPanel();
	JRadioButton soundBeepRB = new JRadioButton();
	JLabel jLabel6 = new JLabel();
	JTextField soundFile = new JTextField();
	JRadioButton soundDefaultRB = new JRadioButton();
	BorderLayout borderLayout3 = new BorderLayout();
	JPanel jPanel3 = new JPanel();
	JRadioButton soundCustomRB = new JRadioButton();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel editorConfigPanel = new JPanel(new BorderLayout());
	JPanel econfPanel = new JPanel(new GridLayout(5, 2));
	Vector fontnames = getFontNames();
	JComboBox normalFontCB = new JComboBox(fontnames);
	JComboBox headerFontCB = new JComboBox(fontnames);
	JComboBox monoFontCB = new JComboBox(fontnames);
	JSpinner baseFontSize = new JSpinner();
	JCheckBox antialiasChB = new JCheckBox();
	JLabel normalFontLabel = new JLabel();
	JLabel headerFontLabel = new JLabel();
	JLabel monoFontLabel = new JLabel();
	JLabel baseFontSizeLabel = new JLabel();
	String[] languages = {"English" , "Espanol"};
	JComboBox languageCB = new JComboBox(languages);
	JLabel languageLabel = new JLabel("Language Selection");
	
	JButton calcButton = new JButton("Progress Calculation Preferences");
	
	public PreferencesDialog(Frame frame) {
		super(frame, Local.getString("Preferences"), true);
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

	public PreferencesDialog() {
		this(null);
	}

	void jbInit() throws Exception {
		titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(
				Color.white, new Color(156, 156, 158)), Local
				.getString("Sound"));
		this.setResizable(true);
		
		
		enableSoundCB.setText(Local.getString("Enable sound notifications"));
		enableSoundCB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableSoundCB_actionPerformed(e);
			}
		});
		soundPanel.setLayout(borderLayout1);
		soundFileBrowseB.setText(Local.getString("Browse"));
		soundFileBrowseB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundFileBrowseB_actionPerformed(e);
			}
		});
		gridLayout1.setRows(4);
		jPanel1.setBorder(titledBorder1);
		jPanel1.setLayout(gridLayout1);
		soundBeepRB.setText(Local.getString("System beep"));
		soundBeepRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundBeepRB_actionPerformed(e);
			}
		});
		jLabel6.setText(Local.getString("Sound file") + ":");
		soundDefaultRB.setText(Local.getString("Default"));
		soundDefaultRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundDefaultRB_actionPerformed(e);
			}
		});
		jPanel3.setLayout(borderLayout3);
		soundCustomRB.setText(Local.getString("Custom"));
		soundCustomRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundCustomRB_actionPerformed(e);
			}
		});
		jPanel2.setLayout(borderLayout2);
		soundPanel.add(jPanel2, BorderLayout.CENTER);
		jPanel2.add(jPanel1, BorderLayout.NORTH);
		
		jPanel1.add(soundDefaultRB, null);
		jPanel1.add(soundBeepRB, null);
		jPanel1.add(soundCustomRB, null);
		this.soundGroup.add(soundDefaultRB);
		this.soundGroup.add(soundBeepRB);
		this.soundGroup.add(soundCustomRB);
		jPanel1.add(jPanel3, null);
		jPanel3.add(soundFile, BorderLayout.CENTER);
		jPanel3.add(soundFileBrowseB, BorderLayout.EAST);
		jPanel3.add(jLabel6, BorderLayout.WEST);

		jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel2.setText(Local.getString("Window close action:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(2, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel2, gbc);
		closeGroup.add(closeExitRB);
		closeExitRB.setSelected(true);
		closeExitRB.setText(Local.getString("Close and exit"));
		closeExitRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeExitRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(closeExitRB, gbc);

		closeGroup.add(closeHideRB);
		closeHideRB.setText(Local.getString("Minimize to system tray"));
		closeHideRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeHideRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(closeHideRB, gbc);
		jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel3.setText(Local.getString("Look and feel:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(2, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel3, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(lfSystemRB, gbc);
		lfSystemRB.setText(Local.getString("System Default"));
		lfSystemRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfCustomRB_actionPerformed(e);
			}
		});
		lfGroup.add(lfSystemRB);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(lfJavaRB, gbc);
		lfJavaRB.setText(Local.getString("Java Default"));
		lfJavaRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfCustomRB_actionPerformed(e);
			}
		});
		lfGroup.add(lfJavaRB);
		lfGroup.add(lfCustomRB);
		lfCustomRB.setText(Local.getString("Custom"));
		lfCustomRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lfCustomRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(lfCustomRB, gbc);
		classNameLabel.setEnabled(false);
		classNameLabel.setText(Local.getString("L&F class name:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.insets = new Insets(2, 20, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(classNameLabel, gbc);
		lfClassName.setEnabled(false);
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.insets = new Insets(7, 20, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		GeneralPanel.add(lfClassName, gbc);
		jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel4.setText(Local.getString("Startup:"));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(2, 10, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel4, gbc);
		enSystrayChB.setText(Local.getString("Enable system tray icon"));
		enSystrayChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enSystrayChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enSystrayChB, gbc);
		startMinimizedChB.setText(Local.getString("Start minimized"));
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(startMinimizedChB, gbc);
		enSplashChB.setText(Local.getString("Show splash screen"));
		enSplashChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enSplashChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enSplashChB, gbc);
		enL10nChB.setText(Local.getString("Enable localization"));
		enL10nChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enL10nChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 12;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(enL10nChB, gbc);
		firstdow.setText(Local.getString("First day of week - Monday"));
		firstdow.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 13;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(firstdow, gbc);
		lblExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExit.setText(Local.getString("Exit") + ":");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.insets = new Insets(2, 0, 10, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(lblExit, gbc);
		askConfirmChB.setSelected(true);
		askConfirmChB.setText(Local.getString("Ask confirmation"));
		askConfirmChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				askConfirmChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 14;
		gbc.insets = new Insets(2, 0, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(askConfirmChB, gbc);
		
		lblSwitchProj.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSwitchProj.setText(Local.getString("Auto-switch projects") + ":");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 15;
		gbc.insets = new Insets(2, 0, 10, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(lblSwitchProj, gbc);
		askConfirmProjectChB.setSelected(true);
		askConfirmProjectChB.setText(Local.getString("Ask confirmation"));
		askConfirmProjectChB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				askConfirmProjectChB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 15;
		gbc.insets = new Insets(2, 0, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(askConfirmProjectChB, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 16;
		gbc.insets = new Insets(2, 0, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel7, gbc);
		jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel7.setText(Local.getString("Time format:"));
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 15;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(standardTimeRB, gbc);
		timeGroup.add(standardTimeRB);
		standardTimeRB.setSelected(true);
		standardTimeRB.setText(Local.getString("12 Hour"));
		standardTimeRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				standardTimeRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 16;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(standardTimeRB, gbc);

		timeGroup.add(militaryTimeRB);
		militaryTimeRB.setText(Local.getString("24 Hour"));
		militaryTimeRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				militaryTimeRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 17;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(militaryTimeRB, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 18;
		gbc.insets = new Insets(2, 0, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel9, gbc);
		jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel9.setText(Local.getString("Auto-switch to new projects:"));
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 17;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(confirmSwitchRB, gbc);
		projectGroup.add(confirmSwitchRB);
		confirmSwitchRB.setSelected(true);
		confirmSwitchRB.setText(Local.getString("Yes"));
		confirmSwitchRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmSwitchRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 18;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(confirmSwitchRB, gbc);

		projectGroup.add(declineSwitchRB);
		declineSwitchRB.setText(Local.getString("No"));
		declineSwitchRB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				declineSwitchRB_actionPerformed(e);
			}
		});
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 19;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(declineSwitchRB, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 20;
		gbc.insets = new Insets(2, 0, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel8, gbc);
		jLabel8.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel8.setText(String.format("%s:", Local.getString("Task Progress Calculation")));
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 20;
		gbc.insets = new Insets(7, 0, 10, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(calcButton, gbc);
		calcButton.setToolTipText("Controls how task progress is calculated");
		calcButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e){
				TaskCalcDialog dlg = new TaskCalcDialog(App.getFrame());
		        dlg.pack();
		        
		        Dimension frmSize = App.getFrame().getSize();
		        Point loc = App.getFrame().getLocation();
		        
		        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
		        dlg.setVisible(true);
		        
		        if (dlg.CANCELLED) {
		            return;            
		        }
		        
		        if(dlg.calcEffortChB.isSelected()) {
		        	Configuration.put("CALC_EFFORT", "yes");
		        }
		        else{
		        	Configuration.put("CALC_EFFORT", "no");
		        }
		        
		        if(dlg.compactDatesChB.isSelected()) {
		           Configuration.put("COMPACT_DATES", "yes");
		        }
		        else{
		        	Configuration.put("COMPACT_DATES", "no");
		        }
		        
		        if(dlg.calcCompletionChB.isSelected()) {
		            Configuration.put("CALC_COMPLETION", "yes");
		        }
		        else{
		        	Configuration.put("CALC_COMPLETION", "no");
		        }
		        Configuration.saveConfig();
			}
		});
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 21;
		gbc.insets = new Insets(2, 0, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel9, gbc);
		jLabel9.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel9.setText(Local.getString("Default snooze duration") + ":");
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 21;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		GeneralPanel.add(snoozeVals, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 22;
		gbc.insets = new Insets(2, 0, 0, 15);
		gbc.anchor = GridBagConstraints.EAST;
		GeneralPanel.add(jLabel10, gbc);
		jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel10.setText(Local.getString("Maximum snoozes allowed") + ":");
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 22;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		GeneralPanel.add(maxSnoozeAmt, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 23;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		GeneralPanel.add(languageLabel, gbc);
		
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 24;
		gbc.insets = new Insets(2, 0, 0, 10);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		GeneralPanel.add(languageCB, gbc);
		languageCB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (languageCB.getSelectedItem().equals("English")){
					Local.setLocale(new Locale("en"));
					Configuration.put("LOCALES_DIR", "en");
					JOptionPane.showMessageDialog(null, "Change will not take place until "
							+ "Memoranda is restarted.");
				}
				else if(languageCB.getSelectedItem().equals("Espanol")){
					Local.setLocale(new Locale("es"));
					Configuration.put("LOCALES_DIR", "es");
					JOptionPane.showMessageDialog(null, "Los cambios no se van a aplicar hasta "
							+ "que se reinicie Memoranda.");
				}
			}
		});
		
		rstPanelBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		
		JButton resourceSelector = new JButton();
		
		resourceSelector.setText("Choose resource location");
		resourceSelector.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser picker = new JFileChooser();
				picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				picker.showOpenDialog(picker);
				File chosen = picker.getSelectedFile();
				Configuration.put("RESOURCE_PATH", chosen.toString());
			}
		});
		
		
		
		JButton resetter = new JButton();
		resetter.setText("Reset Resource Location");
		resetter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Configuration.put("RESOURCE_PATH", "");
			}
		});
		
		JPanel buttonPane = new JPanel();
		buttonPane.add(resourceSelector);
		buttonPane.add(resetter);
		resourcePanel.add(buttonPane, BorderLayout.WEST);
		normalFontLabel.setText(Local.getString("Normal text font"));
		normalFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		headerFontLabel.setText(Local.getString("Header font"));
		headerFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		monoFontLabel.setText(Local.getString("Monospaced font"));
		monoFontLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		baseFontSizeLabel.setText(Local.getString("Base font size"));
		baseFontSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		antialiasChB.setText(Local.getString("Antialias text"));
		antialiasChB.setToolTipText("Smoothes the edges of text");
		JPanel bfsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		bfsPanel.add(baseFontSize);
		econfPanel.add(normalFontLabel);
		econfPanel.add(normalFontCB);
		econfPanel.add(headerFontLabel);
		econfPanel.add(headerFontCB);
		econfPanel.add(monoFontLabel);
		econfPanel.add(monoFontCB);
		econfPanel.add(baseFontSizeLabel);
		econfPanel.add(bfsPanel);
		econfPanel.add(antialiasChB);
		econfPanel.setBorder(BorderFactory.createEmptyBorder(10,5,10,10));
		((GridLayout)econfPanel.getLayout()).setHgap(10);
		((GridLayout)econfPanel.getLayout()).setVgap(5);
		editorConfigPanel.add(econfPanel, BorderLayout.NORTH);
		// Build TabbedPanel
		tabbedPanel.add(GeneralPanel, Local.getString("General"));
		tabbedPanel.add(resourcePanel, Local.getString("Resource location"));
		tabbedPanel.add(soundPanel, Local.getString("Sound"));
		tabbedPanel.add(editorConfigPanel, Local.getString("Editor"));

		// Build TopPanel
		topPanel.add(tabbedPanel, BorderLayout.CENTER);

		// Build BottomPanel
		okB.setMaximumSize(new Dimension(100, 25));
		okB.setPreferredSize(new Dimension(100, 25));
		okB.setText(Local.getString("Ok"));
		okB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okB_actionPerformed(e);
			}
		});
		this.getRootPane().setDefaultButton(okB);
		bottomPanel.add(okB);
		cancelB.setMaximumSize(new Dimension(100, 25));
		cancelB.setPreferredSize(new Dimension(100, 25));
		cancelB.setText(Local.getString("Cancel"));
		cancelB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelB_actionPerformed(e);
			}
		});
		bottomPanel.add(cancelB);

		// Build Preferences-Dialog
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		soundPanel.add(enableSoundCB, BorderLayout.NORTH);

		// set all config-values
		setValues();

	}

	void setValues() {
		enL10nChB.setSelected(!Configuration.get("DISABLE_L10N").toString()
				.equalsIgnoreCase("yes"));
		enSplashChB.setSelected(!Configuration.get("SHOW_SPLASH").toString()
				.equalsIgnoreCase("no"));
		enSystrayChB.setSelected(!Configuration.get("DISABLE_SYSTRAY")
				.toString().equalsIgnoreCase("yes"));
		startMinimizedChB.setSelected(Configuration.get("START_MINIMIZED")
				.toString().equalsIgnoreCase("yes"));
		firstdow.setSelected(Configuration.get("FIRST_DAY_OF_WEEK").toString()
				.equalsIgnoreCase("mon"));
		
		String timeform = Configuration.get("TIME_FORMAT").toString();
		if (timeform.equalsIgnoreCase("standard"))
			standardTimeRB.setSelected(true);
		else if (timeform.equalsIgnoreCase("military"))
			militaryTimeRB.setSelected(true);
		
		String autoSelect = Configuration.get("AUTO_SWITCH_PROJECT").toString();
		if (autoSelect.equalsIgnoreCase("yes"))
			confirmSwitchRB.setSelected(true);
		else if (autoSelect.equalsIgnoreCase("no"))
			declineSwitchRB.setSelected(true);

		String onNew = Configuration.get("ASK_ON_NEW").toString();
		if (onNew.equalsIgnoreCase("yes"))
			askConfirmProjectChB.setSelected(true);
		else if (onNew.equalsIgnoreCase("no"))
			askConfirmProjectChB.setSelected(false);
		
		enableCustomLF(false);
		String lf = Configuration.get("LOOK_AND_FEEL").toString();
		if (lf.equalsIgnoreCase("system"))
			lfSystemRB.setSelected(true);
		else if (lf.equalsIgnoreCase("default"))
			lfJavaRB.setSelected(true);
		else if (lf.length() > 0) {
			lfCustomRB.setSelected(true);
			enableCustomLF(true);
			lfClassName.setText(lf);
		} else
			lfJavaRB.setSelected(true);

		askConfirmChB.setSelected(!Configuration.get("ASK_ON_EXIT").toString()
				.equalsIgnoreCase("no"));
		String onclose = Configuration.get("ON_CLOSE").toString();
		if (onclose.equals("exit")) {
			this.closeExitRB.setSelected(true);
			// this.askConfirmChB.setEnabled(true);
		} else {
			this.closeHideRB.setSelected(true);
			// this.askConfirmChB.setEnabled(false);
		}

		String onmin = Configuration.get("ON_MINIMIZE").toString();
		this.minTaskbarRB.setSelected(true);

		if (Configuration.get("NOTIFY_SOUND").equals("")) {
			Configuration.put("NOTIFY_SOUND", "DEFAULT");
		}

		boolean enableSnd = !Configuration.get("NOTIFY_SOUND").toString()
				.equalsIgnoreCase("DISABLED");
		enableSoundCB.setSelected(enableSnd);
		if (Configuration.get("NOTIFY_SOUND").toString().equalsIgnoreCase(
				"DEFAULT")
				|| Configuration.get("NOTIFY_SOUND").toString()
						.equalsIgnoreCase("DISABLED")) {
			this.soundDefaultRB.setSelected(true);
			this.enableCustomSound(false);
		} else if (Configuration.get("NOTIFY_SOUND").toString()
				.equalsIgnoreCase("BEEP")) {
			this.soundBeepRB.setSelected(true);
			this.enableCustomSound(false);
		} else {
			System.out.println(Configuration.get("NOTIFY_SOUND").toString());
			this.soundCustomRB.setSelected(true);
			this.soundFile
					.setText(Configuration.get("NOTIFY_SOUND").toString());
			this.enableCustomSound(true);
		}
		this.enableSound(enableSnd);
		
		antialiasChB.setSelected(Configuration.get("ANTIALIAS_TEXT")
				.toString().equalsIgnoreCase("yes"));
		if (Configuration.get("NORMAL_FONT").toString().length() >0)
			normalFontCB.setSelectedItem(Configuration.get("NORMAL_FONT").toString());
		else
			normalFontCB.setSelectedItem("serif");
		if (Configuration.get("HEADER_FONT").toString().length() >0)
			headerFontCB.setSelectedItem(Configuration.get("HEADER_FONT").toString());
		else
			headerFontCB.setSelectedItem("sans-serif");
		if (Configuration.get("MONO_FONT").toString().length() >0)
			monoFontCB.setSelectedItem(Configuration.get("MONO_FONT").toString());
		else
			monoFontCB.setSelectedItem("monospaced");
		if (Configuration.get("BASE_FONT_SIZE").toString().length() >0)
			baseFontSize.setValue(Integer.decode(Configuration.get("BASE_FONT_SIZE").toString()));
		else
			baseFontSize.setValue(new Integer(16));
		
		String snoozeDuration = Configuration.get("SNZ_DURATION").toString();
		if (snoozeDuration.matches("^[0-9]+ ?(minutes?|hours?)$"))
			snoozeVals.setSelectedItem(snoozeDuration);
		else
			snoozeVals.setSelectedIndex(0);
		String maxSnoozeAmount = Configuration.get("SNZ_AMOUNT").toString();
		if (maxSnoozeAmount.matches("^[0-9]+$")) 
			maxSnoozeAmt.setText(maxSnoozeAmount);
		else 
			maxSnoozeAmt.setText("0");
		
	}

	void apply() {
		
		if (this.standardTimeRB.isSelected())
			Configuration.put("TIME_FORMAT", "standard");
		else
			Configuration.put("TIME_FORMAT", "military");
		
		if (this.confirmSwitchRB.isSelected())
			Configuration.put("AUTO_SWITCH_PROJECT", "yes");
		else
			Configuration.put("AUTO_SWITCH_PROJECT", "no");
		
		if (this.firstdow.isSelected())
			Configuration.put("FIRST_DAY_OF_WEEK", "mon");
		else
			Configuration.put("FIRST_DAY_OF_WEEK", "sun");

		if (this.enL10nChB.isSelected())
			Configuration.put("DISABLE_L10N", "no");
		else
			Configuration.put("DISABLE_L10N", "yes");

		if (this.enSplashChB.isSelected())
			Configuration.put("SHOW_SPLASH", "yes");
		else
			Configuration.put("SHOW_SPLASH", "no");

		if (this.enSystrayChB.isSelected())
			Configuration.put("DISABLE_SYSTRAY", "no");
		else
			Configuration.put("DISABLE_SYSTRAY", "yes");

		if (this.startMinimizedChB.isSelected())
			Configuration.put("START_MINIMIZED", "yes");
		else
			Configuration.put("START_MINIMIZED", "no");

		if (this.askConfirmChB.isSelected())
			Configuration.put("ASK_ON_EXIT", "yes");
		else
			Configuration.put("ASK_ON_EXIT", "no");
		
		if (this.askConfirmProjectChB.isSelected())
			Configuration.put("ASK_ON_NEW", "yes");
		else
			Configuration.put("ASK_ON_NEW", "no");

		if (this.closeExitRB.isSelected())
			Configuration.put("ON_CLOSE", "exit");
		else
			Configuration.put("ON_CLOSE", "minimize");

		Configuration.put("ON_MINIMIZE", "normal");

		String lf = Configuration.get("LOOK_AND_FEEL").toString();
		String newlf = "";

		if (this.lfSystemRB.isSelected())
			newlf = "system";
		else if (this.lfJavaRB.isSelected())
			newlf = "default";
		else if (this.lfCustomRB.isSelected())
			newlf = this.lfClassName.getText();

		if (!lf.equalsIgnoreCase(newlf)) {
			Configuration.put("LOOK_AND_FEEL", newlf);
			try {
				if (Configuration.get("LOOK_AND_FEEL").equals("system"))
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				else if (Configuration.get("LOOK_AND_FEEL").equals("default"))
					UIManager.setLookAndFeel(UIManager
							.getCrossPlatformLookAndFeelClassName());
				else if (Configuration.get("LOOK_AND_FEEL").toString().length() > 0)
					UIManager.setLookAndFeel(Configuration.get("LOOK_AND_FEEL")
							.toString());

				SwingUtilities.updateComponentTreeUI(App.getFrame());

			} catch (Exception e) {
				Configuration.put("LOOK_AND_FEEL", lf);
				new ExceptionDialog(
						e,
						"Error when initializing a pluggable look-and-feel. Default LF will be used.",
						"Make sure that specified look-and-feel library classes are on the CLASSPATH.");
			}
		}

		if (!this.enableSoundCB.isSelected())
			Configuration.put("NOTIFY_SOUND", "DISABLED");
		else if (this.soundDefaultRB.isSelected())
			Configuration.put("NOTIFY_SOUND", "DEFAULT");
		else if (this.soundBeepRB.isSelected())
			Configuration.put("NOTIFY_SOUND", "BEEP");
		else if ((this.soundCustomRB.isSelected())
				&& (this.soundFile.getText().trim().length() > 0))
			Configuration.put("NOTIFY_SOUND", this.soundFile.getText().trim());

		if (antialiasChB.isSelected())
			Configuration.put("ANTIALIAS_TEXT", "yes");
		else
			Configuration.put("ANTIALIAS_TEXT", "no");
		
		Configuration.put("NORMAL_FONT", normalFontCB.getSelectedItem());
		Configuration.put("HEADER_FONT", headerFontCB.getSelectedItem());
		Configuration.put("MONO_FONT", monoFontCB.getSelectedItem());
		Configuration.put("BASE_FONT_SIZE", baseFontSize.getValue());
		App.getFrame().workPanel.dailyItemsPanel.editorPanel.editor.editor.setAntiAlias(antialiasChB.isSelected());
		App.getFrame().workPanel.dailyItemsPanel.editorPanel.initCSS();
		App.getFrame().workPanel.dailyItemsPanel.editorPanel.editor.repaint();
		App.getFrame().workPanel.dailyItemsPanel.agendaPanel.refresh(CurrentDate.get());
		App.getFrame().workPanel.dailyItemsPanel.eventsPanel.updateUI();
		
		Configuration.put("SNZ_DURATION",snoozeVals.getSelectedItem());
		Configuration.put("SNZ_AMOUNT", maxSnoozeAmt.getText());
		
		Configuration.saveConfig();
		
	}

	void enableCustomLF(boolean is) {
		this.classNameLabel.setEnabled(is);
		this.lfClassName.setEnabled(is);
	}

	void enableCustomSound(boolean is) {
		this.soundFile.setEnabled(is);
		this.soundFileBrowseB.setEnabled(is);
		this.jLabel6.setEnabled(is);
	}
	

	void enableSound(boolean is) {
		this.soundDefaultRB.setEnabled(is);
		this.soundBeepRB.setEnabled(is);
		this.soundCustomRB.setEnabled(is);
		enableCustomSound(is);

		this.soundFileBrowseB.setEnabled(is && soundCustomRB.isSelected());
		this.soundFile.setEnabled(is && soundCustomRB.isSelected());
		this.jLabel6.setEnabled(is && soundCustomRB.isSelected());

	}

	void okB_actionPerformed(ActionEvent e) {
		apply();
		this.dispose();
	}

	void cancelB_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	void minTaskbarRB_actionPerformed(ActionEvent e) {

	}

	void minHideRB_actionPerformed(ActionEvent e) {

	}

	void closeExitRB_actionPerformed(ActionEvent e) {
		// this.askConfirmChB.setEnabled(true);
	}
	
	void standardTimeRB_actionPerformed(ActionEvent e) {
		//System.out.println("standard time selected");		
	}
	
	void militaryTimeRB_actionPerformed(ActionEvent e) {
		//System.out.println("military time selected");	
	}
	
	void confirmSwitchRB_actionPerformed(ActionEvent e) {
			
	}
	
	void declineSwitchRB_actionPerformed(ActionEvent e) {
		
	}
	
	void askConfirmProjectChB_actionPerformed(ActionEvent e) {
		
	}
	

	void askConfirmChB_actionPerformed(ActionEvent e) {

	}

	void closeHideRB_actionPerformed(ActionEvent e) {
		// this.askConfirmChB.setEnabled(false);
	}

	void lfSystemRB_actionPerformed(ActionEvent e) {
		this.enableCustomLF(false);
	}

	void lfJavaRB_actionPerformed(ActionEvent e) {
		this.enableCustomLF(false);
	}

	void lfCustomRB_actionPerformed(ActionEvent e) {
		if (lfGroup.getSelection().equals(lfCustomRB.getModel()))
			this.enableCustomLF(true);
		else
			this.enableCustomLF(false);
	}

	void enSystrayChB_actionPerformed(ActionEvent e) {

	}

	void enSplashChB_actionPerformed(ActionEvent e) {

	}

	void enL10nChB_actionPerformed(ActionEvent e) {

	}

	void enableSoundCB_actionPerformed(ActionEvent e) {
		enableSound(enableSoundCB.isSelected());
	}

	void soundFileBrowseB_actionPerformed(ActionEvent e) {
		// Fix until Sun's JVM supports more locales...
		UIManager.put("FileChooser.lookInLabelText", Local
				.getString("Look in:"));
		UIManager.put("FileChooser.upFolderToolTipText", Local
				.getString("Up One Level"));
		UIManager.put("FileChooser.newFolderToolTipText", Local
				.getString("Create New Folder"));
		UIManager.put("FileChooser.listViewButtonToolTipText", Local
				.getString("List"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
				.getString("Details"));
		UIManager.put("FileChooser.fileNameLabelText", Local
				.getString("File Name:"));
		UIManager.put("FileChooser.filesOfTypeLabelText", Local
				.getString("Files of Type:"));
		UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
		UIManager.put("FileChooser.openButtonToolTipText", Local
				.getString("Open selected file"));
		UIManager
				.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", Local
				.getString("Cancel"));

		JFileChooser chooser = new JFileChooser();
		chooser.setFileHidingEnabled(false);
		chooser.setDialogTitle(Local.getString("Select the sound file"));
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setPreferredSize(new Dimension(550, 375));
		chooser.setFileFilter(new AllFilesFilter(AllFilesFilter.WAV));
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			this.soundFile.setText(chooser.getSelectedFile().getPath());
	}

	void soundDefaultRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(false);
	}

	void soundBeepRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(false);
	}

	void soundCustomRB_actionPerformed(ActionEvent e) {
		this.enableCustomSound(true);
	}
	
	Vector getFontNames() {
		GraphicsEnvironment gEnv = 
        	GraphicsEnvironment.getLocalGraphicsEnvironment();
        String envfonts[] = gEnv.getAvailableFontFamilyNames();
        Vector fonts = new Vector();
        fonts.add("serif");
        fonts.add("sans-serif");
        fonts.add("monospaced");
        for (int i = 0; i < envfonts.length; i++)
            fonts.add(envfonts[i]);
		return fonts;
	}
	
	public static Vector<String> getSnoozeDurations() {
		Vector<String> durations = new Vector<String>();
		durations.add("1 minute");
		durations.add("5 minutes");
		durations.add("10 minutes");
		durations.add("15 minutes");
		durations.add("30 minutes");
		durations.add("45 minutes");
		durations.add("1 hour");
		durations.add("2 hours");
		durations.add("3 hours");
		durations.add("6 hours");
		durations.add("12 hours");
		return durations;
	}
}
