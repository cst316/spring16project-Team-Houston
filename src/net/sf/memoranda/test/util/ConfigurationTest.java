/**
 * 
 */
package net.sf.memoranda.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.memoranda.util.Configuration;

/**
 * @author Martin Gasiorowski
 *
 */
public class ConfigurationTest {

	@Test
	public void putTest() {
		Configuration.put("testKey", "testValue");
		assertTrue(Configuration.get("testKey").toString().equals("testValue"));
	}

}
