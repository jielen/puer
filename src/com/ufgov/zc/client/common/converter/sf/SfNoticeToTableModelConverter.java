package com.ufgov.zc.client.common.converter.sf;

import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfNotice;
import com.ufgov.zc.common.sf.model.SfSj;

public class SfNoticeToTableModelConverter {

	  public static TableModel convertMainLst(List majorLst) {
	    // TCJLODO Auto-generated method stub

	    MyTableModel tableModel = null;

	    Vector names = new Vector();

	    Vector values = new Vector();

	    names.add(LangTransMeta.translate(SfNotice.COL_NAME));  
	    names.add(LangTransMeta.translate(SfNotice.COL_BEFORE_TIMES1)); 
	    names.add(LangTransMeta.translate(SfNotice.COL_TIME_UNIT1)); 
	    names.add(LangTransMeta.translate(SfNotice.COL_RATE1)); 
	    names.add(LangTransMeta.translate(SfNotice.COL_RATE1_TYPE));  
	    
	    if (majorLst != null && majorLst.size() > 0) {

	      for (int i = 0; i < majorLst.size(); i++) {

	        Vector rowData = new Vector();

	        SfNotice standard = (SfNotice) majorLst.get(i);

	        rowData.add(standard.getName()); 
	        rowData.add(standard.getBeforeTimes1());    
	        rowData.add(AsValDataCache.getName(SfNotice.VS_SF_NOTICE_RATE_TYPE,standard.getTimeUnit1()));  
	        rowData.add(standard.getRate1());   
	        rowData.add(AsValDataCache.getName(SfNotice.VS_SF_NOTICE_RATE_TYPE,standard.getRate1Type()));  
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
