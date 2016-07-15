package com.ufgov.zc.client.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Window;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import com.ufgov.smartclient.common.JBaseApplet;
import com.ufgov.zc.client.common.AppletAware;
import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.ParentWindowAware;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.util.SwingUtil;
import com.ufgov.zc.common.commonbiz.model.Company;
import com.ufgov.zc.common.commonbiz.publish.IBaseDataServiceDelegate;
import com.ufgov.zc.common.console.model.AsRole;
import com.ufgov.zc.common.console.publish.IConsoleServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SystemOptionConstants;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.util.EmpMeta;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

public class ZcApplet extends JBaseApplet {
  private static final Logger logger = Logger.getLogger(ZcApplet.class);

  private static final long serialVersionUID = 7317831513093537273L;

  private Window appletWindow;

  private String panelClassName;

  private String token;

  private String clientIP;

  private String transDate;

  private String userId;

  private String coCode;

  private String orgCode;

  private String orgPoCode;

  private String poCode;

  private String webRoot;

  private String webIp;

  private String accountId;

  private String expertCode;

  private String expertName;

  private String empCode;

  private String empName;

  private HashMap urlMap;

  private static Map<String, User> userCache = new HashMap<String, User>();

  private static Map<String, Company> companyCache = new HashMap<String, Company>();

  private static Map<String, String> orgPoCodeCache = new HashMap<String, String>();

  private User getUser(IBaseDataServiceDelegate baseDataServiceDelegate, String userId, RequestMeta requestMeta) {

    User user = userCache.get(userId);
    if (user == null) {
      user = baseDataServiceDelegate.getUserById(userId, requestMeta);
      userCache.put(userId, user);
    }
    return user;
  }

  private Company getCompany(IBaseDataServiceDelegate baseDataServiceDelegate, int nd, String coCode, RequestMeta requestMeta) {
    Company company = companyCache.get(coCode);
    if (company == null) {
      company = baseDataServiceDelegate.getCompanyByCoCode(nd, coCode, requestMeta);
      companyCache.put(coCode, company);
    }
    return company;
  }

  private String getOrgPoCode(IBaseDataServiceDelegate baseDataServiceDelegate, String coCode, String orgCode, String posiCode, int nd,
    RequestMeta requestMeta) {
    String key = coCode + "-" + orgCode + "-" + posiCode;
    String orgPoCode = orgPoCodeCache.get(key);
    if (orgPoCode == null) {
      orgPoCode = baseDataServiceDelegate.getOrgPosiId(coCode, orgCode, poCode, nd, requestMeta);
      orgPoCodeCache.put(key, orgPoCode);
    }
    return orgPoCode;
  }

  public void init() {
    initAppletParameters();
    initWorkEnv();
    super.init();
  }

  protected void createComponent(Container container) {

    setAppletWindow(detectParentWindow(container));

    initPanel(container);

    createLogDir();

  }

