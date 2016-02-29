package net.sf.memoranda.test;

import java.util.Vector;
import org.junit.*;

import net.sf.memoranda.DefaultEventNotifier;
import net.sf.memoranda.Event;
import net.sf.memoranda.EventNotificationListener;
import net.sf.memoranda.EventsScheduler;

import static org.junit.Assert.*;

public class EventsSchedulerTest {
	@Test
	public void testEventsScheduler_1()
		throws Exception {
		EventsScheduler result = new EventsScheduler();
		assertNotNull(result);
	}

	@Test
	public void testAddListener_1()
		throws Exception {
		EventNotificationListener enl = new DefaultEventNotifier();
		EventsScheduler.addListener(enl);
	}

	@Test
	public void testCancelAll_1()
		throws Exception {
		EventsScheduler.cancelAll();
	}

	@Test
	public void testCancelAll_2()
		throws Exception {
		EventsScheduler.cancelAll();
	}

	@Test
	public void testGetFirstScheduledEvent_1()
		throws Exception {
		Event result = EventsScheduler.getFirstScheduledEvent();
		assertEquals(null, result);
	}

	@Test
	public void testGetFirstScheduledEvent_2()
		throws Exception {
		Event result = EventsScheduler.getFirstScheduledEvent();
		assertEquals(null, result);
	}

	@Test
	public void testGetFirstScheduledEvent_3()
		throws Exception {
		Event result = EventsScheduler.getFirstScheduledEvent();
		assertEquals(null, result);
	}

	@Test
	public void testGetFirstScheduledEvent_4()
		throws Exception {
		Event result = EventsScheduler.getFirstScheduledEvent();
		assertEquals(null, result);
	}

	@Test
	public void testGetScheduledEvents_1()
		throws Exception {
		Vector result = EventsScheduler.getScheduledEvents();
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testGetScheduledEvents_2()
		throws Exception {
		Vector result = EventsScheduler.getScheduledEvents();
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testInit_1()
		throws Exception {
		EventsScheduler.init();
	}

	@Test
	public void testInit_2()
		throws Exception {
		EventsScheduler.init();
	}

	@Test
	public void testInit_3()
		throws Exception {
		EventsScheduler.init();
	}

	@Test
	public void testIsEventScheduled_1()
		throws Exception {
		boolean result = EventsScheduler.isEventScheduled();
		assertEquals(false, result);
	}

	@Test
	public void testIsEventScheduled_2()
		throws Exception {
		boolean result = EventsScheduler.isEventScheduled();
		assertEquals(false, result);
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
		new org.junit.runner.JUnitCore().run(EventsSchedulerTest.class);
	}
}
