package com.ufgov.zc.client.common.converter.sf;

import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.common.sf.model.SfSjUnit;

public class SfSjUnitToTableModelConverter {


  public static TableModel convertToTableModel(List majorLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfSjUnit.COL_UNIT_NAME));  

    if (majorLst != null && majorLst.size() > 0) {

      for (int i = 0; i < majorLst.size(); i++) {

        Vector rowData = new Vector();

        SfSjUnit major = (SfSjUnit) majorLst.get(i);
 
        rowData.add(major.getUnitName());
//        rowData.add(major.getParentCode());
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

    tableModel.setList(majorLst);

    return tableModel;
  }
 
}
