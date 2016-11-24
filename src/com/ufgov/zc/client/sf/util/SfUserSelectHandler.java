package com.ufgov.zc.client.sf.util;

import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.common.console.model.AsEmp;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

public abstract class SfUserSelectHandler  implements IForeignEntityHandler {



  private String sqlId = "com.ufgov.zc.server.sf.dao.SfJdPersonMapper.getUser";

  private String columNames[] = { "姓名"};


  public SfUserSelectHandler() {
  }
  public String getSqlId() {
    return sqlId;
  }
  public String[] getColumNames() {
    return columNames;
  }
  public TableModel createTableModel(List showDatas) {
    Object data[][] = new Object[showDatas.size()][columNames.length];
    for (int i = 0; i < showDatas.size(); i++) {
      AsEmp rowData = (AsEmp) showDatas.get(i);
      int col = 0;
      data[i][col++] = rowData.getEmpName();
    }

    MyTableModel model = new MyTableModel(data, columNames) {
      public boolean isCellEditable(int row, int colum) {
        return false;
      }
    };
    return model;

  }

  public boolean isMultipleSelect() {

    return false;

  }


}
