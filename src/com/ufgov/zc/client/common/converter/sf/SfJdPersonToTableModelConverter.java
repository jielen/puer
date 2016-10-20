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
import com.ufgov.zc.common.sf.model.SfCertificate;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdPersonMajor;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.system.util.BeanUtil;

public class SfJdPersonToTableModelConverter {
  public static TableModel convertMainLst(List mainLst) {
    // TCJLODO Auto-generated method stub

    MyTableModel tableModel = null;
    Vector names = new Vector();
    Vector values = new Vector();

    names.add(LangTransMeta.translate(SfJdPerson.COL_NAME));
    names.add(LangTransMeta.translate(SfJdPerson.COL_SEX));
    names.add(LangTransMeta.translate(SfJdPerson.COL_BIRTHDAY));
    names.add(LangTransMeta.translate(SfJdPerson.COL_TECH_TITLE));
    names.add(LangTransMeta.translate(SfJdPerson.COL_CERTIFICATE_NO));
    names.add(LangTransMeta.translate(SfJdPerson.COL_STATUS));
    if (mainLst != null && mainLst.size() > 0) {

      SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
      
      for (int i = 0; i < mainLst.size(); i++) {
        Vector rowData = new Vector();
        SfJdPerson jdPerson = (SfJdPerson) mainLst.get(i);
        rowData.add(jdPerson.getName());
        rowData.add(AsValDataCache.getName(SfElementConstants.VS_SEX, jdPerson.getSex()));
        rowData.add(jdPerson.getBirthday()==null?null:df.format(jdPerson.getBirthday()));
        rowData.add(jdPerson.getTechTitle());
        rowData.add(jdPerson.getCertificateNo());
        rowData.add(AsValDataCache.getName(SfJdPerson.SF_VS_JD_PERSON_STATUS, jdPerson.getStatus()));
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

  public static TableModel convertMajorsTableData(List itemList) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<SfJdPersonMajor> tm = new BeanTableModel<SfJdPersonMajor>() {
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

        SfJdPersonMajor bean = dataBeanList.get(rowIndex);

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

    tm.setOidFieldName("major.majorCode");
    tm.setDataBean(itemList, detailInfo);
    return tm;
  }
  private static List<ColumnBeanPropertyPair> detailInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {

    detailInfo.add(new ColumnBeanPropertyPair(SfMajor.COL_MAJOR_NAME, "major.majorName", LangTransMeta.translate(SfMajor.COL_MAJOR_NAME)));
  }
  public static List<ColumnBeanPropertyPair> getDetailInfo(){
    return detailInfo;
  }
  

  public static TableModel convertCertTableData(List itemList) {
    // TCJLODO Auto-generated method stub

    BeanTableModel<SfCertificate> tm = new BeanTableModel<SfCertificate>() {
      /**
       * 
       */ 
      
      @Override
      public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
      }
      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    	  SfCertificate bean = dataBeanList.get(rowIndex);

        String currentColName = this.getColumnIdentifier(columnIndex);

        if (aValue instanceof BaseElement) {

          BeanUtil.set(columnBeanPropertyPairList.get(columnIndex).getBeanPropertyName(), ((BaseElement) aValue).getName(), bean);

          fireTableCellUpdated(rowIndex, columnIndex);

          putEditedData(dataBeanList.get(rowIndex));

        } else  if (SfCertificate.COL_ZF_FILE.equals(this.getColumnIdentifier(columnIndex))) {

            if (aValue == null) {

              this.getBean(rowIndex).setZfFile(null);

              this.getBean(rowIndex).setZfFileBlobid(null);

            } else {

              this.getBean(rowIndex).setZfFile(((AsFile) aValue).getFileName());

              this.getBean(rowIndex).setZfFileBlobid(((AsFile) aValue).getFileId());

            }

            fireTableCellUpdated(rowIndex, columnIndex);

            putEditedData(dataBeanList.get(rowIndex));
        }else {
          super.setValueAt(aValue, rowIndex, columnIndex);
        }
      }
    };

    tm.setOidFieldName("tempId");
    tm.setDataBean(itemList, certInfo);
    return tm;
  }

  private static List<ColumnBeanPropertyPair> certInfo = new ArrayList<ColumnBeanPropertyPair>();

  static {

	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_OWNER_TYPE, "zfOwnerType", LangTransMeta.translate(SfCertificate.COL_ZF_OWNER_TYPE)));
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_NAME, "name", LangTransMeta.translate(SfCertificate.COL_NAME)));
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_CODE, "zfCode", LangTransMeta.translate(SfCertificate.COL_ZF_CODE)));
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_AGENCY, "zfAgency", LangTransMeta.translate(SfCertificate.COL_ZF_AGENCY)));
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_DESC, "zfDesc", LangTransMeta.translate(SfCertificate.COL_ZF_DESC)));
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_FILE, "zfFile", LangTransMeta.translate(SfCertificate.COL_ZF_FILE)));
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_BEGIN_DATE, "zfBeginDate", LangTransMeta.translate(SfCertificate.COL_ZF_BEGIN_DATE)));
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_END_DATE, "zfEndDate", LangTransMeta.translate(SfCertificate.COL_ZF_END_DATE))); 
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_IS_NOTICE_EXPIRE, "isNoticeExpire", LangTransMeta.translate(SfCertificate.COL_IS_NOTICE_EXPIRE)));
	  certInfo.add(new ColumnBeanPropertyPair(SfCertificate.COL_ZF_NOTICE_DAYS, "zfNoticeDays", LangTransMeta.translate(SfCertificate.COL_ZF_NOTICE_DAYS)));

 }
  public static List<ColumnBeanPropertyPair> getCertInfo(){
    return certInfo;
  }
}

