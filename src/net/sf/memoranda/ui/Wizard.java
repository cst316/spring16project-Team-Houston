package net.sf.memoranda.ui;

/**
 * Classname: Wizard
 * 
 * Description: This is the Interface for PSP Wizard objects.
 * 
 * Version: 1.0
 * 
 * Date: 2/21/20156 
 * 
 */

import java.awt.event.ActionEvent;

public interface Wizard {

	public void jbInit() throws Exception;
	public void changed();
	public boolean setValues();
	public void finishB_actionPerformed(ActionEvent e);
	public void cancelB_actionPerformed(ActionEvent e);
	public void setVisible(boolean val);
}