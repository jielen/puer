package com.ufgov.zc.client.common.converter.sf;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfEntrustManage;
import com.ufgov.zc.common.util.EmpMeta;

public class SfEntrustManageToTableModelConverter {
  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfEntrustManage.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfEntrustManage.COL_MANAGE_TYPE));
    names.add(LangTransMeta.translate(SfEntrustManage.COL_MANAGE_TIME)); 
    names.add(LangTransMeta.translate(SfEntrustManage.COL_INPUTOR));  
    if (mainLst != null && mainLst.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfEntrustManage manage = (SfEntrustManage) mainLst.get(i);
        rowData.add(manage.getEntrustCode());
        String str="";
        if(manage.getManageType()!=null){
          str=AsValDataCache.getName(SfEntrustManage.SF_VS_ENTRUST_MANAGE_TYPE, manage.getManageType());
        }else{
          str=manage.getManageTypeTxt();
        }
        rowData.add(str);
        rowData.add(manage.getManageTime() == null ? null : df.format(manage.getManageTime()));
        rowData.add(manage.getInputor()==null?"":EmpMeta.getEmpName(manage.getInputor()));
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
