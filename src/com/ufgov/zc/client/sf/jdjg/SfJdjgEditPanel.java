package com.ufgov.zc.client.sf.jdjg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import com.ufgov.zc.client.common.converter.sf.SfJdjgToTableModelConverter;
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
import com.ufgov.zc.client.component.button.SaveSendButton;
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
import com.ufgov.zc.client.component.zc.fieldeditor.AutoNumFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldCellEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.jdperson.JdPersonMajorHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfCertificate;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdPersonMajor;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.publish.ISfJdjgServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfJdjgEditPanel  extends AbstractMainSubEditPanel {

	  private static final Logger logger = Logger.getLogger(SfJdjgEditPanel.class);

	  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

	  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

	  private static String compoId = "SF_JDJG";

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

	  protected ListCursor<SfJdjg> listCursor;

	  private SfJdjg oldMajor;

	  public SfJdjgListPanel listPanel;

	  protected SfJdjgEditPanel self = this;

	  protected GkBaseDialog parent;

	  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

	  private BillElementMeta mainBillElementMeta;

	  private ElementConditionDto eaccDto = new ElementConditionDto();

//	  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate ;  
	  
	  private ISfJdjgServiceDelegate sfJdjgServiceDelegate;

	  protected JTablePanel certTablePanel = new JTablePanel();
	  
	  public SfJdjgEditPanel(SfJdjgDialog parent, ListCursor listCursor, String tabStatus, SfJdjgListPanel listPanel) {
	    // TCJLODO Auto-generated constructor stub
	    super(SfJdjgEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
	    
	    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd("SF_JDJG");
	    sfJdjgServiceDelegate = (ISfJdjgServiceDelegate) ServiceFactory.create(ISfJdjgServiceDelegate.class,"sfJdjgServiceDelegate"); 
	    
	    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
	      TitledBorder.CENTER, TitledBorder.TOP,

	      new Font("宋体", Font.BOLD, 15), Color.BLUE));

	    this.listCursor = listCursor;

	    this.listPanel = listPanel;

	    this.parent = parent;

	    this.colCount = 3;

	    init();

	    requestMeta.setCompoId(getCompoId());

	    refreshData();
	  }

	  private void refreshData() {
	    // TCJLODO Auto-generated method stub

	    SfJdjg bill = (SfJdjg) listCursor.getCurrentObject();

	    if (bill != null && !"".equals(ZcUtil.safeString(bill.getJgId()))) {//列表页面双击进入

	      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

	       
	      bill =  sfJdjgServiceDelegate.selectByPrimaryKey(bill.getJgId(), requestMeta);
	      
	      listCursor.setCurrentObject(bill);
	      this.setEditingObject(bill);
	    } else {//新增按钮进入

	      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

	      bill = new SfJdjg();

	      listCursor.getDataList().add(bill);

	      listCursor.setCurrentObject(bill);

	      this.setEditingObject(bill);

	    }

	    refreshSubData();
	    
	    setOldObject();

	    setButtonStatus();

	    updateFieldEditorsEditable();

	  }

	  private void refreshSubData() {
	    // TCJLODO Auto-generated method stub
		    SfJdjg jdjg = (SfJdjg) listCursor.getCurrentObject();
		  certTablePanel.setTableModel(SfJdPersonToTableModelConverter.convertCertTableData(jdjg.getCertificatLst()));
		    ZcUtil.translateColName(certTablePanel.getTable(), SfJdPersonToTableModelConverter.getCertInfo());
		    setTablePorperty();
	     
	  }


	  private void setTablePorperty() { 
	    
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

	      setWFSubTableEditable(certTablePanel, isEdit);
	  }

	  public void stopTableEditing() {

	    JPageableFixedTable biTable = this.certTablePanel.getTable();

	    if (biTable.isEditing()) {

	      biTable.getCellEditor().stopCellEditing();

	    } 

	  }
	 

	  protected void setButtonStatus() {
	    SfJdjg bill = (SfJdjg) listCursor.getCurrentObject();
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

	    }

	    SfJdjg bill = (SfJdjg) this.listCursor.getCurrentObject();
	     
	    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), bill.getProcessInstId());

	  }

	  protected void setOldObject() {

	    oldMajor = (SfJdjg) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

//	    toolBar.add(sendButton);

//	    toolBar.add(saveAndSendButton);

//	    toolBar.add(suggestPassButton);

//	    toolBar.add(sendGkButton);

//	    toolBar.add(unAuditButton);

//	    toolBar.add(unTreadButton);

//	    toolBar.add(callbackButton);

	    toolBar.add(deleteButton);

//	    toolBar.add(importButton);

//	    toolBar.add(printButton);

//	    toolBar.add(traceButton);

//	    toolBar.add(previousButton);

//	    toolBar.add(nextButton);

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

	        listCursor.setCurrentObject(oldMajor);

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

	        listCursor.setCurrentObject(oldMajor);

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

	      SfJdjg inData = (SfJdjg) this.listCursor.getCurrentObject();

//	      System.out.println("before=" + inData.getCoCode() + inData.getCoName());
	    
	      inData=sfJdjgServiceDelegate.saveFN(inData, requestMeta);
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

	  /**

	   * 保存前校验

	   * @param cpApply

	   * @return

	   */

	  protected boolean checkBeforeSave() {
	    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
	    SfJdjg bill = (SfJdjg) this.listCursor.getCurrentObject();
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

	    SfJdjg bill = (SfJdjg) this.listCursor.getCurrentObject();

	     
	    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

	    if (num == JOptionPane.YES_OPTION) {

	      boolean success = true;

	      String errorInfo = "";

	      try {

	        requestMeta.setFuncId(deleteButton.getFuncId());
 
	        sfJdjgServiceDelegate.deleteByPrimaryKeyFN(bill.getJgId(), requestMeta);

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

	    return !DigestUtil.digest(oldMajor).equals(DigestUtil.digest(listCursor.getCurrentObject()));

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

 
	    TextFieldEditor code = new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_CO_CODE), "coCode");
	    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_NAME), "name");
      TextFieldEditor  shortName= new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_SHORT_NAME), "shortName"); 
	    TextFieldEditor xkzh = new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_XKZH), "xkzh");
	    TextFieldEditor  tel= new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_TEL), "tel");
	    TextFieldEditor  linkMan= new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_LINK_MAN), "linkMan");
	    TextFieldEditor  address= new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_ADDRESS), "address");
	    TextFieldEditor  zip= new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_ZIP), "zip");
	    TextFieldEditor  fax= new TextFieldEditor(LangTransMeta.translate(SfJdjg.COL_FAX), "fax"); 
	    TextAreaFieldEditor enName = new TextAreaFieldEditor(LangTransMeta.translate(SfJdjg.COL_EN_NAME), "enName", -1, 1, 5);
	    
	    editorList.add(name);
      editorList.add(shortName);
	    editorList.add(code);
	    editorList.add(xkzh);
	    editorList.add(tel);
	    editorList.add(linkMan);
	    editorList.add(address);
	    editorList.add(zip);
	    editorList.add(fax);
	    editorList.add(enName);
	    
	    return editorList;

	  }

	  protected void setCerDefaultValue(SfCertificate item) {
		    item.setTempId("" + System.currentTimeMillis());
		    SfJdjg e = listCursor.getCurrentObject();
		    item.setZfOwnerId(e.getJgId());
		    item.setZfOwnerType(SfCertificate.VS_SF_CERTIFICATE_TYPE_zizhi);
	}

	  /* (non-Javadoc)
	   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
	   */
	  @Override
	  public JComponent createSubBillPanel() {


		    JTabbedPane itemTabPane = new JTabbedPane();

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
  
		    itemTabPane.addTab("相关资质文件", certTablePanel);
		    
		    itemTabPane.setMinimumSize(new Dimension(240, 300));
		    return itemTabPane;
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
