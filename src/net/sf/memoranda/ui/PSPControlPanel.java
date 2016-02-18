package net.sf.memoranda.ui;

import javax.swing.JPanel;


import javax.swing.event.ChangeEvent;

/**
 * PSPControlPanel
 * 
 * Panel for adding time tracker, bug tracker, LOC tracker. Replaces Notes Panel
 * 
 * Version 1
 * 
 * 2/18/2016
 */
public class PSPControlPanel extends JPanel {
    

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
     * Code to execute when state of frame changes
     * 
     * @param e
     */
    void tabbedPane_stateChanged(ChangeEvent e) {
	
    }
    
    /**
     * Code to execute when frame is instantiated
     * 
     * @throws Exception
     */
    void jbInit() throws Exception {
        
    }

    

   



   

}