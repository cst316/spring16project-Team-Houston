/**
 * ProjectManager.java
 * Created on 11.02.2003, 17:50:27 Alex
 * Package: net.sf.memoranda
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Vector;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.ui.Phase;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/*$Id: ProjectManager.java,v 1.9 2005/12/01 08:12:26 alexeya Exp $*/
public class ProjectManager {
	// public static final String NS_JNPROJECT =
	// "http://www.openmechanics.org/2003/jnotes-projects-file";

	public static Document _doc = null;
	static Element _root = null;

	static {
		init();
	}

	public static void init() {
		CurrentStorage.get().openProjectManager();

		if (_doc == null) {

			_root = new Element("projects-list");
			_doc = new Document(_root);
			createProject("__default", Local.getString("Default project"), CalendarDate.today(), null);
		} else
			_root = _doc.getRootElement();
	}

	/**
	 * Description: Save PSD data for current project
	 * 
	 * @param id
	 * @param phase
	 */
	public static void savePSPData(String id, Phase phase) {
		String filePath = System.getProperty("user.dir") + "/pspData/" + id + ".ser";
		try {

			FileOutputStream fileOut = new FileOutputStream(filePath);

			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(phase);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Description: Delete PSP data.
	 * 
	 * @param id
	 */
	private static void deletePSPData(String id) {
		String filePath = System.getProperty("user.dir") + "/pspData/" + id + ".ser";
		File file = new File(filePath);
		try {
			Files.deleteIfExists(file.toPath());
		} catch (NoSuchFileException x) {
			System.out.println("No such file or directory: " + filePath);
		} catch (IOException x) {
			System.out.println("Could not delete file: " + filePath);
		}
	}

	/**
	 * Description: Load PSD data for current project
	 * 
	 * @param id
	 * @return Phase
	 */
	public static Phase loadPSPData(String id) {

		Phase phase = new Phase();
		String filePath = System.getProperty("user.dir") + "/pspData/" + id + ".ser";
		File file = new File(filePath);

		try {

			if (!file.exists()) {
				return phase;
			}

			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			phase = (Phase) in.readObject();
			in.close();
			fileIn.close();

		} catch (IOException i) {
			return phase;
		} catch (ClassNotFoundException c) {
			System.out.println("Phase class not found");
		}

		return phase;
	}

	/**
	 * Description: Get Project based on ID
	 * 
	 * @param id
	 * @return Project
	 */
	public static Project getProject(String id) {
		Elements prjs = _root.getChildElements("project");
		for (int i = 0; i < prjs.size(); i++) {
			String pid = ((Element) prjs.get(i)).getAttribute("id").getValue();
			if (pid.equals(id)) {
				return new ProjectImpl((Element) prjs.get(i));
			}
		}
		return null;
	}

	/**
	 * Description: Get all projects
	 * 
	 * @return Vector
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getAllProjects() {
		Elements prjs = _root.getChildElements("project");
		Vector v = new Vector();
		for (int i = 0; i < prjs.size(); i++)
			v.add(new ProjectImpl((Element) prjs.get(i)));
		return v;
	}

	/**
	 * Description: Get number of projects
	 * 
	 * @return int project count
	 */
	public static int getAllProjectsNumber() {
		int i;
		try {
			i = ((Elements) _root.getChildElements("project")).size();
		} catch (NullPointerException e) {
			i = 1;
		}
		return i;
	}

	/**
	 * Description: Get all active projects
	 * 
	 * @return Vector of active projects
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector getActiveProjects() {
		Elements prjs = _root.getChildElements("project");
		Vector v = new Vector();
		for (int i = 0; i < prjs.size(); i++) {
			Project prj = new ProjectImpl((Element) prjs.get(i));
			if (prj.getStatus() == Project.ACTIVE)
				v.add(prj);
		}
		return v;
	}

	/**
	 * Description: Get number of active projects
	 * 
	 * @return int active project count
	 */
	public static int getActiveProjectsNumber() {
		Elements prjs = _root.getChildElements("project");
		int count = 0;
		for (int i = 0; i < prjs.size(); i++) {
			Project prj = new ProjectImpl((Element) prjs.get(i));
			if (prj.getStatus() == Project.ACTIVE)
				count++;
		}
		return count;
	}

	/**
	 * Description: Create new project.
	 * 
	 * @param id
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @return Project prj
	 */
	public static Project createProject(String id, String title, CalendarDate startDate, CalendarDate endDate) {

		Element el = new Element("project");
		el.addAttribute(new Attribute("id", id));
		_root.appendChild(el);
		Project prj = new ProjectImpl(el);
		prj.setTitle(title);
		prj.setStartDate(startDate);
		prj.setEndDate(endDate);
		CurrentStorage.get().createProjectStorage(prj);
		return prj;
	}

	/**
	 * Description: Create new project.
	 * 
	 * @param title
	 * @param startDate
	 * @param endDate
	 */
	public static Project createProject(String title, CalendarDate startDate, CalendarDate endDate) {
		return createProject(Util.generateId(), title, startDate, endDate);
	}

	public static void removeProject(String id) {
		Project prj = getProject(id);
		if (prj == null)
			return;
		History.removeProjectHistory(prj);
		CurrentStorage.get().removeProjectStorage(prj);
		Elements prjs = _root.getChildElements("project");
		for (int i = 0; i < prjs.size(); i++) {
			String pid = ((Element) prjs.get(i)).getAttribute("id").getValue();
			if (pid.equals(id)) {
				_root.removeChild(prjs.get(i));
				deletePSPData(id);
				return;
			}
		}
	}

}
