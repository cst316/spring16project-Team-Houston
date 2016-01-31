/**
 * JNCalendar.java Created on 13.02.2003, 21:26:38 Alex Package:
 * net.sf.memoranda.ui
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net Copyright (c) 2003
 *         Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Task;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;
import net.sf.memoranda.util.Configuration;
import net.sf.memoranda.util.Context;
import net.sf.memoranda.util.CurrentStorage;

/**
 *  
 */
/*$Id: JNCalendar.java,v 1.8 2004/11/05 07:38:10 pbielen Exp $*/
public class JNCalendar extends JTable {

	private CalendarDate _date = null;
	private boolean ignoreChange = false;
	private Vector selectionListeners = new Vector();
	CalendarDate startPeriod = null;
	CalendarDate endPeriod = null;
	public JNCalendarCellRenderer renderer = new JNCalendarCellRenderer();
	CalendarPopup contextMenu;
	
	public JNCalendar() {
		this(CurrentDate.get());
	}
	/**
	 * Constructor for JNCalendar.
	 */
	public JNCalendar(CalendarDate date) {
		super();
		
		contextMenu = new CalendarPopup();
		this.setComponentPopupMenu(contextMenu);
		
		/* table properties */
		setCellSelectionEnabled(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
		set(date);

		/* selection listeners */
		final ListSelectionModel rowSM = getSelectionModel();
		final ListSelectionModel colSM = getColumnModel().getSelectionModel();
		ListSelectionListener lsl = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
					//Ignore extra messages.
				if (e.getValueIsAdjusting())
					return;
				if (ignoreChange)
					return;
				int row = getSelRow();
				int col = getSelCol();
				Object val = getModel().getValueAt(row, col);
				if (val != null) {
					if (val
						.toString()
						.equals(new Integer(_date.getDay()).toString()))
						return;
					_date =
						new CalendarDate(
							new Integer(val.toString()).intValue(),
							_date.getMonth(),
							_date.getYear());
					notifyListeners();
				} else {
					//getSelectionModel().clearSelection();
					doSelection();
				}
			}

		};
		rowSM.addListSelectionListener(lsl);
		colSM.addListSelectionListener(lsl);
		
