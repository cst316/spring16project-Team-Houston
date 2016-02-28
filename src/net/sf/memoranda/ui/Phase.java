package net.sf.memoranda.ui;

import java.io.Serializable;
import java.util.ArrayList;

public class Phase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList <Defect> _defects;
	private EstimatedProjectSize _estProjSize;
	private ProjectSize _projSize;
	public String phase;
	
	public Phase(){
		_defects = new ArrayList<Defect>();
		_estProjSize = new EstimatedProjectSize();
		_projSize = new ProjectSize();
		phase = "Unknown";
	}
	
	public void addDefect(Defect defect){
		_defects.add(defect);
	}
	
	public void removeDefect(Defect defect){
		_defects.remove(defect);
	}
	
	public Defect getDefect(Defect defect){
		
		int index = _defects.indexOf(defect);
		Defect emptyVal = new Defect("Unknown", "Unknown", "Unknown", "Unknown");
		if(index != -1){
			return _defects.get(index);
		}
		return emptyVal;
	}
	
	public Defect getDefect(int index){
		
		
		Defect emptyVal = new Defect("Unknown", "Unknown", "Unknown", "Unknown");
		if(index < _defects.size()){
			return _defects.get(index);
		}
		return emptyVal;
	}
	
	public int getNumberOfDefects(){
		return _defects.size();
	}
	
	public EstimatedProjectSize getEstProjSize() {
		return _estProjSize;
	}

	public void setEstProjSize(EstimatedProjectSize estProjSize) {
		this._estProjSize = estProjSize;
	}

	public ProjectSize getProjSize() {
		return _projSize;
	}

	public void setProjSize(ProjectSize projSize) {
		this._projSize = projSize;
	}
}
