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
import com.ufgov.zc.common.sf.model.SfOutInfo;
import com.ufgov.zc.common.sf.model.SfOutInfoDetail;
import com.ufgov.zc.common.sf.model.SfOutInfoReq;
import com.ufgov.zc.common.sf.model.SfOutInfoType;
import com.ufgov.zc.common.sf.model.SfOutInfoValidateDetail;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfOutInfoToTableModelConverter {

  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfOutInfo.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfOutInfo.COL_NAME));
    names.add(LangTransMeta.translate(SfOutInfo.COL_TGF));
    names.add(LangTransMeta.translate(SfOutInfo.COL_TGF_PHONE));
    names.add(LangTransMeta.translate(SfOutInfo.COL_ACCEPT_DATE));
    names.add(LangTransMeta.translate(SfOutInfo.COL_STATUS));
    if (mainLst != null && mainLst.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfOutInfo outInfo = (SfOutInfo) mainLst.get(i);
        rowData.add(outInfo.getEntrustCode());
        rowData.add(outInfo.getName());
        rowData.add(outInfo.getTgf());
        rowData.add(outInfo.getTgfPhone());
        rowData.add(outInfo.getAcceptDate() == null ? null : df.format(outInfo.getAcceptDate()));
        rowData.add(AsValDataCache.getName(SfOutInfo.SF_VS_OUT_INFO_STATUS, outInfo.getStatus()));
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
    BeanTableModel<SfOutInfoDetail> tm = new BeanTableModel<SfOutInfoDetail>() {
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

        SfOutInfoDetail bean = dataBeanList.get(rowIndex);

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

    tm.setOidFieldName("outInfoDetailId");
    tm.setDataBean(itemList, detailInfo);
    return tm;
  }

  public static TableModel convertValidateTableData(List itemList) {
    // TCJLODO Auto-generated method stub
    BeanTableModel<SfOutInfoValidateDetail> tm = new BeanTableModel<SfOutInfoValidateDetail>() {
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

        SfOutInfoValidateDetail bean = dataBeanList.get(rowIndex);

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

    tm.setOidFieldName("outInfoReqCode");
    tm.setDataBean(itemList, validateDetailInfo);
    return tm;
  }

  private static List<ColumnBeanPropertyPair> detailInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {
    detailInfo.add(new ColumnBeanPropertyPair(SfOutInfoType.COL_OUT_INFO_TYPE_NAME, "infoType.outInfoTypeName", LangTransMeta
      .translate(SfOutInfoType.COL_OUT_INFO_TYPE_NAME)));
    detailInfo.add(new ColumnBeanPropertyPair(SfOutInfoDetail.COL_TI_QU_FANG_SHI, "tiQuFangShi", LangTransMeta
      .translate(SfOutInfoDetail.COL_TI_QU_FANG_SHI)));
    detailInfo.add(new ColumnBeanPropertyPair(SfOutInfoDetail.COL_NAME, "name", LangTransMeta.translate(SfOutInfoDetail.COL_NAME)));
    detailInfo.add(new ColumnBeanPropertyPair(SfOutInfoDetail.COL_QUANTITY, "quantity", LangTransMeta.translate(SfOutInfoDetail.COL_QUANTITY)));
    detailInfo.add(new ColumnBeanPropertyPair(SfOutInfoDetail.COL_UNIT, "unit", LangTransMeta.translate(SfOutInfoDetail.COL_UNIT)));
    detailInfo.add(new ColumnBeanPropertyPair(SfOutInfoDetail.COL_PRODUCT_TIME, "productTime", LangTransMeta
      .translate(SfOutInfoDetail.COL_PRODUCT_TIME)));
    detailInfo.add(new ColumnBeanPropertyPair(SfOutInfoDetail.COL_DESCRIPTION, "description", LangTransMeta
      .translate(SfOutInfoDetail.COL_DESCRIPTION)));
  }

  public static List<ColumnBeanPropertyPair> getDetailInfo() {
    return detailInfo;
  }

  private static List<ColumnBeanPropertyPair> validateDetailInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {
    validateDetailInfo.add(new ColumnBeanPropertyPair(SfOutInfoReq.COL_OUT_INFO_REQ_NAME, "infoReq.outInfoReqName", LangTransMeta
      .translate(SfOutInfoReq.COL_OUT_INFO_REQ_NAME)));
    validateDetailInfo.add(new ColumnBeanPropertyPair(SfOutInfoValidateDetail.COL_VALIDATE_RESULT, "validateResult", LangTransMeta
      .translate(SfOutInfoValidateDetail.COL_VALIDATE_RESULT)));
    validateDetailInfo.add(new ColumnBeanPropertyPair(SfOutInfoValidateDetail.COL_REMARK, "remark", LangTransMeta
      .translate(SfOutInfoValidateDetail.COL_REMARK)));
  }

  public static List<ColumnBeanPropertyPair> getValidateDetailInfo() {
    return validateDetailInfo;
  }
}
