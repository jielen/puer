package com.ufgov.zc.client.common.converter.sf;

import java.text.SimpleDateFormat;
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
import com.ufgov.zc.common.sf.model.SfJdDocAudit;
import com.ufgov.zc.common.sf.model.SfJdDocAuditDetail;
import com.ufgov.zc.common.sf.model.SfJdDocType;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfJdDocAuditToTableModelConverter {

public static TableModel convertMainLst(List mainLst) {
  // TCJLODO Auto-generated method stub

  MyTableModel tableModel = null;
  Vector names = new Vector();
  Vector values = new Vector();

  names.add(LangTransMeta.translate(SfJdDocAudit.COL_ENTRUST_CODE));
  names.add(LangTransMeta.translate(SfJdDocAudit.COL_NAME));
  names.add(LangTransMeta.translate(SfJdDocAudit.COL_REPORT_TYPE));
  names.add(LangTransMeta.translate(SfJdDocAudit.COL_STATUS));
  names.add(LangTransMeta.translate(SfJdDocAudit.COL_INPUT_DATE));
  if (mainLst != null && mainLst.size() > 0) {

    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    for (int i = 0; i < mainLst.size(); i++) {
      Vector rowData = new Vector();
      SfJdDocAudit outInfo = (SfJdDocAudit) mainLst.get(i);
      rowData.add(outInfo.getEntrustCode());
      rowData.add(outInfo.getName());
      rowData.add(AsValDataCache.getName(SfJdReport.SF_VS_JD_RESULT_TYPE, outInfo.getReportType()));
      rowData.add(AsValDataCache.getName(SfJdDocAudit.SF_VS_JD_DOC_AUDIT_STATUS, outInfo.getStatus()));
      rowData.add(outInfo.getInputDate()==null?null:df.format(outInfo.getInputDate()));
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

public static TableModel convertDetailTableData(List itemList) {
  // TCJLODO Auto-generated method stub
    BeanTableModel<SfJdDocAuditDetail> tm = new BeanTableModel<SfJdDocAuditDetail>() {
      /**
       * 
       */
      private static final long serialVersionUID = -115294332374634087L;
      
      @Override
      public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
      }
      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  
        SfJdDocAuditDetail bean = dataBeanList.get(rowIndex);
  
        String currentColName = this.getColumnIdentifier(columnIndex);
  
        if (aValue instanceof BaseElement) {
  
          BeanUtil.set(columnBeanPropertyPairList.get(columnIndex).getBeanPropertyName(), ((BaseElement) aValue).getName(), bean);
  
          fireTableCellUpdated(rowIndex, columnIndex);
  
          putEditedData(dataBeanList.get(rowIndex));
  
        } else {
          super.setValueAt(aValue, rowIndex, columnIndex);
        }
      }
    };
  
    tm.setOidFieldName("docName");
    tm.setDataBean(itemList, detailInfo);
    return tm;
  }

private static List<ColumnBeanPropertyPair> detailInfo = new ArrayList<ColumnBeanPropertyPair>();

static {
  detailInfo.add(new ColumnBeanPropertyPair(SfJdDocAuditDetail.COL_DOC_NAME, "docName", LangTransMeta.translate(SfJdDocAuditDetail.COL_DOC_NAME)));
  detailInfo.add(new ColumnBeanPropertyPair(SfJdDocAuditDetail.COL_QUANTITY, "quantity", LangTransMeta.translate(SfJdDocAuditDetail.COL_QUANTITY)));
  detailInfo.add(new ColumnBeanPropertyPair(SfJdDocAuditDetail.COL_REMARK, "remark", LangTransMeta.translate(SfJdDocAuditDetail.COL_REMARK)));
}
public static List<ColumnBeanPropertyPair> getDetailInfo(){
  return detailInfo;
}
}
