package net.sf.memoranda.test;

import java.util.Collection;

import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskImpl;
import net.sf.memoranda.TaskList;
import net.sf.memoranda.TaskListImpl;
import net.sf.memoranda.date.CalendarDate;
import org.junit.*;

import nu.xom.Attribute;
import nu.xom.Element;
import static org.junit.Assert.*;

public class TaskImplTest {
	@Test
	public void testTaskImpl_1()
		throws Exception {
		Element taskElement = new Element("test");
		TaskList tl = new TaskListImpl(new ProjectImpl(new Element("test")));

		TaskImpl result = new TaskImpl(taskElement, tl);

		assertNotNull(result);
	}

	@Test
	public void testAddActualEffort_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		long effortToAdd = 1L;

		fixture.addActualEffort(effortToAdd);

	}

	@Test
	public void testAddDependsFrom_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		Element test = new Element("test");
		test.addAttribute(new Attribute("id","test"));
		TaskImpl task = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.addDependsFrom(task);
	}

	@Test
	public void testCompareTo_1()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("progress","100"));
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		Element test2 = new Element("test");
		test2.addAttribute(new Attribute("progress","100"));
		test2.addAttribute(new Attribute("startDate", "1/1/2001"));
		test2.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl o = new TaskImpl(test2, new TaskListImpl(new ProjectImpl(new Element("test"))));

		int result = fixture.compareTo(o);

		assertEquals(0, result);
	}

	@Test
	public void testEquals_1()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("id","test"));
		test.addAttribute(new Attribute("progress","100"));
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		Element test2 = new Element("test");
		test2.addAttribute(new Attribute("id","test"));
		test2.addAttribute(new Attribute("progress","100"));
		test2.addAttribute(new Attribute("startDate", "1/1/2001"));
		test2.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl o = new TaskImpl(test2, new TaskListImpl(new ProjectImpl(new Element("test"))));

		boolean result = fixture.equals(o);

		assertTrue(result);
	}

	@Test
	public void testEquals_2()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("id","test"));
		test.addAttribute(new Attribute("progress","0"));
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		Element test2 = new Element("test");
		test2.addAttribute(new Attribute("id","test1"));
		test2.addAttribute(new Attribute("progress","100"));
		test2.addAttribute(new Attribute("startDate", "1/1/2001"));
		test2.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl o = new TaskImpl(test2, new TaskListImpl(new ProjectImpl(new Element("test"))));

		boolean result = fixture.equals(o);

		assertFalse(result);
	}

	@Test
	public void testFreeze_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.freeze();

	}

	@Test
	public void testGetActualEffort_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		long result = fixture.getActualEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetActualEffort_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		long result = fixture.getActualEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetActualEffort_3()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		long result = fixture.getActualEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetContent_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		Element result = fixture.getContent();

		assertNotNull(result);
	}

	@Test
	public void testGetDependsFrom_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		Collection result = fixture.getDependsFrom();

		assertNotNull(result);
	}

	@Test
	public void testGetDependsFrom_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		Collection result = fixture.getDependsFrom();

		assertNotNull(result);
	}

	@Test
	public void testGetDependsFrom_3()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		Collection result = fixture.getDependsFrom();

		assertNotNull(result);
	}

	@Test
	public void testGetDescription_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		String result = fixture.getDescription();

		assertNull(result);
	}

	@Test
	public void testGetDescription_2()
		throws Exception {
		Element test = new Element("test");
		test.appendChild(new Element("description"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		String result = fixture.getDescription();

		assertNotNull(result);
	}

	@Test
	public void testGetDisplayName_1()
		throws Exception {
		Element test = new Element("test");
		test.appendChild(new Element("text"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		String result = fixture.getDisplayName();

		assertNotNull(result);
	}

	@Test(expected=java.lang.NullPointerException.class)
	public void testGetEndDate_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		CalendarDate result = fixture.getEndDate();

		assertNotNull(result);
	}
	
	@Test
	public void testGetEndDate_2()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("endDate", "4/20/2016"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		CalendarDate result = fixture.getEndDate();

		assertNotNull(result);
	}
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testGetEndDate_3()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("endDate", ""));
		test.addAttribute(new Attribute("startDate", ""));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		CalendarDate result = fixture.getEndDate();

		assertNotNull(result);
	}

	@Test
	public void testGetEndDate_4()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("endDate", ""));
		test.addAttribute(new Attribute("startDate", "4/20/2016"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		CalendarDate result = fixture.getEndDate();

		assertNotNull(result);
	}

	@Test
	public void testGetEstimatedEffort_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		long result = fixture.getEstimatedEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetEstimatedEffort_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		long result = fixture.getEstimatedEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetEstimatedEffort_3()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		long result = fixture.getEstimatedEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetID_1()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("id","test"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		String result = fixture.getID();

		assertNotNull(result);
	}

	@Test
	public void testGetParentId_1()
		throws Exception {
		Element testSub = new Element("task");
		testSub.addAttribute(new Attribute("id", "testID"));
		TaskImpl fixtureSub = new TaskImpl(testSub, new TaskListImpl(new ProjectImpl(new Element("test"))));
		Element test = new Element("test");
		test.addAttribute(new Attribute("id", "parent"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		fixture.getContent().appendChild(fixtureSub.getContent());

		String result = fixture.getParentId();

		assertNull(result);
	}

	@Test
	public void testGetParentTask_1()
		throws Exception {
		Element testSub = new Element("task");
		testSub.addAttribute(new Attribute("id", "testID"));
		TaskImpl fixtureSub = new TaskImpl(testSub, new TaskListImpl(new ProjectImpl(new Element("test"))));
		Element test = new Element("test");
		test.addAttribute(new Attribute("id", "parent"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		fixture.getContent().appendChild(fixtureSub.getContent());

		Task result = fixtureSub.getParentTask();

		assertNull(result);
	}

	@Test
	public void testGetPriority_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		int result = fixture.getPriority();

		assertEquals(Task.PRIORITY_NORMAL, result);
	}

	@Test
	public void testGetPriority_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.setPriority(Task.PRIORITY_HIGHEST);
		int result = fixture.getPriority();

		assertEquals(Task.PRIORITY_HIGHEST, result);
	}

	@Test
	public void testGetProgress_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		int result = fixture.getProgress();

		assertEquals(0, result);
	}

	@Test
	public void testGetProgress_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		int result = fixture.getProgress();

		assertEquals(0, result);
	}

	@Test
	public void testGetRate_1()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		long result = fixture.getRate();

		assertEquals(1L, result);
	}

	@Test
	public void testGetStartDate_1()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		CalendarDate result = fixture.getStartDate();

		assertNotNull(result);
	}

	@Test
	public void testGetStatus_1()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate(1,1,2001);

		int result = fixture.getStatus(date);

		assertEquals(Task.ACTIVE, result);
	}

	@Test
	public void testGetStatus_2()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate(1,11,2001);

		int result = fixture.getStatus(date);

		assertEquals(Task.DEADLINE, result);
	}

	@Test
	public void testGetStatus_3()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate();

		fixture.freeze();
		int result = fixture.getStatus(date);

		assertEquals(Task.FROZEN, result);
	}

	@Test
	public void testGetStatus_4()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("progress","100"));
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate();

		int result = fixture.getStatus(date);

		assertEquals(Task.COMPLETED, result);
	}

	@Test
	public void testGetStatus_5()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate();

		fixture.lock();
		int result = fixture.getStatus(date);

		assertEquals(Task.LOCKED, result);
	}

	@Test
	public void testGetStatus_6()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate(1,12,2000);

		int result = fixture.getStatus(date);

		assertEquals(Task.SCHEDULED, result);
	}

	@Test
	public void testGetStatus_7()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/12/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate();

		int result = fixture.getStatus(date);

		assertEquals(Task.ACTIVE, result);
	}

	@Test
	public void testGetStatus_8()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("startDate", "1/1/2001"));
		test.addAttribute(new Attribute("endDate", "1/11/2001"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate();

		int result = fixture.getStatus(date);

		assertEquals(Task.FAILED, result);
	}

	@Test
	public void testGetSubTask_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		String id = "";

		Task result = fixture.getSubTask(id);

		assertNull(result);
	}

	@Test
	public void testGetSubTask_2()
		throws Exception {
		Element test = new Element("task");
		test.addAttribute(new Attribute("id", "testID"));
		TaskImpl fixtureSub = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		fixture.getContent().appendChild(fixtureSub.getContent());
		String id = "testID";

		Task result = fixture.getSubTask(id);

		assertNotNull(result);
	}

	@Test
	public void testGetSubTasks_1()
		throws Exception {
		Element test = new Element("task");
		test.addAttribute(new Attribute("id", "testID"));
		TaskImpl fixtureSub = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		fixture.getContent().appendChild(fixtureSub.getContent());
		String id = "testID";

		Collection result = fixture.getSubTasks();

		assertNotNull(result);
	}

	@Test
	public void testGetText_1()
		throws Exception {
		Element test = new Element("test");
		test.appendChild(new Element("text"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		String result = fixture.getText();

		assertNotNull(result);
	}

	@Test
	public void testHasSubTasks_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		String id = "";

		boolean result = fixture.hasSubTasks(id);

		assertFalse(result);
	}

	@Test
	public void testHasSubTasks_2()
		throws Exception {
		Element test = new Element("task");
		test.addAttribute(new Attribute("id", "testID"));
		TaskImpl fixtureSub = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		fixture.getContent().appendChild(fixtureSub.getContent());
		String id = "testID";

		boolean result = fixture.hasSubTasks(id);

		assertTrue(result);
	}

	@Test
	public void testIsLocked_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		boolean result = fixture.isLocked();

		assertFalse(result);
	}

	@Test
	public void testIsLocked_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.lock();
		boolean result = fixture.isLocked();

		assertTrue(result);
	}

	@Test
	public void testLock_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.lock();

	}

	@Test
	public void testRemoveDependsFrom_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		Task task = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.removeDependsFrom(task);

	}

	@Test
	public void testRemoveDependsFrom_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		Task task = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.removeDependsFrom(task);

	}

	@Test
	public void testRemoveDependsFrom_3()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		Task task = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.removeDependsFrom(task);

	}

	@Test
	public void testSetActualEffort_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		long effort = 1L;

		fixture.setActualEffort(effort);

	}

	@Test
	public void testSetDescription_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		String s = "";

		fixture.setDescription(s);

	}

	@Test
	public void testSetDescription_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		String s = "";

		fixture.setDescription(s);

	}

	@Test
	public void testSetEndDate_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate();

		fixture.setEndDate(date);

	}

	@Test
	public void testSetEstimatedEffort_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		long effort = 1L;

		fixture.setEstimatedEffort(effort);

	}

	@Test
	public void testSetPriority_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		int p = 1;

		fixture.setPriority(p);

	}

	@Test
	public void testSetProgress_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		int p = 1;

		fixture.setProgress(p);

	}

	@Test
	public void testSetProgress_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		int p = -1;

		fixture.setProgress(p);

	}

	@Test
	public void testSetProgress_3()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		int p = 101;

		fixture.setProgress(p);

	}

	@Test
	public void testSetStartDate_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));
		CalendarDate date = new CalendarDate();

		fixture.setStartDate(date);

	}

	@Test
	public void testSetText_1()
		throws Exception {
		Element test = new Element("test");
		test.appendChild(new Element("text"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));
		String s = "";

		fixture.setText(s);

	}

	@Test
	public void testToString_1()
		throws Exception {
		Element test = new Element("test");
		test.appendChild(new Element("text"));
		TaskImpl fixture = new TaskImpl(test, new TaskListImpl(new ProjectImpl(new Element("test"))));

		String result = fixture.toString();

		assertNotNull(result);
	}

	@Test
	public void testUnfreeze_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.unfreeze();

	}

	@Test
	public void testUnfreeze_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.unfreeze();

	}

	@Test
	public void testUnlock_1()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.unlock();

	}

	@Test
	public void testUnlock_2()
		throws Exception {
		TaskImpl fixture = new TaskImpl(new Element("test"), new TaskListImpl(new ProjectImpl(new Element("test"))));

		fixture.unlock();

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
		new org.junit.runner.JUnitCore().run(TaskImplTest.class);
	}
}