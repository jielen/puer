package com.ufgov.zc.client.zc.ztb.activex;

import java.awt.Canvas;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ufgov.zc.common.sf.model.SfBookmark;

public class ExcelPane extends JPanel {
	  private static final long serialVersionUID = 882379749383014297L;
	  
	  private static Logger log=Logger.getLogger(ExcelPane.class);

	  public static final String EVENT_NAME_OPEN_CALLBACK = "open_callback";

	  public String EVENT_NAME_OPEN_FINISH = "open_finish";

	  private OleFrame frame;

	  private OleClientSite site;

//	  private OleControlSite  site;

	  private OleAutomation automation;

	  private Display display;

	  private Shell shell;

	  private Canvas canvas;

	  private File openFile;
	  

	  
	  /**
	   * 通过使用id，可以访问excel的各个组件，如sheet、cell、value等，也可以通过方法访问
	   */
	  /** 
	     * Sheet的Id
	      */ 
	     private   static   final   int  SHEET_ID  =   0x000001e5 ;
	    
	     /** 
	     * 单元格的Id
	      */ 
	     private   static   final   int  CELL_ID  =    0x000000c5 ;
	        
	     /** 
	     * 单元格值的Id
	      */ 
	     private   static   final   int  CELL_VALUE_ID  =   0x00000006 ; 

	  public ExcelPane() {
	  }

	  /**
	   * 打开excel文档，另开线程进行打开操作，使用回调函数触发打开完成。
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
	        canvas = new Canvas();
	        add(canvas);
	        display = new Display();
	        shell = SWT_AWT.new_Shell(display, canvas);
	        shell.setLayout(new FillLayout());
	        frame = new OleFrame(shell, SWT.NONE);
	        
	        try {
//	          site = new OleControlSite (frame, SWT.NONE, openFile);
	        	site = new OleClientSite (frame, SWT.NONE, openFile);
	        	
//	          site.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
	        } catch (SWTException e) {
	          String str = "Create OleClientSite Error " + e.toString();
	          System.out.println(str);
	          open_callback(false);
	          e.printStackTrace();
	          throw new RuntimeException(e);
	        }
	        //setSize(500, 500);
	        validate();
	        site.doVerb(OLE.OLEIVERB_PRIMARY);
	        automation = new OleAutomation(site);
	        open_callback(true);
	        while (shell != null && !shell.isDisposed()) {
	          if (!display.readAndDispatch())
	            display.sleep();
	        }
//	        display.close();
	        display.dispose();
	      }
	    });
	    thread.start();
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
	  public boolean isDirty(){
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
//	        	  System.out.println(file.getAbsolutePath());
	            isSuccess[0] = site.save(file, true);
	          } else {
	            isSuccess[0] = false;
	          }
	        }
	      });
	    }
	    return isSuccess[0];
	  }
	  
	  public boolean saveAs(){
		    final boolean[] isSuccess = { false };

		    JFileChooser jChooser2 = new JFileChooser();
		    jChooser2.setCurrentDirectory(new File("c:/"));//设置默认打开路径
		    jChooser2.setDialogType(JFileChooser.SAVE_DIALOG);//设置保存对话框
		    //将设置好了的两种文件过滤器添加到文件选择器中来 
		    ExcelPane.XlsFileFilter xf=new ExcelPane().new XlsFileFilter();
		    jChooser2.addChoosableFileFilter(xf);
		    
		    int index = jChooser2.showDialog(null, "保存文件");
		    if (index == JFileChooser.APPROVE_OPTION) {
		        String existsFileType=getFileType();
		        String fname = jChooser2.getSelectedFile().getAbsolutePath();
		         if(!fname.endsWith(existsFileType)){
		        	 fname=fname+"."+existsFileType;
		         }
		         File f=new File(fname);
		         if(!f.exists()){
		        	 try {
						boolean rtn=f.createNewFile(); 
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		         }
		        return save(f);
		       } 
		    return isSuccess[0];		  
	  }

	  private String getFileType() { 
		  String filename = openFile.getName();
		  String str=filename.substring(filename.lastIndexOf(".")+1);
		return str;
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
	          if (shell != null && !shell.isDisposed()) {
	            shell.dispose();
	          }
	          shell = null;
	          display.dispose();
	          display = null;
	        }
	      });
	      remove(canvas);
	      canvas = null;
	    }
	    return isSuccess[0];
	  }

 
	 

	  /**
	   * 当前是否有打开的excel
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
	   * 取到excel对象，操作excel
	   *
	   * @param clientSite
	   * @return OleAutomation
	   * @throws
	   */
	  private OleAutomation getExcelApplication() {
	    OleAutomation excelAppAutomation = getSubOleAutomation(automation, "Application");
	    return excelAppAutomation;
	  }

	  private OleAutomation getExcelActiveSheet() {
	    OleAutomation excelAppAutomation = getExcelApplication();
	    OleAutomation activeSheet = getSubOleAutomation(excelAppAutomation, "ActiveSheet");
	    return activeSheet;
	  }
	  

