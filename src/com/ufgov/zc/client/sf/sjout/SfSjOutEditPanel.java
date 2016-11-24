/**
 * 
 */
package com.ufgov.zc.client.sf.sjout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
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
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfSj;
import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.sf.model.SfSjOut;
import com.ufgov.zc.common.sf.model.SfSjSupplier;
import com.ufgov.zc.common.sf.publish.ISfSjOutServiceDelegate;
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
public class SfSjOutEditPanel extends AbstractMainSubEditPanel {

  private static final Logger logger = Logger.getLogger(SfSjOutEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_SJ_OUT";

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

  protected ListCursor<SfSjOut> listCursor;

  private SfSjOut oldMajor;

  public SfSjOutListPanel listPanel;

  protected SfSjOutEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  private ElementConditionDto eaccDto = new ElementConditionDto();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate ;  
  private ISfSjOutServiceDelegate sfSjOutServiceDelegate ;

  protected JTablePanel detailTablePanel = new JTablePanel();
  
  public SfSjOutEditPanel(SfSjOutDialog parent, ListCursor listCursor, String tabStatus, SfSjOutListPanel listPanel) {
    // TCJLODO Auto-generated constructor stub
    super(SfSjOut.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate");
    sfSjOutServiceDelegate = (ISfSjOutServiceDelegate) ServiceFactory.create(ISfSjOutServiceDelegate.class,"sfSjOutServiceDelegate");
    
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

    SfSjOut bill = (SfSjOut) listCursor.getCurrentObject();

    if (bill != null && bill.getOutId()!=null) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

      bill = sfSjOutServiceDelegate.selectByPrimaryKey(bill.getOutId(), this.requestMeta);

      listCursor.setCurrentObject(bill);
      this.setEditingObject(bill);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      bill = new SfSjOut();
      
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

  protected void setDefaultValue(SfSjOut bill) {
    bill.setNd(requestMeta.getSvNd());
    bill.setCoCode(requestMeta.getSvCoCode());
    bill.setInputor(requestMeta.getSvUserID());
    bill.setInputDate(SfUtil.getSysDate());
    bill.setOutDate(SfUtil.getSysDate());
    bill.setOutType(SfSjOut.SF_VS_SJ_OUT_TYPE_USED);
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
    SfSjOut bill = (SfSjOut) listCursor.getCurrentObject();
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

    SfSjOut bill = (SfSjOut) this.listCursor.getCurrentObject();
     
    ZcUtil.setButtonEnable(this.btnStatusList, null, this.pageStatus, getCompoId(), bill.getProcessInstId());

  }

  protected void setOldObject() {

    oldMajor = (SfSjOut) ObjectUtil.deepCopy(listCursor.getCurrentObject());

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

      SfSjOut bill = (SfSjOut) this.listCursor.getCurrentObject();

//      System.out.println("before=" + bill.getCoCode() + bill.getCoName());

      bill = sfSjOutServiceDelegate.saveFN(bill, this.requestMeta);

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
      reGetSjInFreeAmount();

    }

    return success;

  }

  /**
   * 重新获取试剂的可用数量
   */
  private void reGetSjInFreeAmount() {
    SfSjOut bill = (SfSjOut) this.listCursor.getCurrentObject();
    ElementConditionDto dto=new ElementConditionDto();
    dto.setCoCode(requestMeta.getSvCoCode());
    dto.setSfId(bill.getSjin().getInId());
    
    SfSjIn sjin=(SfSjIn) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfSjInMapper.getCanOutSjLst", dto, requestMeta);
    bill.setSjin(sjin==null?new SfSjIn():sjin);
    setEditingObject(bill);
  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    SfSjOut bill = (SfSjOut) this.listCursor.getCurrentObject();
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

    SfSjOut bill = (SfSjOut) this.listCursor.getCurrentObject();
 
    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

    if (num == JOptionPane.YES_OPTION) {

      boolean success = true;

      String errorInfo = "";

      try {

        requestMeta.setFuncId(deleteButton.getFuncId());

        sfSjOutServiceDelegate.deleteByPrimaryKeyFN(bill.getOutId(), requestMeta);

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

     final String sjCols[]=new String[]{LangTransMeta.translate(SfSj.COL_NAME),
       "可用数量",
       LangTransMeta.translate(SfSj.COL_UNIT),
       LangTransMeta.translate(SfSjIn.COL_IN_DATE),
       LangTransMeta.translate(SfSj.COL_SJ_GROUP),
      LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID),
      LangTransMeta.translate(SfSjIn.COL_SUPPLIER_ID)};
    IForeignEntityHandler sfSjHandler=new IForeignEntityHandler(){
      
      @Override
      public void excute(List selectedDatas) {
        SfSjOut bill = (SfSjOut) listCursor.getCurrentObject();
        if(selectedDatas!=null && selectedDatas.size()>0){
          SfSjIn sj=(SfSjIn)selectedDatas.get(0);
          bill.setSjin(sj);
          setEditingObject(bill);
        }
      }

      public void afterClear() {
        SfSjOut bill = (SfSjOut) listCursor.getCurrentObject();
        bill.setSjin(new SfSjIn());
        setEditingObject(bill);
      }
      @Override
      public boolean isMultipleSelect() {
        return false;
      }

      @Override
      public TableModel createTableModel(List showDatas) {
        Object data[][] = new Object[showDatas.size()][sjCols.length];
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < showDatas.size(); i++) {
          SfSjIn rowData = (SfSjIn) showDatas.get(i);
          int col = 0;
          data[i][col++] = rowData.getSj().getName();
          data[i][col++] =rowData.getAmount();
          data[i][col++] ="";  
          data[i][col++] =rowData.getInDate() == null ? null : df.format(rowData.getInDate()); 
          data[i][col++] =rowData.getSj().getSjGroup().getGroupName();
          data[i][col++] = rowData.getSj().getProductor().getName();
          data[i][col++] = rowData.getSupplier().getName();  
        }

        MyTableModel model = new MyTableModel(data, sjCols) {
          @Override
          public boolean isCellEditable(int row, int colum) {
            return false;
          }
        };
        return model;
      }
      
    };
    ElementConditionDto dto = new ElementConditionDto(); 
    dto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor sj = new ForeignEntityFieldEditor("com.ufgov.zc.server.sf.dao.SfSjInMapper.getCanOutSjLst", dto, 20, sfSjHandler, sjCols, 
        LangTransMeta.translate(SfSj.COL_NAME), "sjin.sj.name"); 
    MoneyFieldEditor canUseAmount = new MoneyFieldEditor(false,"可用数量", "sjin.amount"); 
    AsValFieldEditor unit = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_UNIT), "sjin.sj.unit", SfSj.VS_SF_SJ_UNIT);
      
    TextFieldEditor packSpec = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PACK_SPEC), "sjin.sj.packSpec");
    AsValFieldEditor storeCondition = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_STORE_CONDITION), "sjin.sj.storeCondition", SfSj.VS_SF_SJ_STORE_CONDITION);
