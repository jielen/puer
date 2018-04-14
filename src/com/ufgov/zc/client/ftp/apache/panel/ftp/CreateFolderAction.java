package com.ufgov.zc.client.ftp.apache.panel.ftp;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.ufgov.zc.client.ftp.apache.utils.FtpUtils;

/**
 * 创建文件夹按钮的动作处理器
 */
class CreateFolderAction extends AbstractAction {
  private static Logger logger = Logger.getLogger(CreateFolderAction.class);

  private FtpPanel ftpPanel;

  /**
   * 构造方法
   * @param ftpPanel - FTP资源管理面板
   * @param name - 动作名称
   * @param icon - 动作图标
   */
  public CreateFolderAction(FtpPanel ftpPanel, String name, Icon icon) {
    super(name, icon); // 调用父类构造方法
    this.ftpPanel = ftpPanel; // 赋值FTP资源管理面板的引用
  }

  /**
   * 创建文件夹的事件处理方法
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    // 接收用户输入的新建文件夹的名称
    String folderName = JOptionPane.showInputDialog(ftpPanel, "请输入文件夹名称：");
    if (folderName == null || folderName.trim().equals(".")|| folderName.trim().equals("..")) return;
    boolean read = true;
    try {
      // 发送创建文件夹的命令
      FtpUtils fu = new FtpUtils();
      FtpUtils.UploadStatus rtnStatus = fu.createDir(folderName, ftpPanel.getFtpClient());
      read = FtpUtils.UploadStatus.Create_Directory_Success.equals(rtnStatus); 
      
    } catch (IOException ex) {
      logger.error("重命名失败：" + ex.getMessage(), ex);
      JOptionPane.showMessageDialog(ftpPanel, folderName + "文件夹无法被创建。", "创建文件夹", JOptionPane.ERROR_MESSAGE);
    }
    if (read) {
       this.ftpPanel.refreshCurrentFolder();
    }
  }
}