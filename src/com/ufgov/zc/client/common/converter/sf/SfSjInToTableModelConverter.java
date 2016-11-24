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

public class SfSjInToTableModelConverter {

  public static TableModel convertMainLst(List sjInLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfSj.COL_NAME)); 
    names.add(LangTransMeta.translate(SfSj.COL_PACK_SPEC)); 
    names.add(LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID)); 
    names.add(LangTransMeta.translate(SfSjIn.COL_SUPPLIER_ID));
    names.add(LangTransMeta.translate(SfSjIn.COL_AMOUNT));  
    names.add(LangTransMeta.translate(SfSj.COL_UNIT)); 
    names.add(LangTransMeta.translate(SfSjIn.COL_PRICE));  
    names.add(LangTransMeta.translate(SfSjIn.COL_TOTAL_NUM));  
    names.add(LangTransMeta.translate(SfSj.COL_SJ_GROUP)); 
    names.add(LangTransMeta.translate(SfSj.COL_STORE_CONDITION)); 
    names.add(LangTransMeta.translate(SfSjIn.COL_IN_DATE));  
    names.add(LangTransMeta.translate(SfSjIn.COL_EXPIRY_DATE));  
    if (sjInLst != null && sjInLst.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < sjInLst.size(); i++) {

        Vector rowData = new Vector();

        SfSjIn sj = (SfSjIn) sjInLst.get(i);

        rowData.add(sj.getSj().getName()); 
        rowData.add(sj.getSj().getPackSpec()); 
        rowData.add(sj.getSj().getProductor().getName()); 
        rowData.add(sj.getSupplier().getName()); 
        rowData.add(sj.getAmount()); 
        rowData.add("");  
        rowData.add(sj.getPrice());  
        rowData.add(sj.getTotalNum()); 
        rowData.add(sj.getSj().getSjGroup().getGroupName());  
        rowData.add(AsValDataCache.getName(SfSj.VS_SF_SJ_STORE_CONDITION,sj.getSj().getStoreCondition())); 
        rowData.add(sj.getInDate() == null ? null : df.format(sj.getInDate())); 
        rowData.add(sj.getExpiryDate() == null ? null : df.format(sj.getExpiryDate())); 
        
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