package net.sf.memoranda;

import java.util.Arrays;
import java.util.List;

import net.sf.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Element;

public class MiscTracking implements Timeable {

	private static final List<String> PROTECTED_KEYS = Arrays.asList("id",
																	 "name",
																	 "description",
																	 "locked",
																	 "effort_actual");
	private Element _element = null;
	private MiscTrackingList mtl = null;
	
	public MiscTracking() {
		Element el = new Element("misctracking");
		el.addAttribute(new Attribute("id", Util.generateId()));
		mtl = MiscTrackingList.getInstance();
	}
	
	public MiscTracking(Element el) {
		_element = el;
		mtl = MiscTrackingList.getInstance();
	}

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
	
	public String getCustom(String key) throws IllegalArgumentException {
		if (isProtectedKey(key)) {
			throw new IllegalArgumentException("Unable to access protected key");
		}
		Attribute attr = _element.getAttribute(key);
		if (attr != null) {
			return attr.getValue();
		}
		return "";
	}
	
	public String getDescription() {
		Attribute attr = _element.getAttribute("description");
		if (attr != null) {
			return attr.getValue();
		}
		return "";
	}
	
    public String getID() {
        return _element.getAttribute("id").getValue();
    }
    
    public String getName() {
    	Attribute attr = _element.getAttribute("name");
    	if (attr != null) {
    		return attr.getValue();
    	}
    	return "";
    }

	@Override
	public void setActualEffort(long effort) {
		setAttr("effort_actual", String.valueOf(effort));
	}
	
	public void setCustom(String key, String value) throws IllegalArgumentException {
		if (isProtectedKey(key)) {
			throw new IllegalArgumentException("Unable to modify protected key");
		}
		setAttr(key, value);
	}
	
	public void setDescription(String description) {
		setAttr("description", description);
	}
	
	public void setName(String name) {
		setAttr("name", name);
	}
	
	@Override
	public void addActualEffort(long effortToAdd) {
		setAttr("effort_actual", String.valueOf(getActualEffort() + effortToAdd)); 
	}
	
	@Override
	public boolean isLocked() {
		return _element.getAttribute("locked") != null;
	}
	
	@Override
	public void lock() {
		setAttr("locked", "yes");
	}

	@Override
	public void unlock() {
		if (this.isLocked())
    		_element.removeAttribute(_element.getAttribute("locked"));
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

}
