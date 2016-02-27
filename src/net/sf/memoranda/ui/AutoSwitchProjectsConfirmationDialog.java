package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Configuration;

@SuppressWarnings("serial")
public class AutoSwitchProjectsConfirmationDialog extends JDialog{
    
	public JLabel header = new JLabel();
	JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel bottomPanel = new JPanel(new BorderLayout());
	
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    JButton yesB = new JButton();
    JButton noB = new JButton();
	
	public JCheckBox donotaskCB = new JCheckBox();
	
	JPanel mainPanel = new JPanel(new BorderLayout());
	
    public AutoSwitchProjectsConfirmationDialog(Frame frame, String title) {
       super(frame, title, true);
       try {
           jbInit();
           pack();
       }
       catch (Exception ex) {
           new ExceptionDialog(ex);
       }
    }

	void jbInit() throws Exception {
		this.setResizable(false);
        
		headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("NOTICE"));
        header.setIcon(new ImageIcon(net.sf.memoranda.ui.EventDialog.class.getResource(
            "resources/icons/pr_highest.png")));
        headerPanel.add(header);
		
		JLabel confirm = new JLabel();
		confirm.setText("<HTML>"+Local.getString("Do you want to automatically switch to new projects?"));
										
		donotaskCB.setText(Local.getString("do not ask again"));
		donotaskCB.setHorizontalAlignment(SwingConstants.CENTER);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		mainPanel.add(donotaskCB,BorderLayout.SOUTH);
		mainPanel.add(confirm,BorderLayout.CENTER);
		
	    yesB.setMaximumSize(new Dimension(100, 26));
        yesB.setMinimumSize(new Dimension(100, 26));
        yesB.setPreferredSize(new Dimension(100, 26));
        yesB.setText(Local.getString("Yes"));
        yesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yesB_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(yesB);
        noB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                noB_actionPerformed(e);
            }
        });
        noB.setText(Local.getString("No"));
        noB.setPreferredSize(new Dimension(100, 26));
        noB.setMinimumSize(new Dimension(100, 26));
        noB.setMaximumSize(new Dimension(100, 26));
        buttonsPanel.add(yesB);
        buttonsPanel.add(noB);
		bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		this.getRootPane().setDefaultButton(yesB);
		
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.getContentPane().add(headerPanel, BorderLayout.NORTH);
		this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void  checkDoNotAsk() {
		if (this.donotaskCB.isSelected()) {
			Configuration.put("ASK_ON_NEW", "no");
			Configuration.saveConfig();
		}
	}
	
	void yesB_actionPerformed(ActionEvent e) {
		checkDoNotAsk();
		Configuration.put("AUTO_SWITCH_PROJECT", "yes");
        this.dispose();
    }

	void noB_actionPerformed(ActionEvent e) {
    	checkDoNotAsk();
		Configuration.put("AUTO_SWITCH_PROJECT", "no");
        this.dispose();
    }
	
}
