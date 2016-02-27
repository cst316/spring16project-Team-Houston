/**
 * Storage.java
 * Created on 12.02.2003, 0:58:42 Alex
 * Package: net.sf.memoranda.util
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda.util;

import net.sf.memoranda.Project;
import net.sf.memoranda.ResourcesList;
import net.sf.memoranda.TaskList;
/**
 * 
 */
public interface Storage {
            
    TaskList openTaskList(Project prj);    
    void storeTaskList(TaskList tl, Project prj);
    
    
    void openProjectManager();    
    void storeProjectManager();
    
    void openEventsManager();
    void storeEventsManager();
    
    void openMimeTypesList();
    void storeMimeTypesList();
    
    void createProjectStorage(Project prj);
    void removeProjectStorage(Project prj);
   
    ResourcesList openResourcesList(Project prj);
    void storeResourcesList(ResourcesList rl, Project prj);
    
    void restoreContext();
    void storeContext(); 
       
}
