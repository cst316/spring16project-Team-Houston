package net.sf.memoranda.test.date;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.date.CurrentDate;

@SuppressWarnings("static-access")
public class CurrentDateTest {
	
	private static CurrentDate date;

	@BeforeClass
	public static void setUp() {
		date = new CurrentDate();
	}
	
	@Test
	public void testGetCurrentDatePattern(){
		assertTrue(date.get().toString().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})") 
				|| date.get().toString().matches("([0-9]{1})/([0-9]{1})/([0-9]{4})")
				|| date.get().toString().matches("([0-9]{1})/([0-9]{2})/([0-9]{4})")
				|| date.get().toString().matches("([0-9]{2})/([0-9]{1})/([0-9]{4})")
		);
	}
	
	

}
