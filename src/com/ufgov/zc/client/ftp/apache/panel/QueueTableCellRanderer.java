package com.ufgov.zc.client.ftp.apache.panel;

import java.awt.Component;

import com.ufgov.zc.client.ftp.apache.utils.ProgressArg;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
/**
 *  渲染上传下载队列表格组件的渲染器   有显示进度条的功能
 */
public class QueueTableCellRanderer extends JProgressBar implements
		TableCellRenderer {
	
	public QueueTableCellRanderer() {
		setStringPainted(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof ProgressArg) {
			ProgressArg arg = (ProgressArg) value; 
			setMinimum(arg.getPerMin());
			setMaximum(arg.getPerMax());
			setValue(arg.getPer());
//			setString(arg.getSpeed());
			table.setRowSelectionInterval(row, row);
			table.setColumnSelectionInterval(column, column);
		}
		if (getValue() < getMaximum())
			return this;
		else {
			if (getMaximum() <= 0){
				return new DefaultTableCellRenderer();
			}
			TableCellRenderer renderer=new DefaultTableCellRenderer();
			return renderer.getTableCellRendererComponent(table, "完成", isSelected, hasFocus, row, column); 
//			return new JLabel("完成");
		}
	}
}
