/**
 * 
 */
package com.ufgov.zc.client.sf.sjin;

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
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.MyTableModel;
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
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.MoneyFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfSj;
import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.sf.publish.ISfSjInServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.DigestUtil;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfSjInEditPanel extends AbstractMainSubEditPanel {

  private static final Logger logger = Logger.getLogger(SfSjInEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_SJ_IN";

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

  protected ListCursor<SfSjIn> listCursor;

  private SfSjIn oldMajor;

  public SfSjInListPanel listPanel;

  protected SfSjInEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  private ElementConditionDto eaccDto = new ElementConditionDto();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate ;  
  private ISfSjInServiceDelegate sfSjInServiceDelegate ;

  protected JTablePanel detailTablePanel = new JTablePanel();
  
  public SfSjInEditPanel(SfSjInDialog parent, ListCursor listCursor, String tabStatus, SfSjInListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfSjIn.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate");
    sfSjInServiceDelegate = (ISfSjInServiceDelegate) ServiceFactory.create(ISfSjInServiceDelegate.class,"sfSjInServiceDelegate");
    
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

    SfSjIn bill = (SfSjIn) listCursor.getCurrentObject();

    if (bill != null && bill.getInId()!=null) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

      bill = sfSjInServiceDelegate.selectByPrimaryKey(bill.getInId(), this.requestMeta);

      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      bill = new SfSjIn();
      
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

  private void setDefaultValue(SfSjIn bill) {
    bill.setNd(requestMeta.getSvNd());
    bill.setCoCode(requestMeta.getSvCoCode());
    bill.setInputor(requestMeta.getSvUserID());
    bill.setInDate(SfUtil.getSysDate());
  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub 
  }


  private void setTablePorperty() {
    JPageableFixedTable table = detailTablePanel.getTable();
    SwingUtil.setTableCellRenderer(table, SfJdPerson.COL_SEX, new AsValCellRenderer(SfElementConstants.VS_SEX));  
    SwingUtil.setTableCellRenderer(table, SfJdPerson.COL_STATUS, new AsValCellRenderer(SfJdPerson.SF_VS_JD_PERSON_STATUS));  
    SwingUtil.setTableCellRenderer(table, SfJdPerson.COL_BIRTHDAY, new DateCellRenderer("yyyy-MM-dd"));  
    
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
    SfSjIn bill = (SfSjIn) listCursor.getCurrentObject();
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

    SfSjIn bill = (SfSjIn) this.listCursor.getCurrentObject();
     
    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), bill.getProcessInstId());

  }

  protected void setOldObject() {

    oldMajor = (SfSjIn) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

      SfSjIn inData = (SfSjIn) this.listCursor.getCurrentObject();

//      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      SfSjIn bill = sfSjInServiceDelegate.saveFN(inData, this.requestMeta);

      listCursor.setCurrentObject(bill);

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
    SfSjIn bill = (SfSjIn) this.listCursor.getCurrentObject();
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

    SfSjIn bill = (SfSjIn) this.listCursor.getCurrentObject();
 
    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

    if (num == JOptionPane.YES_OPTION) {

      boolean success = true;

      String errorInfo = "";

      try {

        requestMeta.setFuncId(deleteButton.getFuncId());

        sfSjInServiceDelegate.deleteByPrimaryKeyFN(bill.getInId(), requestMeta);

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

     String sjCols[]=new String[]{LangTransMeta.translate(SfSj.COL_NAME),
      LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID),
      LangTransMeta.translate(SfSj.COL_SJ_GROUP)};
    IForeignEntityHandler sfSjHandler=new IForeignEntityHandler(){
      private final String columNames[]=new String[]{LangTransMeta.translate(SfSj.COL_NAME),
        LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID),
        LangTransMeta.translate(SfSj.COL_SJ_GROUP)};
      @Override
      public void excute(List selectedDatas) {
        SfSjIn inData = (SfSjIn) listCursor.getCurrentObject();
        if(selectedDatas!=null && selectedDatas.size()>0){
          SfSj sj=(SfSj)selectedDatas.get(0);
          inData.setSj(sj);
          setEditingObject(inData);
        }
      }

      public void afterClear() {
        SfSjIn inData = (SfSjIn) listCursor.getCurrentObject();
        inData.setSj(new SfSj());
        setEditingObject(inData);
      }
      @Override
      public boolean isMultipleSelect() {
        return false;
      }

      @Override
      public TableModel createTableModel(List showDatas) {
        Object data[][] = new Object[showDatas.size()][columNames.length];
        for (int i = 0; i < showDatas.size(); i++) {
          SfSj rowData = (SfSj) showDatas.get(i);
          int col = 0;
          data[i][col++] = rowData.getName();
          data[i][col++] = rowData.getProductor().getName();
          data[i][col++] = rowData.getSjGroup().getGroupName();
        }

        MyTableModel model = new MyTableModel(data, columNames) {
          @Override
          public boolean isCellEditable(int row, int colum) {
            return false;
          }
        };
        return model;
      }
      
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setStatus(SfSjSupplier.VS_SF_SUPPLIER_STATUS_ENABLE); 
    dto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor sj = new ForeignEntityFieldEditor("com.ufgov.zc.server.sf.dao.SfSjMapper.selectMainDataLst", dto, 20, sfSjHandler, sjCols, 
        LangTransMeta.translate(SfSj.COL_NAME), "sj.name"); 
      
    TextFieldEditor packSpec = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PACK_SPEC), "sj.packSpec");
    AsValFieldEditor unit = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_UNIT), "sj.unit", SfSj.VS_SF_SJ_UNIT);
    AsValFieldEditor storeCondition = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_STORE_CONDITION), "sj.storeCondition", SfSj.VS_SF_SJ_STORE_CONDITION);
