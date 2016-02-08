package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import net.sf.memoranda.Event;
import net.sf.memoranda.util.Configuration;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;


/*$Id: EventNotificationDialog.java,v 1.8 2004/10/18 19:08:56 ivanrise Exp $*/
public class EventNotificationDialog extends JDialog {
  JPanel mainPanel = new JPanel();
  BorderLayout mainLayout = new BorderLayout();
  JButton okButton = new JButton();
  JButton snoozeButton = new JButton();
  JComboBox<String> snoozeDuration = new JComboBox<String>(PreferencesDialog.getSnoozeDurations());
  Border border1;
  Border border2;
  Border border3;
  JPanel buttonPanel = new JPanel();
  GridBagLayout buttonLayout = new GridBagLayout();
  JLabel textLabel = new JLabel();
  JLabel timeLabel = new JLabel();
  Border border4;
  private boolean isSnoozed;
  private static final String FALLBACK_SNZ_DURATION = PreferencesDialog.getSnoozeDurations().get(0);
  private static final int FALLBACK_SNZ_AMOUNT = -1;
  
  public EventNotificationDialog(String title, String time, String text, int timesSnoozed) {
	  	super(App.getFrame(), title, true);
	  	int maxSnoozes;
	    try {
	      jbInit();
	      pack();
	    }
	    catch(Exception ex) {
	      new ExceptionDialog(ex);
	    }
		if(Configuration.get("TIME_FORMAT").toString().equalsIgnoreCase("military")){
			time = Util.getMilitaryTime(time);
		}
		String snoozeDur = Configuration.get("SNZ_DURATION").toString();
		try {
			snoozeDuration.setSelectedItem(snoozeDur);
		}
		catch (Exception e) {
			snoozeDuration.setSelectedItem(FALLBACK_SNZ_DURATION);
		}
		try {
			maxSnoozes = Integer.parseInt(Configuration.get("SNZ_AMOUNT").toString());
		}
		catch (Exception e) {
			maxSnoozes = FALLBACK_SNZ_AMOUNT;
		}
		if (maxSnoozes >= 0 && timesSnoozed >= maxSnoozes) {
			snoozeButton.setEnabled(false);
			snoozeDuration.setEnabled(false);
		}
	    timeLabel.setText(time);
	    timeLabel.setIcon(new ImageIcon(net.sf.memoranda.ui.TaskDialog.class.getResource(
	            "resources/icons/event48.png")));
	    textLabel.setText(text);
	    this.setSize(300,200);
	    this.setLocationRelativeTo(null);
	    this.toFront();
	    this.requestFocus();
	    isSnoozed = false;
  }
  
  public EventNotificationDialog(String title, String time, String text) {
    this(title, time, text, 0);
  }
  
  public EventNotificationDialog(String title, Event ev) {
	  this(title, ev.getTimeString(), ev.getText(), ev.getTimesSnoozed());
  }

  public EventNotificationDialog() {
    this("", "", "", 0);
  }
  void jbInit() throws Exception {
    this.setResizable(false);
    this.setIconImage(new ImageIcon(EventNotificationDialog.class.getResource("resources/icons/jnotes16.png")).getImage());
    this.getContentPane().setBackground(new Color(251, 197, 63));
    border2 = BorderFactory.createEmptyBorder(0,30,0,30);
    border3 = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),BorderFactory.createEmptyBorder(0,30,0,30));
    border4 = BorderFactory.createEmptyBorder(10,10,0,10);
    mainPanel.setLayout(mainLayout);
    mainPanel.setBackground(new Color(251, 197, 63));
    
    okButton.setText(Local.getString("Ok"));
    okButton.setBounds(150, 415, 95, 30);
    okButton.setPreferredSize(new Dimension(95, 30));
    okButton.setBackground(new Color(69, 125, 186));
    okButton.setDefaultCapable(true);
    okButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        okButton_actionPerformed(e);
      }
    });
    snoozeButton.setText(Local.getString("Snooze"));
    snoozeButton.setBounds(150, 415, 95, 30);
    snoozeButton.setPreferredSize(new Dimension(95, 30));
    snoozeButton.setBackground(new Color(69, 125, 186));
    snoozeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        snoozeButton_actionPerformed(e);
      }
    });
    mainPanel.setBorder(border4);
    mainPanel.setMinimumSize(new Dimension(300, 200));
    mainPanel.setPreferredSize(new Dimension(300, 200));
    GridBagConstraints blc = new GridBagConstraints();
    buttonPanel.setLayout(buttonLayout);
    blc.gridy = 0;
    blc.gridx = 0;
    blc.gridwidth = 1;
    blc.weightx = 1d;
    blc.fill = GridBagConstraints.HORIZONTAL;
    buttonPanel.add(snoozeDuration, blc);
    blc.gridx = 1;
    blc.insets = new Insets(0,5,0,5);
    buttonPanel.add(snoozeButton, blc);
    blc.insets = new Insets(0,0,0,0);
    blc.gridx = 2;
    buttonPanel.add(okButton, blc);
    buttonPanel.setBackground(new Color(251, 197, 63));
    timeLabel.setFont(new java.awt.Font("Dialog", 0, 20));
    timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    textLabel.setHorizontalAlignment(SwingConstants.CENTER);
    getContentPane().add(mainPanel);
    mainPanel.add(textLabel, BorderLayout.CENTER);
    mainPanel.add(timeLabel, BorderLayout.NORTH);
    mainPanel.add(buttonPanel,  BorderLayout.SOUTH);
    playSoundNotification();
  }

  void okButton_actionPerformed(ActionEvent e) {
	  this.dispose();
  }
  
  void snoozeButton_actionPerformed(ActionEvent e) {
	  isSnoozed = true;
	  this.dispose();
  }
  
  private void playSoundNotification() {
  	if (Configuration.get("NOTIFY_SOUND").equals("DISABLED"))
		return;
	if (Configuration.get("NOTIFY_SOUND").equals("BEEP")) {
		java.awt.Toolkit.getDefaultToolkit().beep();
		return;
	}
	if (Configuration.get("NOTIFY_SOUND").equals("")) {
		Configuration.put("NOTIFY_SOUND", "DEFAULT");
		Configuration.saveConfig();
	}
	URL url;
	if (Configuration.get("NOTIFY_SOUND").equals("DEFAULT"))
		url =
			EventNotificationDialog.class.getResource(
				"resources/beep.wav");
	else
		try {
			url =
				new File(Configuration.get("NOTIFY_SOUND").toString())
					.toURL();
		} catch (Exception ex) {
			url =
				EventNotificationDialog.class.getResource(
					"resources/beep.wav");
		}
	try {
		AudioClip clip = Applet.newAudioClip(url);
		clip.play();
	} catch (Exception ex) {
		new ExceptionDialog(ex, "Error loading audioclip from "+url, "Check the location and type of audioclip file.");
	}
  }
  
  public boolean isSnoozed() {
	  return isSnoozed;
  }
  
  public String getSnoozeDuration() {
	  return (String)snoozeDuration.getSelectedItem();
  }
}
