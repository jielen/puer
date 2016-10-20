package com.ufgov.zc.client.sf.appendmaterial;

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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
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
import com.ufgov.zc.client.common.converter.sf.SfEntrustToTableModelConverter;
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
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.AsValComboBoxCellEditor;
import com.ufgov.zc.client.component.table.codecelleditor.FileCellEditor;
import com.ufgov.zc.client.component.table.codecellrenderer.AsValCellRenderer;
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
import com.ufgov.zc.client.sf.outinfo.SfOutInfoWordPrintHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.util.freemark.IWordHandler;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.publish.ISfAppendMaterialServiceDelegate;
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

public class SfAppendMaterialEditPanel extends AbstractMainSubEditPanel {
 

  /**
   * 
   */
  private static final long serialVersionUID = -396731724588595000L;

  private static final Logger logger = Logger.getLogger(SfAppendMaterialEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_APPEND_MATERIAL";

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

  protected ListCursor<SfAppendMaterial> listCursor;

  private SfAppendMaterial oldAppentMaterial;

  public SfAppendMaterialListPanel listPanel;

  protected SfAppendMaterialEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  private BillElementMeta detailBillElementMeta;

  private BillElementMeta validateDetailBillElementMeta;

  protected JTablePanel detailTablePanel = new JTablePanel();

  protected JTablePanel validateDetailTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfAppendMaterialServiceDelegate sfAppendMaterialServiceDelegate;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  private ElementConditionDto reqDto = new ElementConditionDto();

  public SfAppendMaterialEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfAppendMaterialListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfAppendMaterialEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfAppendMaterialServiceDelegate = (ISfAppendMaterialServiceDelegate) ServiceFactory.create(ISfAppendMaterialServiceDelegate.class, "sfAppendMaterialServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    detailBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd("SF_OUT_INFO_DETAIL"); 
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 3;

    WordFileUtil.setDir("sf");

    init();

    requestMeta.setCompoId(getCompoId());

    refreshData();

    setButtonStatus();

    updateFieldEditorsEditable();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    SfAppendMaterial material = (SfAppendMaterial) listCursor.getCurrentObject();

    if (material != null && material.getAppendMaterialId() != null) {//列表页面双击进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
      material = sfAppendMaterialServiceDelegate.selectByPrimaryKey(material.getAppendMaterialId(), this.requestMeta);
      listCursor.setCurrentObject(material);
      this.setEditingObject(material);
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      material = new SfAppendMaterial();
      setDefaultValue(material);
      listCursor.getDataList().add(material);
      listCursor.setCurrentObject(material);
      this.setEditingObject(material);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
    //更新信息要求的检索条件
//    updateInfoReqCondition();
  }
 

  private void setDefaultValue(SfAppendMaterial material) {
    // TCJLODO Auto-generated method stub
    material.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    material.setNd(this.requestMeta.getSvNd());
    material.setInputDate(this.requestMeta.getSysDate());
    material.setInputor(requestMeta.getSvUserID()); 
    material.setValidatIsPass("Y"); 
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    refreshDetailTableData();
//    refreshValidateTableData();
  }

  private void refreshDetailTableData() {
    /*SfAppendMaterial material = (SfAppendMaterial) listCursor.getCurrentObject();
    detailTablePanel.setTableModel(SfOutInfoToTableModelConverter.convertDetailTableData(material.getDetailLst()));
    ZcUtil.translateColName(detailTablePanel.getTable(), SfOutInfoToTableModelConverter.getDetailInfo());
    setDetailTablePorperty();*/
    SfAppendMaterial material = (SfAppendMaterial) listCursor.getCurrentObject();
      detailTablePanel.setTableModel(SfEntrustToTableModelConverter.convertMaterialsTableData(material.getDetailLst()));
      ZcUtil.translateColName(detailTablePanel.getTable(), SfEntrustToTableModelConverter.getMaterialInfo());
      setMaterialsTablePorperty();

//      ZcUtil.translateColName(detailTablePanel.getTable(), SfOutInfoToTableModelConverter.getDetailInfo());
// setDetailTablePorperty();
  }
 

    private void setMaterialsTablePorperty() {
      JPageableFixedTable table = detailTablePanel.getTable();
      table.setDefaultEditor(String.class, new TextCellEditor());
      SwingUtil.setTableCellEditor(table, SfMaterials.COL_QUANTITY, new MoneyCellEditor(false));
      SwingUtil.setTableCellRenderer(table, SfMaterials.COL_QUANTITY, new NumberCellRenderer());
      SwingUtil.setTableCellEditor(table, SfMaterials.COL_MATERIAL_TYPE, new AsValComboBoxCellEditor(SfMaterials.SF_VS_MATERIAL_TYPE));
      SwingUtil.setTableCellRenderer(table, SfMaterials.COL_MATERIAL_TYPE, new AsValCellRenderer(SfMaterials.SF_VS_MATERIAL_TYPE));

      FileCellEditor fileEditor=new FileCellEditor("attachFileBlobid",true,(BeanTableModel) table.getModel());
      fileEditor.setDownloadFileEnable(true);
      SwingUtil.setTableCellEditor(table, SfMaterials.COL_ATTACH_FILE,fileEditor ); 
      SwingUtil.setTableCellEditor(table, SfMaterials.COL_JIAN_HOU_STORE_TIME, new MoneyCellEditor(false));
      SwingUtil.setTableCellRenderer(table, SfMaterials.COL_JIAN_HOU_STORE_TIME, new NumberCellRenderer());
      SwingUtil.setTableCellEditor(table, SfMaterials.COL_JIAN_HOU_CHULI_TYPE, new AsValComboBoxCellEditor(SfMaterials.SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE));
      SwingUtil.setTableCellRenderer(table, SfMaterials.COL_JIAN_HOU_CHULI_TYPE, new AsValCellRenderer(SfMaterials.SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE));
    }
  

  private void setDetailTablePorperty() {
    final JPageableFixedTable table = detailTablePanel.getTable();
    table.setDefaultEditor(String.class, new TextCellEditor());   
 
  }

  protected void updateFieldEditorsEditable() {

    SfAppendMaterial qx = (SfAppendMaterial) listCursor.getCurrentObject();
    Long processInstId = qx.getProcessInstId();
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(qx, requestMeta);
      if ("cancel".equals(this.oldAppentMaterial.getStatus())) {// 撤销单据设置字段为不可编辑
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
            || "nd".equals(editor.getFieldName()) || "acceptor".equals(editor.getFieldName()) || "acceptorName".equals(editor.getFieldName())
            || "acceptDate".equals(editor.getFieldName())) {
            editor.setEnabled(false);
          } else {
            editor.setEnabled(true);
          }
          isEdit = true;
        } else {
          isEdit = false;
          editor.setEnabled(false);
        }
      }
    }

