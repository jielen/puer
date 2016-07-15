package com.ufgov.zc.client.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.ufgov.zc.client.common.AsOptionMeta;
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

public class LocalResouceAppletUtil {

  private static Map<String, User> userCache = new HashMap<String, User>();

  private static Map<String, Company> companyCache = new HashMap<String, Company>();

  private static Map<String, String> orgPoCodeCache = new HashMap<String, String>();

  private static String oldTorken = "";

  private static String lafClassName = "", preferredFontSize = "12";

  private boolean isReLoad = true;

  public LocalResouceAppletUtil() {

  }

  public void initApplet(final JApplet applet) {
    try {
      final Method method = applet.getClass().getMethod("get", String.class);
      initWorkEnv(applet, method);
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          try {
            installLaf(applet, method);
            initPanel(applet, method);
            createLogDir();
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      });
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void createLogDir() {
    File file = new File("c:/ufgovlog");
    if (!file.exists()) {
      file.mkdir();
    }
  }

  private void initWorkEnv(Object f, Method method) {
    try {
      if (oldTorken != null && oldTorken.equals(getValue(method, f, "token"))) {
        isReLoad = false;
        return;
      }
      WorkEnv workEnv = WorkEnv.getInstance();
      workEnv.setToken((String) getValue(method, f, "token"));
      oldTorken = workEnv.getToken();
      workEnv.setClientIP((String) getValue(method, f, "clientIP"));
      workEnv.setTransDate((String) getValue(method, f, "transDate"));
      workEnv.setRoot((String) getValue(method, f, "root"));
      workEnv.setWebRoot((String) getValue(method, f, "webRoot"));
      workEnv.setOrgCode((String) getValue(method, f, "orgCode"));
      workEnv.setOrgName((String) getValue(method, f, "orgName"));
      workEnv.setRoleId((String) getValue(method, f, "roleId"));
      workEnv.setEmpCode((String) getValue(method, f, "empCode"));
      workEnv.setEmpName((String) getValue(method, f, "empName"));
      workEnv.setExpertCode((String) getValue(method, f, "empCode"));
      workEnv.setExpertName((String) getValue(method, f, "empName"));
      workEnv.setOrgCode((String) getValue(method, f, "orgCode"));
      workEnv.setOrgPoCode((String) getValue(method, f, "orgPoCode"));
      workEnv.setPoCode((String) getValue(method, f, "poCode"));
      workEnv.setExpertCode((String) getValue(method, f, "expertCode"));
      workEnv.setExpertName((String) getValue(method, f, "expertName"));
      workEnv.setUrlMap(changeStrToMap((String) getValue(method, f, "urlArray")));
      workEnv.setAccountId((String) getValue(method, f, "accountId"));

      //      workEnv.setCompoId((String) getValue(method, f, "compoId"));
      workEnv.setApplet((Applet) f);
      //      ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
      //      Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
      IBaseDataServiceDelegate baseDataServiceDelegate = (IBaseDataServiceDelegate) ServiceFactory.create(IBaseDataServiceDelegate.class, "baseDataServiceDelegate");

      RequestMeta meta = WorkEnv.getInstance().getRequestMeta();
      meta.setSvUserID((String) getValue(method, f, "userId"));
      User user = baseDataServiceDelegate.getUserById((String) getValue(method, f, "userId"), meta);
      workEnv.setCurrUser(user);
      Company company = baseDataServiceDelegate.getCompanyByCoCode(workEnv.getTransNd(), (String) getValue(method, f, "coCode"), meta);
      workEnv.setCurrCompany(company);
      meta.setSvCoCode(workEnv.getCurrCoCode());
      meta.setSvCoName(workEnv.getCurrCompany() == null ? null : workEnv.getCurrCompany().getName());
      java.util.Date sysDate = baseDataServiceDelegate.getSysDate(meta);
      workEnv.setSysDate(sysDate);

      IConsoleServiceDelegate consoleServiceDelegate = (IConsoleServiceDelegate) ServiceFactory.create(IConsoleServiceDelegate.class, "consoleServiceDelegate");

      List<AsRole> roles = consoleServiceDelegate.getAsRoleByPosi(workEnv.getPoCode(), meta);
      workEnv.setRoles(roles);

      IZcEbBaseServiceDelegate zcbaseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");

      List empLst = zcbaseDataServiceDelegate.queryDataForList("AsEmp.getAsEmp", null, meta);

      EmpMeta.setEmpLst(empLst);

      if (workEnv.getOrgPoCode() == null || workEnv.getOrgPoCode().trim().equals("")) {
        workEnv.setOrgPoCode(getOrgPoCode(baseDataServiceDelegate, meta.getSvCoCode(), meta.getSvOrgCode(), meta.getSvPoCode(), workEnv.getTransNd(), meta));
      }

      //      Thread.currentThread().setContextClassLoader(oldLoader);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  private String getOrgPoCode(IBaseDataServiceDelegate baseDataServiceDelegate, String coCode, String orgCode, String posiCode, int nd, RequestMeta requestMeta) {
    String key = coCode + "-" + orgCode + "-" + posiCode;
    String orgPoCode = orgPoCodeCache.get(key);
    if (orgPoCode == null) {
      orgPoCode = baseDataServiceDelegate.getOrgPosiId(coCode, orgCode, posiCode, nd, requestMeta);
      orgPoCodeCache.put(key, orgPoCode);
    }
    return orgPoCode;
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

  private void initPanel(Object f, Method method) {
    try {

      JApplet _applet = (JApplet) f;
      String panelClassName = (String) getValue(method, _applet, "panelClassName");
      Class panelClass = Class.forName(panelClassName);
      _applet.getContentPane().setLayout(new BorderLayout());
      Object panelInstance = panelClass.newInstance();
      JComponent panel = (JComponent) panelInstance;
      _applet.getContentPane().removeAll();
      _applet.getContentPane().setLayout(new BorderLayout());
      _applet.getContentPane().add(panel, BorderLayout.CENTER);
      SwingUtilities.updateComponentTreeUI(_applet);

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private void installLaf(Object f, Method method) {
    try {

      lafClassName = UIManager.getSystemLookAndFeelClassName();

      String v = AsOptionMeta.getOptVal(SystemOptionConstants.OPT_SYS_LOOK_AND_FEEL);
      preferredFontSize = AsOptionMeta.getOptVal(SystemOptionConstants.OPT_PREFERRED_FONT_SIZE);
      if (v != null) {
        lafClassName = v;
      }

      LookAndFeel lf = (LookAndFeel) (Class.forName(lafClassName).newInstance());//"com.ufgov.smartclient.plaf.BlueLookAndFeel"
      UIManager.setLookAndFeel(lf);
      SwingUtil.setFontSize(Integer.parseInt(preferredFontSize));
      SwingUtilities.updateComponentTreeUI((JApplet) f);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private Object getValue(Method m, Object t, String p) {
    try {
      return m.invoke(t, p);
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

}
