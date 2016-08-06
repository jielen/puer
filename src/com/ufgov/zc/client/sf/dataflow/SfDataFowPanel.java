/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ufgov.smartclient.plaf.BlueLookAndFeel;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ListCursor;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.ui.AbstractEditListBill;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.DateFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.IntFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.agreement.SfAgreementEditPanel;
import com.ufgov.zc.client.sf.appendmaterialnotice.SfAppendMaterialNoticeEditPanel;
import com.ufgov.zc.client.sf.appendmaterialnotice.SfAppendMaterialNoticeListPanel;
import com.ufgov.zc.client.sf.component.JClosableTabbedPane;
import com.ufgov.zc.client.sf.component.JDragPanel;
import com.ufgov.zc.client.sf.component.JEnableImageButton;
import com.ufgov.zc.client.sf.dossier.SfDossierEditPanel;
import com.ufgov.zc.client.sf.dossier.SfDossierListPanel;
import com.ufgov.zc.client.sf.jdresult.SfJdRecordEditPanel;
import com.ufgov.zc.client.sf.receipt.SfReceiptEditPanel;
import com.ufgov.zc.client.sf.receipt.SfReceiptListPanel;
import com.ufgov.zc.client.sf.report.SfJdReportEditPanel;
import com.ufgov.zc.client.sf.util.ResourceUtil;
import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfDataFowPanel extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 257121107953297793L;

  //  private BigDecimal entrustId = new BigDecimal(2);//当时为了调试程序，在本界面直接启动，所以设置了这个值2，运行环境时外面传递进来的

  private String currentCompo = null;

  private ListCursor listCursor;

  private AbstractEditListBill listPanel;

  private SfEntrust entrust = null;

  private JClosableTabbedPane tabPanel = new JClosableTabbedPane(JTabbedPane.BOTTOM);

  private SfDataFowPanel self = this;

  private GkBaseDialog parentWindow;

  private ISfEntrustServiceDelegate entrustService;

  private IZcEbBaseServiceDelegate zcEbBaseServiceDelegate;

  private RequestMeta meta;

  private Hashtable<String, JComponent> nodePanels = new Hashtable<String, JComponent>();

  private ArrayList<AbstractFieldEditor> fieldEditors;

  private List<String> userCompoLst;

  private List<SfFlowNode> nodeLst;

  private HashMap<String, JEnableImageButton> nodeBtnMap = new HashMap<String, JEnableImageButton>();

  private HashMap<String, ISfFlowNodeBusiness> nodeBusinessMap = new HashMap<String, ISfFlowNodeBusiness>();

  private List<Class> wordPanelLst = new ArrayList<Class>();

  private RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

  public SfDataFowPanel(SfEntrust entrust) {
    // TCJLODO Auto-generated constructor stub
    super();
    this.entrust = entrust;
    init();
  }

  public SfDataFowPanel(GkBaseDialog window, String compoId, SfEntrust entrust, AbstractEditListBill listPanel) {
    super();
    this.entrust = entrust;
    currentCompo = compoId;
    parentWindow = window;
    this.listPanel = listPanel;
    init();

    //setSelectedTab(); 将其放到dialog层来调用，dialog显示后再调用，用于有word控件时的swt错误
  }

  private void init() {
    // TCJLODO Auto-generated method stub
    entrustService = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
    meta = WorkEnv.getInstance().getRequestMeta();

    //初始化当前用户可以查看的compo列表
    initUserCompos();
    //创建界面
    initPanel();

    updateFieldEditors();
  }

  /**
   * 设置当前选中的页签
   */
  void setSelectedTab() {
    // TCJLODO Auto-generated method stub
    setNodePanelSelected();
  }

  private void setNodePanelSelected() {
    // TCJLODO Auto-generated method stub
    JComponent nodePanel = null;
    if (nodePanels.containsKey(currentCompo)) {
      nodePanel = nodePanels.get(currentCompo);
    } else {
      for (SfFlowNode node : nodeLst) {
        if (node.getCompoId().equals(currentCompo)) {
          node.getNodeBusiness().excute(this, node, entrust, meta);
          nodePanel = nodePanels.get(currentCompo);
          break;
        }
      }
    }
    boolean find = false;
    for (int i = 0; i < tabPanel.getTabCount(); i++) {
      JComponent c = (JComponent) tabPanel.getComponentAt(i);
      if (c.equals(nodePanel)) {
        find = true;
        break;
      }
    }
    if (nodePanel != null) {
      if (!find) {
        tabPanel.addTab(LangTransMeta.translate(currentCompo), nodePanel);
        _refreshWord(nodePanel);
      }
      tabPanel.setSelectedComponent(nodePanel);
    }
  }

  public void addTab(JComponent component, String compoId) {

//    if (!nodePanels.containsKey(compoId) && !isWordPanel(component)) {

        if (!nodePanels.containsKey(compoId)) {

      nodePanels.put(compoId, component);
    }

    boolean find = false;
    for (int i = 0; i < tabPanel.getTabCount(); i++) {
      JComponent c = (JComponent) tabPanel.getTabComponentAt(i);
      if (component.equals(c)) {
        find = true;
        break;
      }
    }
    if (!find) {
      tabPanel.addTab(LangTransMeta.translate(compoId), component);
      //      self.getParentDlg().validate();
      if (getParentDlg().isVisible()) {
        _refreshWord(component);
      }
    }
    tabPanel.setSelectedComponent(component);
  }

  /**
   * 当前部件是否含有word控件
   * @param component
   * @return
   */
  private boolean isWordPanel(JComponent component) {
    // TCJLODO Auto-generated method stub
    if (component instanceof SfAgreementEditPanel || component instanceof SfReceiptEditPanel || component instanceof SfAppendMaterialNoticeEditPanel
      || component instanceof SfDossierEditPanel || component instanceof SfJdRecordEditPanel || component instanceof SfJdReportEditPanel)
      return true;
    return false;
  }

  public void removeTab(JComponent component, String compoId) {
    if (nodePanels.containsKey(compoId)) {
      nodePanels.remove(component);
    }
    tabPanel.remove(component);
  }

  public JComponent getTabComponent(String compoId) {
    if (compoId.equals("SF_AGREEMENT")) {
      for (int i = 0; i < tabPanel.getTabCount(); i++) {
        JComponent c = (JComponent) tabPanel.getComponentAt(i);
        if (c instanceof SfAgreementEditPanel) {
          return c;
        }
      }
    } else if (compoId.equals("SF_RECEIPT")) {
      for (int i = 0; i < tabPanel.getTabCount(); i++) {
        JComponent c = (JComponent) tabPanel.getComponentAt(i);
        if (c instanceof SfReceiptEditPanel || c instanceof SfReceiptListPanel) {
          return c;
        }
      }
    } else if (compoId.equals("SF_APPEND_MATERIAL_NOTICE")) {
      for (int i = 0; i < tabPanel.getTabCount(); i++) {
        JComponent c = (JComponent) tabPanel.getComponentAt(i);
        if (c instanceof SfAppendMaterialNoticeEditPanel || c instanceof SfAppendMaterialNoticeListPanel) {
          return c;
        }
      }
    } else if (compoId.equals("SF_DOSSIER")) {
      for (int i = 0; i < tabPanel.getTabCount(); i++) {
        JComponent c = (JComponent) tabPanel.getComponentAt(i);
        if (c instanceof SfDossierEditPanel || c instanceof SfDossierListPanel) {
          return c;
        }
      }
    }
    return nodePanels.get(compoId);
  }

  public void refresh(BigDecimal entrustId) {
    entrust = SfDataFlowUtil.getEntrust(entrustId);
    Iterator<String> compos = nodeBtnMap.keySet().iterator();
    while (compos.hasNext()) {
      String compo = compos.next();
      JEnableImageButton btn = nodeBtnMap.get(compo);
      ISfFlowNodeBusiness nodebusiness = nodeBusinessMap.get(compo);
      btn.setEnable(nodebusiness.isEnable(entrust, requestMeta));
    }
    updateFieldEditors();
  }

  public void setSelectedTab(String compoId) {
    this.currentCompo = compoId;
    setSelectedTab();
  }

  public void setSelectedTab(String compoId, JComponent componet) {
    this.currentCompo = compoId;
    boolean find = false;
    for (int i = 0; i < tabPanel.getTabCount(); i++) {
      JComponent c = (JComponent) tabPanel.getComponentAt(i);
      if (c.equals(componet)) {
        find = true;
        break;
      }
    }
    if (componet != null) {
      if (!find) {
        tabPanel.addTab(LangTransMeta.translate(currentCompo), componet);
      }
      tabPanel.setSelectedComponent(componet);
    }
  }

  private void initUserCompos() {
    // TCJLODO Auto-generated method stub
    userCompoLst = zcEbBaseServiceDelegate.queryDataForList("asMenuCompo.getCompoIdsByUserInfo", meta, meta);
    userCompoLst = userCompoLst == null ? new ArrayList<String>() : userCompoLst;
  }

  /*private void initEntrust() {
    // TCJLODO Auto-generated method stub
    entrust = entrustService.selectByPrimaryKey(entrustId, meta);
    if (entrust == null) {
      entrust = new SfEntrust();
    }
  }*/

  private JDragPanel createHeadPanel() {
    // TCJLODO Auto-generated method stub
    fieldEditors = new ArrayList<AbstractFieldEditor>();

    TextFieldEditor code = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_CODE), "code");
    TextFieldEditor name = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_NAME), "name");
    AsValFieldEditor status = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_STATUS), "status", "SF_VS_ENTRUST_STATUS");

    TextFieldEditor entrustor = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_ENTRUSTOR_NAME), "entrustor.name");
    TextFieldEditor entrustorAddress = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.ADDRESS), "entrustor.address");
    TextFieldEditor entrustorLinkMan = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.LINK_MAN), "entrustor.linkMan");
    TextFieldEditor entrustorTel = new TextFieldEditor(LangTransMeta.translate(SfEntrustor.LINK_TEL), "entrustor.linkTel");

    DateFieldEditor wtDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_WT_DATE), "wtDate");

    TextFieldEditor sjr = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR), "sjr");
    TextFieldEditor sjrTel = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_TEL), "sjrTel");
    TextFieldEditor sjrZjType = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_TYPE), "sjrZjType");
    TextFieldEditor sjrZjCode = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_SJR_ZJ_CODE), "sjrZjCode");

    AsValFieldEditor majorCode = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME), "majorCode", "SF_VS_MAJOR");
    AsValFieldEditor jdOrg = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_ORG), "jdOrg", SfElementConstants.VS_SF_ORG);

    TextFieldEditor jdFzr = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_FZR), "jdFzrName");

    TextAreaFieldEditor jdHistory = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_HISTORY), "jdHistory", 500, 3, 5);
    TextAreaFieldEditor jdRequire = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_JD_REQUIRE), "jdRequire", 1000, 4, 5);
    TextAreaFieldEditor remark = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_REMARK), "remark", 100, 2, 5);
    AsValFieldEditor isCxjd = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_IS_CXJD), "isCxjd", "VS_Y/N");
    TextAreaFieldEditor brief = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_BRIEF), "brief", 100, 3, 5);
    TextFieldEditor inputor = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_INPUTOR), "inputorName");
    DateFieldEditor inputDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_INPUT_DATE), "inputDate");
    TextFieldEditor acceptor = new TextFieldEditor(LangTransMeta.translate(SfEntrust.COL_ACCEPTOR), "acceptorName");
    DateFieldEditor acceptDate = new DateFieldEditor(LangTransMeta.translate(SfEntrust.COL_ACCEPT_DATE), "acceptDate");
    AsValFieldEditor isAccept = new AsValFieldEditor(LangTransMeta.translate(SfEntrust.COL_IS_ACCEPT), "isAccept", "VS_Y/N");
    TextAreaFieldEditor notAcceptReason = new TextAreaFieldEditor(LangTransMeta.translate(SfEntrust.COL_NOT_ACCEPT_REASON), "notAcceptReason", 100,
      3, 5);
    IntFieldEditor nd = new IntFieldEditor(LangTransMeta.translate(SfEntrust.COL_ND), "nd");

    TextFieldEditor jdTarget = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_NAME), "jdTarget.name");
    IntFieldEditor jdTargetAge = new IntFieldEditor(LangTransMeta.translate(SfJdTarget.COL_AGE), "jdTarget.age");
    AsValFieldEditor jdTargetSex = new AsValFieldEditor(LangTransMeta.translate(SfJdTarget.COL_SEX), "jdTarget.sex", SfElementConstants.VS_SEX);
    TextFieldEditor jdTargetIdName = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_NAME), "jdTarget.idName");
    TextFieldEditor jdTargetIdCode = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ID_CODE), "jdTarget.idCode");
    TextFieldEditor jdTargetPhone = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_PHONE), "jdTarget.phone");
    TextFieldEditor jdTargetAddress = new TextFieldEditor(LangTransMeta.translate(SfJdTarget.COL_ADDRESS), "jdTarget.address");

    fieldEditors.add(new NewLineFieldEditor());

    fieldEditors.add(code);
    fieldEditors.add(name);
    fieldEditors.add(isAccept);

    fieldEditors.add(entrustor);
    fieldEditors.add(wtDate);
    fieldEditors.add(sjr);

    fieldEditors.add(majorCode);
    //    fieldEditors.add(jdOrg);
    fieldEditors.add(jdFzr);
    fieldEditors.add(sjrTel);

    SfClientUtil util = new SfClientUtil();

    return util.createPanel(fieldEditors, 3, parentWindow);
  }

  private JDragPanel createDataFowPanel() {
    // TCJLODO Auto-generated method stub
    JDragPanel iconsPanel = new JDragPanel(parentWindow);
    FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 40, 60);
    iconsPanel.setLayout(fl);

    JPanel pp = new JPanel();
    pp.setPreferredSize(new Dimension(800, 400));
    pp.setLayout(new GridLayout(2, 5));

    try {
      SfClientUtil cu = new SfClientUtil();
      //      nodeLst = cu.loadModelNode("/com/ufgov/zc/client/sf/dataflow/sf_flow_node_config.xml");
      nodeLst = cu.loadModelNode("sf_flow_node_config.xml");
      for (final SfFlowNode node : nodeLst) {
    	  System.out.println(node.getCompoId());
        for (int i = 0; i < userCompoLst.size(); i++) {
          String compo = userCompoLst.get(i);
          if (compo.equals(node.getCompoId())) {
            JEnableImageButton nodeImageBtn = new JEnableImageButton(ResourceUtil.getImageIcon(node.getIcon()));
            //            JLabel imageLb=new JLabel(ResourceUtil.getImageIcon(node.getIcon()));
            nodeImageBtn.setToolTipText(node.getToolTip());
            final ISfFlowNodeBusiness nodebusiness = node.getNodeBusiness();
            nodeImageBtn.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent e) {
                if (((JEnableImageButton) e.getComponent()).isEnable()) {
                  nodebusiness.excute(self, node, entrust, meta);
                }
              }
            });
            nodeImageBtn.setEnable(nodebusiness.isEnable(entrust, requestMeta));
            JLabel lb = new JLabel();
            lb.setHorizontalAlignment(JLabel.CENTER);
            lb.setText(LangTransMeta.translate(node.getCompoId()));

            JPanel p1 = new JPanel();
            //            p1.setBorder(BorderFactory.createLineBorder(Color.red));
            //            nodeImageBtn.setBorder(BorderFactory.createLineBorder(Color.yellow));
            JPanel p3 = new JPanel();
            p3.setLayout(new FlowLayout(FlowLayout.CENTER));
            p3.add(nodeImageBtn);
            p1.setLayout(new BorderLayout());
            p1.add(p3, BorderLayout.CENTER);
            p1.add(lb, BorderLayout.SOUTH);

            //            iconsPanel.add(p1);
            JPanel p2 = new JPanel();
            p2.setLayout(new BorderLayout());
            p2.add(p1, BorderLayout.NORTH);
            pp.add(p2);

            nodeBtnMap.put(compo, nodeImageBtn);
            nodeBusinessMap.put(compo, nodebusiness);
            break;
          }
        }
      }
    } catch (SfBusinessException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
      JOptionPane.showMessageDialog(parentWindow, "创建图形面板出错！\n" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
      parentWindow.dispose();
    }
    iconsPanel.add(pp);
    return iconsPanel;
  }

  private void initPanel() {
    //创建上部panel
    JDragPanel headPanel = createHeadPanel();
    //创建图标panel
    JDragPanel flowPanel = createDataFowPanel();

    JTabbedPane jt = new JTabbedPane();
    jt.addTab("相关业务", flowPanel);

    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "鉴定信息", TitledBorder.CENTER, TitledBorder.TOP, new Font(
      "宋体", Font.BOLD, 18), Color.BLUE));
    panel.setLayout(new BorderLayout());
    panel.add(headPanel, BorderLayout.NORTH);
    panel.add(jt, BorderLayout.CENTER);

    System.out.println(panel.getPreferredSize().width + " " + panel.getPreferredSize().height);
    //       tabPanel.addTabNotClose("全部", p);
    //    tabPanel.addTab("全部0", new JButton("hello0"));
    tabPanel.addTabNotClose("全部", panel);
    //    tabPanel.addTab("全部1", new JButton("hello1"));
    //    tabPanel.addTab("全部2", new JButton("hello2"));
    //    tabPanel.addTab("全部3", new JButton("hello3"));

    JTabbedPane tt = new JTabbedPane();
    //    tt.addTab("全部", panel);
    //    tt.addTab("全部2", new JButton("hello2"));
    //    tt.addTab("全部3", new JButton("hello3"));

    setLayout(new BorderLayout());
    add(tabPanel, BorderLayout.CENTER);
    
    tabPanel.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			_tabChanged();
		}
	});

    //       setNodePanelSelected();

    //       tabPanel.gett
  }

  protected void _tabChanged() {
	  Component c=tabPanel.getSelectedComponent();
	  if(c instanceof AbstractMainSubEditPanel){
			AbstractMainSubEditPanel editP=(AbstractMainSubEditPanel)c;
			if(editP.getMenuBar()==null){
				if(parentWindow.getJMenuBar()!=null){
					parentWindow.getJMenuBar().setVisible(false);
				}
			}else{
				parentWindow.setJMenuBar(editP.getMenuBar());	
				parentWindow.getJMenuBar().setVisible(true);		
			}
		}else{ 
				if(parentWindow.getJMenuBar()!=null){
					parentWindow.getJMenuBar().setVisible(false);
				}
			 
		}
		parentWindow.validate();
}
 

public GkBaseDialog getParentDlg() {
    return parentWindow;
  }

  protected void updateFieldEditors() {

    for (AbstractFieldEditor editor : fieldEditors) {

      editor.setEditObject(entrust);
      editor.setEnabled(false);

    }

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          UIManager.setLookAndFeel(new BlueLookAndFeel());
        } catch (Exception e) {
          e.printStackTrace();
        }
        ISfEntrustServiceDelegate entrustService = (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class,
          "sfEntrustServiceDelegate");
        SfEntrust entrust = entrustService.selectByPrimaryKey(new BigDecimal(2), WorkEnv.getInstance().getRequestMeta());
        JComponent pane = new SfDataFowPanel(entrust);
        JFrame frame = new JFrame("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(pane);
        frame.setVisible(true);
      }
    });
  }

  public void refreshWordPanel() {
    for (int i = 0; i < tabPanel.getTabCount(); i++) {
      JComponent c = (JComponent) tabPanel.getComponentAt(i);
      _refreshWord(c);
    }
  }

  private void _refreshWord(JComponent c) {
    if (isWordPanel(c) && c instanceof AbstractMainSubEditPanel) {
      ((AbstractMainSubEditPanel) c).refreshFilePanel();
    }
  }
}
