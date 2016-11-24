/**
 * 
 */
package com.ufgov.zc.client.sf.jdfees;

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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.component.table.fixedtable.JPageableFixedTable;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfChargeToTableModelConverter;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.JFuncToolBar;
import com.ufgov.zc.client.component.JTablePanel;
import com.ufgov.zc.client.component.button.AddButton;
import com.ufgov.zc.client.component.button.DeleteButton;
import com.ufgov.zc.client.component.button.EditButton;
import com.ufgov.zc.client.component.button.ExitButton;
import com.ufgov.zc.client.component.button.FuncButton;
import com.ufgov.zc.client.component.button.PrintButton;
import com.ufgov.zc.client.component.button.SaveButton;
import com.ufgov.zc.client.component.button.SubaddButton;
import com.ufgov.zc.client.component.button.SubdelButton;
import com.ufgov.zc.client.component.button.SubinsertButton;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.celleditor.TextCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.AsValComboBoxCellEditor;
import com.ufgov.zc.client.component.table.codecellrenderer.AsValCellRenderer;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldCellEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.MoneyFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.charge.ChargeStandardHandler;
import com.ufgov.zc.client.sf.charge.SfChargeEditPanel;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.util.SfUserSelectHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.console.model.AsEmp;
import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.publish.ISfChargeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.system.util.Utils;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfJdFeeEditPanel extends AbstractMainSubEditPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 4403093490841792209L;

  private static final Logger logger = Logger.getLogger(SfJdFeeEditPanel.class);

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private static String compoId = "SF_JD_FEES";

  protected FuncButton saveButton = new SaveButton();

  protected FuncButton addButton = new AddButton();

  protected FuncButton editButton = new EditButton();

  protected FuncButton deleteButton = new DeleteButton();

  private FuncButton exitButton = new ExitButton();

  public FuncButton printButton = new PrintButton();

  private SfCharge oldCharge;

  SfCharge currentBill;

  public SfJdFeeMainPanel listPanel;

  protected SfJdFeeEditPanel self = this;

  protected GkBaseDialog parent;

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private BillElementMeta mainBillElementMeta;

  protected JTablePanel detailTablePanel = new JTablePanel();

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfChargeServiceDelegate sfChargeServiceDelegate;

  public SfJdFeeEditPanel(GkBaseDialog parent, SfCharge currentBill, SfJdFeeMainPanel listPanel) {
    super(SfChargeEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(compoId));
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfChargeServiceDelegate = (ISfChargeServiceDelegate) ServiceFactory.create(ISfChargeServiceDelegate.class, "sfChargeServiceDelegate");
    mainBillElementMeta = BillElementMeta.getBillElementMetaWithoutNd(compoId);
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId),
      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));

    this.listPanel = listPanel;

    this.currentBill = currentBill;

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

    if (currentBill.getChargeId() != null) {
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;
      this.setEditingObject(currentBill);
    } else if (currentBill.getFees() != null) {//列表界面进来的新增，已经确定了entrust
      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;
      setDefaultValue(currentBill);
      this.setEditingObject(currentBill);
    }
    refreshSubData();
    setOldObject();
    setButtonStatus();
    updateFieldEditorsEditable();
  }

  protected void setOldObject() {

    oldCharge = (SfCharge) ObjectUtil.deepCopy(currentBill);

  }

  private void refreshSubData() {
    // TCJLODO Auto-generated method stub

    detailTablePanel.setTableModel(SfChargeToTableModelConverter.convertChargeDetailsTableData(currentBill.getFees().getEntrust()
      .getJdChargeDetaillst()));
    ZcUtil.translateColName(detailTablePanel.getTable(), SfChargeToTableModelConverter.getDetailInfo());
    setTablePorperty();
    addTableLisenter();
  }

  private void addTableLisenter() {
    // TCJLODO Auto-generated method stub

    /* final JPageableFixedTable table = detailTablePanel.getTable();
     final BeanTableModel model = (BeanTableModel) (table.getModel());

     model.addTableModelListener(new TableModelListener() {

       public void tableChanged(TableModelEvent e) {

         if (e.getType() == TableModelEvent.UPDATE) {

           if (e.getColumn() >= 0 && table.getSelectedRow() >= 0) {

             int k = table.getSelectedRow();
             if (SfChargeDetail.COL_PRICE.equals(model.getColumnIdentifier(e.getColumn()))
               || SfChargeDetail.COL_QUANTITY.equals(model.getColumnIdentifier(e.getColumn()))) {

               SfChargeDetail item = (SfChargeDetail) (model.getBean(table.convertRowIndexToModel(k)));

               BigDecimal price = item.getPrice() == null ? BigDecimal.ZERO : item.getPrice();
               BigDecimal quantity = item.getQuantity() == null ? BigDecimal.ZERO : item.getQuantity();

               item.setTotalPrice(price.multiply(quantity));

               BigDecimal num = BigDecimal.ZERO;
               for (int i = 0; i < table.getRowCount(); i++) {
                 SfChargeDetail row = (SfChargeDetail) (model.getBean(table.convertRowIndexToModel(i)));
                 if (row.getTotalPrice() != null) {
                   num = num.add(row.getTotalPrice());
                 }
               }
               currentBill.setTotalPrice(num);
               setEditingObject(currentBill);
             } else if (SfChargeDetail.COL_TOTAL_PRICE.equals(model.getColumnIdentifier(e.getColumn()))) {

               BigDecimal num = BigDecimal.ZERO;
               for (int i = 0; i < table.getRowCount(); i++) {
                 SfChargeDetail row = (SfChargeDetail) (model.getBean(table.convertRowIndexToModel(i)));
                 if (row.getTotalPrice() != null) {
                   num = num.add(row.getTotalPrice());
                 }
               }
               currentBill.setTotalPrice(num);
               setEditingObject(currentBill);

             }
           }
         }
       }

     });*/
  }

  private void setTablePorperty() {
    final JPageableFixedTable table = detailTablePanel.getTable();
    table.setDefaultEditor(String.class, new TextCellEditor());
    ChargeStandardHandler handler = new ChargeStandardHandler() {
      @Override
      public void excute(List selectedDatas) {
        BeanTableModel model = (BeanTableModel) table.getModel();
        int k = table.getSelectedRow();
        if (k < 0) {
          return;
        }

        int k2 = table.convertRowIndexToModel(k);
        SfChargeDetail detail = (SfChargeDetail) (model.getBean(k2));
        for (Object obj : selectedDatas) {
          SfChargeStandard standard = (SfChargeStandard) obj;
          detail.setChargeStandardId(standard.getChargeStandardId());
          detail.setChargeStandardName(standard.getName());
          detail.setPrice(standard.getPrice());
        }
        model.fireTableRowsUpdated(k, k);
      }
    };

    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("enable");
    ForeignEntityFieldCellEditor foreignExpertCodeEditor = new ForeignEntityFieldCellEditor(handler.getSqlId(), dto, 20, handler,
      handler.getColumNames(), "收费标准", "name");

    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_CHARGE_STANDARD_NAME, foreignExpertCodeEditor);
    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_PRICE_TYPE, new AsValComboBoxCellEditor(SfChargeDetail.SF_VS_CHARGE_PRICE_TYPE));
    SwingUtil.setTableCellRenderer(table, SfChargeDetail.COL_PRICE_TYPE, new AsValCellRenderer(SfChargeDetail.SF_VS_CHARGE_PRICE_TYPE));

    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_PRICE, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfChargeDetail.COL_PRICE, new NumberCellRenderer());

    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_TOTAL_PRICE, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfChargeDetail.COL_TOTAL_PRICE, new NumberCellRenderer());

    SwingUtil.setTableCellEditor(table, SfChargeDetail.COL_QUANTITY, new MoneyCellEditor(false));
    SwingUtil.setTableCellRenderer(table, SfChargeDetail.COL_QUANTITY, new NumberCellRenderer());
  }

  protected void updateFieldEditorsEditable() {
    Long processInstId = currentBill.getProcessInstId();
    if (processInstId != null && processInstId.longValue() > 0) {
      // 工作流的单据
      wfCanEditFieldMap = BillElementMeta.getWfCanEditField(currentBill, requestMeta);
      if ("cancel".equals(this.oldCharge.getStatus())) {// 撤销单据设置字段为不可编辑
        wfCanEditFieldMap = null;
      }

      for (AbstractFieldEditor editor : fieldEditors) {
        // 工作流中定义可编辑的字段
        //        System.out.println(editor.getFieldName());
        if (editor instanceof NewLineFieldEditor)
          continue;
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
          if ("fees.entrustCode".equals(editor.getFieldName()) || "fees.entrsutName".equals(editor.getFieldName())
            || "fees.totalFee".equals(editor.getFieldName()) || "fees.payedFee".equals(editor.getFieldName())
            || "fees.needFee".equals(editor.getFieldName()) || "cashierName".equals(editor.getFieldName())
            || "fees.entrustorName".equals(editor.getFieldName()) || "fees.majorName".equals(editor.getFieldName())
            || "fees.jdFzrName".equals(editor.getFieldName()) || "fees.jdFhrName".equals(editor.getFieldName())
          //|| "cashDate".equals(editor.getFieldName())
          ) {
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

    setWFSubTableEditable(detailTablePanel, false);
  }

  protected void setButtonStatus() {
    ListCursor listCursor = new ListCursor();
    listCursor.setDataList(new ArrayList());
    listCursor.getDataList().add(currentBill);
    listCursor.setCurrentObject(currentBill);
    setButtonStatus(currentBill, requestMeta, listCursor);

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
      bs.setButton(this.deleteButton);
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
      bs.setButton(this.printButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);
    }

    ZcUtil.setButtonEnable(this.btnStatusList, currentBill.getStatus(), this.pageStatus, getCompoId(), currentBill.getProcessInstId());

  }

  private void setDefaultValue(SfCharge Charge) {
    // TCJLODO Auto-generated method stub
    Charge.setStatus(ZcSettingConstants.WF_STATUS_DRAFT);
    Charge.setNd(this.requestMeta.getSvNd());
    Charge.setInputDate(SfUtil.getSysDate());
    Charge.setInputor(requestMeta.getSvUserID());
    Charge.setCashDate(SfUtil.getSysDate());
    Charge.setCashier(this.requestMeta.getSvUserID());
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#initToolBar(com.ufgov.zc.client.component.JFuncToolBar)
   */
  @Override
  public void initToolBar(JFuncToolBar toolBar) {
    // TCJLODO Auto-generated method stub

    toolBar.setModuleCode("SF");

    toolBar.setCompoId(getCompoId());

    //    toolBar.add(addButton);

    toolBar.add(editButton);

    toolBar.add(saveButton);

    toolBar.add(deleteButton);

    //    toolBar.add(printButton);

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

        doPrintButton();

      }

    });
  }

  protected void doPrintButton() {
    // TCJLODO Auto-generated method stub

  }

  protected void doDelete() {
    // TCJLODO Auto-generated method stub

    requestMeta.setFuncId(deleteButton.getFuncId());

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        sfChargeServiceDelegate.deleteByPrimaryKeyFN(currentBill.getChargeId(), requestMeta);
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        success = false;
        errorInfo += e.getMessage();
      }

      if (success) {
        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        refreshListPanel();
        doExit();
      } else {
        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  protected boolean doSave() {
    // TCJLODO Auto-generated method stub

    if (!checkBeforeSave()) {

      return false;

    }

    boolean success = true;

    String errorInfo = "";

    try {

      requestMeta.setFuncId(saveButton.getFuncId());

      //      System.out.println("before=" + inData.getCoCode() + inData.getCoName());

      currentBill = sfChargeServiceDelegate.saveFN(currentBill, this.requestMeta);
      currentBill.setFees(oldCharge.getFees());

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
    // TCJLODO Auto-generated method stub
    listPanel.refreshed();
  }

  protected void doExit() {
    // TCJLODO Auto-generated method stub
    this.parent.dispose();
  }

  protected void doEdit() {
    // TCJLODO Auto-generated method stub

    this.pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
    updateFieldEditorsEditable();
    setButtonStatus();
  }

  protected void doAdd() {
    // TCJLODO Auto-generated method stub

  }

  /**

   * 保存前校验

   * @param cpApply

   * @return

   */

  protected boolean checkBeforeSave() {
    List mainNotNullList = mainBillElementMeta.getNotNullBillElement();
    StringBuilder errorInfo = new StringBuilder();
    String mainValidateInfo = ZcUtil.validateBillElementNull(currentBill, mainNotNullList);
    if (mainValidateInfo.length() != 0) {
      errorInfo.append("\n").append(mainValidateInfo.toString());
    }
    if (errorInfo.length() != 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createFieldEditors()
   */
  @Override
  public List<AbstractFieldEditor> createFieldEditors() {
    // TCJLODO Auto-generated method stub

    List<AbstractFieldEditor> editorList = new ArrayList<AbstractFieldEditor>();

    SfEntrustHandler entrustHandler = new SfEntrustHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfEntrust entrust = (SfEntrust) obj;
          currentBill.setEntrustId(entrust.getEntrustId());
          currentBill.setEntrustCode(entrust.getCode());
          currentBill.setName(entrust.getName() + "鉴定费用");
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        currentBill.setEntrustId(null);
        currentBill.setEntrustCode(null);
        currentBill.setName(null);
        setEditingObject(currentBill);
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_CHARGE");
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfCharge.COL_ENTRUST_CODE), "fees.entrustCode");

    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_NAME), "fees.entrsutName");
    MoneyFieldEditor totalFee = new MoneyFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_CHARGE), "fees.totalFee");
    MoneyFieldEditor payedFee = new MoneyFieldEditor("已交金额", "fees.payedFee");
    MoneyFieldEditor needFee = new MoneyFieldEditor("待交金额", "fees.needFee");
    MoneyFieldEditor benciFee = new MoneyFieldEditor("本次交费金额", "totalPrice");
    TextFieldEditor payer = new TextFieldEditor(LangTransMeta.translate(SfCharge.COL_PAYER), "payer");
    TextFieldEditor payerTel = new TextFieldEditor(LangTransMeta.translate(SfCharge.COL_PAYER_TEL), "payerTel");
    SfUserSelectHandler userHandler = new SfUserSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          AsEmp user = (AsEmp) obj;
          currentBill.setCashier(user.getUserId());
          setEditingObject(currentBill);
        }
      }

      public void afterClear() {
        currentBill.setCashier(null);
        setEditingObject(currentBill);
      }
    };
    dto = new ElementConditionDto();
    dto.setCoCode("000");
    dto.setNd(this.requestMeta.getSvNd());
    ForeignEntityFieldEditor cashier = new ForeignEntityFieldEditor(userHandler.getSqlId(), dto, 20, userHandler, userHandler.getColumNames(),
      LangTransMeta.translate(SfCharge.COL_CASHIER), "cashierName");
    //    TextFieldEditor cashier = new TextFieldEditor(LangTransMeta.translate(SfCharge.COL_CASHIER), "cashierName");
    DateFieldEditor cashDate = new DateFieldEditor(LangTransMeta.translate(SfCharge.COL_CASH_DATE), "cashDate");
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfCharge.COL_REMARK), "remark", 100, 2, 5);

    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfCharge.COL_INPUTOR), "inputor");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfCharge.COL_INPUT_DATE), "inputDate");

    TextFieldEditor entrustor = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_ENTRUSTOR_NAME), "fees.entrustorName");
    TextFieldEditor major = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "fees.majorName");
    TextFieldEditor jdFzr = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_FZR), "fees.jdFzrName");
    TextFieldEditor jdFhr = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_FHR), "fees.jdFhrName");

    editorList.add(entrust);
    editorList.add(name);
    editorList.add(entrustor);

    editorList.add(major);
    editorList.add(jdFzr);
    editorList.add(jdFhr);

    editorList.add(totalFee);
    editorList.add(payedFee);
    editorList.add(needFee);

    editorList.add(benciFee);
    editorList.add(payer);
    editorList.add(payerTel);

    editorList.add(cashier);
    editorList.add(cashDate);
    editorList.add(new NewLineFieldEditor());

    editorList.add(remark);

    return editorList;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {
    // TCJLODO Auto-generated method stub

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

    //    detailTablePanel.add(detailBtnBar, BorderLayout.SOUTH);

    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfChargeDetail item = new SfChargeDetail();
        setChargeDetailDefaultValue(item);
        int rowNum = addSub(detailTablePanel, item);
        detailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SfChargeDetail item = new SfChargeDetail();
        setChargeDetailDefaultValue(item);
        int rowNum = insertSub(detailTablePanel, item);
        detailTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(detailTablePanel);
      }
    });
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.setBorder(BorderFactory.createTitledBorder("收费明细项目"));
    p.add(detailTablePanel, BorderLayout.CENTER);
    return p;
  }

  protected void setChargeDetailDefaultValue(SfChargeDetail item) {
    // TCJLODO Auto-generated method stub

  }

  protected String getCompoId() {
    return compoId;
  }
}
