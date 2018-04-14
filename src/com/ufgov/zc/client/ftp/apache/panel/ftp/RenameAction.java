package com.ufgov.zc.client.ftp.apache.panel.ftp;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.ufgov.zc.client.ftp.apache.utils.FtpUtils;
import com.ufgov.zc.client.ftp.apache.utils.MyFtpFile;

/**
 * 重命名按钮的动作处理器
 */
class RenameAction extends AbstractAction {
	private FtpPanel ftpPanel;

	/**
	 * 构造方法
	 * 
	 * @param ftpPanel
	 *            - FTP资源管理面板
	 * @param name
	 *            - 动作的名称
	 * @param icon
	 *            - 动作的图标
	 */
	public RenameAction(FtpPanel ftpPanel, String name, Icon icon) {
		super(name, icon); // 调用父类的构造方法
		this.ftpPanel = ftpPanel; // 赋值FTP资源管理面板的引用
	}

	/**
	 * 重命名FTP文件的事件处理方法
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取显示FTP资源的表格当前选择行号
		int selRow = ftpPanel.ftpDiskTable.getSelectedRow();
		if (selRow < 0)
			return;
		// 获取当前行的第一个表格单元值，并转换成FtpFile类型的对象
		MyFtpFile file = (MyFtpFile) ftpPanel.ftpDiskTable.getValueAt(selRow, 0);
    if (file.getName().equals(".") || file.getName().equals(".."))
    return;
		// 使用对话框接收用户输入的新文件或文件夹名称
		String newName = JOptionPane.showInputDialog(ftpPanel, "请输入新名称。",file.getName()); 
		if(newName == null || newName.trim().length()==0){
		  return;
		}
		try {
		  FtpUtils ftpUtils=new FtpUtils();
		  ftpUtils.rename(ftpPanel.getFtpClient(), file.getName(), newName); 
			ftpPanel.refreshCurrentFolder(); // 刷新当前文件夹
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}