package com.ufgov.zc.client.sf.sfdocsend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfDocSendToTableModelConverter;
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
import com.ufgov.zc.client.component.button.SendButton;
import com.ufgov.zc.client.component.button.SendGkButton;
import com.ufgov.zc.client.component.button.SubaddButton;
import com.ufgov.zc.client.component.button.SubdelButton;
import com.ufgov.zc.client.component.button.SubinsertButton;
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.table.celleditor.DateCellEditor;
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.DateCellRenderer;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.AsValComboBoxCellEditor;
import com.ufgov.zc.client.component.table.codecellrenderer.AsValCellRenderer;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfJdDocAuditHandler;
import com.ufgov.zc.client.sf.jdresult.SfJdResultDialog;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfCertificate;
import com.ufgov.zc.common.sf.model.SfDocSend;
import com.ufgov.zc.common.sf.model.SfDocSendDetail;
import com.ufgov.zc.common.sf.model.SfDocSendMaterial;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.sf.publish.ISfDocSendServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfJdDocAuditServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfDocSendEditPanel  extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -3538752096000004615L;

  private static final Logger logger = Logger.getLogger(SfDocSendEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_DOC_SEND";

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

  protected ListCursor<SfDocSend> listCursor;

  private SfDocSend oldJdPerson;

  public SfDocSendListPanel listPanel;

  protected SfDocSendEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;
  private BillElementMeta detailBillElementMeta;

  protected JTablePanel docDetailTablePanel = new JTablePanel();  
  protected JTablePanel materialTablePanel = new JTablePanel();  

//  protected JTablePanel certTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfDocSendServiceDelegate sfDocSendServiceDelegate;
  
  private ISfJdDocAuditServiceDelegate sfJdDocAuditServiceDelegate;

  public SfDocSendEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfDocSendListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfDocSend.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfDocSendServiceDelegate = (ISfDocSendServiceDelegate) ServiceFactory.create(ISfDocSendServiceDelegate.class, "sfDocSendServiceDelegate");
    sfJdDocAuditServiceDelegate = (ISfJdDocAuditServiceDelegate) ServiceFactory.create(ISfJdDocAuditServiceDelegate.class, "sfJdDocAuditServiceDelegate");
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    detailBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd("SF_DOC_SEND_DETAIL");
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

    SfDocSend bill = (SfDocSend) listCursor.getCurrentObject();
 
    if (bill != null) {
      if (bill.getSendId() != null) {//列表页面双击进入
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
        bill = sfDocSendServiceDelegate.selectByPrimaryKey(bill.getSendId(), this.requestMeta);
        listCursor.setCurrentObject(bill);
        this.setEditingObject(bill);
      } else if (bill.getEntrustId() != null) {//图形界面进来的新增，已经确定了entrust
        this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
        setDefaultValue(bill);
        this.setEditingObject(bill);
      }
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      bill = new SfDocSend();
      setDefaultValue(bill);
      listCursor.getDataList().add(bill);
      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
  }

  private void setDefaultValue(SfDocSend docSend) {
    // TCJLODO Auto-generated method stub 
    docSend.setNd(this.requestMeta.getSvNd());
    docSend.setCoCode(requestMeta.getSvCoCode());
    docSend.setSendDate(SfUtil.getSysDate());
    docSend.setSendor(requestMeta.getSvUserName());
    docSend.setSendType("1");
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    SfDocSend docSend = (SfDocSend) listCursor.getCurrentObject();
    docDetailTablePanel.setTableModel(SfDocSendToTableModelConverter.convertDetailTableData(docSend.getDocDetailLst()));
    ZcUtil.translateColName(docDetailTablePanel.getTable(), SfDocSendToTableModelConverter.getDetailInfo()); 
    materialTablePanel.setTableModel(SfDocSendToTableModelConverter.convertMaterialTableData(docSend.getMaterialLst()));
    ZcUtil.translateColName(materialTablePanel.getTable(), SfDocSendToTableModelConverter.getMaterialInfo()); 
    
    setTablePorperty();
  }


  private void setTablePorperty() {
    final JPageableFixedTable table = docDetailTablePanel.getTable();
    table.setDefaultEditor(String.class, new TextCellEditor());
    final JPageableFixedTable table2 = materialTablePanel.getTable();
    table2.setDefaultEditor(String.class, new TextCellEditor());

    SwingUtil.setTableCellEditor(table2, SfMaterialsTransferDetail.COL_PROCESSING, new AsValComboBoxCellEditor(SfMaterials.SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE));
    SwingUtil.setTableCellRenderer(table2, SfMaterialsTransferDetail.COL_PROCESSING, new AsValCellRenderer(SfMaterials.SF_VS_MATERIAL_JIAN_HOU_CHULI_TYPE)); 
     

    SwingUtil.setTableCellEditor(table2, SfMaterialsTransferDetail.COL_QUANTITY, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table2, SfMaterialsTransferDetail.COL_QUANTITY, new NumberCellRenderer());  

    SwingUtil.setTableCellEditor(table2, SfMaterialsTransferDetail.COL_PROCESSING_DATE, new DateCellEditor());
    SwingUtil.setTableCellRenderer(table2, SfMaterialsTransferDetail.COL_PROCESSING_DATE, new DateCellRenderer("YY-MM-DD")); 
    
    SwingUtil.setTableCellEditor(table2, SfMaterials.COL_MATERIAL_TYPE, new AsValComboBoxCellEditor(SfMaterials.SF_VS_MATERIAL_TYPE));
    SwingUtil.setTableCellRenderer(table2, SfMaterials.COL_MATERIAL_TYPE, new AsValCellRenderer(SfMaterials.SF_VS_MATERIAL_TYPE));
  }

  protected void updateFieldEditorsEditable() {

    for (AbstractFieldEditor editor : fieldEditors) {
      if ("sendDate".equals(editor.getFieldName())
        ||"sendor".equals(editor.getFieldName())
        ||"entrust.entrustor.name".equals(editor.getFieldName())
        ||"entrust.name".equals(editor.getFieldName())){
        editor.setEnabled(false);
        continue;
      }
      if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {       
          editor.setEnabled(true);
          isEdit=true;
      } else {
        editor.setEnabled(false);
        isEdit=false;
      }
    }

    setWFSubTableEditable(docDetailTablePanel, isEdit); 
    setWFSubTableEditable(materialTablePanel, isEdit); 
  }

  protected void setButtonStatus() {
    SfDocSend docSend = (SfDocSend) listCursor.getCurrentObject();
    setButtonStatus(docSend, requestMeta, this.listCursor);

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

    SfDocSend docSend = (SfDocSend) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), docSend.getProcessInstId());

  }

  protected void setOldObject() {

    oldJdPerson = (SfDocSend) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

  protected void doAdd() {
    // TCJLODO Auto-generated method stub
//    SfDocSend charge=new SfDocSend();
//    setDefaultValue(charge);
//    listCursor.getDataList().add(charge);
//    listCursor.setCurrentObject(charge);
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

        listCursor.setCurrentObject(oldJdPerson);

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

        listCursor.setCurrentObject(oldJdPerson);

      }

    }

    listCursor.next();

    refreshData();

  }

  public void stopTableEditing() {

    

    JPageableFixedTable itemTable = this.docDetailTablePanel.getTable();

    if (itemTable.isEditing()) {

      itemTable.getCellEditor().stopCellEditing();

    }
    JPageableFixedTable mtable = this.materialTablePanel.getTable();

    if (mtable.isEditing()) {

      mtable.getCellEditor().stopCellEditing();

    }

  }
  public boolean doSave() {
	  stopTableEditing();
   /* if (!isDataChanged()) {

      JOptionPane.showMessageDialog(this, "数据没有发生改变，不用保存.", "提示", JOptionPane.INFORMATION_MESSAGE);

      return true;

    }*/

    if (!checkBeforeSave()) {

      return false;

    }

    boolean success = true;

    String errorInfo = "";

    SfDocSend docSend = (SfDocSend) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      docSend = sfDocSendServiceDelegate.saveFN(docSend, this.requestMeta);

      listCursor.setCurrentObject(docSend);

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

  /**
   * 更新数据流界面
   */
  private void updateDataFlowDialog() {
    // TCJLODO Auto-generated method stub
    SfDocSend bill = (SfDocSend) this.listCursor.getCurrentObject();
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
  private void refreshListPanel() {
    if (listPanel != null) {
      listPanel.refreshCurrentTabData();
    }
  }

 
  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() { 
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfDocSend docSend = (SfDocSend) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(docSend, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString());
    } 
    StringBuffer detailError=new StringBuffer();
    List detailNotNullList = detailBillElementMeta.getNotNullBillElement();
    for(int i=0;i<docSend.getDocDetailLst().size();i++){
      SfDocSendDetail d=(SfDocSendDetail)docSend.getDocDetailLst().get(i);
      String dInfo = ZcUtil.validateBillElementNull(d, detailNotNullList);
      if(dInfo!=null && dInfo.trim().length()>0){
        detailError.append(dInfo).append("\n");
      }
    }

    if (detailError.length() != 0) {
      errorInfo.append("\n").append(detailError.toString());
    }
    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  protected void doDelete() {
    requestMeta.setFuncId(deleteButton.getFuncId());
    SfDocSend docSend = (SfDocSend) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfDocSendServiceDelegate.deleteByPrimaryKeyFN(docSend.getSendId(), requestMeta);
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        success = false;
        errorInfo += e.getMessage();
      }

      if (success) {
        this.listCursor.removeCurrentObject();
        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        refreshListPanel();
        doAdd();
      } else {
        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public boolean isDataChanged() {
    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) {
      return false;
    }
    return !DigestUtil.digest(oldJdPerson).equals(DigestUtil.digest(listCursor.getCurrentObject()));
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

    SfJdDocAuditHandler docAuditHandler = new SfJdDocAuditHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfDocSend currentBill = (SfDocSend) listCursor.getCurrentObject();
          SfJdDocAudit da = (SfJdDocAudit) obj;
          da=sfJdDocAuditServiceDelegate.selectByPrimaryKey(da.getJdDocAuditId(), requestMeta);
          currentBill.setEntrust(da.getEntrust()); 
          currentBill.setJdDocAuditId(da.getJdDocAuditId()); 
          
          currentBill.getDocDetailLst().clear(); 
          if (da.getDetailLst()!= null) {
            for (int i = 0; i < da.getDetailLst().size(); i++) {
              SfJdDocAuditDetail m = (SfJdDocAuditDetail) da.getDetailLst().get(i);
              SfDocSendDetail d=new SfDocSendDetail();
              d.setDocAuditDetail(m); 
//              d.setProcessingMan(entrust.getJdFzr());
              currentBill.getDocDetailLst().add(d);
            }
          }
          currentBill.getMaterialLst().clear();
          if(da.getMaterialLst()!=null){
            for (int i = 0; i < da.getMaterialLst().size(); i++) {
              SfMaterialsTransferDetail mt=(SfMaterialsTransferDetail)da.getMaterialLst().get(i);
              SfDocSendMaterial sm=new SfDocSendMaterial();
              sm.setMaterialTransfer(mt);
              sm.setProcessing(mt.getProcessing());
              sm.setRemark(mt.getRemark());
              currentBill.getMaterialLst().add(sm);
            }            
          }
          setEditingObject(currentBill);
          refreshSubData();
          break;
        }
      }

      public void afterClear() {
        SfDocSend currentBill = (SfDocSend) listCursor.getCurrentObject();
        currentBill.setEntrust(new SfEntrust());
        currentBill.setJdDocAuditId(null); 
        currentBill.getDocDetailLst().clear();  
        setEditingObject(currentBill);
        refreshSubData();
      }
    };
    ElementConditionDto dto = new ElementConditionDto(); 
    dto.setCoCode(requestMeta.getSvCoCode());
    dto.setNd(requestMeta.getSvNd()); 
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(docAuditHandler.getSqlId(), dto, 20, docAuditHandler,
      docAuditHandler.getColumNames(), LangTransMeta.translate(SfDocSend.COL_ENTRUST_CODE), "entrust.code");

    AsValFieldEditor sendType = new AsValFieldEditor(LangTransMeta.translate(SfDocSend.COL_SEND_TYPE), "sendType", SfDocSend.VS_SF_DOC_SEND_SEND_TYPE);
    DateFieldEditor sendDate = new DateFieldEditor(LangTransMeta.translate(SfDocSend.COL_SEND_DATE), "sendDate");
    TextFieldEditor sendor = new TextFieldEditor(LangTransMeta.translate(SfDocSend.COL_SENDOR), "sendor");
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfDocSend.COL_NAME), "entrust.name");
    TextFieldEditor wtf = new TextFieldEditor(LangTransMeta.translate(SfDocSend.COL_WTF), "entrust.entrustor.name");
    TextFieldEditor recievor = new TextFieldEditor(LangTransMeta.translate(SfDocSend.COL_RECIEVOR), "recievor");
    TextFieldEditor recievorTel = new TextFieldEditor(LangTransMeta.translate(SfDocSend.COL_RECIEVOR_TEL), "recievorTel"); 
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfDocSend.COL_REMARK), "remark", 100, 2,5); 

    editorList.add(entrust);
    editorList.add(name);
    editorList.add(wtf);    
    
    editorList.add(recievor);
    editorList.add(recievorTel);
    editorList.add(sendType);
