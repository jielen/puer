package com.ufgov.zc.client.component.ui.conditionitem;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.table.TableModel;

import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.event.ValueChangeEvent;
import com.ufgov.zc.client.component.event.ValueChangeListener;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityField;
import com.ufgov.zc.client.util.NumLimUtil;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.BeanUtil;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.model.ZcEbEntrust;

public class ZcEbEntrustSearchConditonItem extends AbstractSearchConditionItem  {
	
	private String compoId;
	
	private String fieldName = "sn";

	private ForeignEntityField projectSelectField;
	
	public ZcEbEntrustSearchConditonItem(String displayName){

	    init(displayName);
	    
	}
	
	public ZcEbEntrustSearchConditonItem(String displayName, String compoId) {

		this.compoId = compoId;
		
		init(displayName);
		
	}

	@Override
	protected JComponent createEditorComponent() {
		
		if (projectSelectField == null) {
			
			creatField();
			
			projectSelectField.addValueChangeListener(new ValueChangeListener() {
				
				public void valueChanged(ValueChangeEvent e) {
					
					ZcEbEntrust value = (ZcEbEntrust) projectSelectField.getValue();
					
					String code = null;
					
					if (value != null) {
						
						code = value.getSn();
						
					}
					
					fireSearch();
					
					fireValueChanged();
					
				}
				
			});
			
		}
		
		return projectSelectField;
	}

	@Override
	public void setValue(Object value) {
		
		projectSelectField.setValue(value);
		
	}

	@Override
	public Object getValue() {
		
	    return projectSelectField.getValue();
	    
	}
	
	public void setProject(ZcEbEntrust obj) {

	    if (projectSelectField != null) {

	      projectSelectField.setValue(obj);

	    }

	  }
	
	private void creatField() {

		String columNames[] = { "通知书编号","任务单编号", "项目编码", "项目名称", "单位代码", "单位名称" };
		
		ZcEbEntrustHandler objHandler = new ZcEbEntrustHandler(columNames);
		
		ElementConditionDto dto = new ElementConditionDto();
		
		// 这里要取业务年度
		
		dto.setNd(WorkEnv.getInstance().getTransNd());
		
		dto.setCoCode(WorkEnv.getInstance().getCurrCoCode());
		
		dto.setOrgCode(WorkEnv.getInstance().getOrgCode());
		
		dto.setNumLimitStr(NumLimUtil.getInstance().getNumLimCondByCoType(compoId, NumLimConstants.FWATCH, "SN"));
		
		projectSelectField = new ForeignEntityField("ZcEbEntrust.getZcEbEntrustCondition", dto, 50, objHandler, columNames, "采购项目");
		
	}
	
	public void putToElementConditionDto(ElementConditionDto element) {
		
		String v = "";
		
		if (projectSelectField.getValue() != null) {
			
			ZcEbEntrust proj = (ZcEbEntrust) projectSelectField.getValue();
			
			v = proj.getSn();
			
		}
		
		BeanUtil.set(fieldName, v.equals("") ? null : v, element);
		
	}
	
	private class ZcEbEntrustHandler implements IForeignEntityHandler {

	    private String columNames[];

	    public ZcEbEntrustHandler(String columNames[]) {

	      this.columNames = columNames;

	    }

	    public void excute(List selectedDatas) {

	      // TCJLODO Auto-generated method stub

	      for (Object object : selectedDatas) {

	        ZcEbEntrust obj = (ZcEbEntrust) object;

	        ZcEbEntrustSearchConditonItem.this.projectSelectField.setValue(obj);

	        ZcEbEntrustSearchConditonItem.this.projectSelectField.setText(obj.getZcMakeName());

	        fireSearch();

	        fireValueChanged();

	      }

	    }

	    @Override
	    public TableModel createTableModel(List showDatas) {

	      Object data[][] = new Object[showDatas.size()][columNames.length];

	      for (int i = 0; i < showDatas.size(); i++) {

	        ZcEbEntrust rowData = (ZcEbEntrust) showDatas.get(i);

	        int col = 0;

	        data[i][col++] = rowData.getSn();

	        data[i][col++] = rowData.getSnCode();

	        data[i][col++] = rowData.getZcMakeCode();

	        data[i][col++] = rowData.getZcMakeName();

	        data[i][col++] = rowData.getCoCode();

	        data[i][col++] = rowData.getCoName();

	      }

	      MyTableModel model = new MyTableModel(data, columNames) {

	        @Override
	        public boolean isCellEditable(int row, int colum) {

	          return false;

	        }

	      };

	      return model;

	    }

	    @Override
	    public boolean isMultipleSelect() {

	      // TCJLODO Auto-generated method stub

	      return false;

	    }

	  }

}
