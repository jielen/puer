/**
 * 
 */
package com.ufgov.zc.client.sf.entrust;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

/**
 * @author Administrator
 *
 */
public abstract class SfEntrustHandler implements IForeignEntityHandler {

  private boolean isMultipleSelect;
  private String sqlId = "com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectEntrustLst";
  
  private String columNames[] = {"委托编号","案//事件名称","委托方","鉴定专业","是否受理","受理时间"};
  public SfEntrustHandler() {
    this(false);
  }

  public SfEntrustHandler(boolean isMultipleSelect) {
    this.isMultipleSelect = isMultipleSelect;
  }

  @SuppressWarnings("unchecked")
  public abstract void excute(List selectedDatas);

  @SuppressWarnings("unchecked")
  public TableModel createTableModel(List showDatas) {

    Object data[][] = new Object[showDatas.size()][columNames.length];
    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < showDatas.size(); i++) {
      SfEntrust rowData = (SfEntrust) showDatas.get(i);
      int col = 0;
      data[i][col++] = rowData.getCode();
      data[i][col++] = rowData.getName();
      data[i][col++] = rowData.getEntrustor().getName();   
      data[i][col++] = AsValDataCache.getName(SfMajor.SF_VS_MAJOR, rowData.getMajorCode());   
      data[i][col++] = AsValDataCache.getName(SfElementConstants.VS_Y_N, rowData.getIsAccept());
      data[i][col++] = rowData.getAcceptDate()==null?null:df.format(rowData.getAcceptDate());
    }

    MyTableModel model = new MyTableModel(data, columNames) {
      private static final long serialVersionUID = 5069824753340617654L;
      public boolean isCellEditable(int row, int colum) {
        return false;
      }
    };
    return model;
  }

  public boolean isMultipleSelect() {
    return this.isMultipleSelect;
  }

  public String[] getColumNames() {
    return columNames;
  }

  public void setColumNames(String[] columNames) {
    this.columNames = columNames;
  }

  public String getSqlId() {
    return sqlId;
  }

  public void setSqlId(String sqlId) {
    this.sqlId = sqlId;
  }
}
