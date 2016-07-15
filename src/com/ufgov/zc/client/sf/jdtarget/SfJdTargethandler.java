/**
 * 
 */
package com.ufgov.zc.client.sf.jdtarget;

import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

/**
 * @author Administrator
 *
 */
public abstract class SfJdTargethandler implements IForeignEntityHandler {



  private boolean isMultipleSelect;

  private String sqlId = "com.ufgov.zc.server.sf.dao.SfJdTargetMapper.getJdTargetLst";

  private String columNames[] = { "姓名", "性别", "年龄", "证件类型","证件号码", "联系电话" };;

  public SfJdTargethandler() {

    this(false);

  }

  public SfJdTargethandler(boolean isMultipleSelect) {

    this.isMultipleSelect = isMultipleSelect;

  }

  @SuppressWarnings("unchecked")
  @Override
  public TableModel createTableModel(List showDatas) {
    Object data[][] = new Object[showDatas.size()][columNames.length];
    for (int i = 0; i < showDatas.size(); i++) {
      SfJdTarget rowData = (SfJdTarget) showDatas.get(i);
      int col = 0;
      data[i][col++] = rowData.getName();
      data[i][col++] = AsValDataCache.getName(SfElementConstants.VS_SEX, rowData.getSex());
      data[i][col++] = rowData.getAge();
      data[i][col++] = rowData.getIdName();
      data[i][col++] = rowData.getIdCode();
      data[i][col++] = rowData.getPhone();

    }

    MyTableModel model = new MyTableModel(data, columNames) {

      private static final long serialVersionUID = -1045880580648402603L;

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
