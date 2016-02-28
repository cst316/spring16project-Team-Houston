package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;

import net.sf.memoranda.util.Util;

/**
 * PSPControlPanel
 * 
 * Panel for adding time tracker, bug tracker, LOC tracker. Replaces Notes Panel
 * 
 * Version 1
 * 
 * 2/18/2016
 */
public class PSPControlPanel extends JLayeredPane {
    
	private List<PSPWidgetPanel> widgets;

	/**
	 * Constructor
	 * 
	 * @param
	 * 
	 * @return
	 */
	
    public PSPControlPanel() {
        try {
            jbInit();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    
    /**
     * Code to execute when frame refreshes
     * 
     * @param
     * 
     * @return
     */
    public void refresh() {
        
    }
    
    /**
     * Code to execute when frame is instantiated
     * 
     * @throws Exception
     */
    private void jbInit() throws Exception {
        widgets = new ArrayList<PSPWidgetPanel>();
        setLayout(new GridLayout(2,2));
        PSPWidgetPanel timePanel = new PSPWidgetPanel("Time Entry");
        TimeTable timeTable = new TimeTable();
        timePanel.setContentPane(new JPanel(new BorderLayout()) {{
        		add(new JScrollPane(timeTable), BorderLayout.CENTER);
        	}
        });
        timePanel.setButton(new JButton("Start Time Entry Wizard") {{
        		this.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						ManualTimeEntryDialog mted = new ManualTimeEntryDialog();
						mted.setVisible(true);
						timeTable.tableChanged();
					}
        		});
        	}
        });
        widgets.add(timePanel);
        updatePanels();
        setVisible(true);
    }
    
    private void updatePanels() {
    	removeAll();
    	for (int i = 0; i < widgets.size(); i++) {
    		PSPWidgetPanel lePanel = widgets.get(i);
    		add(lePanel, JLayeredPane.DEFAULT_LAYER);
        }
    	revalidate();
    	repaint();
    }

    

   



   

}
