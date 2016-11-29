package com.ufgov.zc.client.common.converter.sf;

import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.system.constants.SfElementConstants;

public class SfEntrustorToTableModelConverter {

  public static TableModel convertToTableModel(List entrustorLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfEntrustor.ENTRUSTOR_TYPE));
//    names.add(LangTransMeta.translate(SfEntrustor.CODE));
    names.add(LangTransMeta.translate(SfEntrustor.NAME));
    names.add(LangTransMeta.translate(SfEntrustor.SHORT_NAME));
    names.add(LangTransMeta.translate(SfEntrustor.LINK_MAN));
    names.add(LangTransMeta.translate(SfEntrustor.LINK_TEL));
    names.add(LangTransMeta.translate(SfEntrustor.ADDRESS));
    if (entrustorLst != null && entrustorLst.size() > 0) {

      for (int i = 0; i < entrustorLst.size(); i++) {

        Vector rowData = new Vector();

        SfEntrustor entrustor = (SfEntrustor) entrustorLst.get(i);

        rowData.add(AsValDataCache.getName(SfEntrustor.SF_VS_ENTRUSTOR_TYPE, entrustor.getEntrustorType()));
//        rowData.add(entrustor.getCode());
        rowData.add(entrustor.getName());
        rowData.add(entrustor.getShortName());
        rowData.add(entrustor.getLinkMan());
        rowData.add(entrustor.getLinkTel());
        rowData.add(entrustor.getAddress());
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
