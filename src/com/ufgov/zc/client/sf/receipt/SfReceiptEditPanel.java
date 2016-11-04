package com.ufgov.zc.client.sf.receipt;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ufgov.zc.client.common.UIConstants;
import com.ufgov.zc.client.common.WorkEnv;
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
import com.ufgov.zc.client.sf.util.SfBookmarkUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.client.zc.ztb.activex.WordPane;
import com.ufgov.zc.common.sf.model.SfBookmark;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfReceiptServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfReceiptEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -6258499243419971245L;

  private static final Logger logger = Logger.getLogger(SfReceiptEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_RECEIPT";

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

  protected ListCursor<SfReceipt> listCursor;

  private SfReceipt oldReceipt;

  public SfReceiptListPanel listPanel;

  protected SfReceiptEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);

  protected JTablePanel personsTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfReceiptServiceDelegate sfReceiptServiceDelegate;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  protected WordPane wordPane;

  private boolean openAndProtect = true;

  protected String fileName = "";

  protected JTabbedPane tabPane = new JTabbedPane();

  private SfEntrust entrust;

  ElementConditionDto entrustDto = new ElementConditionDto();

private TextFieldEditor noticeName;

private AsValFieldEditor receiptType;

  public SfReceiptEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfReceiptListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfReceiptEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");

    sfReceiptServiceDelegate = (ISfReceiptServiceDelegate) ServiceFactory.create(ISfReceiptServiceDelegate.class, "sfReceiptServiceDelegate");

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
    wordPane = new WordPane();
    wordPane.setMinimumSize(new Dimension(10, 100));
    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH-10, UIConstants.DIALOG_0_LEVEL_HEIGHT-10);
    parent.validate();
    wordPane.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent evt) {
        //打开文件完成之后的回调函数
        boolean isSuccess = (Boolean) evt.getNewValue();
        if (isSuccess) {
          //下面一句是为了打开word后刷新窗口
    	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
    	    parent.validate();
    	    parent.moveToScreenCenter(); 
        }
      }
    });
    tabPane.addTab("内容", wordPane);
    tabPane.validate();
    tabPane.repaint();
  }

  private void refreshMainData() {
    // TCJLODO Auto-generated method stub

    SfReceipt bill = (SfReceipt) listCursor.getCurrentObject();

    if (bill != null) {
      if (bill.getReceiptId() != null) {//列表页面双击进入
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
        this.openAndProtect = true;
        bill = sfReceiptServiceDelegate.selectByPrimaryKey(bill.getReceiptId(), this.requestMeta);
        listCursor.setCurrentObject(bill);
        this.setEditingObject(bill);
      } else if (bill.getEntrustId() != null) {//图形界面进来的新增，已经确定了entrust
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
        this.openAndProtect = false;
        setDefaultValue(bill);
        listCursor.getDataList().add(bill);
        listCursor.setCurrentObject(bill);
        this.setEditingObject(bill);
      }
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      this.openAndProtect = false;
      bill = new SfReceipt();
      setDefaultValue(bill);
      listCursor.getDataList().add(bill);
      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    }
    updateBtnFields();
  }

  private void refreshData() {

    refreshMainData();

    refreshSubTableData();

    updateBtnFields();
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

    SfReceipt bill = (SfReceipt) listCursor.getCurrentObject();
    String fileId = bill.getFileId();

    closeWordPanel(wordPane, false);

    if (fileId != null && !fileId.equals("")) {

      this.fileName = WordFileUtil.loadMold(fileId);

    } else if (bill.getEntrustId() != null) {
      SfEntrust entrust = getEntrust(bill.getEntrustId());
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
    if (entrust != null)
      return entrust;
    entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrustId, requestMeta);
    return entrust;
  }

  private void setDefaultValue(SfReceipt bill) {
    // TCJLODO Auto-generated method stub
    bill.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    bill.setNd(this.requestMeta.getSvNd());
    bill.setInputDate(this.requestMeta.getSysDate());
    bill.setInputor(requestMeta.getSvUserID());
  }

  protected void updateFieldEditorsEditable() {

    SfReceipt qx = (SfReceipt) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      if ("cancel".equals(this.oldReceipt.getStatus())) {// 撤销单据设置字段为不可编辑
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
    this.openAndProtect = isEdit;
    //      setWFSubTableEditable(biTablePanel, isEdit);
  }

  private void protectWordPanel() {
    wordPane.protectDoc(SfElementConstants.WORD_PASSWORD);
  }

  protected void setButtonStatus() {
    SfReceipt bill = (SfReceipt) listCursor.getCurrentObject();
    setButtonStatus(bill, requestMeta, this.listCursor);
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

    }

    SfReceipt bill = (SfReceipt) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, bill.getStatus(), this.pageStatus, getCompoId(), bill.getProcessInstId());

  }

  protected void setOldObject() {

    oldReceipt = (SfReceipt) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    toolBar.add(addButton);

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

  protected void doAdd() {
    // TCJLODO Auto-generated method stub
    this.listCursor.setCurrentObject(null);
    refreshData();
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

  protected void doPrevious() {
    if (isDataChanged()) {
      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);
      if (num == JOptionPane.YES_OPTION) {
        if (!doSave()) {
          return;
        }
      } else {
        listCursor.setCurrentObject(oldReceipt);
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
        listCursor.setCurrentObject(oldReceipt);
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
    SfReceipt bill = (SfReceipt) this.listCursor.getCurrentObject();
    try {
      //保存word文件
      // 支持直接修改word内容。
      wordPane.save(this.fileName);
      //每次都保存一文件，产生新的文件id
      String fileId = WordFileUtil.uploadWordFile(fileName, bill.getFileId());
      bill.setFileId(fileId);
      requestMeta.setFuncId(saveButton.getFuncId());
      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());
      bill = sfReceiptServiceDelegate.saveFN(bill, this.requestMeta);
      listCursor.setCurrentObject(bill);
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
    SfReceipt bill = (SfReceipt) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) {
      return;
    }
    if (parent instanceof SfReceiptDialog) {//新增的委托书，创建数据流界面
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
    SfReceipt bill = (SfReceipt) this.listCursor.getCurrentObject();
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
    SfReceipt bill = (SfReceipt) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfReceiptServiceDelegate.deleteByPrimaryKeyFN(bill.getReceiptId(), requestMeta);
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
    return !DigestUtil.digest(oldReceipt).equals(DigestUtil.digest(listCursor.getCurrentObject()));
  }

  private void doPrint() {
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
          SfReceipt currentBill = (SfReceipt) listCursor.getCurrentObject();
          SfEntrust entrust = (SfEntrust) obj;
          entrust = getEntrust(entrust.getEntrustId());
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          if (currentBill.getReceiptType() != null && currentBill.getReceiptType().trim().length() > 0) {
            if (currentBill.getReceiptType().equals(SfReceipt.RECIEPT_TYPE_SHOU_LI)) {
              currentBill.setName(entrust.getName() + "受理回执");
            } else {
              currentBill.setName(entrust.getName() + "不予受理函");
            }
          }
//          noticeName.setValue(currentBill);
          setEditingObject(currentBill);
          selectEntrust(entrust);
        }
      }

      public void afterClear() {
        SfReceipt currentBill = (SfReceipt) listCursor.getCurrentObject();
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        currentBill.setName(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {
        SfReceipt bill = (SfReceipt) listCursor.getCurrentObject();
        if (bill.getReceiptType() == null || bill.getReceiptType().trim().length() == 0) {
          JOptionPane.showMessageDialog(self, "请先选择回执类别！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
        return true;
      }
    };
    entrustDto.setDattr1("SF_RECEIPT");
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), entrustDto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfReceipt.COL_ENTRUST_CODE), "entrustCode");

    noticeName = new TextFieldEditor(LangTransMeta.translate(SfReceipt.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfReceipt.COL_STATUS), "status", "SF_VS_RECEIPT_STATUS");
    receiptType = new AsValFieldEditor(LangTransMeta.translate(SfReceipt.COL_RECEIPT_TYPE), "receiptType", "SF_VS_RECEIPT_TYPE") {
      @Override
      protected void afterChange(AsValComboBox field) {
        /*if (field.getSelectedAsVal() == null || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_BROWSE)) {
          entrustDto.setDattr2(null);
          return;
        }
        String valId = field.getSelectedAsVal().getValId();
        entrustDto.setDattr2(valId);
        SfReceipt bill = (SfReceipt) listCursor.getCurrentObject();
        if (bill.getEntrustId() != null && valId != null) { 
          loadFile();
        }*/
      }
    };
    receiptType.getField().addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        selectEntrust(null);
      }

    });
    //    receiptType.addv
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfReceipt.COL_REMARK), "remark", 100, 2, 5);
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfReceipt.COL_INPUTOR), "inputorName");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfReceipt.COL_INPUT_DATE), "inputDate");

    editorList.add(receiptType);
    editorList.add(entrust);
    editorList.add(noticeName);

    editorList.add(remark);

    editorList.add(status);
    editorList.add(inputor);
    editorList.add(inputDate);

    return editorList;

  }

  protected void loadFile() {
//	  loadWord(fileId);
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

    this.tabPane.addChangeListener(new ChangeListener() {

      public void stateChanged(ChangeEvent e) {
        JTabbedPane tab = (JTabbedPane) e.getSource();
        SfReceipt bulletin = (SfReceipt) self.listCursor.getCurrentObject();

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

    SfReceipt bill = (SfReceipt) listCursor.getCurrentObject();
    if (entrust != null) {

      entrust=sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
      bill.setEntrustCode(entrust.getCode());
      bill.setEntrustId(entrust.getEntrustId());
      bill.setEntrust(entrust);
      if (bill.getReceiptType() != null && bill.getReceiptType().trim().length() > 0) {
    	  bill.setName(getName(entrust.getName(),bill.getReceiptType()));
//    	  noticeName.setValue(bill);
          setEditingObject(bill); 
    	  String fileId=getFileId(bill.getReceiptType());
    	  loadWord(fileId);
      } 
    }else{
      if(bill.getEntrust()!=null && receiptType.getField().getSelectedAsVal()!=null && bill.getFileId() ==null){          
        String fileId=getFileId(receiptType.getField().getSelectedAsVal().getValId());
        loadWord(fileId);
      }
    }
  } 
  
  private void loadWord(String fileId) {
	  
	  if(fileId==null)return;
	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH-10, UIConstants.DIALOG_0_LEVEL_HEIGHT-10);
	    parent.validate();
	    wordPane.closeNotSave();
	    wordPane.addPropertyChangeListener(WordPane.EVENT_NAME_OPEN_CALLBACK, new PropertyChangeListener() {
		      public void propertyChange(PropertyChangeEvent evt) {
		        //打开文件完成之后的回调函数
		        boolean isSuccess = (Boolean) evt.getNewValue();
		        if (isSuccess) {
		          //下面一句是为了打开word后刷新窗口
//		          parent.setSize(new Dimension(parent.getSize().width +200, parent.getSize().height +200));

		    	    parent.setSize(UIConstants.DIALOG_0_LEVEL_WIDTH, UIConstants.DIALOG_0_LEVEL_HEIGHT);
		    	    parent.validate();
		    	    parent.moveToScreenCenter(); 
		    	    replaceBookMarks();
		        }
		      }
		    });

	    SfReceiptEditPanel.this.fileName= WordFileUtil.loadMold(fileId);
		if(SfReceiptEditPanel.this.fileName==null)return ;
		wordPane.open(SfReceiptEditPanel.this.fileName);
}

	protected void replaceBookMarks() {

		SfReceipt currentBill = (SfReceipt) this.listCursor.getCurrentObject();
		SfBookmarkUtil bku = new SfBookmarkUtil();
		List<SfBookmark> bks = bku.getEntrustBookValueLst(currentBill.getEntrust());
		bks.addAll(bku.getEntrustorBookValueLst(currentBill.getEntrust().getEntrustor()));
		bks.addAll(bku.getJdTargetBookValueLst(currentBill.getEntrust().getJdTarget()));
		bks.addAll(bku.getJdjgBookValueLst(requestMeta.getSvCoCode())); 

		SfBookmark bk=new SfBookmark();
		bk.setName("INPUTOR");
		bk.setValue(requestMeta.getSvUserName());
		bks.add(bk);

	    SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
	    
		 bk=new SfBookmark();
			bk.setName("INPUT_DATE");
			bk.setValue(df.format(requestMeta.getSysDate()));
			bks.add(bk);
			 bk=new SfBookmark();
				bk.setName("INPUT_DATE2");
				bk.setValue(df.format(requestMeta.getSysDate()));
				bks.add(bk);
		
		wordPane.replaceBookMarks(bks);
	}


private String getName(String entrustName,String type) {
	  if(SfReceipt.RECIEPT_TYPE_APPEND.equals(type)){
		  return entrustName+"补充检材检样告知书";
	  }else if(SfReceipt.RECIEPT_TYPE_JU_JUE.equals(type)){
		  return entrustName+"不受理告知书";
	  }else if(SfReceipt.RECIEPT_TYPE_PAUSE.equals(type)){
		  return entrustName+"暂停鉴定告知书";
	  }else if(SfReceipt.RECIEPT_TYPE_STOP.equals(type)){
		  return entrustName+"终止鉴定告知书";
	  }
	  return null;
}

private void createWord(SfEntrust entrust) {

    SfReceipt bill = (SfReceipt) listCursor.getCurrentObject();
    
    if(entrust != null && bill.getReceiptType() != null ){
    	
    }
/*
    if (entrust != null && bill.getReceiptType() != null && bill.getReceiptType().trim().length() > 0) {
      Hashtable userData = new Hashtable();
      userData.put("receipt", bill);
      userData.put(IWordHandler.FILE_NAME, bill.getEntrustCode());
      userData.put("entrust", entrust);

      IWordHandler handler = null;
      if (bill.getReceiptType().equals(SfReceipt.RECIEPT_TYPE_SHOU_LI)) {
        handler = new SfReceiptWordHandler();
      } else {
        handler = new SfReceiptWordRejectHandler();
      }
      if (handler == null) {
        JOptionPane.showMessageDialog(this.parent, "没有找到模版，请手工编制", "提示", JOptionPane.WARNING_MESSAGE);
        return;
      }
      fileName = handler.createDocumnet(userData);
      if (wordPane != null) {
        wordPane.close(false);
      }
      wordPane.open(this.fileName);
    }*/
  }

  /**
   * 送审
   */
  protected void doSend() {

    boolean success = true;

    SfReceipt afterSaveBill = null;

    if (this.isDataChanged()) {
      JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    try {
      requestMeta.setFuncId(this.sendButton.getFuncId());
      SfReceipt qx = (SfReceipt) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfReceiptServiceDelegate.newCommitFN(qx, requestMeta);
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
    SfReceipt qx = (SfReceipt) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
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
      qx = sfReceiptServiceDelegate.auditFN(qx, requestMeta);
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
    SfReceipt qx = (SfReceipt) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    boolean success = true;
    SfReceipt afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }
    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfReceiptServiceDelegate.unAuditFN(qx, requestMeta);
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
    SfReceipt afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      SfReceipt qx = (SfReceipt) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = sfReceiptServiceDelegate.untreadFN(qx, requestMeta);
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
    SfReceipt afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      SfReceipt qx = (SfReceipt) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfReceiptServiceDelegate.callbackFN(qx, requestMeta);
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
  private String getFileId(String type){
	  if(SfReceipt.RECIEPT_TYPE_APPEND.equals(type)){
		  return "SF_NOTICE_APPEND";
	  }else if(SfReceipt.RECIEPT_TYPE_JU_JUE.equals(type)){
		  return "SF_NOTICE_NOT_ACCEIPT";
	  }else if(SfReceipt.RECIEPT_TYPE_PAUSE.equals(type)){
		  return "SF_NOTICE_PAUSE";
	  } else if(SfReceipt.RECIEPT_TYPE_STOP.equals(type)){
		  return "SF_NOTICE_STOP";
	  }
	  return null;
  }
}
