package net.sf.memoranda.ui;

/**
 * Classname: WizardFactory
 * 
 * Description: This class follows the Factory design pattern, and returns
 * a Wizard object based on user input. Key word "DEFECT" returns the 
 * Defect Wizard, key word "ESTIMATE" returns the Size Estimation Wizard
 * and key word "ACCOUNT" returns the Size Accounting Wizard. These wizards
 * are used for data collection for use in the Personal Software Process.
 * 
 * Version: 1.0
 * 
 * Date: 2/21/20156 
 * 
 */

import javax.swing.JFrame;

public class WizardFactory {

	/**
	 * Description: This method returns a Wizard object based on given input. The key
	 * word "DEFECT" returns the Defect Wizard, key word "ESTIMATE" returns the
	 * Size Estimation Wizard and key word "ACCOUNT" returns the Size Accounting
	 * Wizard. These wizards are used for data collection for use in the
	 * Personal Software Process.
	 * 
	 * @param wizardType, the type of Wizard to return
	 * @return Wizard object based on valid user input, null otherwise
	 */
	public Wizard getWizard(String wizardType) {

		if (wizardType == null) {
			return null;
		}
		if (wizardType.equalsIgnoreCase("DEFECT")) {
			return new DefectWizard(new JFrame(), "Defect Wizard");

		} else if (wizardType.equalsIgnoreCase("ESTIMATE")) {
			return new SizeEstimationWizard(new JFrame(), "Size Estimation Wizard");

		} else if (wizardType.equalsIgnoreCase("ACCOUNT")) {
			return new SizeAccountingWizard(new JFrame(), "Size Accounting Wizard");
		}

		return null;
	}

}