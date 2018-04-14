package com.ufgov.zc.client.ftp.apache.panel.queue;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import com.ufgov.zc.client.ftp.apache.panel.QueueTableCellRanderer;
import com.ufgov.zc.client.ftp.apache.utils.ProgressArg;

public class UploadPanel extends JPanel {
  private static Logger logger=Logger.getLogger(UploadPanel.class);
	private JTable uploadTable = new JTable(); // 表格组件
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultTableModel model; // 表格的数据模型

	/**
	 * 构造方法，用于初始化程序界面
	 */
	public UploadPanel() {
		CardLayout cardLayout = new CardLayout();
		setLayout(cardLayout);
		ProgressArg progressArg = new ProgressArg(0, 0, 0);
		model = new DefaultTableModel(new Object[][] { new Object[] { "", "","", progressArg } }, new String[] { "文件名", "大小",  "操作", "状态" });
		uploadTable.setModel(model);
		uploadTable.getTableHeader().setReorderingAllowed(false);
		uploadTable.setRowSelectionAllowed(false);
		TableColumn column = uploadTable.getColumn("状态");
		column.setCellRenderer(new QueueTableCellRanderer());
		scrollPane.setViewportView(uploadTable);
		cardLayout.layoutContainer(scrollPane);
		add(scrollPane, "queue");

   /* column=uploadTable.getColumn("文件名");
     TableCellRenderer cr=column.getCellRenderer();
     logger.debug(cr.toString());*/
	}

	/**
	 * 向上传队列的表格组件添加新任务的方法
	 * 
	 * @param values
	 *            - 添加到表格的一行数据的数组对象
	 */
	public void addRow(final Object[] values) {
		Runnable runnable = new Runnable() {
			public void run() {
				model.insertRow(0, values); // 向表格的数据模型添加数据
			}
		};
		if (SwingUtilities.isEventDispatchThread())
			runnable.run(); // 在事件队列执行
		else
			SwingUtilities.invokeLater(runnable); // 或有事件队列调用
	}
}
