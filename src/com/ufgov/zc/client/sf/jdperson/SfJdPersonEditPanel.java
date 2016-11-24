package com.ufgov.zc.client.sf.jdperson;

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
import com.ufgov.zc.client.common.converter.sf.SfJdPersonToTableModelConverter;
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
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.DateCellEditor;
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.DateCellRenderer;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.AsValComboBoxCellEditor;
import com.ufgov.zc.client.component.table.codecelleditor.FileCellEditor;
import com.ufgov.zc.client.component.table.codecellrenderer.AsValCellRenderer;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldCellEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.MoneyFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.sf.util.SfUserSelectHandler;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.console.model.AsEmp;
import com.ufgov.zc.common.sf.model.SfCertificate;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdPersonMajor;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfMaterialsTransferDetail;
import com.ufgov.zc.common.sf.publish.ISfJdPersonServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfJdPersonEditPanel  extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = -3538752096000004615L;

  private static final Logger logger = Logger.getLogger(SfJdPersonEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_JD_PERSON";

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

  protected ListCursor<SfJdPerson> listCursor;

  private SfJdPerson oldJdPerson;

  public SfJdPersonListPanel listPanel;

  protected SfJdPersonEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;
  private BillElementMeta detailBillElementMeta;

  protected JTablePanel majorTablePanel = new JTablePanel();  

  protected JTablePanel certTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfJdPersonServiceDelegate sfJdPersonServiceDelegate;

  public SfJdPersonEditPanel(GkBaseDialog parent, ListCursor listCursor, String tabStatus, SfJdPersonListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfJdPersonEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfJdPersonServiceDelegate = (ISfJdPersonServiceDelegate) ServiceFactory.create(ISfJdPersonServiceDelegate.class, "sfJdPersonServiceDelegate");
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    detailBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd("SF_JD_PERSON_MAJOR");
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

    SfJdPerson charge = (SfJdPerson) listCursor.getCurrentObject();

    if (charge != null && charge.getJdPersonId() != null) {//列表页面双击进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
      charge = sfJdPersonServiceDelegate.selectByPrimaryKey(charge.getJdPersonId(), this.requestMeta);
      listCursor.setCurrentObject(charge);
      this.setEditingObject(charge);
    } else {//新增按钮进入
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      charge = new SfJdPerson();
      setDefaultValue(charge);
      listCursor.getDataList().add(charge);
      listCursor.setCurrentObject(charge);
      this.setEditingObject(charge);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
  }

  private void setDefaultValue(SfJdPerson jdPerson) {
    // TCJLODO Auto-generated method stub
    jdPerson.setStatus(SfJdPerson.STATUS_ENABLE);
    jdPerson.setNd(this.requestMeta.getSvNd());
    jdPerson.setCoCode(requestMeta.getSvCoCode());
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    SfJdPerson jdPerson = (SfJdPerson) listCursor.getCurrentObject();
    majorTablePanel.setTableModel(SfJdPersonToTableModelConverter.convertMajorsTableData(jdPerson.getMajorLst()));
    ZcUtil.translateColName(majorTablePanel.getTable(), SfJdPersonToTableModelConverter.getDetailInfo());
    certTablePanel.setTableModel(SfJdPersonToTableModelConverter.convertCertTableData(jdPerson.getCertificatLst()));
    ZcUtil.translateColName(certTablePanel.getTable(), SfJdPersonToTableModelConverter.getCertInfo());
    setTablePorperty();
  }


  private void setTablePorperty() {
    final JPageableFixedTable table = majorTablePanel.getTable();
    table.setDefaultEditor(String.class, new TextCellEditor());
    JdPersonMajorHandler handler = new JdPersonMajorHandler() {
      @Override
      public void excute(List selectedDatas) {
        BeanTableModel model = (BeanTableModel) table.getModel();
        int k = table.getSelectedRow();
        if (k < 0) {
          return;
        }

        int k2 = table.convertRowIndexToModel(k);
        SfJdPersonMajor detail = (SfJdPersonMajor) (model.getBean(k2));
        for (Object obj : selectedDatas) {
          SfMajor standard = (SfMajor) obj;
          detail.setMajor(standard);
        }
        model.fireTableRowsUpdated(k, k);
      }
    };

    ElementConditionDto dto = new ElementConditionDto();
    ForeignEntityFieldCellEditor foreignExpertCodeEditor = new ForeignEntityFieldCellEditor(handler.getSqlId(), dto, 20, handler,
      handler.getColumNames(), "鉴定专业", "majorName");

    SwingUtil.setTableCellEditor(table, SfMajor.COL_MAJOR_NAME, foreignExpertCodeEditor);
    
    JPageableFixedTable ctb = certTablePanel.getTable();
    ctb.setDefaultEditor(String.class, new TextCellEditor()); 
    

    SwingUtil.setTableCellEditor(ctb, SfCertificate.COL_ZF_NOTICE_DAYS, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(ctb, SfCertificate.COL_ZF_NOTICE_DAYS, new NumberCellRenderer());
    SwingUtil.setTableCellEditor(ctb, SfCertificate.COL_IS_NOTICE_EXPIRE, new AsValComboBoxCellEditor(SfElementConstants.VS_Y_N));
    SwingUtil.setTableCellRenderer(ctb, SfCertificate.COL_IS_NOTICE_EXPIRE, new AsValCellRenderer(SfElementConstants.VS_Y_N));
    SwingUtil.setTableCellEditor(ctb, SfCertificate.COL_ZF_OWNER_TYPE, new AsValComboBoxCellEditor(SfCertificate.VS_SF_CERTIFICATE_TYPE));
    SwingUtil.setTableCellRenderer(ctb, SfCertificate.COL_ZF_OWNER_TYPE, new AsValCellRenderer(SfCertificate.VS_SF_CERTIFICATE_TYPE));
    
    FileCellEditor fileEditor=new FileCellEditor("zfFileBlobid",false,(BeanTableModel) ctb.getModel());
    fileEditor.setDownloadFileEnable(true);
    SwingUtil.setTableCellEditor(ctb, SfCertificate.COL_ZF_FILE,fileEditor ); 
    

    SwingUtil.setTableCellEditor(ctb, SfCertificate.COL_ZF_BEGIN_DATE, new DateCellEditor());
    SwingUtil.setTableCellRenderer(ctb, SfCertificate.COL_ZF_BEGIN_DATE, new DateCellRenderer("YY-MM-DD")); 
    SwingUtil.setTableCellEditor(ctb, SfCertificate.COL_ZF_END_DATE, new DateCellEditor());
    SwingUtil.setTableCellRenderer(ctb, SfCertificate.COL_ZF_END_DATE, new DateCellRenderer("YY-MM-DD")); 
    
  }

  protected void updateFieldEditorsEditable() {

    for (AbstractFieldEditor editor : fieldEditors) {
      if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT) || pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {       
          editor.setEnabled(true);
          isEdit=true;
      } else {
        editor.setEnabled(false);
        isEdit=false;
      }
    }

    setWFSubTableEditable(majorTablePanel, isEdit);
    setWFSubTableEditable(certTablePanel, isEdit);
  }

  protected void setButtonStatus() {
    SfJdPerson jdPerson = (SfJdPerson) listCursor.getCurrentObject();
    setButtonStatus(jdPerson, requestMeta, this.listCursor);

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

    SfJdPerson jdPerson = (SfJdPerson) this.listCursor.getCurrentObject();

    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), jdPerson.getProcessInstId());

  }

  protected void setOldObject() {

    oldJdPerson = (SfJdPerson) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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
    SfJdPerson charge=new SfJdPerson();
    setDefaultValue(charge);
    listCursor.getDataList().add(charge);
    listCursor.setCurrentObject(charge);
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

    JPageableFixedTable biTable = this.certTablePanel.getTable();

    if (biTable.isEditing()) {

      biTable.getCellEditor().stopCellEditing();

    }

    JPageableFixedTable itemTable = this.majorTablePanel.getTable();

    if (itemTable.isEditing()) {

      itemTable.getCellEditor().stopCellEditing();

    }

  }
  public boolean doSave() {
	  stopTableEditing();
    if (!isDataChanged()) {

      JOptionPane.showMessageDialog(this, "数据没有发生改变，不用保存.", "提示", JOptionPane.INFORMATION_MESSAGE);

      return true;

    }

    if (!checkBeforeSave()) {

      return false;

    }

    boolean success = true;

    String errorInfo = "";

    SfJdPerson jdPerson = (SfJdPerson) this.listCursor.getCurrentObject();

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      jdPerson = sfJdPersonServiceDelegate.saveFN(jdPerson, this.requestMeta);

      listCursor.setCurrentObject(jdPerson);

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      success = false;

      errorInfo += e.getMessage();

    }

    if (success) {

      JOptionPane.showMessageDialog(this, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

      refreshListPanel();
      refreshData();

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

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() { 
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfJdPerson jdPerson = (SfJdPerson) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(jdPerson, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString());
    }
    if(jdPerson.getMajorLst()==null || jdPerson.getMajorLst().size()==0){
      errorInfo.append("\n鉴定专业不能为空");
    }
    StringBuffer detailError=new StringBuffer();
    List detailNotNullList = detailBillElementMeta.getNotNullBillElement();
    for(int i=0;i<jdPerson.getMajorLst().size();i++){
      SfJdPersonMajor d=(SfJdPersonMajor)jdPerson.getMajorLst().get(i);
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
    SfJdPerson jdPerson = (SfJdPerson) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfJdPersonServiceDelegate.deleteByPrimaryKeyFN(jdPerson.getJdPersonId(), requestMeta);
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

    SfUserSelectHandler userHandler = new SfUserSelectHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfJdPerson currentBill = (SfJdPerson) listCursor.getCurrentObject();
          AsEmp user = (AsEmp) obj;
          currentBill.setName(user.getEmpName());
          currentBill.setAccount(user.getUserId());
          currentBill.setAsEmp(user);
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        SfJdPerson currentBill = (SfJdPerson) listCursor.getCurrentObject();
        currentBill.setName(null);
        currentBill.setAccount(null);
        currentBill.setAsEmp(new AsEmp());
        setEditingObject(currentBill);
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setNd(requestMeta.getSvNd());
    dto.setCoCode(requestMeta.getSvCoCode());
    dto.setDattr1("SF_JD_PERSON");
    ForeignEntityFieldEditor name = new ForeignEntityFieldEditor(userHandler.getSqlId(), dto, 20, userHandler,
      userHandler.getColumNames(), LangTransMeta.translate(SfJdPerson.COL_NAME), "name");

    AsValFieldEditor sex = new AsValFieldEditor(LangTransMeta.translate(SfJdPerson.COL_SEX), "sex", SfElementConstants.VS_SEX);
    DateFieldEditor birthday = new DateFieldEditor(LangTransMeta.translate(SfJdPerson.COL_BIRTHDAY), "birthday");
    TextFieldEditor techTitle = new TextFieldEditor(LangTransMeta.translate(SfJdPerson.COL_TECH_TITLE), "techTitle");
    TextFieldEditor certificateNo = new TextFieldEditor(LangTransMeta.translate(SfJdPerson.COL_CERTIFICATE_NO), "certificateNo");
    TextFieldEditor phone = new TextFieldEditor("移动电话", "asEmp.phone");
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfJdPerson.COL_REMARK), "remark", 100, 2,5);
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfJdPerson.COL_STATUS), "status", SfJdPerson.SF_VS_JD_PERSON_STATUS);

    editorList.add(name);
    editorList.add(sex);
    editorList.add(birthday);
    
    editorList.add(techTitle);    
    editorList.add(certificateNo); 
    editorList.add(phone);
    
    editorList.add(status);

    editorList.add(remark);

    return editorList;
  }

 

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {

    JTabbedPane itemTabPane = new JTabbedPane();

    majorTablePanel.init();

    majorTablePanel.setPanelId(this.getClass().getName() + "_detailTablePanel");

    majorTablePanel.getSearchBar().setVisible(false);

    majorTablePanel.setTablePreferencesKey(this.getClass().getName() + "__detailTable");

    majorTablePanel.getTable().setShowCheckedColumn(true);

    majorTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    JFuncToolBar detailBtnBar = new JFuncToolBar();

    FuncButton addBtn2 = new SubaddButton(false);

    JButton insertBtn2 = new SubinsertButton(false);

    JButton delBtn2 = new SubdelButton(false);

    detailBtnBar.add(addBtn2);

    detailBtnBar.add(insertBtn2);

    detailBtnBar.add(delBtn2);

    majorTablePanel.add(detailBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfJdPersonMajor item = new SfJdPersonMajor();
        setPersonDefaultValue(item);
        int rowNum = addSub(majorTablePanel, item);
        majorTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfJdPersonMajor item = new SfJdPersonMajor();
        setPersonDefaultValue(item);
        int rowNum = insertSub(majorTablePanel, item);
        majorTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(majorTablePanel);
      }
    });
    


    certTablePanel.init();

    certTablePanel.setPanelId(this.getClass().getName() + "_cerTablePanel");

    certTablePanel.getSearchBar().setVisible(false);

    certTablePanel.setTablePreferencesKey(this.getClass().getName() + "__cerTable");

    certTablePanel.getTable().setShowCheckedColumn(true);

    certTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0)); 

    JFuncToolBar detailBtnBar3 = new JFuncToolBar();

    FuncButton addBtn3 = new SubaddButton(false);

    JButton insertBtn3 = new SubinsertButton(false);

    JButton delBtn3 = new SubdelButton(false);

    detailBtnBar3.add(addBtn3);

    detailBtnBar3.add(insertBtn3);

    detailBtnBar3.add(delBtn3);

    certTablePanel.add(detailBtnBar3, BorderLayout.SOUTH);

    addBtn3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfCertificate item = new SfCertificate();
        setCerDefaultValue(item);
        int rowNum = addSub(certTablePanel, item);
        certTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfCertificate item = new SfCertificate();
        setCerDefaultValue(item);
        int rowNum = insertSub(certTablePanel, item);
        certTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(certTablePanel);
      }
    });


    itemTabPane.addTab("鉴定专业", majorTablePanel);

    itemTabPane.addTab("相关资质文件", certTablePanel);
    
    itemTabPane.setMinimumSize(new Dimension(240, 300));
    return itemTabPane;
  }

  protected void setCerDefaultValue(SfCertificate item) {
	    item.setTempId("" + System.currentTimeMillis());
	    SfJdPerson e = listCursor.getCurrentObject();
	    item.setZfOwnerId(e.getJdPersonId());
	    item.setZfOwnerType(SfCertificate.VS_SF_CERTIFICATE_TYPE_zizhi);
}

protected void setPersonDefaultValue(SfJdPersonMajor item) {
    // TCJLODO Auto-generated method stub
    item.setTempId("" + System.currentTimeMillis());
    SfJdPerson e = listCursor.getCurrentObject();
    item.setJdPersonId(e.getJdPersonId());
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