/**
 * 
 */
package com.ufgov.zc.client.sf.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.BillElementMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.button.FuncButton;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.commonbiz.fieldmap.FieldMapRegister;
import com.ufgov.zc.common.commonbiz.model.BillElement;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.model.SfZongheZhiban;
import com.ufgov.zc.common.sf.publish.ISfZongheZhibanServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.constants.SystemOptionConstants;
import com.ufgov.zc.common.system.constants.ZcSettingConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfUtil {
  

  private String mac="";

  static IZcEbBaseServiceDelegate baseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
    "zcEbBaseServiceDelegate");

  //  static RequestMeta meta = WorkEnv.getInstance().getRequestMeta();

  static List<HashMap<String, String>> userFuncs = new ArrayList<HashMap<String, String>>();

  static HashMap<String, List> userCompoFuncCache = new HashMap<String, List>();

  public static boolean canNew(String compoId, RequestMeta meta) {
    return canNew(compoId, null, meta);
  }

  /**
   * 判断当前用户对当前部件是否有新增权限
   * @param compoId
   * @param entrust
   * @return
   */
  public static boolean canNew(String compoId, SfEntrust entrust, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    boolean rtn = haveFunc(compoId, entrust, SfElementConstants.FUNC_NEW, meta);
    if (rtn)
      return rtn;
    return haveFunc(compoId, entrust, SfElementConstants.FUNC_ADD, meta);
  }

  public static boolean haveFunc(String compoId, String func, RequestMeta meta) {
    return haveFunc(compoId, null, func, meta);
  }

  /*public static boolean haveFunc(String compoId, String func, RequestMeta meta, String moduleCode) {
    return haveFunc(compoId, null, func, meta, moduleCode);
  }*/

  /**
   * 判断当前用户对当前部件是否有对应权限
   * @param compoId
   * @param entrust
   * @param func
   * @return
   */
  public static boolean haveFunc(String compoId, SfEntrust entrust, String func, RequestMeta meta) {

    return haveFunc(compoId, func, meta, "SF");
  }

  /**
   * 判断当前用户对当前部件是否有对应权限
   * @param compoId
   * @param entrust
   * @param func
   * @param compoPreFix
   * @return
   */
  public static boolean haveFunc(String compoId, String func, RequestMeta meta, String compoPreFix) {
    if (compoId == null || compoId.trim().length() == 0 || func == null || func.trim().length() == 0) {
      return false;
    }
    // TCJLODO Auto-generated method stub
    String key = WorkEnv.getInstance().getToken();
    if (!haveInitCompoFunc(key)) {
      userCompoFuncCache.clear();
      ElementConditionDto dto = new ElementConditionDto();
      dto.setUserId(meta.getSvUserID());
      //      dto.setCompoId(compoId);
      dto.setDattr1(compoPreFix == null ? "" : compoPreFix);
      List rtn = baseDataServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectUserFunc", dto, meta);
      if (rtn != null) {
        //        userFuncs.addAll(rtn);
        userCompoFuncCache.put(key, rtn);
      }
    }
    List funcs = userCompoFuncCache.get(key);
    if (funcs == null || funcs.size() == 0)
      return false;
    for (int i = 0; i < funcs.size(); i++) {
      HashMap<String, String> funcMap = (HashMap<String, String>) funcs.get(i);
      if (funcMap.containsValue(compoId) && funcMap.containsValue(func)) {
        return true;
      }
    }
    return false;
  }

  private static boolean haveInitCompoFunc(String token) {
    if (userCompoFuncCache == null || userCompoFuncCache.size() == 0)
      return false;
    if (userCompoFuncCache.containsKey(token))
      return true;

    return false;
  }

  /**
   * 将数据转换为字符，有小数时，转换时带有小数，没有小数时，转换为整数字符串
   * @param d
   * @return
   */
  public static String getDecimalStr(BigDecimal d) {
    String rtn = "";
    if (d == null)
      return null;
    int intVal = d.intValue();
    double douVal = d.doubleValue();
    double nd = Double.parseDouble("" + intVal);
    if (douVal > nd) {
      rtn = "" + douVal;
    } else {
      rtn = "" + intVal;
    }
    return rtn;
  }

  public static int getScreenWidth() {
    Dimension screenSize = getScreenSize();
    return screenSize.width - 100;

  }

  public static int getScreenHeight() {
    Dimension screenSize = getScreenSize();
    return screenSize.height - 100;

  }

  /**
   * @return
   */
  public static Dimension getScreenSize() {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

    GraphicsDevice gs = ge.getDefaultScreenDevice();

    GraphicsConfiguration gc = gs.getDefaultConfiguration();

    Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize;
  }

  public static void FitTableColumns(JTable myTable) {
    JTableHeader header = myTable.getTableHeader();
    int rowCount = myTable.getRowCount();

    Enumeration columns = myTable.getColumnModel().getColumns();
    while (columns.hasMoreElements()) {
      TableColumn column = (TableColumn) columns.nextElement();
      int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
      int width = (int) myTable.getTableHeader().getDefaultRenderer()
        .getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
      for (int row = 0; row < rowCount; row++) {
        int preferedWidth = (int) myTable.getCellRenderer(row, col)
          .getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
        width = Math.max(width, preferedWidth);
      }
      header.setResizingColumn(column); // 此行很重要  
      column.setWidth(width + myTable.getIntercellSpacing().width);
    }
  }

  /**
   * 将数字转换为整数字符或者double字符
   * @param num
   * @return
   */
  public static String convertNumToStr(BigDecimal num) {
    // TCJLODO Auto-generated method stub
    if (num == null)
      return null;
    double d1 = num.doubleValue();
    int k = num.intValue();
    double d2 = new Double(k);
    if (d1 > d2) {
      return "" + d1;
    } else {
      return "" + k;
    }
  }

  /**
   * 将数字转换为整数字符或者double字符
   * @param num
   * @return
   */
  public static String convertNumToStr(double num) {
    return convertNumToStr(new BigDecimal(num));
  }

  /**
   * 将数字转换为整数字符或者double字符
   * @param num
   * @return
   */
  public static String convertNumToStr(float num) {
    return convertNumToStr(new BigDecimal(num));
  }

  public static JPanel createFieldEditorPanel(Class billClass, BillElementMeta eleMeta, List<AbstractFieldEditor> editors, int colCount) {
    int row = 0;
    int col = 0;
    int preferredFontSize = Integer.valueOf(AsOptionMeta.getOptVal(SystemOptionConstants.OPT_PREFERRED_FONT_SIZE));

    List<BillElement> notNullFields = eleMeta.getNotNullBillElement();
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new GridBagLayout());

    for (int i = 0; i < editors.size(); i++) {
      AbstractFieldEditor comp = editors.get(i);
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
          if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
            label.setText(label.getText() + "*");
            label.setForeground(new Color(254, 100, 1));
            label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
          }
          comp.setPreferredSize(new Dimension(150 * comp.getOccCol(), comp.getOccRow() * 26));
          contentPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 0,
            4, 4), 0, 0));
          contentPanel.add(comp, new GridBagConstraints(col + 1, row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0, GridBagConstraints.WEST,
            GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4, 4), 0, 0));
          // 将当前所占的行空间偏移量计算上
          row += comp.getOccRow();
          col = 0;
          continue;
        }

        JLabel label = new JLabel(comp.getName());
        if (isNotNullField(billClass, comp.getFieldName(), notNullFields)) {
          label.setText(label.getText() + "*");
          label.setForeground(new Color(254, 100, 1));
          label.setFont(new Font("宋体", Font.BOLD, preferredFontSize));
        }
        comp.setPreferredSize(new Dimension(150, 23));
        contentPanel.add(label, new GridBagConstraints(col, row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0,
          5, 5), 0, 0));
        contentPanel.add(comp, new GridBagConstraints(col + 1, row, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
          new Insets(5, 0, 5, 5), 0, 0));
        if (col == colCount * 2 - 2) {
          row++;
          col = 0;
        } else {
          col += 2;
        }
      }
    }
    return contentPanel;
  }

  private static String getLabelText(AbstractFieldEditor comp) {

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

  public static boolean isNotNullField(Class billClass, String fieldName, List<BillElement> notNullFields) {
    for (BillElement billElement : notNullFields) {
      String name = null;
      try {
        name = (String) FieldMapRegister.get(billClass).get(billElement.getElementCode());
        if (name == null || "".equals(name.trim())) {
          name = ZcUtil.convertColumnToField(billElement.getElementCode());
        }
      } catch (RuntimeException e) {
        name = ZcUtil.convertColumnToField(billElement.getElementCode());
      }
      if (name.equalsIgnoreCase(fieldName))
        return true;
    }
    return false;
  }
  /**
   * 返回当前用户是否委托方
   * @return
   */

  public static boolean isWtf() {

    if (WorkEnv.getInstance().containRole(ZcSettingConstants.R_SF_WTF) ) { return true; }
    return false;
  }
  /**
   * 返回当前用户是否鉴定机构
   * @return
   */

  public static boolean isJdjg() {

    if (WorkEnv.getInstance().containRole(ZcSettingConstants.R_SF_JDJG) ||WorkEnv.getInstance().containRole(ZcSettingConstants.R_SF_JD_PERSON)) { return true; }
    return false;
  }

  public static Date getSysDate() {
    return baseDataServiceDelegate.getSysDate(WorkEnv.getInstance().getRequestMeta());
  }
  
  /**
   * 判断当前登录人是否在综合组值班
   * @return
   */
  public static boolean isZhiban(){
    RequestMeta meta=WorkEnv.getInstance().getRequestMeta();
    ISfZongheZhibanServiceDelegate sfZongheZhibanServiceDelegate = (ISfZongheZhibanServiceDelegate) ServiceFactory.create(ISfZongheZhibanServiceDelegate.class, "sfZongheZhibanServiceDelegate");
    ElementConditionDto dto=new ElementConditionDto();
    dto.setNd(meta.getSvNd());
    dto.setCoCode(meta.getSvCoCode());
    SfZongheZhiban zb=sfZongheZhibanServiceDelegate.getCurrent(dto, meta);
    if(zb==null || !meta.getSvUserID().equals(zb.getUserId())){
      return false;
    }
    
    return true;
  }
 public SfJdjg getJdjgInfo(String coCode) {
    
    ElementConditionDto dto=new ElementConditionDto();
    dto.setCoCode(coCode);
    RequestMeta meta=WorkEnv.getInstance().getRequestMeta();
    SfJdjg jg=(SfJdjg) baseDataServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectMainDataLst", dto,meta);
    
    return jg;
  }

 public SfJdjg getJdjgInfo() {
   RequestMeta meta=WorkEnv.getInstance().getRequestMeta();
   return getJdjgInfo(meta.getSvCoCode()); 
  }
 
 public static boolean isMobile(String mobile) {
   Pattern p = Pattern.compile("^((13[0-9])|(12[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(19[0-9])|(18[0-9]))\\d{8}$");  
     
   Matcher m = p.matcher(mobile);  
     
//   System.out.println(m.matches()+"---");  
     
   return m.matches();  
 }
 /**
  * 将单名的姓名中间加入两个空格，如张三 变成 张  三
  * @param name
  * @return
  */
 public static String getPersonName(String name){
   if(name==null)return null;
   if(name.length()>2 || name.length()<2)return name; 
    
    String str1="",str2="";
    str1=name.substring(0, 1);
    str2=name.substring(1, 2);

   return str1+"  "+str2; 
 }
 public static void main(String[] args) {
   
   SfUtil.getPersonName("张三");
 }

 /**
  * 文书审批单终审后，锁定修改，审批等操作
  * @param btns 需要锁定的按钮
  * @param entrust
  */
public void lockBillWithDocAudit(Component[] btns, SfEntrust entrust) {
  if(btns==null || entrust==null)return;
  SfJdDocAudit audit=getJdDocAudit(entrust.getEntrustId());
  if(audit==null){
    setPrintBtn(false, btns);
    return;
  }
  
  
  if(audit.getStatus().equals("exec")){
    for(int i=0;i<btns.length;i++){
      if(btns[i] instanceof FuncButton){
        FuncButton button=(FuncButton)btns[i];
        //fdelete,fcallback,fedit,fmanualcommit,fnew,fnewcommit,fsave,funaudit,funtread都设置为不可见
        if("fdelete".equals(button.getFuncId())
          ||"fcallback".equals(button.getFuncId())
          ||"fedit".equals(button.getFuncId())
          ||"fmanualcommit".equals(button.getFuncId())
          ||"fnew".equals(button.getFuncId())
          ||"fnewcommit".equals(button.getFuncId())
          ||"fsave".equals(button.getFuncId())
          ||"funaudit".equals(button.getFuncId())
          ||"funtread".equals(button.getFuncId())){
          button.setVisible(false);
        }else if("fprint".equals(button.getFuncId())){
          button.setVisible(true);
        }
      }
    }
  }else{
    setPrintBtn(false, btns);
  }
}
private void setPrintBtn(boolean enable,Component[] btns){
  //只有审批后才可以打印
  for(int i=0;i<btns.length;i++){
    if(btns[i] instanceof FuncButton){
      FuncButton button=(FuncButton)btns[i];
      if("fprint".equals(button.getFuncId())){
        button.setVisible(enable);
      }
    }
  }
}

private SfJdDocAudit getJdDocAudit(BigDecimal entrustId) {
 List docsList= baseDataServiceDelegate.queryDataForList("com.ufgov.zc.server.sf.dao.SfJdDocAuditMapper.selectByEntrustId", entrustId, WorkEnv.getInstance().getRequestMeta());
 if(docsList!=null && docsList.size()>0){
   return (SfJdDocAudit) docsList.get(0);
 }
  return null;
}

public String getMac() {
  if(mac==null || mac.length()==0){
    try {
      InetAddress ia = InetAddress.getLocalHost();
      mac=getMACAddress(ia);
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  return mac;
}

//获取MAC地址的方法
private  String getMACAddress(InetAddress ia) throws Exception {
  //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
  byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

  //下面代码是把mac地址拼装成String
  StringBuffer sb = new StringBuffer();

  for (int i = 0; i < mac.length; i++) {
    if (i != 0) {
      sb.append("-");
    }
    //mac[i] & 0xFF 是为了把byte转化为正整数
    String s = Integer.toHexString(mac[i] & 0xFF);
    sb.append(s.length() == 1 ? 0 + s : s);
  }

  //把字符串所有小写字母改为大写成为正规的mac地址并返回
  return sb.toString().toUpperCase();
}

public void setMac(String mac) {
  this.mac = mac;
}

}
