package com.ufgov.zc.client.sf.agreement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
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
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.freemark.IWordHandler;
import com.ufgov.zc.client.util.freemark.WordHandlerFactory;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.client.zc.ztb.activex.WordPane;
import com.ufgov.zc.common.sf.model.SfAgreement;
import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.publish.ISfAgreementServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfChargeServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfAgreementEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 2820251304983233505L;

  private static final Logger logger = Logger.getLogger(SfAgreementEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_AGREEMENT";

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

  protected ListCursor<SfAgreement> listCursor;

  private SfAgreement oldAgreement;

  public SfAgreementListPanel listPanel;

  protected SfAgreementEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);

  protected JTablePanel personsTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfAgreementServiceDelegate sfAgreementServiceDelegate;

  private ISfChargeServiceDelegate sfChargeServiceDelegate;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  protected WordPane wordPane = new WordPane();

  private boolean openAndProtect = true;

  protected String fileName = "";

  protected JTabbedPane tabPane = new JTabbedPane();

  public SfAgreementEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfAgreementListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfAgreementEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");

    sfAgreementServiceDelegate = (ISfAgreementServiceDelegate) ServiceFactory.create(ISfAgreementServiceDelegate.class, "sfAgreementServiceDelegate");

    sfChargeServiceDelegate = (ISfChargeServiceDelegate) ServiceFactory.create(ISfChargeServiceDelegate.class, "sfChargeServiceDelegate");

    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");

    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 3;

    init();

    requestMeta.setCompoId(getCompoId());

    WordFileUtil.setDir("sf");

    addSubPane();

    refreshMainData();
  }

  protected void addSubPane() {
    //下面一句是为了打开word后刷新窗口
    parent.setSize(parent.getSize().width + 1, parent.getSize().height + 1);
    wordPane.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent evt) {
        //打开文件完成之后的回调函数
        boolean isSuccess = (Boolean) evt.getNewValue();
        if (isSuccess) {
          //下面一句是为了打开word后刷新窗口
          parent.setSize(parent.getSize().width - 1, parent.getSize().height - 1);
        }
      }
    });
    tabPane.addTab("协议内容", wordPane);
  }

  private void refreshMainData() {
    // TCJLODO Auto-generated method stub

    SfAgreement agreement = (SfAgreement) listCursor.getCurrentObject();

    if (agreement != null) {
      if (agreement.getAgreementId() != null) {//列表页面双击进入
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
        this.openAndProtect = true;
        agreement = sfAgreementServiceDelegate.selectByPrimaryKey(agreement.getAgreementId(), this.requestMeta);
        listCursor.setCurrentObject(agreement);
        this.setEditingObject(agreement);
      } else if (agreement.getEntrustId() != null) {//图形界面进来的新增，已经确定了entrust
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
        this.openAndProtect = false;
        setDefaultValue(agreement);
        this.setEditingObject(agreement);
      }
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      this.openAndProtect = false;
      agreement = new SfAgreement();
      setDefaultValue(agreement);
      listCursor.getDataList().add(agreement);
      listCursor.setCurrentObject(agreement);
      this.setEditingObject(agreement);
    }
    updateBtnFields();
  }

  private void refreshData() {

    refreshMainData();

    refreshSubTableData();

  }

  private void updateBtnFields() {
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
  }

  private void refreshSubTableData() {
    refreshFilePanel();
    updateBtnFields();
  }

  public synchronized void closeWordPanel(WordPane wp, boolean isSave) {
    if (wp != null && wp.isDocOpened()) {
      wp.close(isSave);
    }
  }

  /**
   * 
  * 刷新word文本
   */
  public void refreshFilePanel() {

    SfAgreement agreement = (SfAgreement) listCursor.getCurrentObject();
    String fileId = agreement.getFileId();

    closeWordPanel(wordPane, false);

    if (fileId != null && !fileId.equals("")) {

      this.fileName = WordFileUtil.loadMold(fileId);

    } else if (agreement.getEntrustId() != null) {
      SfEntrust entrust = getEntrust(agreement.getEntrustId());
      selectEntrust(entrust);
      return;
    } else {
      this.fileName = WordFileUtil.loadDefaultMold();
    }
    if (openAndProtect) {
      wordPane.openAndProtect(this.fileName, SfElementConstants.WORD_PASSWORD);
    } else {
      wordPane.open(this.fileName);
    }

  }

  private SfEntrust getEntrust(BigDecimal entrustId) {
    // TCJLODO Auto-generated method stub
    return sfEntrustServiceDelegate.selectByPrimaryKey(entrustId, requestMeta);
  }

  private void setDefaultValue(SfAgreement agreement) {
    // TCJLODO Auto-generated method stub

    agreement.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    agreement.setNd(this.requestMeta.getSvNd());
    agreement.setInputDate(SfUtil.getSysDate());
    agreement.setInputor(requestMeta.getSvUserID());
  }

  protected void updateFieldEditorsEditable() {

    for (AbstractFieldEditor editor : fieldEditors) {
      if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
        if ("inputDate".equals(editor.getFieldName()) || "inputorName".equals(editor.getFieldName())) {
          editor.setEnabled(false);
        } else {
          editor.setEnabled(true);
        }
      } else {
        editor.setEnabled(false);
      }
    }

    SfAgreement qx = (SfAgreement) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      if ("cancel".equals(this.oldAgreement.getStatus())) {// 撤销单据设置字段为不可编辑
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
          if ("inputDate".equals(editor.getFieldName()) || "inputorName".equals(editor.getFieldName()) || "status".equals(editor.getFieldName())) {
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
    //      setWFSubTableEditable(biTablePanel, isEdit);
  }

  private void protectWordPanel() {
    wordPane.protectDoc(SfElementConstants.WORD_PASSWORD);
  }

  protected void setButtonStatus() {
    SfAgreement agreement = (SfAgreement) listCursor.getCurrentObject();
    setButtonStatus(agreement, requestMeta, this.listCursor);
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

    SfAgreement agreement = (SfAgreement) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, agreement.getStatus(), this.pageStatus, getCompoId(), agreement.getProcessInstId());

  }

  protected void setOldObject() {

    oldAgreement = (SfAgreement) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    printButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doPrint();
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
        listCursor.setCurrentObject(oldAgreement);
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
        listCursor.setCurrentObject(oldAgreement);
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
    SfAgreement agreement = (SfAgreement) this.listCursor.getCurrentObject();
    try {
      //保存word文件
      // 支持直接修改word内容。
      wordPane.save(this.fileName);
      //每次都保存一文件，产生新的文件id
      String fileId = WordFileUtil.uploadWordFile(fileName, agreement.getFileId());
      agreement.setFileId(fileId);
      requestMeta.setFuncId(saveButton.getFuncId());
      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());
      agreement = sfAgreementServiceDelegate.saveFN(agreement, this.requestMeta);
      listCursor.setCurrentObject(agreement);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      success = false;
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshMainData();
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
    SfAgreement agreement = (SfAgreement) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) {
      return;
    }
    if (parent instanceof SfAgreementDialog) {//新增的委托书，创建数据流界面
      SfDataFlowDialog d = new SfDataFlowDialog(compoId, SfDataFlowUtil.getEntrust(agreement.getEntrustId()), listPanel);
      parent.dispose();
    } else {
      SfDataFlowDialog d = (SfDataFlowDialog) parent;
      d.refresh(agreement.getEntrustId());
    }
  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfAgreement agreement = (SfAgreement) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(agreement, mainNotNullList);
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
    SfAgreement agreement = (SfAgreement) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfAgreementServiceDelegate.deleteByPrimaryKeyFN(agreement.getAgreementId(), requestMeta);
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
    return !DigestUtil.digest(oldAgreement).equals(DigestUtil.digest(listCursor.getCurrentObject()));
  }

  private void doPrint() {
    SfAgreement agreement = (SfAgreement) this.listCursor.getCurrentObject();
    if (wordPane != null) {
      wordPane.print();
    }
  }

  private void doEdit() {

    this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
    this.openAndProtect = false;
    wordPane.unProtectDoc(SfElementConstants.WORD_PASSWORD);
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
          SfAgreement currentBill = (SfAgreement) listCursor.getCurrentObject();
          SfEntrust entrust = (SfEntrust) obj;
          entrust = getEntrust(entrust.getEntrustId());
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setName(entrust.getName() + "协议书");
          setEditingObject(currentBill);
          selectEntrust(entrust);
        }
      }

      public void afterClear() {
        SfAgreement currentBill = (SfAgreement) listCursor.getCurrentObject();
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        currentBill.setName(null);
        setEditingObject(currentBill);
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_AGREEMENT");
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfAgreement.COL_ENTRUST_CODE), "entrustCode");

    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfAgreement.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfAgreement.COL_STATUS), "status", SfAgreement.SF_VS_AGREEMENT_STATUS);
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfAgreement.COL_REMARK), "remark", 100, 2, 5);
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfAgreement.COL_INPUTOR), "inputorName");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfAgreement.COL_INPUT_DATE), "inputDate");

    editorList.add(entrust);
    editorList.add(name);
    editorList.add(status);

    editorList.add(remark);

    editorList.add(inputor);
    editorList.add(inputDate);

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

    JComponent subPanel = createSubBillPanel();
    if (subPanel == null) {
      subPanel = new JPanel();
    }
    JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    sp.setOneTouchExpandable(true);//让分割线显示出箭头
    sp.setContinuousLayout(true);//操作箭头，重绘图形
    sp.setDividerLocation(0.28);
    sp.setDividerSize(15);//設置分割線寬度的大小
    sp.add(fieldEditorPanel, JSplitPane.TOP);
    sp.add(subPanel, JSplitPane.BOTTOM);
    workPanel.add(sp, BorderLayout.CENTER);
    this.add(workPanel, BorderLayout.CENTER);
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {

    wordPane.setMinimumSize(new Dimension(10, 100));
    this.tabPane.addChangeListener(new ChangeListener() {

      public void stateChanged(ChangeEvent e) {
        JTabbedPane tab = (JTabbedPane) e.getSource();
        SfAgreement bulletin = (SfAgreement) self.listCursor.getCurrentObject();

        JPanel pan = (JPanel) tab.getSelectedComponent();

        /* if ("panel_filenamezb".equals(pan.getName())) {
           refreshZbFile(zbFileID);
         }*/
        /*
        if (isShowPanel && pan!=null) {
          if ("panel_filename1".equals(pan.getName()) && cnt1++ < 1) {
            refreshWordPaneFile1(bulletin);
          } else if ("panel_filename2".equals(pan.getName()) && cnt2++ < 1) {
            refreshWordPaneFile2(bulletin);
          }

        }
        */
      }
    });

    return this.tabPane;
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

  protected void selectEntrust(SfEntrust entrust) {

    if (entrust != null) {

      SfAgreement agreement = (SfAgreement) listCursor.getCurrentObject();
      agreement.setEntrustCode(entrust.getCode());
      agreement.setEntrustId(entrust.getEntrustId());
      agreement.setName(entrust.getCode() + "委托协议");
      setEditingObject(agreement);
      Hashtable userData = new Hashtable();
      userData.put("agreement", agreement);
      userData.put(IWordHandler.FILE_NAME, agreement.getEntrustCode());
      userData.put("entrust", entrust);
      userData.put("charge", getCharge(entrust));

      IWordHandler handler = WordHandlerFactory.getInstance().getHandler(agreement);
      if (handler == null) {
        JOptionPane.showMessageDialog(this.parent, "没有找到模版，请手工编制", "提示", JOptionPane.WARNING_MESSAGE);
        return;
      }
      fileName = handler.createDocumnet(userData);
      if (wordPane != null) {
        wordPane.close(false);
      }
      wordPane.open(this.fileName);
    }
  }

  private List<SfCharge> getCharge(SfEntrust entrust) {
    // TCJLODO Auto-generated method stub
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("" + entrust.getEntrustId());
    List<SfCharge> lst = sfChargeServiceDelegate.getMainDataLst(dto, requestMeta);
    //    return lst;
    if (lst == null || lst.size() == 0)
      return lst;
    List<SfCharge> rtn = new ArrayList<SfCharge>();
    for (SfCharge sfCharge : lst) {
      rtn.add(sfChargeServiceDelegate.selectByPrimaryKey(sfCharge.getChargeId(), requestMeta));
    }
    return rtn;
  }

  /**
   * 送审
   */
  protected void doSend() {

    boolean success = true;

    SfAgreement afterSaveBill = null;

    if (this.isDataChanged()) {
      JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    try {
      requestMeta.setFuncId(this.sendButton.getFuncId());
      SfAgreement qx = (SfAgreement) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfAgreementServiceDelegate.newCommitFN(qx, requestMeta);
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
      success = false;
      UIUtilities.showStaickTraceDialog(ex, this, "错误", ex.getMessage());
    }

    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "送审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshMainData();
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
    SfAgreement qx = (SfAgreement) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
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
      qx = sfAgreementServiceDelegate.auditFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      JOptionPane.showMessageDialog(this, "审核成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshMainData();
      updateDataFlowDialog();
    } else {
      JOptionPane.showMessageDialog(this, "审核失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * 销审
   */
  protected void doUnAudit() {
    SfAgreement qx = (SfAgreement) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    boolean success = true;
    SfAgreement afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }
    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfAgreementServiceDelegate.unAuditFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "销审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshMainData();
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
    SfAgreement afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      SfAgreement qx = (SfAgreement) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = sfAgreementServiceDelegate.untreadFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      JOptionPane.showMessageDialog(this, "退回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshMainData();
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
    SfAgreement afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      SfAgreement qx = (SfAgreement) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfAgreementServiceDelegate.callbackFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      logger.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }

    if (success) {
      JOptionPane.showMessageDialog(this, "收回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      refreshListPanel();
      refreshMainData();
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
