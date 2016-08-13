package com.ufgov.zc.client.zc.ztb.activex;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.ufgov.zc.common.sf.model.SfBookmark;

public class WordPane extends JPanel {
	private static final long serialVersionUID = 882379749383014297L;

	private static Logger log = Logger.getLogger(WordPane.class);

	public static final String EVENT_NAME_OPEN_CALLBACK = "open_callback";

	public String EVENT_NAME_OPEN_FINISH = "open_finish";

	private OleFrame frame;

	private OleClientSite site;

	private OleAutomation automation;

	private Display display;

	private Shell swtParent;

	private Canvas awtParent;

	private File openFile;

	public WordPane() {
	}

	/**
	 * 打开word文档，另开线程进行打开操作，使用回调函数触发打开完成。
	 * 
	 * @param openFileName
	 * @return void
	 * @throws
	 */
	public void open(String openFileName) {
		if (!new File(openFileName).exists()) {
			open_callback(false);
			return;
		}
		this.openFile = new File(openFileName);

		Thread thread = new Thread(new Runnable() {
			public void run() {
				setLayout(new GridLayout(1, 1));
				awtParent = new Canvas();
				add(awtParent);
				display = new Display();
				swtParent = SWT_AWT.new_Shell(display, awtParent);
				swtParent.setLayout(new FillLayout());
				frame = new OleFrame(swtParent, SWT.NONE);
				try {
					site = new OleClientSite(frame, SWT.NONE, openFile);//自动根据文件类型打开对应的控件，如word、excel等
					//如果希望打开具体的程序，如outlook、windowsmideaplay、explore等，需要到注册表查其程序对应的名称，把openFile参数改成对应的名字即可，如"Outlook.Application"
				} catch (SWTException e) {
					String str = "Create OleClientSite Error " + e.toString();
					System.out.println(str);
					open_callback(false);
					e.printStackTrace();
					throw new RuntimeException(e);
				} // setSize(500, 500); validate();
				site.doVerb(OLE.OLEIVERB_SHOW);
				automation = new OleAutomation(site);
				open_callback(true);
				while (swtParent != null && !swtParent.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				} // display.close();
				display.dispose();
			}
		});
		thread.start();

		/*
		 * setLayout(new GridLayout(1, 1)); awtParent = new Canvas() { public
		 * void addNotiFy() { super.addNotify(); synchronized (getTreeLock()) {
		 * new Thread() { public void run() { _openFile(); } }.start(); } } };
		 * 
		 * add(awtParent);
		 */
	}

