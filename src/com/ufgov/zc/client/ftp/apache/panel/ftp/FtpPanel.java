
package com.ufgov.zc.client.ftp.apache.panel.ftp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level; 

import javax.swing.ActionMap;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import sun.net.TelnetInputStream;

import com.ufgov.zc.client.ftp.apache.FTPClientPanel;
import com.ufgov.zc.client.ftp.apache.panel.FTPTableCellRanderer;
import com.ufgov.zc.client.ftp.apache.utils.FtpConfig;
import com.ufgov.zc.client.ftp.apache.utils.FtpUtils;
import com.ufgov.zc.client.ftp.apache.utils.MyFtpFile;

public class FtpPanel extends javax.swing.JPanel {

  private static Logger logger=Logger.getLogger(FtpPanel.class);
 
	private FTPClient ftpClient;
	FtpUtils ftpUtils=new FtpUtils();
	private javax.swing.JButton createFolderButton;
	private javax.swing.JButton delButton;
	private javax.swing.JButton downButton;
	javax.swing.JTable ftpDiskTable;
	private javax.swing.JLabel ftpSelFilePathLabel;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JToolBar toolBar;
	private javax.swing.JButton refreshButton;
	private javax.swing.JButton renameButton;
	FTPClientPanel frame = null;
	Queue<Object[]> queue = new LinkedList<Object[]>();
	FtpUtils fUtils=new FtpUtils();
	
	private DownThread thread;

	public FtpPanel() {
		initComponents();
	}

	public FtpPanel(FTPClientPanel client_Frame) {
		frame = client_Frame;
		initComponents();
	}

	public FTPClient getFtpClient(){
	  return ftpClient;
	}
	 
