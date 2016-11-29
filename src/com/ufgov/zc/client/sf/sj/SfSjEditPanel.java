package com.ufgov.zc.client.sf.sj;

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
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.MyTableModel;
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
import com.ufgov.zc.client.component.sf.fieldeditor.SfEntrustorNewFieldEditor;
import com.ufgov.zc.client.component.sf.fieldeditor.SfSjGroupNewFieldEditor;
import com.ufgov.zc.client.component.sf.fieldeditor.SfSjProductorNewFieldEditor;
import com.ufgov.zc.client.component.sf.fieldeditor.SfSjUnitNewFieldEditor;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.MoneyFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorHandler;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfSj;
import com.ufgov.zc.common.sf.model.SfSjGroup;
import com.ufgov.zc.common.sf.model.SfSjProductor;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.sf.model.SfSjUnit;
import com.ufgov.zc.common.sf.publish.ISfJdDocTypeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.model.ZcEbProj;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfSjEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -3538825124938243132L;

  private static final Logger logger = Logger.getLogger(SfSjEditPanel.class);

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

  protected ListCursor<SfSj> listCursor;

  private SfSj oldJdDocType;

  public SfSjListPanel listPanel;

  protected SfSjEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  private ElementConditionDto eaccDto = new ElementConditionDto();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfJdDocTypeServiceDelegate sfJdDocTypeServiceDelegate;

  public SfSjEditPanel(SfSjDialog parent, ListCursor listCursor, String tabStatus, SfSjListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfSj.class, BillElementMeta.getBillElementMetaWithoutNd(listPanel.getcompoId()));

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

    requestMeta.setCompoId(getCompoId());

    refreshData();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfSj bill = (SfSj) listCursor.getCurrentObject();

    if (bill != null
      && (!"自动编号".equals(ZcUtil.safeString(bill.getSjId())) || !"".equals(ZcUtil.safeString(bill.getSjId())))) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

      bill = (SfSj) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfSjMapper.selectByPrimaryKey", bill.getSjId(), requestMeta);

      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      bill = new SfSj();

      setDefaultValue(bill);

      listCursor.getDataList().add(bill);

      listCursor.setCurrentObject(bill);

      this.setEditingObject(bill);

    }
    setOldObject();

    setButtonStatus();

    updateFieldEditorsEditable();

  }

  void setDefaultValue(SfSj bill) {
    // TCJLODO Auto-generated method stub \
	  bill.setStatus(SfSjSupplier.VS_SF_SUPPLIER_STATUS_ENABLE); 
	  bill.setHasTiaoma(SfElementConstants.VAL_Y);
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
    SfSj bill = (SfSj) listCursor.getCurrentObject();
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

    SfSj bill = (SfSj) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), bill.getProcessInstId());

  }

  protected void setOldObject() {

    oldJdDocType = (SfSj) ObjectUtil.deepCopy(listCursor.getCurrentObject());

  }

  public String getCompoId() {
    // TCJLODO Auto-generated method stub
    return listPanel.getcompoId();
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

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

	 /* if(sameName()){
		  JOptionPane.showMessageDialog(this, "名称重复，请确认名称！", "提示", JOptionPane.INFORMATION_MESSAGE);
		  return false;
	  }*/
      SfSj inData = (SfSj) this.listCursor.getCurrentObject();
      if(inData.getSjId()==null){
    	  String id=ZcUtil.getNextVal(SfSj.SEQ_SF_SF_SJ_ID);
    	  inData.setSjId(new BigDecimal(id));
    	  zcEbBaseServiceDelegate.insertFN("com.ufgov.zc.server.sf.dao.SfSjMapper.insert", inData, requestMeta);
      }else{
    	  List lst=new ArrayList();
    	  lst.add(inData);
    	  zcEbBaseServiceDelegate.updateObjectListFN("com.ufgov.zc.server.sf.dao.SfSjMapper.updateByPrimaryKey", lst, requestMeta);
      }
 

      listCursor.setCurrentObject(inData);

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

      refreshData();

      this.listPanel.refreshCurrentTabData();

    } else {

      JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

    return success;

  }
/*
   private boolean sameName() {
	      SfSj inData = (SfSj) this.listCursor.getCurrentObject();
	   ElementConditionDto dto=new ElementConditionDto();
	   dto.setDattr1(inData.getName()); 
	   dto.setSfId(inData.getSjId());
	   List  b=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfSjMapper.selectByName", dto, requestMeta);
	   if(b!=null && b.size()>0){
		   return true;
	   }
	return false;
}*/
 
