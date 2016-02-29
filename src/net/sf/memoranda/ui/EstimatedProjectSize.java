package net.sf.memoranda.ui;

import java.io.Serializable;

public class EstimatedProjectSize implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _addedLOC = 0;
	private int _modifiedLOC = 0;
	private int _deletedLOC = 0;
	private double _estTime = 0;
	private String _notes = "";
	
	public EstimatedProjectSize(){
		
	}
	
	public EstimatedProjectSize(int addedLOC, int modifiedLOC, int deletedLOC, double time, String notes){
		_addedLOC = addedLOC;
		_modifiedLOC = modifiedLOC;
		_deletedLOC = deletedLOC;
		_estTime = time;
		_notes = notes;
	}

	public int getAddedLOC() {
		return _addedLOC;
	}

	public void setAddedLOC(int addedLOC) {
		this._addedLOC = addedLOC;
	}

	public int getModifiedLOC() {
		return _modifiedLOC;
	}

	public void setModifiedLOC(int modifiedLOC) {
		this._modifiedLOC = modifiedLOC;
	}

	public int getDeletedLOC() {
		return _deletedLOC;
	}

	public void setDeletedLOC(int deletedLOC) {
		this._deletedLOC = deletedLOC;
	}

	public double getEstTime() {
		return _estTime;
	}

	public void setEstTime(double estTime) {
		this._estTime = estTime;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		this._notes = notes;
	}

}
