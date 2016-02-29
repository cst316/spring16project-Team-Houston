package net.sf.memoranda.test;

import org.junit.*;

import net.sf.memoranda.MiscTracking;
import nu.xom.Attribute;
import nu.xom.Element;
import static org.junit.Assert.*;

public class MiscTrackingTest {
	@Test
	public void testMiscTracking_1()
		throws Exception {

		MiscTracking result = new MiscTracking();

		assertNotNull(result);
		assertEquals(null, result.getName());
		assertEquals(null, result.getDisplayName());
		assertEquals(false, result.isLocked());
		assertEquals(null, result.getDescription());
		assertEquals(0L, result.getActualEffort());
	}

	@Test
	public void testMiscTracking_2()
		throws Exception {
		Element el = new Element("test");

		MiscTracking result = new MiscTracking(el);

		assertNotNull(result);
	}

	@Test
	public void testAddActualEffort_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		long effortToAdd = 1L;

		fixture.addActualEffort(effortToAdd);

	}

	@Test
	public void testGetActualEffort_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		long result = fixture.getActualEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetActualEffort_2()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		long result = fixture.getActualEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetActualEffort_3()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		long result = fixture.getActualEffort();

		assertEquals(0L, result);
	}

	@Test
	public void testGetCustom_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		String key = "test";
		String value = "blank";
		
		fixture.setCustom(key, value);

		String result = fixture.getCustom(key);

		assertNotNull(result);
		assertEquals(value, result);
	}

	@Test
	public void testGetCustom_2()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		String key = "";

		String result = fixture.getCustom(key);

		assertNull(result);
	}

	@Test
	public void testGetDescription_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		String result = fixture.getDescription();

		assertNull(result);
	}
	
	@Test
	public void testGetDescription_2()
		throws Exception {
		Element test = new Element("test");
		test.appendChild(new Element("description"));
		MiscTracking fixture = new MiscTracking(test);

		String result = fixture.getDescription();

		assertNotNull(result);
	}

	@Test
	public void testGetDisplayName_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		String result = fixture.getDisplayName();

		assertNull(result);
	}
	
	@Test
	public void testGetDisplayName_2()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("name", "test"));
		MiscTracking fixture = new MiscTracking(test);

		String result = fixture.getDisplayName();

		assertNotNull(result);
	}

	@Test
	public void testGetElement_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		Element result = fixture.getElement();

		assertNotNull(result);
	}

	@Test
	public void testGetID_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		String result = fixture.getID();

		assertNull(result);
	}
	
	@Test
	public void testGetID_2()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("id", "test"));
		MiscTracking fixture = new MiscTracking(test);

		String result = fixture.getID();

		assertNotNull(result);
	}

	@Test
	public void testGetName_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		String result = fixture.getName();

		assertNull(result);
	}

	@Test
	public void testGetName_2()
		throws Exception {
		Element test = new Element("test");
		test.addAttribute(new Attribute("name", "test"));
		MiscTracking fixture = new MiscTracking(test);

		String result = fixture.getDisplayName();

		assertNotNull(result);
	}

	@Test
	public void testIsLocked_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		boolean result = fixture.isLocked();

		assertFalse(result);
	}

	@Test
	public void testIsLocked_2()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		fixture.lock();
		boolean result = fixture.isLocked();

		assertTrue(result);
	}

	@Test
	public void testLock_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		fixture.lock();

	}

	@Test
	public void testSetActualEffort_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		long effort = 1L;

		fixture.setActualEffort(effort);

	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetCustom_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		String key = "";
		String value = "";

		fixture.setCustom(key, value);

	}

	@Test
	public void testSetCustom_2()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		String key = "test";
		String value = "";

		fixture.setCustom(key, value);

	}
	
	@Test
	public void testSetCustom_3()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		String key = "test";
		String value = "";

		fixture.setCustom(key, value);
		
		value = "ooolala";
		
		fixture.setCustom(key, value);
	}

	@Test
	public void testSetDescription_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		String description = "";

		fixture.setDescription(description);

	}

	@Test
	public void testSetName_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));
		String name = "";

		fixture.setName(name);

	}

	@Test
	public void testUnlock_1()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

		fixture.unlock();

	}

	@Test
	public void testUnlock_2()
		throws Exception {
		MiscTracking fixture = new MiscTracking(new Element("test"));

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
		new org.junit.runner.JUnitCore().run(MiscTrackingTest.class);
	}
}