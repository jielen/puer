/**
 * 
 */
package com.ufgov.zc.client.sf.util;

import java.util.List;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.sf.jdperson.JdPersonMajorHandler;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.system.model.User;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

/**
 * 鉴定中心人员选择外部部件处理类
 * @author Administrator
 *
 */
public abstract class SfJdPersonSelectHandler  implements IForeignEntityHandler {



  private String sqlId = "com.ufgov.zc.server.sf.dao.SfJdPersonMapper.selectMainDataLst";

  private String columNames[] = { LangTransMeta.translate(SfJdPerson.COL_NAME), LangTransMeta.translate(SfJdPerson.COL_CERTIFICATE_NO)};;


  public SfJdPersonSelectHandler() {
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
      SfJdPerson rowData = (SfJdPerson) showDatas.get(i);
      int col = 0;
      data[i][col++] = rowData.getName();
      data[i][col++] = rowData.getCertificateNo();
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