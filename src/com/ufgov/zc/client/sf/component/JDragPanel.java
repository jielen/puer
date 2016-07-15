package com.ufgov.zc.client.sf.component;

import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 窗体或者Dialog的主面板
 * 
 * @author Administrator
 * 
 */
public class JDragPanel extends BaseOpaqueCtrl {

	private static final long serialVersionUID = 1L;
	private Window window;

	public JDragPanel(Window window) {
		DragAction action = new DragAction();
		addMouseListener(action);
		addMouseMotionListener(action);
		this.window = window;
	}

	class DragAction extends MouseAdapter {
		private Point p;
		private boolean mouseDown = false;

		@Override
		public void mouseClicked(MouseEvent e) {
			mouseDown = false;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			mouseDown = false;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			mouseDown = false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			p = (Point) e.getPoint().clone();
			mouseDown = true;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mouseDown = false;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (!mouseDown) {
				return;
			}
			Point l = window.getLocation();
			Point currentP = e.getPoint();
			window.setLocation(l.x + (currentP.x - p.x), l.y
					+ (currentP.y - p.y));
		}
	}

}
