package com.ufgov.zc.client.ftp.apache.panel.queue;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.ufgov.zc.client.ftp.apache.panel.QueueTableCellRanderer;
import com.ufgov.zc.client.ftp.apache.utils.ProgressArg;

public class DownloadPanel extends JPanel {
	private JTable downloadTable = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultTableModel model;

	public DownloadPanel() {
		CardLayout cardLayout = new CardLayout();
		setLayout(cardLayout);
		ProgressArg progressArg = new ProgressArg(0, 0, 0);
		model = new DefaultTableModel(new Object[][] { new Object[] { "", "",
				"", progressArg } }, new String[] { "文件名", "大小",  "操作", "状态" });
		downloadTable.setModel(model);
		downloadTable.getTableHeader().setReorderingAllowed(false);
		downloadTable.setRowSelectionAllowed(false);
		TableColumn column = downloadTable.getColumn("状态");
		column.setCellRenderer(new QueueTableCellRanderer());
		scrollPane.setViewportView(downloadTable);
		cardLayout.layoutContainer(scrollPane);
		add(scrollPane, "queue");
	}

	public void addRow(final Object[] values) {
		Runnable runnable = new Runnable() {
			public void run() {
				model.insertRow(0, values);
			}
		};
		if (SwingUtilities.isEventDispatchThread())
			runnable.run();
		else
			SwingUtilities.invokeLater(runnable);
	}
}
