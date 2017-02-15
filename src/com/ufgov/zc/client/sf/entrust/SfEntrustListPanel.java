/**
 * 
 */
package com.ufgov.zc.client.sf.entrust;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
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
import com.ufgov.zc.client.common.converter.sf.SfEntrustToTableModelConverter;
import com.ufgov.zc.client.component.JFuncToolBar;
import com.ufgov.zc.client.component.button.zc.CommonButton;
import com.ufgov.zc.client.component.ui.AbstractDataDisplay;
import com.ufgov.zc.client.component.ui.AbstractEditListBill;
import com.ufgov.zc.client.component.ui.AbstractSearchConditionArea;
import com.ufgov.zc.client.component.ui.MultiDataDisplay;
import com.ufgov.zc.client.component.ui.SaveableSearchConditionArea;
import com.ufgov.zc.client.component.ui.TableDisplay;
import com.ufgov.zc.client.component.ui.conditionitem.AbstractSearchConditionItem;
import com.ufgov.zc.client.component.ui.conditionitem.ConditionFieldConstants;
import com.ufgov.zc.client.component.ui.conditionitem.SearchConditionUtil;
import com.ufgov.zc.client.print.PrintPreviewer;
import com.ufgov.zc.client.print.PrintSettingDialog;
import com.ufgov.zc.client.print.Printer;
import com.ufgov.zc.client.sf.dataflow.SfDataFlowDialog;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.ListUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.commonbiz.model.SearchCondition;
import com.ufgov.zc.common.commonbiz.publish.IBaseDataServiceDelegate;
import com.ufgov.zc.common.console.model.AsEmp;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfZongheZhiban;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfZongheZhibanServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.WFConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.dto.PrintObject;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * 司法委托
 * @author Administrator
 *
 */
public class SfEntrustListPanel extends AbstractEditListBill implements ParentWindowAware {

  /**
   * 
   */
  private static final long serialVersionUID = -8537697617637474855L;

  public static final Logger logger = Logger.getLogger(SfEntrustListPanel.class);

  private SfEntrustListPanel self = this;
  
  private CommonButton zhibanBtn=new CommonButton("fzhiban", "值班", "group_edit.png");

  private Window parentWindow;

  public static final String compoId = "SF_ENTRUST";

  private RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private ElementConditionDto elementConditionDto = new ElementConditionDto();

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  private IBaseDataServiceDelegate baseDataServiceDelegate;
  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;
  
  protected ISfZongheZhibanServiceDelegate sfZongheZhibanServiceDelegate;
  
  private  JLabel infoJLabel=new JLabel();

  public Window getParentWindow() {

    return parentWindow;

  }

  public void setParentWindow(Window parentWindow) {

    this.parentWindow = parentWindow;

  }

  public SfEntrustListPanel() {
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");
    baseDataServiceDelegate = (IBaseDataServiceDelegate) ServiceFactory.create(IBaseDataServiceDelegate.class, "baseDataServiceDelegate");
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfZongheZhibanServiceDelegate = (ISfZongheZhibanServiceDelegate) ServiceFactory.create(ISfZongheZhibanServiceDelegate.class, "sfZongheZhibanServiceDelegate");
    UIUtilities.asyncInvoke(new DefaultInvokeHandler<List<SearchCondition>>() {

      @Override
      public List<SearchCondition> execute() throws Exception {

        List<SearchCondition> needDisplaySearchConditonList = SearchConditionUtil.getNeedDisplaySearchConditonList(WorkEnv.getInstance().getCurrUserId(), SfEntrust.TAB_ID);

        return needDisplaySearchConditonList;

      }

      @Override
      public void success(List<SearchCondition> needDisplaySearchConditonList) {

        List<TableDisplay> showingDisplays = SearchConditionUtil.getNeedDisplayTableDisplay(needDisplaySearchConditonList);

        init(createDataDisplay(showingDisplays), null);//调用父类方法

        revalidate();

        repaint();

      }

    });

    requestMeta.setCompoId(compoId);
  }

  private AbstractDataDisplay createDataDisplay(List<TableDisplay> showingDisplays) {

    return new DataDisplay(SearchConditionUtil.getAllTableDisplay(SfEntrust.TAB_ID), showingDisplays,

    createTopConditionArea(), true);//true:显示收索条件区 false：不显示收索条件区

  }

  private AbstractSearchConditionArea topSearchConditionArea;

