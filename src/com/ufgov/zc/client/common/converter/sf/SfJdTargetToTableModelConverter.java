package com.ufgov.zc.client.common.converter.sf;

import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.system.constants.SfElementConstants;

public class SfJdTargetToTableModelConverter {

  public static TableModel convertToTableModel(List entrustorLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfJdTarget.COL_NAME));
    names.add(LangTransMeta.translate(SfJdTarget.COL_SEX));
    names.add(LangTransMeta.translate(SfJdTarget.COL_AGE));
//    names.add(LangTransMeta.translate(SfJdTarget.COL_PHONE));
    names.add(LangTransMeta.translate(SfJdTarget.COL_ADDRESS));
    names.add(LangTransMeta.translate(SfJdTarget.COL_ZIP));
    names.add(LangTransMeta.translate(SfJdTarget.COL_ID_NAME));
    names.add(LangTransMeta.translate(SfJdTarget.COL_ID_CODE));
    if (entrustorLst != null && entrustorLst.size() > 0) {

      for (int i = 0; i < entrustorLst.size(); i++) {

        Vector rowData = new Vector();

        SfJdTarget jdTarget = (SfJdTarget) entrustorLst.get(i);

        rowData.add(jdTarget.getName());
        rowData.add(AsValDataCache.getName(SfElementConstants.VS_SEX, jdTarget.getSex()));
        rowData.add(jdTarget.getAge()==null?"":jdTarget.getAge().intValue());
//        rowData.add(jdTarget.getPhone());
        rowData.add(jdTarget.getAddress());
        rowData.add(jdTarget.getZip());
        rowData.add(jdTarget.getIdName());
        rowData.add(jdTarget.getIdCode());
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

    tableModel.setList(entrustorLst);

    return tableModel;
  }

}
