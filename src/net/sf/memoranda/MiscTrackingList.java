package net.sf.memoranda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import net.sf.memoranda.util.Util;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
* Classname: MiscTrackingList
* 
* Description: Singleton that maintains a current list of MiscTracking objects loaded in system.
* 
* Version information: 1.0
* 
* Date: 2/26/2016
*/
public class MiscTrackingList {

	private static final String ROOT_TYPE = "misctrackinglist";
	private static final String CHILD_TYPE = "misctracking";
	private static MiscTrackingList _instance = null;
	private Project _project = null;
    private Document _doc = null;
    private Element _root = null;
	
    private Hashtable<String,Element> elements = new Hashtable<String,Element>();
    
	private MiscTrackingList() {
		_root = new Element(ROOT_TYPE);
        _doc = new Document(_root);
	}

	/**
	 * Returns the current instance of the class, generating a new instance
	 * if one does not already exist.
	 * 
	 * @return current instance of MiscTrackingList 
	 */
	public static synchronized MiscTrackingList getInstance() {
		if (_instance == null) {
			_instance = new MiscTrackingList();
		}
		return _instance;
	}
	
	/**
	 * Returns the current project.
	 * 
	 * @return the current project
	 */
	public Project getProject() {
		return _project;
	}
	
	/**
	 * Returns the content of the current document in XML format.
	 * 
	 * @return the current doc in XML
	 */
	public Document getXMLContent() {
        return _doc;
    }
	
	/**
	 * Sets the active document to the provided document. Used for controlling
	 * which document is used for reading/writing.
	 * 
	 * @param doc new active document
	 */
	public void setDocument(Document doc) {
		_doc = doc;
		_root = _doc.getRootElement();
		buildElements(_root);
	}
	
	/**
	 * Sets the active project associated with this document.
	 * 
	 * @param prj the new active project
	 */
	public void setProject(Project prj) {
		_project = prj;
	}
	
	/**
	 * Returns a misc tracking object from current document, if it exists.
	 * @param id the misc tracking object's id
	 * @return a new instance of MiscTracking representing the object
	 */
	public MiscTracking getMiscTrackingObj(String id) {
		return new MiscTracking(getMiscTrackingElement(id)); 
	}
	
	/**
	 * Returns a collection containing all MiscTracking objects in the current
	 * document.
	 * @return a Collection of MiscTracking objects from the current document
	 */
	public Collection<MiscTracking> getMiscTrackingObjects() {
        Elements mto = _root.getChildElements(CHILD_TYPE);
        return convertToMiscTrackingObjects(mto);    	    		
    }

	/*
	 * Build the hash table recursively
	 */
	private void buildElements(Element parent) {
		Elements els = parent.getChildElements(CHILD_TYPE);
		for (int i = 0; i < els.size(); i++) {
			Element el = els.get(i);
			elements.put(el.getAttribute("id").getValue(), el);
			buildElements(el);
		}
	}
    
    private Collection<MiscTracking> convertToMiscTrackingObjects(Elements mto) {
    	List<MiscTracking> mtl = new ArrayList<MiscTracking>();

        for (int i = 0; i < mto.size(); i++) {
            MiscTracking mt = new MiscTracking(mto.get(i));
            mtl.add(mt);
        }
        
        return mtl;
    }
    
	private Element getMiscTrackingElement(String id) {
		Element el = (Element)elements.get(id);
		if (el == null) {
			Util.debug("Misc Tracking object " + id + " cannot be found in project " + _project.getTitle());
		}
		return el;
	}
}
