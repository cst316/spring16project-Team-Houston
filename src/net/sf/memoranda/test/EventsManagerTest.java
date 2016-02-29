package net.sf.memoranda.test;

import java.util.Collection;

import net.sf.memoranda.Event;
import net.sf.memoranda.EventsManager;
import net.sf.memoranda.date.CalendarDate;
import org.junit.*;
import static org.junit.Assert.*;

public class EventsManagerTest {
	@Test
	public void testEventsManager_1()
		throws Exception {
		EventsManager result = new EventsManager();
		assertNotNull(result);
	}

	@Test
	public void testCreateEvent_1()
		throws Exception {
		CalendarDate date = new CalendarDate();
		int hh = 1;
		int mm = 1;
		String text = "";
		Event result = EventsManager.createEvent(date, hh, mm, text);
		assertNotNull(result);
		assertEquals("", result.getText());
		assertEquals(1, result.getHour());
		assertEquals(1, result.getMinute());
		assertEquals(null, result.getEndDate());
		assertEquals(null, result.getStartDate());
		assertEquals(false, result.isRepeatable());
		assertEquals("1:01 AM", result.getTimeString());
		assertEquals(0, result.getPeriod());
		assertEquals(0, result.getTimesSnoozed());
		assertEquals(false, result.getWorkingDays());
		assertEquals(0, result.getRepeat());
	}

	@Test
	public void testCreateEvent_2()
		throws Exception {
		CalendarDate date = new CalendarDate();
		int hh = 1;
		int mm = 1;
		String text = "";
		int timesSnoozed = 1;
		Event result = EventsManager.createEvent(date, hh, mm, text, timesSnoozed);
		assertNotNull(result);
		assertEquals("", result.getText());
		assertEquals(1, result.getHour());
		assertEquals(1, result.getMinute());
		assertEquals(null, result.getEndDate());
		assertEquals(null, result.getStartDate());
		assertEquals(false, result.isRepeatable());
		assertEquals("1:01 AM", result.getTimeString());
		assertEquals(0, result.getPeriod());
		assertEquals(1, result.getTimesSnoozed());
		assertEquals(false, result.getWorkingDays());
		assertEquals(0, result.getRepeat());
	}

	@Test
	public void testCreateEvent_3()
		throws Exception {
		CalendarDate date = new CalendarDate();
		int hh = 1;
		int mm = 1;
		String text = "";
		int timesSnoozed = 1;
		Event result = EventsManager.createEvent(date, hh, mm, text, timesSnoozed);
		assertNotNull(result);
		assertEquals("", result.getText());
		assertEquals(1, result.getHour());
		assertEquals(1, result.getMinute());
		assertEquals(null, result.getEndDate());
		assertEquals(null, result.getStartDate());
		assertEquals(false, result.isRepeatable());
		assertEquals("1:01 AM", result.getTimeString());
		assertEquals(0, result.getPeriod());
		assertEquals(1, result.getTimesSnoozed());
		assertEquals(false, result.getWorkingDays());
		assertEquals(0, result.getRepeat());
	}

	@Test
	public void testCreateRepeatableEvent_1()
		throws Exception {
		int type = 1;
		CalendarDate startDate = new CalendarDate();
		CalendarDate endDate = new CalendarDate();
		int period = 1;
		int hh = 1;
		int mm = 1;
		String text = "";
		boolean workDays = true;
		Event result = EventsManager.createRepeatableEvent(type, startDate, endDate, period, hh, mm, text, workDays);
		assertNotNull(result);
		assertEquals("", result.getText());
		assertEquals(1, result.getHour());
		assertEquals(1, result.getMinute());
		assertEquals(true, result.isRepeatable());
		assertEquals("1:01 AM", result.getTimeString());
		assertEquals(1, result.getPeriod());
		assertEquals(0, result.getTimesSnoozed());
		assertEquals(true, result.getWorkingDays());
		assertEquals(1, result.getRepeat());
	}

	@Test
	public void testCreateRepeatableEvent_2()
		throws Exception {
		int type = 1;
		CalendarDate startDate = new CalendarDate();
		CalendarDate endDate = null;
		int period = 1;
		int hh = 1;
		int mm = 1;
		String text = "";
		boolean workDays = true;
		Event result = EventsManager.createRepeatableEvent(type, startDate, endDate, period, hh, mm, text, workDays);
		assertNotNull(result);
		assertEquals("", result.getText());
		assertEquals(1, result.getHour());
		assertEquals(1, result.getMinute());
		assertEquals(null, result.getEndDate());
		assertEquals(true, result.isRepeatable());
		assertEquals("1:01 AM", result.getTimeString());
		assertEquals(1, result.getPeriod());
		assertEquals(0, result.getTimesSnoozed());
		assertEquals(true, result.getWorkingDays());
		assertEquals(1, result.getRepeat());
	}

	@Test
	public void testGetRepeatableEvents_1()
		throws Exception {
		Collection result = EventsManager.getRepeatableEvents();
		assertNotNull(result);
	}

	@Test
	public void testGetRepeatableEventsForDate_1()
		throws Exception {
		CalendarDate date = new CalendarDate(1, 1, 1);
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_2()
		throws Exception {
		CalendarDate date = new CalendarDate(1, 1, 1);
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_3()
		throws Exception {
		CalendarDate date = new CalendarDate(1, 1, 1);
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_4()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_5()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_6()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_7()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_8()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_9()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_10()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_11()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_12()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_13()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetRepeatableEventsForDate_14()
		throws Exception {
		CalendarDate date = new CalendarDate();
		Collection result = EventsManager.getRepeatableEventsForDate(date);
		assertNotNull(result);
		assertEquals(1, result.size());
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
		new org.junit.runner.JUnitCore().run(EventsManagerTest.class);
	}
}
