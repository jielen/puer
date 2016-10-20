/**
 * 
 */
package com.ufgov.zc.client.common.converter.sf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.component.table.BeanTableModel;
import com.ufgov.zc.client.component.table.ColumnBeanPropertyPair;
import com.ufgov.zc.common.sf.model.ZcFaCard;
import com.ufgov.zc.common.sf.model.ZcFaCardDoc;
import com.ufgov.zc.common.sf.model.ZcFaCardSub;
import com.ufgov.zc.common.system.Guid;
import com.ufgov.zc.common.system.constants.ZcElementConstants;
import com.ufgov.zc.common.system.model.AsFile;

/**
 * @author Administrator
 *
 */
public class ZcFaCardToTableModelConverter {  
  
private static List<ColumnBeanPropertyPair> cardDocInfo = new ArrayList<ColumnBeanPropertyPair>();

private static List<ColumnBeanPropertyPair> cardSubInfo = new ArrayList<ColumnBeanPropertyPair>();

static {
  cardSubInfo.add(new ColumnBeanPropertyPair("FA_SUB_CODE", "faSubCode", LangTransMeta.translate("FA_SUB_CODE")));
  cardSubInfo.add(new ColumnBeanPropertyPair("FA_SUB_NUM", "faSubNum", LangTransMeta.translate("FA_SUB_NUM")));
  cardSubInfo.add(new ColumnBeanPropertyPair("FA_SUB_PRICE", "faSubPrice", LangTransMeta.translate("FA_SUB_PRICE")));
  cardSubInfo.add(new ColumnBeanPropertyPair("FA_SUB_SPEC", "faSubSpec", LangTransMeta.translate("FA_SUB_SPEC")));
  cardSubInfo.add(new ColumnBeanPropertyPair("FA_SUB_PURP", "faSubPurp", LangTransMeta.translate("FA_SUB_PURP")));
  cardSubInfo.add(new ColumnBeanPropertyPair("FA_SUB_DATE", "faSubDate", LangTransMeta.translate("FA_SUB_DATE")));
}

static {
  cardDocInfo.add(new ColumnBeanPropertyPair("CARD_DOC_LIST", "cardDocList", LangTransMeta.translate("CARD_DOC_LIST")));
  cardDocInfo.add(new ColumnBeanPropertyPair("CARD_DOC_PURP", "cardDocPurp", LangTransMeta.translate("CARD_DOC_PURP")));
  cardDocInfo.add(new ColumnBeanPropertyPair("CARD_DOC", "cardDoc", LangTransMeta.translate("CARD_DOC")));
}
  public static TableModel convertToTableModel(List cardLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;

    Vector names = new Vector();

    Vector values = new Vector();

//    names.add(LangTransMeta.translate(ZcElementConstants.FIELD_TRANS_ZC_CO_NAME));
//    names.add(LangTransMeta.translate(ZcElementConstants.FIELD_TRANS_ZC_HT_CODE));
//    names.add(LangTransMeta.translate(ZcElementConstants.FIELD_TRANS_ZC_HT_NAME));
    names.add(LangTransMeta.translate("FA_NAME"));
    names.add(LangTransMeta.translate("FATYPE_NAME"));
    names.add("原值");
    names.add("数量");
    names.add(LangTransMeta.translate("FA_PURCHASETIME"));
    names.add(LangTransMeta.translate("FA_CARBRAND"));
    names.add("使用部门");
    names.add("使用人");
    names.add(LangTransMeta.translate("FA_PRODUCTSPEC"));
//    names.add(LangTransMeta.translate("FA_CARNO"));
    names.add(LangTransMeta.translate("FA_ITEM_C04"));

    if (cardLst != null && cardLst.size() > 0) {

      for (int i = 0; i < cardLst.size(); i++) {

        Vector rowData = new Vector();

        ZcFaCard qx = (ZcFaCard) cardLst.get(i);

//        rowData.add(qx.getCoName());
//        rowData.add(qx.getCaigouHt().getZcHtCode());
//        rowData.add(qx.getCaigouHt().getZcHtName());
        rowData.add(qx.getFaName());
        rowData.add(qx.getFatypeName());
        rowData.add(qx.getCost());
        rowData.add(qx.getQty());
        rowData.add(qx.getFaPurchasetime());
        rowData.add(qx.getFaCarbrand());
        rowData.add(qx.getUseOrgName());
        rowData.add(qx.getUseEmpName());
        rowData.add(qx.getFaProductspec());
//        rowData.add(qx.getFaCarno());
        rowData.add(qx.getFaItemC04());

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

    tableModel.setList(cardLst);

    return tableModel;
  }

  public TableModel convertCardSubToTableData(List zcFaCardSubList) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<ZcFaCardSub> tm = new BeanTableModel<ZcFaCardSub>() {

      private static final long serialVersionUID = 6889363838628062064L;

      @Override
      public boolean isCellEditable(int row, int column) {

        String columnId = this.getColumnIdentifier(column);

        if ("ZC_FUND_FILE_BLOBID".equals(columnId) || ZcElementConstants.FIELD_TRANS_ZC_BI_DO_SUM.equals(columnId)
          || ZcElementConstants.FIELD_TRANS_ZC_BI_SUM.equals(columnId)||ZcElementConstants.FIELD_TRANS_FUND_NAME.equals(columnId) || ZcElementConstants.FIELD_TRANS_ORIGIN_NAME.equals(columnId) 
          || ZcElementConstants.FIELD_TRANS_PAYTYPE_NAME.equals(columnId)|| "ZC_SENDDOC_NAME".equals(columnId)|| ZcElementConstants.FIELD_TRANS_PROJECT_NAME.equals(columnId)
          ||ZcElementConstants.FIELD_TRANS_B_ACC_NAME.equals(columnId)) {
          return false;
        }
        return super.isCellEditable(row, column);
      }

      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
      }

    };

    for (int i=0;i<zcFaCardSubList.size();i++) {
      ZcFaCardSub bi=(ZcFaCardSub)zcFaCardSubList.get(i);
      bi.setTempId(Guid.genID());
    }

   tm.setOidFieldName("tempId");

    tm.setDataBean(zcFaCardSubList, cardSubInfo);

    return tm;
  }

