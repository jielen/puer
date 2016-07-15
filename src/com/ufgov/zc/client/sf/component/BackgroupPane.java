package com.ufgov.zc.client.sf.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import com.ufgov.zc.client.sf.util.ResourceUtil;


public class BackgroupPane extends BaseOpaqueCtrl {

	private static final long serialVersionUID = 1L;
	private BufferedImage img;

	public BackgroupPane(Image img) {
		this.img = ResourceUtil.toBufferedImage(img);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect = new Rectangle(0,0,getWidth(),getHeight());
		g2.setPaint(new TexturePaint(img, rect));
		g2.fill(rect);
	}
}
