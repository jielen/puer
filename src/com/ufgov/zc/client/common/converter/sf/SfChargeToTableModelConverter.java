package com.ufgov.zc.client.common.converter.sf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.ColumnBeanPropertyPair;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfPayFees;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfChargeToTableModelConverter {

  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfCharge.COL_ENTRUST_CODE));
    names.add(LangTransMeta.translate(SfCharge.COL_NAME));
    names.add(LangTransMeta.translate(SfCharge.COL_TOTAL_PRICE));
    names.add(LangTransMeta.translate(SfCharge.COL_PAYER));
    names.add(LangTransMeta.translate(SfCharge.COL_CASH_DATE));
    names.add(LangTransMeta.translate(SfCharge.COL_STATUS));
    if (mainLst != null && mainLst.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfCharge charge = (SfCharge) mainLst.get(i);
        rowData.add(charge.getEntrustCode());
        rowData.add(charge.getName());
        rowData.add(charge.getTotalPrice());
        rowData.add(charge.getPayer());
        rowData.add(charge.getCashDate() == null ? null : df.format(charge.getCashDate()));
        rowData.add(AsValDataCache.getName(SfCharge.SF_VS_CHARGE_STATUS, charge.getStatus()));
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

  public static TableModel convertChargeDetailsTableData(List itemList) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<SfChargeDetail> tm = new BeanTableModel<SfChargeDetail>() {
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

        SfChargeDetail bean = dataBeanList.get(rowIndex);

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

    tm.setOidFieldName("tempId");
    tm.setDataBean(itemList, detailInfo);
    return tm;
  }

  private static List<ColumnBeanPropertyPair> detailInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {

    detailInfo.add(new ColumnBeanPropertyPair(SfChargeDetail.COL_PRICE_TYPE, "priceType", LangTransMeta.translate(SfChargeDetail.COL_PRICE_TYPE)));
    detailInfo.add(new ColumnBeanPropertyPair(SfChargeDetail.COL_CHARGE_STANDARD_NAME, "chargeStandardName", LangTransMeta
      .translate(SfChargeDetail.COL_CHARGE_STANDARD_NAME)));
    detailInfo.add(new ColumnBeanPropertyPair(SfChargeDetail.COL_PRICE, "price", LangTransMeta.translate(SfChargeDetail.COL_PRICE)));
    detailInfo.add(new ColumnBeanPropertyPair(SfChargeDetail.COL_QUANTITY, "quantity", LangTransMeta.translate(SfChargeDetail.COL_QUANTITY)));
    detailInfo.add(new ColumnBeanPropertyPair(SfChargeDetail.COL_TOTAL_PRICE, "totalPrice", LangTransMeta.translate(SfChargeDetail.COL_TOTAL_PRICE)));
    detailInfo.add(new ColumnBeanPropertyPair(SfChargeDetail.COL_REMARK, "remark", LangTransMeta.translate(SfChargeDetail.COL_REMARK)));
  }

  public static List<ColumnBeanPropertyPair> getDetailInfo() {
    return detailInfo;
  }

  public static DefaultTableModel convertEntrustFeesTableData(List entrustList) {

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfEntrust.COL_CODE));
    names.add(LangTransMeta.translate(SfEntrust.COL_ENTRUSTOR_NAME));
    names.add(LangTransMeta.translate(SfEntrust.COL_MAJOR_NAME));
    names.add(LangTransMeta.translate(SfEntrust.COL_JD_FZR));
    names.add(LangTransMeta.translate(SfEntrust.COL_JD_FHR));
    names.add(LangTransMeta.translate(SfEntrust.COL_JD_CHARGE));
    names.add("待收");
    names.add("已收");
    names.add("收费时间");

    if (entrustList != null && entrustList.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < entrustList.size(); i++) {

        Vector rowData = new Vector();

        SfPayFees fees = (SfPayFees) entrustList.get(i);

        rowData.add(fees.getEntrust().getCode());
        rowData.add(fees.getEntrustor().getName());
        rowData.add(fees.getMajorName());
        rowData.add(fees.getJdFzrName());
        rowData.add(fees.getJdFhrName());
        rowData.add(fees.getTotalFee());
        rowData.add(fees.getNeedFee());
        rowData.add(fees.getPayedFee());
        rowData.add(fees.getPayTime() == null ? null : df.format(fees.getPayTime()));
        //
        //        rowData.add(zcXmcgHt.getNd());

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

    tableModel.setList(entrustList);

    return tableModel;

  }

  public static DefaultTableModel convertPayFeesTableData(List payFeeLst) {

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfCharge.COL_TOTAL_PRICE));
    names.add(LangTransMeta.translate(SfCharge.COL_PAYER));
    names.add(LangTransMeta.translate(SfCharge.COL_CASHIER));
    names.add(LangTransMeta.translate(SfCharge.COL_CASH_DATE));
    names.add(LangTransMeta.translate(SfCharge.COL_REMARK));

    if (payFeeLst != null && payFeeLst.size() > 0) {

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < payFeeLst.size(); i++) {

        Vector rowData = new Vector();

        SfCharge charge = (SfCharge) payFeeLst.get(i);
        rowData.add(charge.getTotalPrice());
        rowData.add(charge.getPayer());
        rowData.add(charge.getCashierName());
        rowData.add(charge.getCashDate() == null ? null : df.format(charge.getCashDate()));
        rowData.add(charge.getRemark());
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

    tableModel.setList(payFeeLst);

    return tableModel;

  }
}