    setWFSubTableEditable(detailTablePanel, isEdit);

//    setWFSubTableEditable(validateDetailTablePanel, isEdit);

  }

  protected void setButtonStatus() {
    SfAppendMaterial material = (SfAppendMaterial) listCursor.getCurrentObject();
    setButtonStatus(material, requestMeta, this.listCursor);

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

    SfAppendMaterial material = (SfAppendMaterial) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, material.getStatus(), this.pageStatus, getCompoId(), material.getProcessInstId());

  }

  protected void setOldObject() {

    oldAppentMaterial = (SfAppendMaterial) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

    //        toolBar.add(saveAndSendButton);

    toolBar.add(suggestPassButton);

    //    toolBar.add(sendGkButton);

    toolBar.add(unAuditButton);

    toolBar.add(unTreadButton);

    toolBar.add(callbackButton);

    toolBar.add(deleteButton);

    //    toolBar.add(importButton);

    toolBar.add(traceButton);

//    toolBar.add(printButton);

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

    printButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrint();

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

  protected void doAdd() {
    // TCJLODO Auto-generated method stub
    listCursor.setCurrentObject(null);
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

        listCursor.setCurrentObject(oldAppentMaterial);

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

        listCursor.setCurrentObject(oldAppentMaterial);

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

    SfAppendMaterial material = (SfAppendMaterial) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      material = sfAppendMaterialServiceDelegate.saveFN(material, this.requestMeta);

      listCursor.setCurrentObject(material);

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
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
    SfAppendMaterial material = (SfAppendMaterial) this.listCursor.getCurrentObject();
    if (listPanel != null && listPanel.getParent() instanceof JClosableTabbedPane) {
      return;
    }
    if (parent instanceof SfAppendMaterialDialog) {//新增的，创建数据流界面
      SfDataFlowDialog d = new SfDataFlowDialog(compoId, SfDataFlowUtil.getEntrust(material.getEntrustId()), listPanel);
      parent.dispose();
    } else {
      SfDataFlowDialog d = (SfDataFlowDialog) parent;
      d.refresh(material.getEntrustId());
    }
  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfAppendMaterial material = (SfAppendMaterial) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(material, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString());
    }

   /* List detailNotNullList = detailBillElementMeta.getNotNullBillElement();
    StringBuffer detailError = new StringBuffer();
    for (int i = 0; i < material.getDetailLst().size(); i++) {
      SfOutInfoDetail detail = (SfOutInfoDetail) material.getDetailLst().get(i);
      String detailInfo = ZcUtil.validateBillElementNull(detail, detailNotNullList);
      if (detailInfo.length() != 0) {
        detailError.append("\n").append(detailInfo.toString());
      }
    }

    if (detailError.length() != 0) {
      errorInfo.append("\n").append("信息明细:").append(detailError.toString());
    }

    List validateNotNullLst = validateDetailBillElementMeta.getNotNullBillElement();
    StringBuffer validateError = new StringBuffer();
    for (int i = 0; i < material.getValidateDetailLst().size(); i++) {
      SfOutInfoValidateDetail validateDetail = (SfOutInfoValidateDetail) material.getValidateDetailLst().get(i);
      String validateInfo = ZcUtil.validateBillElementNull(validateDetail, validateNotNullLst);
      if (validateInfo.length() != 0) {
        validateError.append("\n").append(validateInfo.toString());
      }
    }
    if (validateError.length() != 0) {
      errorInfo.append("\n").append("验证情况:").append(validateError.toString());
    }*/
    /* //去除空行
     List emptyLst=new ArrayList();
     for(int i=0;i<material.getValidateDetailLst().size();i++){
       SfOutInfoValidateDetail d=(SfOutInfoValidateDetail) material.getValidateDetailLst().get(i);      
       if(d.getInfoReq()==null && d.getInfoReq().getOutInfoReqCode()==null){
         emptyLst.add(d);
       }
     }
     if(emptyLst.size()>0){
       material.getValidateDetailLst().retainAll(emptyLst);
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
    SfAppendMaterial material = (SfAppendMaterial) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfAppendMaterialServiceDelegate.deleteByPrimaryKeyFN(material.getAppendMaterialId(), requestMeta);
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
    return !DigestUtil.digest(oldAppentMaterial).equals(DigestUtil.digest(listCursor.getCurrentObject()));
  }

  private void doPrint() {

    Hashtable userData = new Hashtable();
    SfAppendMaterial material = (SfAppendMaterial) this.listCursor.getCurrentObject();
    SfEntrust entrust = sfEntrustServiceDelegate.selectByPrimaryKey(material.getEntrustId(), requestMeta);
    userData.put("entrust", entrust);
    userData.put("material", material);
    userData.put(IWordHandler.FILE_NAME, entrust.getCode() + "外部信息");
    IWordHandler handler = new SfOutInfoWordPrintHandler();
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
          SfAppendMaterial currentBill = (SfAppendMaterial) listCursor.getCurrentObject();
          SfEntrust entrust = (SfEntrust) obj;
          entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setName(entrust.getName() + "补充检材");
          currentBill.setCoCode(entrust.getCoCode());
          //          currentBill.setTgf(entrust.getEntrustor() == null ? null : entrust.getEntrustor().getName()); 
         /* if (entrust.getMaterials() != null) {
            for (int i = 0; i < entrust.getMaterials().size(); i++) {
              SfOutInfoDetail d = new SfOutInfoDetail();
              SfMaterials m = (SfMaterials) entrust.getMaterials().get(i);
              d.setName(m.getName());
              currentBill.getDetailLst().add(d);
            }
          }*/
          setEditingObject(currentBill);
//          refreshDetailTableData();
          break;
        }
      }

      public void afterClear() {
        SfAppendMaterial currentBill = (SfAppendMaterial) listCursor.getCurrentObject();
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        currentBill.setName(null);
        setEditingObject(currentBill);
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_APPEND_MATERIAL");
    if(SfUtil.isWtf()){
      dto.setUserId(requestMeta.getSvUserID());
    }
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfAppendMaterial.COL_ENTRUST_CODE), "entrustCode");
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_STATUS), "status", SfAppendMaterial.SF_VS_APPEND_MATERIAL_STATUS);
    TextFieldEditor sjr = new TextFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_SJR), "sjr");
    TextFieldEditor sjrTel = new TextFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_SJR_TEL), "sjrTel");
    TextFieldEditor sjrZjType = new TextFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_SJR_ZJ_TYPE), "sjrZjType");
    TextFieldEditor sjrZjCode = new TextFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_SJR_ZJ_CODE), "sjrZjCode");
    TextFieldEditor acceptor = new TextFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_ACCEPTOR), "acceptorName");
    DateFieldEditor acceptDate = new DateFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_ACCEPT_DATE), "acceptDate");
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_REMARK), "remark", 100, 2, 5);

    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_INPUTOR), "inputorName");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_INPUT_DATE), "inputDate");

    TextFieldEditor validator = new TextFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_VALIDATOR), "validatorName");
    DateFieldEditor validatDate = new DateFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_VALIDAT_DATE), "validatDate");
    TextAreaFieldEditor validatOpinion = new TextAreaFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_VALIDAT_OPINION), "validatOpinion", 100, 2, 5);
    AsValFieldEditor validatIsPass = new AsValFieldEditor(LangTransMeta.translate(SfAppendMaterial.COL_VALIDAT_IS_PASS), "validatIsPass",
      SfElementConstants.VS_Y_N, true);

    editorList.add(entrust);
    editorList.add(name);
    editorList.add(status);

    editorList.add(sjr);
    editorList.add(sjrTel);
    editorList.add(sjrZjType);
    editorList.add(sjrZjCode);
    
    editorList.add(acceptor); 
    editorList.add(acceptDate); 
    editorList.add(remark);
    
    editorList.add(inputor);
    editorList.add(inputDate);
    
    return editorList;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {

    initDetailTablePanel();
//    initValidateDetailTablePanel();

    JTabbedPane itemTabPane = new JTabbedPane();
    itemTabPane.addTab("检材信息", detailTablePanel);
//    itemTabPane.addTab("验证情况", validateDetailTablePanel);
    itemTabPane.setMinimumSize(new Dimension(240, 300));
    return itemTabPane;
  }
 
 
  private void initDetailTablePanel() {

    detailTablePanel.init();

    detailTablePanel.setPanelId(this.getClass().getName() + "_detailTablePanel");

    detailTablePanel.getSearchBar().setVisible(false);

    detailTablePanel.setTablePreferencesKey(this.getClass().getName() + "__detailTable");

    detailTablePanel.getTable().setShowCheckedColumn(true);

    detailTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    JFuncToolBar detailBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    detailBtnBar.add(addBtn2);

    detailBtnBar.add(insertBtn2);

    detailBtnBar.add(delBtn2);

    detailTablePanel.add(detailBtnBar, BorderLayout.SOUTH);


    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfMaterials item = new SfMaterials();
        setMaterialDefaultValue(item);
        int rowNum = addSub(detailTablePanel, item);
        detailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfMaterials item = new SfMaterials();
        setMaterialDefaultValue(item);
        int rowNum = insertSub(detailTablePanel, item);
        detailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(detailTablePanel);
      }
    });
  }

  protected void setMaterialDefaultValue(SfMaterials item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
    SfAppendMaterial e = listCursor.getCurrentObject();
    item.setEntrustId(e.getEntrustId());
    item.setMaterialType(SfMaterials.SF_VS_MATERIAL_TYPE_jiancai);
    item.setJianHouChuliType(SfMaterials.SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE_tuihui);
    item.setJianHouStoreTime(new BigDecimal(AsOptionMeta.getOptVal(SfMaterials.OPT_SF_MATERIALS_STORE_DAYS)));
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

    SfAppendMaterial afterSaveBill = null;

    if (this.isDataChanged()) {
      JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    try {
      requestMeta.setFuncId(this.sendButton.getFuncId());
      SfAppendMaterial qx = (SfAppendMaterial) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfAppendMaterialServiceDelegate.newCommitFN(qx, requestMeta);
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
    SfAppendMaterial qx = (SfAppendMaterial) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
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
      qx = sfAppendMaterialServiceDelegate.auditFN(qx, requestMeta);
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
    SfAppendMaterial qx = (SfAppendMaterial) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    boolean success = true;
    SfAppendMaterial afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }
    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfAppendMaterialServiceDelegate.unAuditFN(qx, requestMeta);
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
    SfAppendMaterial afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      SfAppendMaterial qx = (SfAppendMaterial) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = sfAppendMaterialServiceDelegate.untreadFN(qx, requestMeta);
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
    SfAppendMaterial afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      SfAppendMaterial qx = (SfAppendMaterial) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = sfAppendMaterialServiceDelegate.callbackFN(qx, requestMeta);
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