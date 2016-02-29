package net.sf.memoranda.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @generatedBy CodePro at 2/28/16 8:46 PM
 * @author Kyle
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	net.sf.memoranda.test.AllTests.class,
	net.sf.memoranda.test.date.AllTests.class,
	net.sf.memoranda.test.ui.AllTests.class,
	net.sf.memoranda.test.util.AllTests.class
})
public class TestAllPackages {

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 2/28/16 8:46 PM
	 */
	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAllPackages.class });
	}
}