//    TextFieldEditor pizhunDocCode = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PIZHUN_DOC_CODE), "sj.pizhunDocCode"); 
    TextFieldEditor productor = new TextFieldEditor(LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID), "sjin.sj.productor.name");  
    
    AsValFieldEditor sjGroup = new AsValFieldEditor(LangTransMeta.translate(SfSj.COL_SJ_GROUP), "sjin.sj.sjGroup", SfSj.SF_VS_SJ_GROUP); 
    TextFieldEditor shijiPihao = new TextFieldEditor(LangTransMeta.translate(SfSjIn.COL_SHIJI_PIHAO), "sjin.shijiPihao");  
    MoneyFieldEditor price = new MoneyFieldEditor(LangTransMeta.translate(SfSjIn.COL_PRICE), "sjin.price"); 
    MoneyFieldEditor amount = new MoneyFieldEditor(LangTransMeta.translate(SfSjOut.COL_AMOUNT), "amount"); 
    DateFieldEditor expiryDate = new DateFieldEditor(LangTransMeta.translate(SfSjIn.COL_EXPIRY_DATE), "sjin.expiryDate");
    DateFieldEditor outDate = new DateFieldEditor(LangTransMeta.translate(SfSjOut.COL_OUT_DATE), "outDate");
    DateFieldEditor inDate = new DateFieldEditor(LangTransMeta.translate(SfSjIn.COL_IN_DATE), "sjin.inDate");
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfSjOut.COL_INPUTOR), "inputorName"); 
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfSjOut.COL_INPUT_DATE), "inputDate"); 
    TextFieldEditor supplier = new TextFieldEditor(LangTransMeta.translate(SfSjIn.COL_SUPPLIER_ID), "sjin.supplier.name");    


    SfJdPersonSelectHandler jdPersonHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfSjOut cur = listCursor.getCurrentObject();
          SfJdPerson user = (SfJdPerson) selectedDatas.get(0);
          cur.setProposer(user.getAccount()); 
          setEditingObject(cur);
        }
      }

      public void afterClear() {
        SfSjOut currentBill = (SfSjOut) listCursor.getCurrentObject();
        currentBill.setProposer(null);
        setEditingObject(currentBill);
      }

      public boolean beforeSelect(ElementConditionDto dto) {        
        return true;
      }
    };
    dto=new ElementConditionDto();
    dto.setNd(requestMeta.getSvNd());
    dto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor proposer = new ForeignEntityFieldEditor(jdPersonHandler.getSqlId(), dto, 20, jdPersonHandler, jdPersonHandler.getColumNames(),
      LangTransMeta.translate(SfSjOut.COL_PROPOSER), "proposerName");
    
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfSjOut.COL_REMARK), "remark", 100, 3, 5);
    

    editorList.add(sj);
    editorList.add(canUseAmount);
    editorList.add(unit);

    editorList.add(amount);
    editorList.add(proposer);
    editorList.add(outDate);

    editorList.add(inDate); 
    editorList.add(expiryDate); 
    editorList.add(shijiPihao);
    
    editorList.add(productor);
    editorList.add(supplier);     
    editorList.add(packSpec);    
//    editorList.add(storeCondition);
  
    
    editorList.add(sjGroup);
    editorList.add(inputor);  
    editorList.add(inputDate);
    
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
