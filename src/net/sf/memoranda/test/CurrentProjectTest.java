package net.sf.memoranda.test;

import java.util.Collection;
import org.junit.*;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.MiscTrackingList;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.ProjectListener;
import net.sf.memoranda.ResourcesList;
import net.sf.memoranda.TaskList;
import nu.xom.Attribute;
import nu.xom.Element;
import static org.junit.Assert.*;

public class CurrentProjectTest {
	@Test
	public void testCurrentProject_1()
		throws Exception {
		CurrentProject result = new CurrentProject();
		assertNotNull(result);
	}

	@Test
	public void testAddProjectListener_1()
		throws Exception {
		ProjectListener pl = null;

		CurrentProject.addProjectListener(pl);

	}

	@Test
	public void testFree_1()
		throws Exception {

		CurrentProject.free();

	}

	@Test
	public void testGetMiscTrackingList_1()
		throws Exception {

		MiscTrackingList result = CurrentProject.getMiscTrackingList();

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
		new org.junit.runner.JUnitCore().run(CurrentProjectTest.class);
	}
}