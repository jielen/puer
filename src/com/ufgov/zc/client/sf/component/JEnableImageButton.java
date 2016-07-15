package com.ufgov.zc.client.sf.component;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * 可以禁用的ImageButton
 * 
 * @author Administrator
 * 
 */
public class JEnableImageButton extends JImageButton {

	private static final long serialVersionUID = 1L;
	// 是否禁用这个按钮
	protected boolean enable = true;

	public JEnableImageButton(ImageIcon icon) {
		super(icon);
	}

	/**
	 * 用来实例化鼠标效果
	 */
	protected void initMouseStyle() {
		w = icon.getIconWidth() / 4;
		status = STATUS_NONE;
		setPreferredSize(new Dimension(w, icon.getIconHeight()));

		ButtonStyle mouseAction = new ButtonStyle();
		addMouseListener(mouseAction);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		int x = 0;
		if (enable) {
			if (status == STATUS_NONE) {
				x = 0;
			} else if (status == STATUS_HOVER) {
				x = w;
			} else if (status == STATUS_DOWN) {
				x = w << 1;//相当于 x=w*2
			}
		} else {
			x = w * 3;
		}
		g.drawImage(icon.getImage(), 0, 0, w, icon.getIconHeight(), x, 0,
				x + w, icon.getIconHeight(), this);
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
