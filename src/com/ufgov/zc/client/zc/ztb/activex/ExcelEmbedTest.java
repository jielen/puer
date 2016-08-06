package com.ufgov.zc.client.zc.ztb.activex;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.swtdesigner.SWTResourceManager;

public class ExcelEmbedTest {

	public static void main(final String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(592, 426);

		OleFrame frame = new OleFrame(shell, SWT.NONE);
		frame.setBounds(10, 10, 391, 185);
		final OleControlSite controlSite = new OleControlSite(frame,SWT.BORDER, new File("C:\\扬财采计[20160223]-0047号.xls"));

//		final OleClientSite controlSite = new OleClientSite(frame,SWT.BORDER, new File("C:\\扬财采计[20160223]-0047号.xls"));
		controlSite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		controlSite.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
		controlSite.exec(OLE.OLECMDID_HIDETOOLBARS,
				OLE.OLECMDEXECOPT_DODEFAULT, null, null);

		Button prinButton = new Button(shell, SWT.NONE);
		prinButton.setText("Print");
		prinButton.setBounds(115, 327, 98, 25);
		prinButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlSite.exec(OLE.OLECMDID_PRINT,
						OLE.OLECMDEXECOPT_PROMPTUSER, null, null);
			}
		});
		Button prinPreviewButton = new Button(shell, SWT.NONE);
		prinPreviewButton.setText("Print Preview");
		prinPreviewButton.setBounds(219, 327, 98, 25);
		prinPreviewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controlSite.exec(OLE.OLECMDID_PRINTPREVIEW,
						OLE.OLECMDEXECOPT_PROMPTUSER, null, null);
			}
		});
		shell.open();
		while (!shell.isDisposed()) {

			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

}