	private void _openFile() {

		display = new Display();
		swtParent = SWT_AWT.new_Shell(display, awtParent);
		swtParent.setLayout(new FillLayout());
		frame = new OleFrame(swtParent, SWT.NONE);
		try {
			site = new OleClientSite(frame, SWT.NONE, openFile);
		} catch (SWTException e) {
			String str = "Create OleClientSite Error " + e.toString();
			System.out.println(str);
			open_callback(false);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// setSize(500, 500);
		validate();
		site.doVerb(OLE.OLEIVERB_SHOW);
		automation = new OleAutomation(site);
		open_callback(true);
		while (swtParent != null && !swtParent.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		// display.close();
		display.dispose();
	}

	// 显示，隐藏修订

	public void viewTrackRevisions(final Boolean isView) {
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation activeDocument = getWordActiveDocument();
					int[] appId = activeDocument
							.getIDsOfNames(new String[] { "ShowRevisions" });
					activeDocument.setProperty(appId[0], new Variant(isView));
				}
			});
		}
	}

	// 增加，取消修订

	public void addTrackRevisions(final Boolean isView, final String name) {
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation wordAppAutomation = getWordApplication();
					int[] appId1 = wordAppAutomation
							.getIDsOfNames(new String[] { "UserName" });
					wordAppAutomation.setProperty(appId1[0], new Variant(name));
					OleAutomation activeDocument = getWordActiveDocument();
					int[] appId = activeDocument
							.getIDsOfNames(new String[] { "TrackRevisions" });
					activeDocument.setProperty(appId[0], new Variant(isView));
				}
			});
		}
	}

	/**
	 * 以留痕方式打开word文档，另开线程进行打开操作，使用回调函数触发打开完成。
	 * 
	 * @param openFileName
	 * @return void
	 * @throws
	 */
	public void openTrackRevisions(String openFileName) {
		// WordBean("jixueyou", openFileName);
		open(openFileName);
		addPropertyChangeListener(EVENT_NAME_OPEN_CALLBACK,
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						// 打开文件完成之后的回调函数
						OleAutomation activeDocument = getWordActiveDocument();
						int[] appId = activeDocument
								.getIDsOfNames(new String[] { "TrackRevisions" });
						activeDocument.setProperty(appId[0], new Variant(true));
					}
				});
	}

	/**
	 * 接授所有word痕迹
	 * 
	 * @param fListPath
	 */
	public void acceptAllRevisions() {
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation wordAppAutomation = getWordApplication();
					OleAutomation activeDocument = getWordActiveDocument();
					OleAutomation revisions = getSubOleAutomation(
							activeDocument, "Revisions");
					int[] appId11 = revisions
							.getIDsOfNames(new String[] { "Count" });
					Variant count = revisions.getProperty(appId11[0]);
					if (count.getInt() >= 1) {
						int[] appId1 = wordAppAutomation
								.getIDsOfNames(new String[] { "UserName" });
						wordAppAutomation.setProperty(appId1[0], new Variant(
								"*"));
						int[] appId = activeDocument
								.getIDsOfNames(new String[] { "TrackRevisions" });
						activeDocument
								.setProperty(appId[0], new Variant(false));
						getFunctionResult(activeDocument, "AcceptAllRevisions");
					}
				}
			});
		}
	}

	/**
	 * 打开word文档，并且使用密码保护，不允许修改。
	 * 
	 * @return void 返回类型
	 * @since 1.0
	 */
	public void openAndProtect(String openFileName, final String password) {
		open(openFileName);
		addPropertyChangeListener(EVENT_NAME_OPEN_CALLBACK,
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						// 打开文件完成之后的回调函数
						boolean isSuccess = (Boolean) evt.getNewValue();
						if (isSuccess) {
							protectDoc(password);
						}
						firePropertyChange(EVENT_NAME_OPEN_FINISH, !isSuccess,
								isSuccess);
						removePropertyChangeListener(EVENT_NAME_OPEN_CALLBACK,
								this);
					}
				});
	}

	/**
	 * 使用事件触发是否打开成功
	 * 
	 * @param isSuccess
	 * @return void
	 * @throws
	 */
	private void open_callback(boolean isSuccess) {
		firePropertyChange(EVENT_NAME_OPEN_CALLBACK, !isSuccess, isSuccess);
	}

	/**
	 * 文件是否被修改过
	 * 
	 * @return
	 */
	public boolean isDirty() {
		return site.isDirty();
	}

	/**
	 * 保存当前编辑文件
	 * 
	 * @return boolean
	 * @throws
	 */
	public boolean save() {
		return save(openFile);
	}

	/**
	 * 保存文件
	 * 
	 * @param fileName
	 * @return boolean
	 * @throws
	 */
	public boolean save(String fileName) {
		return save(new File(fileName));
	}

	;

	/**
	 * 保存文件，使用线程同步
	 * 
	 * @param file
	 * @return boolean
	 * @throws
	 */
	public boolean save(final File file) {
		final boolean[] isSuccess = { false };
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					if (site.isDirty()) {
						isSuccess[0] = site.save(file, true);
					} else {
						isSuccess[0] = false;
					}
				}
			});
		}
		return isSuccess[0];
	}

	/**
	 * 关闭并保存当前文件
	 * 
	 * @return boolean
	 * @throws
	 */
	public boolean close() {
		return close(true);
	}

	/**
	 * 关闭并不保存当前文件
	 * 
	 * @return boolean 返回类型
	 * @since 1.0
	 */
	public boolean closeNotSave() {
		return close(false);
	}

	public boolean close(final boolean isSave) {
		final boolean[] isSuccess = { false };
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					if (site.isDirty()) {
						isSuccess[0] = site.save(openFile, isSave);
					} else {
						isSuccess[0] = false;
					}
					if (swtParent != null && !swtParent.isDisposed()) {
						swtParent.dispose();
					}
					swtParent = null;
					display.dispose();
					display = null;
				}
			});
			remove(awtParent);
			awtParent = null;
		}
		return isSuccess[0];
	}

	/**
	 * 替换书签
	 * 
	 * @return boolean 返回类型
	 * @since 1.0
	 */
	public boolean replaceBookMarks(final String[] names, final String[] values) {
		display.syncExec(new Runnable() {
			public void run() {
				OleAutomation activeDocument = getWordActiveDocument();
				OleAutomation bookMarksAutomation = getSubOleAutomation(
						activeDocument, "Bookmarks");
				Variant pVarResult = getSubOleResult(bookMarksAutomation,
						"Count");
				for (int i = 0, j = names.length; i < j; i++) {
					Variant[] params = new Variant[1];
					params[0] = new Variant(names[i]);
					pVarResult = getFunctionResult(bookMarksAutomation,
							"Exists", params);
					if (pVarResult.getBoolean()) {
						pVarResult = getFunctionResult(bookMarksAutomation,
								"Item", params);
						OleAutomation itemOleAutomation = pVarResult
								.getAutomation();
						pVarResult = getSubOleResult(itemOleAutomation, "Range");
						OleAutomation rangOleAutomation = pVarResult
								.getAutomation();
						setSubOleResult(rangOleAutomation, "Text", values[i]);
					}
				}
			}
		});
		return true;
	}

	/**
	 * 替换书签，此处必须使用线程同步方法
	 * 
	 * @param attributes
	 * @return boolean
	 * @throws
	 */
	public boolean replaceBookMarks(String attributes) {
		String sep1 = "@@@@@";
		String sep2 = "$$$$$";
		List<String> names = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		String[] bookMarks = attributes.split(sep1);
		for (int i = 0, j = bookMarks.length; i < j; i++) {
			String bookMark = bookMarks[i];
			if (bookMark != null) {
				String[] oneBook = bookMark.split(Matcher
						.quoteReplacement(sep2));
				if (oneBook != null && oneBook.length == 2) {
					names.add(oneBook[0] == null ? "" : oneBook[0]);
					values.add(oneBook[1] == null ? "" : oneBook[1]);
				}
			}
		}
		return replaceBookMarks(names, values);
	}

	;

	/**
	 * 替换书签
	 * 
	 * @return boolean 返回类型
	 * @since 1.0
	 */
	public boolean replaceBookMarks(List<String> names, List<String> values) {
		String[] vnames = new String[names.size()];
		String[] vvalues = new String[names.size()];
		for (int i = 0, j = names.size(); i < j; i++) {
			String name = names.get(i);
			vnames[i] = name == null ? "" : name;
			String value = values.get(i);
			vvalues[i] = value == null ? "" : value;
		}
		return replaceBookMarks(vnames, vvalues);
	}

	public boolean replaceBookMarks(List<SfBookmark> mks) {
		String[] vnames = new String[mks.size()];
		String[] vvalues = new String[mks.size()];
		for (int i = 0; i < mks.size(); i++) {
			SfBookmark bk = mks.get(i);
			String name = bk.getName();
			vnames[i] = name == null ? "" : name;
			String value = bk.getValue();
			vvalues[i] = value == null ? "" : value;
//			System.out.println(vnames[i]+"="+vvalues[i]);
		}
		return replaceBookMarks(vnames, vvalues);
	}

	/**
	 * 使用密码保护文档不被修改
	 * 
	 * @return boolean 返回类型
	 * @since 1.0
	 */
	public boolean protectDoc(final String password) {
		final Map<String, Variant> result = new HashMap<String, Variant>();
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation activeDocument = getWordActiveDocument();
					Variant[] params = new Variant[5];
					params[0] = new Variant(1);
					params[1] = new Variant(false);
					params[2] = new Variant(password);
					params[3] = new Variant(false);
					params[4] = new Variant(false);
					Variant variant = getFunctionResult(activeDocument,
							"Protect", params);
					result.put("result", variant);
				}
			});
		}
		Variant variant = result.get("result");
		if (variant == null) {
			// JOptionPane.showMessageDialog(null, "已经处于保护状态...");
			return false;
		}
		return true;
	}

	;

	/**
	 * 使用密码解除保护文档
	 * 
	 * @return boolean 返回类型
	 * @since 1.0
	 */
	public boolean unProtectDoc(final String password) {
		final Map<String, Variant> result = new HashMap<String, Variant>();
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation activeDocument = getWordActiveDocument();
					Variant[] params = new Variant[1];
					params[0] = new Variant(password);
					Variant variant = getFunctionResult(activeDocument,
							"Unprotect", params);
					result.put("result", variant);
				}
			});
		}
		Variant variant = result.get("result");
		if (variant == null) {
			// JOptionPane.showMessageDialog(null, "密码不正确，解除保护失败");
			return false;
		}
		return true;
	}

	;

	/**
	 * 当前是否有打开的word
	 * 
	 * @return boolean 返回类型
	 * @since 1.0
	 */
	public boolean isDocOpened() {
		if (display != null && !display.isDisposed()) {
			return true;
		}
		return false;
	}

	/**
	 * 取到word对象，操作word
	 * 
	 * @param clientSite
	 * @return OleAutomation
	 * @throws
	 */
	private OleAutomation getWordApplication() {
		OleAutomation wordAppAutomation = getSubOleAutomation(automation,
				"Application");
		return wordAppAutomation;
	}

	private OleAutomation getWordActiveDocument() {
		OleAutomation wordAppAutomation = getWordApplication();
		OleAutomation wordActiveDocument = getSubOleAutomation(
				wordAppAutomation, "ActiveDocument");
		return wordActiveDocument;
	}

	// private OleAutomation getWordDocuments() {
	// OleAutomation wordAppAutomation = getWordApplication();
	// OleAutomation wordActiveDocument = getSubOleAutomation(wordAppAutomation,
	// "Documents");
	// return wordActiveDocument;
	// }
	//
	// private OleAutomation getWordDocument() {
	// OleAutomation wordAppAutomation = getWordDocuments();
	// OleAutomation wordActiveDocument = getSubOleAutomation(wordAppAutomation,
	// "Document");
	// return wordActiveDocument;
	// }

	private OleAutomation getSubOleAutomation(OleAutomation oleAutomation,
			String name) {
		Variant pVarResult = getSubOleResult(oleAutomation, name);
		return pVarResult.getAutomation();
	}

	private Variant getSubOleResult(OleAutomation oleAutomation, String name) {
		int[] appId = oleAutomation.getIDsOfNames(new String[] { name });
		if (appId == null)
			OLE.error(OLE.ERROR_APPLICATION_NOT_FOUND);
		Variant pVarResult = oleAutomation.getProperty(appId[0]);
		if (pVarResult == null)
			OLE.error(OLE.ERROR_APPLICATION_NOT_FOUND);
		return pVarResult;
	}

	private boolean setSubOleResult(OleAutomation oleAutomation, String name,
			String value) {
		int[] appId = oleAutomation.getIDsOfNames(new String[] { name });
		if (appId == null)
			OLE.error(OLE.ERROR_APPLICATION_NOT_FOUND);
		Variant[] params = new Variant[1];
		params[0] = new Variant(value);
		oleAutomation.setProperty(appId[0], params);
		return true;
	}

	private int[] getAppId(OleAutomation oleAutomation, String[] names) {
		int[] appId = oleAutomation.getIDsOfNames(names);
		if (appId == null)
			OLE.error(OLE.ERROR_APPLICATION_NOT_FOUND);
		return appId;
	}

	private Variant getFunctionResult(OleAutomation oleAutomation,
			String function, Variant[] params) {
		int[] appId = getAppId(oleAutomation, new String[] { function });
		return oleAutomation.invoke(appId[0], params);
	}

	private Variant getFunctionResult(OleAutomation oleAutomation,
			String function) {
		int[] appId = getAppId(oleAutomation, new String[] { function });
		return oleAutomation.invoke(appId[0]);
	}

	/**
	 * 合并word文件
	 * 
	 * @param fListPath
	 */
	public void combineMsWord(final List<String> fListPath) {
		if (fListPath == null || fListPath.size() == 0) {
			return;
		}
		display.syncExec(new Runnable() {
			public void run() {
				OleAutomation document = getWordApplication();
				for (int i = 0; i < fListPath.size(); i++) {
					OleAutomation selection = getSubOleAutomation(document,
							"Selection");
					Variant[] params = new Variant[5];
					params[0] = new Variant(fListPath.get(i));
					params[1] = new Variant("");
					params[2] = new Variant(false);
					params[3] = new Variant(false);
					params[4] = new Variant(false);
					getFunctionResult(selection, "insertFile", params);
				}
			}
		});
	}

	/**
	 * 在当前光标的位置插入字符串内容
	 * 
	 * @param text
	 */
	public void insertTextToDoc(final String text) {
		if (text == null) {
			return;
		}
		display.syncExec(new Runnable() {
			public void run() {
				OleAutomation document = getWordApplication();
				OleAutomation selection = getSubOleAutomation(document,
						"Selection");
				Variant[] params = new Variant[1];
				params[0] = new Variant(text);
				getFunctionResult(selection, "TypeText", params);
			}
		});
	}

	/**
	 * 把当前的word文档转换成html文件格式的文档
	 * 
	 * @param htmlFileName
	 * @return
	 */
	public boolean convertWordToHtml(final String htmlFileName) {
		display.syncExec(new Runnable() {
			public void run() {
				log.debug("--------------------------------------begin word to html="
						+ htmlFileName);
				OleAutomation activeDocument = getWordActiveDocument();
				Variant[] params = new Variant[11];
				params[0] = new Variant(htmlFileName);// filename
				params[1] = new Variant(8);// htm格式 format
				params[2] = new Variant(true);// LockComments 如果为
												// true，则锁定文档以进行注释。默认值为 false
				params[3] = new Variant("");// Password
				params[4] = new Variant(true);// AddToRecentFiles 如果为
												// true，则将该文档添加到“文件”菜单上最近使用的文件列表中。默认值为
												// true
				params[5] = new Variant("");// WritePassword 用来保存对文件所做更改的密码字符串。
				params[6] = new Variant(false);// ReadOnlyRecommended 如果为
												// true，则让 Microsoft Office Word
												// 在打开文档时建议只读状态。默认值为 false。
				params[7] = new Variant(true);// EmbedTrueTypeFonts 如果为 true，则将
												// TrueType 字体随文档一起保存。如果省略的话，则
												// EmbedTrueTypeFonts 参数假定
												// EmbedTrueTypeFonts 属性的值。
				params[8] = new Variant(false);// SaveNativePictureFormat
												// 如果图形是从另一个平台（例如，Macintosh）导入的，则
												// true 表示仅保存导入图形的 Windows 版本。
				params[9] = new Variant(false);// SaveFormsData 如果为
												// true，则将用户在窗体中输入的数据另存为数据记录。
				params[10] = new Variant(false);// SaveAsAOCELetter
												// 如果文档附加了邮件程序，则 true 表示会将文档另存为
												// AOCE 信函（邮件程序会进行保存）。
				// params[11] = new
				// Variant("msoEncodingMacSimplifiedChineseGB2312");
				getFunctionResult(activeDocument, "SaveAs", params);
				log.debug("--------------------------------------end  word to html="
						+ htmlFileName);
			}
		});
		return true;
	}

	/**
	 * 在当前光标的位置插入一个超链接
	 * 
	 * @param hrefFileName
	 * @param fileName
	 * @return
	 */
	public boolean insertAttachmentUrl(final String hrefFileName,
			final String fileName) {
		display.syncExec(new Runnable() {
			public void run() {
				OleAutomation activeDocument = getWordActiveDocument();
				OleAutomation hyperLinks = getSubOleAutomation(activeDocument,
						"Hyperlinks");
				OleAutomation document = getWordApplication();
				OleAutomation selectOleAutomation = getSubOleAutomation(
						document, "Selection");
				Variant pVarResult = getSubOleResult(selectOleAutomation,
						"Range");
				OleAutomation rangOleAutomation = pVarResult.getAutomation();
				Variant[] params = new Variant[5];
				params[0] = new Variant(rangOleAutomation);
				params[1] = new Variant(hrefFileName);
				params[2] = new Variant("");
				params[3] = new Variant("");
				params[4] = new Variant(fileName);
				getFunctionResult(hyperLinks, "Add", params);
			}
		});
		return true;
	}

	/**
	 * 根据传入的书签名称定位书签所在的位置
	 * 
	 * @param bookMarkName
	 * @return
	 */
	public boolean selectBookMark(final String bookMarkName) {
		display.syncExec(new Runnable() {
			public void run() {
				// 获得书签
				OleAutomation activeDocument = getWordActiveDocument();
				OleAutomation bookMarksAutomation = getSubOleAutomation(
						activeDocument, "Bookmarks");
				Variant[] param = new Variant[1];
				param[0] = new Variant(bookMarkName);
				// 判断书签是否存在
				Variant pVarResult = getFunctionResult(bookMarksAutomation,
						"Exists", param);
				// 如果存在,选择此item
				if (pVarResult.getBoolean()) {
					pVarResult = getFunctionResult(bookMarksAutomation, "Item",
							param);
					OleAutomation itemOleAutomation = pVarResult
							.getAutomation();
					pVarResult = getFunctionResult(itemOleAutomation, "Select");
				}
			}
		});
		return true;
	}

	/**
	 * 打印当前文档
	 * 
	 * @return
	 */
	public boolean print() {
		display.syncExec(new Runnable() {
			public void run() {
				// 得到文档的Dialogs对话框集合
				OleAutomation document = getWordApplication();
				OleAutomation dialogsOleAutomation = getSubOleAutomation(
						document, "Dialogs");
				// 根据传入的参数获得打印设置对话框对象
				// 88(wdDialogFilePrint):打印设置对话框
				// 80(wdDialogFileSave) :保存对话框
				Variant[] params = new Variant[1];
				params[0] = new Variant(88);
				Variant pVarResult = getFunctionResult(dialogsOleAutomation,
						"Item", params);
				// 调用Show方法
				OleAutomation itemOleAutomation = pVarResult.getAutomation();
				getFunctionResult(itemOleAutomation, "Show");
				// 下面为直接打印的情况
				// OleAutomation activeDocument = getWordActiveDocument();
				// int[] appId = getAppId(activeDocument, new String[] {
				// "PrintOut" });
				// activeDocument.invoke(appId[0]);
			}
		});
		return true;
	}

	/**
	 * 根据传入的书签名称为当前的活动文档添加书签
	 * 
	 * @param bookMarkName
	 * @return
	 */
	public boolean insertBookMark(final String bookMarkName) {
		display.syncExec(new Runnable() {
			public void run() {
				// 获得当前活动文档，并得到Bookmarks对象
				OleAutomation activeDocument = getWordActiveDocument();
				OleAutomation bookMarks = getSubOleAutomation(activeDocument,
						"Bookmarks");
				// 获得当前的selection对象，selection就是当前光标所在的地方
				OleAutomation document = getWordApplication();
				OleAutomation selectOleAutomation = getSubOleAutomation(
						document, "Selection");
				// 定位当前选中的区域
				Variant pVarResult = getSubOleResult(selectOleAutomation,
						"Range");
				OleAutomation rangOleAutomation = pVarResult.getAutomation();
				Variant[] params = new Variant[2];
				params[0] = new Variant(bookMarkName);
				params[1] = new Variant(rangOleAutomation);
				getFunctionResult(bookMarks, "Add", params);
			}
		});
		return true;
	}

	/**
	 * 打开word文档，并且使用密码保护，不允许修改。
	 * 
	 * @return void 返回类型
	 * @since 1.0
	 */
	public void openAndReadOnly(String openFileName, final String password) {
		open(openFileName);
		addPropertyChangeListener(EVENT_NAME_OPEN_CALLBACK,
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent evt) {
						// 打开文件完成之后的回调函数
						boolean isSuccess = (Boolean) evt.getNewValue();
						if (isSuccess) {
							protectDocReadOnly(password);
						}
						removePropertyChangeListener(EVENT_NAME_OPEN_CALLBACK,
								this);
					}
				});
	}

	/**
	 * 使用密码对文档所有内容进行保护。调用此方法后，文档内原来保留的可录入区域将不存在，均变为受密码保护。此过程不可逆。
	 * 
	 * @return boolean 返回类型
	 * @since 1.0
	 */
	public boolean protectDocReadOnly(final String password) {
		final Map<String, Boolean> result = new HashMap<String, Boolean>();
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					int docLength = 0;
					Variant[] params = new Variant[1];
					OleAutomation activeDocument = getWordActiveDocument();
					// 获得selection对象
					OleAutomation document = getWordApplication();
					OleAutomation selectOleAutomation = getSubOleAutomation(
							document, "Selection");
					// 判断文件是否已经加密，如果文件已经加密，需要先解密
					Variant pVarProtectType = getSubOleResult(activeDocument,
							"ProtectionType");
					if (pVarProtectType.getInt() > 0) {
						unProtectDoc(password);
					}
					// 选中整个文件内容
					selectionWholeStory();
					// 获得选定内容的结束字符的位置
					docLength = activeDocLength();
					// 根据首字符位置和结束字符位置获得选中的区域对象。由于结束字符为整个文档的末尾，因此也就获得了整个文档区域对象
					OleAutomation rangeOleAutomation = getOleSelectionRange(0,
							docLength);
					// 获得range的Editors对象，代表这个区域可编辑的用户集合
					Variant pVarRangeEditors = getSubOleResult(
							rangeOleAutomation, "Editors");
					OleAutomation rangeEditorsOleAutomation = pVarRangeEditors
							.getAutomation();
					// 为整个区域添加可编辑用户,-1:代表word中的"每个人"选择项
					addOleRangeEditor(rangeEditorsOleAutomation, -1);
					// 获得上面定义的"每个人"用户的所有可编辑区域
					params = new Variant[1];
					params[0] = new Variant(-1);
					getFunctionResult(activeDocument,
							"SelectAllEditableRanges", params);
					// 获得Editors对象
					Variant pVarEditors = getSubOleResult(selectOleAutomation,
							"Editors");
					OleAutomation editorsOleAutomation = pVarEditors
							.getAutomation();
					params = new Variant[1];
					params[0] = new Variant(1);
					Variant pVarResult = getFunctionResult(
							editorsOleAutomation, "Item", params);
					OleAutomation itemOleAutomation = pVarResult
							.getAutomation();
					getFunctionResult(itemOleAutomation, "DeleteAll");
					// 重新对文档进行加密
					protectDoc(password);
					// 全选文档后光标会停留在文档末尾,下面的过程移动光标到文档的开始处
					boolean moveFlag = moveCursorLeft(selectOleAutomation,
							docLength);
					result.put("result", moveFlag);
				}
			});
		}
		Boolean flag = result.get("result");
		return flag;
	}

	;

	/**
	 * 返回选定内容结束字符的位置
	 * 
	 * @return：长度
	 */
	public int activeDocLength() {
		final Map<String, Variant> result = new HashMap<String, Variant>();
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation document = getWordApplication();
					OleAutomation selectOleAutomation = getSubOleAutomation(
							document, "Selection");
					// 记录文档结束位置
					Variant pVarEnd = getSubOleResult(selectOleAutomation,
							"End");
					result.put("result", pVarEnd);
				}
			});
		}
		Variant variant = result.get("result");
		if (variant == null) {
			return 0;
		}
		return variant.getInt();
	}

	;

	/**
	 * 返回选定内容结束字符的位置
	 * 
	 * @return：长度
	 */
	public Map<String, String> getSelectedDocRange() {
		final Map<String, Variant> result = new HashMap<String, Variant>();
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation document = getWordApplication();
					OleAutomation selectOleAutomation = getSubOleAutomation(
							document, "Selection");
					// 记录文档结束位置
					Variant pVarEnd = getSubOleResult(selectOleAutomation,
							"End");
					result.put("end", pVarEnd);
					Variant pVarStart = getSubOleResult(selectOleAutomation,
							"Start");
					result.put("start", pVarStart);
				}
			});
		}
		Map<String, String> retMap = new HashMap<String, String>();
		Variant end = result.get("end");
		if (end == null) {
			retMap.put("end", "0");
		} else {
			retMap.put("end", end.getString());
		}
		Variant start = result.get("start");
		if (start == null) {
			retMap.put("start", "0");
		} else {
			retMap.put("start", start.getString());
		}
		return retMap;
	}

	;

	/**
	 * 旋转word中选中的区域页的纸张方向
	 */
	public Map<String, String> rotateWordSelectionPage(final int width,
			final int height) {
		final Map<String, Variant> result = new HashMap<String, Variant>();
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation document = getWordApplication();
					OleAutomation selectOleAutomation = getSubOleAutomation(
							document, "Selection");
					// 定位当前选中的区域
					Variant pVarResult = getSubOleResult(selectOleAutomation,
							"Range");
					OleAutomation rangOleAutomation = pVarResult
							.getAutomation();
					int i = 0;
					Variant[] params = new Variant[25];
					// .LineNumbering.Active = False
					params[i++] = new Variant(false);
					// .Orientation = wdOrientLandscape
					params[i++] = new Variant("wdOrientLandscape");
					// .TopMargin = CentimetersToPoints(3.17)
					params[i++] = new Variant("CentimetersToPoints(3.17)");
					// .BottomMargin = CentimetersToPoints(3.17)
					params[i++] = new Variant("CentimetersToPoints(3.17)");
					// .LeftMargin = CentimetersToPoints(2.54)
					params[i++] = new Variant("CentimetersToPoints(2.54)");
					// .RightMargin = CentimetersToPoints(2.54)
					params[i++] = new Variant("CentimetersToPoints(2.54)");
					// .Gutter = CentimetersToPoints(0)
					params[i++] = new Variant("CentimetersToPoints(0)");
					// .HeaderDistance = CentimetersToPoints(1.5)
					params[i++] = new Variant("CentimetersToPoints(1.5)");
					// .FooterDistance = CentimetersToPoints(1.75)
					params[i++] = new Variant("CentimetersToPoints(1.75)");
					// .PageWidth = CentimetersToPoints(29.7)
					params[i++] = new Variant("CentimetersToPoints(" + width
							+ ")");
					// .PageHeight = CentimetersToPoints(21)
					params[i++] = new Variant("CentimetersToPoints(" + height
							+ ")");
					// .FirstPageTray = wdPrinterDefaultBin
					params[i++] = new Variant("wdPrinterDefaultBin");
					// .OtherPagesTray = wdPrinterDefaultBin
					params[i++] = new Variant("wdPrinterDefaultBin");
					// .SectionStart = wdSectionNewPage
					params[i++] = new Variant("wdSectionNewPage");
					// .OddAndEvenPagesHeaderFooter = False
					params[i++] = new Variant(false);
					// .DifferentFirstPageHeaderFooter = False
					params[i++] = new Variant(false);
					// .VerticalAlignment = wdAlignVerticalTop
					params[i++] = new Variant("wdAlignVerticalTop");
					// .SuppressEndnotes = False
					params[i++] = new Variant(false);
					// .MirrorMargins = False
					params[i++] = new Variant(false);
					// .TwoPagesOnOne = False
					params[i++] = new Variant(false);
					// .BookFoldPrinting = False
					params[i++] = new Variant(false);
					// .BookFoldRevPrinting = False
					params[i++] = new Variant(false);
					// .BookFoldPrintingSheets = 1
					params[i++] = new Variant(1);
					// .GutterPos = wdGutterPosLeft
					params[i++] = new Variant("wdGutterPosLeft");
					// .LayoutMode = wdLayoutModeLineGrid
					params[i++] = new Variant("wdLayoutModeLineGrid");
					getFunctionResult(selectOleAutomation, "PageSetup", params);
				}
			});
		}
		Map<String, String> retMap = new HashMap<String, String>();
		Variant end = result.get("end");
		if (end == null) {
			retMap.put("end", "0");
		} else {
			retMap.put("end", end.getString());
		}
		Variant start = result.get("start");
		if (start == null) {
			retMap.put("start", "0");
		} else {
			retMap.put("start", start.getString());
		}
		return retMap;
	}

	;

	/**
	 * 选定整个文档
	 * 
	 * @return：长度
	 */
	public boolean selectionWholeStory() {
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation document = getWordApplication();
					OleAutomation selectOleAutomation = getSubOleAutomation(
							document, "Selection");
					// 选择所有文档内容，类似于CTR+A
					getFunctionResult(selectOleAutomation, "WholeStory");
				}
			});
		}
		return true;
	}

	;

	/**
	 * 根据传入的起始位置和结束位置返回指定的区域对象
	 * 
	 * @return：
	 */
	public OleAutomation getOleSelectionRange(final int intStart,
			final int intEnd) {
		if (intStart < 0 || intEnd < 0) {
			OLE.error(OLE.ERROR_APPLICATION_NOT_FOUND);
			return null;
		}
		final Map<String, OleAutomation> result = new HashMap<String, OleAutomation>();
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					OleAutomation activeDocument = getWordActiveDocument();
					Variant[] params = new Variant[2];
					params[0] = new Variant(intStart);
					params[1] = new Variant(intEnd);
					Variant pVarRange = getFunctionResult(activeDocument,
							"Range", params);
					OleAutomation rangeOleAutomation = pVarRange
							.getAutomation();
					result.put("result", rangeOleAutomation);
				}
			});
		}
		OleAutomation variant = result.get("result");
		return variant;
	}

	;

	/**
	 * 为选定的区域添加可编辑用户
	 * 
	 * @return：长度
	 */
	public boolean addOleRangeEditor(
			final OleAutomation rangeEditorsOleAutomation, final int type) {
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					Variant[] params = new Variant[1];
					params[0] = new Variant(type);
					getFunctionResult(rangeEditorsOleAutomation, "Add", params);
				}
			});
		}
		return true;
	}

	;

	/**
	 * 把光标向后移动一定位置。
	 * 
	 * @return：长度
	 */
	public boolean moveCursorLeft(final OleAutomation selectOleAutomation,
			final int moveLeftLength) {
		if (moveLeftLength <= 0) {
			return true;
		}
		if (display != null && !display.isDisposed()) {
			display.syncExec(new Runnable() {
				public void run() {
					Variant[] params = new Variant[2];
					params[0] = new Variant(1);
					params[1] = new Variant(moveLeftLength);
					getFunctionResult(selectOleAutomation, "MoveLeft", params);
				}
			});
		}
		return true;
	}

	;

	/**
	 * 电子签章
	 */
	public void InsertSign() {
		display.syncExec(new Runnable() {
			public void run() {
				OleAutomation document = getWordActiveDocument();
				int[] appId = document
						.getIDsOfNames(new String[] { "FullName" });
				Variant pVarResult = document.getProperty(appId[0]);
				// OleAutomation wordAppAutomation = getWordApplication();
				ActiveXComponent comx = new ActiveXComponent(
						"TZWordAddInSignToolBar.TZSToolBar");
				Dispatch myCom = (Dispatch) comx.getObject();
				// Variant f = new Variant(wordAppAutomation);
				com.jacob.com.Variant Res;
				Res = Dispatch.call(myCom, "SetDocByName",
						pVarResult.toString());
				if (Res.getBoolean() == true) {
					Dispatch.call(myCom, "TZ_InsertSign");
				}
			}
		});
	}

	public File getOpenFile() {
		return openFile;
	}

	public void setOpenFile(File openFile) {
		this.openFile = openFile;
	}

	private boolean isEnd = false;
	private boolean isHas = false;

	public boolean hasBookMarks(final String bookMarkName) {
		isEnd = false;
		display.syncExec(new Runnable() {
			public void run() {
				// 获得书签
				OleAutomation activeDocument = getWordActiveDocument();
				OleAutomation bookMarksAutomation = getSubOleAutomation(
						activeDocument, "Bookmarks");
				Variant[] param = new Variant[1];
				param[0] = new Variant(bookMarkName);
				// 判断书签是否存在
				Variant pVarResult = getFunctionResult(bookMarksAutomation,
						"Exists", param);
				// 如果存在,选择此item
				isHas = pVarResult.getBoolean();
				isEnd = true;
			}
		});
		int i = 0;
		while (!isEnd || i++ > 1000) {
		}
		return isHas;
	}

	public int hashCode() {
		return super.hashCode();
	}
}
