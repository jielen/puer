package com.ufgov.zc.client.common;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.ufgov.zc.common.commonbiz.model.Company;
import com.ufgov.zc.common.commonbiz.model.Position;
import com.ufgov.zc.common.console.model.AsEmp;
import com.ufgov.zc.common.console.model.AsRole;
import com.ufgov.zc.common.console.publish.IConsoleServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class WorkEnv {

  private Map<String, List<Applet>> appletMap = new HashMap<String, List<Applet>>();

  private Applet applet;

  private java.util.Date transDate;

  private java.util.Date sysDate = new Date();

  private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  private String webRoot = "";

  private String webIp = "";

  private String serviceRoot = "";

  private String root = "";

  private String orgName;

  private String publish = "/publish";

  private String token;

  private String clientIP;

  private User currUser;

  private Company currCompany;

  private String orgCode;

  private String orgPoCode;

  private String poCode;

  private String accountId;

  private String expertCode;

  private String expertName;

  private String empCode;

  private String empName;

  private HashMap urlMap;

  private String roleId;

  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  private Map attributes = new HashMap();

  private static WorkEnv workEvn = new WorkEnv();

  private WorkEnv self = this;

  private List<AsRole> roles = new ArrayList<AsRole>();

  private List empLst = new ArrayList();

  public RequestMeta getRequestMeta() {

    RequestMeta requestMeta = new RequestMeta();

    requestMeta.setToken(this.token);
    requestMeta.setClientIP(clientIP);
    requestMeta.setSvCoCode(this.getCurrCoCode());
    if (this.currCompany != null) {
      requestMeta.setSvCoCode(this.currCompany.getCode());
      requestMeta.setSvCoName(this.currCompany.getName());
    }
    requestMeta.setSvOrgCode(this.orgCode);
    requestMeta.setSvOrgPoCode(this.orgPoCode);
    requestMeta.setSvPoCode(this.poCode);
    requestMeta.setExpertCode(this.expertCode);
    requestMeta.setExpertName(this.expertName);
    requestMeta.setEmpCode(empCode);
    requestMeta.setEmpName(empName);

    if (this.currUser != null) {
      requestMeta.setSvUserID(this.currUser.getUserId());
      requestMeta.setSvUserName(this.currUser.getUserName());
    }
    requestMeta.setAccountId(accountId);
    requestMeta.setTransDate(Calendar.getInstance().getTime());
    requestMeta.setSysDate(Calendar.getInstance().getTime());
    requestMeta.setSvCoTypeCode(this.getCurrCoTypeCode());

    requestMeta.setUrlMap(urlMap);

    requestMeta.setRoles(roles);

    return requestMeta;
  }

  private WorkEnv() {
    this.currUser = new User();

  }

  public static WorkEnv getInstance() {
    if (workEvn.getCurrUser().getUserId() == null) {
      loginWin();
    }
    return workEvn;
  }

  public int getTransNd() {
    Calendar c = Calendar.getInstance();
    c.setTime(transDate);
    return c.get(Calendar.YEAR);
  }

  public synchronized void setAttribute(Object key, Object value) {
    attributes.put(key, value);
  }

  public synchronized Object getAttribute(Object key) {
    return attributes.get(key);
  }

  public String getWebRoot() {
    return webRoot;
  }

  public void setWebRoot(String webRoot) {
    this.webRoot = webRoot;
    if (webRoot.endsWith("/")) {
      String tmp = webRoot.substring(0, webRoot.length() - 1);
      this.setServiceRoot(tmp + publish);
    } else {
      this.setServiceRoot(webRoot + publish);
    }
  }

  public String getWebIp() {
    return webIp;
  }

  public void setWebIp(String webIp) {
    this.webIp = webIp;
  }

  public User getCurrUser() {
    return currUser;
  }

  public String getCurrUserName() {
    String userName = null;
    if (currUser != null) {
      userName = currUser.getUserName();
    }
    return userName;
  }

  public String getCurrUserId() {
    String userId = null;
    if (currUser != null) {
      userId = currUser.getUserId();
    }
    return userId;
  }

  public void setCurrUser(User currUser) {
    this.currUser = currUser;
  }

  public Company getCurrCompany() {
    return currCompany;
  }

  public String getCurrCoCode() {
    String coCode = null;
    if (currCompany != null) {
      coCode = currCompany.getCode();
    }
    return coCode;
  }

  public void setCurrCompany(Company currCompany) {
    this.currCompany = currCompany;
  }

  public String getServiceRoot() {
    return serviceRoot;
  }

  private void setServiceRoot(String serviceRoot) {
    this.serviceRoot = serviceRoot;
  }

  public java.util.Date getTransDate() {
    return transDate;
  }

  public int getTransMonth() {
    Calendar c = Calendar.getInstance();
    c.setTime(transDate);
    return c.get(Calendar.MONTH) + 1;
  }

  public void setTransDate(java.util.Date transDate) {
    this.transDate = transDate;
  }

  public void setTransDate(String transDateString) {
    try {
      this.transDate = this.dateFormat.parse(transDateString);
    } catch (ParseException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public String getTransDateString() {
    return dateFormat.format(transDate);
  }

  public java.util.Date getSysDate() {
    return sysDate;
  }

  public void setSysDate(java.util.Date sysDate) {
    this.sysDate = sysDate;
  }

  public int getSysMonth() {
    Calendar c = Calendar.getInstance();
    c.setTime(sysDate);
    return c.get(Calendar.MONTH) + 1;
  }

  public int getSysNd() {
    Calendar c = Calendar.getInstance();
    c.setTime(sysDate);
    return c.get(Calendar.YEAR);
  }

  public String getSysDateString() {
    return dateFormat.format(sysDate);
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  public String getOrgPoCode() {
    return orgPoCode;
  }

  public void setOrgPoCode(String orgPoCode) {
    this.orgPoCode = orgPoCode;
  }

  public String getPoCode() {
    return poCode;
  }

  public void setPoCode(String poCode) {
    this.poCode = poCode;
  }

  public String getClientIP() {
    return clientIP;
  }

  public void setClientIP(String clientIP) {
    this.clientIP = clientIP;
  }

  public String getCurrCoTypeCode() {
    String coTypeCode = null;
    if (currCompany != null) {
      coTypeCode = currCompany.getCoTypeCode();
    }
    return coTypeCode;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public Applet getApplet() {
    return applet;
  }

  public void setApplet(Applet applet) {
    this.applet = applet;
  }

  public List<Applet> getAppletList(String token) {
    List<Applet> list = this.appletMap.get(token);
    if (list == null) {
      list = new ArrayList<Applet>();
    }
    return list;
  }

  public void addApplet(String token, Applet applet) {
    List<Applet> list = this.appletMap.get(token);
    if (list == null) {
      list = new ArrayList<Applet>();
      this.appletMap.put(token, list);
    }
    list.add(applet);
  }

  public String getExpertCode() {
    return expertCode;
  }

  public void setExpertCode(String expertCode) {
    this.expertCode = expertCode;
  }

  public String getExpertName() {
    return expertName;
  }

  public void setExpertName(String expertName) {
    this.expertName = expertName;
  }

  public String getEmpCode() {
    return empCode;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public HashMap getUrlMap() {
    return urlMap;
  }

  public void setUrlMap(HashMap urlMap) {
    this.urlMap = urlMap;
  }

  private static void initSa() {
    workEvn.currUser.setUserId("sa");
    workEvn.currUser.setUserName("aa");
    workEvn.currCompany = new Company();
    workEvn.currCompany.setCoTypeCode("01");
    workEvn.currCompany.setCode("000");
    // this.currCompany.setName("100name");
    workEvn.orgCode = "002";
    workEvn.poCode = "P_WTSL";
    workEvn.orgPoCode = "003007";
    workEvn.token = "" + System.currentTimeMillis();

    workEvn.setTransDate("2016-10-22");
    workEvn.setEmpCode("EM-00000286");
    workEvn.setEmpName("冯希杰");
    workEvn.setSysDate(new java.util.Date());
    AsRole role = new AsRole();
    role.setRoleId("ysdwjb");
    workEvn.getRoles().add(role);
    workEvn.setWebRoot("http://127.0.0.1:7001/GB/");
    workEvn.setWebIp("http://127.0.0.1:7001");
    workEvn.setEmpLst(initEmpLst());

    EmpMeta.setEmpLst(workEvn.getEmpLst());
  }

  private static List initEmpLst() {
    // TCJLODO Auto-generated method stub

    IZcEbBaseServiceDelegate baseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");

    RequestMeta requestMeta = new RequestMeta();
    requestMeta.setToken("ss");
    requestMeta.setSvUserID("sss");
    List emps = baseDataServiceDelegate.queryDataForList("AsEmp.getAsEmp", null, requestMeta);
    return emps;
  }

  public static String getUserName(String userId) {
    RequestMeta requestMeta = new RequestMeta();
    requestMeta.setToken("ss");
    requestMeta.setSvUserID("sss");

    IZcEbBaseServiceDelegate baseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");
    Map parameter2 = new HashMap();
    parameter2.put("userId", userId);
    List userlist = baseDataServiceDelegate.queryDataForList("User.getUserByIdByMap", parameter2, requestMeta);
    if (userlist == null || userlist.size() == 0) {
      // JOptionPane.showMessageDialog(dialog, "【" + userId +
      // "】没有对应的as_user记录，用户名错误！", " 提示",
      // JOptionPane.INFORMATION_MESSAGE);
      return null;
    }
    com.ufgov.zc.common.system.model.User user = (User) userlist.get(0);
    return user.getUserName();
  }

  public static void loginWin() {
    if ("sss1".equals(workEvn.getToken())) {
      return;
    }
    final JDialog dialog = new JDialog();
    dialog.setTitle("UFGOV-WorkEnv bate2.1 ");
    JPanel panel = new JPanel();
    final DefaultComboBoxModel model = new DefaultComboBoxModel(new String[] { "sa", "chenh", "shif", "zhangsj", "yanh", "lij" });
    final JLabel userNameLabel = new JLabel();
    userNameLabel.setText("系统管理员");
    final JComboBox field = new JComboBox(model);
    field.setPreferredSize(new Dimension(120, 25));
    field.setEditable(true);
    field.getEditor().selectAll();

    final JButton button = new JButton("login");

    panel.add(userNameLabel);
    panel.add(field);
    panel.add(button);
    dialog.add(panel);
    dialog.setSize(UIConstants.DIALOG_4_LEVEL_WIDTH, UIConstants.DIALOG_4_LEVEL_HEIGHT);
    dialog.setModal(true);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = dialog.getSize();

    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gs = ge.getDefaultScreenDevice();
    GraphicsConfiguration gc = gs.getDefaultConfiguration();
    Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    dialog.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - insets.bottom - frameSize.height) / 2);
    button.registerKeyboardAction(button.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), JComponent.WHEN_FOCUSED);

    field.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        // TCJLODO Auto-generated method stub
        if (e.getStateChange() == ItemEvent.SELECTED) {
          JComboBox jcb = (JComboBox) e.getSource();
          String userId = (String) (jcb.getSelectedItem());
          String userName = getUserName(userId);
          userNameLabel.setText(userName == null ? "" : userName);
        }
      }

    });

    field.getEditor().getEditorComponent().addKeyListener(new KeyListener() {
      public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
          button.doClick();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // TCJLODO Auto-generated method stub
        String userId = (String) (field.getEditor().getItem());
        String userName = getUserName(userId);
        userNameLabel.setText(userName == null ? "" : userName);
      }

      @Override
      public void keyTyped(KeyEvent e) {
        // TCJLODO Auto-generated method stub

      }
    });

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        RequestMeta requestMeta = new RequestMeta();
        requestMeta.setToken("ss");
        requestMeta.setSvUserID("sss");

        String userId = field.getEditor().getItem().toString();

        if ("sa".equals(userId)) {
          initSa();
          dialog.dispose();
          return;
        }
        IZcEbBaseServiceDelegate baseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
          "zcEbBaseServiceDelegate");
        Map parameter2 = new HashMap();
        parameter2.put("userId", userId);
        List userlist = baseDataServiceDelegate.queryDataForList("User.getUserByIdByMap", parameter2, requestMeta);
        if (userlist == null || userlist.size() == 0) {
          JOptionPane.showMessageDialog(dialog, "【" + userId + "】没有对应的as_user记录，用户名错误！", " 提示", JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        com.ufgov.zc.common.system.model.User user = (User) userlist.get(0);

        ElementConditionDto dto = new ElementConditionDto();
        dto.setExtField1(userId);
        List empList = baseDataServiceDelegate.getForeignEntitySelectedData("AsEmp.getProviderInfoByUserId", dto, requestMeta);
        if (empList == null || empList.size() == 0) {
          JOptionPane.showMessageDialog(dialog, "【" + userId + "】没有找到对应的as_emp记录！", " 提示", JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        AsEmp asEmp = (AsEmp) empList.get(0);

        Map parameter = new HashMap();
        parameter.put("EMP_CODE", asEmp.getEmpCode());
        parameter.put("ND", "2016");
        List list = baseDataServiceDelegate.queryDataForList("User.getAsEmpPosiByEmpCode", parameter, requestMeta);

        if (list == null || list.size() == 0) {
          JOptionPane.showMessageDialog(dialog, "【" + asEmp.getEmpCode() + "】as_emp_position对应的记录！", " 提示", JOptionPane.INFORMATION_MESSAGE);
          return;
        } else {

          Position po = (Position) list.get(0);

          Map paramete3 = new HashMap();
          paramete3.put("CO_CODE", po.getCoCode());
          paramete3.put("ORG_CODE", po.getOrgCode());
          paramete3.put("POSI_CODE", po.getPosiCode());
          paramete3.put("ND", "2016");

          List orglist = baseDataServiceDelegate.queryDataForList("WfCommonDraft.getOrgPosiId", paramete3, requestMeta);
          if (orglist == null || orglist.size() == 0) {
            JOptionPane.showMessageDialog(dialog, "【" + paramete3 + "】as_org_position 没有对应数据！", " 提示", JOptionPane.INFORMATION_MESSAGE);
            return;
          }
          String org_posi_id = (String) orglist.get(0);

          workEvn.getCurrUser().setUserId(po.getEmpCode());
          workEvn.currUser.setUserName(user.getUserName());
          workEvn.currCompany = new Company();
          workEvn.currCompany.setCoTypeCode("01");
          workEvn.currCompany.setCode(po.getCoCode());
          workEvn.orgCode = po.getOrgCode();
          workEvn.poCode = po.getPosiCode();
          workEvn.orgPoCode = org_posi_id;
          workEvn.setToken("" + System.currentTimeMillis());
          workEvn.setTransDate("2016-06-01");
          workEvn.setEmpCode(po.getEmpCode());
          workEvn.setEmpName(user.getUserName());
          workEvn.setWebRoot("http://127.0.0.1:7001/GB/");
          workEvn.setWebIp("http://127.0.0.1:7001");
          IConsoleServiceDelegate consoleServiceDelegate = (IConsoleServiceDelegate) ServiceFactory.create(IConsoleServiceDelegate.class,
            "consoleServiceDelegate");
          List<AsRole> roles = consoleServiceDelegate.getAsRoleByPosi(po.getPosiCode(), requestMeta);

          workEvn.setRoles(roles);
          workEvn.setEmpLst(initEmpLst());
          EmpMeta.setEmpLst(workEvn.getEmpLst());

          dialog.dispose();
        }

      }
    });
    dialog.setVisible(true);
  }

  public List<AsRole> getRoles() {
    return roles;
  }

  public void setRoles(List<AsRole> roles) {
    this.roles = roles;
  }

  public boolean containRole(String roleCode) {
    if (this.roles != null) {
      for (int i = 0; i < roles.size(); i++) {
        AsRole role = (AsRole) roles.get(i);
        if (role.getRoleId() != null && role.getRoleId().equals(roleCode)) {
          return true;
        }
      }
    }
    return false;
  }

  // public String getRoleCode() {
  // return roleCode;
  // }
  //
  // public void setRoleCode(String roleCode) {
  // this.roleCode = roleCode;
  // }

  public List getEmpLst() {
    return empLst;
  }

  public void setEmpLst(List empLst) {
    this.empLst = empLst;
  }

}
