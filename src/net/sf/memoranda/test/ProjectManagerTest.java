package net.sf.memoranda.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.ProjectManager;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local;

@SuppressWarnings("static-access")
public class ProjectManagerTest {
	
	private static ProjectManager prjMgr = new ProjectManager();
	private static int nonTestProjects;

	@BeforeClass	
	public static void setUp() {
		prjMgr.init();
		nonTestProjects = prjMgr.getAllProjectsNumber();
		prjMgr.createProject("testProj0", Local.getString("TestProject_1"), CalendarDate.today(), CalendarDate.tomorrow());
		prjMgr.createProject("testProj1", Local.getString("TestProject_2"), CalendarDate.today(), CalendarDate.tomorrow());
		prjMgr.createProject("testProj2", Local.getString("TestProject_3"), CalendarDate.today(), CalendarDate.tomorrow());
	}
	
	@AfterClass	
	public static void cleanUp() {
		
		prjMgr.removeProject("testProj0");
		prjMgr.removeProject("testProj1");
		prjMgr.removeProject("testProj2");
	}
	
	@Test
	public void testAllProjectCount(){
		assertTrue((prjMgr.getAllProjectsNumber() - nonTestProjects) == 3);
	}
	
	@Test
	public void testGetAllProjects(){
		@SuppressWarnings("rawtypes")
		Vector v = prjMgr.getAllProjects();
		assertFalse(v.isEmpty());
	}

	@Test
	public void testGetProject(){		
		for(int i = 0; i < 3; i++){
			assertTrue(prjMgr.getProject("testProj"+ i).getTitle().equals("TestProject_" + (i + 1)) );
		}
	}
	
	@Test
	public void testActiveProjectCount(){
		assertTrue((prjMgr.getActiveProjectsNumber() >= 3));
	}
	
	@Test
	public void testGetAllActiveProjects(){
		@SuppressWarnings("rawtypes")
		Vector v = prjMgr.getActiveProjects();
		assertFalse(v.isEmpty());
	}

	@Test
	public void testRemoveProject(){
		//remove project
		prjMgr.createProject("testProj3", Local.getString("TestProject_4"), CalendarDate.today(), CalendarDate.tomorrow());
		prjMgr.removeProject("testProj3");
		
		//verify project has been removed
		assertNull(prjMgr.getProject("testProj3"));
	}
	
}
