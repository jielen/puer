/**
 * 
 */
package com.ufgov.zc.client.sf.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JToolTip;
import javax.swing.UIManager;

/** 
* A JTabbedPane which has a close ('X') icon on each tab. 
* To add a tab, use the method addTab(String, Component) 
* To have an extra icon on each tab (e.g. like in JBuilder, 
* showing the file type) use the method 
* addTab(String, Component, Icon). 
* Only clicking the 'X' closes the tab. */
public class JClosableTabbedPane extends JTabbedPane implements MouseListener {
  /**
  * 
  */
  private static final long serialVersionUID = -2655936441880005181L;

  private double scaleRatio = 0.3;

  private HashMap<String, Component> maps = new HashMap<String, Component>();
  
  private List<Component> notRemoveTabs=new ArrayList<Component>();
  
  private boolean closeAble=true;

  public JClosableTabbedPane() {
    super();
    addMouseListener(this);
  }

  public JClosableTabbedPane(int tabPlacement) {
    super(tabPlacement);
    addMouseListener(this);
  }
  public void addTab(String title, Component component) {
    this.insertTab(title, new CloseTabIcon(null),component, null,-1);
  }
  public void addTabNotClose(String title, Component component) {
    this.insertTab(title, new CloseTabIcon(null),component, null,-1);
    notRemoveTabs.add(component);
  }
  public void addTab(String title, Component component, Icon extraIcon) {
    this.insertTab(title, new CloseTabIcon(extraIcon), component,null,-1);
  }
  public void addTabNotClose(String title, Component component, Icon extraIcon) {
    this.insertTab(title, new CloseTabIcon(extraIcon), component,null,-1);
    notRemoveTabs.add(component);
  }

  public void insertTab(String title, Icon icon, Component component, String tip, int index) {
    tip =title;
    maps.put(tip+component.hashCode(), component);
    if(index==-1){
      super.addTab(title, icon, component, tip);
    }else{
      super.insertTab(title, icon, component, tip, index);
    }
  }

  public void removeTabAt(int index) {
    Component component = getComponentAt(index);
    if(notRemoveTabs.contains(component)){
      return;
    }
    maps.remove("tab" + component.hashCode());
    super.removeTabAt(index);
  }

  public JToolTip createToolTip() {
    return super.createToolTip();
    /*ImageToolTip tooltip = new ImageToolTip();
    tooltip.setComponent(this);
    return tooltip;*/
  }

  class ImageToolTip extends JToolTip {
    public Dimension getPreferredSize() {
      String tip = getTipText();
      Component component = maps.get(tip);
      if (component != null) {
        return new Dimension((int) (getScaleRatio() * component.getWidth()), (int) (getScaleRatio() * component.getHeight()));
      } else {
        return super.getPreferredSize();
      }
    }

    public void paintComponent(Graphics g) {
      String tip = getTipText();
      Component component = maps.get(tip);
      if (component instanceof JComponent) {
        JComponent jcomponent = (JComponent) component;
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = g2d.getTransform();
        g2d.transform(AffineTransform.getScaleInstance(getScaleRatio(), getScaleRatio()));
        ArrayList<JComponent> dbcomponents = new ArrayList<JComponent>();
        updateDoubleBuffered(jcomponent, dbcomponents);
        jcomponent.paint(g);
        resetDoubleBuffered(dbcomponents);
        g2d.setTransform(at);
      }
    }

    private void updateDoubleBuffered(JComponent component, ArrayList<JComponent> dbcomponents) {
      if (component.isDoubleBuffered()) {
        dbcomponents.add(component);
        component.setDoubleBuffered(false);
      }
      for (int i = 0; i < component.getComponentCount(); i++) {
        Component c = component.getComponent(i);
        if (c instanceof JComponent) {
          updateDoubleBuffered((JComponent) c, dbcomponents);
        }
      }
    }

    private void resetDoubleBuffered(ArrayList<JComponent> dbcomponents) {
      for (JComponent component : dbcomponents) {
        component.setDoubleBuffered(true);
      }
    }
  }

  public double getScaleRatio() {
    return scaleRatio;
  }

  public void setScaleRatio(double scaleRatio) {
    this.scaleRatio = scaleRatio;
  }

  public void mouseClicked(MouseEvent e) {
//	  System.out.println("MOUSE KEY:"+e.getButton());
	  if(e.getButton()==e.BUTTON3){//right mouse key
//		  System.out.println("you jian");
		  return;
	  }
    int tabNumber = getUI().tabForCoordinate(this, e.getX(), e.getY());
    if (tabNumber < 0) {
      return;
    }
    Rectangle rect = ((CloseTabIcon) getIconAt(tabNumber)).getBounds();
    if (rect.contains(e.getX(), e.getY()) && closeAble) {
      //the tab is being closed  
    	if(beforeCloseTab(tabNumber)){
    		this.removeTabAt(tabNumber);
    	}
    	afterCloseTab(tabNumber);
    }
  }

  public boolean beforeCloseTab(int tabIndex){
	  return true;
  }
  public boolean afterCloseTab(int tabIndex){
	  return true;
  }
  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    JClosableTabbedPane pane = new JClosableTabbedPane();
    ImageIcon icon = new ImageIcon(JClosableTabbedPane.class.getClass().getResource("/img/func/send.jpg"));
    pane.addTab("tab1", new JButton("first Button"), null);
    pane.addTab("tab2", new JButton("sec Button"), null);
    pane.addTab("tab3", new JButton("third Button"), null);
    pane.addTab("tab4", new JButton("fourth Button"), null);
    pane.addTabNotClose("tab5", new JButton("five Button"), null);
    
    for(int i=0;i<pane.getTabCount();i++){
//      System.out.println("no."+i+":"+pane.getComponent(i));
    }
    JFrame frame = new JFrame("Demo");
    frame.getContentPane().add(pane, BorderLayout.CENTER);
    frame.setSize(500, 300);
    frame.setLocation(300, 200);
    frame.show();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
  }

public boolean isCloseAble() {
	return closeAble;
}

public void setCloseAble(boolean closeAble) {
	this.closeAble = closeAble;
}
}

