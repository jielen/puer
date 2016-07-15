package com.ufgov.zc.client.component;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.View;

/**
 * 帮助消息显示面板
 * @author Administrator
 *
 */

public class HelpMsgPanel extends JPanel {

  private String msg;

  private String msgSub;

  private JLabel msgLb = new JLabel();

  /**
   * @param args
   * Administrator 
   * Jul 9, 2013
   */
  public static void main(String[] args) {
    // TCJLODO Auto-generated method stub

  }

  public HelpMsgPanel(String helpMsg) {
    super();
    this.msg = helpMsg;
    init();
  }

  private void init() {
    // TCJLODO Auto-generated method stub
    this.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));

    msgLb.setText(msg);
    setMsgLbSizt();
    this.add(msgLb);
  }

  private void setMsgLbSizt() {
    // TCJLODO Auto-generated method stub

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice gs = ge.getDefaultScreenDevice();

    GraphicsConfiguration gc = gs.getDefaultConfiguration();

    Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int maxWidth = new Double(screenSize.getWidth() - 100).intValue(); //限制的宽度

    View v = javax.swing.plaf.basic.BasicHTML.createHTMLView(msgLb, msgLb.getText());

    v.setSize(maxWidth, Integer.MAX_VALUE);
    int h = (int) v.getMinimumSpan(View.Y_AXIS); //这是取得的高度

    msgLb.setPreferredSize(new Dimension(maxWidth, h));
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
    this.msgLb.setText(msg);
    //    this.msgLb.setToolTipText(msg);
  }

}
