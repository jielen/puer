/**
 * 
 */
package com.ufgov.zc.client.sf.util;

import java.awt.Color;
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
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.commonbiz.fieldmap.FieldMapRegister;
import com.ufgov.zc.common.commonbiz.model.BillElement;
import com.ufgov.zc.common.sf.model.SfEntrust;
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
   Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(19[0-9])|(18[0,5-9]))\\d{8}$");  
     
   Matcher m = p.matcher(mobile);  
     
//   System.out.println(m.matches()+"---");  
     
   return m.matches();  
 }
}
