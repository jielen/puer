package com.ufgov.zc.client.common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public abstract class BasicTableModel extends AbstractTableModel {
	// 显示用数据
	protected List recordList = new ArrayList();

	// 新增用数据
	protected List insertList = new ArrayList();

	// 删除用数据
	protected List deleteList = new ArrayList();

	// 修改用数据
	protected List updateList = new ArrayList();

	public BasicTableModel() {
		super();
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		if (recordList == null) {
			recordList = new ArrayList();
		}
		this.recordList = recordList;
		this.insertList.clear();
		this.deleteList.clear();
		this.updateList.clear();
		this.fireTableDataChanged();
	}

	public List getInsertList() {
		return insertList;
	}

	public void setInsertList(List insertList) {
		this.insertList = insertList;
	}

	public List getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List deleteList) {
		this.deleteList = deleteList;
	}

	public List getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List updateList) {
		this.updateList = updateList;
	}

	public int getRowCount() {
		if (recordList == null)
			return 0;
		return recordList.size();
	}

	public Object getRecord(int index) {
		return recordList.get(index);
	}
	
	public void insertRecord(Object record) {
		//if (!insertList.contains(record)) {
			recordList.add(record);
			insertList.add(record);
			this.fireTableDataChanged();
		//}
	}

	public void insertRecord(int index, Object record) {
//		if (!insertList.contains(record)) {
			recordList.add(index, record);
			insertList.add(record);
			this.fireTableDataChanged();
//		}
	}

	public void appendRecords(List records) {
		for (int i = 0; i < records.size(); i++) {
//			if (!insertList.contains(records.get(i))) {
				recordList.add(records.get(i));
				insertList.add(records.get(i));
//			}
		}
		this.fireTableDataChanged();
	}

	public void deleteRecord(Object record) {
		int rowIndex = recordList.indexOf(record);
		if (rowIndex >= 0) {
			recordList.remove(record);
			insertList.remove(record);
			updateList.remove(record);
			deleteList.add(record);
			this.fireTableDataChanged();
		}
	}

	public void deleteRecords(List records) {
		Object record = null;
		for (int i = 0; i < records.size(); i++) {
			record = records.get(i);
			int rowIndex = recordList.indexOf(record);
			if (rowIndex >= 0) {
				recordList.remove(record);
				insertList.remove(record);
				updateList.remove(record);
				deleteList.add(record);
			}
		}
		this.fireTableDataChanged();
	}
	
	public void clearRecords() {
	  this.recordList.clear();
    this.insertList.clear();
    this.deleteList.clear();
    this.updateList.clear();
    this.fireTableDataChanged();
	}

	public void updateRecord(Object record) {
		int rowIndex = recordList.indexOf(record);
		if (rowIndex >= 0) {
			if (!updateList.contains(record) && !insertList.contains(record))
				updateList.add(record);
			this.fireTableDataChanged();
		}
	}

	/**
	 * 清除所有的model listener
	 * 
	 */
	public void removeAllTableModelListener() {
		TableModelListener[] listeners = this.getTableModelListeners();
		for (int i = 0; i < listeners.length; i++) {
			this.removeTableModelListener(listeners[i]);
		}
	}

	public boolean isChanged() {
		return insertList.size() > 0 || deleteList.size() > 0
				|| updateList.size() > 0;
	}
	
	public abstract void insertRecord();
	
	public abstract void insertRecord(int index);

}
