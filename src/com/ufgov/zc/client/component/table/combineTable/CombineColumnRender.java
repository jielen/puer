package com.ufgov.zc.client.component.table.combineTable;

/**
* @ClassName: CombineColumnRender
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: Jan 30, 2013 9:16:18 AM
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/** 
 * 设置需要合并的列的单元格不能被选中，不能聚焦 
 * @author hualun-alan 
 */
public class CombineColumnRender extends JTextArea implements TableCellRenderer {

  public CombineColumnRender() {
    setLineWrap(true);
    setWrapStyleWord(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    // 计算当下行的最佳高度 
    CombineTable cTable = (CombineTable) table;
    if (cTable.combineData.combineColumns.contains(column)) {
      hasFocus = false;
    }
    int maxPreferredHeight = 0;
    for (int i = 0; i < table.getColumnCount(); i++) {
      setText("" + table.getValueAt(row, i));
      setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
      maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);
    }

    if (table.getRowHeight(row) != maxPreferredHeight) // 少了这行则处理器瞎忙 
      table.setRowHeight(row, maxPreferredHeight);

    setText(value == null ? "" : value.toString());
    return this;
  }

}