  private AbstractSearchConditionArea createTopConditionArea() {

    Map defaultValueMap = new HashMap();

    defaultValueMap.put(ConditionFieldConstants.ND, "" + requestMeta.getSvNd());

    topSearchConditionArea = new SaveableSearchConditionArea(SfEntrust.SEARCH_ID, null, true, defaultValueMap, null);

    AbstractSearchConditionItem item = this.topSearchConditionArea.getCondItemsByCondiFieldCode(ConditionFieldConstants.ND);
    /* AsVal val=new AsVal();
     val.setVal(""+requestMeta.getSvNd());
     val.setValId(""+requestMeta.getSvNd());
     item.setValue(val);*/
    return topSearchConditionArea;

  }

  private final class DataDisplay extends MultiDataDisplay {

    private DataDisplay(List<TableDisplay> displays, List<TableDisplay> showingDisplays, AbstractSearchConditionArea conditionArea,

    boolean showConditionArea) {

      super(displays, showingDisplays, conditionArea, showConditionArea, SfEntrust.TAB_ID);

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

              SfEntrust entrust = (SfEntrust) viewList.get(row);
              entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
              new SfDataFlowDialog(compoId, entrust, self);

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
      
      elementConditionDto.setZhiban(SfUtil.isZhiban());

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
          return SfEntrustToTableModelConverter.convertEntrustLst(self.sfEntrustServiceDelegate.getEntrustLst(elementConditionDto, requestMeta));

        }

