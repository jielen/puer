package com.ufgov.zc.client.ftp.apache;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.ufgov.zc.client.ftp.apache.panel.ftp.FtpPanel;
import com.ufgov.zc.client.ftp.apache.panel.local.LocalPanel;
import com.ufgov.zc.client.ftp.apache.panel.queue.DownloadPanel;
import com.ufgov.zc.client.ftp.apache.panel.queue.QueuePanel;
import com.ufgov.zc.client.ftp.apache.panel.queue.UploadPanel;
import com.ufgov.zc.client.ftp.apache.utils.FtpConfig;
import com.ufgov.zc.client.ftp.apache.utils.FtpUtils;
import com.ufgov.zc.client.ftp.apache.utils.SiteInfoBean;

public class FTPClientPanel extends javax.swing.JPanel {
  private static Logger logger=Logger.getLogger(FTPClientPanel.class);
	FTPClient ftpClient;
	private JPasswordField PassField;
	private JButton cutLinkButton;
	FtpPanel ftpPanel;
	LocalPanel localPanel;
	private JTextField portTextField;
	private JTextField serverTextField;
	private JTextField userTextField;
	private QueuePanel queuePanel;
	private UploadPanel uploadPanel;
	private DownloadPanel downloadPanel;
	private JSplitPane jSplitPane1;
	private JButton linkButton;
	private final LinkToAction LINK_TO_ACTION=null; // 连接到 按钮的动作处理器
	private final CutLinkAction CUT_LINK_ACTION=null; // 断开 按钮的动作处理器
	// private SystemTray systemTray;
	private JToggleButton shutdownButton;
	// private final ImageIcon icon = new
	// ImageIcon(getClass().getResource("/com/oyp/ftp/res/trayIcon.png"));

	private FtpConfig ftpConfig = null;

  public FTPClientPanel() { 
    this(null);
  }
	public FTPClientPanel(FtpConfig config) { 
	  this.ftpConfig=config;
		initComponents();
		initData();
	}

	private boolean initData() {
	  boolean initFtp=false;
	  try {
	    initFtp=initFtpClient();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      logger.error(e.getMessage(), e);
    }
	  localPanel.enableUpload(initFtp);
	  ftpPanel.setFtpClient(ftpClient);
	  queuePanel.startQueue(initFtp); // 启动任务队列线程
	  return initFtp;
	}
 public boolean connectToFtp(FtpConfig config){
	 this.ftpConfig=config;
	 return initData();
	 
 }
  private boolean initFtpClient() throws IOException {
    _initFtpConfig();
    FtpUtils fu=new FtpUtils();
    ftpClient=fu.createFtpClient(ftpConfig);
    return true;
	}

