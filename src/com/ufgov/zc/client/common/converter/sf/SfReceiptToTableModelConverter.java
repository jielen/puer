package com.ufgov.zc.client.common.converter.sf;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.AsEmpMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfReceipt;
import com.ufgov.zc.common.sf.model.SfReceipt;

public class SfReceiptToTableModelConverter {

  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfReceipt.COL_RECEIPT_TYPE));
    names.add(LangTransMeta.translate(SfReceipt.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfReceipt.COL_NAME));
    names.add(LangTransMeta.translate(SfReceipt.COL_INPUTOR));
    names.add(LangTransMeta.translate(SfReceipt.COL_INPUT_DATE));
    names.add(LangTransMeta.translate(SfReceipt.COL_STATUS));
    if (mainLst != null && mainLst.size() > 0) {

      SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfReceipt agreement = (SfReceipt) mainLst.get(i);
        rowData.add(AsValDataCache.getName(SfReceipt.SF_VS_RECEIPT_TYPE, agreement.getReceiptType()));
        rowData.add(agreement.getEntrustCode());
        rowData.add(agreement.getName());
        rowData.add(AsEmpMeta.getEmpName(agreement.getInputor()));
        rowData.add(df.format(agreement.getInputDate()));
        rowData.add(AsValDataCache.getName(SfReceipt.SF_VS_RECEIPT_STATUS, agreement.getStatus()));
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
