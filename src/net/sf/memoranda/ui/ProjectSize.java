package net.sf.memoranda.ui;

import java.io.Serializable;

public class ProjectSize implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int _addedLOC = 0;
	private int _modifiedLOC = 0;
	private int _deletedLOC = 0;
	private String _notes = "";
	
	public ProjectSize(){
		
	}
	
	public ProjectSize(int addedLOC, int modifiedLOC, int deletedLOC, String notes){
		_addedLOC = addedLOC;
		_modifiedLOC = modifiedLOC;
		_deletedLOC = deletedLOC;
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

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		this._notes = notes;
	}

}
