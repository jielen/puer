package com.ufgov.zc.client.common.converter.sf;

import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.common.sf.model.SfOutInfoReq;

public class SfOutInfoReqToTableModelConverter {
  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfOutInfoReq.COL_OUT_INFO_REQ_CODE));
    names.add(LangTransMeta.translate(SfOutInfoReq.COL_OUT_INFO_REQ_NAME));
    names.add(LangTransMeta.translate(SfOutInfoReq.COL_REMARK));
    if (mainLst != null && mainLst.size() > 0) {

      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfOutInfoReq agreement = (SfOutInfoReq) mainLst.get(i);
        rowData.add(agreement.getOutInfoReqCode());
        rowData.add(agreement.getOutInfoReqName());
        rowData.add(agreement.getRemark());
        values.add(rowData);
      }
    }

    tableModel = new MyTableModel(values, names) {
      public Class getColumnClass(int column) {
        if ((column >= 0) && (column < getColumnCount()) && this.getRowCount() > 0) {
          for (int row = 0; row < this.getRowCount(); row++) {
            if (getValueAt(row, column) != null) {
              return getValueAt(row, column).getClass();
            }
          }
        }
        return Object.class;
      }

      public boolean isCellEditable(int row, int colum) {
        return false;
      }
    };
    tableModel.setList(mainLst);
    return tableModel;
  }
}