  private void initWorkEnv() {
    WorkEnv workEnv = WorkEnv.getInstance();
    workEnv.setWebRoot(webRoot);
    workEnv.setWebIp(webIp);
    workEnv.setTransDate(transDate);

    String oldToken = workEnv.getToken();

    if (oldToken != null && !"".equals(oldToken)) {
      if (!token.equals(oldToken)) {
        List<Applet> oldAppletList = workEnv.getAppletList(oldToken);
        for (Applet oldApplet : oldAppletList) {
          try {
            if (oldApplet instanceof ZcApplet) {
              this.closeOwnedWindows(((ZcApplet) oldApplet).getAppletWindow());
              this.closeOwnerlessWindows(((ZcApplet) oldApplet).getAppletWindow());
            }
            oldApplet.getAppletContext().showDocument(new URL("javascript:window.close()"));
          } catch (MalformedURLException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }

    workEnv.setToken(token);
    workEnv.setApplet(this);
    workEnv.addApplet(token, this);

    workEnv.setClientIP(clientIP);
    workEnv.setOrgCode(orgCode);
    workEnv.setOrgPoCode(orgPoCode);
    workEnv.setPoCode(poCode);
    workEnv.setAccountId(accountId);
    workEnv.setEmpCode(empCode);
    workEnv.setEmpName(empName);
    workEnv.setUrlMap(urlMap);

    RequestMeta requestMeta = workEnv.getRequestMeta();
    requestMeta.setToken(token);
    requestMeta.setClientIP(clientIP);
    requestMeta.setTransDate(workEnv.getTransDate());
    requestMeta.setSvUserID(userId);
    requestMeta.setSvCoCode(coCode);
    requestMeta.setSvOrgCode(orgCode);
    requestMeta.setSvPoCode(poCode);
    requestMeta.setExpertCode(expertCode);
    requestMeta.setExpertName(expertName);
    requestMeta.setEmpCode(empCode);
    requestMeta.setEmpName(empName);
    requestMeta.setUrlMap(urlMap);
    //    System.out.println("---------set requestMeta=empCode" + requestMeta.getEmpCode());

    IBaseDataServiceDelegate baseDataServiceDelegate = (IBaseDataServiceDelegate) ServiceFactory.create(IBaseDataServiceDelegate.class,
      "baseDataServiceDelegate");

    java.util.Date sysDate = baseDataServiceDelegate.getSysDate(requestMeta);
    workEnv.setSysDate(sysDate);

    if (orgPoCode == null || orgPoCode.trim().equals("")) {
      orgPoCode = getOrgPoCode(baseDataServiceDelegate, coCode, orgCode, poCode, workEnv.getTransNd(), requestMeta);
    }
    workEnv.setOrgPoCode(orgPoCode);
    requestMeta.setSvOrgPoCode(orgPoCode);

    IConsoleServiceDelegate consoleServiceDelegate = (IConsoleServiceDelegate) ServiceFactory.create(IConsoleServiceDelegate.class,
      "consoleServiceDelegate");
    List<AsRole> roles = consoleServiceDelegate.getAsRoleByPosi(poCode, requestMeta);
    workEnv.setRoles(roles);

    User user = getUser(baseDataServiceDelegate, userId, requestMeta);
    Company company = getCompany(baseDataServiceDelegate, workEnv.getTransNd(), coCode, requestMeta);

    workEnv.setCurrUser(user);
    workEnv.setCurrCompany(company);

    IZcEbBaseServiceDelegate zcbaseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");

    List empLst = zcbaseDataServiceDelegate.queryDataForList("AsEmp.getAsEmp", null, requestMeta);

    EmpMeta.setEmpLst(empLst);

    if (logger.isDebugEnabled()) {
      logger.debug("workEnv:: user: " + workEnv.getCurrUser().getUserId() + " " + workEnv.getCurrUser().getUserName() + " "
        + workEnv.getCurrCompany() + " transDate:" + workEnv.getTransDate() + " expertName:" + workEnv.getExpertName() + workEnv.getTransDate()
        + " empCode:" + workEnv.getEmpCode());
    }
  }

  public Window getAppletWindow() {
    return appletWindow;
  }

  private void closeOwnedWindows(Window w) {
    if (w != null) {
      Window[] ws = w.getOwnedWindows();
      if (ws != null) {
        for (Window ww : ws) {
          if (ww != null) {
            closeOwnedWindows(ww);
            ww.dispose();
          }
        }
      }
    }
  }

  private void closeOwnerlessWindows(Window w) {
    Window[] ws = Window.getOwnerlessWindows();
    if (ws != null) {
      for (Window ww : ws) {
        if (ww != null) {
          ww.dispose();
        }
      }
    }
  }

  private void initAppletParameters() {

    panelClassName = this.getParameter("panelClassName");

    token = this.getParameter("token");

    clientIP = this.getParameter("clientIP");

    transDate = this.getParameter("transDate");

    userId = this.getParameter("userId");

    coCode = this.getParameter("coCode");

    orgCode = this.getParameter("orgCode");

    orgPoCode = this.getParameter("orgPoCode");

    poCode = this.getParameter("poCode");

    webRoot = this.getParameter("webRoot");
    webIp = this.getParameter("webIp");

    accountId = this.getParameter("accountId");
    expertCode = this.getParameter("expertCode");
    expertName = this.getParameter("expertName");
    empCode = this.getParameter("empCode");
    empName = this.getParameter("empName");

    urlMap = changeStrToMap(this.getParameter("urlArray"));

    if (logger.isDebugEnabled()) {
      logger.debug("panelClassName: " + panelClassName + " token:" + token + " clientIP:" + clientIP + " userId:" + userId + " cocode:" + coCode
        + " orgCode:" + orgCode + " orgPocode: " + orgPoCode + " poCode:" + poCode + " webRoot:" + webRoot + " transDate: " + transDate
        + " accountId:" + expertCode + " : " + expertCode + " expertName:" + expertName + "empCode:" + empCode + "empName:" + empName);
    }

    System.out.println("panelClassName: " + panelClassName + " token:" + token + " clientIP:" + clientIP + " userId:" + userId + " cocode:" + coCode
      + " orgCode:" + orgCode + " orgPocode: " + orgPoCode + " poCode:" + poCode + " webRoot:" + webRoot + " webIp:" + webIp + " transDate: "
      + transDate + " accountId:" + expertCode + " : " + expertCode + " expertName:" + expertName + "empCode:" + empCode + "empName:" + empName);

  }

  private void initPanel(Container container) {
    try {

      String preferredFontSize = AsOptionMeta.getOptVal(SystemOptionConstants.OPT_PREFERRED_FONT_SIZE);

      SwingUtil.setFontSize(Integer.parseInt(preferredFontSize));

      Class panelClass = Class.forName(panelClassName);

      Object panelInstance = panelClass.newInstance();

      if (panelInstance instanceof ParentWindowAware) {
        ParentWindowAware pwa = (ParentWindowAware) panelInstance;
        pwa.setParentWindow(this.appletWindow);
      }

      if (panelInstance instanceof AppletAware) {
        AppletAware appletAware = (AppletAware) panelInstance;
        appletAware.setApplet(this);
      }

      JComponent panel = (JComponent) panelInstance;

      container.setLayout(new BorderLayout());

      container.add(panel, BorderLayout.CENTER);

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }

  }

  private void setAppletWindow(Window appletWindow) {
    this.appletWindow = appletWindow;
  }

  private Window detectParentWindow(Container container) {
    JLabel detectLabel = new JLabel("");
    container.add(detectLabel);
    Container c = detectLabel.getParent();
    while (c != null) {
      if (c instanceof Window)
        return (Window) c;
      c = c.getParent();
    }
    return null;
  }

  private void createLogDir() {
    File file = new File("c:/ufgovlog");
    if (!file.exists()) {
      file.mkdir();
    }
  }

  public void destroy() {
    WorkEnv.getInstance().getAppletList(token).remove(this);
  }

  private static Map<String, String> lookAndFeelMap = new HashMap<String, String>();
  static {
    lookAndFeelMap.put("0", "com.ufgov.smartclient.plaf.GrayLookAndFeel");
    lookAndFeelMap.put("1", "com.ufgov.smartclient.plaf.BlueLookAndFeel");
  }

  public String getParameter(String name) {
    if ("lookAndFeel".equals(name)) {
      String className = "com.ufgov.smartclient.plaf.GrayLookAndFeel";
      String v = AsOptionMeta.getOptVal(SystemOptionConstants.OPT_SYS_LOOK_AND_FEEL);
      if (v != null && v.trim().length()>10) {
        className = v;
      }
      return className;
    } else {
      return super.getParameter(name);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
    result = prime * result + ((appletWindow == null) ? 0 : appletWindow.hashCode());
    result = prime * result + ((clientIP == null) ? 0 : clientIP.hashCode());
    result = prime * result + ((coCode == null) ? 0 : coCode.hashCode());
    result = prime * result + ((orgCode == null) ? 0 : orgCode.hashCode());
    result = prime * result + ((orgPoCode == null) ? 0 : orgPoCode.hashCode());
    result = prime * result + ((panelClassName == null) ? 0 : panelClassName.hashCode());
    result = prime * result + ((poCode == null) ? 0 : poCode.hashCode());
    result = prime * result + ((token == null) ? 0 : token.hashCode());
    result = prime * result + ((transDate == null) ? 0 : transDate.hashCode());
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    result = prime * result + ((webRoot == null) ? 0 : webRoot.hashCode());
    result = prime * result + ((webIp == null) ? 0 : webIp.hashCode());

    result = prime * result + ((expertCode == null) ? 0 : expertCode.hashCode());
    result = prime * result + ((expertName == null) ? 0 : expertName.hashCode());
    result = prime * result + ((empCode == null) ? 0 : empCode.hashCode());
    result = prime * result + ((empName == null) ? 0 : empName.hashCode());

    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final ZcApplet other = (ZcApplet) obj;
    if (accountId == null) {
      if (other.accountId != null)
        return false;
    } else if (!accountId.equals(other.accountId))
      return false;
    if (appletWindow == null) {
      if (other.appletWindow != null)
        return false;
    } else if (!appletWindow.equals(other.appletWindow))
      return false;
    if (clientIP == null) {
      if (other.clientIP != null)
        return false;
    } else if (!clientIP.equals(other.clientIP))
      return false;
    if (coCode == null) {
      if (other.coCode != null)
        return false;
    } else if (!coCode.equals(other.coCode))
      return false;
    if (orgCode == null) {
      if (other.orgCode != null)
        return false;
    } else if (!orgCode.equals(other.orgCode))
      return false;
    if (orgPoCode == null) {
      if (other.orgPoCode != null)
        return false;
    } else if (!orgPoCode.equals(other.orgPoCode))
      return false;
    if (panelClassName == null) {
      if (other.panelClassName != null)
        return false;
    } else if (!panelClassName.equals(other.panelClassName))
      return false;
    if (poCode == null) {
      if (other.poCode != null)
        return false;
    } else if (!poCode.equals(other.poCode))
      return false;
    if (token == null) {
      if (other.token != null)
        return false;
    } else if (!token.equals(other.token))
      return false;
    if (transDate == null) {
      if (other.transDate != null)
        return false;
    } else if (!transDate.equals(other.transDate))
      return false;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    if (webRoot == null) {
      if (other.webRoot != null)
        return false;
    } else if (!webRoot.equals(other.webRoot))
      return false;
    if (webIp == null) {
      if (other.webIp != null)
        return false;
    } else if (!webIp.equals(other.webIp))
      return false;
    if (expertCode == null) {
      if (other.expertCode != null)
        return false;
    } else if (!expertCode.equals(other.expertCode))
      return false;
    if (expertName == null) {
      if (other.expertName != null)
        return false;
    } else if (!expertName.equals(other.expertName))
      return false;
    if (empCode == null) {
      if (other.empCode != null)
        return false;
    } else if (!empCode.equals(other.empCode))
      return false;
    if (empName == null) {
      if (other.empName != null)
        return false;
    } else if (!empName.equals(other.empName))
      return false;
    return true;
  }

  private HashMap<String, String> changeStrToMap(String str) {
    HashMap map = new HashMap();
    if (str != null && !"".equals(str)) {
      String array[] = str.split(",");
      for (int i = 0; i < array.length; i++) {
        String mapArray[] = array[i].split("@");
        String key = mapArray[0];
        String value = mapArray[1];
        map.put(key, value);
      }
    }
    return map;
  }
}
