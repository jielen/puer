package com.ufgov.zc.client.zc.ztb.activex;

import java.io.File;

import org.eclipse.swt.SWT;
//import org.eclipse.swt.ole.win32.ExcelAppEvents; 
import org.eclipse.swt.internal.ole.win32.COMObject;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.ole.win32.IDispatch;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleEvent;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.OleListener;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ExcelTest {

	OleAutomation _application = null;

	public void makeExcelApplicationVisible() {
		Variant[] arguments = new Variant[1];
		arguments[0] = new Variant("true");

		int[] ids = _application.getIDsOfNames(new String[] { "Visible" });

		// Visible---true
		// excelApplication.setProperty(558, arguments);
		_application.setProperty(ids[0], arguments);
	}

	public void makeExcelApplicationEnableEvents() {
		Variant[] arguments = new Variant[1];
		arguments[0] = new Variant("true");

		int[] ids = _application.getIDsOfNames(new String[] { "EnableEvents" });

		// EnableEvent--true
		// boolean b = excelApplication.setProperty(1212, arguments);
		_application.setProperty(ids[0], arguments);
		// System.out.println(b);

	}

	public static void main(String[] args) {
		ExcelTest excel = new ExcelTest();

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setMinimized(true);
		OleFrame frame = new OleFrame(shell, SWT.NONE);
		File xlsFile = new File(
				"C:\\扬财采计[20160223]-0047号.xls");
		if (xlsFile.exists())
			System.out.println("ok, file exists");
		else
			System.out.println("xlsfile fine doesn't exist ...");

//		OleControlSite site = new OleControlSite(frame, SWT.NONE,"Excel.Application");
		OleControlSite site = new OleControlSite(frame, SWT.NONE,xlsFile);
		site.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);

		/*** try to open the xlsFile in Excel ***/
		// // prepare the xlsFile to open
		Variant xlsFileVariant = new Variant(xlsFile.getAbsolutePath());
		Variant[] openArgs = new Variant[] { xlsFileVariant };
		// // create the OleAutomation of "Excel.Application"
		excel._application = new OleAutomation(site);
		excel.makeExcelApplicationVisible();
		// get "Application"'s memebers' Id, such as the property "Workbooks"
		int[] excelIds = excel._application.getIDsOfNames(new String[] { "Workbooks" });
		// get the "Workboooks" Property
		Variant workbooksVariant = excel._application.getProperty(excelIds[0]);

		// // get the Workbooks OleAutomation so we can work with it and get its
		// // members
		OleAutomation workbooks = workbooksVariant.getAutomation();
		// get "Workbooks"'s memebers' Id, such as the function "Open"
		int[] workbooksIds = workbooks.getIDsOfNames(new String[] { "Open" });
		int workbooksOpenId = workbooksIds[0];
		// open the desired excel file
		Variant workbookVariant = workbooks.invoke(workbooksOpenId, openArgs);

		// // get the Workbook OleAutomation so we can work with it and get its
		// members
		OleAutomation workbook = workbookVariant.getAutomation();
		// // get "Workbook"'s memebers' Id, such as the property "Worksheets"
		int[] workbookIds = workbook
				.getIDsOfNames(new String[] { "Worksheets" });
		// // get the "Worksheets" Property
		Variant sheetsVariant = workbook.getProperty(workbookIds[0]);
		OleAutomation sheets = sheetsVariant.getAutomation();

		/*****************************************************/

		// get "Worksheets"'s memebers' Id, such as the property "Item"
		int[] sheetsIds = sheets.getIDsOfNames(new String[] { "Item" });
		// want the first sheet for exammple
		Variant intVariant = new Variant(1); // but can be 2 or 3 ...
		Variant[] itemArgs = new Variant[] { intVariant };
		// get the sheet ..................... :(
		Variant sheetVariant = sheets.getProperty(sheetsIds[0], itemArgs); // if(sheetVariant
																				// ==
																				// null)
		System.out.println("sheetVariant is null !!!");

		/******************************************************/

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}