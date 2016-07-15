/**
 * 
 */
package com.ufgov.zc.client.sf.jdfees;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.component.table.JGroupableTable;
import com.ufgov.smartclient.component.table.fixedtable.JFixedTable;
import com.ufgov.smartclient.plaf.BlueLookAndFeel;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.common.ParentWindowAware;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfChargeToTableModelConverter;
import com.ufgov.zc.client.component.AsValComboBox;
import com.ufgov.zc.client.component.JFuncToolBar;
import com.ufgov.zc.client.component.button.AddButton;
import com.ufgov.zc.client.component.button.DeleteButton;
import com.ufgov.zc.client.component.button.EditButton;
import com.ufgov.zc.client.component.button.FuncButton;
import com.ufgov.zc.client.component.button.zc.CommonButton;
import com.ufgov.zc.client.component.sf.fieldeditor.SfEntrustorNewFieldEditor;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.ui.fieldeditor.YearComboBoxFieldeditor;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.sf.charge.SfChargeListPanel;
import com.ufgov.zc.client.sf.entrust.SfEntrustHandler;
import com.ufgov.zc.client.sf.entrustor.SfEntrustorHandler;
import com.ufgov.zc.client.sf.util.SfJdPersonSelectHandler;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.ListUtil;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.client.zc.ButtonStatus;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfPayFees;
import com.ufgov.zc.common.sf.publish.ISfChargeServiceDelegate;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.dto.SfElementConditionDto;
import com.ufgov.zc.common.system.util.ObjectUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * 
 * @author Administrator
 *
 */
public class SfJdFeeMainPanel extends JComponent implements ParentWindowAware {

  /**
   * 
   */
  private static final long serialVersionUID = -8234363884846288885L;

  public static final Logger logger = Logger.getLogger(SfChargeListPanel.class);

  private SfJdFeeMainPanel self = this;

  private Window parentWindow;

  public static final String compoId = "SF_JD_FEES";

  private RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  private ElementConditionDto elementConditionDto = new ElementConditionDto();

  private SfElementConditionDto searchDto = new SfElementConditionDto();

  private ISfChargeServiceDelegate sfChargeServiceDelegate;

  protected IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private ISfEntrustServiceDelegate sfEntrustServiceDelegate;

  private JPanel searchConditonPanel;

  private JGroupableTable entrustFeesTable;

  private JGroupableTable payFeeTable;

  private FuncButton searchButton = new CommonButton("fsearch", "搜索", "search.png");

  private FuncButton clearButton = new CommonButton("fclear", "清空", "clear.png");

  private FuncButton showSearchBtn = new CommonButton("fshowsearch", "收费", "search.png");

  private FuncButton deleteButton = new DeleteButton();

  private FuncButton addButton = new AddButton();

  protected FuncButton editButton = new EditButton();

  private List entustLst = new ArrayList();

  List<AbstractFieldEditor> searchFields = new ArrayList<AbstractFieldEditor>();

  private SfPayFees currentEntrustFee;

  private SfCharge currentPayedFee;

  private HashMap<BigDecimal, SfEntrust> entrustCache = new HashMap<BigDecimal, SfEntrust>();

  private HashMap<BigDecimal, List> payFeesCache = new HashMap<BigDecimal, List>();

  private ArrayList<ButtonStatus> btnStatusList = new ArrayList<ButtonStatus>();

  private boolean showSearchPanel = false;

  static {

    LangTransMeta.init("SF%");

  }

  public SfJdFeeMainPanel() {
    init();
  }

  public SfJdFeeMainPanel(boolean showSearchPanel) {
    this.showSearchPanel = showSearchPanel;
    init();
  }

  public SfJdFeeMainPanel(SfEntrust entrust) {
    searchDto.setEntrust(entrust);
    init();
  }

  public SfJdFeeMainPanel(SfEntrust entrust, boolean showSearchPanel) {
    searchDto.setEntrust(entrust);
    this.showSearchPanel = showSearchPanel;
    init();
  }

