package com.ufgov.zc.client.sf.jdresult;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.GkCommentDialog;
import com.ufgov.zc.client.component.GkCommentUntreadDialog;
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
import com.ufgov.zc.client.component.zc.fieldeditor.IntFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.freemark.IWordHandler;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfJdResultServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfJdResultEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 857394215620054321L;

  private static final Logger logger = Logger.getLogger(SfJdResultEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_JD_RESULT";

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

  public FuncButton printButton = new CommonButton("fprint", "鉴定记录", "print.gif", true);

  public FuncButton printZbButton = new CommonButton("fprintZb", "鉴定结果正本", "print.gif", true);

  public FuncButton printFbButton = new CommonButton("fprintFb", "鉴定结果副本", "print.gif", true);

  public FuncButton importButton = new ImportButton();

  //送国库
  private FuncButton sendGkButton = new SendGkButton();

  // 工作流填写意见审核通过
  protected FuncButton suggestPassButton = new SuggestAuditPassButton();

  // 工作流销审
  protected FuncButton unAuditButton = new UnauditButton();

  // 工作流退回
  protected FuncButton unTreadButton = new UntreadButton();

  protected ListCursor<SfJdResult> listCursor;

  private SfJdResult oldJdResult;

  public SfJdResultListPanel listPanel;

  protected SfJdResultEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfJdResultServiceDelegate sfJdResultServiceDelegate;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  private ElementConditionDto reqDto = new ElementConditionDto();

  private List<AbstractFieldEditor> headFieldList = new ArrayList<AbstractFieldEditor>();

  private List<AbstractFieldEditor> targetFieldList = new ArrayList<AbstractFieldEditor>();

  private List<AbstractFieldEditor> footFieldList = new ArrayList<AbstractFieldEditor>();

  TextAreaFieldEditor brief = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_BRIEF), "brief", -1, 5, 5);

  TextAreaFieldEditor jdProcess = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_PROCESS), "jdProcess", -1, 5, 5);

  TextAreaFieldEditor jdResult = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_RESULT), "jdResult", -1, 5, 5);

  TextAreaFieldEditor jdOpinion = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_OPINION), "jdOpinion", -1, 5, 5);

  TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_REMARK), "remark", -1, 5, 5);

  TextAreaFieldEditor zhuSu = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_ZHU_SU), "zhuSu", -1, 5, 5);

  private JTabbedPane centerPanel;
  

  private JPanel jdTargetDetailPanel;

  List<String> hideTabs = new ArrayList<String>();

  //  HashMap<String, JComponent> hideTabs = new HashMap<String, JComponent>();

  String titleJdResult = "分析说明";

  String titleZhusu = "主诉";

  String titleJdTarget = "被鉴定人";

  public SfJdResultEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfJdResultListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfJdResultEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfJdResultServiceDelegate = (ISfJdResultServiceDelegate) ServiceFactory.create(ISfJdResultServiceDelegate.class, "sfJdResultServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 3;

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();

    if (bill != null) {
      if (bill.getJdResultId() != null) {//列表页面双击进入
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
        bill = sfJdResultServiceDelegate.selectByPrimaryKey(bill.getJdResultId(), this.requestMeta);
        listCursor.setCurrentObject(bill);
        this.setEditingObject(bill);
      } else if (bill.getEntrustId() != null) {//图形界面进来的新增，已经确定了entrust
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
        setDefaultValue(bill);
        this.setEditingObject(bill);
      }
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      bill = new SfJdResult();
      setDefaultValue(bill);
      listCursor.getDataList().add(bill);
      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
    adjustTabs();
  }

  private void setDefaultValue(SfJdResult bill) {
    // TCJLODO Auto-generated method stub
    bill.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    bill.setNd(this.requestMeta.getSvNd());
    bill.setInputDate(SfUtil.getSysDate());
    bill.setInputor(requestMeta.getSvUserID());
    bill.setResultType(SfJdReport.RESULT_TYPE_YJS);
    bill.setJdAddress(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS));
  }

  private void refreshSubData() {
  }

  protected void updateFieldEditorsEditable() {

    SfJdResult qx = (SfJdResult) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      if ("cancel".equals(this.oldJdResult.getStatus())) {// 撤销单据设置字段为不可编辑
        wfCanEditFieldMap = null;
      }

      for (AbstractFieldEditor editor : fieldEditors) {
        // 工作流中定义可编辑的字段
        if (wfCanEditFieldMap != null && wfCanEditFieldMap.containsKey(Utils.getDBColNameByFieldName(editor.getEditObject(), editor.getFieldName()))) {
          isEdit = true;
          this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
          editor.setEnabled(true);
        } else {
          editor.setEnabled(false);
        }
      }
      //工作流中该节点选中了保存按钮可用，则当前状态当前人可用编辑
      if (saveButton.isVisible() && saveButton.isEnabled()) {
        isEdit = true;
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
      }

    } else {

      for (AbstractFieldEditor editor : fieldEditors) {
        if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
          if ("inputDate".equals(editor.getFieldName()) || "inputorName".equals(editor.getFieldName()) || "status".equals(editor.getFieldName())
            || "nd".equals(editor.getFieldName()) || "jdrName".equals(editor.getFieldName()) || "entrust.majorCode".equals(editor.getFieldName())) {
            editor.setEnabled(false);
          } else {
            editor.setEnabled(true);
          }
          isEdit = true;
        } else {
          editor.setEnabled(false);
        }
      }
    }

  }

  protected void setButtonStatus() {
    SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();
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

      bs.addBillStatus(ZcSettingConstants.WF_STATUS_DRAFT);

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

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.printButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.printZbButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

      bs = new ButtonStatus();

      bs.setButton(this.printFbButton);

      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);

      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);

      btnStatusList.add(bs);

    }

    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, bill.getStatus(), this.pageStatus, getCompoId(), bill.getProcessInstId());

  }

  protected void setOldObject() {

    oldJdResult = (SfJdResult) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    toolBar.add(sendButton);

    //        toolBar.add(saveAndSendButton);

    toolBar.add(suggestPassButton);

    //    toolBar.add(sendGkButton);

    toolBar.add(unAuditButton);

    toolBar.add(unTreadButton);

    toolBar.add(callbackButton);

    toolBar.add(deleteButton);

    //    toolBar.add(importButton);

    toolBar.add(traceButton);

    toolBar.add(printButton);
    printButton.setText("鉴定记录");
    printButton.setToolTipText("打印鉴定记录");

    toolBar.add(printZbButton);
    printZbButton.setText("鉴定结果正本");
    printZbButton.setToolTipText("打印鉴定结果正本");

    toolBar.add(printFbButton);
    printFbButton.setText("鉴定结果副本");
    printFbButton.setToolTipText("打印鉴定结果副本");

    //    toolBar.add(previousButton);

    //    toolBar.add(nextButton);

    toolBar.add(exitButton);

    addButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doAdd();

      }

    });

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

    printZbButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintReport(true);

      }

    });

    printFbButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintReport(false);

      }

    });

    printButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintJdRecord();

      }

    });

    sendButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doSend();
      }
    });

    suggestPassButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doSuggestPass();
      }
    });

    callbackButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doCallback();
      }
    });

    unTreadButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doUnTread();
      }
    });

    unAuditButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doUnAudit();
      }
    });

    traceButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doTrace();
      }
    });
  }

  protected void doPrintReport(boolean printZb) {
    // TCJLODO Auto-generated method stub

    Hashtable userData = new Hashtable();
    SfJdResult jdResult = (SfJdResult) this.listCursor.getCurrentObject();
    userData.put("jdresult", jdResult);

    String zfb = "正本";
    if (!printZb) {
      zfb = "副本";
    }

    IWordHandler handler;
    if (SfJdReport.RESULT_TYPE_JYBG.equalsIgnoreCase(jdResult.getResultType())) {
      userData.put(IWordHandler.FILE_NAME, jdResult.getEntrust().getCode() + "检验报告" + zfb);
      handler = new SfJdReport1WordHandler(printZb);
    } else {
      userData.put(IWordHandler.FILE_NAME, jdResult.getEntrust().getCode() + "检验意见书" + zfb);
      handler = new SfJdReport2WordHandler(printZb);
    }

    String fileName = handler.createDocumnet(userData);
    try {
      Desktop.getDesktop().open(new File(fileName));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "抱歉！没有找到合适的程序来打开文件！" + fileName, "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
  }

  protected void doAdd() {
    // TCJLODO Auto-generated method stub
    SfJdResult bill = new SfJdResult();
    setDefaultValue(bill);
    listCursor.getDataList().add(bill);
    listCursor.setCurrentObject(bill);
    refreshData();

  }

  protected void doPrevious() {

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      } else {

        listCursor.setCurrentObject(oldJdResult);

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

        listCursor.setCurrentObject(oldJdResult);

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

    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      bill = sfJdResultServiceDelegate.saveFN(bill, this.requestMeta);

      listCursor.setCurrentObject(bill);

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

      refreshListPanel();
      refreshData();
      updateDataFlowDialog();

    } else {

      JOptionPane.showMessageDialog(this, "保存失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

    }

    return success;

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
    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) {
      return;
    }
    if (parent instanceof SfJdResultDialog) {//新增的，创建数据流界面
      SfDataFlowDialog d = new SfDataFlowDialog(compoId, SfDataFlowUtil.getEntrust(bill.getEntrustId()), listPanel);
      parent.dispose();
    } else {
      SfDataFlowDialog d = (SfDataFlowDialog) parent;
      d.refresh(bill.getEntrustId());
    }
  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(bill, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString());
    }

    /* //去除空行
     List emptyLst=new ArrayList();
     for(int i=0;i<bill.getValidateDetailLst().size();i++){
       SfJdResultValidateDetail d=(SfJdResultValidateDetail) bill.getValidateDetailLst().get(i);      
       if(d.getInfoReq()==null && d.getInfoReq().getJdResultReqCode()==null){
         emptyLst.add(d);
       }
     }
     if(emptyLst.size()>0){
       bill.getValidateDetailLst().retainAll(emptyLst);
       refreshValidateTableData();
     }*/

    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  protected void doDelete() {
    requestMeta.setFuncId(deleteButton.getFuncId());
    SfJdResult bill = (SfJdResult) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfJdResultServiceDelegate.deleteByPrimaryKeyFN(bill.getJdResultId(), requestMeta);
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
    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) {
      return false;
    }
    return !DigestUtil.digest(oldJdResult).equals(DigestUtil.digest(listCursor.getCurrentObject()));
  }

  private void doPrintJdRecord() {

    Hashtable userData = new Hashtable();
    SfJdResult entrust = (SfJdResult) this.listCursor.getCurrentObject();
    userData.put("jdresult", entrust);
    userData.put(IWordHandler.FILE_NAME, entrust.getEntrustCode() + "鉴定记录");

    IWordHandler handler = new SfJdResultWordHandler();
    String fayiCode = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_MAJOR_FA_YI_CODE);
    if (fayiCode.equals(entrust.getEntrust().getMajorCode())) {
      handler = new SfJdResultFayiWordHandler();
    }

    String fileName = handler.createDocumnet(userData);
    try {
      Desktop.getDesktop().open(new File(fileName));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "抱歉！没有找到合适的程序来打开文件！" + fileName, "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
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
          SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
          SfEntrust entrust = (SfEntrust) obj;
          entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setName(entrust.getName() + "鉴定结果");
          currentBill.setJdr(entrust.getJdFzr());
          currentBill.setBrief(entrust.getBrief());
          currentBill.setEntrust(entrust);
          setEditingObject(currentBill);
          adjustTabs();
          break;
        }
      }

      public void afterClear() {
        SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        currentBill.setName(null);
        currentBill.setJdr(null);
        currentBill.setBrief(null);
        currentBill.setJdDate(null);
        currentBill.setJdDate(null);
        currentBill.setZcPersons(null);
        currentBill.setJdProcess(null);
        currentBill.setJdResult(null);
        currentBill.setJdOpinion(null);
        currentBill.setRemark(null);
        currentBill.setEntrust(new SfEntrust());
        setEditingObject(currentBill);
        adjustTabs();
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_JD_RESULT");
    dto.setUserId(requestMeta.getSvUserID());
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfJdResult.COL_ENTRUST_CODE), "entrustCode");
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfJdResult.COL_STATUS), "status", SfJdResult.SF_VS_JD_RESULT_STATUS);
    AsValFieldEditor resultType = new AsValFieldEditor(LangTransMeta.translate(SfJdResult.COL_RESULT_TYPE), "resultType",
    		SfJdReport.SF_VS_JD_RESULT_TYPE);
    DateFieldEditor jdDate = new DateFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_DATE), "jdDate");
    TextFieldEditor jdAddress = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_ADDRESS), "jdAddress");
    TextAreaFieldEditor zcPersons = new TextAreaFieldEditor(LangTransMeta.translate(SfJdResult.COL_ZC_PERSONS), "zcPersons", 100, 2, 5);
    AsValFieldEditor majorCode = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "entrust.majorCode", "SF_VS_MAJOR");

    SfJdPersonSelectHandler jdrHandler = new SfJdPersonSelectHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfJdResult currentBill = (SfJdResult) listCursor.getCurrentObject();
          User jdr = (User) obj;
          currentBill.setJdr(jdr.getUserId());
          break;
        }
      }
    };
    dto = new ElementConditionDto();
    ForeignEntityFieldEditor jdr = new ForeignEntityFieldEditor(jdrHandler.getSqlId(), dto, 20, jdrHandler, jdrHandler.getColumNames(),
      LangTransMeta.translate(SfJdResult.COL_JDR), "jdrName");
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_INPUTOR), "inputorName");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfJdResult.COL_INPUT_DATE), "inputDate");

    TextFieldEditor jdTargetName = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_TARGET), "jdTargetName");
    TextFieldEditor jdTargetName2 = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_TARGET), "jdTargetName");
    IntFieldEditor jdTargetAge = new IntFieldEditor(LangTransMeta.translate(SfJdTarget.COL_AGE), "jdTarget.age");
    AsValFieldEditor jdTargetSex = new AsValFieldEditor(LangTransMeta.translate(SfJdTarget.COL_SEX), "jdTarget.sex", SfElementConstants.VS_SEX);
    TextFieldEditor jdTargetIdName = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_NAME), "jdTarget.idName");
    TextFieldEditor jdTargetIdCode = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_CODE), "jdTarget.idCode");
    TextFieldEditor jdTargetPhone = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_PHONE), "jdTarget.phone");
    TextFieldEditor jdTargetAddress = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ADDRESS), "jdTarget.address");

    TextFieldEditor jdMethod = new TextFieldEditor(LangTransMeta.translate(SfJdResult.COL_JD_METHOD), "jdMethod");

    headFieldList.add(entrust);
    headFieldList.add(name);
    headFieldList.add(status);

    headFieldList.add(jdr);
    headFieldList.add(jdDate);
    headFieldList.add(jdAddress);

    headFieldList.add(jdTargetName);
    headFieldList.add(jdMethod);
    headFieldList.add(majorCode);

    headFieldList.add(zcPersons);

    headFieldList.add(resultType);
    headFieldList.add(inputor);
    headFieldList.add(inputDate);

    targetFieldList.add(jdTargetName2);
    targetFieldList.add(jdTargetAge);
    targetFieldList.add(jdTargetSex);
    targetFieldList.add(jdTargetIdName);
    targetFieldList.add(jdTargetIdCode);
    targetFieldList.add(jdTargetPhone);
    targetFieldList.add(jdTargetAddress);

    editorList.add(brief);
    editorList.add(jdProcess);
    editorList.add(jdResult);
    editorList.add(jdOpinion);
    editorList.add(remark);
    editorList.add(zhuSu);

    editorList.addAll(headFieldList);
    editorList.addAll(footFieldList);
    editorList.addAll(targetFieldList);

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
    if (this.parent instanceof SfDataFlowDialog) {
      ((SfDataFlowDialog) parent).removeTab(this, compoId);
    } else {
      this.parent.dispose();
    }
  }

  /**
   * 送审
   */
  protected void doSend() {

    boolean success = true;

    SfJdResult afterSaveBill = null;

    if (this.isDataChanged()) {
      JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    try {
      requestMeta.setFuncId(this.sendButton.getFuncId());
      SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfJdResultServiceDelegate.newCommitFN(qx, requestMeta);
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
      success = false;
      UIUtilities.showStaickTraceDialog(ex, this, "错误", ex.getMessage());
    }

    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "送审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    }
  }

  /**
   * 审核
   */
  protected void doSuggestPass() {
    if (!checkBeforeSave()) {
      return;
    }
    SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    requestMeta.setFuncId(this.suggestPassButton.getFuncId());
    GkCommentDialog commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),
      ModalityType.APPLICATION_MODAL);
    if (commentDialog.cancel) {
      return;
    }
    boolean success = true;
    String errorInfo = "";
    try {
      qx.setComment(commentDialog.getComment());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx = sfJdResultServiceDelegate.auditFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(this, "审核成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "审核失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 销审
   */
  protected void doUnAudit() {
    SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    boolean success = true;
    SfJdResult afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }
    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfJdResultServiceDelegate.unAuditFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "销审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "销审失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 退回
   */
  protected void doUnTread() {
    GkCommentUntreadDialog commentDialog = new GkCommentUntreadDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),
      ModalityType.APPLICATION_MODAL);
    if (commentDialog.cancel) {
      return;
    }
    boolean success = true;
    SfJdResult afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = sfJdResultServiceDelegate.untreadFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "退回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "退回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 收回
   */
  protected void doCallback() {
    boolean success = true;
    SfJdResult afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      SfJdResult qx = (SfJdResult) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfJdResultServiceDelegate.callbackFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }

    if (success) {
      JOptionPane.showMessageDialog(this, "收回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "收回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /*
   * 流程跟踪
   */
  private void doTrace() {
    ZcBaseBill bean = (ZcBaseBill) this.listCursor.getCurrentObject();
    if (bean == null) {
      return;
    }
    ZcUtil.showTraceDialog(bean, compoId);
  }

  protected void init() {

    this.initToolBar(toolBar);

    this.setLayout(new BorderLayout());

    this.add(toolBar, BorderLayout.NORTH);

    fieldEditors = createFieldEditors();

    JPanel headPanel = SfUtil.createFieldEditorPanel(this.billClass, this.eleMeta, headFieldList, 3);
    JPanel footPanel = SfUtil.createFieldEditorPanel(this.billClass, this.eleMeta, footFieldList, 3);
    initCenterPanel();

    workPanel.setLayout(new BorderLayout());

    workPanel.add(headPanel, BorderLayout.NORTH);

    workPanel.add(centerPanel, BorderLayout.CENTER);

    workPanel.add(footPanel, BorderLayout.SOUTH);

    JScrollPane js = new JScrollPane(workPanel);
    this.add(js, BorderLayout.CENTER);
  }

  private void initCenterPanel() {
    // TCJLODO Auto-generated method stub

    centerPanel = new JTabbedPane();

    if (jdTargetDetailPanel == null) {
      JPanel p = SfUtil.createFieldEditorPanel(this.billClass, mainBillElementMeta, targetFieldList, 3);
      jdTargetDetailPanel = new JPanel();
      jdTargetDetailPanel.setLayout(new BorderLayout());
      jdTargetDetailPanel.add(p, BorderLayout.NORTH);
    }
    centerPanel.add(titleJdTarget, jdTargetDetailPanel);

    centerPanel.add("检案摘要", brief);

    //    centerPanel.add(titleZhusu, zhuSu);

    centerPanel.add("检验过程", jdProcess);

    centerPanel.add(titleJdResult, jdResult);

    centerPanel.add("鉴定意见", jdOpinion);

    centerPanel.add("备注", remark);
  }

  /**
   * 根据不同类型的鉴定，隐藏部分页签
   */
  private void adjustTabs() {
    SfJdResult bill = (SfJdResult) listCursor.getCurrentObject();
    if (bill == null || bill.getEntrust() == null || bill.getEntrust().getMajorCode() == null) {

      if (hideTabs.size() > 0) {
        for (int i = 0; i < hideTabs.size(); i++) {
          String title = hideTabs.get(i);
          if (titleJdTarget.equals(title)) {
            centerPanel.add(titleJdTarget, jdTargetDetailPanel);
            continue;
          }
          /*if (titleZhusu.equals(title)) {
            centerPanel.add(titleZhusu, zhuSu);
            continue;
          }
          if (titleJdResult.equals(title)) {
            centerPanel.add(titleJdResult, jdResult);
            continue;
          }*/
        }
        hideTabs.clear();
      }
      return;
    }

    String fayiCode = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_MAJOR_FA_YI_CODE);
    if (bill.getEntrust().getMajorCode().equals(fayiCode)) {
      //法医隐藏综合评价，   显示鉴定对象、分析说明
      /*if (hideTabs.contains(titleZhusu)) {
        centerPanel.add(titleZhusu, zhuSu);
        hideTabs.remove(titleZhusu);
      }*/
      if (hideTabs.contains(titleJdTarget)) {
        centerPanel.add(titleJdTarget, jdTargetDetailPanel);
        hideTabs.remove(titleJdTarget);
      }
      /*if (!hideTabs.contains(titleJdResult)) {
        centerPanel.remove(jdResult);
        hideTabs.add(titleJdResult);
      }*/
    } else {
      /* if (!hideTabs.contains(titleZhusu)) {
         centerPanel.remove(zhuSu);
         hideTabs.add(titleZhusu);
       }*/
      if (!hideTabs.contains(titleJdTarget)) {
        centerPanel.remove(jdTargetDetailPanel);
        hideTabs.add(titleJdTarget);
      }
      /*if (hideTabs.contains(titleJdResult)) {
        centerPanel.add(titleJdResult, jdResult);
        hideTabs.remove(titleJdResult);
      }*/
    }
  }
}
