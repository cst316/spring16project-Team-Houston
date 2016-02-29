package net.sf.memoranda.test;

import org.junit.*;

import nu.xom.Attribute;
import nu.xom.Element;
import net.sf.memoranda.History;
import net.sf.memoranda.HistoryItem;
import net.sf.memoranda.HistoryListener;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.date.CalendarDate;
import static org.junit.Assert.*;

public class HistoryTest {
	@Test
	public void testHistory_1()
		throws Exception {
		History result = new History();
		assertNotNull(result);
	}

	@Test
	public void testAdd_1()
		throws Exception {
		Element testElem = new Element("test");
		testElem.addAttribute(new Attribute("id", "test1"));
		Project prj = new ProjectImpl(testElem);
		HistoryItem item = new HistoryItem(new CalendarDate(), prj);
		History.add(item);

	}

	@Test
	public void testAddHistoryListener_1()
		throws Exception {
		HistoryListener hl = null;
		History.addHistoryListener(hl);
	}

	@Test
	public void testCanRollForward_1()
		throws Exception {
		boolean result = History.canRollForward();
		assertEquals(false, result);
	}

	@Test
	public void testRemoveProjectHistory_1()
		throws Exception {
		Element testElem = new Element("test");
		testElem.addAttribute(new Attribute("id", "test2"));
		Project prj = new ProjectImpl(testElem);
		HistoryItem item = new HistoryItem(new CalendarDate(), prj);
		History.add(item);
		History.removeProjectHistory(prj);

	}

	@Test
	public void testRollForward_1()
		throws Exception {
		HistoryItem result = History.rollForward();
		assertEquals(null, result);
	}

	@Before
	public void setUp()
		throws Exception {
	}

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(HistoryTest.class);
	}
}
