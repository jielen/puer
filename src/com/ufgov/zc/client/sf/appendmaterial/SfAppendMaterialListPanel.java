/**
 * 
 */
package com.ufgov.zc.client.sf.appendmaterial;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.DefaultInvokeHandler;
import com.ufgov.smartclient.common.UIUtilities;
import com.ufgov.smartclient.component.table.JGroupableTable;
import com.ufgov.smartclient.plaf.BlueLookAndFeel;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.common.ParentWindowAware;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfAppendMaterialToTableModelConverter;
import com.ufgov.zc.client.component.JFuncToolBar;
import com.ufgov.zc.client.component.ui.AbstractDataDisplay;
import com.ufgov.zc.client.component.ui.AbstractEditListBill;
import com.ufgov.zc.client.component.ui.AbstractSearchConditionArea;
import com.ufgov.zc.client.component.ui.MultiDataDisplay;
import com.ufgov.zc.client.component.ui.SaveableSearchConditionArea;
import com.ufgov.zc.client.component.ui.TableDisplay;
import com.ufgov.zc.client.component.ui.conditionitem.AbstractSearchConditionItem;
import com.ufgov.zc.client.component.ui.conditionitem.SearchConditionUtil;
import com.ufgov.zc.client.component.ui.conditionitem.SfEntrustSearchConditionItem;
import com.ufgov.zc.client.print.PrintPreviewer;
import com.ufgov.zc.client.print.PrintSettingDialog;
import com.ufgov.zc.client.print.Printer;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowUtil;
import com.ufgov.zc.client.util.ListUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.commonbiz.model.SearchCondition;
import com.ufgov.zc.common.commonbiz.publish.IBaseDataServiceDelegate;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfAppendMaterial;
import com.ufgov.zc.common.sf.publish.ISfAppendMaterialServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.WFConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.dto.PrintObject;
import com.ufgov.zc.common.system.util.ObjectUtil;

/**
 * @author Administrator
 *
 */
public class SfAppendMaterialListPanel  extends AbstractEditListBill implements ParentWindowAware {
  /**
   * 
   */
  private static final long serialVersionUID = -5846663619525302744L;

  public static final Logger logger = Logger.getLogger(SfAppendMaterialListPanel.class);

  private SfAppendMaterialListPanel self = this;

  private Window parentWindow;

  public static final String compoId = "SF_APPEND_MATERIAL";

  private RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private ElementConditionDto elementConditionDto = new ElementConditionDto();

  private ISfAppendMaterialServiceDelegate sfAppendMaterialServiceDelegate;

  private IBaseDataServiceDelegate baseDataServiceDelegate;

  private SfEntrust entrust;

  public Window getParentWindow() {

    return parentWindow;

  }

  public void setParentWindow(Window parentWindow) {

    this.parentWindow = parentWindow;

  }

  public SfAppendMaterialListPanel() {
    this(null);
  }

  public SfAppendMaterialListPanel(SfEntrust entrust) {
    this.entrust = entrust;
    init();
  }

  public void init() {
    sfAppendMaterialServiceDelegate = (ISfAppendMaterialServiceDelegate) ServiceFactory.create(ISfAppendMaterialServiceDelegate.class, "sfAppendMaterialServiceDelegate");
    baseDataServiceDelegate = (IBaseDataServiceDelegate) ServiceFactory.create(IBaseDataServiceDelegate.class, "baseDataServiceDelegate");

    UIUtilities.asyncInvoke(new DefaultInvokeHandler<List<SearchCondition>>() {

      @Override
      public List<SearchCondition> execute() throws Exception {

        List<SearchCondition> needDisplaySearchConditonList = SearchConditionUtil.getNeedDisplaySearchConditonList(WorkEnv.getInstance()

        .getCurrUserId(), SfAppendMaterial.TAB_ID);

        return needDisplaySearchConditonList;

      }

      @Override
      public void success(List<SearchCondition> needDisplaySearchConditonList) {

        List<TableDisplay> showingDisplays = SearchConditionUtil.getNeedDisplayTableDisplay(needDisplaySearchConditonList);

        init(createDataDisplay(showingDisplays), null);//调用父类方法

        revalidate();

        repaint();

      }

      @Override
      public void after() {
        super.after();
        checkEntrust();
      }

    });

    requestMeta.setCompoId(compoId);
  }

