/**
 * 
 */
package com.ufgov.zc.client.sf.sb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.ZcFaCardToTableModelConverter;
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
import com.ufgov.zc.client.component.button.NextButton;
import com.ufgov.zc.client.component.button.PreviousButton;
import com.ufgov.zc.client.component.button.PrintButton;
import com.ufgov.zc.client.component.button.SaveButton;
import com.ufgov.zc.client.component.button.SendButton;
import com.ufgov.zc.client.component.button.SubaddButton;
import com.ufgov.zc.client.component.button.SubdelButton;
import com.ufgov.zc.client.component.button.SubinsertButton;
import com.ufgov.zc.client.component.button.SuggestAuditPassButton;
import com.ufgov.zc.client.component.button.TraceButton;
import com.ufgov.zc.client.component.button.UnauditButton;
import com.ufgov.zc.client.component.button.UntreadButton;
import com.ufgov.zc.client.component.sf.fieldeditor.ZcFaCardTypeTreeSelectEditor;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.celleditor.DateCellEditor;
import com.ufgov.zc.client.component.table.celleditor.IntCellEditor;
import com.ufgov.zc.client.component.table.celleditor.MoneyCellEditor;
import com.ufgov.zc.client.component.table.cellrenderer.DateCellRenderer;
import com.ufgov.zc.client.component.table.cellrenderer.IntCellRenderer;
import com.ufgov.zc.client.component.table.cellrenderer.NumberCellRenderer;
import com.ufgov.zc.client.component.table.codecelleditor.FileCellEditor;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.MoneyFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.commonbiz.fieldmap.FieldMapRegister;
import com.ufgov.zc.common.sf.model.ZcFaCard;
import com.ufgov.zc.common.sf.model.ZcFaCardDoc;
import com.ufgov.zc.common.sf.model.ZcFaCardField;
import com.ufgov.zc.common.sf.model.ZcFaCardStyle;
import com.ufgov.zc.common.sf.model.ZcFaCardSub;
import com.ufgov.zc.common.sf.publish.IZcFaCardServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.WFConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.model.ZcBaseBill;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class ZcFaCardEditPanel extends AbstractMainSubEditPanel {

  private static Logger log = Logger.getLogger(ZcFaCardEditPanel.class);

  protected ListCursor<ZcFaCard> listCursor;

  private ZcFaCardListPanel listPanel;

  protected GkBaseDialog parent;

  protected static String compoId = "SF_FA_CARD";

  //非空字段集合
  private List<String> notNullFieldLst = new ArrayList<String>();

  //只读字段集合
  private List<String> readOnlyFieldLst = new ArrayList<String>();

  protected String pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

  protected RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private IZcFaCardServiceDelegate zcFaCardServiceDelegate = (IZcFaCardServiceDelegate) ServiceFactory.create(IZcFaCardServiceDelegate.class,
    "zcFaCardServiceDelegate");

  private IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
    "zcEbBaseServiceDelegate");

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

  private JPanel headPanel, footPanel, centerPanel;

  private JTabbedPane cardTabs;

  private JPanel htPanel = new JPanel();

  // 工作流填写意见审核通过
  protected FuncButton suggestPassButton = new SuggestAuditPassButton();

  // 工作流销审
  protected FuncButton unAuditButton = new UnauditButton();

  // 工作流退回
  protected FuncButton unTreadButton = new UntreadButton();

  private Hashtable fieldEditorHst = new Hashtable();

  private JTabbedPane cardAndHtTab;

  protected JTablePanel cardSubTablePanel = new JTablePanel();

  protected JTablePanel cardDocTablePanel = new JTablePanel();

  protected JTablePanel cardUsingTablePanel = new JTablePanel();

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();
  

  private ZcFaCard oldCard;

  public ZcFaCardEditPanel(ZcFaCardDialog parent, ListCursor listCursor, ZcFaCardListPanel listPanel) {
    // 
    super(ZcFaCardEditPanel.class, BillElementMeta.getBillElementMetaWithoutNd(listPanel.getcompoId()));

    this.listCursor = listCursor;

    this.listPanel = listPanel;

    this.parent = parent;

    this.colCount = 3;

    init();

    requestMeta.setCompoId(listPanel.getcompoId());

    refreshData();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub

    ZcFaCard card = listCursor.getCurrentObject();

    if (card != null && !"".equals(ZcUtil.safeString(card.getCardId()))) {//列表页面双击进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_BROWSE;

      card = zcFaCardServiceDelegate.selectByPrimaryKey(card.getCardId(), this.requestMeta);
      card.setCurrentDate(SfUtil.getSysDate());
      //获取该合同未入资产的金额
      setCandidateSum(card);
      listCursor.setCurrentObject(card);
      this.setEditingObject(card);
    } else {//新增按钮进入

      this.pageStatus = ZcSettingConstants.PAGE_STATUS_NEW;

      setDefaultValue(card);
      //获取该合同未入资产的金额
      setCandidateSum(card);

      listCursor.setCurrentObject(card);

      this.setEditingObject(card);

    }
    refreshSubData(card);

    setOldObject();

    setButtonStatus();

    updateFieldEditorsEditable();

    boolean isEdit=false;
    if(pageStatus==ZcSettingConstants.PAGE_STATUS_EDIT || pageStatus==ZcSettingConstants.PAGE_STATUS_NEW){
      isEdit=true;
    }
    setWFSubTableEditable(cardDocTablePanel, isEdit);

    setWFSubTableEditable(cardSubTablePanel, isEdit);
  }

  private void setCandidateSum(ZcFaCard card) {
    // TCJLODO Auto-generated method stub
  /*  if(card==null || card.getCaigouHt()==null || card.getCaigouHt().getZcHtCode()==null)return;
    
    HashMap ds=(HashMap) zcEbBaseServiceDelegate.queryObject("ZcFaCard.getCandidateHtSum", card.getCaigouHt().getZcHtCode(), requestMeta);
    card.setCandidateHtSum((BigDecimal) ds.get("ZC_HT_NUM"));*/
  }

  protected void updateFieldEditorsEditable() {
    if (pageStatus.equals(ZcSettingConstants.PAGE_STATUS_BROWSE)) {
      super.updateFieldEditorsEditable();
    } else {
      ZcFaCard card = listCursor.getCurrentObject();
      if (card.getCardStatu().equals("1") || ZcUtil.isYsdw()) {//
        for (int i = 0; i < fieldEditors.size(); i++) {
          AbstractFieldEditor editor = fieldEditors.get(i);
          //System.out.println("--------------"+editor.getFieldName());
          editor.setEnabled(true);
          for (int j = 0; j < readOnlyFieldLst.size(); j++) {
            if (editor.getFieldName().equalsIgnoreCase(getFieldName(readOnlyFieldLst.get(j)))) {
              editor.setEnabled(false);
              System.out.println("--------------"+editor.getFieldName());
              continue;
            }
          }
        }
      }
    }
  }

  protected void setButtonStatus() {
    ZcFaCard card = listCursor.getCurrentObject();
    if (WFConstants.AUDIT_TAB_STATUS_CANCEL.equals(card.getCardStatu())) {
      setCancelStatus(listCursor);
    } else {
      setButtonStatus(card, requestMeta, this.listCursor);
    }
  }

  private void setOldObject() {
    // TCJLODO Auto-generated method stub
    oldCard = (ZcFaCard) ObjectUtil.deepCopy(listCursor.getCurrentObject());
  }

  private void refreshSubData(ZcFaCard card) {
    // TCJLODO Auto-generated method stub
    cardSubTablePanel.setTableModel(new ZcFaCardToTableModelConverter().convertCardSubToTableData(card.getZcFaCardSubList()));

    cardDocTablePanel.setTableModel(new ZcFaCardToTableModelConverter().convertCardDocToTableData(card.getZcFaCardDocList()));

    ZcUtil.translateColName(cardSubTablePanel.getTable(), ZcFaCardToTableModelConverter.getSubInfo());

    ZcUtil.translateColName(cardDocTablePanel.getTable(), ZcFaCardToTableModelConverter.getDocInfo());

    // 设置从表属性 

    setTablePorperty();

  }

  private void setTablePorperty() {
    // TCJLODO Auto-generated method stub
    SwingUtil.setTableCellEditor(cardSubTablePanel.getTable(), "FA_SUB_NUM", new MoneyCellEditor());
    SwingUtil.setTableCellRenderer(cardSubTablePanel.getTable(), "FA_SUB_NUM", new NumberCellRenderer());
    SwingUtil.setTableCellEditor(cardSubTablePanel.getTable(), "FA_SUB_PRICE", new MoneyCellEditor());
    SwingUtil.setTableCellRenderer(cardSubTablePanel.getTable(), "FA_SUB_PRICE", new NumberCellRenderer());
    SwingUtil.setTableCellEditor(cardSubTablePanel.getTable(), "FA_SUB_DATE", new DateCellEditor());
    SwingUtil.setTableCellRenderer(cardSubTablePanel.getTable(), "FA_SUB_DATE", new DateCellRenderer());

    SwingUtil.setTableCellEditor(cardDocTablePanel.getTable(), "CARD_DOC", new FileCellEditor("cardDocBlobid", (BeanTableModel) cardDocTablePanel
      .getTable().getModel()));
    SwingUtil.setTableCellEditor(cardSubTablePanel.getTable(), "CARD_DOC_LIST", new IntCellEditor());
    SwingUtil.setTableCellRenderer(cardSubTablePanel.getTable(), "CARD_DOC_LIST", new IntCellRenderer());

  }

  private void setDefaultValue(ZcFaCard card) {
    // TCJLODO Auto-generated method stub
    card.setCurrentDate(SfUtil.getSysDate());
    //采购组织形式:政府集中机构采购
    card.setFaItemC22("01");
    card.setCardId("自动编号");
    card.setFaCode("自动编号");
    card.setCoCode(requestMeta.getSvCoCode());
    card.setCoName(requestMeta.getSvCoName());
    card.setCardStatu("1");
    card.setInputDate(SfUtil.getSysDate());
    card.setInputEmpCode(requestMeta.getSvUserID());
    card.setInputEmpName(requestMeta.getSvUserName());
    card.setNd(requestMeta.getSvNd());
//    System.out.println("setDefaultValue cocode"+card.getCoCode()+card.getCoName());
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

    toolBar.add(sendButton);

    toolBar.add(suggestPassButton);

    toolBar.add(unAuditButton);

    toolBar.add(unTreadButton);

    toolBar.add(callbackButton);

    toolBar.add(deleteButton);

    toolBar.add(printButton);

    toolBar.add(traceButton);

    toolBar.add(previousButton);

    toolBar.add(nextButton);

    toolBar.add(exitButton);

    editButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doEdit();

      }

    });

    sendButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doSend();

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

    traceButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        doTrace();

      }

    });

    callbackButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        doCallback();

      }

    });

    suggestPassButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {

        // 填写意见审核

        doSuggestPass();

      }

    });

    unAuditButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        // 销审

                doUnAudit();

      }

    });

    unTreadButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        // 退回

        doUnTread();

      }

    });

    printButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doPrintButton();

      }

    });
  }

  protected void doUnAudit() {
    ZcFaCard qx = (ZcFaCard) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());

    boolean success = true;
    ZcFaCard afterSaveBill = null;
    String errorInfo = "";
    int i = JOptionPane.showConfirmDialog(this, "是否确定消审？", "确认", JOptionPane.INFORMATION_MESSAGE);
    if (i != 0) {
      return;
    }

    try {
      requestMeta.setFuncId(unAuditButton.getFuncId());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = zcFaCardServiceDelegate.unAuditFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      log.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }

    if (success) {

      this.listCursor.setCurrentObject(afterSaveBill);
      refreshData();
      JOptionPane.showMessageDialog(this, "销审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      this.listPanel.refreshCurrentTabData();
    } else {
      JOptionPane.showMessageDialog(this, "销审失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  protected void doPrintButton() {
    // TCJLODO Auto-generated method stub

  }


  /*

   * 退回

   */

  protected void doUnTread() {

    GkCommentUntreadDialog commentDialog = new GkCommentUntreadDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),ModalityType.APPLICATION_MODAL);
    if (commentDialog.cancel) {
      return;
    }

    boolean success = true;
    ZcFaCard afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(unTreadButton.getFuncId());
      ZcFaCard qx = (ZcFaCard) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      qx.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      qx.setComment(commentDialog.getComment());
      afterSaveBill = zcFaCardServiceDelegate.untreadFN(qx, requestMeta);
    } catch (Exception e) {
      success = false;
      log.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }
    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      refreshData();
      JOptionPane.showMessageDialog(this, "退回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      this.listPanel.refreshCurrentTabData();
    } else {
      JOptionPane.showMessageDialog(this, "退回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  protected void doSuggestPass() {
    if (!checkBeforSave()) {
      return;
    }

    ZcFaCard card = (ZcFaCard) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
    requestMeta.setFuncId(this.suggestPassButton.getFuncId());
    GkCommentDialog commentDialog = null;
    commentDialog = new GkCommentDialog(DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow(),
      ModalityType.APPLICATION_MODAL);

    if (commentDialog.cancel) {
      return;
    }

    boolean success = true;
    String errorInfo = "";
    try {
      card.setComment(commentDialog.getComment());
      card.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      card.setAuditDate(SfUtil.getSysDate());
      card.setAuditEmpCode(requestMeta.getSvUserID());
      card.setAuditEmpName(requestMeta.getSvUserName());
      card = zcFaCardServiceDelegate.auditFN(card, requestMeta);
    } catch (Exception e) {
      success = false;
      log.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }

    if (success) {
      this.refreshData();
      JOptionPane.showMessageDialog(this, "审核成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      this.listPanel.refreshCurrentTabData();
    } else {
      JOptionPane.showMessageDialog(this, "审核失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  protected void doCallback() {

    boolean success = true;
    ZcFaCard afterSaveBill = null;
    String errorInfo = "";
    try {
      requestMeta.setFuncId(this.callbackButton.getFuncId());
      ZcFaCard card = (ZcFaCard) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());
      card.setAuditorId(WorkEnv.getInstance().getCurrUserId());
      afterSaveBill = zcFaCardServiceDelegate.callbackFN(card, requestMeta);
    } catch (Exception e) {
      success = false;
      log.error(e.getMessage(), e);
      errorInfo += e.getMessage();
    }

    if (success) {
      refreshData();
      JOptionPane.showMessageDialog(this, "收回成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
      this.listPanel.refreshCurrentTabData();
    } else {
      JOptionPane.showMessageDialog(this, "收回失败 ！" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
    }
  }

  protected void doTrace() {
    // TCJLODO Auto-generated method stub
    ZcBaseBill bean = (ZcBaseBill) this.listCursor.getCurrentObject();
    if (bean == null) {
      return;
    }
    ZcUtil.showTraceDialog(bean, compoId);
  }

  protected void doDelete() {

    requestMeta.setFuncId(deleteButton.getFuncId());

    ZcFaCard qx = (ZcFaCard) this.listCursor.getCurrentObject();

    int num = JOptionPane.showConfirmDialog(this, "是否删除当前单据", "删除确认", 0);

    if (num == JOptionPane.YES_OPTION) {
      boolean success = true;
      String errorInfo = "";
      try {
        requestMeta.setFuncId(deleteButton.getFuncId());
        zcFaCardServiceDelegate.deleteByCardIdFN(qx.getCardId(), this.requestMeta);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        success = false;
        errorInfo += e.getMessage();
      }

      if (success) {

        this.listCursor.removeCurrentObject();
        JOptionPane.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
        this.listPanel.refreshCurrentTabData();        
        parent.dispose();
      } else {
        JOptionPane.showMessageDialog(this, "删除失败 ！\n" + errorInfo, "错误", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  protected void doSave() {
    // TCJLODO Auto-generated method stub
    if (!checkBeforSave()) {
      return;
    }
    boolean success = true;
    String errorInfo = "";
    try {

      requestMeta.setFuncId(saveButton.getFuncId());
      ZcFaCard card = (ZcFaCard) this.listCursor.getCurrentObject();
      card = zcFaCardServiceDelegate.updateFN(card, this.requestMeta);
      listCursor.setCurrentObject(card);

    } catch (Exception e) {
      log.error(e.getMessage(), e);
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

  }

  private boolean checkBeforSave() {
    // TCJLODO Auto-generated method stub

    ZcFaCard card = listCursor.getCurrentObject();
    StringBuffer errorInfo = new StringBuffer();
    _checkNotNull(errorInfo);
    _checkUsing(errorInfo);
//    _checkCardSum(errorInfo);
//    _checkHtCandidateSum(errorInfo);
    if (errorInfo.length() > 0) {
      JOptionPane.showMessageDialog(this, errorInfo.toString(), "提示", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }
//检查卡片金额与未纳入资产的合同金额，卡片金额不能超
private boolean _checkHtCandidateSum(StringBuffer errorInfo) {
    // TCJLODO Auto-generated method stub
  StringBuffer errorMsg=new StringBuffer();
  ZcFaCard card = listCursor.getCurrentObject();
  BigDecimal t=new BigDecimal(0);
  if(pageStatus==ZcSettingConstants.PAGE_STATUS_NEW){
    t=t.add(card.getCost()==null?new BigDecimal(0):card.getCost());
    if(t.compareTo(card.getCandidateHtSum()==null?new BigDecimal(0):card.getCandidateHtSum())==1){
      errorMsg.append("原值不能超过未纳入资产的合同金额.当前可用合同金额是:").append(card.getCandidateHtSum().doubleValue()).append("\n");
    }
  }else{
    t=t.add(card.getCost()==null?new BigDecimal(0):card.getCost());
    BigDecimal ot=oldCard.getCost()==null?new BigDecimal(0):oldCard.getCost();
    t=t.subtract(ot);
    
    if(t.compareTo(card.getCandidateHtSum()==null?new BigDecimal(0):card.getCandidateHtSum())==1){
      t=t.subtract(ot);
      errorMsg.append("原值不能超过未纳入资产的合同金额, 当前超出金额是:").append(t.doubleValue()).append("\n");
    }
  }
  if(errorMsg.length()>0){
    if(errorInfo.length()>0){
      errorInfo.append("\n").append(errorMsg);
    }else{
      errorInfo.append(errorMsg);
    }
    return false;
  }
  return true;
    
  }

//检查卡片金额是否相等,原值必须等于财政拨款+事业收入+其他资金之和,原值不能为<=0
private boolean _checkCardSum(StringBuffer errorInfo) {
    // TCJLODO Auto-generated method stub
  StringBuffer errorMsg=new StringBuffer();
  ZcFaCard card = listCursor.getCurrentObject();
  BigDecimal t=new BigDecimal(0);
  if(t.compareTo(card.getCost()==null?new BigDecimal(0):card.getCost())==0){
    errorMsg.append("原值不能为0.\n");
  }
  if(t.compareTo(card.getCost()==null?new BigDecimal(0):card.getCost())==1){
    errorMsg.append("原值不能为负数.\n");
  }
  t=t.add(card.getFaItemN19()==null?new BigDecimal(0):card.getFaItemN19());//财政拨款
  t=t.add(card.getFaItemN22()==null?new BigDecimal(0):card.getFaItemN22());//其他资金
  t=t.add(card.getFaItemN34()==null?new BigDecimal(0):card.getFaItemN34());//事业收入
  if(t.compareTo(card.getCost()==null?new BigDecimal(0):card.getCost())!=0){
    errorMsg.append("原值必须等于财政拨款+事业收入+其他资金之和.\n");
  }
  t=new BigDecimal(0);
  t=t.add(card.getFaItemN35()==null?new BigDecimal(0):card.getFaItemN35());
  if(t.compareTo(card.getFaItemN34()==null?new BigDecimal(0):card.getFaItemN34())==1){
    errorMsg.append("事业收入的预算外收入不能大于事业收入 \n");
  }
  t=new BigDecimal(0);
  t=t.add(card.getFaItemN36()==null?new BigDecimal(0):card.getFaItemN36());
  if(t.compareTo(card.getFaItemN22()==null?new BigDecimal(0):card.getFaItemN22())==1){
    errorMsg.append("财政性结余资金不能大于其他资金 \n");
  }
  t=new BigDecimal(0);
  t=t.add(card.getQty()==null?new BigDecimal(0):card.getQty());
  if(t.compareTo(new BigDecimal(0))<=0){
    errorMsg.append("数量必须大于0\n");
  }
  t=new BigDecimal(0);
  t=t.add(card.getUseLife()==null?new BigDecimal(0):card.getUseLife());
  if(t.compareTo(new BigDecimal(0))<=0){
    errorMsg.append("预计使用月份必须大于0\n");
  }
  if(errorMsg.length()>0){
    if(errorInfo.length()>0){
      errorInfo.append("\n").append(errorMsg);
    }else{
      errorInfo.append(errorMsg);
    }
    return false;
  }
  return true;
    
  }

  //使用状态为“在用”时，必须填写使用部门
  private boolean _checkUsing(StringBuffer errorInfo) {
    // TCJLODO Auto-generated method stub
    StringBuffer errorMsg=new StringBuffer();
    ZcFaCard card = listCursor.getCurrentObject();
    //VS_USING_STATU_NAME
    if("01".equals(card.getUsingStatu()) && card.getUseOrgCode()==null){
      errorMsg.append("使用状态为“在用”时，必须填写科室和责任人.\n");
    }
    if(errorMsg.length()>0){
      if(errorInfo.length()>0){
        errorInfo.append("\n").append(errorMsg);
      }else{
        errorInfo.append(errorMsg);
      }
      return false;
    }
    return true;
  }
//检查非空项
  private boolean _checkNotNull(StringBuffer errorInfo){  
    StringBuffer errorMsg=new StringBuffer();
    ZcFaCard card = listCursor.getCurrentObject();
    String notNullFieldCol, fieldName, fieldTrans, methodName;
    for (int i = 0; i < notNullFieldLst.size(); i++) {
      notNullFieldCol = notNullFieldLst.get(i);
      fieldName = getFieldName(notNullFieldCol);
      if (fieldName == null)
        continue;
      methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
      try {
        Object obj = MethodUtils.invokeMethod(card, methodName, null);
        if (obj == null || obj.toString().equals("")) {
          if (errorMsg.length() == 0) {
            errorMsg.append("下列信息需要填写：\n");
          }
          if (fieldEditorHst.containsKey(fieldName)) {
            fieldTrans = ((AbstractFieldEditor) fieldEditorHst.get(fieldName)).getName();
          } else {
            fieldTrans = fieldName;
          }
          errorMsg.append(" ").append(fieldTrans).append(",");
        }
      } catch (NoSuchMethodException e) {
        // TCJLODO Auto-generated catch block
        log.error(e.getMessage(), e);
        errorMsg.append(e.getMessage());
      } catch (IllegalAccessException e) {
        // TCJLODO Auto-generated catch block
        log.error(e.getMessage(), e);
        errorMsg.append(e.getMessage());
      } catch (InvocationTargetException e) {
        // TCJLODO Auto-generated catch block
        log.error(e.getMessage(), e);
        errorMsg.append(e.getMessage());
      }
    }
    if(errorMsg.length()>0){
      if(errorInfo.length()>0){
        errorInfo.append("\n").append(errorMsg.substring(0, errorMsg.length()-1));
      }else{
        errorInfo.append(errorMsg.substring(0, errorMsg.length()-1));
      }
      return false;
    }
    return true;
  }
  protected void doNext() {
    // TCJLODO Auto-generated method stub

  }

  protected void doPrevious() {
    // TCJLODO Auto-generated method stub

  }

  protected void doSend() {

    boolean success = true;

    ZcFaCard afterSaveBill = null;

    /*    if (this.isDataChanged()) {

          JOptionPane.showMessageDialog(this, "数据发生改变，请先保存.", "提示", JOptionPane.INFORMATION_MESSAGE);

          return;

        }*/

    try {

      requestMeta.setFuncId(this.sendButton.getFuncId());

      ZcFaCard card = (ZcFaCard) ObjectUtil.deepCopy(this.listCursor.getCurrentObject());

      card.setAuditorId(WorkEnv.getInstance().getCurrUserId());

      afterSaveBill = zcFaCardServiceDelegate.newCommitFN(card, requestMeta);

    } catch (Exception ex) {

      log.error(ex.getMessage(), ex);

      success = false;

      UIUtilities.showStaickTraceDialog(ex, this, "错误", ex.getMessage());

    }

    if (success) {
      this.listCursor.setCurrentObject(afterSaveBill);
      refreshData();

      JOptionPane.showMessageDialog(this, "送审成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

      this.listPanel.refreshCurrentTabData();

    }
  }

  protected void doEdit() {
    // TCJLODO Auto-generated method stub
    pageStatus = ZcSettingConstants.PAGE_STATUS_EDIT;
    updateFieldEditorsEditable();
    setButtonStatus();
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createFieldEditors()
   */
  @Override
  public List<AbstractFieldEditor> createFieldEditors() {
    // TCJLODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel#createSubBillPanel()
   */
  @Override
  public JComponent createSubBillPanel() {
    // TCJLODO Auto-generated method stub
    return null;
  }

  public void doExit() {
    // TCJLODO Auto-generated method stub
    this.parent.dispose();
  }

  protected void init() {
    //根据卡片样式获取非空字段和不可编辑字段
    initNotNullAndModifyFields();

    initTitle();

    this.initToolBar(toolBar);

    fieldEditors = new ArrayList<AbstractFieldEditor>();
    headPanel = createHeadPanel();
    footPanel = createFootPanel();
    cardTabs = createCenterPanel();

    workPanel.setLayout(new BorderLayout());

    workPanel.add(headPanel, BorderLayout.NORTH);
    workPanel.add(cardTabs, BorderLayout.CENTER);
    workPanel.add(footPanel, BorderLayout.SOUTH);

    JPanel cardPanel = new JPanel();
    cardPanel.setLayout(new BorderLayout());
    cardPanel.add(toolBar, BorderLayout.NORTH);
    cardPanel.add(workPanel, BorderLayout.CENTER);
    cardAndHtTab = new JTabbedPane(JTabbedPane.LEFT);
    cardAndHtTab.add("资产卡片", cardPanel);

    htPanel.setLayout(new BorderLayout());
    String txt = "<html><a>&nbsp;<font size='12' color='blue'>该卡片没有关联的采购合同!</font></a></html>";
    JLabel lb = new JLabel(txt);
    htPanel.add(lb, BorderLayout.CENTER);
    cardAndHtTab.add("采购合同", htPanel);

    cardAndHtTab.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        tabChangeAction();
      }
    });

    this.setLayout(new BorderLayout());
//    this.add(cardAndHtTab, BorderLayout.CENTER);

    this.add(cardPanel, BorderLayout.CENTER);

  }

  protected void tabChangeAction() {
    // TCJLODO Auto-generated method stub
    // TCJLODO Auto-generated method stub
    //        System.out.println(itemsTablePanel.getDataList().size());
   /* int index = cardAndHtTab.getSelectedIndex();
    if (index == 1 && !(htPanel instanceof AbstractMainSubEditPanel)) {
      ZcFaCard card = listCursor.getCurrentObject();
      if (card.getCaigouHt() != null && card.getCaigouHt().getZcHtCode() != null) {
        List<ZcXmcgHt> l = new ArrayList<ZcXmcgHt>();
        l.add(card.getCaigouHt());
        htPanel = new ZcXmcgHtEditPanel(parent, new ListCursor<ZcXmcgHt>(l, 0), null, null,"ZC_XMCG_HT");
        cardAndHtTab.remove(1);
        cardAndHtTab.add("采购合同", htPanel);
        cardAndHtTab.validate();
        cardAndHtTab.repaint();
        cardAndHtTab.setSelectedIndex(1);
      }
    }*/
  }

  private void initTitle() {
    // TCJLODO Auto-generated method stub
    StringBuffer title = new StringBuffer(LangTransMeta.translate(listPanel.getcompoId()));
    title.append("(");
    ZcFaCard card = listCursor.getCurrentObject();
    ZcFaCardStyle style = (ZcFaCardStyle) zcEbBaseServiceDelegate.queryObject("ZcFaCard.getCardStyleByCode", card.getStyleCode(), requestMeta);
    title.append(style.getCardStylName());
    /*if ("A".equalsIgnoreCase(style.getIsDeprStyl())) {
      title.append("－非折旧类");
    } else if ("1".equals(style.getIsDeprStyl())) {
      title.append("－折旧类");
    } else if ("0".equals(style.getIsDeprStyl())) {
      //      title.append("－无形资产");
    }*/
    title.append(")");
    this.workPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title.toString(), TitledBorder.CENTER,
      TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));
  }

  /*
   * 根据卡片样式获取非空字段和不可编辑字段
   */
  private void initNotNullAndModifyFields() {
    // TCJLODO Auto-generated method stub
    ZcFaCard card = listCursor.getCurrentObject();
    List fields = zcEbBaseServiceDelegate.queryDataForList("ZcFaCard.getNotNullAndModifyField", card.getStyleCode(), requestMeta);
    if (fields == null)
      return;
    Map field;
    String item, isNull, isTNull, isModify = "";
    //    StringBuffer isNotNull=new StringBuffer(),isNotModify=new StringBuffer();

    for (Object obj : fields) {
      field = (Map) obj;
      item = (String) field.get("FAITEM_CODE");
      isNull = field.get("IS_NULL") == null ? "" : (String) field.get("IS_NULL");
      if ("N".equalsIgnoreCase(isNull)) {
        notNullFieldLst.add(item);
      } else if ((isNull == null) || ("".equals(isNull))) {
        isTNull = field.get("T_IS_NULL") == null ? "" : (String) field.get("T_IS_NULL");
        if ("N".equalsIgnoreCase(isTNull)) {
          notNullFieldLst.add(item);
        }
      }
      isModify = field.get("IS_MODIFY") == null ? "" : (String) field.get("IS_MODIFY");
      if ("N".equalsIgnoreCase(isModify)) {
        readOnlyFieldLst.add(item);
      }
    }
    readOnlyFieldLst.add("CANDIDATE_HT_SUM");
    addMustItem(notNullFieldLst, ZcFaCard.NOT_NULL_FIELDS);
    String OPT_FA_IS_MODIFY = AsOptionMeta.getOptVal("OPT_ZC_FA_IS_MODIFY");
    if (OPT_FA_IS_MODIFY == null) {
      OPT_FA_IS_MODIFY = "N";
    }
    if (!"Y".equalsIgnoreCase(OPT_FA_IS_MODIFY)) {
      addMustItem(readOnlyFieldLst, ZcFaCard.READ_ONLY_FIELDS);
    }
  }

  private void addMustItem(List<String> fieldLst, String[] existsFields) {
    // TCJLODO Auto-generated method stub
    for (int i = 0; i < existsFields.length; i++) {
      if (!fieldLst.contains(existsFields[i])) {
        fieldLst.add(existsFields[i]);
      }
    }
  }

  private JTabbedPane createCenterPanel() {

    ZcFaCard card = listCursor.getCurrentObject();

    List<ZcFaCardField> fieldLst = zcEbBaseServiceDelegate.queryDataForList("ZcFaCard.getCardFieldLst", card.getStyleCode(), requestMeta);

    fieldLst = correctionFields(fieldLst);

    List<AbstractFieldEditor> editorList = createMainPanelEditors(fieldLst);

    centerPanel = _createCenterPanel(editorList, 2);

    cardTabs = new JTabbedPane();

    JScrollPane spl = new JScrollPane(centerPanel);

    cardTabs.add("卡片登记", spl);
    cardTabs.add("资产附属设备", createCardSubPanel());
    cardTabs.add("资产附属文件", createCardDocPanel());
    //    cardTabs.add("资产使用部门",createCardUsingPanel());
    return cardTabs;
  }

  private Component createCardUsingPanel() {
    // TCJLODO Auto-generated method stub
    return null;
  }

  private Component createCardDocPanel() {
    // TCJLODO Auto-generated method stub

    cardDocTablePanel.init();
    cardDocTablePanel.setPanelId(this.getClass().getName() + "_docPanel");
    cardDocTablePanel.getSearchBar().setVisible(false);
    cardDocTablePanel.setTablePreferencesKey(this.getClass().getName() + "_docTable");
    cardDocTablePanel.getTable().setShowCheckedColumn(true);
    cardDocTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));
    JFuncToolBar bottomToolBar2 = new JFuncToolBar();
    FuncButton addBtn2 = new SubaddButton(false);
    JButton insertBtn2 = new SubinsertButton(false);
    JButton delBtn2 = new SubdelButton(false);
    bottomToolBar2.add(addBtn2);
    bottomToolBar2.add(insertBtn2);
    bottomToolBar2.add(delBtn2);
    cardDocTablePanel.add(bottomToolBar2, BorderLayout.SOUTH);
    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ZcFaCardDoc item = new ZcFaCardDoc();
        int rowNum = addSub(cardDocTablePanel, item);
        cardDocTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ZcFaCardDoc item = new ZcFaCardDoc();
        int rowNum = insertSub(cardDocTablePanel, item);
        cardDocTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(cardDocTablePanel);
      }
    });
    return cardDocTablePanel;
  }

  private Component createCardSubPanel() {
    // TCJLODO Auto-generated method stub

    cardSubTablePanel.init();
    cardSubTablePanel.setPanelId(this.getClass().getName() + "_subPanel");
    cardSubTablePanel.getSearchBar().setVisible(false);
    cardSubTablePanel.setTablePreferencesKey(this.getClass().getName() + "_subTable");
    cardSubTablePanel.getTable().setShowCheckedColumn(true);
    cardSubTablePanel.getTable().getTableRowHeader().setPreferredSize(new Dimension(60, 0));
    JFuncToolBar bottomToolBar2 = new JFuncToolBar();
    FuncButton addBtn2 = new SubaddButton(false);
    JButton insertBtn2 = new SubinsertButton(false);
    JButton delBtn2 = new SubdelButton(false);
    bottomToolBar2.add(addBtn2);
    bottomToolBar2.add(insertBtn2);
    bottomToolBar2.add(delBtn2);
    cardSubTablePanel.add(bottomToolBar2, BorderLayout.SOUTH);
    addBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ZcFaCardSub item = new ZcFaCardSub();
        int rowNum = addSub(cardSubTablePanel, item);
        cardSubTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    insertBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ZcFaCardSub item = new ZcFaCardSub();
        int rowNum = insertSub(cardSubTablePanel, item);
        cardSubTablePanel.getTable().setRowSelectionInterval(rowNum, rowNum);
      }
    });

    delBtn2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        deleteSub(cardSubTablePanel);
      }
    });
    return cardSubTablePanel;
  }

  private JPanel _createCenterPanel(List<AbstractFieldEditor> editorList, int cols) {
    // TCJLODO Auto-generated method stub

    // TCJLODO Auto-generated method stub

    JPanel panel = new JPanel();

    panel.setLayout(new GridBagLayout());

    int row = 0;

    int col = 0;

    for (int i = 0; i < editorList.size(); i++) {

      AbstractFieldEditor comp = editorList.get(i);

      if (comp.isVisible()) {

        if (comp instanceof NewLineFieldEditor) {
          row++;
          col = 0;
          continue;
        } else if (comp instanceof TextAreaFieldEditor) {
          // 转到新的一行
          row++;
          col = 0;
          JLabel label = new JLabel(getLabelText(comp));
          if (comp.getMaxContentSize() != 9999) {
            label.setText(comp.getName() + "(" + comp.getMaxContentSize() + "字内)" + "*");
          }
          comp.setPreferredSize(new Dimension(150, comp.getOccRow() * 26));

          panel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 0, 2),
            0, 0));

          panel.add(comp, new GridBagConstraints(col + 1, row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0, GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL, new Insets(2, 0, 0, 2), 0, 0));

          // 将当前所占的行空间偏移量计算上
          row += comp.getOccRow();
          col = 0;

          continue;
        }

        JLabel label = new JLabel(getLabelText(comp));

        comp.setPreferredSize(new Dimension(300, 23));

        panel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 0, 2),
          0, 0));

        panel.add(comp, new GridBagConstraints(col + 1, row, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 0,
          0, 2), 0, 0));

        if (col == cols * 2 - 2) {
          row++;
          col = 0;
        } else {
          col += 2;
        }
      }

    }
    return panel;
  }

  public String getLabelText(AbstractFieldEditor comp) {

    StringBuffer buff = new StringBuffer();

    buff.append("<html><a>&nbsp;");

//    buff.append(comp.getName() + comp.getFieldName());
    buff.append(comp.getName());
    if (isNotNullField(comp.getFieldName())) {
      buff.append("<font color='red'>*</font>");
    }
    if (!(comp instanceof TextAreaFieldEditor)) {
      buff.append("</a></html>");
    } else {
      if (comp.getOccRow() >= 2) {
        buff.append("<br>(");
      } else {
        buff.append("(");
      }
      buff.append(comp.getMaxContentSize());
      buff.append("字内)</a></html>");
    }

    return buff.toString();

  }

  private boolean isNotNullField(String fieldName) {

    for (String billElement : notNullFieldLst) {
      String name = null;
      try {
        name = (String) FieldMapRegister.get(ZcFaCard.class).get(billElement);
        if (name == null || "".equals(name.trim())) {
          name = ZcUtil.convertColumnToField(billElement);
        }

      } catch (RuntimeException e) {
        log.error(e.getMessage(), e);
      }

      if (fieldName.equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  private List<AbstractFieldEditor> createMainPanelEditors(List<ZcFaCardField> fieldLst) {
    // TCJLODO Auto-generated method stub
    List<AbstractFieldEditor> rtn = new ArrayList<AbstractFieldEditor>();
    AbstractFieldEditor editor = null;
    String fieldName, boxType, fieldNameTrans = "";
    for (ZcFaCardField zcFaCardField : fieldLst) {
      boxType = getBoxType(zcFaCardField);
      fieldName = getFieldName(zcFaCardField.getFaitemCode());
      fieldNameTrans = zcFaCardField.getFaitemName() == null ? zcFaCardField.getFaitemCode() : zcFaCardField.getFaitemName();
      if ("textbox".equals(boxType)) {
        editor = new TextFieldEditor(fieldNameTrans, fieldName);
      } else if ("numericbox".equals(boxType)) {
        editor = new MoneyFieldEditor(fieldNameTrans, fieldName);
      } else if ("foreignbox".equals(boxType)) {
        editor = createForeignEntityFieldEditor(zcFaCardField);
      } else if ("datebox".equals(boxType)) {
        editor = new DateFieldEditor(fieldNameTrans, fieldName);
      } else if ("combobox".equals(boxType)) {
//    	  System.out.println("valid="+zcFaCardField.getValSetId());
        editor = new AsValFieldEditor(fieldNameTrans, fieldName, zcFaCardField.getValSetId());
      }
      if (editor != null) {
        fieldEditorHst.put(fieldName, editor);
        rtn.add(editor);
        editor = null;
      }
    }
    fieldEditors.addAll(rtn);
    return rtn;
  }

  private AbstractFieldEditor createForeignEntityFieldEditor(ZcFaCardField zcFaCardField) {
    // TCJLODO Auto-generated method stub
    AbstractFieldEditor rtn = null;
    IForeignEntityHandler handler = null;
    ElementConditionDto dto = null;
    String fieldName = getFieldName(zcFaCardField.getFaitemCode());
    String fieldNameTrans = zcFaCardField.getFaitemName() == null ? zcFaCardField.getFaitemCode() : zcFaCardField.getFaitemName();
    ZcFaCard card = listCursor.getCurrentObject();
    if (zcFaCardField.getFaitemCode().equals("FATYPE_NAME")) {
      String[] columNames = { "代码", "名称", "计量单位", "预计使用月份", "净残值率" };
      handler = new ZcFaTypeHandler(null, this);
      dto = new ElementConditionDto();
      dto.setZcText1(card.getStyleCode());
      rtn = new ZcFaCardTypeTreeSelectEditor(fieldNameTrans, "fatypeCode", true, dto, handler);
    } else if (zcFaCardField.getFaitemCode().equals("USE_EMP_NAME")) {
      String[] columNames = { "代码", "姓名" };
      handler = new ZcFaUserHandler(columNames, this);
      dto = new ElementConditionDto();
      dto.setNd(requestMeta.getSvNd());
      dto.setCoCode(requestMeta.getSvCoCode());
      rtn = new ForeignEntityFieldEditor("ZcFaCard.getCardUser", dto, 10, handler, columNames, "责任人", "useEmpName");
    } else if (zcFaCardField.getFaitemCode().equals("USE_ORG_NAME")) {
      String[] columNames = { "代码", "名称" };
      handler = new ZcFaOrgHandler(columNames, this);
      dto = new ElementConditionDto();
      dto.setNd(requestMeta.getSvNd());
      dto.setCoCode(requestMeta.getSvCoCode());
      rtn = new ForeignEntityFieldEditor("ZcFaCard.getCardUserOrg", dto, 10, handler, columNames, "科室", "useOrgName");
    }
    return rtn;
  }

  private String getFieldName(String fieldColumName) {
    // TCJLODO Auto-generated method stub
    //    System.out.println(fieldColumName+"==="+FieldMapRegister.get(ZcFaCard.class).get(fieldColumName));
    return (String) FieldMapRegister.get(ZcFaCard.class).get(fieldColumName);
  }

  /**
   * 初步处理获得的字段，清掉不需要出现的等
   * @param fieldLst
   */
  private List<ZcFaCardField> correctionFields(List<ZcFaCardField> fieldLst) {
    List<ZcFaCardField> rtn = new ArrayList<ZcFaCardField>();
    for (ZcFaCardField zcFaCardField : fieldLst) {
      //去掉当前面板排除的的项目 
      if (ZcFaCard.isExceptedField(zcFaCardField.getFaitemCode())) {
        continue;
      }
      //去掉主表没有的字段
      if (FieldMapRegister.get(ZcFaCard.class).get(zcFaCardField.getFaitemCode()) == null) {
        continue;
      }
      if (zcFaCardField.getIsVisi() != null && zcFaCardField.getIsVisi().equalsIgnoreCase("N")) {
        continue;
      }
      if (ZcFaCard.isNotNullField(zcFaCardField.getFaitemCode())) {
        zcFaCardField.setaIsNull("N");
      } else {
        String isNull = zcFaCardField.getaIsNull();
        if ("".equals(isNull)) {
          isNull = zcFaCardField.getbIsNull();
        }
        isNull = "N".equalsIgnoreCase(isNull) ? "N" : "Y";
        zcFaCardField.setaIsNull(isNull);
      }
      if (ZcFaCard.isReadOnlyField(zcFaCardField.getFaitemCode())) {
        zcFaCardField.setReadOnly(true);
      } else {
        zcFaCardField.setReadOnly(false);
      }
      rtn.add(zcFaCardField);
    }
    return rtn;
  }

  /**
   * @param zcFaCardField
   */
  private String getBoxType(ZcFaCardField zcFaCardField) {
    String boxType = "textbox";
    if (zcFaCardField.getDataType().equalsIgnoreCase("NUM")) {
      boxType = "numericbox";
      String valSetId = zcFaCardField.getValSetId();
      if (valSetId != null && valSetId.length() > 0) {
        boxType = "combobox";
      } else {
        String fRefName = zcFaCardField.getfReFname();
        String isFpk = zcFaCardField.getIsFpk();
        if (fRefName != null && fRefName.length() > 0) {
          if ((!"".equals(zcFaCardField.getfField())) && (zcFaCardField.getfField().length() > 0) && (zcFaCardField.getfField().indexOf(',') != -1)
            && (!isFpk.equalsIgnoreCase("Y")))
            boxType = "foreignbox";
          else if (isFpk != null && isFpk.equalsIgnoreCase("Y"))
            boxType = "foreignbox";
        }
      }
    } else if (zcFaCardField.getDataType().equalsIgnoreCase("DATE")) {
      boxType = "datebox";
    } else if (zcFaCardField.getDataType().equalsIgnoreCase("DATETIME")) {
      boxType = "datetimebox";
    } else if (zcFaCardField.getDataType().equalsIgnoreCase("TEXT")) {
      String valSetId = zcFaCardField.getValSetId();
      if (valSetId != null && valSetId.length() > 0) {
        boxType = "combobox";
      } else {
        String fRefName = zcFaCardField.getfReFname();
        String isFpk = zcFaCardField.getIsFpk();
        if (fRefName != null && fRefName.length() > 0) {
          if ((!"".equals(zcFaCardField.getfField())) && (zcFaCardField.getfField().length() > 0) && (zcFaCardField.getfField().indexOf(',') != -1)
            && (!isFpk.equalsIgnoreCase("Y")))
            boxType = "foreignbox";
          else if (isFpk != null && isFpk.equalsIgnoreCase("Y")) {
            boxType = "foreignbox";
          }
        }
      }
    }
    if (zcFaCardField.getFaitemCode().equalsIgnoreCase("CARD_ID")) {
      boxType = "textbox";
    }
    return boxType;
  }

  private JPanel createFootPanel() {
    List<AbstractFieldEditor> editorList = new ArrayList<AbstractFieldEditor>();

    TextFieldEditor inputEmpCode = new TextFieldEditor(LangTransMeta.translate("INPUT_EMP_CODE"), "inputEmpCode");
    TextFieldEditor auditEmpCode = new TextFieldEditor(LangTransMeta.translate("AUDIT_EMP_CODE"), "auditEmpCode");
    TextFieldEditor inputEmpName = new TextFieldEditor("录入人", "inputEmpName");
    TextFieldEditor auditEmpName = new TextFieldEditor(LangTransMeta.translate("AUDIT_EMP_NAME"), "auditEmpName");
    DateFieldEditor inputDate = new DateFieldEditor("录入时间", "inputDate");
    DateFieldEditor auditDate = new DateFieldEditor(LangTransMeta.translate("AUDIT_DATE"), "auditDate");

//    editorList.add(inputEmpCode);
//    editorList.add(auditEmpCode);
    editorList.add(inputEmpName);
//    editorList.add(auditEmpName);
    editorList.add(inputDate);
//    editorList.add(auditDate);

//    fieldEditorHst.put("inputEmpCode", inputEmpCode);
//    fieldEditorHst.put("auditEmpCode", auditEmpCode);
    fieldEditorHst.put("inputEmpName", inputEmpName);
//    fieldEditorHst.put("auditEmpName", auditEmpName);
    fieldEditorHst.put("inputDate", inputDate);
//    fieldEditorHst.put("auditDate", auditDate);

    fieldEditors.addAll(editorList);

    ZcFaCardClientUtil util = new ZcFaCardClientUtil();
    return util.createPanel(editorList, 2);
  }

  private JPanel createHeadPanel() {
    List<AbstractFieldEditor> editorList = new ArrayList<AbstractFieldEditor>();

    TextFieldEditor cardId = new TextFieldEditor(LangTransMeta.translate("CARD_ID"), "cardId");
    AsValFieldEditor cardStatus = new AsValFieldEditor("状态", "cardStatu", "FA_CARD_STAT");
    TextFieldEditor coCode = new TextFieldEditor(LangTransMeta.translate("CO_NAME"), "coName");
    DateFieldEditor currenDate = new DateFieldEditor("当前业务日期", "currentDate");
    MoneyFieldEditor candidateHtSum=new MoneyFieldEditor("未纳入资产的合同金额", "candidateHtSum");
    TextFieldEditor ndField = new TextFieldEditor("年度", "nd");
    
    

    editorList.add(cardId);
    editorList.add(cardStatus);
//    editorList.add(coCode);
//    editorList.add(currenDate);
//    editorList.add(candidateHtSum);
//    editorList.add(ndField);

    fieldEditorHst.put("cardId", cardId);
    fieldEditorHst.put("cardStatu", cardStatus);
//    fieldEditorHst.put("coName", coCode);
//    fieldEditorHst.put("currentDate", currenDate);
//    fieldEditorHst.put("candidateHtSum", candidateHtSum);
//    fieldEditorHst.put("nd", ndField);

    fieldEditors.addAll(editorList);
    ZcFaCardClientUtil util = new ZcFaCardClientUtil();
    return util.createPanel(editorList, 2);
  }

  public ZcFaCard getCurrentEditingObject() {
    return listCursor.getCurrentObject();
  }

  /**
   * 子类重写该方法，用于非工作流控制状态下按钮的编辑性
   */
  public void setButtonStatusWithoutWf() {
    // TCJLODO Auto-generated method stub

    if (this.btnStatusList.size() == 0) {

      ButtonStatus bs = new ButtonStatus();

      bs.setButton(this.editButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);
      bs.addBillStatus("1");
      btnStatusList.add(bs);
      bs = new ButtonStatus();

      bs.setButton(this.saveButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_EDIT);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_NEW);
      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.deleteButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);
      bs.addBillStatus("1");
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
      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.printButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_BROWSE);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);

    }

    ZcFaCard qx = (ZcFaCard) this.listCursor.getCurrentObject();

    String billStatus = qx.getCardStatu();

    ZcUtil.setButtonEnable(this.btnStatusList, billStatus, this.pageStatus, getCompoId(), qx.getProcessInstId());
  }
  
  public String getCompoId(){
	  return compoId;
  }
}
