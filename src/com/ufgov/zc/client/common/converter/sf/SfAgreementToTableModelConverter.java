package com.ufgov.zc.client.common.converter.sf;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.AsEmpMeta;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfAgreement;

public class SfAgreementToTableModelConverter {

  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfAgreement.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfAgreement.COL_NAME));
    names.add(LangTransMeta.translate(SfAgreement.COL_INPUTOR));
    names.add(LangTransMeta.translate(SfAgreement.COL_INPUT_DATE));
    names.add(LangTransMeta.translate(SfAgreement.COL_STATUS));
    if (mainLst != null && mainLst.size() > 0) {
      SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");      
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfAgreement agreement = (SfAgreement) mainLst.get(i);
        rowData.add(agreement.getEntrustCode());
        rowData.add(agreement.getName());
        rowData.add(AsEmpMeta.getEmpName(agreement.getInputor()));
        rowData.add(agreement.getInputDate()==null?null:df.format(agreement.getInputDate()));
        rowData.add(AsValDataCache.getName(SfAgreement.SF_VS_AGREEMENT_STATUS, agreement.getStatus()));
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
