package com.ufgov.zc.client.ftp.apache.panel.ftp;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.ufgov.zc.client.ftp.apache.panel.local.LocalPanel;
import com.ufgov.zc.client.ftp.apache.utils.FtpUtils;
import com.ufgov.zc.client.ftp.apache.utils.MyFtpFile;

/**
 * FTP面板的删除按钮的动作处理器
 */
class DelFileAction extends AbstractAction {
  private static Logger logger = Logger.getLogger(DelFileAction.class);

  private FtpPanel ftpPanel;

  /**
   * 删除动作处理器的构造方法
   * @param ftpPanel - FTP资源管理面板
   * @param name - 动作名称
   * @param icon - 图标
   */
  public DelFileAction(FtpPanel ftpPanel, String name, Icon icon) {
    super(name, icon);
    this.ftpPanel = ftpPanel;
  }

  private void delFile(MyFtpFile myFile) throws IOException {
    FtpUtils fUtils = new FtpUtils();
    if (myFile.isDirectory()) {
      fUtils.removeDirectory(ftpPanel.getFtpClient(), myFile.getName());
    } else {
      fUtils.removeFile(ftpPanel.getFtpClient(), myFile.getName());
    }
  }

  public void actionPerformed(ActionEvent event) {
    // 获取显示FTP资源列表的表格组件当前选择的所有行
    final int[] selRows = ftpPanel.ftpDiskTable.getSelectedRows();
    if (selRows.length < 1) return;
    int confirmDialog = JOptionPane.showConfirmDialog(ftpPanel, "确定要删除选中文件吗？");
    if (confirmDialog == JOptionPane.YES_OPTION) {

      // 遍历显示FTP资源的表格的所有选择行
      for (int i = 0; i < selRows.length; i++) {
        // 获取每行的第一个单元值，并转换为FtpFile类型
        final MyFtpFile file = (MyFtpFile) ftpPanel.ftpDiskTable.getValueAt(selRows[i], 0);
        if (file != null && !(file.getName().equals(".") || file.getName().equals(".."))) {
          try {
            delFile(file);
            // 刷新FTP服务器资源列表
            DelFileAction.this.ftpPanel.refreshCurrentFolder();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(file.getName()+"删除失败：" + e.getMessage(), e);
            JOptionPane.showMessageDialog(ftpPanel, file.getName() + "无法删除。", "删除", JOptionPane.ERROR_MESSAGE);
          } // 调用删除文件的递归方法 
        }
      }

    }
  }
}