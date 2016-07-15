package com.ufgov.zc.client.component.element;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.JButtonTextField;
import com.ufgov.zc.common.commonbiz.model.EAcc;
import com.ufgov.zc.common.system.util.LevelControlUtil;

public class EAccTreeSelectField extends JButtonTextField {

  private int nd = WorkEnv.getInstance().getTransNd();

  public void handleClick(JButtonTextField jButtonTextField) {

    await();

    selectDialog.setVisible(true);

  }

  private void init(String compoId) {

    this.elementCode = "E_ACC_CODE";

    this.compoId = compoId;

    selectDialog = new EAccTreeSelectDialog(owner, true, EAccTreeSelectField.this);

  }
  private void init(String compoId,String parentCode) {

    this.elementCode = "E_ACC_CODE";

    this.compoId = compoId;

    selectDialog = new EAccTreeSelectDialog(owner, true, EAccTreeSelectField.this,parentCode);

  }
  public void setCtrlLevelNum(int ctrlLevelNum) {

    super.setCtrlLevelNum(ctrlLevelNum);

    this.ctrlLen = LevelControlUtil.getCtrLength(AsOptionMeta.getOptVal("OPT_EACC_LEVEL"), ctrlLevelNum);

  }

  public void setValue(Object value) {

    this.value = value;

    if (value != null) {

      EAcc data = (EAcc) value;

      this.setText(data.getName());

      this.setToolTipText("[" + data.getCode() + "]" + data.getName());

    } else {

      this.setText("");

      setToolTipText(null);

    }

    this.fireValueChanged();

  }

  public void setEAcc(EAcc value) {

    this.setValue(value);

  }

  public EAcc getEAcc() {

    return (EAcc) this.getValue();

  }

  public EAccTreeSelectField(int col) {

    super(col);

    this.init(null);

  }

  public EAccTreeSelectField(int col, int nd) {

    super(col);

    this.nd = nd;

    this.init(null);

  }

  public EAccTreeSelectField(int col, String compoId) {

    super(col);

    this.init(compoId);

  }

  public EAccTreeSelectField() {

    super();

    this.init(null);

  }

  public EAccTreeSelectField(String compoId) {

    super();

    this.init(compoId);

  }
  public EAccTreeSelectField(String compoId,String parentCode) {

    super();

    this.init(compoId,parentCode);

  }
  public EAccTreeSelectField(int col, Dialog owner) {

    super(col);

    this.owner = owner;

    this.init(null);

  }

  public EAccTreeSelectField(int col, Dialog owner, String compoId) {

    super(col);

    this.owner = owner;

    this.init(compoId);

  }

  public EAccTreeSelectField(Dialog owner) {

    super();

    this.owner = owner;

    this.init(null);

  }

  public EAccTreeSelectField(Dialog owner, String compoId) {

    super();

    this.owner = owner;

    this.init(compoId);

  }

  public static void main(String[] args) {

    JFrame f = new JFrame();

    long start = System.currentTimeMillis();

    EAccTreeSelectField textField = new EAccTreeSelectField("","301");

    System.out.println(System.currentTimeMillis() - start);

    textField.setEditable(true);

    textField.setEnabled(false);

    textField.setEnabled(true);

    //    textField.setPrefix("205");

    textField.setCtrlLevelNum(1);

    //    textField.setValueByCode("2050404");

    JPanel panel = new JPanel();

    panel.add(textField);

    panel.add(new JTextField(10));

    f.getContentPane().add(panel, BorderLayout.NORTH);

    // f.pack();

    // SwingUtilities.updateComponentTreeUI(panel);

    f.setSize(400, 300);

    f.setLocationRelativeTo(null);

    f.setVisible(true);

    f.addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent e) {

        System.exit(0);

      }

    });

  }

  public int getNd() {

    return nd;

  }

  public void setNd(int nd) {

    this.nd = nd;

  }

}
