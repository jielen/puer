package com.ufgov.zc.client.component.ui.conditionitem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.ufgov.zc.client.component.ZcCommonComboBox;

public class ZcCommonComboBoxConditionItem extends AbstractSearchConditionItem {

	private List filterList = null;
	
	private String valsetId = null;
	
	private List dataList = null;
	
	private boolean isSQL = false;
	
	private String sqlId = null;

	private ZcCommonComboBox zcCommonComboBox;

	public ZcCommonComboBoxConditionItem(String displayName) {

		init(displayName);

	}
	
	public ZcCommonComboBoxConditionItem(String displayName, String valsetId, List filterList) {
		
		this.valsetId = valsetId;
		
		this.filterList = filterList;

		init(displayName);

	}
	
	public ZcCommonComboBoxConditionItem(String displayName, List dataList, List filterList) {
		
		this.dataList = dataList;
		
		this.filterList = filterList;

		init(displayName);

	}
	
	public ZcCommonComboBoxConditionItem(String displayName, String sqlId, List filterList, boolean isSQL) {
		
		this.sqlId = sqlId;
		
		this.filterList = filterList;
		
		this.isSQL = isSQL;

		init(displayName);

	}

	public ZcCommonComboBoxConditionItem(String displayName, Object defaultValue) {

		if (defaultValue != null) {

			if (defaultValue instanceof ArrayList) {

				ArrayList filterList = (ArrayList) defaultValue;

				this.filterList = filterList;

			}

		}

		init(displayName);

	}

	protected JComponent createEditorComponent() {

		if (zcCommonComboBox == null) {
			
			if(isSQL){
				
				zcCommonComboBox = new ZcCommonComboBox(sqlId, filterList, isSQL);
				
			} else if (valsetId != null) {

				zcCommonComboBox = new ZcCommonComboBox(valsetId, filterList);

			} else if(dataList != null){

				zcCommonComboBox = new ZcCommonComboBox(dataList, filterList);
				
			} else {

				zcCommonComboBox = new ZcCommonComboBox();

			}

			zcCommonComboBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					fireSearch();

					fireValueChanged();

				}

			});

		}

		return zcCommonComboBox;

	}

	public void setValue(Object value) {

		zcCommonComboBox.setSelectedItem(value);

	}

	@Override
	public Object getValue() {

		return zcCommonComboBox.getSelectedItem();

	}
}
