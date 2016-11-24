package com.ufgov.zc.client.sf.supplier;

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
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.AutoNumFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorEditPanel;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.sf.publish.ISfEntrustorServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfJdDocTypeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfSjSupplierEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -3538825124938243132L;

  private static final Logger logger = Logger.getLogger(SfSjSupplierEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

//  private static String listPanel.getcompoId() = "SF_JD_DOC_TYPE";
   

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

  protected ListCursor<SfSjSupplier> listCursor;

  private SfSjSupplier oldJdDocType;

  public SfSjSupplierListPanel listPanel;

  protected SfSjSupplierEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  private ElementConditionDto eaccDto = new ElementConditionDto();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfJdDocTypeServiceDelegate sfJdDocTypeServiceDelegate;
  
//  private String billType=SfSjSupplier.VS_SF_SUPPLIER_TYPE_GYS;

  private ForeignEntityDialog forenEntityDialog;

  public SfSjSupplierEditPanel(SfSjSupplierDialog parent, ListCursor listCursor, String tabStatus, SfSjSupplierListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfSjSupplier.class, BillElementMeta.getBillElementMetaWithoutNd(listPanel.getcompoId()));

    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(listPanel.getcompoId());
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate"); 

    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(listPanel.getcompoId()),
      TitledBorder.CENTER, TitledBorder.TOP,

      new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 2;
     

    init();

    requestMeta.setCompoId(listPanel.getcompoId());

    refreshData();
  }

  public SfSjSupplierEditPanel(SfSjSupplierDialog parent, ListCursor listCursor, ForeignEntityDialog forenEntityDialog2,String compo) {
    // TODO Auto-generated constructor stub
    super(SfEntrustorEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compo));

    this.compoId=compo;

    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compo);
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate"); 

    this.listCursor = listCursor;

    this.parent = parent;

    this.forenEntityDialog = forenEntityDialog2;

    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compo),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.colCount = 2;

    init();

    requestMeta.setCompoId(compo);

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfSjSupplier bill = (SfSjSupplier) listCursor.getCurrentObject();

    if (bill != null
      && (!"自动编号".equals(ZcUtil.safeString(bill.getSupplierId())) || !"".equals(ZcUtil.safeString(bill.getSupplierId())))) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

      bill = (SfSjSupplier) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectByPrimaryKey", bill.getSupplierId(), requestMeta);

      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      bill = new SfSjSupplier();

      setDefaultValue(bill);

      listCursor.getDataList().add(bill);

      listCursor.setCurrentObject(bill);

      this.setEditingObject(bill);

    }
    setOldObject();

    setButtonStatus();

    updateFieldEditorsEditable();

  }

  void setDefaultValue(SfSjSupplier bill) {
    // TCJLODO Auto-generated method stub \
	  bill.setStatus(SfSjSupplier.VS_SF_SUPPLIER_STATUS_ENABLE);
	  bill.setSupplierType(getSupplierType());
	  bill.setCoCode(requestMeta.getSvCoCode());
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
    SfSjSupplier bill = (SfSjSupplier) listCursor.getCurrentObject();
    setButtonStatus(bill, requestMeta, this.listCursor);
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

    SfSjSupplier bill = (SfSjSupplier) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), bill.getProcessInstId());

  }

  protected void setOldObject() {

    oldJdDocType = (SfSjSupplier) ObjectUtil.deepCopy(listCursor.getCurrentObject());

  }

  public String getCompoId() {
    // TCJLODO Auto-generated method stub
    if(listPanel!=null)
    return listPanel.getcompoId();
    
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

        listCursor.setCurrentObject(oldJdDocType);

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

        listCursor.setCurrentObject(oldJdDocType);

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
    
    SfSjSupplier inData = (SfSjSupplier) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

	  if(sameName()){
		  JOptionPane.showMessageDialog(this, "名称重复，请确认名称！", "提示", JOptionPane.INFORMATION_MESSAGE);
		  return false;
	  }
      if(inData.getSupplierId()==null){
    	  String id=ZcUtil.getNextVal(SfSjSupplier.SEQ_SF_SJ_SUPPLIER);
    	  inData.setSupplierId(new BigDecimal(id));
    	  zcEbBaseServiceDelegate.insertFN("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.insert", inData, requestMeta);
      }else{
    	  List lst=new ArrayList();
    	  lst.add(inData);
    	  zcEbBaseServiceDelegate.updateObjectListFN("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.updateByPrimaryKey", lst, requestMeta);
      }
 

      listCursor.setCurrentObject(inData);

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

  void refreshParentForeignDialog(SfSjSupplier entrustor) {

    this.forenEntityDialog.refresh(entrustor);
    this.parent.dispose();

  }

   private boolean sameName() {
	      SfSjSupplier inData = (SfSjSupplier) this.listCursor.getCurrentObject();
	   ElementConditionDto dto=new ElementConditionDto();
	   dto.setDattr1(inData.getName());
	   dto.setDattr2(getSupplierType());
	   dto.setSfId(inData.getSupplierId());
	   dto.setCoCode(requestMeta.getSvCoCode());
	   List  b=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectByName", dto, requestMeta);
	   if(b!=null && b.size()>0){
		   return true;
	   }
	return false;
}

String getSupplierType() {
  
  if("SF_SJ_SUPPLIER".equals(getCompoId())){
    return SfSjSupplier.VS_SF_SUPPLIER_TYPE_GYS;
  }
  if("SF_SJ_PRODUCTOR".equals(getCompoId())){
    return SfSjSupplier.VS_SF_SUPPLIER_TYPE_SCS;
  }
  return "";
}

/**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfSjSupplier bill = (SfSjSupplier) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(bill, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString()).append("\n");
    }

    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  protected void doDelete() {

    requestMeta.setFuncId(deleteButton.getFuncId());

    SfSjSupplier bill = (SfSjSupplier) this.listCursor.getCurrentObject();
    ElementConditionDto dto = new ElementConditionDto();
    dto.setSfId(bill.getSupplierId());
    List usingLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectUsingById", dto, requestMeta);

    if (usingLst != null && usingLst.size() > 0) {
      JOptionPane.showMessageDialog(this, "已经被使用，不能删除 ！\n", "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

    if (num == JOptionPane.YES_OPTION) {

      boolean success = true;

      String errorInfo = "";

      try {

        requestMeta.setFuncId(deleteButton.getFuncId());

        zcEbBaseServiceDelegate.deleteFN("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.deleteByPrimaryKey", bill.getSupplierId(), requestMeta); 

      } catch (Exception e) {

        logger.error(e.getMessage(), e);

        success = false;

        errorInfo += e.getMessage();

      }

      if (success) {

        this.listCursor.removeCurrentObject();

        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

        this.refreshData();

        this.listPanel.refreshCurrentTabData();

      } else {

        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

      }

    }

  }

  public boolean isDataChanged() {

    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) {
      return false;
    }

    return !DigestUtil.digest(oldJdDocType).equals(DigestUtil.digest(listCursor.getCurrentObject()));

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
 
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_NAME), "name");
    TextFieldEditor pym = new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_PYM), "pym");
    TextFieldEditor zip = new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_ZIP), "zip");
    TextAreaFieldEditor address = new TextAreaFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_ADDRESS), "address", -1, 1, 3);
    TextAreaFieldEditor url = new TextAreaFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_URL), "url", -1, 1, 3);

    TextFieldEditor linkMan = new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_LINK_MAN), "linkMan");
    TextFieldEditor duty = new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_DUTY), "duty");
    TextFieldEditor  email= new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_EMAIL), "email");
    TextFieldEditor telWork = new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_TEL_WORK), "telWork");
    TextFieldEditor tel = new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_TEL), "tel");
    TextFieldEditor  fax= new TextFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_FAX), "fax");
    AsValFieldEditor supplierType = new AsValFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_SUPPLIER_TYPE), "supplierType", SfSjSupplier.VS_SF_SUPPLIER_TYPE);
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfSjSupplier.COL_STATUS), "status", SfSjSupplier.VS_SF_SUPPLIER_STATUS);

    editorList.add(name);
    editorList.add(status);
    
    editorList.add(address);
    
    editorList.add(url);
    
    editorList.add(linkMan);
    editorList.add(fax);
    
    editorList.add(tel);
    editorList.add(telWork); 
    
    editorList.add(email);
    editorList.add(zip);

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

}