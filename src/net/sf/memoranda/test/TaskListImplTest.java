package net.sf.memoranda.test;

import java.util.Collection;
import nu.xom.Document;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskListImpl;
import net.sf.memoranda.date.CalendarDate;
import org.junit.*;
import nu.xom.Element;
import static org.junit.Assert.*;

public class TaskListImplTest {
	@Test
	public void testTaskListImpl_1()
		throws Exception {
		Project prj = new ProjectImpl(new Element("test"));

		TaskListImpl result = new TaskListImpl(prj);

		assertNotNull(result);
	}

	@Test
	public void testTaskListImpl_2()
		throws Exception {
		Document doc = new Document(new Element("test"));
		Project prj = new ProjectImpl(new Element("test"));

		TaskListImpl result = new TaskListImpl(doc, prj);

		assertNotNull(result);
	}

	@Test
	public void testGetActiveSubTasks_1()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String taskId = "";
		CalendarDate date = new CalendarDate();

		Collection result = fixture.getActiveSubTasks(taskId, date);

		assertNotNull(result);
	}

	@Test
	public void testGetAllSubTasks_1()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String taskId = null;

		Collection result = fixture.getAllSubTasks(taskId);

		assertNotNull(result);
	}

	@Test
	public void testGetAllSubTasks_2()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String taskId = "";

		Collection result = fixture.getAllSubTasks(taskId);

		assertNotNull(result);
	}

	@Test
	public void testGetAllSubTasks_3()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String taskId = "a";

		Collection result = fixture.getAllSubTasks(taskId);

		assertNotNull(result);
	}

	@Test
	public void testGetAllSubTasks_4()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String taskId = "a";

		Collection result = fixture.getAllSubTasks(taskId);

		assertNotNull(result);
	}

	@Test
	public void testGetProject_1()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));

		Project result = fixture.getProject();

		assertNotNull(result);
	}

	@Test
	public void testGetTask_1()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String id = "";

		Task result = fixture.getTask(id);

		assertNotNull(result);
	}

	@Test
	public void testGetTopLevelTasks_1()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));

		Collection result = fixture.getTopLevelTasks();

		assertNotNull(result);
	}

	@Test
	public void testGetXMLContent_1()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));

		Document result = fixture.getXMLContent();

		assertNotNull(result);
	}

	@Test
	public void testHasSubTasks_1()
		throws Exception {
		TaskListImpl fixture = new TaskListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String id = "";

		boolean result = fixture.hasSubTasks(id);

		assertFalse(result);
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
		new org.junit.runner.JUnitCore().run(TaskListImplTest.class);
	}
}