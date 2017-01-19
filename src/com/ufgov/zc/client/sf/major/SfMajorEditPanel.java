/**
 * 
 */
package com.ufgov.zc.client.sf.major;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.ufgov.zc.client.common.converter.sf.SfMajorToTableModelConverter;
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
import com.ufgov.zc.client.component.zc.fieldeditor.AutoNumFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.util.SfMajorSelectHandler;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.publish.ISfMajorServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfMajorEditPanel extends AbstractMainSubEditPanel {

  private static final Logger logger = Logger.getLogger(SfMajorEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_MAJOR";

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

  protected ListCursor<SfMajor> listCursor;

  private SfMajor oldMajor;

  public SfMajorListPanel listPanel;

  protected SfMajorEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  private ElementConditionDto eaccDto = new ElementConditionDto();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate ;  
  private ISfMajorServiceDelegate sfMajorServiceDelegate ;

  protected JTablePanel detailTablePanel = new JTablePanel();
  
  ElementConditionDto majorParentDto=new ElementConditionDto();
  
  public SfMajorEditPanel(SfMajorDialog parent, ListCursor listCursor, String tabStatus, SfMajorListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfMajorEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd("SF_MAJOR");
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate");
    sfMajorServiceDelegate = (ISfMajorServiceDelegate) ServiceFactory.create(ISfMajorServiceDelegate.class,"sfMajorServiceDelegate");
    
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

    SfMajor major = (SfMajor) listCursor.getCurrentObject();

    if (major != null && !"".equals(ZcUtil.safeString(major.getMajorCode()))) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

      major = sfMajorServiceDelegate.selectByPrimaryKey(major.getMajorCode(), this.requestMeta);
      
            
      listCursor.setCurrentObject(major);
      this.setEditingObject(major);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      major = new SfMajor();
      
      major.setParent(new SfMajor());

      listCursor.getDataList().add(major);

      listCursor.setCurrentObject(major);

      this.setEditingObject(major);

    }

    refreshSubData();
    
    setOldObject();

    setButtonStatus();

    updateFieldEditorsEditable();

  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub
    SfMajor major = (SfMajor) listCursor.getCurrentObject();
    detailTablePanel.setTableModel(SfMajorToTableModelConverter.convertJdPersonsTableData(major.getJdPersonLst()));
    ZcUtil.translateColName(detailTablePanel.getTable(), SfMajorToTableModelConverter.getDetailInfo());
    setTablePorperty();
  }


  private void setTablePorperty() {
    JPageableFixedTable table = detailTablePanel.getTable();
    SwingUtil.setTableCellRenderer(table, SfJdPerson.COL_SEX, new AsValCellRenderer(SfElementConstants.VS_SEX));  
    SwingUtil.setTableCellRenderer(table, SfJdPerson.COL_STATUS, new AsValCellRenderer(SfJdPerson.SF_VS_JD_PERSON_STATUS));  
    SwingUtil.setTableCellRenderer(table, SfJdPerson.COL_BIRTHDAY, new DateCellRenderer("yyyy-MM-dd"));  
    
  }

  protected void updateFieldEditorsEditable() {

      for (AbstractFieldEditor editor : fieldEditors) {
        if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_NEW)) {
          if ("majorName".equals(editor.getFieldName())||"parent.majorCode".equals(editor.getFieldName())){
            editor.setEnabled(true);
          }else{
            editor.setEnabled(false);
          }
        } else if(pageStatus.equals(ZcSettingConstants.PAGE_STATUS_EDIT)) {
          if ("majorName".equals(editor.getFieldName())){
            editor.setEnabled(true);
          }else{
            editor.setEnabled(false);
          }
        }else{
          editor.setEnabled(false);
        }
      }
    
      
  }

 

  protected void setButtonStatus() {
    SfMajor major = (SfMajor) listCursor.getCurrentObject();
    setButtonStatus(major, requestMeta, this.listCursor);
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

    SfMajor major = (SfMajor) this.listCursor.getCurrentObject();
     
    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), major.getProcessInstId());

  }

  protected void setOldObject() {

    oldMajor = (SfMajor) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

      SfMajor inData = (SfMajor) this.listCursor.getCurrentObject();

//      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      SfMajor major = sfMajorServiceDelegate.saveFN(inData, this.requestMeta);

      listCursor.setCurrentObject(major);

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
    SfMajor major = (SfMajor) this.listCursor.getCurrentObject();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(major, mainNotNullList);     
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

    SfMajor major = (SfMajor) this.listCursor.getCurrentObject();

    if(sfMajorServiceDelegate.isUsing(major.getMajorCode(), requestMeta)){
      JOptionPane.showMessageDialog(this, "已经被使用，不能删除 ！\n" , "错误", JOptionPane.ERROR_MESSAGE);
      return;
    }
    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

    if (num == JOptionPane.YES_OPTION) {

      boolean success = true;

      String errorInfo = "";

      try {

        requestMeta.setFuncId(deleteButton.getFuncId());

        sfMajorServiceDelegate.deleteByPrimaryKeyFN(major.getMajorCode(), requestMeta);

      } catch (Exception e) {

        logger.error(e.getMessage(), e);

        success = false;

        errorInfo += e.getMessage();

      }

      if (success) {

        this.listCursor.removeCurrentObject();

        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

//        this.refreshData();
        parent.dispose();

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


    AutoNumFieldEditor majorCode = new AutoNumFieldEditor(LangTransMeta.translate(SfMajor.COL_MAJOR_CODE), "majorCode");
    TextFieldEditor majorName = new TextFieldEditor(LangTransMeta.translate(SfMajor.COL_MAJOR_NAME), "majorName");

    SfMajorSelectHandler jdFzrHandler = new SfMajorSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfMajor cur = listCursor.getCurrentObject();
          SfMajor p = (SfMajor) selectedDatas.get(0);
          cur.setParent(p);
          setEditingObject(cur);
        }
      }

      public void afterClear() {
        SfMajor currentBill = (SfMajor) listCursor.getCurrentObject();
        currentBill.setParent(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {       
        return true;
      }
    }; 
    majorParentDto.setDattr1("getParentLst");
    ForeignEntityFieldEditor parentMajor = new ForeignEntityFieldEditor(jdFzrHandler.getSqlId(), majorParentDto, 20, jdFzrHandler, jdFzrHandler.getColumNames(),
      "上级代码", "parent.majorCode");
    TextFieldEditor  parentMajorName = new TextFieldEditor("上级名称", "parent.majorName");

    editorList.add(majorCode);
    editorList.add(majorName);

    editorList.add(parentMajor);
    editorList.add(parentMajorName);
    
    return editorList;

  }


  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {

    JTabbedPane itemTabPane = new JTabbedPane();

    detailTablePanel.init();
    
    detailTablePanel.getTable().setPreferredScrollableViewportSize(new Dimension(200, 200));
    
    detailTablePanel.setPanelId(this.getClass().getName() + "_detailTablePanel");

    detailTablePanel.getSearchBar().setVisible(false);

    detailTablePanel.setTablePreferencesKey(this.getClass().getName() + "__detailTable");

    detailTablePanel.getTable().setShowCheckedColumn(true);

    detailTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));

    itemTabPane.addTab("相关检验鉴定人员", detailTablePanel);
    
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
