/**
 * 
 */
package com.ufgov.zc.client.sf.supplier;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.ufgov.smartclient.plaf.BlueLookAndFeel;
import com.ufgov.zc.common.sf.model.SfSjSupplier;

/**
 * @author Administrator
 *
 */
public class SfSjProductorListPanel extends SfSjSupplierListPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -379570371997318609L;
	@Override
	String getSupplierType() {
		return SfSjSupplier.VS_SF_SUPPLIER_TYPE_SCS;
	}

	@Override
	public String getcompoId() {
		return "SF_SJ_PRODUCTOR";
	}
	 /**
	   * @param args
	   */
	  public static void main(String[] args) {

	    SwingUtilities.invokeLater(new Runnable() {

	      public void run() {

	        try {

	          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	          UIManager.setLookAndFeel(new BlueLookAndFeel());

	        } catch (Exception e) {

	          e.printStackTrace();

	        }
	        SfSjProductorListPanel bill = new SfSjProductorListPanel();

	        JFrame frame = new JFrame("frame");

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        frame.setSize(800, 600);

	        frame.setLocationRelativeTo(null);

	        frame.getContentPane().add(bill);

	        frame.setVisible(true);

	      }

	    });

	  }
}
