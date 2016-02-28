package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.sf.memoranda.util.Util;

@SuppressWarnings("serial")
public class EffortTimerFrame extends JFrame {
	private static EffortTimerFrame thisFrame = null;
	private EffortTimerPanel effortPanel;
	private boolean advancedGraphics;
	private Point lastClick;
	
	private EffortTimerFrame() {
		super("Memoranda - Effort Timer");
		this.init();
		pack();
	}
	
	private void init() {
		advancedGraphics = Util.checkTranslucentSupport();
		effortPanel = new EffortTimerPanel();
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
		setContentPane(effortPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		addMouseListener(new DragListener());
		addMouseMotionListener(new DragListener());
		setAlwaysOnTop(true);
		setLocationRelativeTo(App.getFrame());
		setJMenuBar(new EffortTimerMenu());
	}
	
	public static EffortTimerFrame getInstance() {
		if (thisFrame == null) 
			thisFrame = new EffortTimerFrame();
		return thisFrame;
	}
	
	@Override
	protected void processWindowEvent(WindowEvent we) {
		switch(we.getID()) {
			case WindowEvent.WINDOW_DEACTIVATED:
				if (advancedGraphics) thisFrame.setOpacity(0.33f);
				break;
			case WindowEvent.WINDOW_ACTIVATED:
				if (advancedGraphics) thisFrame.setOpacity(1f);
				effortPanel.updateUI();
				break;
			case WindowEvent.WINDOW_ICONIFIED:
			case WindowEvent.WINDOW_CLOSING:
				thisFrame.setVisible(false);
				break;
			default:
				super.processWindowEvent(we);
		}
	}
	
	private class DragListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent me) {
			lastClick = me.getLocationOnScreen();
		}
		
		@Override
		public void mouseDragged(MouseEvent me) {
			Rectangle curPos = EffortTimerFrame.getInstance().getBounds();
			Point curClick = me.getLocationOnScreen();
			curPos.x += (curClick.x - lastClick.x);
			curPos.y += (curClick.y - lastClick.y);
			lastClick = curClick;
			EffortTimerFrame.getInstance().setBounds(curPos);
		}
	}
	
	private static class EffortTimerMenu extends JMenuBar {
		public EffortTimerMenu() {
			super();
			this.init();
		}
		
		private void init() {
			JMenu mainMenu = new JMenu("Menu");
			JMenuItem manualEntry = new JMenuItem("Add new manual entry...");
			manualEntry.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent ae) {
					ManualTimeEntryDialog manualEntry = new ManualTimeEntryDialog();
					manualEntry.setVisible(true);
				}
			});
			JMenuItem hideFrame = new JMenuItem("Hide");
			hideFrame.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent ae) {
					EffortTimerFrame.getInstance().setVisible(false);
				}
			});
			mainMenu.add(manualEntry);
			mainMenu.add(hideFrame);
			this.add(mainMenu);
		}
	}
}