	private void initComponents() {
		ActionMap actionMap = getActionMap();
		actionMap.put("createFolderAction", new CreateFolderAction(this,
				"创建文件夹", null));
		actionMap.put("delAction", new DelFileAction(this, "删除", null));
		actionMap.put("refreshAction", new RefreshAction(this, "刷新", null));
		actionMap.put("renameAction", new RenameAction(this, "重命名", null));
		actionMap.put("downAction", new DownAction(this, "下载", null));

		java.awt.GridBagConstraints gridBagConstraints;

		toolBar = new javax.swing.JToolBar();
		delButton = new javax.swing.JButton();
		renameButton = new javax.swing.JButton();
		createFolderButton = new javax.swing.JButton();
		downButton = new javax.swing.JButton();
		refreshButton = new javax.swing.JButton();
		scrollPane = new JScrollPane();
		ftpDiskTable = new JTable();
		ftpDiskTable.setDragEnabled(true);
		ftpSelFilePathLabel = new javax.swing.JLabel();

		setBorder(javax.swing.BorderFactory.createTitledBorder(null, "远程",
				javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.ABOVE_TOP));
		setLayout(new java.awt.GridBagLayout());

		toolBar.setRollover(true);
		toolBar.setFloatable(false);

		delButton.setText("删除");
		delButton.setFocusable(false);
		delButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		delButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		delButton.setAction(actionMap.get("delAction"));
		toolBar.add(delButton);

		renameButton.setText("重命名");
		renameButton.setFocusable(false);
		renameButton.setAction(actionMap.get("renameAction"));
		toolBar.add(renameButton);

		createFolderButton.setText("新文件夹");
		createFolderButton.setFocusable(false);
		createFolderButton.setAction(actionMap.get("createFolderAction"));
		toolBar.add(createFolderButton);

		downButton.setText("下载");
		downButton.setFocusable(false);
		downButton.setAction(actionMap.get("downAction"));
		toolBar.add(downButton);

		refreshButton.setText("刷新");
		refreshButton.setFocusable(false);
		refreshButton.setAction(actionMap.get("refreshAction"));
		toolBar.add(refreshButton);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		add(toolBar, gridBagConstraints);

		ftpDiskTable.setModel(new FtpTableModel());
		ftpDiskTable.setShowHorizontalLines(false);
		ftpDiskTable.setShowVerticalLines(false);
		ftpDiskTable.getTableHeader().setReorderingAllowed(false);
		ftpDiskTable.setDoubleBuffered(true);
		ftpDiskTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				ftpDiskTableMouseClicked(evt);
			}
		});
		scrollPane.setViewportView(ftpDiskTable);
		scrollPane.getViewport().setBackground(Color.WHITE);
		//设置渲染本地资源和FTP资源表格组件的渲染器
		ftpDiskTable.getColumnModel().getColumn(0).setCellRenderer(
				FTPTableCellRanderer.getCellRanderer());
		//RowSorter 的一个实现，它使用 TableModel 提供排序和过滤操作。
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				ftpDiskTable.getModel());
		TableStringConverter converter = new TableConverter();
		//设置负责将值从模型转换为字符串的对象。
		sorter.setStringConverter(converter);
		//设置 RowSorter。RowSorter 用于提供对 JTable 的排序和过滤。 
		ftpDiskTable.setRowSorter(sorter);
		/**
		 * 颠倒指定列的排序顺序。调用此方法时，由子类提供具体行为。
		 * 通常，如果指定列已经是主要排序列，则此方法将升序变为降序（或将降序变为升序）；
		 * 否则，使指定列成为主要排序列，并使用升序排序顺序。如果指定列不可排序，则此方法没有任何效果。 
		 */
		sorter.toggleSortOrder(0);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(scrollPane, gridBagConstraints);

		ftpSelFilePathLabel.setBorder(javax.swing.BorderFactory
				.createEtchedBorder());
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		add(ftpSelFilePathLabel, gridBagConstraints);
	}

	/**
	 * 表格单击或双击事件的处理方法。
	 */
	private void ftpDiskTableMouseClicked(java.awt.event.MouseEvent evt) {
		int selectedRow = ftpDiskTable.getSelectedRow();
		Object value = ftpDiskTable.getValueAt(selectedRow, 0);
		if (value instanceof MyFtpFile) {
			MyFtpFile selFile = (MyFtpFile) value;
//			ftpSelFilePathLabel.setText(selFile.getAbsolutePath());
			ftpSelFilePathLabel.setText(fUtils.subTailStr(frame.getFtpConfig().getRoot(), selFile.getAbsolutePath()));
			logger.debug(ftpSelFilePathLabel.getText());
			if (evt.getClickCount() >= 2) { //双击鼠标
				if (canGo(selFile)) { 
					listFtpFiles(selFile.getName());  
				}
			}
		}
	}

	private boolean canGo(MyFtpFile selFile) {
	  //selFile.isDirectory() && !isRoot()
	  if(selFile.isDirectory()){
	    if(isRoot() && (selFile.getName().equals("..")|| selFile.getName().equals("."))){
	      return false;
	    }else {
        return true;
      }
	  }
    return false;
  }

  /**
	 * 判断当前目录是否是ftpconfig中预设的根目录
	 * @return
	 */
	private boolean isRoot() { 
	  String root=getFtpConfig().getRoot();
	  if(root==null || root.equals("/")||root.equals(".")){
	    return false;
	  }
	  try {
      String path=ftpUtils.pwd(ftpClient);
      if(!path.startsWith("/")){
        path="/"+path;
      }
      if(!path.endsWith("/")){
        path+="/";
      }
      if(!root.startsWith("/")){
        root="/"+root;
      }
      if(!root.endsWith("/")){
        root+="/";
      }
      logger.debug("root:\t"+root+"\tpath\t"+path);
       return path.equals(root);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      logger.error(e.getMessage(), e);
    }
    return false;
  }

  /**
	 * 读取FTP文件到表格的方法
	 * @param remotePaht 
	 * @param list
	 *            读取FTP服务器资源列表的输入流
	 */
	public void listFtpFiles(final String remotePath) {
		 
		// 创建一个线程类
		Runnable runnable = new Runnable() {
			public void run() {
        try {
          ftpUtils.getRemoteListToModel(ftpClient,remotePath,(DefaultTableModel)ftpDiskTable.getModel());
          ftpDiskTable.clearSelection(); 
          /* String pathString=ftpSelFilePathLabel.getText();
          if(pathString==null || pathString.equals("/.")||pathString.equals("/..")){
            return;
          }
         if(pathString.endsWith("/..")){
            pathString=pathString.substring(0, pathString.length()-3);
            pathString=pathString.substring(0,pathString.lastIndexOf("/"));
            ftpSelFilePathLabel.setText(pathString);
          }*/
//          ftpDiskTable.revalidate();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          logger.error("获取远程目录"+remotePath+"异常!",e); 
          return;
        }
			}
		};
		/*if (SwingUtilities.isEventDispatchThread()) // 启动线程对象
			runnable.run();
		else*/
			SwingUtilities.invokeLater(runnable);
	}

	
	public void cutLink(){
	  setFtpClient(null);
	}
	/**
	 * 设置FTP连接，并启动下载队列线程的方法
	 */
	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
		if(ftpClient==null){
		  setBtnStatus(false);
		  stopDownThread();
		  return;
		}else{
      refreshCurrentFolder();
		  setBtnStatus(true);
		}
		// 以30秒为间隔与服务器保持通讯
		final Timer timer = new Timer(30000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try { 
					if (FtpPanel.this.ftpClient != null && FtpPanel.this.ftpClient.isConnected()) {
					  FtpPanel.this.ftpClient.noop();
					}
				} catch (IOException e1) {
					logger.error(e1.getMessage(), e1);
				}
			}
		});
		timer.start();
		startDownThread();
	}

	/**
	 * 刷新FTP资源管理面板的当前文件夹
	 */
	public void refreshCurrentFolder() { 
			listFtpFiles(null);  
	}

	/**
	 * 开始下载队列线程
	 */
	private void startDownThread() {
		if (thread != null)
			thread.stopThread();
		thread = new DownThread(this);
		thread.start();
	}

	/**
	 * 停止下载队列线程
	 */
	private void stopDownThread() {
		if (thread != null) {
			thread.stopThread(); 
		}
	}

	public String getPwd() throws IOException { 
		return ftpUtils.pwd(ftpClient); 
	}

	public Queue<Object[]> getQueue() {
		return queue;
	}

	/**
	 * 清除FTP资源表格内容的方法
	 */
	public void clearTable() {
		FtpTableModel model = (FtpTableModel) ftpDiskTable.getModel();
		model.setRowCount(0);
	}
	
	public FtpConfig getFtpConfig(){
		return frame.getFtpConfig();
	}
 

  private void setBtnStatus(boolean enable) {
	
    createFolderButton.setEnabled(enable);
    delButton.setEnabled(enable);
    downButton.setEnabled(enable);
    refreshButton.setEnabled(enable);
    renameButton.setEnabled(enable); 
  }

  /**
   * 控制用户的更改权限
   * @param enable
   */
  public void enableUserFtpOper(boolean enable) {
    createFolderButton.setEnabled(enable);
    delButton.setEnabled(enable); 
    renameButton.setEnabled(enable); 
  }
}