  private void init() {
    // TCJLODO Auto-generated method stub

    sfChargeServiceDelegate = (ISfChargeServiceDelegate) ServiceFactory.create(ISfChargeServiceDelegate.class, "sfChargeServiceDelegate");
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    sfEntrustServiceDelegate = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");

    setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), LangTransMeta.translate(compoId), TitledBorder.CENTER,
      TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));
    intiPanel();
    refreshData();
    setButtonStatus();
  }

  private void refreshData() {
    // TCJLODO Auto-generated method stub
    searchDto.setIsPay(SfElementConstants.VAL_N);
    searchDto.setNd(new Integer(requestMeta.getSvNd()));
    updateSearchDto();
    doSearch();
  }

  private void intiPanel() {
    // TCJLODO Auto-generated method stub
    initSearchPanel();
    JPanel mainPanel = initMainPanel();
    JPanel payPanel = initFeePanel();
    this.setLayout(new BorderLayout());

    if (showSearchPanel) {
      add(searchConditonPanel, BorderLayout.NORTH);
      add(payPanel, BorderLayout.SOUTH);
    } else {
      JPanel p = new JPanel();
      p.setLayout(new FlowLayout(SwingConstants.LEFT, 60, 5));
      p.add(showSearchBtn);
      showSearchBtn.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          // TCJLODO Auto-generated method stub
          doShowSearchBtn();
        }
      });
      add(p, BorderLayout.NORTH);
    }
    add(mainPanel, BorderLayout.CENTER);
    Dimension d = SfUtil.getScreenSize();
    setPreferredSize(new Dimension(d.width - 100, d.height - 100));

    onInitFinish();

  }

  protected void doShowSearchBtn() {
    // TCJLODO Auto-generated method stub
    SfJdFeeBigWindowDialog dialog = new SfJdFeeBigWindowDialog(self);
  }

  public void onInitFinish() {

    this.firePropertyChange("initfinish", 0, 1);

  }

  private JPanel initFeePanel() {
    // TCJLODO Auto-generated method stub

    payFeeTable = SwingUtil.createTable(JFixedTable.class);
    payFeeTable.setPreferredScrollableViewportSize(new Dimension(SfUtil.getScreenWidth() - 60, 90));
    payFeeTable.setShowCheckedColumn(false);

    //    SfUtil.FitTableColumns(entrustFeesTable);
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.getViewport().setView(payFeeTable);
    scrollPane.revalidate();
    scrollPane.repaint();

    JFuncToolBar detailBtnBar = new JFuncToolBar();
    detailBtnBar.add(addButton);
    //    detailBtnBar.add(editButton);
    //    detailBtnBar.add(deleteButton);
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TCJLODO Auto-generated method stub
        doAdd();
      }
    });
    editButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TCJLODO Auto-generated method stub
        doEdit();
      }
    });
    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TCJLODO Auto-generated method stub
        doDelete();
      }
    });

    payFeeTable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
          MyTableModel model = (MyTableModel) payFeeTable.getModel();
          int row = payFeeTable.getSelectedRow();
          List viewList = (List) ObjectUtil.deepCopy(ListUtil.convertToTableViewOrderList(model.getList(), payFeeTable));
          SfCharge charge = (SfCharge) viewList.get(row);
          charge.setFees(getEntrustFee());
          new SfJdFeeDialog(self, charge);
        }
      }
    });
    JPanel payPanel = new JPanel();
    payPanel.setLayout(new BorderLayout());
    payPanel.setBorder(BorderFactory.createTitledBorder("交费记录"));
    payPanel.add(scrollPane, BorderLayout.CENTER);
    payPanel.add(detailBtnBar, BorderLayout.SOUTH);
    return payPanel;

  }

  protected void doDelete() {
    // TCJLODO Auto-generated method stub

    currentEntrustFee = getEntrustFee();

    if (currentEntrustFee == null) {
      JOptionPane.showMessageDialog(this, "请选择一个委托！", " 提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    currentPayedFee = getSelectedPayFee();

    if (currentPayedFee == null) {
      JOptionPane.showMessageDialog(this, "请选择一条交费记录！", " 提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

  }

  protected void doEdit() {
    // TCJLODO Auto-generated method stub

    currentEntrustFee = getEntrustFee();

    if (currentEntrustFee == null) {
      JOptionPane.showMessageDialog(this, "请选择一个委托！", " 提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    currentPayedFee = getSelectedPayFee();
    if (currentPayedFee == null) {
      JOptionPane.showMessageDialog(this, "请选择一条交费记录！", " 提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    currentPayedFee.setFees(getEntrustFee());
    new SfJdFeeDialog(self, currentPayedFee);
  }

  protected void doAdd() {
    // TCJLODO Auto-generated method stub

    currentEntrustFee = getEntrustFee();

    if (currentEntrustFee == null) {
      JOptionPane.showMessageDialog(this, "请选择一个委托！", " 提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    SfCharge charge = new SfCharge();
    charge.setFees(currentEntrustFee);

    new SfJdFeeDialog(self, charge);
  }

  private SfPayFees getEntrustFee() {
    int k = entrustFeesTable.getSelectedRow();
    if (k < 0) {
      return null;
    }
    int accordDataRow = entrustFeesTable.convertRowIndexToModel(k);
    MyTableModel model = (MyTableModel) entrustFeesTable.getModel();
    List list = model.getList();
    SfPayFees bean = (SfPayFees) list.get(accordDataRow);
    SfEntrust entrust = entrustCache.get(bean.getEntrust().getEntrustId());
    if (entrust == null) {
      entrust = sfEntrustServiceDelegate.selectByPrimaryKey(bean.getEntrust().getEntrustId(), requestMeta);
      bean.setEntrust(entrust);
      entrustCache.put(entrust.getEntrustId(), entrust);
    }
    bean.setEntrust(entrust);
    this.currentEntrustFee = bean;
    return bean;
  }

  private SfCharge getSelectedPayFee() {
    MyTableModel model = (MyTableModel) payFeeTable.getModel();
    int row = payFeeTable.getSelectedRow();
    if (row < 0)
      return null;
    List viewList = (List) ObjectUtil.deepCopy(ListUtil.convertToTableViewOrderList(model.getList(), payFeeTable));
    SfCharge charge = (SfCharge) viewList.get(row);
    return charge;
  }

  /*
    private List getPayedFeesCheckedList() {

      List<SfCharge> beanList = new ArrayList<SfCharge>();
      MyTableModel model = (MyTableModel) payFeeTable.getModel();
      //Modal的数据
      List list = model.getList();
      Integer[] checkedRows = payFeeTable.getCheckedRows();
      for (Integer checkedRow : checkedRows) {
        int accordDataRow = payFeeTable.convertRowIndexToModel(checkedRow);
        SfCharge bean = (SfCharge) list.get(accordDataRow);
        beanList.add(bean);
      }
      return beanList;
    }

    private List getEntrustFeesCheckedList() {

      List<SfPayFees> beanList = new ArrayList<SfPayFees>();
      MyTableModel model = (MyTableModel) entrustFeesTable.getModel();
      //Modal的数据
      List list = model.getList();
      Integer[] checkedRows = entrustFeesTable.getCheckedRows();
      for (Integer checkedRow : checkedRows) {
        int accordDataRow = entrustFeesTable.convertRowIndexToModel(checkedRow);
        SfPayFees bean = (SfPayFees) list.get(accordDataRow);
        beanList.add(bean);
      }
      return beanList;
    }*/

  private JPanel initMainPanel() {
    // TCJLODO Auto-generated method stub
    entrustFeesTable = SwingUtil.createTable(JFixedTable.class);
    entrustFeesTable.setPreferredScrollableViewportSize(new Dimension(SfUtil.getScreenWidth() - 100, 300));
    entrustFeesTable.setShowCheckedColumn(false);

    //    SfUtil.FitTableColumns(entrustFeesTable);
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.getViewport().setView(entrustFeesTable);
    scrollPane.revalidate();
    scrollPane.repaint();

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createTitledBorder("委托与费用"));
    mainPanel.add(scrollPane, BorderLayout.CENTER);
    return mainPanel;
  }

  private JPanel initSearchPanel() {
    // TCJLODO Auto-generated method stub

    createSearchFields();

    JPanel searchFieldPanel = new JPanel();
    int row = 0;

    int col = 0;

    int colCount = 3;

    searchFieldPanel.setLayout(new GridBagLayout());

    for (int i = 0; i < searchFields.size(); i++) {

      AbstractFieldEditor comp = searchFields.get(i);

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

          comp.setPreferredSize(new Dimension(120,

          comp.getOccRow() * 26));

          searchFieldPanel.add(label, new GridBagConstraints(col,

          row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST,

          GridBagConstraints.NONE, new Insets(4, 0, 4, 4), 0,

          0));

          searchFieldPanel.add(comp, new GridBagConstraints(col + 1,

          row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0,

          GridBagConstraints.WEST,

          GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4,

          4), 0, 0));

          // 将当前所占的行空间偏移量计算上

          row += comp.getOccRow();

          col = 0;

          continue;

        }

        JLabel label = new JLabel(comp.getName());

        comp.setPreferredSize(new Dimension(120, 23));

        searchFieldPanel.add(label, new GridBagConstraints(col, row, 1,

        1, 1.0, 1.0, GridBagConstraints.EAST,

        GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));

        searchFieldPanel.add(comp, new GridBagConstraints(col + 1, row,

        1, 1, 1.0, 1.0, GridBagConstraints.WEST,

        GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5),

        0, 0));

        if (col == colCount * 2 - 2) {

          row++;

          col = 0;

        } else {

          col += 2;

        }

      }

    }

    searchConditonPanel = new JPanel();
    searchConditonPanel.setLayout(new BorderLayout());
    searchConditonPanel.add(searchFieldPanel, BorderLayout.CENTER);

    JPanel jp = new JPanel();
    jp.setLayout(new FlowLayout(SwingConstants.RIGHT));
    jp.add(clearButton);
    jp.add(searchButton);
    searchConditonPanel.add(jp, BorderLayout.SOUTH);
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doSearch();
      }
    });
    clearButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        doClear();
      }
    });

    searchConditonPanel.setBorder(BorderFactory.createTitledBorder("检索条件"));
    return searchConditonPanel;
  }

  protected void doClear() {
    // TCJLODO Auto-generated method stub

    searchDto = new SfElementConditionDto();
    searchDto.setNd(new Integer(requestMeta.getSvNd()));
    updateSearchDto();

  }

  protected void doSearch() {
    // TCJLODO Auto-generated method stub
    List<SfPayFees> entrustPayLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfChargeMapper.getEntrustFees", searchDto,
      requestMeta);
    entrustPayLst = entrustPayLst == null ? new ArrayList<SfPayFees>() : entrustPayLst;
    entrustFeesTable.setModel(SfChargeToTableModelConverter.convertEntrustFeesTableData(entrustPayLst));

    entrustFeesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    //    entrustFeesTable.fitColumnsWidth();//因为界面
    int w = 170;
    for (int i = 0; i < entrustFeesTable.getColumnModel().getColumnCount(); i++) {
      if (i == 0 || i == 1) {
        entrustFeesTable.getColumnModel().getColumn(i).setPreferredWidth(w);
      } else {
        entrustFeesTable.getColumnModel().getColumn(i).setPreferredWidth(100);
      }
    }

    setEntrustFeesablePorperty();
    addEntrustFeesTableLisenter();

    //设置合同默认选中第一行 
    entrustFeesTable.setRowSelectionAllowed(true);
    if (entrustPayLst.size() > 0) {
      if (this.currentEntrustFee == null) {
        entrustFeesTable.setRowSelectionInterval(0, 0);
      } else {
        MyTableModel model = (MyTableModel) entrustFeesTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
          String entrustCode = (String) model.getValueAt(i, 0);
          if (this.currentEntrustFee.getEntrustCode().equals(entrustCode)) {
            int viewRowIndex = entrustFeesTable.convertColumnIndexToView(i);
            entrustFeesTable.setRowSelectionInterval(viewRowIndex, viewRowIndex);
            break;
          }
        }
      }
    } else {
      clearData();
    }
  }

  private void clearData() {
    // TCJLODO Auto-generated method stub
    this.currentPayedFee = null;
    this.currentEntrustFee = null;
    payFeeTable.setModel(SfChargeToTableModelConverter.convertPayFeesTableData(new ArrayList()));
  }

  private void addEntrustFeesTableLisenter() {
    // TCJLODO Auto-generated method stub
    entrustFeesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
          if (entrustFeesTable.getSelectedRows() != null && entrustFeesTable.getSelectedRows().length > 0) {
            int row = entrustFeesTable.getSelectedRow();
            MyTableModel model = (MyTableModel) entrustFeesTable.getModel();
            SfPayFees fees = (SfPayFees) model.getList().get(entrustFeesTable.convertRowIndexToModel(row));
            //设置收费明细表
            refreshFeePayedTable();
          }
        }
      }
    });
  }

  protected void refreshFeePayedTable() {
    // TCJLODO Auto-generated method stub
    currentEntrustFee = getEntrustFee();
    if (this.currentEntrustFee == null)
      return;
    List payedFeeLst = payFeesCache.get(this.currentEntrustFee.getEntrustId());
    if (payedFeeLst == null) {
      payedFeeLst = zcEbBaseServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfChargeMapper.selectByEntrustId",
        this.currentEntrustFee.getEntrustId(), requestMeta);
      payFeesCache.put(this.currentEntrustFee.getEntrustId(), payedFeeLst);
    }
    this.currentEntrustFee.setPayedFeeslst(payedFeeLst == null ? new ArrayList() : payedFeeLst);
    payFeeTable.setModel(SfChargeToTableModelConverter.convertPayFeesTableData(this.currentEntrustFee.getPayedFeeslst()));
    //    payFeeTable.fitColumnsWidth();
    for (int i = 0; i < payFeeTable.getColumnModel().getColumnCount(); i++) {
      payFeeTable.getColumnModel().getColumn(i).setPreferredWidth(100);
    }
  }

  private void setEntrustFeesablePorperty() {
    // TCJLODO Auto-generated method stub

  }

  String getLabelText(AbstractFieldEditor comp) {

    StringBuffer buff = new StringBuffer();

    buff.append("<html><a>&nbsp;");

    buff.append(comp.getName());

    if (comp.getMaxContentSize() <= 0 || comp.getMaxContentSize() == 9999) {

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

  private List<AbstractFieldEditor> createSearchFields() {
    // TCJLODO Auto-generated method stub
    searchFields.clear();

    SfEntrustHandler entrustHandler = new SfEntrustHandler() {

      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfEntrust entrust = (SfEntrust) obj;
          entrust = sfEntrustServiceDelegate.selectByPrimaryKey(entrust.getEntrustId(), requestMeta);
          searchDto.setEntrust(entrust);
          searchDto.setMajor(entrust.getMajor());
          searchDto.setEntrustor(entrust.getEntrustor());
          updateSearchDto();
        }
      }

      public void afterClear() {
        searchDto.setEntrust(new SfEntrust());
        searchDto.setMajor(new SfMajor());
        searchDto.setEntrustor(new SfEntrustor());
        updateSearchDto();
      }
    };
    ElementConditionDto dto = new ElementConditionDto();
    dto.setDattr1("SF_CHARGE");
    ForeignEntityFieldEditor entrust = new ForeignEntityFieldEditor(entrustHandler.getSqlId(), dto, 20, entrustHandler,
      entrustHandler.getColumNames(), LangTransMeta.translate(SfCharge.COL_ENTRUST_CODE), "entrust.code");

    SfEntrustorHandler entrustorHandler = new SfEntrustorHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        for (Object obj : selectedDatas) {
          SfEntrustor entrustor = (SfEntrustor) obj;
          searchDto.setEntrustor(entrustor);
          searchDto.setEntrust(new SfEntrust());
          updateSearchDto();
        }
      }

      public void afterClear() {
        searchDto.setEntrustor(new SfEntrustor());
        updateSearchDto();
      }
    };
    SfEntrustorNewFieldEditor entrustor = new SfEntrustorNewFieldEditor(entrustorHandler.getSqlId(), 20, entrustorHandler,
      entrustorHandler.getColumNames(), LangTransMeta.translate(SfEntrust.COL_ENTRUSTOR_NAME), "entrustor.name");

    final ElementConditionDto majorPersonDto = new ElementConditionDto();
    AsValFieldEditor majorCode = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "major.majorCode", "SF_VS_MAJOR", true) {
      @Override
      protected void afterChange(AsValComboBox field) {
        if (field.getSelectedAsVal() == null) {
          majorPersonDto.setDattr1(null);
          return;
        }
        String valId = field.getSelectedAsVal().getValId();
        majorPersonDto.setDattr1(valId);
      }
    };

    SfJdPersonSelectHandler jdFzrHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfJdPerson user = (SfJdPerson) selectedDatas.get(0);
          searchDto.getEntrust().setJdFzr(user.getAccount());
          searchDto.getEntrust().setJdFzrName(user.getName());
          updateSearchDto();
        }
      }

      public void afterClear() {
        searchDto.getEntrust().setJdFzr(null);
        searchDto.getEntrust().setJdFzrName(null);
        updateSearchDto();
      }

    };

    majorPersonDto.setNd(requestMeta.getSvNd());
    majorPersonDto.setCoCode(requestMeta.getSvCoCode());
    ForeignEntityFieldEditor jdFzr = new ForeignEntityFieldEditor(jdFzrHandler.getSqlId(), majorPersonDto, 20, jdFzrHandler,
      jdFzrHandler.getColumNames(), LangTransMeta.translate(SfEntrust.COL_JD_FZR), "entrust.jdFzrName");

    SfJdPersonSelectHandler jdFhrHandler = new SfJdPersonSelectHandler() {
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if (selectedDatas != null && selectedDatas.size() > 0) {
          SfJdPerson user = (SfJdPerson) selectedDatas.get(0);
          searchDto.getEntrust().setJdFhr(user.getAccount());
          searchDto.getEntrust().setJdFhrName(user.getName());
          updateSearchDto();
        }
      }

      public void afterClear() {
        searchDto.getEntrust().setJdFhr(null);
        searchDto.getEntrust().setJdFhrName(null);
        updateSearchDto();
      }
    };
    ForeignEntityFieldEditor jdFhr = new ForeignEntityFieldEditor(jdFhrHandler.getSqlId(), majorPersonDto, 20, jdFhrHandler,
      jdFhrHandler.getColumNames(), LangTransMeta.translate(SfEntrust.COL_JD_FHR), "entrust.jdFhrName");

    AsValFieldEditor isPay = new AsValFieldEditor("完成交费", "isPay", SfElementConstants.VS_Y_N, true);
    DateFieldEditor wtBeginTime = new DateFieldEditor("委托时间->开始", "wtBeginTime");
    DateFieldEditor wtEndTime = new DateFieldEditor("委托时间->结束", "wtEndTime");
    DateFieldEditor payBeginTime = new DateFieldEditor("交费时间->开始", "payBeginTime");
    DateFieldEditor payEndTime = new DateFieldEditor("交费时间->结束", "payEndTime");

    YearComboBoxFieldeditor nd = new YearComboBoxFieldeditor("年度", "nd", true, true);

    searchFields.add(entrust);
    searchFields.add(entrustor);
    searchFields.add(isPay);

    searchFields.add(majorCode);
    searchFields.add(jdFzr);
    searchFields.add(jdFhr);

    searchFields.add(wtBeginTime);
    searchFields.add(wtEndTime);
    searchFields.add(nd);

    searchFields.add(payBeginTime);
    searchFields.add(payEndTime);

    return searchFields;
  }

  protected void updateSearchDto() {
    // TCJLODO Auto-generated method stub
    for (AbstractFieldEditor editor : searchFields) {
      editor.setEditObject(searchDto);
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TCJLODO Auto-generated method stub

    SwingUtilities.invokeLater(new Runnable() {

      public void run() {

        try {

          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

          UIManager.setLookAndFeel(new BlueLookAndFeel());

        } catch (Exception e) {

          e.printStackTrace();

        }
        SfJdFeeMainPanel bill = new SfJdFeeMainPanel(false);

        JFrame frame = new JFrame("frame");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = SfUtil.getScreenSize();
        frame.setSize(d.width - 50, d.height - 50);

        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(bill);

        frame.setVisible(true);

      }

    });
  }

  @Override
  public void setParentWindow(Window window) {
    // TCJLODO Auto-generated method stub
    this.parentWindow = window;
  }

  @Override
  public Window getParentWindow() {
    // TCJLODO Auto-generated method stub
    return this.parentWindow;
  }

  public String getcompoId() {
    // TCJLODO Auto-generated method stub
    return compoId;
  }

  public void refreshed() {
    // TCJLODO Auto-generated method stub
    if (this.currentEntrustFee != null)
      payFeesCache.remove(this.currentEntrustFee.getEntrustId());
    doSearch();

  }

  private void setButtonStatus() {
    if (this.btnStatusList.size() == 0) {

      ButtonStatus bs = new ButtonStatus();
      bs.setButton(this.addButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_ALL);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.deleteButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_ALL);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.editButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_ALL);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.searchButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_ALL);
      bs.addPageStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);

      bs = new ButtonStatus();
      bs.setButton(this.clearButton);
      bs.addPageStatus(ZcSettingConstants.PAGE_STATUS_ALL);
      bs.addBillStatus(ZcSettingConstants.BILL_STATUS_ALL);
      btnStatusList.add(bs);

    }

    ZcUtil.setButtonEnable(this.btnStatusList, null, ZcSettingConstants.PAGE_STATUS_BROWSE, "SF_JD_FEES", new Long(-1));

  }
}
