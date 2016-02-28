package net.sf.memoranda;

import java.util.Arrays;
import java.util.List;

import net.sf.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Element;

/**
* Classname: MiscTracking
* 
* Description: Flexible class for tracking miscellaneous information, such as time 
* 
* Version information: 1.0
* 
* Date: 2/26/2016
*/
public class MiscTracking implements Timeable {

	private static final List<String> PROTECTED_KEYS = Arrays.asList("id",
																	 "name",
																	 "description",
																	 "locked",
																	 "effort_actual");
	private Element _element = null;
	
	/**
	 * Constructs a new MiscTracking object, generating a new XML element
	 * and ID
	 */
	public MiscTracking() {
		Element el = new Element("misctracking");
		el.addAttribute(new Attribute("id", Util.generateId()));
		_element = el;
	}
	
	/**
	 * Constructs a new MiscTracking object using the provided XML element 
	 * tree
	 * @param el the Element to use
	 */
	public MiscTracking(Element el) {
		_element = el;
	}

	/* (non-Javadoc)
	 * @see net.sf.memoranda.Timeable#getActualEffort()
	 */
	@Override
	public long getActualEffort() {
		Attribute attr = _element.getAttribute("effort_actual");
    	if (attr == null) {
    		return 0;
    	}
    	else {
    		try {
        		return Long.parseLong(attr.getValue());
    		}
    		catch (NumberFormatException e) {
    			return 0;
    		}
    	}
	}
	
	/**
	 * Finds a custom-defined child element and returns its value if it exists.
	 * @param key the name of the child element to find
	 * @return the value of found child element or null if child doesn't exist
	 * @throws IllegalArgumentException if provided key is a protected key
	 */
	public String getCustom(String key) throws IllegalArgumentException {
		if (isProtectedKey(key)) {
			throw new IllegalArgumentException("Unable to access protected key");
		}
		return getChild(key);
	}
	
	/**
	 * Returns the description of this object.
	 * @return the description or null if no description found
	 */
	@Override
	public String getDescription() {
		return getChild("description");
	}
	
	/**
	 * Returns the Element object containing this objects attributes and 
	 * properties.
	 * @return the Element object of this MiscTracking object
	 */
	public Element getElement() {
		return _element;
	}
	
    /**
     * Returns the ID of this object
     * @return the ID of this object
     */
    public String getID() {
        return _element.getAttribute("id").getValue();
    }
    
    /**
     * Returns the "name" attribute of this object, if it exists.
     * @return a string representing the name attribute, or null if no name
     */
    public String getName() {
    	Attribute attr = _element.getAttribute("name");
    	if (attr != null) {
    		return attr.getValue();
    	}
    	return null;
    }

	/* (non-Javadoc)
	 * @see net.sf.memoranda.Timeable#setActualEffort(long)
	 */
	@Override
	public void setActualEffort(long effort) {
		setAttr("effort_actual", String.valueOf(effort));
	}
	
	/**
	 * Creates a new custom child element using the provided key and value.
	 * @param key the name of the child element
	 * @param value the value of the child element
	 * @throws IllegalArgumentException if the provided key is a protected key
	 */
	public void setCustom(String key, String value) throws IllegalArgumentException {
		if (isProtectedKey(key)) {
			throw new IllegalArgumentException("Unable to modify protected key");
		}
		setChild(key, value);
	}
	
	/**
	 * Sets the description of this object.
	 * @param description the new description to use
	 */
	public void setDescription(String description) {
		setChild("description", description);
	}
	
	/**
	 * Sets the name of this object.
	 * @param name the new name to use
	 */
	public void setName(String name) {
		setAttr("name", name);
	}
	
	/* (non-Javadoc)
	 * @see net.sf.memoranda.Timeable#addActualEffort(long)
	 */
	@Override
	public void addActualEffort(long effortToAdd) {
		setAttr("effort_actual", String.valueOf(getActualEffort() + effortToAdd)); 
	}
	
	/* (non-Javadoc)
	 * @see net.sf.memoranda.Lockable#isLocked()
	 */
	@Override
	public boolean isLocked() {
		return _element.getAttribute("locked") != null;
	}
	
	/* (non-Javadoc)
	 * @see net.sf.memoranda.Lockable#lock()
	 */
	@Override
	public void lock() {
		setAttr("locked", "yes");
	}

	/* (non-Javadoc)
	 * @see net.sf.memoranda.Lockable#unlock()
	 */
	@Override
	public void unlock() {
		if (this.isLocked())
    		_element.removeAttribute(_element.getAttribute("locked"));
	}
	
	private String getChild(String child) {
		Element targetEl = _element.getFirstChildElement(child);
    	if (targetEl == null) {
    		return null;
    	}
    	else {
       		return targetEl.getValue();
    	}
	}
	
	private boolean isProtectedKey(String key) {
		return PROTECTED_KEYS.contains(key);
	}
	
    private void setAttr(String a, String value) {
        Attribute attr = _element.getAttribute(a);
        if (attr == null)
           _element.addAttribute(new Attribute(a, value));
        else
            attr.setValue(value);
    }
    
    private void setChild(String child, String value) {
    	Element targetEl = _element.getFirstChildElement(child);
    	if (targetEl == null) {
    		targetEl = new Element(child);
    		targetEl.appendChild(value);
    		_element.appendChild(targetEl);
    	}
    	else {
    		targetEl.removeChildren();
    		targetEl.appendChild(value);
    	}
    }

	@Override
	public String getDisplayName() {
		return getName();
	}

}