//    TextFieldEditor pizhunDocCode = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PIZHUN_DOC_CODE), "sj.pizhunDocCode"); 
    TextFieldEditor productor = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID), "sj.productor.name");  
    
    AsValFieldEditor sjGroup = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_SJ_GROUP), "sj.sjGroup", SfSj.SF_VS_SJ_GROUP); 
    TextFieldEditor shijiPihao = new TextFieldEditor(LangTransMeta.translate(SfSjIn.COL_SHIJI_PIHAO), "shijiPihao");  
    MoneyFieldEditor price = new MoneyFieldEditor(LangTransMeta.translate(SfSjIn.COL_PRICE), "price"); 
    MoneyFieldEditor amount = new MoneyFieldEditor(LangTransMeta.translate(SfSjIn.COL_AMOUNT), "amount");
    MoneyFieldEditor totalNum = new MoneyFieldEditor(LangTransMeta.translate(SfSjIn.COL_TOTAL_NUM), "totalNum");
    DateFieldEditor expiryDate = new DateFieldEditor(LangTransMeta.translate(SfSjIn.COL_EXPIRY_DATE), "expiryDate");
    DateFieldEditor inDate = new DateFieldEditor(LangTransMeta.translate(SfSjIn.COL_IN_DATE), "inDate");
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfSjIn.COL_INPUTOR), "inputorName");  
    TextFieldEditor buyCode = new TextFieldEditor(LangTransMeta.translate(SfSjIn.COL_BUY_CODE), "buyCode"); 

    String expertSelectBillColumNames[] = { LangTransMeta.translate(SfSjSupplier.COL_NAME), LangTransMeta.translate(SfSjSupplier.COL_LINK_MAN), 
        LangTransMeta.translate(SfSjSupplier.COL_TEL), LangTransMeta.translate(SfSjSupplier.COL_ADDRESS) };
    SupplierSelectBillHandler productorHandler = new SupplierSelectBillHandler(expertSelectBillColumNames);
    dto = new ElementConditionDto();
    dto.setStatus(SfSjSupplier.VS_SF_SUPPLIER_STATUS_ENABLE);
    dto.setDattr2(SfSjSupplier.VS_SF_SUPPLIER_TYPE_GYS);
    dto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor supplier = new ForeignEntityFieldEditor("com.ufgov.zc.server.sf.dao.SfSjSupplierMapper.selectMainDataLst", dto, 20, productorHandler, expertSelectBillColumNames, 
        LangTransMeta.translate(SfSjIn.COL_SUPPLIER_ID), "supplier.name");
    
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfSjIn.COL_REMARK), "remark", 100, 3, 5);
    

    editorList.add(sj);
    editorList.add(amount);
    editorList.add(unit);

    editorList.add(price);
    editorList.add(totalNum);
    editorList.add(expiryDate);    
    
    editorList.add(productor);
    editorList.add(supplier);
    editorList.add(sjGroup);
    
    editorList.add(packSpec);
    editorList.add(storeCondition);
    editorList.add(shijiPihao);

    editorList.add(buyCode);    
    editorList.add(inputor);    
    editorList.add(inDate);
    
    editorList.add(remark);
     
     
    
    return editorList;

  }


  private class SupplierSelectBillHandler implements IForeignEntityHandler {

    private final String columNames[];

    public SupplierSelectBillHandler(String columNames[]) {
      this.columNames = columNames;
    }

    public void excute(List selectedDatas) {
      for (Object object : selectedDatas) {
        SfSjSupplier productor = (SfSjSupplier) object; 
        SfSjIn inData = (SfSjIn) listCursor.getCurrentObject();
        inData.setSupplier(productor);
        setEditingObject(inData);
        break;
      }
    }

    public void afterClear() { 
      SfSjIn inData = (SfSjIn) listCursor.getCurrentObject();
      inData.setSupplier(new SfSjSupplier());
    }

    public TableModel createTableModel(List showDatas) {

      Object data[][] = new Object[showDatas.size()][columNames.length];

      for (int i = 0; i < showDatas.size(); i++) {

        SfSjSupplier rowData = (SfSjSupplier) showDatas.get(i);

        int col = 0;

        data[i][col++] = rowData.getName();
        data[i][col++] = rowData.getLinkMan();
        data[i][col++] = rowData.getTel();
        data[i][col++] = rowData.getAddress();

      }

      MyTableModel model = new MyTableModel(data, columNames) {

        @Override
        public boolean isCellEditable(int row, int colum) {

          return false;

        }

      };

      return model;

    }

    public boolean isMultipleSelect() {

      return false;

    }

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

    this.parent.dispose();

  }

 

}
