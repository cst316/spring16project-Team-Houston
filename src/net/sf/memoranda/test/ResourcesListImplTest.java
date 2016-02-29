package net.sf.memoranda.test;

import java.util.Vector;
import nu.xom.Element;
import nu.xom.Document;
import org.junit.*;

import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.Resource;
import net.sf.memoranda.ResourcesListImpl;

import static org.junit.Assert.*;

public class ResourcesListImplTest {
	@Test
	public void testResourcesListImpl_1()
		throws Exception {
		Project prj = new ProjectImpl(new Element("test"));

		ResourcesListImpl result = new ResourcesListImpl(prj);

		assertNotNull(result);
	}

	@Test
	public void testResourcesListImpl_2()
		throws Exception {
		Document doc = new Document(new Element("test"));
		Project prj = new ProjectImpl(new Element("test"));

		ResourcesListImpl result = new ResourcesListImpl(doc, prj);

		assertNotNull(result);
	}

	@Test
	public void testAddResource_1()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";

		fixture.addResource(path);

	}

	@Test
	public void testAddResource_2()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";
		boolean isInternetShortcut = true;
		boolean isProjectFile = true;

		fixture.addResource(path, isInternetShortcut, isProjectFile);

	}

	@Test
	public void testAddResource_3()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";
		boolean isInternetShortcut = false;
		boolean isProjectFile = true;

		fixture.addResource(path, isInternetShortcut, isProjectFile);

	}

	@Test
	public void testAddResource_4()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";
		boolean isInternetShortcut = true;
		boolean isProjectFile = false;

		fixture.addResource(path, isInternetShortcut, isProjectFile);

	}

	@Test
	public void testAddResource_5()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";
		boolean isInternetShortcut = false;
		boolean isProjectFile = false;

		fixture.addResource(path, isInternetShortcut, isProjectFile);

	}

	@Test
	public void testGetAllResources_1()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));

		Vector result = fixture.getAllResources();

		assertNotNull(result);
	}

	@Test
	public void testGetAllResources_2()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));

		Vector result = fixture.getAllResources();

		assertNotNull(result);
	}

	@Test
	public void testGetAllResourcesCount_1()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));

		int result = fixture.getAllResourcesCount();

		assertEquals(0, result);
	}

	@Test
	public void testGetResource_1()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";

		Resource result = fixture.getResource(path);

		assertNull(result);
	}

	@Test
	public void testGetXMLContent_1()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));

		Document result = fixture.getXMLContent();

		assertNotNull(result);
	}

	@Test
	public void testRemoveResource_1()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";

		fixture.removeResource(path);

	}

	@Test
	public void testRemoveResource_2()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";

		fixture.removeResource(path);

	}

	@Test
	public void testRemoveResource_3()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";

		fixture.removeResource(path);

	}

	@Test
	public void testRemoveResource_4()
		throws Exception {
		ResourcesListImpl fixture = new ResourcesListImpl(new Document(new Element("test")), new ProjectImpl(new Element("test")));
		String path = "";

		fixture.removeResource(path);

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
		new org.junit.runner.JUnitCore().run(ResourcesListImplTest.class);
	}
}