  /**
   * 检查当前是否设置了entrust，如果设置了，则切换到全部页签，显示过滤后的数据
   */
  protected void checkEntrust() {
    // TCJLODO Auto-generated method stub
    if (entrust != null) {
      for (int i = 0; i < getTopDataDisplay().getShowingTableDisplays().size(); i++) {
        TableDisplay td = getTopDataDisplay().getShowingTableDisplays().get(i);
        if (td.getStatus().equals("all")) {
          getTopDataDisplay().getTabbedPane().setSelectedComponent(td);
          break;
        }
      }
      AbstractSearchConditionArea conditonArea = getTopSearchConditionArea();
      AbstractSearchConditionItem conditionItem = conditonArea.getCondItemsByCondiFieldCode("ENTRUST_CODE");
      SfEntrustSearchConditionItem sfEntrustConditionItem = (SfEntrustSearchConditionItem) conditionItem;
      sfEntrustConditionItem.setValue(entrust);
      refreshCurrentTabData();
    }
  }

  public void setEntrust(SfEntrust entrust) {
    this.entrust = entrust;
  }

  private AbstractDataDisplay createDataDisplay(List<TableDisplay> showingDisplays) {

    return new DataDisplay(SearchConditionUtil.getAllTableDisplay(SfAppendMaterial.TAB_ID), showingDisplays,

    createTopConditionArea(), true);//true:显示收索条件区 false：不显示收索条件区

  }

  private AbstractSearchConditionArea topSearchConditionArea;

  private AbstractSearchConditionArea createTopConditionArea() {

    Map defaultValueMap = new HashMap();
    defaultValueMap.put("ND", ""+requestMeta.getSvNd());
    if(entrust!=null){
      defaultValueMap.put("ENTRUST_CODE", entrust);    
    }

    topSearchConditionArea = new SaveableSearchConditionArea(SfAppendMaterial.SEARCH_ID, null, true, defaultValueMap, null);

    AbstractSearchConditionItem item = this.topSearchConditionArea.getCondItemsByCondiFieldCode(null);

    return topSearchConditionArea;

  }

  public AbstractSearchConditionArea getTopSearchConditionArea() {
    return topSearchConditionArea;
  }

  private final class DataDisplay extends MultiDataDisplay {

