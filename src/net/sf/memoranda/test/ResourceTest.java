package net.sf.memoranda.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.Resource;

public class ResourceTest {
	
	private static Resource path;
	private static Resource inetShortcut;
	private static Resource projectFile;
	

	@BeforeClass
	public static void setUp() {
		path = new Resource("C:/testdir");
		inetShortcut = new Resource("www.google.com", true, false);
		projectFile = new Resource("C:/testdir/prjectFile.txt", false, true);
	}
	
	@Test
	public void testGetPath(){
		assertTrue(path.getPath().equals("C:/testdir"));
		assertTrue(inetShortcut.getPath().equals("www.google.com"));
		assertTrue(projectFile.getPath().equals("C:/testdir/prjectFile.txt"));
		
	}

	@Test
	public void testIsInetShortcut(){
		assertTrue(inetShortcut.isInetShortcut());
	}
	
	@Test
	public void testIsProjectFile(){
		assertTrue(projectFile.isProjectFile());
	}
}
