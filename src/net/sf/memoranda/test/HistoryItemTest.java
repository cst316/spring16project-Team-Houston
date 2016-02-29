package net.sf.memoranda.test;

import org.junit.*;

import nu.xom.Attribute;
import nu.xom.Element;
import net.sf.memoranda.HistoryItem;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.date.CalendarDate;
import static org.junit.Assert.*;

public class HistoryItemTest {
	@Test
	public void testHistoryItem_1()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Project project = new ProjectImpl(new Element("test"));
		HistoryItem result = new HistoryItem(date, project);

		assertNotNull(result);
	}

	@Test
	public void testEquals_1()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("id", "test"));
		HistoryItem fixture = new HistoryItem(new CalendarDate(), new ProjectImpl(test));
		HistoryItem i = new HistoryItem(new CalendarDate(), new ProjectImpl(test));
		boolean result = fixture.equals(i);

		assertTrue(result);
	}
	
	@Test
	public void testEquals_2()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("id", "test"));
		Element test2 = new Element("test");
		test2.addAttribute(new Attribute("id", "test2"));
		HistoryItem fixture = new HistoryItem(new CalendarDate(), new ProjectImpl(test));
		HistoryItem i = new HistoryItem(new CalendarDate(), new ProjectImpl(test2));
		boolean result = fixture.equals(i);

		assertFalse(result);
	}
	
	@Test
	public void testEquals_3()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("id", "test"));
		HistoryItem fixture = new HistoryItem(new CalendarDate(1,1,1), new ProjectImpl(test));
		HistoryItem i = new HistoryItem(new CalendarDate(1,1,2), new ProjectImpl(test));
		boolean result = fixture.equals(i);

		assertFalse(result);
	}

	@Test
	public void testGetDate_1()
		throws Exception {
		HistoryItem fixture = new HistoryItem(new CalendarDate(), new ProjectImpl(new Element("test")));
		CalendarDate result = fixture.getDate();

		assertNotNull(result);
	}

	@Test
	public void testGetProject_1()
		throws Exception {
		HistoryItem fixture = new HistoryItem(new CalendarDate(), new ProjectImpl(new Element("test")));
		Project result = fixture.getProject();

		assertNotNull(result);
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
		new org.junit.runner.JUnitCore().run(HistoryItemTest.class);
	}
}
