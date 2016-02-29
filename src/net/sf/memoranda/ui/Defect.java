package net.sf.memoranda.ui;

import java.io.Serializable;

public class Defect implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _phase = "";
	private String _defectType = "";
	private String _defectName = "";
	private String _notes = "";
	
	public Defect(String phase, String type, String name, String notes){
		
		_phase = phase;
		_defectType = type;
		_defectName = name;
		_notes = notes;
	}

	public String getPhase() {
		return _phase;
	}

	public void setPhase(String phase) {
		this._phase = phase;
	}

	public String getDefectType() {
		return _defectType;
	}

	public void setDefectType(String defectType) {
		this._defectType = defectType;
	}

	public String getDefectName() {
		return _defectName;
	}

	public void setDefectName(String defectName) {
		this._defectName = defectName;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		this._notes = notes;
	}

}
