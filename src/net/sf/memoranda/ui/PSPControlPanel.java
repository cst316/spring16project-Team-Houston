package net.sf.memoranda.ui;

import javax.swing.JPanel;

public class PSPControlPanel extends JPanel {
    


	
    public PSPControlPanel() {
        try {
            jbInit();

        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        
    }

    public void refresh() {
        
    }

    void tabbedPane_stateChanged(ChangeEvent e) {
	
    }

   



   

}