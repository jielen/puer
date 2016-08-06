/**
 * 
 */
package com.ufgov.zc.client.component.table.cellrenderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.beanutils.BeanUtils;

import com.ufgov.zc.client.component.FileUploader;
import com.ufgov.zc.common.system.util.BeanUtil;

/**
 * @author Administrator
 *
 */
public class FileCellRenderer extends FileUploader implements TableCellRenderer {

	public FileCellRenderer(){
		
	}
	/**
	 * 未完成
	 */
	private static final long serialVersionUID = -2523276605645479082L;

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
//		BeanUtil.set(editor.getFieldName(), value, getEditObject());
//		BeanUtils.setProperty(bean, name, value)
		return null;
	}

}