  /**
	 * 初始化程序界面的方法
	 */
	private void initComponents() {

		java.awt.GridBagConstraints gridBagConstraints;

		JPanel toolBarPanel = new JPanel();
		JToolBar jToolBar1 = new JToolBar();
		JButton linkTo = new JButton();
		cutLinkButton = new JButton();
		JPanel hostInfoPanel = new JPanel();
		JLabel jLabel1 = new JLabel();
		serverTextField = new JTextField();
		JLabel jLabel2 = new JLabel();
		userTextField = new JTextField();
		JLabel jLabel3 = new JLabel();
		PassField = new JPasswordField();
		JLabel jLabel6 = new JLabel();
		portTextField = new JTextField();
		linkButton = new JButton();
		JSplitPane jSplitPane2 = new JSplitPane();
		jSplitPane1 = new JSplitPane();
		ftpPanel = new FtpPanel(this); // 初始化FTP远程资源面板
		localPanel = new LocalPanel(this); // 初始化本地资源管理面板
		uploadPanel = new UploadPanel(); // 初始化上传队列面板
		downloadPanel = new DownloadPanel(); // 初始化下载队列面板
		queuePanel = new QueuePanel(this); // 初始化队列面板

		JTabbedPane jTabbedPane1 = new JTabbedPane();

		// setTitle("基于Socket的FTP软件Java实现");

		setLayout(new java.awt.GridBagLayout());

		toolBarPanel.setLayout(new java.awt.GridLayout(0, 1));

		jToolBar1.setRollover(true);
		jToolBar1.setFloatable(false);

		linkTo.setText("连接到");
		linkTo.setFocusable(false);
//		linkTo.setAction(LINK_TO_ACTION);
		jToolBar1.add(linkTo);

		cutLinkButton.setText("断开");
		cutLinkButton.setEnabled(false);
		cutLinkButton.setFocusable(false);
//		cutLinkButton.setAction(CUT_LINK_ACTION);
		jToolBar1.add(cutLinkButton);

		shutdownButton = new JToggleButton();
		shutdownButton.setText("自动关机");
		shutdownButton.setToolTipText("队列完成后，自动关闭计算机");
		shutdownButton.setFocusable(false);
		jToolBar1.add(shutdownButton);

//		toolBarPanel.add(jToolBar1);

		hostInfoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		hostInfoPanel.setLayout(new javax.swing.BoxLayout(hostInfoPanel, javax.swing.BoxLayout.LINE_AXIS));

		jLabel1.setText("主机地址：");
		hostInfoPanel.add(jLabel1);

		serverTextField.setText("127.0.0.1");
		serverTextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				LinkFTPKeyPressed(evt);
			}
		});
		hostInfoPanel.add(serverTextField);

		jLabel2.setText("用户名：");
		hostInfoPanel.add(jLabel2);

		userTextField.setText("puersf");
		userTextField.setMaximumSize(new java.awt.Dimension(200, 2147483647));
		userTextField.setPreferredSize(new java.awt.Dimension(100, 21));
		userTextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				LinkFTPKeyPressed(evt);
			}
		});
		hostInfoPanel.add(userTextField);

		jLabel3.setText("密码：");
		hostInfoPanel.add(jLabel3);

		PassField.setText("123456");
		PassField.setMaximumSize(new java.awt.Dimension(200, 2147483647));
		PassField.setPreferredSize(new java.awt.Dimension(100, 21));
		PassField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				LinkFTPKeyPressed(evt);
			}
		});
		hostInfoPanel.add(PassField);

		jLabel6.setText("端口：");
		hostInfoPanel.add(jLabel6);

		portTextField.setText("21");
		portTextField.setMaximumSize(new java.awt.Dimension(100, 2147483647));
		portTextField.setPreferredSize(new java.awt.Dimension(50, 21));
		portTextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				LinkFTPKeyPressed(evt);
			}
		});
		hostInfoPanel.add(portTextField);

		linkButton.setText("连接");
		linkButton.setFocusable(false);
		linkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		linkButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		linkButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				linkButtonActionPerformed(evt);
			}
		});
		hostInfoPanel.add(linkButton);

