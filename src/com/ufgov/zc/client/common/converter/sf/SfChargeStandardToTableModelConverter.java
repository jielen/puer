package com.ufgov.zc.client.common.converter.sf;

import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfChargeStandard;
import com.ufgov.zc.common.system.constants.SfElementConstants;

public class SfChargeStandardToTableModelConverter {

  public static TableModel convertMainLst(List majorLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfChargeStandard.COL_CODE));
    names.add(LangTransMeta.translate(SfChargeStandard.COL_NAME));
    names.add(LangTransMeta.translate(SfChargeStandard.COL_PRICE));
    names.add(LangTransMeta.translate(SfChargeStandard.COL_ENABLE_DATE));
    names.add(LangTransMeta.translate(SfChargeStandard.COL_STATUS));
    
    if (majorLst != null && majorLst.size() > 0) {

      for (int i = 0; i < majorLst.size(); i++) {

        Vector rowData = new Vector();

        SfChargeStandard standard = (SfChargeStandard) majorLst.get(i);

        rowData.add(standard.getCode());
        rowData.add(standard.getName());
        rowData.add(standard.getPrice());
        rowData.add(standard.getEnableDate());
        rowData.add(AsValDataCache.getName(SfElementConstants.VS_Y_N, standard.getStatus()));
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
