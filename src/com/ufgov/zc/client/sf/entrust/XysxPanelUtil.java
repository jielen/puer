/**
 * 
 */
package com.ufgov.zc.client.sf.entrust;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.common.commonbiz.model.BillElement;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfXysx;

/**
 * 协议事项面板辅助类
 * @author Administrator
 *
 */
public class XysxPanelUtil {

  public static JComponent createXysxPanel(Class billClass, BillElementMeta eleMeta, AsValFieldEditor jdDocSendType, TextFieldEditor jdDocSendTypeFz,
    Hashtable<BigDecimal, JComponent> xysxComponents) {
    // TCJLODO Auto-generated method stub

    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(null);
    int x, y, w, h = 0;
    int reg = 10;
    int tab = 40;
    int rowReg = 1;
    int rowHeight = 23;

    JLabel l = new JLabel();
    l.setText("1.鉴定文书发送方式:");
    FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(l.getFont());
    w = fm.stringWidth(l.getText());
    x = reg;
    y = reg;
    h = rowHeight;
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;

    w = 100;
    jdDocSendType.setBounds(x, y, w, h);
    contentPanel.add(jdDocSendType);
    x = x + w + reg;

    l = new JLabel();
    l.setText("地址或备注:");
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;

    w = 600;
    jdDocSendTypeFz.setBounds(x, y, w, h);
    contentPanel.add(jdDocSendTypeFz);
    x = reg;
    y = y + h + 5;

    JSeparator sep1 = new JSeparator();
    sep1.setBounds(x, y, getScreenWidth() - 100, 1);
    sep1.setForeground(Color.WHITE);
    contentPanel.add(sep1);
    x = reg;
    y = y + 5;

    int valId = 1;
    String str = "2.鉴定机构应当严格依照有关技术规范保管和使用鉴定材料。鉴定委托人同意或认可：";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    JCheckBox c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "因鉴定需要耗尽检材；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "因鉴定需要可能损坏检材；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "鉴定完成后无法完整退还检材；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "检材留样保存3个月。";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = reg;
    y = y + h + rowReg;

    str = "3.鉴定时限：从协议签订之日起";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;
    JTextField tf = new JTextField();
    w = 50;
    tf.setBounds(x, y, w, h);
    tf.setHorizontalAlignment(SwingUtilities.CENTER);
    tf.setText("" + 30);
    contentPanel.add(tf);
    xysxComponents.put(new BigDecimal(valId), tf);
    ++valId;
    x = x + w + reg;
    str = "个工作日完成。遇复杂、疑难、特殊的技术问题，或者检验过程确需要较长时间的，延长";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;
    tf = new JTextField();
    w = 50;
    tf.setBounds(x, y, w, h);
    tf.setHorizontalAlignment(SwingUtilities.CENTER);
    tf.setText("" + 30);
    contentPanel.add(tf);
    xysxComponents.put(new BigDecimal(valId), tf);
    ++valId;
    x = x + w + reg;
    str = "个工作日；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = reg;
    y = y + h + rowReg;

    str = "4.特殊情形鉴定：";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "需要对女性作妇科检查；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "需要对未成年人的身体进行检查；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "需要对被鉴定人进行法医精神病鉴定；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "需要到现场提取检材；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = tab;
    y = y + h + rowReg;

    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "需要进行尸体解剖；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = reg;
    y = y + h + rowReg;

    str = "5.";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;
    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "需要补充或者重新提取鉴定材料的，延长";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;
    tf = new JTextField();
    w = 50;
    tf.setBounds(x, y, w, h);
    tf.setHorizontalAlignment(SwingUtilities.CENTER);
    contentPanel.add(tf);
    xysxComponents.put(new BigDecimal(valId), tf);
    ++valId;
    x = x + w + reg;
    str = "个工作日；";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = reg;
    y = y + h + rowReg;

    str = "6.";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;
    c = new JCheckBox();
    w = 20;
    c.setBounds(x, y, w, h);
    contentPanel.add(c);
    xysxComponents.put(new BigDecimal(valId), c);
    ++valId;
    x = x + w + reg;
    str = "委托人要求鉴定人回避。被要求回避的鉴定人姓名:";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;
    tf = new JTextField();
    w = 600;
    tf.setBounds(x, y, w, h);
    tf.setHorizontalAlignment(SwingUtilities.LEFT);
    contentPanel.add(tf);
    xysxComponents.put(new BigDecimal(valId), tf);
    ++valId;
    x = reg;
    y = y + h + rowReg;

    str = "7.鉴定过程中如需变更协议书内容，由协议双方协议确定。";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    //    System.out.println("x=" + x + ",y=" + y);
    contentPanel.add(l);
    x = reg;
    y = y + h + rowReg;

    str = "8.其他约定事项:";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    //    System.out.println("x=" + x + ",y=" + y);
    contentPanel.add(l);
    x = x + w + reg;
    JTextArea txtArea = new JTextArea();
    w = 1000;
    h = 100;
    txtArea.setBounds(x, y, w, h);
    //    System.out.println("x=" + x + ",y=" + y);
    JScrollPane js = new JScrollPane(txtArea);
    js.setBounds(x, y, w, h);
    contentPanel.add(js);
    xysxComponents.put(new BigDecimal(valId), txtArea);
    ++valId;
    x = reg;
    y = y + h + 5;
    h = rowHeight;

    str = "9.协议变更事项:";
    l = new JLabel();
    l.setText(str);
    w = fm.stringWidth(l.getText());
    l.setBounds(x, y, w, h);
    //    System.out.println("x=" + x + ",y=" + y);
    contentPanel.add(l);
    x = x + w + reg;
    JTextArea txtArea2 = new JTextArea();
    w = 1000;
    h = 100;
    txtArea2.setBounds(x, y, w, h);
    //    System.out.println("x=" + x + ",y=" + y);
    JScrollPane js2 = new JScrollPane(txtArea2);
    js2.setBounds(x, y, w, h);
    contentPanel.add(js2);
    xysxComponents.put(new BigDecimal(valId), txtArea2);
    ++valId;
    x = reg;
    y = y + h + rowReg;
    h = rowHeight;

    contentPanel.setPreferredSize(new Dimension(getScreenWidth(), y));
    JScrollPane jp = new JScrollPane(contentPanel);
    jp.setPreferredSize(new Dimension(getScreenWidth() - 100, 200));
    jp.getVerticalScrollBar().setUnitIncrement(20);
    return jp;
  }

