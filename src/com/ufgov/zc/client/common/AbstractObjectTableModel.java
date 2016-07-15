package com.ufgov.zc.client.common;

import java.util.List;

import javax.swing.SwingWorker;

public abstract class AbstractObjectTableModel extends BasicTableModel {

  private static final long serialVersionUID = -943987009570836482L;

  protected String[] columnNames;

  protected String[] propNames;

  protected ObjectPropSelector propSelector = new ObjectPropSelector.DefaultPropSelector();

  private boolean selectSignal = true;

  private boolean editSignal = false;

  public boolean isSelectSignal() {
    return selectSignal;
  }

  public void setSelectSignal(boolean selectSignal) {
    this.selectSignal = selectSignal;
  }

  public boolean isUpdateSignal() {
    return editSignal;
  }

  public void setUpdateSignal(boolean updateSignal) {
    this.editSignal = updateSignal;
  }

  public boolean isEditSignal() {
    return editSignal;
  }

  public void setEditSignal(boolean editSignal) {
    this.editSignal = editSignal;
  }

  public AbstractObjectTableModel() {

  }

  public AbstractObjectTableModel(String[] columnNames, String[] propNames) {
    this.columnNames = columnNames;
    this.propNames = propNames;
  }

  public AbstractObjectTableModel(String[] columnNames, String[] propNames, ObjectPropSelector selector) {
    this(columnNames, propNames);
    this.propSelector = selector;
  }

  public int getColumnCount() {
    if (columnNames == null)
      return 0;
    return columnNames.length;
  }

  public String getColumnName(int column) {
    return columnNames[column];
  }

  public String getPropName(int column) {
    return propNames[column];
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    if (this.getRecordList() == null || this.getRecordList().size() == 0) {
      return "";
    } else {
      Object record = this.getRecordList().get(rowIndex);
      return propSelector.select(record, columnIndex, propNames);
    }
  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    if (this.getRecordList() == null)
      return;
    Object record = this.getRecordList().get(rowIndex);
    Object oldValue = propSelector.select(record, columnIndex, propNames);

    if (oldValue == null || !oldValue.equals(aValue)) {
      propSelector.update(record, aValue, columnIndex, propNames);
      if (editSignal && !this.getUpdateList().contains(record) && !this.getInsertList().contains(record)) {
        this.getUpdateList().add(this.getRecordList().get(rowIndex));
      }
    }
    fireTableCellUpdated(rowIndex, columnIndex);
  }

  public Class getColumnClass(int column) {
    if ((column >= 0) && (column < getColumnCount()) && this.getRowCount() > 0) {
      Object record = this.getRecordList().get(0);
      Object v = propSelector.select(record, column, propNames);
      if (v != null) {
        return v.getClass();
      }
    }
    return Object.class;
  }

  public boolean isCellEditable(int rowIndex, int columnIndex) {
    if (editSignal)
      return true;
    return false;
  }

  public void refresh() {
    if (selectSignal) {
      SwingWorker worker = new SwingWorker() {
        public Object doInBackground() throws Exception {
          return queryData();
        }

        public void done() {
          try {
            List records = (List) this.get();
            setRecordList(records);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      };
      worker.execute();

    }
  }

  public abstract void save() throws Exception;

  public abstract List queryData() throws Exception;

}