/**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfSj bill = (SfSj) this.listCursor.getCurrentObject();
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

    SfSj bill = (SfSj) this.listCursor.getCurrentObject();
    ElementConditionDto dto = new ElementConditionDto();
    dto.setSfId(bill.getSjId());
    List usingLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfSjMapper.selectUsingById", dto, requestMeta);

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

        zcEbBaseServiceDelegate.deleteFN("com.ufgov.zc.server.sf.dao.SfSjMapper.deleteByPrimaryKey", bill.getSjId(), requestMeta); 

      } catch (Exception e) {

        logger.error(e.getMessage(), e);

        success = false;

        errorInfo += e.getMessage();

      }

      if (success) {

        this.listCursor.removeCurrentObject();

        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

        this.listPanel.refreshCurrentTabData();
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
 
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_NAME), "name");
    TextFieldEditor pym = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PYM), "pym");
    TextFieldEditor txm = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_TXM), "txm");
    TextFieldEditor packSpec = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PACK_SPEC), "packSpec");
//    AsValFieldEditor unit = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_UNIT), "unit", SfSj.VS_SF_SJ_UNIT);
//    AsValFieldEditor storeCondition = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_STORE_CONDITION), "storeCondition", SfSj.VS_SF_SJ_STORE_CONDITION);
    TextFieldEditor storeCondition = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_STORE_CONDITION), "storeCondition");
    TextFieldEditor pizhunDocCode = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PIZHUN_DOC_CODE), "pizhunDocCode");
    MoneyFieldEditor storeLimitMin = new MoneyFieldEditor(LangTransMeta.translate(SfSj.COL_STORE_LIMIT_MIN), "storeLimitMin");
    MoneyFieldEditor storeLimitMax = new MoneyFieldEditor(LangTransMeta.translate(SfSj.COL_STORE_LIMIT_MAX), "storeLimitMax");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_STATUS), "status", SfSjSupplier.VS_SF_SUPPLIER_STATUS);
    AsValFieldEditor hasTiaoma = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_HAS_TIAOMA), "hasTiaoma", SfElementConstants.VS_Y_N);
    TextAreaFieldEditor stopReason = new TextAreaFieldEditor(LangTransMeta.translate(SfSj.COL_STOP_REASON), "stopReason", -1, 1, 3);
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfSj.COL_REMARK), "remark", -1, 1, 3); 
    TextFieldEditor registCode = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_REGIST_CODE), "registCode");

    String expertSelectBillColumNames[] = { LangTransMeta.translate(SfSjSupplier.COL_NAME), LangTransMeta.translate(SfSjSupplier.COL_LINK_MAN), 
    		LangTransMeta.translate(SfSjSupplier.COL_TEL), LangTransMeta.translate(SfSjSupplier.COL_ADDRESS) };
    SupplierSelectBillHandler productorHandler = new SupplierSelectBillHandler(expertSelectBillColumNames);
    ElementConditionDto dto = new ElementConditionDto();
    dto.setStatus(SfSjSupplier.VS_SF_SUPPLIER_STATUS_ENABLE);
    dto.setDattr2(SfSjSupplier.VS_SF_SUPPLIER_TYPE_SCS);

    SfSjProductorNewFieldEditor productor = new SfSjProductorNewFieldEditor("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectMainDataLst",dto, 20, productorHandler, expertSelectBillColumNames,
      LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID), "productor.name");
//    dto.setCoCode(requestMeta.getSvCoCode());
//    ForeignEntityFieldEditor productor = new ForeignEntityFieldEditor("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectMainDataLst", dto, 20, productorHandler, expertSelectBillColumNames, 
//    		LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID), "productor.name");
    
//    AsValFieldEditor sjGroup = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_SJ_GROUP), "sjGroup", SfSj.SF_VS_SJ_GROUP);

    String groupCols[] = { LangTransMeta.translate(SfSjGroup.COL_GROUP_NAME) };
    GroupSelectHandler groupHandler = new GroupSelectHandler(groupCols);
    dto = new ElementConditionDto();
    dto.setCoCode(requestMeta.getSvCoCode());
    SfSjGroupNewFieldEditor group = new SfSjGroupNewFieldEditor("com.ufgov.zc.server.sf.dao.SfSjGroupMapper.selectMainDataLst", 20, groupHandler, groupCols,
      LangTransMeta.translate(SfSjGroup.COL_GROUP_NAME), "sjGroup.groupName");
    
    String unitCols[] = { LangTransMeta.translate(SfSjUnit.COL_UNIT_NAME) };
  UnitSelectHandler unitHandler = new UnitSelectHandler(unitCols);
  dto = new ElementConditionDto();
//  dto.setCoCode(requestMeta.getSvCoCode());
  SfSjUnitNewFieldEditor unit = new SfSjUnitNewFieldEditor("com.ufgov.zc.server.sf.dao.SfSjUnitMapper.selectMainDataLst", 20, unitHandler, unitCols,
    LangTransMeta.translate(SfSj.COL_UNIT), "unit.unitName");

    editorList.add(name);
    editorList.add(registCode);

    editorList.add(productor);
    editorList.add(group);
    
    editorList.add(hasTiaoma);
    editorList.add(txm);

    editorList.add(storeLimitMin);    
    editorList.add(storeLimitMax);
    
    editorList.add(packSpec);
    editorList.add(unit);
    
    editorList.add(storeCondition);
    editorList.add(status);
    
    
    editorList.add(stopReason);
    
    editorList.add(remark); 
    
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

  private class SupplierSelectBillHandler implements IForeignEntityHandler {

    private final String columNames[];

    public SupplierSelectBillHandler(String columNames[]) {
      this.columNames = columNames;
    }

    public void excute(List selectedDatas) {
      for (Object object : selectedDatas) {
        SfSjSupplier productor = (SfSjSupplier) object;
        setProductor(productor);
      }
    }

    public void afterClear() {
    	setProductor(null);
    }

    public TableModel createTableModel(List showDatas) {

      Object data[][] = new Object[showDatas.size()][columNames.length];

      for (int i = 0; i < showDatas.size(); i++) {

        SfSjSupplier rowData = (SfSjSupplier) showDatas.get(i);

        int col = 0;

        data[i][col++] = rowData.getName();
        data[i][col++] = rowData.getLinkMan();
        data[i][col++] = rowData.getTel();
        data[i][col++] = rowData.getAddress();

      }

      MyTableModel model = new MyTableModel(data, columNames) {

        @Override
        public boolean isCellEditable(int row, int colum) {

          return false;

        }

      };

      return model;

    }

    public boolean isMultipleSelect() {

      return false;

    }

  }

  private class GroupSelectHandler implements IForeignEntityHandler {

    private final String columNames[];

    public GroupSelectHandler(String columNames[]) {
      this.columNames = columNames;
    }

    public void excute(List selectedDatas) {
      for (Object object : selectedDatas) {
        SfSjGroup group = (SfSjGroup) object;
        setGroup(group);
        
      }
    }

    public void afterClear() {
      setGroup(null);
    }

    public TableModel createTableModel(List showDatas) {

      Object data[][] = new Object[showDatas.size()][columNames.length];

      for (int i = 0; i < showDatas.size(); i++) {

        SfSjGroup rowData = (SfSjGroup) showDatas.get(i);

        int col = 0;

        data[i][col++] = rowData.getGroupName(); 

      }

      MyTableModel model = new MyTableModel(data, columNames) {

        @Override
        public boolean isCellEditable(int row, int colum) {

          return false;

        }

      };

      return model;

    }

    public boolean isMultipleSelect() {

      return false;

    }

  }

  private class UnitSelectHandler implements IForeignEntityHandler {

    private final String columNames[];

    public UnitSelectHandler(String columNames[]) {
      this.columNames = columNames;
    }

    public void excute(List selectedDatas) {
      for (Object object : selectedDatas) {
        SfSjUnit unit = (SfSjUnit) object;
        setUnit(unit);
        
      }
    }

    public void afterClear() {
      setUnit(null);
    }

    public TableModel createTableModel(List showDatas) {

      Object data[][] = new Object[showDatas.size()][columNames.length];

      for (int i = 0; i < showDatas.size(); i++) {

        SfSjUnit rowData = (SfSjUnit) showDatas.get(i);

        int col = 0;

        data[i][col++] = rowData.getUnitName(); 

      }

      MyTableModel model = new MyTableModel(data, columNames) {

        @Override
        public boolean isCellEditable(int row, int colum) {

          return false;

        }

      };

      return model;

    }

    public boolean isMultipleSelect() {

      return false;

    }

  }
public void setProductor(SfSjSupplier productor) {
	if(productor==null){
		productor=new SfSjSupplier();
	}
	 SfSj inData = (SfSj) this.listCursor.getCurrentObject();
	 inData.setProductor(productor);
	 setEditingObject(inData);
}

public void setUnit(SfSjUnit unit) {

  SfSj inData = (SfSj) this.listCursor.getCurrentObject();
  inData.setUnit(unit==null?new SfSjUnit():unit);
  setEditingObject(inData);
}

public void setGroup(SfSjGroup group) {

  SfSj inData = (SfSj) this.listCursor.getCurrentObject();
  inData.setSjGroup(group==null?new SfSjGroup():group);
  setEditingObject(inData);
}
}