  public static void setValue(SfEntrust bill, Hashtable<BigDecimal, JComponent> xysxComponents) {
    if (bill == null || bill.getXysxLst() == null)
      return;
    for (int i = 0; i < bill.getXysxLst().size(); i++) {
      SfXysx sx = (SfXysx) bill.getXysxLst().get(i);
      if (sx.getXysxTypeId() != null && xysxComponents.containsKey(sx.getXysxTypeId())) {
        JComponent c = xysxComponents.get(sx.getXysxTypeId());
        if (c instanceof JCheckBox) {
          JCheckBox cb = (JCheckBox) c;
          if (sx.getInputContent() != null && sx.getInputContent().trim().equalsIgnoreCase("y")) {
            cb.setSelected(true);
          } else {
            cb.setSelected(false);
          }
        } else if (c instanceof JTextField) {
          JTextField tf = (JTextField) c;
          tf.setText(sx.getInputContent());
        } else if (c instanceof JTextArea) {
          JTextArea ta = (JTextArea) c;
          ta.setText(sx.getInputContent());
        }
      }
    }
  }

  public static SfEntrust getValue(SfEntrust bill, Hashtable<BigDecimal, JComponent> xysxComponents) {
    bill.setXysxLst(new ArrayList());
    Iterator<BigDecimal> keys = xysxComponents.keySet().iterator();
    while (keys.hasNext()) {
      BigDecimal key = keys.next();
      //      System.out.println(key.intValue());
      SfXysx sx = new SfXysx();
      sx.setEntrustId(bill.getEntrustId());
      sx.setXysxTypeId(key);
      JComponent c = xysxComponents.get(key);
      if (c instanceof JCheckBox) {
        JCheckBox cb = (JCheckBox) c;
        sx.setInputContent(cb.isSelected() ? "Y" : "");
      } else if (c instanceof JTextField) {
        JTextField jf = (JTextField) c;
        sx.setInputContent(jf.getText());
      } else if (c instanceof JTextArea) {
        JTextArea ta = (JTextArea) c;
        sx.setInputContent(ta.getText());
      }
      bill.getXysxLst().add(sx);
    }
    return bill;
  }

  public static void updateEditable(boolean isEdit, Hashtable<BigDecimal, JComponent> xysxComponents) {
    Iterator<BigDecimal> keys = xysxComponents.keySet().iterator();
    while (keys.hasNext()) {
      BigDecimal key = keys.next();
      JComponent c = xysxComponents.get(key);
      c.setEnabled(isEdit);
    }
  }

  static int getScreenWidth() {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice gs = ge.getDefaultScreenDevice();

    GraphicsConfiguration gc = gs.getDefaultConfiguration();

    Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.width - 100;

  }
}
