package com.ufgov.zc.client.sf.util;

import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

public abstract class SfMajorSelectHandler  implements IForeignEntityHandler {



  private String sqlId = "com.ufgov.zc.server.sf.dao.SfMajorMapper.getMajorLst";

  private String columNames[] = { LangTransMeta.translate(SfMajor.COL_MAJOR_CODE), LangTransMeta.translate(SfMajor.COL_MAJOR_NAME)};;


  public SfMajorSelectHandler() {
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
      SfMajor rowData = (SfMajor) showDatas.get(i);
      int col = 0;
      data[i][col++] = rowData.getMajorCode();
      data[i][col++] = rowData.getMajorName();
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