  public TableModel convertCardDocToTableData(List zcFaCardDocList) {
    
    BeanTableModel<ZcFaCardDoc> tm = new BeanTableModel<ZcFaCardDoc>() {

      private static final long serialVersionUID = 6888363833328062064L;

      @Override
      public boolean isCellEditable(int row, int column) {

        String columnId = this.getColumnIdentifier(column);

        if ("ZC_FUND_FILE_BLOBID".equals(columnId) || ZcElementConstants.FIELD_TRANS_ZC_BI_DO_SUM.equals(columnId)
          || ZcElementConstants.FIELD_TRANS_ZC_BI_SUM.equals(columnId)||ZcElementConstants.FIELD_TRANS_FUND_NAME.equals(columnId) || ZcElementConstants.FIELD_TRANS_ORIGIN_NAME.equals(columnId) 
          || ZcElementConstants.FIELD_TRANS_PAYTYPE_NAME.equals(columnId)|| "ZC_SENDDOC_NAME".equals(columnId)|| ZcElementConstants.FIELD_TRANS_PROJECT_NAME.equals(columnId)
          ||ZcElementConstants.FIELD_TRANS_B_ACC_NAME.equals(columnId)) {
          return false;
        }

        return super.isCellEditable(row, column);

      }

      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        ZcFaCardDoc bean = dataBeanList.get(rowIndex);

        if ("CARD_DOC".equals(this.getColumnIdentifier(columnIndex))) {

          if (aValue == null) {

            this.getBean(rowIndex).setCardDoc(null);

            this.getBean(rowIndex).setCardDocBlobid(null);

          } else {

            this.getBean(rowIndex).setCardDoc(((AsFile) aValue).getFileName());

            this.getBean(rowIndex).setCardDocBlobid(((AsFile) aValue).getFileId());

          }

          fireTableCellUpdated(rowIndex, columnIndex);

          putEditedData(bean);

        } else {

          super.setValueAt(aValue, rowIndex, columnIndex);

        }
      }
    };


    for (int i=0;i<zcFaCardDocList.size();i++) {
      ZcFaCardDoc bi=(ZcFaCardDoc)zcFaCardDocList.get(i);
      bi.setTempId(Guid.genID());
    }
    tm.setOidFieldName("tempId");

    tm.setDataBean(zcFaCardDocList, cardDocInfo);

    return tm;
  }

  public static List<ColumnBeanPropertyPair> getSubInfo() {
    // TCJLODO Auto-generated method stub
    return cardSubInfo;
  }

  public static List<ColumnBeanPropertyPair> getDocInfo() {
    // TCJLODO Auto-generated method stub
    return cardDocInfo;
  }

}