    private DataDisplay(List<TableDisplay> displays, List<TableDisplay> showingDisplays, AbstractSearchConditionArea conditionArea,

    boolean showConditionArea) {

      super(displays, showingDisplays, conditionArea, showConditionArea, SfAppendMaterial.TAB_ID);

      setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId), TitledBorder.CENTER,
        TitledBorder.TOP, new Font("宋体",

        Font.BOLD, 15), Color.BLUE));

    }

    protected void preprocessShowingTableDisplay(List<TableDisplay> showingTableDisplays) {

      for (final TableDisplay d : showingTableDisplays) {

        final JGroupableTable table = d.getTable();

        table.addMouseListener(new MouseAdapter() {

          public void mouseClicked(MouseEvent e) {

            if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {

              String tabStatus = d.getStatus();

              JGroupableTable table = d.getTable();

              MyTableModel model = (MyTableModel) table.getModel();

              int row = table.getSelectedRow();

              List viewList = (List) ObjectUtil.deepCopy(ListUtil.convertToTableViewOrderList(model.getList(), table));

              SfAppendMaterial charge = (SfAppendMaterial) viewList.get(row);
              if (self.getParent() instanceof JClosableTabbedPane) {
                new SfAppendMaterialDialog(self, viewList, row, topDataDisplay.getActiveTableDisplay().getStatus());
              } else {
                new SfDataFlowDialog(compoId, SfDataFlowUtil.getEntrust(charge.getEntrustId()), self);
              }

            }

          }

        });

      }

    }

    @Override
    protected void handleTableDisplayActived(AbstractSearchConditionItem[] searchConditionItems, final TableDisplay tableDisplay) {

      elementConditionDto.setWfcompoId(compoId);

      elementConditionDto.setExecutor(WorkEnv.getInstance().getCurrUserId());

      //    elementConditionDto.setNd(WorkEnv.getInstance().getTransNd());

      elementConditionDto.setStatus(tableDisplay.getStatus());

      for (AbstractSearchConditionItem item : searchConditionItems) {

        item.putToElementConditionDto(elementConditionDto);

      }

      final Container c = tableDisplay.getTable().getParent();

      UIUtilities.asyncInvoke(new DefaultInvokeHandler<TableModel>() {

        @Override
        public void before() {

          assert c != null;

          installLoadingComponent(c);

        }

        @Override
        public void after() {

          assert c != null;

          unInstallLoadingComponent(c);

          c.add(tableDisplay.getTable());

        }

        @Override
        public TableModel execute() throws Exception {
          return SfAppendMaterialToTableModelConverter.convertMainLst(self.sfAppendMaterialServiceDelegate.getMainDataLst(elementConditionDto, requestMeta));

        }

        @Override
        public void success(TableModel model) {

          tableDisplay.setTableModel(model);
          //        setButtonsVisiable();
          setButtonStatus();
        }

      });

    }

  }

  static {

    LangTransMeta.init("SF%");

  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {

      public void run() {

        try {

          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

          UIManager.setLookAndFeel(new BlueLookAndFeel());

        } catch (Exception e) {

          e.printStackTrace();

        }
        SfAppendMaterialListPanel bill = new SfAppendMaterialListPanel();

        JFrame frame = new JFrame("frame");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(bill);

        frame.setVisible(true);

      }

    });

  }

  public void setButtonsVisiable() {

    String panelId = WFConstants.AUDIT_TAB_STATUS_TODO;

    if (topDataDisplay != null && topDataDisplay.getActiveTableDisplay() != null) {

      panelId = topDataDisplay.getActiveTableDisplay().getStatus();

    }

    if (WFConstants.AUDIT_TAB_STATUS_TODO.equalsIgnoreCase(panelId) && !ZcUtil.isYsdw()) {//代办

      auditPassButton.setVisible(true);

      suggestPassButton.setVisible(true);

      isSendToNextButton.setVisible(true);

      unTreadButton.setVisible(true);

      sendButton.setVisible(false);

      deleteButton.setVisible(false);

      addButton.setVisible(false);

      addReChangeButton.setVisible(false);

      callbackButton.setVisible(false);

      traceButton.setVisible(true);

      editButton.setVisible(false);

      unAuditButton.setVisible(false);

      cancelButton.setVisible(true);

    } else if (WFConstants.AUDIT_TAB_STATUS_DONE.equalsIgnoreCase(panelId)) {//已办

      auditPassButton.setVisible(false);

      isSendToNextButton.setVisible(false);

      unTreadButton.setVisible(false);

      sendButton.setVisible(false);

      deleteButton.setVisible(false);

      addButton.setVisible(false);

      addReChangeButton.setVisible(false);

      callbackButton.setVisible(true);

      traceButton.setVisible(true);

      suggestPassButton.setVisible(false);

      editButton.setVisible(false);

      unAuditButton.setVisible(true);

      cancelButton.setVisible(false);

    } else if (WFConstants.AUDIT_TAB_STATUS_ALL.equalsIgnoreCase(panelId)) {//全部

      auditPassButton.setVisible(false);

      isSendToNextButton.setVisible(false);

      unTreadButton.setVisible(false);

      sendButton.setVisible(false);

      deleteButton.setVisible(false);

      addButton.setVisible(false);

      addReChangeButton.setVisible(false);

      callbackButton.setVisible(false);

      traceButton.setVisible(true);

      suggestPassButton.setVisible(false);

      editButton.setVisible(false);

      unAuditButton.setVisible(false);

      cancelButton.setVisible(false);

    } else if (WFConstants.EDIT_TAB_STATUS_EXEC.equalsIgnoreCase(panelId)) {//终审

      auditPassButton.setVisible(false);

      isSendToNextButton.setVisible(false);

      unTreadButton.setVisible(false);

      sendButton.setVisible(false);

      deleteButton.setVisible(false);

      addButton.setVisible(false);

      addReChangeButton.setVisible(false);

      callbackButton.setVisible(false);

      suggestPassButton.setVisible(false);

      editButton.setVisible(false);

      unAuditButton.setVisible(false);

      cancelButton.setVisible(false);

    } else if (WFConstants.EDIT_TAB_STATUS_DRAFT.equalsIgnoreCase(panelId)) {//草稿

      auditPassButton.setVisible(false);

      isSendToNextButton.setVisible(false);

      unTreadButton.setVisible(false);

      sendButton.setVisible(true);

      deleteButton.setVisible(true);

      addButton.setVisible(true);

      addReChangeButton.setVisible(true);

      callbackButton.setVisible(false);

      suggestPassButton.setVisible(false);

      editButton.setVisible(true);

      unAuditButton.setVisible(false);

      cancelButton.setVisible(true);

    } else if ("cancel".equalsIgnoreCase(panelId)) {

      traceButton.setVisible(false);

      sendButton.setVisible(false);

      suggestPassButton.setVisible(false);

      unTreadButton.setVisible(false);

      addButton.setVisible(false);

      deleteButton.setVisible(false);

      printButton.setVisible(false);

      printPreviewButton.setVisible(false);

      printSettingButton.setVisible(false);

      callbackButton.setVisible(false);

      unAuditButton.setVisible(false);

      cancelButton.setVisible(false);

    }

  }

  @Override
  protected void addToolBarComponent(JFuncToolBar toolBar) {

    toolBar.setModuleCode("SF");

    toolBar.setCompoId(compoId);

    toolBar.add(addButton);

    // toolBar.add(updateButton);

    //    toolBar.add(deleteButton);

    toolBar.add(helpButton);

    //    toolBar.add(sendButton);//送审

    //    toolBar.add(suggestPassButton);//填写意见审核通过

    //    toolBar.add(auditFinalButton);

    //    toolBar.add(callbackButton);//收回

    //    toolBar.add(unTreadButton);//退回

    //    toolBar.add(unAuditButton);//销审

    //    toolBar.add(cancelButton);//撤销

    //    toolBar.add(traceButton);

    //toolBar.add(printButton);

    //toolBar.add(isSendToNextButton);

    //    toolBar.add(traceDataButton);

    // 初始化按钮的action事件

    // 初始化按钮的action事件

    addButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doAdd();

      }

    });

    printButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {

        doPrint();

      }

    });

    printPreviewButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {

        doPrintPreview();

      }

    });

    printSettingButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {

        doPrintSetting();

      }

    });

  }

  public void refreshCurrentTabData() {

    topSearchConditionArea.doSearch();

  }

  public void refreshCurrentTabData(List beanList) {

    topDataDisplay.getActiveTableDisplay().getTable().setModel(SfAppendMaterialToTableModelConverter.convertMainLst(beanList));

  }

  public List getCheckedList() {

    List<SfAppendMaterial> beanList = new ArrayList<SfAppendMaterial>();

    JGroupableTable table = topDataDisplay.getActiveTableDisplay().getTable();

    MyTableModel model = (MyTableModel) table.getModel();

    //Modal的数据

    List list = model.getList();

    Integer[] checkedRows = table.getCheckedRows();

    for (Integer checkedRow : checkedRows) {

      int accordDataRow = table.convertRowIndexToModel(checkedRow);

      SfAppendMaterial bean = (SfAppendMaterial) list.get(accordDataRow);

      beanList.add(bean);

    }

    return beanList;

  }

  private void doAdd() {

    new SfAppendMaterialDialog(self, new ArrayList(1), -1, topDataDisplay.getActiveTableDisplay().getStatus());

    //    new SfDataFlowDialog(compoId,null,self);
  }

  private void doSend() {

  }

  private void doBatEdit() {

  }

  private void doBlankout() {

  }

  private void doTrace() {

    List beanList = getCheckedList();

    if (beanList.size() == 0) {

      JOptionPane.showMessageDialog(this, "没有选择数据！", " 提示", JOptionPane.INFORMATION_MESSAGE);

      return;

    }

    if (beanList.size() > 1) {

      JOptionPane.showMessageDialog(this, "只能选择一条数据！", " 提示", JOptionPane.INFORMATION_MESSAGE);

      return;

    }

    for (int i = 0; i < beanList.size(); i++) {

      SfAppendMaterial bean = (SfAppendMaterial) beanList.get(i);

      ZcUtil.showTraceDialog(bean, compoId);

    }

  }

  private void doCallBack() {

  }

  private void doPrint() {

    List printList = getCheckedList();

    if (printList.size() == 0) {

      JOptionPane.showMessageDialog(this, "请选择需要打印的数据 ！", "提示", JOptionPane.INFORMATION_MESSAGE);

      return;

    }

    requestMeta.setFuncId(this.printButton.getFuncId());

    requestMeta.setPageType(this.compoId + "_L");

    boolean success = true;

    boolean printed = false;

    try {

      PrintObject printObject = this.baseDataServiceDelegate.genMainBillPrintObjectFN(printList, requestMeta);

      if (Printer.print(printObject)) {

        printed = true;

      }

    } catch (Exception e) {

      success = false;

      logger.error(e.getMessage(), e);

      JOptionPane.showMessageDialog(this, "打印出错！\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);

    }

    if (success && printed) {

    }

  }

  private void doGroupPrint() {

  }

  private void doPrintPreview() {

    final List printList = getCheckedList();

    if (printList.size() == 0) {

      JOptionPane.showMessageDialog(this, "请选择需要打印预览的数据 ！", "提示", JOptionPane.INFORMATION_MESSAGE);

      return;

    }

    requestMeta.setFuncId(this.printPreviewButton.getFuncId());

    requestMeta.setPageType(this.compoId + "_L");

    try {

      PrintObject printObject = this.baseDataServiceDelegate.genMainBillPrintObjectFN(printList, requestMeta);

      PrintPreviewer previewer = new PrintPreviewer(printObject) {

        protected void afterSuccessPrint() {

        }

      };

      previewer.preview();

    } catch (Exception e) {

      logger.error(e.getMessage(), e);

      JOptionPane.showMessageDialog(this, "打印预览出错！\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);

    }

  }

  private void doPrintPreviewGroup() {

  }

  private void doPrintSetting() {

    requestMeta.setFuncId(this.printSettingButton.getFuncId());

    requestMeta.setPageType(this.compoId + "_L");

    new PrintSettingDialog(requestMeta);

  }

  private void setButtonStatus() {
    //    addButton.setVisible(SfUtil.canNew(compoId, null));
  }

  public String getcompoId() {
    // TCJLODO Auto-generated method stub
    return compoId;
  }

}