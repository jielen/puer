/**
 * 
 */
package com.ufgov.zc.client.component;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ufgov.smartclient.component.JComboBoxEx;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.common.system.model.AsVal;

/**
 * @author Administrator
 *
 */
public class YearComboBox extends JComboBoxEx {

  /**
   * 
   */
  private static final long serialVersionUID = 796537382718494743L;

  private List dataList = new ArrayList();

  private Map<String, AsVal> dataMap = new HashMap<String, AsVal>();

  private boolean firstAddNull = true;

  private boolean defaultCurrentYear = true;

  public YearComboBox() {

    super();

    init();

  }

  public YearComboBox(boolean firstAddNull) {

    super();

    this.firstAddNull = firstAddNull;

    init();

  }

  public YearComboBox(boolean firstAddNull, boolean defaultCurrentYear) {

    super();

    this.firstAddNull = firstAddNull;

    this.defaultCurrentYear = defaultCurrentYear;

    init();

  }

  private void init() {

    dataList = initDataList();

    if (firstAddNull) {

      this.addItem(null);

    }

    for (int i = 0; i < dataList.size(); i++) {

      AsVal data = (AsVal) dataList.get(i);

      this.addItem(data);

      this.addItemDisplaLable(data, data.getVal());

      this.dataMap.put(data.getValId(), data);

    }

    this.addItemListener(new ItemListener() {

      public void itemStateChanged(ItemEvent e) {

        if (((AsVal) getSelectedItem()) != null) {

          setToolTipText(((AsVal) e.getItem()).getVal());

        } else {

          setToolTipText(null);

        }

      }

    });

    if (this.defaultCurrentYear) {

      int year = WorkEnv.getInstance().getTransNd();

      this.setSelectedAsValByCode(Integer.toString(year));

    }

  }

  private List initDataList() {
    // TCJLODO Auto-generated method stub
    String beginYear = AsOptionMeta.getOptVal("SF_OPTION_BEGIN_YEAR");
    int year = 2010;
    if (beginYear != null && Integer.parseInt(beginYear) > 0) {
      year = Integer.parseInt(beginYear);
    }
    int curYear = WorkEnv.getInstance().getTransNd();
    for (int i = year; i <= curYear; i++) {
      AsVal val = new AsVal();
      val.setVal("" + i);
      val.setValId("" + i);
      dataList.add(val);
    }
    return dataList;
  }

  public AsVal getSelectedAsVal() {

    return (AsVal) this.getSelectedItem();

  }

  public void setSelectedAsVal(AsVal asVal) {

    this.setSelectedItem(asVal);

  }

  public void setSelectedAsValByCode(String asValCode) {

    if (asValCode == null) {
      this.setSelectedItem(null);
    }
    AsVal asVal = dataMap.get(asValCode);

    this.setSelectedItem(asVal);

  }

  public static void main(String[] args) {

    JFrame f = new JFrame();

    YearComboBox textField = new YearComboBox();

    textField.setEditable(false);

    textField.setEnabled(true);

    textField.setSelectedAsValByCode(null);

    JPanel panel = new JPanel();

    panel.add(textField);

    f.getContentPane().add(panel, BorderLayout.NORTH);

    // f.pack();

    // SwingUtilities.updateComponentTreeUI(panel);

    f.setSize(400, 300);

    f.setLocationRelativeTo(null);

    f.setVisible(true);

    f.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {

        System.exit(0);

      }

    });

  }

}
