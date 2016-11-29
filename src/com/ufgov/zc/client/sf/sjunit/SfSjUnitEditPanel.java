/**
 * 
 */
package com.ufgov.zc.client.sf.sjunit;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.JFuncToolBar;
import com.ufgov.zc.client.component.JTablePanel;
import com.ufgov.zc.client.component.button.AddButton;
import com.ufgov.zc.client.component.button.CallbackButton;
import com.ufgov.zc.client.component.button.DeleteButton;
import com.ufgov.zc.client.component.button.EditButton;
import com.ufgov.zc.client.component.button.ExitButton;
import com.ufgov.zc.client.component.button.FuncButton;
import com.ufgov.zc.client.component.button.ImportButton;
import com.ufgov.zc.client.component.button.NextButton;
import com.ufgov.zc.client.component.button.PreviousButton;
import com.ufgov.zc.client.component.button.PrintButton;
import com.ufgov.zc.client.component.button.SaveButton;
import com.ufgov.zc.client.component.button.SaveSendButton;
import com.ufgov.zc.client.component.button.SendButton;
import com.ufgov.zc.client.component.button.SendGkButton;
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorDialog;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorEditPanel;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfSjUnit;
import com.ufgov.zc.common.sf.publish.ISfEntrustorServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfSjUnitEditPanel extends AbstractMainSubEditPanel {

  private static final Logger logger = Logger.getLogger(SfSjUnitEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_SJ_UNIT";

  protected FuncButton saveButton = new SaveButton();

  protected FuncButton addButton = new AddButton();

  protected FuncButton editButton = new EditButton();

  private FuncButton traceButton = new TraceButton();

  protected FuncButton callbackButton = new CallbackButton();

  protected FuncButton deleteButton = new DeleteButton();

  private FuncButton previousButton = new PreviousButton();

  private FuncButton nextButton = new NextButton();

  private FuncButton exitButton = new ExitButton();

  private FuncButton saveAndSendButton = new SaveSendButton();

  protected FuncButton sendButton = new SendButton();

  public FuncButton printButton = new PrintButton();

  public FuncButton importButton = new ImportButton();

  //送国库
  private FuncButton sendGkButton = new SendGkButton();

  // 工作流填写意见审核通过
  protected FuncButton suggestPassButton = new SuggestAuditPassButton();

  // 工作流销审
  protected FuncButton unAuditButton = new UnauditButton();

  // 工作流退回
  protected FuncButton unTreadButton = new UntreadButton();

  protected ListCursor<SfSjUnit> listCursor;

  private SfSjUnit oldMajor;

  public SfSjUnitListPanel listPanel;

  protected SfSjUnitEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  private ElementConditionDto eaccDto = new ElementConditionDto();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate ; 

  protected JTablePanel detailTablePanel = new JTablePanel();
  
  private ForeignEntityDialog forenEntityDialog;
  
  public SfSjUnitEditPanel(SfSjUnitDialog parent, ListCursor listCursor, String tabStatus, SfSjUnitListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfSjUnitEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate"); 
    
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP,

      new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 3;

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();
  }

  public SfSjUnitEditPanel(SfSjUnitDialog dialog, ListCursor listCursor, ForeignEntityDialog forenEntityDialog) {

    super(SfEntrustorEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));


    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate"); 
    
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = dialog;

    this.forenEntityDialog = forenEntityDialog;


    this.colCount = 3;

    init();

    requestMeta.setCompoId(compoId);

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();

  }
  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfSjUnit major = (SfSjUnit) listCursor.getCurrentObject();

    if (major != null && major.getUnitId()!=null) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE; 

      major = (SfSjUnit) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfSjUnitMapper.selectByPrimaryKey", major.getUnitId(), requestMeta);

      listCursor.setCurrentObject(major);
      this.setEditingObject(major);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      major = new SfSjUnit();
      major.setCoCode(requestMeta.getSvCoCode());

      listCursor.getDataList().add(major);

      listCursor.setCurrentObject(major);

      this.setEditingObject(major);

    }

    refreshSubData();
    
    setOldObject();

    setButtonStatus();

    updateFieldEditorsEditable();

  }

  private void refreshSubData() { 
  }
 

  protected void updateFieldEditorsEditable() {

      for (AbstractFieldEditor editor : fieldEditors) {
        if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
          editor.setEnabled(true);
        } else {
          editor.setEnabled(false);
        }
      }
    

  }

 

  protected void setButtonStatus() {
    SfSjUnit major = (SfSjUnit) listCursor.getCurrentObject();
    setButtonStatus(major, requestMeta, this.listCursor);
  }

  public void setButtonStatusWithoutWf() {

    if (this.btnStatusList.size() == 0) {

      ButtonStatus bs = new ButtonStatus();

      bs.setButton(this.addButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);
      
      bs = new ButtonStatus();

      bs.setButton(this.editButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.saveButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_EDIT);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_NEW);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.exitButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_ALL);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.sendButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_EDIT);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.suggestPassButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.callbackButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.unAuditButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.unTreadButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      bs = new ButtonStatus();

      bs.setButton(this.printButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.sendGkButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_AUDITED);

      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.importButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_EDIT);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_NEW);
      btnStatusList.add(bs);

    }

    SfSjUnit major = (SfSjUnit) this.listCursor.getCurrentObject();
     
    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), major.getProcessInstId());

  }

  protected void setOldObject() {

    oldMajor = (SfSjUnit) ObjectUtil.deepCopy(listCursor.getCurrentObject());

  }

 
 

  public String getCompoId() {
    // TCJLODO Auto-generated method stub
    return compoId;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#initToolBar(com.ufgov.zc.client.component.JFuncToolBar)
   */
  @Override
  public void initToolBar(JFuncToolBar toolBar) {
    // TCJLODO Auto-generated method stub

    toolBar.setModuleCode("SF");

    toolBar.setCompoId(getCompoId());

    toolBar.add(editButton);

    toolBar.add(saveButton);

//    toolBar.add(sendButton);

//    toolBar.add(saveAndSendButton);

//    toolBar.add(suggestPassButton);

//    toolBar.add(sendGkButton);

//    toolBar.add(unAuditButton);

//    toolBar.add(unTreadButton);

//    toolBar.add(callbackButton);

    toolBar.add(deleteButton);

//    toolBar.add(importButton);

//    toolBar.add(printButton);

//    toolBar.add(traceButton);

//    toolBar.add(previousButton);

//    toolBar.add(nextButton);

    toolBar.add(exitButton);


    editButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doEdit();

      }

    });



    previousButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrevious();

      }

    });

    nextButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doNext();

      }

    });

    exitButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doExit();

      }

    });

    saveButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        doSave();

      }

    });

    deleteButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        doDelete();

      }

    });

   



    unAuditButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        // 销审

        //        doUnAudit();

      }

    });

  
    printButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintButton();

      }

    });
  }

 

  protected void doPrevious() {

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      } else {

        listCursor.setCurrentObject(oldMajor);

      }

    }

    listCursor.previous();

    refreshData();

  }

  protected void doNext() {

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      } else {

        listCursor.setCurrentObject(oldMajor);

      }

    }

    listCursor.next();

    refreshData();

  }

  public boolean doSave() {

    if (!isDataChanged()) {

      JOptionPane.showMessageDialog(this, "数据没有发生改变，不用保存.", "提示", JOptionPane.INFORMATION_MESSAGE);

      return true;

    }

    if (!checkBeforeSave()) {

      return false;

    }

    boolean success = true;

    String errorInfo = "";
    SfSjUnit inData = (SfSjUnit) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());