		JNCalendar _self = this;
		this.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mousePressed(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON3) {
	    			int newCol = _self.columnAtPoint(e.getPoint());
	    			int newRow = _self.rowAtPoint(e.getPoint());
					Object val = getModel().getValueAt(newRow, newCol);
					if (val != null) {
						if (val
							.toString()
							.equals(new Integer(_date.getDay()).toString()))
							return;
						_date =
							new CalendarDate(
								new Integer(val.toString()).intValue(),
								_date.getMonth(),
								_date.getYear());
						notifyListeners();
					} else {
						doSelection();
					}
	    		}
	    	}
	    });
		
	}

	int getSelRow() {
		return this.getSelectedRow();
	}

	int getSelCol() {
		return this.getSelectedColumn();
	}

	public JNCalendar(CalendarDate date, CalendarDate sd, CalendarDate ed) {
		this(date);
		setSelectablePeriod(sd, ed);
	}

	public void set(CalendarDate date) {
		_date = date;
		setCalendarParameters();
		ignoreChange = true;
		this.setModel(new JNCalendarModel());
		ignoreChange = false;
		doSelection();
	}

	public CalendarDate get() {
		return _date;
	}

	public void addSelectionListener(ActionListener al) {
		selectionListeners.add(al);
	}

	public void setSelectablePeriod(CalendarDate sd, CalendarDate ed) {
		startPeriod = sd;
		endPeriod = ed;
	}

	private void notifyListeners() {
		for(int i=0;i<selectionListeners.size();i++) {
			((ActionListener) selectionListeners.get(i)).actionPerformed(
				new ActionEvent(this, 0, "Calendar event"));
		}
	}

	public TableCellRenderer getCellRenderer(int row, int column) {
		Object d = this.getModel().getValueAt(row, column);
		/*
		 * if (d != null) return new JNCalendarCellRenderer( new
		 * CalendarDate(new Integer(d.toString()).intValue(), _date.getMonth(),
		 * _date.getYear()));
		 */
		if (d != null)
			renderer.setDate(
				new CalendarDate(
					new Integer(d.toString()).intValue(),
					_date.getMonth(),
					_date.getYear()));
		else
			renderer.setDate(null);
		return renderer;
	}

	void doSelection() {
		ignoreChange = true;
		int selRow = getRow(_date.getDay());
		int selCol = getCol(_date.getDay());
		this.setRowSelectionInterval(selRow, selRow);
		this.setColumnSelectionInterval(selCol, selCol);
		this.setRowSelectionInterval(selRow, selRow);
		ignoreChange = false;
		contextMenu.updateMenu();
	}

	int getRow(int day) {
		return ((day - 1) + firstDay) / 7;
	}

	int getCol(int day) {
		return ((day - 1) + firstDay) % 7;
	}

	int firstDay;
	int daysInMonth;

	void setCalendarParameters() {
		int d = 1;

		Calendar cal = _date.getCalendar();

		if (Configuration.get("FIRST_DAY_OF_WEEK").equals("mon")) {
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			d = 2;
		} else
			cal.setFirstDayOfWeek(Calendar.SUNDAY);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.getTime();
		firstDay = cal.get(Calendar.DAY_OF_WEEK) - d;
		if (firstDay == -1)
			firstDay = 6;
		daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/*$Id: JNCalendar.java,v 1.8 2004/11/05 07:38:10 pbielen Exp $*/
	public class JNCalendarModel extends AbstractTableModel {

		private String[] dayNames = Local.getWeekdayNames();

		public JNCalendarModel() {
			super();
		}

		public int getColumnCount() {
			return 7;
		}

		public Object getValueAt(int row, int col) {
			//int pos = (row * 7 + col) - firstDay + 1;
			int pos = (row * 7 + (col + 1)) - firstDay;
			if ((pos > 0) && (pos <= daysInMonth))
				return new Integer(pos);
			else
				return null;

		}

		public int getRowCount() {
			return 6;
		}

		public String getColumnName(int col) {
			return dayNames[col];
		}

	}

	class CalendarPopup extends JPopupMenu {
		JMenuItem addTask;
		JMenuItem viewTasks;
		JMenuItem removeTask;
		JMenuItem curDate;
		JMenu tasksMenu;
		
		Action addTaskAction = new AbstractAction("Add") {
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskDialog dlg = new TaskDialog(App.getFrame(), Local.getString("New task"));
		        Dimension frmSize = App.getFrame().getSize();
		        Point loc = App.getFrame().getLocation();
		        dlg.startDate.getModel().setValue(CurrentDate.get().getDate());
		        dlg.endDate.getModel().setValue(CurrentDate.get().getDate());
		        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
		        dlg.setVisible(true);
		        if (dlg.CANCELLED)
		            return;
		        CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
		        CalendarDate ed;
		 		if(dlg.chkEndDate.isSelected())
		 			ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
		 		else
		 			ed = null;
		        long effort = Util.getMillisFromHours(dlg.effortField.getText());
				Task newTask = CurrentProject.getTaskList().createTask(sd, ed, dlg.todoField.getText(), dlg.priorityCB.getSelectedIndex(),effort, dlg.descriptionField.getText(),null);
				newTask.setProgress(((Integer)dlg.progress.getValue()).intValue());
		        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
		        DailyItemsPanel diPanel = App.getFrame().workPanel.dailyItemsPanel;
		        diPanel.tasksPanel.taskTable.tableChanged();
		        diPanel.updateIndicators();
		        diPanel.agendaPanel.refresh(CurrentDate.get());
			}
		};
		
		Action viewTasksAction = new AbstractAction("View") {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getFrame().workPanel.tasksB_actionPerformed(null);
				viewTasks.setEnabled(false);
			}
		};
		
		Action removeTaskAction = new AbstractAction("Remove") {
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskRemoveDialog trDialog = new TaskRemoveDialog(App.getFrame(),"Remove Task");
				Dimension frmSize = App.getFrame().getSize();
		        Point loc = App.getFrame().getLocation();
				trDialog.setLocation((frmSize.width - trDialog.getSize().width) / 2 + loc.x, (frmSize.height - trDialog.getSize().height) / 2 + loc.y);
				trDialog.setVisible(true);
				if (trDialog.CANCELLED)
					return;
				else {
					// only 1 row can be selected but leaving multi-select handling code in case this changes later on
					String msg;
			        String thisTaskId = trDialog.taskTable.getModel().getValueAt(trDialog.taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
			        if (trDialog.taskTable.getSelectedRows().length > 1)
			            msg = Local.getString("Remove")+" "+trDialog.taskTable.getSelectedRows().length +" "+Local.getString("tasks")+"?"
			             + "\n"+Local.getString("Are you sure?");
			        else {        	
			        	Task t = CurrentProject.getTaskList().getTask(thisTaskId);
						if(CurrentProject.getTaskList().hasSubTasks(thisTaskId)) {
							msg = Local.getString("Remove task")+"\n'" + t.getText() + Local.getString("' and all subtasks") +"\n"+Local.getString("Are you sure?");
						}
						else {		            
							msg = Local.getString("Remove task")+"\n'" + t.getText() + "'\n"+Local.getString("Are you sure?");
						}
			        }
			        int n =
			            JOptionPane.showConfirmDialog(
			                App.getFrame(),
			                msg,
			                Local.getString("Remove task"),
			                JOptionPane.YES_NO_OPTION);
			        if (n != JOptionPane.YES_OPTION)
			            return;
			        Vector toremove = new Vector();
			        for (int i = 0; i < trDialog.taskTable.getSelectedRows().length; i++) {
			            Task t =
			            CurrentProject.getTaskList().getTask(
			                trDialog.taskTable.getModel().getValueAt(trDialog.taskTable.getSelectedRows()[i], TaskTable.TASK_ID).toString());
			            if (t != null)
			                toremove.add(t);
			        }
			        for (int i = 0; i < toremove.size(); i++) {
			            CurrentProject.getTaskList().removeTask((Task)toremove.get(i));
			        }
			        trDialog.taskTable.tableChanged();
			        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
			        DailyItemsPanel diPanel = App.getFrame().workPanel.dailyItemsPanel;
			        diPanel.tasksPanel.taskTable.tableChanged();
			        diPanel.updateIndicators();
			        diPanel.agendaPanel.refresh(CurrentDate.get());
				}
			}
		};
		
		public CalendarPopup() {
			tasksMenu = new JMenu("Tasks");
			addTask = new JMenuItem(addTaskAction);
			viewTasks = new JMenuItem(viewTasksAction);
			removeTask = new JMenuItem(removeTaskAction);
			curDate = new JMenuItem("");
			curDate.setEnabled(false);
			
			this.add(curDate);
			this.addSeparator();
			tasksMenu.add(addTask);
			tasksMenu.add(viewTasks);
			tasksMenu.add(removeTask);
			this.add(tasksMenu);
			
			// attach listener to Calendar
			addSelectionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateMenu();
				}
			});
		}
		
		public void updateMenu() {
			curDate.setText(_date.getFullDateString());
			try {
				if (Context.get("CURRENT_PANEL").toString().equalsIgnoreCase("tasks"))
					viewTasks.setEnabled(false);
				else
					viewTasks.setEnabled(true);
			}
			catch (Exception e) {
				//ignore
			}
		}
	}

}


