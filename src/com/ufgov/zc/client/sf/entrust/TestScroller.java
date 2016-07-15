package com.ufgov.zc.client.sf.entrust;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TestScroller extends JPanel {
  JPanel panel;

  JScrollPane scrollPane;

  public TestScroller() {
    // TCJLODO Auto-generated constructor stub

    //this is in the constructor of the mother object, JPanel
    panel = new JPanel();
    panel.setBorder(BorderFactory.createEtchedBorder());
    //panel.setBounds(0,0,415,420);

    panel.setPreferredSize(new Dimension(415, 420));
    panel.setBackground(Color.BLACK);

    scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(new Dimension(415, 400));
    add(scrollPane);
    //  add(scrollPane, 0, 40, 415, 400);
  }

  /*  //method add defined in the mother object
        public void add(Component c, int x, int y, int sX, int sY)
        {
            add(c);
            c.setBounds(x,y,sX,sY);
        }*/

  public static void main(String args[]) {
    JPanel baseView = new TestScroller();
    baseView.setPreferredSize(new Dimension(500, 500));
    baseView.setOpaque(false);
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(baseView);
    frame.setSize(500, 500);

    frame.setVisible(true);
    frame.repaint();
  }
}
