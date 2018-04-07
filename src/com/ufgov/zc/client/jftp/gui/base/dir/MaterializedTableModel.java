package com.ufgov.zc.client.jftp.gui.base.dir;

import javax.swing.JList;
import javax.swing.table.AbstractTableModel;

/**
 * Helperklasse f�r TableUtils.generate*Model()
 *
 */
public abstract class MaterializedTableModel extends AbstractTableModel {

	protected JList list;
	
	/**
	 * Speichert eine JList auf die sp�ter in einem TableModel zugegriffen werden kann.
	 * 
	 * @param list JList
	 */
	public MaterializedTableModel(JList list) {
		super();
		
		this.list = list;  
	}
	
}
 