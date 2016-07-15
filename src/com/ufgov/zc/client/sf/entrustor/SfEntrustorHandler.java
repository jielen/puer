/**
 * 
 */
package com.ufgov.zc.client.sf.entrustor;

import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.model.EmProMake;

/**
 * @author Administrator
 *
 */
public abstract class SfEntrustorHandler implements IForeignEntityHandler {



  private boolean isMultipleSelect;

  private String sqlId = "com.ufgov.zc.server.sf.dao.SfEntrustorMapper.getEntrustorLst";

  private String columNames[] = { "名称", "地址", "联系人",  "联系电话" };;

  public SfEntrustorHandler() {

    this(false);

  }

  public SfEntrustorHandler(boolean isMultipleSelect) {

    this.isMultipleSelect = isMultipleSelect;

  }

  @SuppressWarnings("unchecked")
  @Override
  public TableModel createTableModel(List showDatas) {

    Object data[][] = new Object[showDatas.size()][columNames.length];
    for (int i = 0; i < showDatas.size(); i++) {
      SfEntrustor rowData = (SfEntrustor) showDatas.get(i);
      int col = 0;
      data[i][col++] = rowData.getName();
      data[i][col++] = rowData.getAddress();
      data[i][col++] = rowData.getLinkMan();
      data[i][col++] = rowData.getLinkTel();
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
