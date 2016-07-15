package com.ufgov.zc.client.sf.component;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.ImageIcon;

public class JImageButton extends BaseOpaqueCtrl {

	private static final long serialVersionUID = 1L;
	protected ImageIcon icon;
	protected int status;
	protected int w;
	protected Action action;

	protected static final int STATUS_NONE = 0;
	protected static final int STATUS_HOVER = 1;
	protected static final int STATUS_DOWN = 2;

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public JImageButton(ImageIcon icon) {
		this.icon = icon;
		this.icon = icon;

		initMouseStyle();
	}

	/**
	 * 用来实例化鼠标效果
	 */
	protected void initMouseStyle() {
		w = icon.getIconWidth() / 3;
		status = STATUS_NONE;
		setPreferredSize(new Dimension(w, icon.getIconHeight()));

		ButtonStyle mouseAction = new ButtonStyle();
		addMouseListener(mouseAction);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		int x = 0;
		if (status == STATUS_NONE) {
			x = 0;
		} else if (status == STATUS_HOVER) {
			x = w;
		} else if (status == STATUS_DOWN) {
			x = w << 1;
		}
		g.drawImage(icon.getImage(), 0, 0, w, icon.getIconHeight(), x, 0,
				x + w, icon.getIconHeight(), this);
	}

	class ButtonStyle implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (action == null) {
				return;
			}
			action.actionPerformed(new ActionEvent(e.getSource(), e.getID(),
					"click"));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			status = STATUS_DOWN;
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			status = STATUS_HOVER;
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			status = STATUS_HOVER;
      setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			status = STATUS_NONE;
      setCursor(Cursor.getDefaultCursor());
			repaint();
		}
	}
}