//    editorList.add(new NewLineFieldEditor());
    
    editorList.add(sendor);    
    editorList.add(sendDate);  
    editorList.add(new NewLineFieldEditor());
    
    editorList.add(remark);

    return editorList;
  }

 

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {

    JTabbedPane itemTabPane = new JTabbedPane();

    docDetailTablePanel.init();

    docDetailTablePanel.setPanelId(this.getClass().getName() + "_detailTablePanel");

    docDetailTablePanel.getSearchBar().setVisible(false);

    docDetailTablePanel.setTablePreferencesKey(this.getClass().getName() + "__detailTable");

    docDetailTablePanel.getTable().setShowCheckedColumn(true);

    docDetailTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    JFuncToolBar detailBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    detailBtnBar.add(addBtn2);

    detailBtnBar.add(insertBtn2);

    detailBtnBar.add(delBtn2);

    docDetailTablePanel.add(detailBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfDocSendDetail item = new SfDocSendDetail();
        setSfDocSendDetailDefaultVal(item);
        int rowNum = addSub(docDetailTablePanel, item);
        docDetailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfDocSendDetail item = new SfDocSendDetail();
        setSfDocSendDetailDefaultVal(item);
        int rowNum = insertSub(docDetailTablePanel, item);
        docDetailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(docDetailTablePanel);
      }
    });
    
