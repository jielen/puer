/**
 * 
 */
package com.ufgov.zc.client.sf.entrust;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.sf.print.SfBarPrintUtil;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfPrintClient;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * 条码打印机选择对话框
 * @author Administrator
 */
public class SfPrintSelectDialog extends GkBaseDialog {

  JButton confirmBtn = null;

  JButton cancelBtn = null;
  
  ButtonGroup btnGroup = new ButtonGroup();

  SfEntrustEditPanel entrustPanel = null;

  IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = null;

  SfPrintClient curentPrint = null;

  RequestMeta meta = null;

  String mac = "", ipString = "";
   
 private boolean havingPrints=true;
 
 /**
  * 当前对话框是条码打印按钮打开的还是条码打印机设置按钮打开的
  */
 String tmBtn="";
 
 public SfPrintSelectDialog(Window parent, SfEntrustEditPanel entrustPl, RequestMeta meta,String fromPrintBtn) {
   this(parent, entrustPl, meta);
   tmBtn=fromPrintBtn;
 }

  public SfPrintSelectDialog(Window parent, SfEntrustEditPanel entrustPl, RequestMeta meta) {
    super(parent, "选择条码打印机", Dialog.ModalityType.APPLICATION_MODAL);
    entrustPanel = entrustPl;
    this.meta = meta;
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");

    init();
  }

  private void init() {

    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null); 
//    printServices = null;
    if (printServices == null || printServices.length == 0) {
      JOptionPane.showMessageDialog(this, "当前电脑没有安装打印机，请先安装条码打印机！", "提示", JOptionPane.INFORMATION_MESSAGE);
//      dispose();
      havingPrints=false;
      return;
    }

    curentPrint = getCurrentPrintService();
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(printServices.length, 1));
//    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

//    panel.setPreferredSize(new Dimension(200, 500));
    for (int i = 0; i < printServices.length; i++) {
      System.out.println(printServices[i].getName());
      JRadioButton button = new JRadioButton(printServices[i].getName());
      if (curentPrint != null && printServices[i].getName().equalsIgnoreCase(curentPrint.getPrintName())) {
        button.setSelected(true);
      }
      btnGroup.add(button);
      panel.add(button);
    }
    JScrollPane sPane = new JScrollPane();
//    sPane.add(panel);
    sPane.getViewport().add(panel);
    sPane.setPreferredSize(new Dimension(300, 500));

    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "选择条码打印机", TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));
    // this.getContentPane(). 
    p.add(sPane, BorderLayout.CENTER);
    p.add(createBtnPanel(), BorderLayout.SOUTH);

    p.setPreferredSize(new Dimension(300, printServices.length*30+50));
    add(p);

  }

  private Component createBtnPanel() {
    confirmBtn = new JButton("确定");
    confirmBtn.setPreferredSize(new Dimension(60, 26));
    cancelBtn = new JButton("取消");
    cancelBtn.setPreferredSize(new Dimension(60, 26));

    confirmBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        doConfirm();
      }
    });

    cancelBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        doCancel();
      }
    });

    JPanel p = new JPanel();
    p.setLayout(new FlowLayout());
    p.add(confirmBtn);
    p.add(cancelBtn);

    return p;
  }

  protected void doCancel() {
    dispose();
   }

  protected void doConfirm() {
    
    boolean selected=false;
    String name="";
    Enumeration<AbstractButton> radioBtns=btnGroup.getElements();  
    while (radioBtns.hasMoreElements()) {  
        AbstractButton btn = radioBtns.nextElement();  
        if(btn.isSelected()){  
          selected=true;
            name=btn.getText();  
            break;  
        }  
    }  
    
    
    if(selected){ 
      if(curentPrint==null){
        curentPrint=new SfPrintClient();
        curentPrint.setPrintId(new BigDecimal(zcEbBaseServiceDelegate.getNextVal(SfPrintClient.SEQ_SF_BAR_PRINT_ID, meta)));
        curentPrint.setPrintName(name);
        SfUtil su=new SfUtil();
        curentPrint.setMac(su.getMac());
        curentPrint.setIp(meta.getClientIP());
        zcEbBaseServiceDelegate.insertFN("com.ufgov.zc.server.sf.dao.SfPrintClientMapper.insert", curentPrint, meta);
      }else{
        if(!name.equalsIgnoreCase(curentPrint.getPrintName())){
          curentPrint.setPrintName(name);
          List l=new ArrayList();
          l.add(curentPrint);
          zcEbBaseServiceDelegate.updateObjectListFN("com.ufgov.zc.server.sf.dao.SfPrintClientMapper.updateByPrimaryKey", l, meta);
        }
      }
      entrustPanel.setBarPrint(curentPrint);
      dispose();
      if(SfPrintClient.TM_BTN_WT.equals(tmBtn)){
        entrustPanel.doPrintMastTm();
      }else if(SfPrintClient.TM_BTN_JC.equals(tmBtn)){
        entrustPanel.doPrintDetailTm();
      }
    }else{
      JOptionPane.showMessageDialog(this, "请选择条码打印机！", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
  }

  private SfPrintClient getCurrentPrintService() {
    if (curentPrint == null) {
      SfBarPrintUtil bpu=new SfBarPrintUtil();
      curentPrint=bpu.getCurrentPrintService();
    }
    return curentPrint;
  }

  public boolean isHavingPrints() {
    return havingPrints;
  }

  public void setHavingPrints(boolean havingPrints) {
    this.havingPrints = havingPrints;
  }

}