        @Override
        public void success(TableModel model) {

          tableDisplay.setTableModel(model);
          //        SwingUtil.setTableCellRenderer(topDataDisplay.getActiveTableDisplay().getTable(), SfEntrust.COL_IS_ACCEPT, new AsValCellRenderer("VS_Y/N"));
          //        SwingUtil.setTableCellRenderer(topDataDisplay.getActiveTableDisplay().getTable(), SfEntrust.COL_STATUS, new AsValCellRenderer(SfEntrust.SF_VS_ENTRUST_STATUS));
          //        SwingUtil.setTableCellRenderer(topDataDisplay.getActiveTableDisplay().getTable(), SfEntrust.COL_MAJOR_NAME, new AsValCellRenderer(SfMajor.SF_VS_MAJOR));
          //        setButtonsVisiable();
          setButtonStatus();
          deleteGarbidge();
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
        SfEntrustListPanel bill = new SfEntrustListPanel();

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
       
    toolBar.add(zhibanBtn);
    zhibanBtn.setText("值班");//值班
    zhibanBtn.setToolTipText("值班");

    // toolBar.add(updateButton);

    //    toolBar.add(deleteButton);

    toolBar.add(helpButton);
    setInfoLabel(); 
    toolBar.add(infoJLabel);

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

    zhibanBtn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        doZhiban();

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

  private void setInfoLabel() {
    List<SfZongheZhiban> zbLst=getZiBanInfo();
    setInfoLabel(zbLst);
  }

  private void setInfoLabel(List<SfZongheZhiban> zbLst) {
    if(zbLst==null || zbLst.size()==0){
      infoJLabel.setText("当前无值班人信息.");
      return;
    }
    ElementConditionDto dto=new ElementConditionDto();
    for(int i=0;i<zbLst.size();i++){
      SfZongheZhiban zb=zbLst.get(i);
      dto.getPmAdjustCodeList().add(zb.getUserId());
    }
    if(dto.getPmAdjustCodeList().size()==0){
      infoJLabel.setText("当前无值班人信息.");
      return;
    } 
    List<AsEmp> empLst= zcEbBaseServiceDelegate.queryDataForList("AsEmp.getAsEmpLst", dto, requestMeta);

    if(empLst==null || empLst.size()==0 ){
      infoJLabel.setText("当前无值班人信息.");
      return;
    }
    StringBuffer sb=new StringBuffer(),userNames=new StringBuffer(),phones=new StringBuffer();
    for(int i=0;i<empLst.size();i++){
      AsEmp emp=empLst.get(i);
      if(userNames.length()>0){
        userNames.append(" ");
      }
      userNames.append(emp.getEmpName());
      if(phones.length()>0){
        phones.append(" ");
      }
      phones.append(emp.getPhone()==null?"":emp.getPhone());
    }
    if(userNames.length()==0){
      infoJLabel.setText("当前无值班人信息.");
      return;      
    }
    if(phones.length()==0){
      phones.append("暂无");
    }
    sb.append("【鉴定中心值班人】 ").append(userNames).append("  【电话】 ").append(phones);
    infoJLabel.setText(sb.toString());
  }

  private List<SfZongheZhiban> getZiBanInfo() {

    ElementConditionDto dto=new ElementConditionDto();
    dto.setNd(requestMeta.getSvNd());
    if(SfUtil.isWtf()){
      dto.setCoCode("000");
    }else{
      dto.setCoCode(requestMeta.getSvCoCode());
    }
    List<SfZongheZhiban> zbLst=zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfZongheZhibanMapper.selectCurrentZhiban", dto, requestMeta);
     
    return zbLst;
  }

  /**
   * 综合受理是每天有人值班，进行办理相关的业务
   */
  protected void doZhiban() {
    List<SfZongheZhiban> zbLst=getZiBanInfo();
    
    if(zbLst!=null && zbLst.size()>0){
      
      for(int i=0;i<zbLst.size();i++){
        SfZongheZhiban zb=zbLst.get(i);
        if(zb.getUserId().equals(requestMeta.getSvUserID())){
          JOptionPane.showMessageDialog(this, "您当前已经在值班了！", "提示", JOptionPane.INFORMATION_MESSAGE);
          return;           
        }
      }
    }

    int num = JOptionPane.showConfirmDialog(this, "您确定要值班吗", "确认", 0);
    if (num == JOptionPane.YES_OPTION) {
      SfZongheZhiban zb=new SfZongheZhiban();
      zb.setNd(requestMeta.getSvNd());
      zb.setCoCode(requestMeta.getSvCoCode());
      zb.setUserId(requestMeta.getSvUserID());
      sfZongheZhibanServiceDelegate.saveFN(zb, requestMeta);
      //刷新当前页签数据
      elementConditionDto.setZhiban(true);
      refreshCurrentTabData();
      zhibanBtn.setText("值班中");//值班
      zhibanBtn.setToolTipText("值班中"); 
      setInfoLabel();
    }
    
    
  }

  public void refreshCurrentTabData() {

    topSearchConditionArea.doSearch();

  }

  public void refreshCurrentTabData(List beanList) {

    topDataDisplay.getActiveTableDisplay().getTable().setModel(SfEntrustToTableModelConverter.convertEntrustLst(beanList));

  }

  public List getCheckedList() {

    List<SfEntrust> beanList = new ArrayList<SfEntrust>();

    JGroupableTable table = topDataDisplay.getActiveTableDisplay().getTable();

    MyTableModel model = (MyTableModel) table.getModel();

    //Modal的数据

    List list = model.getList();

    Integer[] checkedRows = table.getCheckedRows();

    for (Integer checkedRow : checkedRows) {

      int accordDataRow = table.convertRowIndexToModel(checkedRow);

      SfEntrust bean = (SfEntrust) list.get(accordDataRow);

      beanList.add(bean);

    }

    return beanList;

  }

  private void doAdd() {

    new SfEntrustDialog(self, new ArrayList(1), -1, topDataDisplay.getActiveTableDisplay().getStatus());

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

      SfEntrust bean = (SfEntrust) beanList.get(i);

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
    addButton.setVisible(SfUtil.canNew(compoId, null));
    if(SfUtil.isZhiban()){
      zhibanBtn.setText("值班中");//值班
      zhibanBtn.setToolTipText("值班中");      
    }else{
      zhibanBtn.setText("值班");//值班
      zhibanBtn.setToolTipText("值班");       
    }

  }

  public String getcompoId() {
    // TCJLODO Auto-generated method stub
    return compoId;
  }

  //删除桌面上因word原因导致产生的日志文件，文件名称类似hs_err_pid10204.log
  void deleteGarbidge(){
    FileSystemView fsv = FileSystemView.getFileSystemView();
    File desktop=fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
    System.out.println(desktop.getPath()); 
    boolean find=false;
          File fa[] = desktop.listFiles();
          for (int i = 0; i < fa.length; i++) {
             File fs = fa[i];
              if (!fs.isDirectory()) { 
               if(fs.getName().startsWith("hs_err_") && fs.getName().endsWith("log")){
                 System.out.println(fs.getName());
                 find=true;
                 try {
                   fs.delete();
                   
                } catch (Exception e) {
                  // TODO: handle exception
                  logger.error(e.getMessage(), e);
                }
               }
             }
          }
          if(find){
           // desktop.notifyAll();
          }
  } 
}
