package com.ufgov.zc.client.jftp.gui.base.dir;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ufgov.zc.client.jftp.config.Settings;


public class DirComponent extends DirPanel implements ListSelectionListener {

	public JTable table = new JTable();
	public JLabel msgJLabel=new JLabel();
	DirPanel target;
	
	public DirComponent() {
	
		table.setDefaultRenderer(Object.class, new ColoredCellRenderer());
		table.getSelectionModel().addListSelectionListener(this);
		
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
	}
	
	public void update() {
		if(Settings.getUseJTableLayout()) {
			Vector colNames = new Vector();
			colNames.add("");
			colNames.add("内  容");
			colNames.add("大  小");
//			colNames.add("##");

			TableUtils.layoutTable(jl, table, colNames);
		}
	}

	   /**
	    * This manages the selections
	    */
	    public void valueChanged(ListSelectionEvent e)
	    {			
	        if(e.getValueIsAdjusting() == false)
	        {
	        	if(Settings.getUseJTableLayout()) {
	        		TableUtils.copyTableSelectionsToJList(jl, table);
	        	}
				
	            //  ui refresh bugfix
	            int index = jl.getSelectedIndex() - 1;

	            if((index < 0) || (dirEntry == null) || (dirEntry.length < index) ||
	                   (dirEntry[index] == null))
	            {
	                return;
	            }
	            else
	            { // -------------------- local --------------------------

	                String tgt = (String) jl.getSelectedValue().toString();

	                for(int i = 0; i < dirEntry.length; i++)
	                {
	                    dirEntry[i].setSelected(jl.isSelectedIndex(i + 1));
	                }
	            }
	        }
	    }

	
}
