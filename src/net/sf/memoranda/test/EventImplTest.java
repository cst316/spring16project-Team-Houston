package net.sf.memoranda.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.EventImpl;
import net.sf.memoranda.date.CalendarDate;
import nu.xom.Attribute;
import nu.xom.Element;

public class EventImplTest {
	
	private static Element nullElem;
	private static Element regularElem;
	private static Element repeatElem;
	private EventImpl nullTest;
	private EventImpl regularTest;
	private EventImpl repeatTest;

	@BeforeClass
	public static void init() {
		nullElem = null;
		regularElem = new Element("event");
		regularElem.addAttribute(new Attribute("id","regularTest"));
		regularElem.addAttribute(new Attribute("hour", "0"));
		regularElem.addAttribute(new Attribute("min", "0"));
		regularElem.addAttribute(new Attribute("timesSnoozed", "0"));
		repeatElem = new Element("event");
		repeatElem.addAttribute(new Attribute("id", "repeatTest"));
		repeatElem.addAttribute(new Attribute("hour", "23"));
		repeatElem.addAttribute(new Attribute("min", "59"));
		repeatElem.addAttribute(new Attribute("timesSnoozed", "1"));
		repeatElem.addAttribute(new Attribute("startDate", "01/01/2001"));
		repeatElem.addAttribute(new Attribute("endDate", "01/11/2001"));
		repeatElem.addAttribute(new Attribute("repeatable", "yes"));
		repeatElem.addAttribute(new Attribute("repeat-type", "1"));
		repeatElem.addAttribute(new Attribute("period", "1"));
		repeatElem.addAttribute(new Attribute("workingDays", "1"));
	}
	
	@Before
	public void setUp() throws Exception {
		nullTest = new EventImpl(nullElem);
		regularTest = new EventImpl(regularElem);
		repeatTest = new EventImpl(repeatElem);
	}

	@After
	public void tearDown() throws Exception {
		nullTest = null;
		regularTest = null;
		repeatTest = null;
	}

	@Test
	public void testGetHour() {
		assertEquals(0, regularTest.getHour());
		assertEquals(23, repeatTest.getHour());
	}

	@Test
	public void testGetMinute() {
		assertEquals(0, regularTest.getMinute());
		assertEquals(59, repeatTest.getMinute());
	}

	@Test
	public void testGetTimeString() {
		assertEquals("12:00 AM", regularTest.getTimeString());
		assertEquals("11:59 PM", repeatTest.getTimeString());
	}

	@Test
	public void testGetText() {
		assertEquals("", regularTest.getText());
		assertEquals("", repeatTest.getText());
	}

	@Test
	public void testGetContent() {
		assertNull(nullTest.getContent());
		assertEquals(regularElem, regularTest.getContent());
		assertEquals(repeatElem, repeatTest.getContent());
	}

	@Test
	public void testIsRepeatable() {
		assertFalse(regularTest.isRepeatable());
		assertTrue(repeatTest.isRepeatable());
	}

	@Test
	public void testGetStartDate() {
		assertNull(regularTest.getStartDate());
		assertEquals(new CalendarDate(repeatElem.getAttribute("startDate").getValue()).toString(), repeatTest.getStartDate().toString());
	}

	@Test
	public void testGetEndDate() {
		assertNull(regularTest.getEndDate());
		assertEquals(new CalendarDate(repeatElem.getAttribute("endDate").getValue()).toString(),repeatTest.getEndDate().toString());
	}

	@Test
	public void testGetPeriod() {
		assertEquals(1, repeatTest.getPeriod());
		assertEquals(0, regularTest.getPeriod());
	}

	@Test
	public void testGetId() {
		assertEquals("regularTest", regularTest.getId());
		assertEquals("repeatTest", repeatTest.getId());
	}

	@Test
	public void testGetRepeat() {
		assertEquals(1, repeatTest.getPeriod());
		assertEquals(0, regularTest.getPeriod());
	}

	@Test
	public void testGetWorkingDays() {
		assertEquals(1, repeatTest.getPeriod());
		assertEquals(0, regularTest.getPeriod());
	}

	@Test
	public void testGetTimesSnoozed() {
		assertEquals(0, regularTest.getTimesSnoozed());
		assertEquals(1, repeatTest.getTimesSnoozed());
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(-1439, regularTest.compareTo(repeatTest));
		assertEquals(1439, repeatTest.compareTo(regularTest));
		assertEquals(0, regularTest.compareTo(regularTest));
		assertEquals(0, repeatTest.compareTo(repeatTest));
	}

	@Test
	public void testLock() {
		regularTest.lock();
		assertTrue(regularTest.isLocked());
		repeatTest.lock();
		assertTrue(repeatTest.isLocked());
	}

	@Test
	public void testUnlock() {
		regularTest.lock();
		regularTest.unlock();
		assertFalse(regularTest.isLocked());
		repeatTest.lock();
		repeatTest.unlock();
		assertFalse(repeatTest.isLocked());
	}

	@Test
	public void testGetActualEffort() {
		assertEquals(0, regularTest.getActualEffort());
	}

	@Test
	public void testSetActualEffort() {
		regularTest.setActualEffort(1L);
		assertEquals(0, regularTest.getActualEffort());
	}

	@Test
	public void testAddActualEffort() {
		regularTest.addActualEffort(1L);
		assertEquals(0, regularTest.getActualEffort());
	}

	@Test
	public void testGetDisplayName() {
		assertEquals("", regularTest.getDisplayName());
		assertEquals("", repeatTest.getDisplayName());
	}

	@Test
	public void testGetDescription() {
		assertEquals("", regularTest.getDisplayName());
		assertEquals("", repeatTest.getDisplayName());
	}
}

