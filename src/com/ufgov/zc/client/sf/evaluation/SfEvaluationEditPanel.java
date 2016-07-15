package com.ufgov.zc.client.sf.evaluation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Desktop;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfEvaluationToTableModelConverter;
import com.ufgov.zc.client.component.AsValComboBox;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.GkCommentDialog;
import com.ufgov.zc.client.component.GkCommentUntreadDialog;
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
import com.ufgov.zc.client.component.button.SendButton;
import com.ufgov.zc.client.component.button.SendGkButton;
import com.ufgov.zc.client.component.button.SubaddButton;
import com.ufgov.zc.client.component.button.SubdelButton;
import com.ufgov.zc.client.component.button.SubinsertButton;
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldCellEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.sf.util.SfUserSelectHandler;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.util.freemark.IWordHandler;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEvaluation;
import com.ufgov.zc.common.sf.model.SfEvaluationPerson;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfEvaluationServiceDelegate;
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

public class SfEvaluationEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -3538752096000004615L;

  private static final Logger logger = Logger.getLogger(SfEvaluationEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_EVALUATION";

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

  // 工作流填写意见审核通过
  protected FuncButton suggestPassButton = new SuggestAuditPassButton();

  // 工作流销审
  protected FuncButton unAuditButton = new UnauditButton();

  // 工作流退回
  protected FuncButton unTreadButton = new UntreadButton();

  protected ListCursor<SfEvaluation> listCursor;

  private SfEvaluation oldEvaluation;

  public SfEvaluationListPanel listPanel;

  protected SfEvaluationEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);

  protected JTablePanel personsTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfEvaluationServiceDelegate sfEvaluationServiceDelegate;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  final ElementConditionDto jdFzrDto = new ElementConditionDto();

  public SfEvaluationEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfEvaluationListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfEvaluationEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfEvaluationServiceDelegate = (ISfEvaluationServiceDelegate) ServiceFactory.create(ISfEvaluationServiceDelegate.class,
      "sfEvaluationServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");

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

    SfEvaluation evaluation = (SfEvaluation) listCursor.getCurrentObject();

    if (evaluation != null) {//列表页面双击进入
      if (evaluation.getEvaluationId() != null) {
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
        evaluation = sfEvaluationServiceDelegate.selectByPrimaryKey(evaluation.getEvaluationId(), this.requestMeta);
        jdFzrDto.setDattr1(evaluation.getEntrust().getMajorCode());
        listCursor.setCurrentObject(evaluation);
        this.setEditingObject(evaluation);
      } else if (evaluation.getEntrustId() != null) {//图形界面进来的新增，已经确定了entrust
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
        jdFzrDto.setDattr1(evaluation.getEntrust().getMajorCode());
        setDefaultValue(evaluation);
        this.setEditingObject(evaluation);
      }
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      evaluation = new SfEvaluation();
      setDefaultValue(evaluation);
      listCursor.getDataList().add(evaluation);
      listCursor.setCurrentObject(evaluation);
      this.setEditingObject(evaluation);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
  }

  private void setDefaultValue(SfEvaluation Evaluation) {
    // TCJLODO Auto-generated method stub

    Evaluation.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    Evaluation.setNd(this.requestMeta.getSvNd());
    Evaluation.setInputDate(this.requestMeta.getSysDate());
    Evaluation.setInputor(requestMeta.getSvUserID());
    Evaluation.setEvaluateAddress(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS));
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    SfEvaluation Evaluation = (SfEvaluation) listCursor.getCurrentObject();
    personsTablePanel.setTableModel(SfEvaluationToTableModelConverter.convertPersonsTableData(Evaluation.getEvaluationPersons()));
    ZcUtil.translateColName(personsTablePanel.getTable(), SfEvaluationToTableModelConverter.getPersonInfo());
    setTablePorperty();
  }

  private void setTablePorperty() {
    final JPageableFixedTable table = personsTablePanel.getTable();
    table.setDefaultEditor(String.class, new TextCellEditor());
    SfUserSelectHandler handler = new SfUserSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        BeanTableModel model = (BeanTableModel) table.getModel();
        int k = table.getSelectedRow();
        if (k < 0) {
          return;
        }

        int k2 = table.convertRowIndexToModel(k);
        SfEvaluationPerson person = (SfEvaluationPerson) (model.getBean(k2));
        for (Object obj : selectedDatas) {
          User user = (User) obj;
          person.setName(user.getUserName());
          person.setCode(user.getUserId());
        }
        model.fireTableRowsUpdated(k, k);
      }
    };

    ElementConditionDto dto = new ElementConditionDto();
    dto.setCoCode(requestMeta.getSvCoCode());
    dto.setNd(requestMeta.getSvNd());
    ForeignEntityFieldCellEditor foreignExpertCodeEditor = new ForeignEntityFieldCellEditor(handler.getSqlId(), dto, 20, handler,
      handler.getColumNames(), "人员", "userName");

    SwingUtil.setTableCellEditor(table, SfEvaluationPerson.COL_NAME, foreignExpertCodeEditor);
  }

  protected void updateFieldEditorsEditable() {

    SfEvaluation qx = (SfEvaluation) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      if ("cancel".equals(this.oldEvaluation.getStatus())) {// 撤销单据设置字段为不可编辑
        wfCanEditFieldMap = null;
      }

      for (AbstractFieldEditor editor : fieldEditors) {
        // 工作流中定义可编辑的字段
        //          System.out.println(editor.getFieldName());
        if (editor instanceof NewLineFieldEditor)
          continue;
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
          if ("status".equals(editor.getFieldName()) || "nd".equals(editor.getFieldName())) {
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

    setWFSubTableEditable(personsTablePanel, isEdit);

  }

  protected void setButtonStatus() {
    SfEvaluation Evaluation = (SfEvaluation) listCursor.getCurrentObject();
    setButtonStatus(Evaluation, requestMeta, this.listCursor);

  }

  public void setButtonStatusWithoutWf() {

    super.setButtonStatusWithoutWf();
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

    SfEvaluation Evaluation = (SfEvaluation) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, Evaluation.getStatus(), this.pageStatus, getCompoId(), Evaluation.getProcessInstId());

  }

  protected void setOldObject() {

    oldEvaluation = (SfEvaluation) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    toolBar.add(sendButton);

    //    toolBar.add(saveAndSendButton);

    toolBar.add(suggestPassButton);

    //    toolBar.add(sendGkButton);

    toolBar.add(unAuditButton);

    toolBar.add(unTreadButton);

    toolBar.add(callbackButton);

    toolBar.add(deleteButton);

    //    toolBar.add(importButton);

    toolBar.add(printButton);

    toolBar.add(traceButton);

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

    printButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintButton();

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

  protected void doPrevious() {

    if (isDataChanged()) {

      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

      if (num == JOptionPane.YES_OPTION) {

        if (!doSave()) {

          return;

        }

      } else {

        listCursor.setCurrentObject(oldEvaluation);

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

        listCursor.setCurrentObject(oldEvaluation);

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

    SfEvaluation Evaluation = (SfEvaluation) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      Evaluation = sfEvaluationServiceDelegate.saveFN(Evaluation, this.requestMeta);

      listCursor.setCurrentObject(Evaluation);

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
    SfEvaluation evaluation = (SfEvaluation) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) {
      return;
    }
    if (parent instanceof SfEvaluationDialog) {//新增的委托书，创建数据流界面
      SfDataFlowDialog d = new SfDataFlowDialog(compoId, SfDataFlowUtil.getEntrust(evaluation.getEntrustId()), listPanel);
      parent.dispose();
    } else {
      SfDataFlowDialog d = (SfDataFlowDialog) parent;
      if (evaluation != null)
        d.refresh(evaluation.getEntrustId());
    }
  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {

    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfEvaluation Evaluation = (SfEvaluation) this.listCursor.getCurrentObject();
    if (SfElementConstants.VAL_N.equalsIgnoreCase(Evaluation.getIsAccept()) && Evaluation.getEntrust().getNotAcceptReason() == null) {
      Evaluation.getEntrust().setNotAcceptReason(Evaluation.getEvaluationOpinions());
    }
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(Evaluation, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString()).append("\n");
    }
    if (Evaluation.getEntrust().getIsAccept() == null)
      errorInfo.append("请指定").append(LangTransMeta.translate(SfEvaluation.COL_IS_ACCEPT)).append("\n");

    if (SfElementConstants.VAL_Y.equalsIgnoreCase(Evaluation.getIsAccept())) {
      if (Evaluation.getEntrust().getMajorCode() == null)
        errorInfo.append("请指定").append(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME)).append("\n");
      if (Evaluation.getEntrust().getJdFzr() == null)
        errorInfo.append("请指定").append(LangTransMeta.translate(SfEntrust.COL_JD_FZR)).append("\n");
      if (Evaluation.getEntrust().getJdFhr() == null && !SfElementConstants.VAL_Y.equalsIgnoreCase(Evaluation.getEntrust().getIsCxjd()))//重新鉴定不需要指定复核人
        errorInfo.append("请指定").append(LangTransMeta.translate(SfEntrust.COL_JD_FHR)).append("\n");
    }
    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  protected void doDelete() {
    requestMeta.setFuncId(deleteButton.getFuncId());
    SfEvaluation Evaluation = (SfEvaluation) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfEvaluationServiceDelegate.deleteByPrimaryKeyFN(Evaluation.getEvaluationId(), requestMeta);
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        success = false;
        errorInfo += e.getMessage();
      }

      if (success) {
        this.listCursor.removeCurrentObject();
        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        refreshListPanel();
        updateDataFlowDialog();
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
    return !DigestUtil.digest(oldEvaluation).equals(DigestUtil.digest(listCursor.getCurrentObject()));
  }

  private void doPrintButton() {

    Hashtable userData = new Hashtable();
    SfEvaluation evaluation = (SfEvaluation) this.listCursor.getCurrentObject();
    userData.put("evaluation", evaluation);
    userData.put(IWordHandler.FILE_NAME, evaluation.getEntrustCode());
    IWordHandler handler = new SfEvalutionWordPrintHandler();
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
          SfEvaluation currentBill = (SfEvaluation) listCursor.getCurrentObject();
          SfEntrust entrust = (SfEntrust) obj;
          entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setName(entrust.getName() + "委托评审");
          currentBill.setEntrust(entrust);
          currentBill.setEntrustId(entrust.getEntrustId());
          jdFzrDto.setDattr1(currentBill.getEntrust().getMajorCode());
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        SfEvaluation currentBill = (SfEvaluation) listCursor.getCurrentObject();
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        currentBill.setName(null);
        currentBill.setEntrust(new SfEntrust());
        setEditingObject(currentBill);
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_EVALUATION");
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfEvaluation.COL_ENTRUST_CODE), "entrustCode");

    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfEvaluation.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfEvaluation.COL_STATUS), "status", SfEvaluation.SF_VS_EVALUATION_STATUS);
    DateFieldEditor evaluatDate = new DateFieldEditor(LangTransMeta.translate(SfEvaluation.COL_EVALUATE_DATE), "evaluateDate");
    TextFieldEditor evaluateAddress = new TextFieldEditor(LangTransMeta.translate(SfEvaluation.COL_EVALUATE_ADDRESS), "evaluateAddress");
    AsValFieldEditor isAccept = new AsValFieldEditor(LangTransMeta.translate(SfEvaluation.COL_IS_ACCEPT), "entrust.isAccept",
      SfElementConstants.VS_Y_N, true);
    TextAreaFieldEditor evaluationOpions = new TextAreaFieldEditor(LangTransMeta.translate(SfEvaluation.COL_EVALUATION_OPINIONS),
      "evaluationOpinions", -1, 6, 5);
    TextAreaFieldEditor notAcceptReason = new TextAreaFieldEditor(LangTransMeta.translate(SfEvaluation.COL_NOT_ACCEPT_REASON), "notAcceptReason", -1,
      2, 5);
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfEvaluation.COL_INPUTOR), "inputor");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfEvaluation.COL_INPUT_DATE), "inputDate");

    AsValFieldEditor majorCode = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "entrust.majorCode", "SF_VS_MAJOR") {
      @Override
      protected void afterChange(AsValComboBox field) {
        if (field.getSelectedAsVal() == null) {
          jdFzrDto.setDattr1(null);
          return;
        }
        String valId = field.getSelectedAsVal().getValId();
        jdFzrDto.setDattr1(valId);
      }
    };

    SfJdPersonSelectHandler jdFzrHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfEvaluation cur = listCursor.getCurrentObject();
          SfJdPerson user = (SfJdPerson) selectedDatas.get(0);
          cur.getEntrust().setJdFzr(user.getAccount());
          cur.getEntrust().setJdFzrName(user.getName());
          setEditingObject(cur);
        }
      }

      public void afterClear() {
        SfEvaluation currentBill = (SfEvaluation) listCursor.getCurrentObject();
        currentBill.getEntrust().setJdFzr(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {
        SfEvaluation currentBill = (SfEvaluation) listCursor.getCurrentObject();
        if (currentBill.getEntrust().getMajorCode() == null || currentBill.getEntrust().getMajorCode().trim().length() == 0) {
          JOptionPane
            .showMessageDialog(self, "请先选择" + LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME) + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };

    jdFzrDto.setNd(requestMeta.getSvNd());
    jdFzrDto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor jdFzr = new ForeignEntityFieldEditor(jdFzrHandler.getSqlId(), jdFzrDto, 20, jdFzrHandler, jdFzrHandler.getColumNames(),
      LangTransMeta.translate(SfEntrust.COL_JD_FZR), "entrust.jdFzrName");

    SfJdPersonSelectHandler jdFhrHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfEvaluation cur = listCursor.getCurrentObject();
          SfJdPerson user = (SfJdPerson) selectedDatas.get(0);
          cur.getEntrust().setJdFhr(user.getAccount());
          cur.getEntrust().setJdFhrName(user.getName());
          setEditingObject(cur);
        }
      }

      public void afterClear() {
        SfEvaluation currentBill = (SfEvaluation) listCursor.getCurrentObject();
        currentBill.getEntrust().setJdFhr(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {
        SfEvaluation currentBill = (SfEvaluation) listCursor.getCurrentObject();
        if (currentBill.getEntrust().getMajorCode() == null || currentBill.getEntrust().getMajorCode().trim().length() == 0) {
          JOptionPane
            .showMessageDialog(self, "请先选择" + LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME) + "！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };
    ForeignEntityFieldEditor jdFhr = new ForeignEntityFieldEditor(jdFhrHandler.getSqlId(), jdFzrDto, 20, jdFhrHandler, jdFhrHandler.getColumNames(),
      LangTransMeta.translate(SfEntrust.COL_JD_FHR), "entrust.jdFhrName");

    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfEvaluation.COL_REMARK), "remark", -1, 3, 5);

    editorList.add(entrust);
    editorList.add(name);
    editorList.add(status);

    editorList.add(majorCode);
    editorList.add(jdFzr);
    editorList.add(jdFhr);

    editorList.add(evaluatDate);
    editorList.add(evaluateAddress);
    editorList.add(isAccept);

    editorList.add(evaluationOpions);
    editorList.add(remark);

    return editorList;

  }

  protected void init() {

    this.initToolBar(toolBar);

    this.setLayout(new BorderLayout());

    this.add(toolBar, BorderLayout.NORTH);

    if (this.billClass != null && this.eleMeta != null) {

      initFieldEditorPanel(this.billClass, this.eleMeta);

    } else {

      initFieldEditorPanel();

    }

    workPanel.setLayout(new BorderLayout());

    workPanel.add(fieldEditorPanel, BorderLayout.NORTH);

    JComponent tabTable = createSubBillPanel();

    if (tabTable != null) {

      workPanel.add(tabTable, BorderLayout.CENTER);

    }

    JScrollPane js = new JScrollPane(workPanel);
    this.add(js, BorderLayout.CENTER);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {

    JTabbedPane itemTabPane = new JTabbedPane();

    personsTablePanel.init();

    personsTablePanel.setPanelId(this.getClass().getName() + "_personTablePanel");

    personsTablePanel.getSearchBar().setVisible(false);

    personsTablePanel.setTablePreferencesKey(this.getClass().getName() + "__personTable");

    personsTablePanel.getTable().setShowCheckedColumn(true);

    personsTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    itemTabPane.addTab("评审人员", personsTablePanel);

    JFuncToolBar personBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    personBtnBar.add(addBtn2);

    personBtnBar.add(insertBtn2);

    personBtnBar.add(delBtn2);

    personsTablePanel.add(personBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfEvaluationPerson item = new SfEvaluationPerson();
        setPersonDefaultValue(item);
        int rowNum = addSub(personsTablePanel, item);
        personsTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfEvaluationPerson item = new SfEvaluationPerson();
        setPersonDefaultValue(item);
        int rowNum = insertSub(personsTablePanel, item);
        personsTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(personsTablePanel);
      }
    });
    itemTabPane.setMinimumSize(new Dimension(240, 300));
    return itemTabPane;
  }

  protected void setPersonDefaultValue(SfEvaluationPerson item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
    SfEvaluation e = listCursor.getCurrentObject();
    item.setEvaluationId(e.getEvaluationId());
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
    SfEvaluation bill = this.listCursor.getCurrentObject();

    SfEvaluation afterSaveBill = null;

    if (this.isDataChanged()) {
      JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    try {
      requestMeta.setFuncId(this.sendButton.getFuncId());
      SfEvaluation qx = (SfEvaluation) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfEvaluationServiceDelegate.newCommitFN(qx, requestMeta);
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
    SfEvaluation qx = (SfEvaluation) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
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
      qx = sfEvaluationServiceDelegate.auditFN(qx, requestMeta);
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
    SfEvaluation qx = (SfEvaluation) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    boolean success = true;
    SfEvaluation afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }
    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfEvaluationServiceDelegate.unAuditFN(qx, requestMeta);
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
    SfEvaluation afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      SfEvaluation qx = (SfEvaluation) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = sfEvaluationServiceDelegate.untreadFN(qx, requestMeta);
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
    SfEvaluation afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      SfEvaluation qx = (SfEvaluation) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfEvaluationServiceDelegate.callbackFN(qx, requestMeta);
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

}