//-----------------


    materialTablePanel.init();

    materialTablePanel.setPanelId(this.getClass().getName() + "_materialTablePanel");

    materialTablePanel.getSearchBar().setVisible(false);

    materialTablePanel.setTablePreferencesKey(this.getClass().getName() + "_materialTable");

    materialTablePanel.getTable().setShowCheckedColumn(true);

    materialTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    JFuncToolBar materialBtnBar = new JFuncToolBar();

    FuncButton addBtn = new SubaddButton(false);

    JButton insertBtn = new SubinsertButton(false);

    JButton delBtn = new SubdelButton(false);

    materialBtnBar.add(addBtn,false);

    materialBtnBar.add(insertBtn,false);

    materialBtnBar.add(delBtn,false);

    materialTablePanel.add(materialBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfDocSendMaterial item = new SfDocSendMaterial();
        setSfDocSendMaterialDefaultVal(item);
        int rowNum = addSub(materialTablePanel, item);
        materialTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfDocSendMaterial item = new SfDocSendMaterial();
        setSfDocSendMaterialDefaultVal(item);
        int rowNum = insertSub(materialTablePanel, item);
        materialTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(materialTablePanel);
      }
    });
    
 


    itemTabPane.addTab("文书明细", docDetailTablePanel); 
    itemTabPane.addTab("检材明细", materialTablePanel); 
    
    itemTabPane.setMinimumSize(new Dimension(240, 300));
    
    return itemTabPane;
  }

  protected void setCerDefaultValue(SfCertificate item) {
	    item.setTempId("" + System.currentTimeMillis());
	    SfDocSend e = listCursor.getCurrentObject();
	    item.setZfOwnerId(e.getSendId());
	    item.setZfOwnerType(SfCertificate.VS_SF_CERTIFICATE_TYPE_zizhi);
}

protected void setSfDocSendDetailDefaultVal(SfDocSendDetail item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
    SfDocSend e = listCursor.getCurrentObject();
    item.setSendId(e.getSendId());
  }
protected void setSfDocSendMaterialDefaultVal(SfDocSendMaterial item) {
  // TCJLODO Auto-generated method stub
  item.setTempId("" + System.currentTimeMillis());
  SfDocSend e = listCursor.getCurrentObject();
  item.setSendId(e.getSendId());
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

}