package net.sf.memoranda.test;

import java.util.Vector;

import junit.framework.TestCase;
import net.sf.memoranda.ui.PreferencesDialog;

public class PreferencesDialogTest extends TestCase {

	public PreferencesDialogTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Run the Vector<String> getSnoozeDurations() method test
	 */
	public void testGetSnoozeDurations() {
		Vector<String> result = PreferencesDialog.getSnoozeDurations();
		assertNotNull(result);
		assertEquals("1 minute", result.get(0));
		assertEquals("12 hours", result.get(result.size() - 1));
		assertTrue(result.size() > 2);
	}
}