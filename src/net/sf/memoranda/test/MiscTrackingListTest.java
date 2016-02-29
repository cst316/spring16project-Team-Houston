package net.sf.memoranda.test;

import java.util.Collection;
import nu.xom.Document;
import org.junit.*;

import net.sf.memoranda.MiscTracking;
import net.sf.memoranda.MiscTrackingList;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import nu.xom.Element;
import static org.junit.Assert.*;

public class MiscTrackingListTest {
	@Test
	public void testMiscTrackingList_1()
		throws Exception {
		MiscTrackingList result = new MiscTrackingList();

		assertNotNull(result);
		assertEquals(null, result.getProject());
	}

	@Test
	public void testMiscTrackingList_2()
		throws Exception {
		Project prj = new ProjectImpl(new Element("test"));

		MiscTrackingList result = new MiscTrackingList(prj);

		assertNotNull(result);
	}

	@Test
	public void testMiscTrackingList_3()
		throws Exception {
		Document doc = new Document(new Element("test"));
		Project prj = new ProjectImpl(new Element("test"));

		MiscTrackingList result = new MiscTrackingList(doc, prj);

		assertNotNull(result);
	}

	@Test
	public void testAddMiscTrackingObj_1()
		throws Exception {
		MiscTrackingList fixture = new MiscTrackingList();
		fixture.setDocument(new Document(new Element("test")));
		fixture.setProject(new ProjectImpl(new Element("test")));
		MiscTracking newObj = new MiscTracking(new Element("test"));

		fixture.addMiscTrackingObj(newObj);

	}

	@Test
	public void testGetMiscTrackingObj_1()
		throws Exception {
		MiscTrackingList fixture = new MiscTrackingList();
		fixture.setDocument(new Document(new Element("test")));
		fixture.setProject(new ProjectImpl(new Element("test")));
		String id = "";

		MiscTracking result = fixture.getMiscTrackingObj(id);

		assertNotNull(result);
	}

	@Test
	public void testGetMiscTrackingObjects_1()
		throws Exception {
		MiscTrackingList fixture = new MiscTrackingList();
		fixture.setDocument(new Document(new Element("test")));
		fixture.setProject(new ProjectImpl(new Element("test")));

		Collection<MiscTracking> result = fixture.getMiscTrackingObjects();

		assertNotNull(result);
	}

	@Test
	public void testGetProject_1()
		throws Exception {
		MiscTrackingList fixture = new MiscTrackingList();
		fixture.setDocument(new Document(new Element("test")));
		fixture.setProject(new ProjectImpl(new Element("test")));

		Project result = fixture.getProject();

		assertNotNull(result);
	}

	@Test
	public void testGetXMLContent_1()
		throws Exception {
		MiscTrackingList fixture = new MiscTrackingList();
		fixture.setDocument(new Document(new Element("test")));
		fixture.setProject(new ProjectImpl(new Element("test")));

		Document result = fixture.getXMLContent();

		assertNotNull(result);
	}

	@Test
	public void testSetDocument_1()
		throws Exception {
		MiscTrackingList fixture = new MiscTrackingList();
		fixture.setDocument(new Document(new Element("test")));
		fixture.setProject(new ProjectImpl(new Element("test")));
		Document doc = new Document(new Element("test"));

		fixture.setDocument(doc);

	}

	@Test
	public void testSetProject_1()
		throws Exception {
		MiscTrackingList fixture = new MiscTrackingList();
		fixture.setDocument(new Document(new Element("test")));
		fixture.setProject(new ProjectImpl(new Element("test")));
		Project prj = new ProjectImpl(new Element("test"));

		fixture.setProject(prj);

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
		new org.junit.runner.JUnitCore().run(MiscTrackingListTest.class);
	}
}