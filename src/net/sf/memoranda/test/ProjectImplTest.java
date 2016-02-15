package net.sf.memoranda.test;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.ProjectManager;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;

public class ProjectImplTest extends TestCase {

	Project prj;
	ProjectManager prjMgr = new ProjectManager();
	Element _root = null;

	@BeforeClass
	@SuppressWarnings("static-access")
	public void setUp() {

		if (prjMgr._doc == null) {
			_root = new Element("projects-list");
			prjMgr._doc = new Document(_root);
		} else
			_root = prjMgr._doc.getRootElement();

		String id = Util.generateId();
		String title = "Test_Project";
		String description = "Test_Description";
		CalendarDate startDate = CalendarDate.today();
		CalendarDate endDate = CalendarDate.tomorrow();

		Element el = new Element("project");
		el.addAttribute(new Attribute("id", id));
		_root.appendChild(el);
		prj = new ProjectImpl(el);
		prj.setTitle(title);
		prj.setStartDate(startDate);
		prj.setEndDate(endDate);
		prj.setDescription(description);
	}

	@Test
	public void testStartDatePattern(){
		assertTrue(prj.getStartDate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})") 
				|| prj.getStartDate().toString().matches("([0-9]{1})/([0-9]{1})/([0-9]{4})")
				|| prj.getStartDate().toString().matches("([0-9]{1})/([0-9]{2})/([0-9]{4})")
				|| prj.getStartDate().toString().matches("([0-9]{2})/([0-9]{1})/([0-9]{4})")
		);	
	}
	
	@Test
	public void testEndDatePattern(){
		assertTrue(prj.getEndDate().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})") 
				|| prj.getEndDate().toString().matches("([0-9]{1})/([0-9]{1})/([0-9]{4})")
				|| prj.getEndDate().toString().matches("([0-9]{1})/([0-9]{2})/([0-9]{4})")
				|| prj.getEndDate().toString().matches("([0-9]{2})/([0-9]{1})/([0-9]{4})")
		);	
	}
	
	@Test
	public void testGeneralProjectInfo(){
		assertTrue(prj.getTitle().equals("Test_Project"));
		assertTrue(prj.getDescription().equals("Test_Description"));
		assertTrue(prj.getID() != null);
	}
	
	@Test
	@SuppressWarnings("static-access")
	public void testProjectStatus(){
		
		//project is currently active
		assertTrue(prj.getStatus() == prj.ACTIVE);
		
		//project is now currently scheduled
		prj.setStartDate(CalendarDate.tomorrow());
		assertTrue(prj.getStatus() == prj.SCHEDULED);
		
		//project is now currently completed
		prj.setEndDate(CalendarDate.yesterday());
		assertTrue(prj.getStatus() == prj.COMPLETED);
		
		//project is now currently frozen
		prj.freeze();
		assertTrue(prj.getStatus() == prj.FROZEN);		
	}

}
