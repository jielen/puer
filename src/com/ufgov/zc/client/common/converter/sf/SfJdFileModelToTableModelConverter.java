package com.ufgov.zc.client.common.converter.sf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.ColumnBeanPropertyPair;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdRecordFileModel;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfJdFileModelToTableModelConverter {

	  public static TableModel convertToTableModel(List dataLst) {
	    // TCJLODO Auto-generated method stub

	    MyTableModel tableModel = null;

	    Vector names = new Vector();

	    Vector values = new Vector();

	    names.add(LangTransMeta.translate(SfJdRecordFileModel.COL_NAME));
	    names.add(LangTransMeta.translate(SfMajor.COL_MAJOR_NAME));
	    names.add(LangTransMeta.translate(SfJdRecordFileModel.COL_FILE_TYPE));
	    names.add("文书类别");
	    names.add(LangTransMeta.translate(SfJdRecordFileModel.COL_DESCRIPTION));
	    names.add(LangTransMeta.translate(SfJdRecordFileModel.COL_IS_ENABLE));

	    if (dataLst != null && dataLst.size() > 0) {

	      for (int i = 0; i < dataLst.size(); i++) {

	        Vector rowData = new Vector();

	        SfJdRecordFileModel model = (SfJdRecordFileModel) dataLst.get(i);

	        rowData.add(model.getName()); 
	        rowData.add(AsValDataCache.getName("SF_VS_MAJOR", model.getMajorCode())); 
	        rowData.add(AsValDataCache.getName(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_TYPE, model.getFileType())); 
	        rowData.add(AsValDataCache.getName(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_DOC_TYPE, model.getDocType())); 
	        rowData.add(model.getDescription()); 
	        rowData.add(AsValDataCache.getName(SfJdRecordFileModel.SF_VS_JD_FILE_MODEL_STATUS, model.getIsEnable())); 
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

	    tableModel.setList(dataLst);

	    return tableModel;
	  }
 
	}
