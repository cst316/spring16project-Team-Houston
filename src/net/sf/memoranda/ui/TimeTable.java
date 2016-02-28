package net.sf.memoranda.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.MiscTracking;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectListener;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskImpl;
import net.sf.memoranda.Timeable;
import net.sf.memoranda.ui.table.TableSorter;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;

public class TimeTable extends JTable {

	private static final int NUM_COLUMNS = 3;
	Vector<Timeable> timeEntries = null;
    TableSorter sorter = null;
	
	public TimeTable() {
		super();
        initTable();
        sorter = new TableSorter(new TimeTableModel());
        sorter.addMouseListenerToHeaderInTable(this);
        setModel(sorter);
        this.setShowGrid(false);
        this.setFont(new Font("Dialog",0,11));
        initColumsWidth();
        CurrentProject.addProjectListener(new ProjectListener() {
            public void projectChange(Project p, Object newLists) {                
            }
            public void projectWasChanged() {
                 tableChanged();
            }
        });
	}
    
    public void tableChanged() {
        initTable();
        sorter.tableChanged(null);
        initColumsWidth();
        updateUI();
    }
    
    private void initColumsWidth() {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setMinWidth(100);
            column.setPreferredWidth(100);
        }
    }

    @SuppressWarnings("unchecked")
	private void initTable() {
    	Vector<Task> tasks = (Vector<Task>) CurrentProject.getTaskList().getTopLevelTasks();
    	Vector<MiscTracking> mtos = (Vector<MiscTracking>) CurrentProject.getMiscTrackingList().getMiscTrackingObjects();
        timeEntries = new Vector<Timeable>();
        for (Task task : tasks) {
        	timeEntries.add((TaskImpl) task);
        }
        for (MiscTracking mto : mtos) {
        	timeEntries.add(mto);
        }
    }
    
    public TableCellRenderer getCellRenderer(int row, int column) {
        return new javax.swing.table.DefaultTableCellRenderer() {
	        public Component getTableCellRendererComponent(
	            JTable table,
	            Object value,
	            boolean isSelected,
	            boolean hasFocus,
	            int row,
	            int column) {
	            JLabel comp;
	
	            comp = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            return comp;
	        }
        };
    }
    
    class TimeTableModel extends AbstractTableModel {

        String[] columnNames = {
                Local.getString("Title"),
                Local.getString("Duration"),
                Local.getString("Description")};

        public String getColumnName(int i) {
            return columnNames[i];
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return timeEntries.size();
        }
        
        public Object getValueAt(int row, int col) {
            Timeable t = timeEntries.get(row);
            switch (col) {
                case 0: return t.getDisplayName();
                case 1: return Util.formatTimeDuration(t.getActualEffort(),"DHMS");
                case 2: return t.getDescription();
            }
            return null;
        }
    }
    
    public Class getColumnClass(int col) {
        try {
            return Class.forName("java.lang.String");
        }
        catch (Exception ex) {
        	new ExceptionDialog(ex);
        }
        return null;
    }
}
