package com.ufgov.zc.client.zc.ztb.activex;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

public class MainExcel {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				JFrame frame = new JFrame("frame");
				// frame.setIconImage(frame.getToolkit().getImage(GV.getImageUrl("windowicon.jpg")));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(640, 480);
				frame.setLocationRelativeTo(null);
				final ExcelPane pane = new ExcelPane() {
					@Override
					public void addNotify() {
						super.addNotify();
					}
				};
				final Container contentPane = frame.getContentPane();
				JButton saveBtn = new JButton(new AbstractAction("save") {
					@Override
					public void actionPerformed(ActionEvent e) {
						// pane.save("c:\\new_file.doc");
					}
				});
				JButton openBtn = new JButton(new AbstractAction("open") {
					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser c = new JFileChooser("C:/");
						c.setFileFilter(new FileFilter() {
							@Override
							public boolean accept(File f) {
								return f.getAbsolutePath().toLowerCase()
										.endsWith(".xls")
										|| f.getAbsolutePath().toLowerCase()
												.endsWith(".xlsx");
							}

							@Override
							public String getDescription() {
								return "";
							}
						});
						if (c.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
							// contentPane
							pane.open(c.getSelectedFile().getPath());
						}
					}
				});

				Action insert = new AbstractAction("插入文本") {
					@Override
					public void actionPerformed(ActionEvent e) {
						pane.insertTextToCurCell("hello word");
					}
				};
				Action replaceNamedRange = new AbstractAction("替换命名") {
					@Override
					public void actionPerformed(ActionEvent e) {
						pane.insertTextToNamedRang("cool!", "XKZH");
					}
				};

				Action save = new AbstractAction("保存") {
					@Override
					public void actionPerformed(ActionEvent e) {
						pane.save();
					}
				};
				Action saveAs = new AbstractAction("另存为") {
					@Override
					/*public void actionPerformed(ActionEvent e) {
						JFileChooser jChooser2 = new JFileChooser();
					    jChooser2.setCurrentDirectory(new File("c:/"));//设置默认打开路径
					    jChooser2.setDialogType(JFileChooser.SAVE_DIALOG);//设置保存对话框
					    //将设置好了的两种文件过滤器添加到文件选择器中来 
					    MainExcel.XlsFileFilter xf=new MainExcel().new XlsFileFilter();
					    jChooser2.addChoosableFileFilter(xf);
					    
					    int index = jChooser2.showDialog(null, "保存文件");
					    if (index == JFileChooser.APPROVE_OPTION) {
					        
					        String fname = jChooser2.getSelectedFile().getAbsolutePath();
					         if(!(fname.endsWith("xls")|| fname.endsWith("xlsx"))){
					        	 fname=fname+".xls";
					         }
					         File f=new File(fname);
					         if(!f.exists()){
					        	 try {
									boolean rtn=f.createNewFile();
									System.out.print(rtn);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					         }
					        pane.saveAs(f);
					       }
					}*/
					public void actionPerformed(ActionEvent e) {
						pane.saveAs();
					}
				};
				JToolBar bar = new JToolBar();
				bar.add(openBtn);
				bar.add(insert);
				bar.add(replaceNamedRange);
				bar.add(save);
				bar.add(saveAs);
				contentPane.add(bar, BorderLayout.NORTH);
				contentPane.add(pane);
				frame.setVisible(true);
				// Runtime.getRuntime().addShutdownHook(new Thread() {
				// public void run() {
				// if (pane != null) {
				// try {
				// pane.close();
				// } catch (Exception e) {
				// // TCJLODO: handle exception
				// }
				// }
				// }
				// });
			}
		});
	}
	
	//重写文件过滤器，设置打开类型中几种可选的文件类型，这里设了两种，一种txt，一种xls
	class TxtFileFilter extends FileFilter {
	 @Override
	 public boolean accept(File f) {
	  // TODO Auto-generated method stub
	  String nameString = f.getName();
	  return nameString.toLowerCase().endsWith(".txt");
	 }
	 @Override
	 public String getDescription() {
	  // TODO Auto-generated method stub
	  return "*.txt(文本文件)";
	 }
	 
	}
	class XlsFileFilter extends FileFilter {
	 @Override
	 public boolean accept(File f) {
	  // TODO Auto-generated method stub
	  String nameString = f.getName();
	  return nameString.toLowerCase().endsWith(".xls");
	 }
	 @Override
	 public String getDescription() {
	  // TODO Auto-generated method stub
	  return "*.xls(表格文件)";
	 }
	 
	}
}
