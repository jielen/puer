package com.ufgov.zc.client.common;

import java.applet.Applet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.common.commonbiz.model.Company;
import com.ufgov.zc.common.console.model.AsRole;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.model.User;

public class WorkEnv {

  private Map<String, List<Applet>> appletMap = new HashMap<String, List<Applet>>();

  private Applet applet;

  private java.util.Date transDate;

  private java.util.Date sysDate;

  private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  private String webRoot = "";

  private String root = "";

  private String serviceRoot = "";

  private String publish = "/publish";

  private String token;

  private String clientIP;

  private String webIp;

  private User currUser;

  private Company currCompany;

  private String orgCode;

  private String orgName;

  private String orgPoCode;

  private String poCode;

  private String accountId;

  private String expertCode;

  private String expertName;

  private String empCode;

  private String empName;

  private String roleId;

  private String roleCode;

  private HashMap urlMap;

  private Map attributes = new HashMap();

  private List roles = new ArrayList();

  private static WorkEnv workEvn = new WorkEnv();

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
    requestMeta.setSvOrgName(this.orgName);
    requestMeta.setSvOrgPoCode(this.orgPoCode);
    //      System.out.println("workEnv.getRequestMeta " + orgPoCode);
    requestMeta.setSvPoCode(this.poCode);
    requestMeta.setExpertCode(this.expertCode);
    requestMeta.setExpertName(this.expertName);
    requestMeta.setEmpCode(this.empCode);
    requestMeta.setEmpName(this.empName);

    if (this.currUser != null) {
      requestMeta.setSvUserID(this.currUser.getUserId());
      requestMeta.setSvUserName(this.currUser.getUserName());
    }
    requestMeta.setAccountId(accountId);
    requestMeta.setTransDate(transDate);
    requestMeta.setSysDate(sysDate);
    requestMeta.setSvCoTypeCode(this.getCurrCoTypeCode());
    requestMeta.setUrlMap(urlMap);
    //		requestMeta.setSvRoleId(roleId);
    requestMeta.setRoles(this.roles);

    return requestMeta;
  }

  private WorkEnv() {

  }

  public static WorkEnv getInstance() {
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

  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
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
      throw new RuntimeException("会话停顿时间超限，当前操作失败。请重新登录！", new Exception("会话停顿时间超限，当前操作失败。请重新登录！"));
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

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getWebIp() {
    return webIp;
  }

  public void setWebIp(String webIp) {
    this.webIp = webIp;
  }

  public List getRoles() {
    return roles;
  }

  public void setRoles(List<AsRole> roles) {
    this.roles = roles;
  }

  public boolean containRole(String roleCode) {
    if (this.roles != null) {
      for (int i = 0; i < roles.size(); i++) {
        AsRole role = (AsRole) roles.get(i);
        if (role.getRoleId() != null && role.getRoleId().equals(roleCode)) { return true; }
      }
    }
    return false;
  }

}
