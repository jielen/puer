package com.ufgov.zc.client.common.converter.sf;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfSj;
import com.ufgov.zc.common.sf.model.SfSjIn;
import com.ufgov.zc.common.sf.model.SfSjOut;
import com.ufgov.zc.common.util.EmpMeta;

public class SfSjOutToTableModelConverter {

  public static TableModel convertMainLst(List sjInLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfSj.COL_NAME));  
    names.add(LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID)); 
    names.add(LangTransMeta.translate(SfSjIn.COL_SUPPLIER_ID)); 
    names.add(LangTransMeta.translate(SfSj.COL_SJ_GROUP));  
    names.add(LangTransMeta.translate(SfSjIn.COL_IN_DATE));  
    names.add(LangTransMeta.translate(SfSjIn.COL_EXPIRY_DATE));  
    names.add(LangTransMeta.translate(SfSjOut.COL_AMOUNT));  
    names.add(LangTransMeta.translate(SfSj.COL_UNIT)); 
    names.add(LangTransMeta.translate(SfSjOut.COL_PROPOSER));  
    names.add(LangTransMeta.translate(SfSjOut.COL_OUT_DATE));  
    if (sjInLst != null && sjInLst.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < sjInLst.size(); i++) {

        Vector rowData = new Vector();

        SfSjOut sjOut = (SfSjOut) sjInLst.get(i);

        rowData.add(sjOut.getSjin().getSj().getName());
        rowData.add(sjOut.getSjin().getSj().getProductor().getName()); 
        rowData.add(sjOut.getSjin().getSupplier().getName()); 
        rowData.add(sjOut.getSjin().getSj().getSjGroup().getGroupName());  
        rowData.add(sjOut.getSjin().getInDate() == null ? null : df.format(sjOut.getSjin().getInDate())); 
        rowData.add(sjOut.getSjin().getExpiryDate() == null ? null : df.format(sjOut.getSjin().getExpiryDate())); 
        rowData.add(sjOut.getAmount()); 
        rowData.add(sjOut.getSjin().getSj().getUnit().getUnitName());  
        rowData.add(sjOut.getProposer()==null?"":EmpMeta.getEmpName(sjOut.getProposer()));   
        rowData.add(sjOut.getOutDate() == null ? null : df.format(sjOut.getOutDate()));          
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

    tableModel.setList(sjInLst);

    return tableModel;
  }
}
