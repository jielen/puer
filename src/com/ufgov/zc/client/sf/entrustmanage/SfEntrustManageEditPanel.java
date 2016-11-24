package com.ufgov.zc.client.sf.entrustmanage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ufgov.zc.client.component.button.SendButton;
import com.ufgov.zc.client.component.button.SendGkButton;
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.button.zc.CommonButton;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.entrust.SfEntrustEditPanel;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.commonbiz.model.WfAware;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.common.sf.publish.ISfEntrustManageServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfEntrustManageEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -396731724588595000L;

  private static final Logger logger = Logger.getLogger(SfEntrustManageEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_ENTRUST_MANAGE";

  protected FuncButton saveButton = new SaveButton();

  protected FuncButton addButton = new AddButton();

  protected FuncButton editButton = new EditButton();

  private FuncButton traceButton = new TraceButton();

  protected FuncButton callbackButton = new CallbackButton();

  protected FuncButton deleteButton = new DeleteButton();

  private FuncButton previousButton = new PreviousButton();

  private FuncButton nextButton = new NextButton();

  private FuncButton exitButton = new ExitButton();

  protected FuncButton sendButton = new SendButton();

  public FuncButton printButton = new PrintButton();

  public FuncButton importButton = new ImportButton();

  //送国库
  private FuncButton sendGkButton = new SendGkButton();

  public FuncButton acceptBtn = new CommonButton("faccepted", "audit.jpg");

  // 工作流填写意见审核通过
  protected FuncButton suggestPassButton = new SuggestAuditPassButton();

  // 工作流销审
  protected FuncButton unAuditButton = new UnauditButton();

  // 工作流退回
  protected FuncButton unTreadButton = new UntreadButton();

  protected ListCursor<SfEntrustManage> listCursor;

  private SfEntrustManage oldAppentMaterial;

  public SfEntrustManageListPanel listPanel;

  protected SfEntrustManageEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfEntrustManageServiceDelegate sfEntrustManageServiceDelegate;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  private ElementConditionDto reqDto = new ElementConditionDto();

  private SfEntrustEditPanel entrustPanel = null;

  public SfEntrustManageEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfEntrustManageListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfEntrustManageEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfEntrustManageServiceDelegate = (ISfEntrustManageServiceDelegate) ServiceFactory.create(ISfEntrustManageServiceDelegate.class, "sfEntrustManageServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId), TitledBorder.CENTER, TitledBorder.TOP,
      new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 2;

    WordFileUtil.setDir("sf");

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();
  }

  public SfEntrustManageEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfEntrustManageListPanel listPanel, SfEntrustEditPanel entrustPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfEntrustManageEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfEntrustManageServiceDelegate = (ISfEntrustManageServiceDelegate) ServiceFactory.create(ISfEntrustManageServiceDelegate.class, "sfEntrustManageServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId), TitledBorder.CENTER, TitledBorder.TOP,
      new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.entrustPanel = entrustPanel;

    this.parent = parent;

    this.colCount = 2;

    WordFileUtil.setDir("sf");

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfEntrustManage bill = (SfEntrustManage) listCursor.getCurrentObject();

    if (bill != null && bill.getManageId() != null) {//列表页面双击进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
      bill = sfEntrustManageServiceDelegate.selectByPrimaryKey(bill.getManageId(), this.requestMeta);
      setManageContent(bill);
      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    } else if (bill != null && bill.getEntrustId() != null && bill.getManageId() == null) {//其他界面进来的,已经有了操作信息,进入这里增加其他信息
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      this.setEditingObject(bill);
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      bill = new SfEntrustManage();
      setDefaultValue(bill);
      listCursor.getDataList().add(bill);
      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
    //更新信息要求的检索条件
    //    updateInfoReqCondition();
  }

  private void setManageContent(SfEntrustManage bill) {

    String str="";
    if(bill.getManageType()!=null){
      str=AsValDataCache.getName(SfEntrustManage.SF_VS_ENTRUST_MANAGE_TYPE, bill.getManageType());
      bill.setManageTypeTxt(str);
    }
  }

  private void setDefaultValue(SfEntrustManage bill) {
    // TCJLODO Auto-generated method stub 
    bill.setNd(this.requestMeta.getSvNd());
    bill.setInputDate(SfUtil.getSysDate());
    bill.setManageTime(SfUtil.getSysDate());
    bill.setInputor(requestMeta.getSvUserID());
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    refreshDetailTableData();
    //    refreshValidateTableData();
  }

  private void refreshDetailTableData() {}

  protected void updateFieldEditorsEditable() {

    SfEntrustManage qx = (SfEntrustManage) listCursor.getCurrentObject();
    if (entrustPanel != null) {

      for (AbstractFieldEditor editor : fieldEditors) {
        if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT)||pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
          if ("reason".equals(editor.getFieldName()) || "newEndDate".equals(editor.getFieldName())) {
            editor.setEnabled(true);
          } else {
            editor.setEnabled(false);
          }
        } else {
          editor.setEnabled(false);
        }
      }
    } else {

      for (AbstractFieldEditor editor : fieldEditors) {
        if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
          if ("inputDate".equals(editor.getFieldName()) || "inputorName".equals(editor.getFieldName()) || "status".equals(editor.getFieldName()) || "nd".equals(editor.getFieldName())) {
            editor.setEnabled(false);
          } else {
            editor.setEnabled(true);
          }
        } else if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT)) {
          if ("reason".equals(editor.getFieldName()) || "newEndDate".equals(editor.getFieldName())) {
            editor.setEnabled(true);
          } else {
            editor.setEnabled(false);
          }
        } else {
          editor.setEnabled(false);
        }
      }
    }
  }

  protected void setButtonStatus() {
    SfEntrustManage bill = (SfEntrustManage) listCursor.getCurrentObject();
    setButtonStatus(bill, requestMeta, this.listCursor);
  }

  public void setButtonStatusWithoutWf() {
    if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
      saveButton.setVisible(true);
      editButton.setVisible(false);
    } else {
      saveButton.setVisible(false);
      editButton.setVisible(true);
    }
    SfEntrustManage bill = (SfEntrustManage) listCursor.getCurrentObject();
    if (bill.getManageId() == null) {
      deleteButton.setVisible(false);
    } else {
      deleteButton.setVisible(true);
    }

  }

  protected void setOldObject() {

    oldAppentMaterial = (SfEntrustManage) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    //    toolBar.add(addButton);

    toolBar.add(editButton);

    toolBar.add(saveButton);

    toolBar.add(deleteButton);

    toolBar.add(exitButton);

    editButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doEdit();

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
  }

  public boolean doSave() {

   /* if (!isDataChanged()) {

      JOptionPane.showMessageDialog(this, "数据没有发生改变，不用保存.", "提示", JOptionPane.INFORMATION_MESSAGE);

      return true;

    }*/

    if (!checkBeforeSave()) {

    return false;

    }

    boolean success = true;

    String errorInfo = "";

    SfEntrustManage bill = (SfEntrustManage) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      bill = sfEntrustManageServiceDelegate.saveFN(bill, this.requestMeta);

      listCursor.setCurrentObject(bill);

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
      refreshListPanel();
      refreshEntrustpanel();
      refreshData();
      updateDataFlowDialog();

    } else {

      JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

    return success;

  }

  private void refreshEntrustpanel() {
    if (entrustPanel != null) {
      entrustPanel.refreshData();
    }
  }

  private void refreshListPanel() {
    if (listPanel != null) {
      listPanel.refreshCurrentTabData();
    }
  }

  /**
   * 更新数据流界面
   */
  private void updateDataFlowDialog() {
    // TCJLODO Auto-generated method stub
    SfEntrustManage bill = (SfEntrustManage) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) { return; }
  }

  /**
   * 保存前校验
   * @param cpApply
   * @return
   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfEntrustManage bill = (SfEntrustManage) this.listCursor.getCurrentObject();
    setManageContent(bill);
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(bill, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString());
    }

    if(SfEntrustManage.MANAGE_TYPE_DELAY.equals(bill.getManageType())){
      if(bill.getNewEndDate()==null){
        errorInfo.append("\n请指定调整后的日期.");
      }
      if(bill.getReason()==null || bill.getReason().trim().length()==0){
        errorInfo.append("\n请说明延期原因.");
      }
    }
    if(SfEntrustManage.MANAGE_TYPE_STOP.equals(bill.getManageType())){
      if(bill.getReason()==null || bill.getReason().trim().length()==0){
        errorInfo.append("\n请说明终止鉴定原因.");
      }
    }
    if(SfEntrustManage.MANAGE_TYPE_PAUSE.equals(bill.getManageType())){
      if(bill.getReason()==null || bill.getReason().trim().length()==0){
        errorInfo.append("\n请说明暂停鉴定原因.");
      }
    }
    if(SfEntrustManage.MANAGE_TYPE_ZHUANSONG.equals(bill.getManageType())){
      if(bill.getReason()==null || bill.getReason().trim().length()==0){
        errorInfo.append("\n请说明转送鉴定的原因.");
      }
    }
    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  protected void doDelete() {
    requestMeta.setFuncId(deleteButton.getFuncId());
    SfEntrustManage bill = (SfEntrustManage) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfEntrustManageServiceDelegate.deleteByPrimaryKeyFN(bill.getManageId(), requestMeta);
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        success = false;
        errorInfo += e.getMessage();
      }

      if (success) {
        this.listCursor.removeCurrentObject();
        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        refreshListPanel();
        doExit();
      } else {
        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public boolean isDataChanged() {
    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) { return false; }
    return !DigestUtil.digest(oldAppentMaterial).equals(DigestUtil.digest(listCursor.getCurrentObject()));
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

    SfEntrustHandler entrustHandler = new SfEntrustHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfEntrustManage currentBill = (SfEntrustManage) listCursor.getCurrentObject();
          SfEntrust entrust = (SfEntrust) obj;
          entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setCoCode(entrust.getCoCode());
          setEditingObject(currentBill);
          break;
        }
      }

      public void afterClear() {
        SfEntrustManage currentBill = (SfEntrustManage) listCursor.getCurrentObject();
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        setEditingObject(currentBill);
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_ENTRUST_MANAGE");
    if (SfUtil.isWtf()) {
      dto.setUserId(requestMeta.getSvUserID());
    }
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler, entrustHandler.getColumNames(),
      LangTransMeta.translate(SfEntrustManage.COL_ENTRUST_CODE), "entrustCode");
    AsValFieldEditor manageType = new AsValFieldEditor(LangTransMeta.translate(SfEntrustManage.COL_MANAGE_TYPE), "manageType", SfEntrustManage.SF_VS_ENTRUST_MANAGE_TYPE);
    TextFieldEditor manageTypeTxt = new TextFieldEditor(LangTransMeta.translate(SfEntrustManage.COL_MANAGE_TYPE_TXT), "manageTypeTxt");
    TextAreaFieldEditor managerContent = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrustManage.COL_MANAGE_CONTENT), "managerContent", 100, 2, 5);
    TextAreaFieldEditor reason = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrustManage.COL_REASON), "reason", 100, 4, 3);

    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfEntrustManage.COL_INPUTOR), "inputorName");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfEntrustManage.COL_INPUT_DATE), "inputDate");

    DateFieldEditor oldEndDate = new DateFieldEditor(LangTransMeta.translate(SfEntrustManage.COL_OLD_END_DATE), "oldEndDate");
    DateFieldEditor newEndDate = new DateFieldEditor(LangTransMeta.translate(SfEntrustManage.COL_NEW_END_DATE), "newEndDate");

    editorList.add(entrust);
    if (isNew()||containManageType()) {
      editorList.add(manageType);
    } else {
      editorList.add(manageTypeTxt);
    }
    editorList.add(reason);
    //    editorList.add(managerContent); 

    if (isDelay()) {
      editorList.add(oldEndDate);
      editorList.add(newEndDate);
    }

    editorList.add(inputor);
    editorList.add(inputDate);

    return editorList;
  }

  private boolean isNew() {

    SfEntrustManage bill = (SfEntrustManage) listCursor.getCurrentObject();
    if (bill == null) {
      return true;
    } else {
      if (bill.getManageType() == null && bill.getManageTypeTxt()==null) {
        return true;//新增按钮进来的新建,可以选择延期,显示相关字段
      } else {
        return false;
      }
    }
  }

  private boolean isDelay() {
    SfEntrustManage bill = (SfEntrustManage) listCursor.getCurrentObject();
    if (bill != null) {
      if (bill.getManageType() == null) {
        return true;//新增按钮进来的新建,可以选择延期,显示相关字段
      } else {
        if (SfEntrustManage.MANAGE_TYPE_DELAY.equalsIgnoreCase(bill.getManageType())) {
          return true;
        } else {
          return false;
        }
      }
    }
    return true;
  }
  private boolean containManageType(){

    SfEntrustManage bill = (SfEntrustManage) listCursor.getCurrentObject();
    if (bill != null) {
      if (bill.getManageType() != null) {
        return true;
      }
    }
    return false;
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
    if (this.parent instanceof SfDataFlowDialog) {
      ((SfDataFlowDialog) parent).removeTab(this, compoId);
    } else {
      this.parent.dispose();
    }
  }

  protected void setButtonStatus(WfAware baseBill, RequestMeta requestMeta, ListCursor listCursor) {

    Long processInstId = baseBill.getProcessInstId();

    if (processInstId == null || processInstId.longValue() < 0) {

      // 新增单据,草稿单据或不挂接工作流的单据

      Component[] funcs = toolBar.getComponents();

      String funcId;

      for (Component func : funcs) {

        funcId = ((FuncButton) func).getFuncId();

        if ("fauditfinal" == funcId || "fcallback" == funcId

        || "fautocommit" == funcId || "funaudit" == funcId

        || "funtread" == funcId

        || "fshowinstancetrace" == funcId

        || "f_uncollectcreate" == funcId

        || "fconfirmsup" == funcId || "fmanualcommit" == funcId

        || "fsendnextcommit" == funcId

        ) {
          func.setVisible(false);
        }

      }
      setButtonStatusWithoutWf();

    } else {

      // 流程已经启动

      List enableFuncs = this.getWFNodeEnableFunc(baseBill, requestMeta);

      if (enableFuncs == null || enableFuncs.size() == 0) {//委托里面，委托流转到科室时，不是指定科室的具体人，而是用了一个公用的用户(KESHI_SHOULI 科室受理人)，根据当前单据流程号、鉴定专业判断当当前单据是否应该接受这个单据

        enableFuncs = getKeshiShouliEnableFunc(baseBill, requestMeta);
      }

      ZcUtil.setWfNodeEnableFunc(toolBar, enableFuncs, processInstId, requestMeta);

    }

    if (listCursor == null || listCursor.getDataList() == null || listCursor.getDataList().size() <= 1) {

      // 如果listCursor中只有一条记录，就隐藏上一条下一条按钮

      FuncButton previousButton = toolBar.getButtonByDefaultText("上一条");

      FuncButton nextButton = toolBar.getButtonByDefaultText("下一条");

      if (previousButton != null) {

        previousButton.setVisible(false);

      }

      if (nextButton != null) {

        nextButton.setVisible(false);

      }

    }

  }

  private List getKeshiShouliEnableFunc(WfAware baseBill, RequestMeta meta) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setProcessInstId(baseBill.getProcessInstId());
    dto.setExecutor(meta.getSvUserID());
    dto.setCompoId(getCompoId());
    List funcs = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.getKeshiShouliEnableFunc", dto, meta);

    return funcs == null ? new ArrayList() : funcs;
  }

  private Map getKeshiShouliEnableField(WfAware baseBill, RequestMeta meta) {
    ElementConditionDto dto = new ElementConditionDto();
    dto.setProcessInstId(baseBill.getProcessInstId());
    dto.setExecutor(meta.getSvUserID());
    dto.setCompoId(getCompoId());
    List funcs = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfAppendMaterialMapper.getKeshiShouliEnableField", dto, meta);

    if (funcs == null) { return null; }
    Map rtn = new HashMap();
    for (int i = 0; i < funcs.size(); i++) {
      HashMap row = (HashMap) funcs.get(i);
      rtn.put(row.get("DATA_ITEM"), row.get("READ_WRITE"));
    }

    return rtn;
  }
}