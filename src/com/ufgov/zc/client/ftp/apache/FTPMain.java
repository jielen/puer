package com.ufgov.zc.client.ftp.apache;

import java.awt.event.WindowEvent; 

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.ufgov.zc.client.ftp.apache.utils.FtpConfig;

public class FTPMain {
  private static Logger logger=Logger.getLogger(FTPMain.class);
	/**
	 * 本应用的程序入口
	 */
	public static void main(String args[]) {
    PropertyConfigurator.configure("./log4j.properties");
		// 导致 runnable 的 run 方法在 EventQueue 的指派线程上被调用。
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 使用 LookAndFeel 对象设置当前的默认外观。
					UIManager.setLookAndFeel(new NimbusLookAndFeel());// 设置一个非常漂亮的外观
					// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FtpConfig ftpConfig=new FtpConfig("127.0.0.1", Integer.parseInt("21"), "jielen", "123456","/219/recods/4");
					final FTPClientPanel ftpPanel = new FTPClientPanel(ftpConfig);
					final JFrame frame = new JFrame();
					frame.addWindowListener(new java.awt.event.WindowAdapter() {
						public void windowOpened(java.awt.event.WindowEvent evt) {
							ftpPanel.formWindowOpened(evt);
						}

						public void windowIconified(final WindowEvent e) {
							frame.setVisible(false);
						} 
				    
					});
					frame.addComponentListener(new java.awt.event.ComponentAdapter() {
						public void componentResized(
								java.awt.event.ComponentEvent evt) {
							ftpPanel.formComponentResized(evt);
						}
					});
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 
					frame.setSize(800, 600);

					frame.setLocationRelativeTo(null);

					frame.getContentPane().add(ftpPanel);

					frame.setVisible(true);

				} catch (Exception ex) {
				  logger.error(ex.getMessage(),ex);
				}
			}
		});
	}
}
