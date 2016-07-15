package com.ufgov.zc.client.sf.component;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JComponent;

public class BaseOpaqueCtrl extends JComponent {

	private static final long serialVersionUID = 1L;

	public BaseOpaqueCtrl() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
	}

	@Override
	public final void update(Graphics g) {
		// TCJLODO Auto-generated method stub
		super.update(g);
	}

}