	  private OleAutomation getExcelActiveCell() {
	    OleAutomation excelAppAutomation = getExcelApplication();
	    OleAutomation activeCell = getSubOleAutomation(excelAppAutomation, "ActiveCell");
	    return activeCell;
	  }

	 
	  private OleAutomation getSubOleAutomation(OleAutomation oleAutomation, String name) {
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

	  private boolean setSubOleResult(OleAutomation oleAutomation, String name, String value) {
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

	  private Variant getFunctionResult(OleAutomation oleAutomation, String function, Variant[] params) {
	    int[] appId = getAppId(oleAutomation, new String[] { function });
	    return oleAutomation.invoke(appId[0], params);
	  }

	  private Variant getFunctionResult(OleAutomation oleAutomation, String function) {
	    int[] appId = getAppId(oleAutomation, new String[] { function });
	    return oleAutomation.invoke(appId[0]);
	  }

	 

	  /**
	   * 在当前单元格插入字符串内容
	   *
	   * @param text
	   */
	  public void insertTextToCurCell(final String text) {
	    if (text == null) {
	      return;
	    }
	    display.syncExec(new Runnable() {
	      public void run() { 
	        OleAutomation cell = getExcelActiveCell();
	        /*int[] appId=getAppId(cell,new String[] { "Value" });
	        cell.setProperty(appId[0],  new Variant(text)); */
	        setSubOleResult(cell, "Value", text);
	      }
	    });
	  }
	     
	     
	     /**
	      * 在命名区域插入字符串内容
	      * @param values  字符串数组
	      * @param names 命名数组
	      */
		  public void insertTextToNamedRang(final String[] values,final String[] names) {
			  if(values==null || names==null){
				  return;
			  }
			  if(values.length!=names.length){
				  return;
			  }
			  for(int i=0;i<names.length;i++){
				  insertTextToNamedRang(values[i], names[i]);
			  }
		  }
	     
	  /**
	   * 在命名区域插入字符串内容
	   *
	   * @param vale
	   */
	  public void insertTextToNamedRang(final String val,final String name) {
	    if (val == null) {
	      return;
	    }
	    display.syncExec(new Runnable() {
	      public void run() { 

	  	    OleAutomation sheet = getExcelActiveSheet(); 
	  	  // 获得Sheet页的A1单元格 
//	  	  Variant cellA1Variant  =  sheet.getProperty(CELL_ID,  new  Variant[]{ new  Variant( " A1 " )});
	  	  Variant cellA1Variant  =  sheet.getProperty(CELL_ID,  new  Variant[]{ new  Variant( name )});
	  	  if(cellA1Variant==null){
	  		  return;
	  	  }

	        OleAutomation cellA1  =  cellA1Variant.getAutomation();
	  	 // 为A1单元格赋值 
	        cellA1.setProperty(CELL_VALUE_ID,  new  Variant( val ));
	        
//	        OleAutomation ranges = getSubOleAutomation(sheet, "Range");
//	        OleAutomation namedRang = getSubOleAutomation(ranges, name);	
//	        setSubOleResult(namedRang, "Value", text);
//	        int[] appId=getAppId(namedRang,new String[] { "Value" });
//	        namedRang.setProperty(appId[0],  new Variant(text)); 
	      }
	    });
	  }


		/**
		 * 替换命名区域
		 * @param bks
		 */
		public void insertTextToNamedRangs(List<SfBookmark> bks) {
			if(bks==null)return;
			for(int i=0;i<bks.size();i++){
				SfBookmark bk=bks.get(i);
				insertTextToNamedRang(bk.getValue(),bk.getName());
			}
		}
 

	  /**
	   * 打印当前文档
	   *
	   * @return
	   */
	  public boolean print() {
      /*display.syncExec(new Runnable() {
	      public void run() {
	    	  OleAutomation sheet = getExcelActiveSheet();
	    	  log.debug(
	    	  sheet.getFunctionDescription(OLE.OLECMDID_PRINT));
	    	  String[] names=new String[1];
	    	  names[0]= "PrintOut";
	    	  int[] ids=sheet.getIDsOfNames(names);
	    	  if(ids==null){
	    		  log.debug("no ids for printout");
	    	  }else{
	    		  for(int i=0;i<ids.length;i++){
	    			  log.debug("ids="+ids[i]);
	    		  }
	    	  }
	    	  log.debug(  sheet.getIDsOfNames(names));
	    	  
//	    	  OleAutomation sheet=getExcelApplication();
 
	          Variant[] params = new Variant[8]; 
	          params[0] = null;
	          params[1] = null;
	          params[2] = null;
	          params[3] = new Variant(true);
	          params[4] = null;
	          params[5] = null;
	          params[6] = null;
	          params[7] = null;  
	          getFunctionResult(sheet, "PrintOut", params);
//	    	  getFunctionResult(sheet, "PrintPreview", null);
	      }
	    });  */
		 /* int result = site.queryStatus(OLE.OLECMDID_PRINT);
		  if ((result & OLE.OLECMDF_ENABLED) == OLE.OLECMDF_ENABLED) {
			  log.info("print function status:" +result);
			  site.exec(OLE.OLECMDID_PRINT, OLE.OLECMDEXECOPT_PROMPTUSER, null, null);
			}else{
				log.info("print function status:" +result);
				log.info("print function OLECMDF_SUPPORTED :" +OLE.OLECMDF_SUPPORTED  );
				log.info("print function OLECMDF_ENABLED:" +OLE.OLECMDF_ENABLED);
				log.info("print function OLECMDF_LATCHED :" +OLE.OLECMDF_LATCHED );
				
			}
		  
		  site.exec(OLE.OLECMDID_PRINT,OLE.OLECMDEXECOPT_PROMPTUSER, null, null); */
      site.exec(OLE.OLECMDID_PRINT,OLE.OLECMDEXECOPT_PROMPTUSER, null, null); 
		  
		  
	    return true;
	  }

	   
         

	  public File getOpenFile() {
	    return openFile;
	  }

	  public void setOpenFile(File openFile) {
	    this.openFile = openFile;
	  }
	  
	 
	  public int hashCode(){
		  return super.hashCode();
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

