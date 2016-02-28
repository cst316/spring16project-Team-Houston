/**
 * CurrentProject.java
 * Created on 13.02.2003, 13:16:52 Alex
 * Package: net.sf.memoranda
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 *
 */
package net.sf.memoranda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import net.sf.memoranda.ui.AppFrame;
import net.sf.memoranda.util.Context;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Storage;

/**
 *
 */
/*$Id: CurrentProject.java,v 1.6 2005/12/01 08:12:26 alexeya Exp $*/
public class CurrentProject {

    private static Project _project = null;
    private static TaskList _tasklist = null;
    private static ResourcesList _resources = null;
    private static MiscTrackingList _miscTrackingList = null;
    private static Vector projectListeners = new Vector();

        
    static {
        String prjId = (String)Context.get("LAST_OPENED_PROJECT_ID");
        if (prjId == null) {
            prjId = "__default";
            Context.put("LAST_OPENED_PROJECT_ID", prjId);
        }
        _project = ProjectManager.getProject(prjId);
		
		if (_project == null) {
			_project = ProjectManager.getProject("__default");
			if (_project == null) 
				_project = (Project)ProjectManager.getActiveProjects().get(0);						
            Context.put("LAST_OPENED_PROJECT_ID", _project.getID());
			
		}		
		
        _tasklist = CurrentStorage.get().openTaskList(_project);
        _resources = CurrentStorage.get().openResourcesList(_project);
        _miscTrackingList = CurrentStorage.get().openMiscTrackingList(_project);
        AppFrame.addExitListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();                                               
            }
        });
    }
        

    public static Project get() {
        return _project;
    }

    public static TaskList getTaskList() {
            return _tasklist;
    }

    
    
    public static ResourcesList getResourcesList() {
            return _resources;
    }
    
    public static MiscTrackingList getMiscTrackingList() {
    	return _miscTrackingList;
    }

    public static void set(Project project) {
        if (project.getID().equals(_project.getID())) return;
        TaskList newtasklist = CurrentStorage.get().openTaskList(project);
        ResourcesList newresources = CurrentStorage.get().openResourcesList(project);
        MiscTrackingList newMiscTrackingList = CurrentStorage.get().openMiscTrackingList(project);
        Map<String,Object> newLists = new HashMap<String, Object>();
        newLists.put("tasklist", newtasklist);
        newLists.put("resources", newresources);
        newLists.put("miscTrackingList", newMiscTrackingList);
        notifyListenersBefore(project, newLists);
        _project = project;
        _tasklist = newtasklist;
        _resources = newresources;
        _miscTrackingList = newMiscTrackingList;
        notifyListenersAfter();
        Context.put("LAST_OPENED_PROJECT_ID", project.getID());
    }

    public static void addProjectListener(ProjectListener pl) {
        projectListeners.add(pl);
    }

    public static Collection getChangeListeners() {
        return projectListeners;
    }

    private static void notifyListenersBefore(Project project, Map<String, Object> newLists) {
        for (int i = 0; i < projectListeners.size(); i++) {
            ((ProjectListener)projectListeners.get(i)).projectChange(project, newLists);
        }
    }
    
    private static void notifyListenersAfter() {
        for (int i = 0; i < projectListeners.size(); i++) {
            ((ProjectListener)projectListeners.get(i)).projectWasChanged();            
        }
    }

    public static void save() {
        Storage storage = CurrentStorage.get();

        storage.storeTaskList(_tasklist, _project); 
        storage.storeResourcesList(_resources, _project);
        storage.storeMiscTrackingList(_miscTrackingList, _project);
        storage.storeProjectManager();
    }
    
    public static void free() {
        _project = null;
        _tasklist = null;
        _resources = null;
    }
}
