package com.ufgov.zc.client.sf.jdresult;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;

/**
 * 新增鉴定结果时，新增一个时，先弹出这个界面，先选择一个委托，然后进入具体的编辑界面
 * @author Administrator
 *
 */
public class SfJdResultFirstDialog extends GkBaseDialog {

  /**
   * 
   */
  private static final long serialVersionUID = 7752694816844590053L;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  SfJdResult currentBill;

  ForeignEntityFieldEditor entrustField;

  public SfJdResultFirstDialog(SfJdResultListPanel listPanel) {
    super(listPanel.getParentWindow(), Dialog.ModalityType.APPLICATION_MODAL);

    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");

    init();

    this.setTitle(LangTransMeta.translate(listPanel.getcompoId()));

    this.setPreferredSize(new Dimension(320, 200));

    this.setResizable(false);

    this.moveToScreenCenter();

    this.pack();

    //editPanel.refreshData();s

    this.setVisible(true);

  }

  private void init() {
    // TCJLODO Auto-generated method stub

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(null);
    contentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "选择鉴定委托", TitledBorder.CENTER, TitledBorder.TOP,
      new Font("宋体", Font.BOLD, 15), Color.BLUE));
    int x, y, w, h = 0;
    int reg = 10;
    int x0 = 30, y0 = 40;
    int tab = 40;
    int rowReg = 1;
    int rowHeight = 23;

    JLabel l = new JLabel();
    l.setText("委托编号:");
    FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(l.getFont());
    w = fm.stringWidth(l.getText());
    x = x0;
    y = y0;
    h = rowHeight;
    l.setBounds(x, y, w, h);
    contentPanel.add(l);
    x = x + w + reg;

    w = 200;

    SfEntrustHandler entrustHandler = new SfEntrustHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfEntrust entrust = (SfEntrust) obj;
          entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
          currentBill = new SfJdResult();
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setName(entrust.getName() + "鉴定结果");
          currentBill.setJdr(entrust.getJdFzr());
          currentBill.setBrief(entrust.getBrief());
          currentBill.setEntrust(entrust);
          entrustField.getField().setText(entrust.getCode());
          break;
        }
      }

      public void afterClear() {
        currentBill = new SfJdResult();
        entrustField.getField().setText("");
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_JD_RESULT");
    entrustField = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler, entrustHandler.getColumNames(),
      LangTransMeta.translate(SfJdResult.COL_ENTRUST_CODE), "entrustCode");

    entrustField.setPreferredSize(new Dimension(w, h));

    entrustField.setBounds(x, y, w, h);
    contentPanel.add(entrustField);
    x = x0;
    y = y + h + 20;

    JSeparator sep1 = new JSeparator();
    sep1.setBounds(x, y, 270, 1);
    sep1.setForeground(Color.WHITE);
    contentPanel.add(sep1);
    x = x0;
    y = y + 25;

    w = 100;
    JButton nextBtn = new JButton();
    nextBtn.setText("下一步");
    nextBtn.setBounds(x, y, w, h);
    contentPanel.add(nextBtn);
    nextBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TCJLODO Auto-generated method stub
        doNext();
      }
    });
    x = x + w + 50;

    JButton cancelBtn = new JButton();
    cancelBtn.setText("取 消");
    cancelBtn.setBounds(x, y, w, h);
    contentPanel.add(cancelBtn);
    cancelBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TCJLODO Auto-generated method stub
        doCancel();
      }
    });

    this.getContentPane().setLayout(new BorderLayout());

    this.getContentPane().add(contentPanel, BorderLayout.CENTER);
  }

  protected void doCancel() {
    // TCJLODO Auto-generated method stub
    this.dispose();
  }

  protected void doNext() {
    // TCJLODO Auto-generated method stub

  }

}
