package com.ufgov.zc.client.common.converter.sf;

import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfSj;

public class SfSjToTableModelConverter {

	  public static TableModel convertMainLst(List majorLst) {
	    // TCJLODO Auto-generated method stub

	    MyTableModel tableModel = null;

	    Vector names = new Vector();

	    Vector values = new Vector();

	    names.add(LangTransMeta.translate(SfSj.COL_NAME)); 
	    names.add(LangTransMeta.translate(SfSj.COL_PACK_SPEC)); 
	    names.add(LangTransMeta.translate(SfSj.COL_UNIT)); 
	    names.add(LangTransMeta.translate(SfSj.COL_PRODUCTOR_ID)); 
	    names.add(LangTransMeta.translate(SfSj.COL_REGIST_CODE)); 
	    names.add(LangTransMeta.translate(SfSj.COL_STORE_CONDITION)); 
	    names.add(LangTransMeta.translate(SfSj.COL_STORE_LIMIT_MIN)); 
	    names.add(LangTransMeta.translate(SfSj.COL_STORE_LIMIT_MAX)); 
	    
	    if (majorLst != null && majorLst.size() > 0) {

	      for (int i = 0; i < majorLst.size(); i++) {

	        Vector rowData = new Vector();

	        SfSj standard = (SfSj) majorLst.get(i);

	        rowData.add(standard.getName()); 
	        rowData.add(standard.getPackSpec()); 
	        rowData.add(standard.getUnit().getUnitName()); 
	        rowData.add(standard.getProductor().getName()); 
	        rowData.add(standard.getRegistCode()); 
	        rowData.add(AsValDataCache.getName(SfSj.VS_SF_SJ_STORE_CONDITION,standard.getStoreCondition())); 
	        rowData.add(standard.getStoreLimitMin()); 
	        rowData.add(standard.getStoreLimitMax()); 
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
