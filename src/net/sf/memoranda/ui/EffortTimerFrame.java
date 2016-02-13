package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import net.sf.memoranda.util.Util;

@SuppressWarnings("serial")
public class EffortTimerFrame extends JFrame {
	private static EffortTimerFrame thisFrame = null;
	private EffortTimerPanel effortPanel;
	private boolean advancedGraphics;
	private Point lastClick;
	
	private EffortTimerFrame() {
		super("Memoranda - Effort Timer");
		init();
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
}
