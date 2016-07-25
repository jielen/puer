package com.ufgov.zc.client.sf.jdresult;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.table.cellrenderer.DateCellRenderer;
import com.ufgov.zc.client.component.table.codecellrenderer.AsValCellRenderer;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.AutoNumFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.FileFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdRecordFileModel;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class SfJdRecordFileModelEditPanel extends AbstractMainSubEditPanel {

	  private static final Logger logger = Logger.getLogger(SfJdRecordFileModelEditPanel.class);

	  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

	  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

	  private static String compoId = "SF_JD_RECORD_FILE_MODEL";

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

	  protected ListCursor<SfJdRecordFileModel> listCursor;

	  private SfJdRecordFileModel oldModel;

	  public SfJdRecordFileModelListPanel listPanel;

	  protected SfJdRecordFileModelEditPanel self = this;

	  protected GkBaseDialog parent;

	  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

	  private BillElementMeta mainBillElementMeta;

	  private ElementConditionDto eaccDto = new ElementConditionDto();

	  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate ;   
 
	  
	  public SfJdRecordFileModelEditPanel(SfJdRecordFileModelDialog parent, ListCursor listCursor, String tabStatus, SfJdRecordFileModelListPanel listPanel) {
	    // TCJLODO Auto-generated constructor stub
	    super(SfJdRecordFileModelEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
	    
	    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
	    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate"); 
	    
	    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
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

	    SfJdRecordFileModel model = (SfJdRecordFileModel) listCursor.getCurrentObject();

	    if (model != null && !"".equals(ZcUtil.safeString(model.getModelId()))) {//列表页面双击进入

	      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

	      model = getModel(model.getModelId());
	      listCursor.setCurrentObject(model);
	      this.setEditingObject(model);
	    } else {//新增按钮进入

	      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

	      model = new SfJdRecordFileModel();
	      
	      setDefaultValue(model);

	      listCursor.getDataList().add(model);

	      listCursor.setCurrentObject(model);

	      this.setEditingObject(model);

	    }

	    refreshSubData();
	    
	    setOldObject();

	    setButtonStatus();

	    updateFieldEditorsEditable();

	  }

	  private void setDefaultValue(SfJdRecordFileModel model) {
		  model.setCoCode(requestMeta.getSvCoCode());
		  model.setInputDate(requestMeta.getSysDate());
		  model.setInputor(requestMeta.getSvUserID());
		  model.setFileType(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE_word);
		  model.setIsEnable(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_STATUS_enable);
		  model.setNd(requestMeta.getSvNd());
	}

	private SfJdRecordFileModel getModel(BigDecimal modelId) {
		return (SfJdRecordFileModel) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.selectByPrimaryKey",modelId, this.requestMeta);

	}

	private void refreshSubData() {
	    // TCJLODO Auto-generated method stub
	    SfJdRecordFileModel model = (SfJdRecordFileModel) listCursor.getCurrentObject(); 
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
	    SfJdRecordFileModel model = (SfJdRecordFileModel) listCursor.getCurrentObject();
	    setButtonStatus(model, requestMeta, this.listCursor);
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

	    SfJdRecordFileModel model = (SfJdRecordFileModel) this.listCursor.getCurrentObject();
	     
	    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), model.getProcessInstId());

	  }

	  protected void setOldObject() {

	    oldModel = (SfJdRecordFileModel) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

	        listCursor.setCurrentObject(oldModel);

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

	        listCursor.setCurrentObject(oldModel);

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

	      SfJdRecordFileModel inData = (SfJdRecordFileModel) this.listCursor.getCurrentObject();
	      inData.setCoCode(requestMeta.getSvCoCode());
	      inData.setInputDate(requestMeta.getSysDate());
	      inData.setInputor(requestMeta.getSvUserID()); 
	      inData.setNd(requestMeta.getSvNd());
		  
	      if(inData.getModelId()==null){
	    	  inData.setModelId(new BigDecimal(ZcUtil.getNextVal(SfJdRecordFileModel.SEQ_SF_RECORD_FILE_MODEL_ID)));
	    	   zcEbBaseServiceDelegate.insertFN("com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.insert", inData, requestMeta);
	      }else{
	    	  zcEbBaseServiceDelegate.insertWithDeleteFN("com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.deleteByPrimaryKey", inData.getModelId(), "com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.insert", inData, requestMeta);
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

	  /**

	   * 保存前校验

	   * @param cpApply

	   * @return

	   */

	  protected boolean checkBeforeSave() {
	    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
	    SfJdRecordFileModel model = (SfJdRecordFileModel) this.listCursor.getCurrentObject();
	    StringBuilder errorInfo = new StringBuilder();
	    String mainValidateInfo = ZcUtil.validateBillElementNull(model, mainNotNullList);     
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

	    SfJdRecordFileModel model = (SfJdRecordFileModel) this.listCursor.getCurrentObject();

	    if(isUsing()){
	      JOptionPane.showMessageDialog(this, "已经被使用，不能删除 ！\n" , "错误", JOptionPane.ERROR_MESSAGE);
	      return;
	    }
	    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

	    if (num == JOptionPane.YES_OPTION) {

	      boolean success = true;

	      String errorInfo = "";

	      try {

	        requestMeta.setFuncId(deleteButton.getFuncId());

	        zcEbBaseServiceDelegate.delete("com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.deleteByPrimaryKey",model.getModelId(), requestMeta);

	      } catch (Exception e) {

	        logger.error(e.getMessage(), e);

	        success = false;

	        errorInfo += e.getMessage();

	      }

	      if (success) {

	        this.listCursor.removeCurrentObject();

	        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE); 

	        this.listPanel.refreshCurrentTabData();
	        
	        doExit();

	      } else {

	        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);

	      }

	    }

	  }

	 
	  private boolean isUsing() {
		  ElementConditionDto dto=new ElementConditionDto();
		  dto.setDattr1("isUsing");
		  List rtn=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdRecordFileModelMapper.selectMainDataLst", dto, requestMeta);
		  if(rtn!=null && rtn.size()>0){
			  return true;
		  }
		return false;
	}

	public boolean isDataChanged() {

	    if (!this.saveButton.isVisible() || !saveButton.isEnabled()) {
	      return false;
	    }

	    return !DigestUtil.digest(oldModel).equals(DigestUtil.digest(listCursor.getCurrentObject()));

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
	    
	    AsValFieldEditor majorCode = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "majorCode", "SF_VS_MAJOR");
	    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfJdRecordFileModel.COL_NAME), "name");
	    AsValFieldEditor isEnable = new AsValFieldEditor(LangTransMeta.translate(SfJdRecordFileModel.COL_IS_ENABLE), "isEnable", SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_STATUS);
	    AsValFieldEditor fileType = new AsValFieldEditor(LangTransMeta.translate(SfJdRecordFileModel.COL_FILE_TYPE), "fileType", SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE);
	    TextAreaFieldEditor desc = new TextAreaFieldEditor(LangTransMeta.translate(SfJdRecordFileModel.COL_DESCRIPTION), "description", 100, 1, 3);
	    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfJdRecordFileModel.COL_REMARK), "remark", 100, 3, 3);
	    FileFieldEditor file = new FileFieldEditor(LangTransMeta.translate(SfJdRecordFileModel.COL_FILE_NAME), "fileName", "fileId");
  

	    editorList.add(majorCode); 
	    editorList.add(name);  
	    editorList.add(file);  
	    editorList.add(fileType); 
	    editorList.add(isEnable);
	    editorList.add(desc); 
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

	   /* if (isDataChanged()) {

	      int num = JOptionPane.showConfirmDialog(this, "当前页面数据已修改，是否要保存", "保存确认", 0);

	      if (num == JOptionPane.YES_OPTION) {

	        if (!doSave()) {

	          return;

	        }

	      }

	    }
*/
	    this.parent.dispose();

	  }

	 

	}