//		toolBarPanel.add(hostInfoPanel);
		

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0; // 指定包含组件的显示区域开始边的单元格，其中行的第一个单元格为 gridx=0。
		gridBagConstraints.gridy = 0; // 指定位于组件显示区域的顶部的单元格，其中最上边的单元格为 gridy=0。
		// 当组件的显示区域大于它所请求的显示区域的大小时使用此字段。
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL; // 在水平方向而不是垂直方向上调整组件大小。
		gridBagConstraints.weightx = 1.0; // 指定如何分布额外的水平空间。

		add(toolBarPanel, gridBagConstraints);

		jSplitPane2.setBorder(null);
		jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		jSplitPane2.setResizeWeight(1.0);
		jSplitPane2.setContinuousLayout(true);

		jSplitPane1.setDividerLocation(400);
		jSplitPane1.setDividerSize(10);
		jSplitPane1.setOneTouchExpandable(true);
		jSplitPane1.setRightComponent(ftpPanel);
		jSplitPane1.setLeftComponent(localPanel);

		jSplitPane2.setLeftComponent(jSplitPane1);

		jTabbedPane1.setMinimumSize(new java.awt.Dimension(40, 170));

		jTabbedPane1.addTab("队列", queuePanel);// 添加队列面板
		jTabbedPane1.addTab("上传队列", uploadPanel);// 添加上传面板
		jTabbedPane1.addTab("下载队列", downloadPanel);// 添加下载面板

		jSplitPane2.setBottomComponent(jTabbedPane1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH; // 在水平方向和垂直方向上同时调整组件大小。
		gridBagConstraints.weightx = 1.0; // 指定如何分布额外的水平空间。
		gridBagConstraints.weighty = 1.0; // 指定如何分布额外的垂直空间。
		add(jSplitPane2, gridBagConstraints);

//		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//		setBounds((screenSize.width - 800) / 2, (screenSize.height - 600) / 2,800, 700);
	}

	public JToggleButton getShutdownButton() {
		return shutdownButton;
	}

	/**
	 * 窗体装载的事件处理方法
	 */
	public void formWindowOpened(java.awt.event.WindowEvent evt) {
		jSplitPane1.setDividerLocation(0.50);
		localPanel.getLocalDiskComboBox().setSelectedIndex(1);
		localPanel.getLocalDiskComboBox().setSelectedIndex(0);
	}

	/**
	 * 窗体大小调整的事件处理方法
	 */
	public void formComponentResized(java.awt.event.ComponentEvent evt) {
		jSplitPane1.setDividerLocation(0.50);
	}

	/**
	 * 连接按钮的事件处理方法
	 */
	private void linkButtonActionPerformed(java.awt.event.ActionEvent evt) {
		/*try {
			String server = serverTextField.getText(); // 获取服务器地址
			if (server == null) {
				return;
			}
			String portStr = portTextField.getText(); // 获取端口号
			if (portStr == null) {
				portStr = "21";
			}
			int port = Integer.parseInt(portStr.trim());
			String userStr = userTextField.getText(); // 获取用户名
			userStr = userStr == null ? "" : userStr.trim();
			String passStr = PassField.getText(); // 获取密码
			passStr = passStr == null ? "" : passStr.trim();
			cutLinkButton.doClick();
			FtpUtils fu = new FtpUtils();
			ftpClient = fu.createFtpClient(server, port, userStr, passStr);
			// 连接到FTP服务器
			ftpClient.connect(server.trim(), port);
			if (ftpClient == null) {
				CUT_LINK_ACTION.setEnabled(false); // 设置断开按钮不可用
			} else {
				CUT_LINK_ACTION.setEnabled(true); // 设置断开按钮可用
				setFtpConfig(new FtpConfig(server, port, userStr, passStr, ""));
			} 

			//
			// 设置本地资源管理面板的FTP连接信息
			localPanel.startUploadThread();
			// 设置上传按钮可用
			localPanel.getActionMap().get("uploadAction").setEnabled(true);
			ftpPanel.setFtpClient(ftpClient);// 设置FTP资源管理面板的FTP连接信息
			// 设置下载按钮可用
			ftpPanel.getActionMap().get("downAction").setEnabled(true);
			ftpPanel.refreshCurrentFolder();// 刷新FTP资源管理面板的当前文件夹
			queuePanel.startQueue(); // 启动任务队列线程
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
	}

	/**
	 * 连接FTP相关的文本框 和密码框的回车事件
	 */
	private void LinkFTPKeyPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyChar() == '\n') {
			linkButton.doClick();
		}
	}

	public LocalPanel getLocalPanel() {
		return localPanel;
	}

	public FtpPanel getFtpPanel() {
		return ftpPanel;
	}

	public QueuePanel getQueuePanel() {
		return queuePanel;
	}

	public UploadPanel getUploadPanel() {
		return uploadPanel;
	}

	public DownloadPanel getDownloadPanel() {
		return downloadPanel;
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	/**
	 * 设置FTP连接信息的方法，由FTP站点管理器调用
	 */
	public void setLinkInfo(SiteInfoBean bean) {
		serverTextField.setText(bean.getServer()); // 设置主机地址
		portTextField.setText(bean.getPort() + ""); // 设置端口号
		userTextField.setText(bean.getUserName()); // 设置用户名
		PassField.setText(""); // 密码清空
		PassField.requestFocus(); // 密码框请求焦点
	}

	public FtpConfig getFtpConfig() {
	  if(ftpConfig==null){
	    _initFtpConfig();
	  }
		return ftpConfig;
	}

	private boolean _initFtpConfig() {
	  if(ftpConfig!=null){
	    return true;
	  }
	  //开发 时使用的ftp配置信息
//	  ftpConfig=new FtpConfig("127.0.0.1", Integer.parseInt("21"), "jielen", "123456","/2018/4");
	  return false;
	}

  public void setFtpConfig(FtpConfig ftpConfig) {
		this.ftpConfig = ftpConfig;
	}
public void enableUserFtpOper(boolean b) {
	ftpPanel.enableUserFtpOper(b);
	localPanel.enableUserFtpOper(b);
}

	 
}