//      System.out.println("before=" + inData.getCoCode() + inData.getCoName());
      if(inData.getUnitId()==null){
        inData.setUnitId(new BigDecimal(ZcUtil.getNextVal(SfSjUnit.SEQ_SF_SJ_UNIT_ID)));
        zcEbBaseServiceDelegate.insertFN("com.ufgov.zc.server.sf.dao.SfSjUnitMapper.insert", inData, requestMeta); 
      }else{
        List lst=new ArrayList();
        lst.add(inData);
        zcEbBaseServiceDelegate.updateObjectListFN("com.ufgov.zc.server.sf.dao.SfSjUnitMapper.updateByPrimaryKey", lst, requestMeta); 
      }

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);


      if (this.forenEntityDialog == null) {
        this.listPanel.refreshCurrentTabData();
        refreshData();
      } else {
        refreshParentForeignDialog(inData);
      }

    } else {

      JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

    return success;

  }

  void refreshParentForeignDialog(SfSjUnit entrustor) {

    this.forenEntityDialog.refresh(entrustor);
    this.parent.dispose();

  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfSjUnit major = (SfSjUnit) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(major, mainNotNullList);     
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString()).append("\n");
    }
    if(existsName()){
      errorInfo.append(major.getUnitName()).append("已经存在了。");
    }
    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

 

 

  protected void doDelete() {

    requestMeta.setFuncId(deleteButton.getFuncId());

    SfSjUnit major = (SfSjUnit) this.listCursor.getCurrentObject();

    ElementConditionDto dto=new ElementConditionDto();
    dto.setSfId(major.getUnitId());
    SfSjUnit  t=(SfSjUnit) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfSjUnitMapper.selectUsingById", dto, requestMeta);
    if(t!=null){
      JOptionPane.showMessageDialog(this, "已经被使用，不能删除 ！\n" , "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

    if (num == JOptionPane.YES_OPTION) {

      boolean success = true;

      String errorInfo = "";

      try {

        requestMeta.setFuncId(deleteButton.getFuncId());
        zcEbBaseServiceDelegate.deleteFN("com.ufgov.zc.server.sf.dao.SfSjUnitMapper.deleteByPrimaryKey", major.getUnitId(), requestMeta); 

      } catch (Exception e) {

        logger.error(e.getMessage(), e);

        success = false;

        errorInfo += e.getMessage();

      }

      if (success) {

        this.listCursor.removeCurrentObject();

        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

        if (this.forenEntityDialog == null) {
          this.listPanel.refreshCurrentTabData();
        } else {
          refreshParentForeignDialog(null);
        }

        parent.dispose();

      } else {

        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

      }

    }

  }

 
  public boolean isDataChanged() {

    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) {
      return false;
    }

    return !DigestUtil.digest(oldMajor).equals(DigestUtil.digest(listCursor.getCurrentObject()));

  }


  private void doPrintButton() {

  }

  private void doEdit() {

    this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;

    updateFieldEditorsEditable();

    setButtonStatus();

  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createFieldEditors()
   */
  @Override
  public List<AbstractFieldEditor> createFieldEditors() {

    List<AbstractFieldEditor> editorList = new ArrayList<AbstractFieldEditor>();
 
    TextFieldEditor majorName = new TextFieldEditor(LangTransMeta.translate(SfSjUnit.COL_UNIT_NAME), "unitName");
 
    editorList.add(majorName);
    
    return editorList;

  }


  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {

   return null;
  }


  public void doExit() {
    // TCJLODO Auto-generated method stub

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      }

    }

    this.parent.dispose();

  }

 
private boolean existsName(){
  SfSjUnit inData = (SfSjUnit) this.listCursor.getCurrentObject();
  if(inData.getUnitName()==null)return false;
  ElementConditionDto dto=new ElementConditionDto();
  dto.setDattr1(inData.getUnitName());
  dto.setCoCode(requestMeta.getSvCoCode());
  List lst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfSjUnitMapper.selectMainDataLst", dto, requestMeta);
  if(lst!=null && lst.size()>0){
    if(inData.getUnitId()!=null){
      SfSjUnit group=(SfSjUnit) lst.get(0);
      if(group.getUnitId().intValue()!=inData.getUnitId().intValue()){
        return true;
      }
    }else{
      return true;
    }
  }
  return false;
}
}
