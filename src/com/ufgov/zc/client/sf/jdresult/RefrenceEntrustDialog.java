/**
 * 
 */
package com.ufgov.zc.client.sf.jdresult;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import com.ufgov.smartclient.component.table.JGroupableTable;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfEntrustToTableModelConverter;
import com.ufgov.zc.client.common.converter.sf.SfJdResultToTableModelConverter;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.JTablePanel;
import com.ufgov.zc.client.sf.report.SfJdReportEditPanel;
import com.ufgov.zc.client.util.ListUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.publish.ISfEntrustServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.ObjectUtil;

/**
 * 菜单上引用其他的委托，如鉴定记录、鉴定报告上的引用其他委托菜单，点击时，弹出这个对话框
 * @author Administrator
 */
public class RefrenceEntrustDialog extends GkBaseDialog implements MouseListener, PropertyChangeListener {
  /**
   * 
   */
  private static final long serialVersionUID = -8855523688976184357L;
 
  private Component dd;

  private JOptionPane optionPane;

  private String btnString1 = "确认";

  private String btnString2 = "取消";
 
  
  private BigDecimal entrustId;
  
  private JTablePanel attacheFileTable = new JTablePanel();
 

  /**
   * Returns null if the typed string was invalid; otherwise, returns the string
   * as the user entered it.
   */
  public String getValidatedText() {
    return "";
  }

  /** Creates the reusable dialog. */
  public RefrenceEntrustDialog(Dialog owner,Component hook, BigDecimal entrustId) {
    super(owner, true);
    dd = hook; 
    setTitle("参照其他委托");
    this.entrustId=entrustId;
    init(); 
  }

  private void init() {

	    attacheFileTable.init();

	    attacheFileTable.setTablePreferencesKey(this.getClass().getName() + "_entrusttable");

	    attacheFileTable.getTable().setShowCheckedColumn(true);

	    attacheFileTable.getSearchBar().setVisible(true);

	    attacheFileTable.getTable().getTableRowHeader().setPreferredSize(new Dimension(50, 0));
	    
	    attacheFileTable.setTableModel(SfEntrustToTableModelConverter.convertEntrustLst(getEntrusLst()));
	    
	    attacheFileTable.setPreferredSize(new Dimension(1000, 600));
	    String msgString1 = "请选择委托:";
//	    String msgString2 = "\n";
//	    Object[] array = { msgString1, msgString2, attacheFileTable };

	    Object[] array = { msgString1, attacheFileTable };
	    Object[] options = { btnString1, btnString2 };
	    optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);

	    setContentPane(optionPane);

	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
 
	    addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent we) {
	        /*
	        * Instead of directly closing the window,
	        * we're going to change the JOptionPane's
	        * value property.
	        */
	        optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
	      }
	    });
	    
	    //Register an event handler that table row selected into the option pane.
	    attacheFileTable.getTable().addMouseListener(this);

	    //Register an event handler that reacts to option pane state changes.
	    optionPane.addPropertyChangeListener(this);

}

private List getEntrusLst() {
	ISfEntrustServiceDelegate sfEntrustServiceDelegate= (ISfEntrustServiceDelegate) ServiceFactory.create(ISfEntrustServiceDelegate.class, "sfEntrustServiceDelegate");
	ElementConditionDto dto=new ElementConditionDto();
	RequestMeta meta=WorkEnv.getInstance().getRequestMeta();
	dto.setCoCode(meta.getSvCoCode());
	dto.setSfId(entrustId);
	dto.setDattr1("refrence_menu");
	List rtn=sfEntrustServiceDelegate.getEntrustLst(dto, meta);
	return rtn==null?new ArrayList():rtn;
}

/** This method handles events for the text field. */
  public void actionPerformed(ActionEvent e) {
    optionPane.setValue(btnString1);
  }

  /** This method reacts to state changes in the option pane. */
  public void propertyChange(PropertyChangeEvent e) {
    String prop = e.getPropertyName();

    if (isVisible() && (e.getSource() == optionPane) && (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
      Object value = optionPane.getValue();

      if (value == JOptionPane.UNINITIALIZED_VALUE) {
        //ignore reset
        return;
      }

      //Reset the JOptionPane's value.
      //If you don't do this, then if the user
      //presses the same button next time, no
      //property change event will be fired.
      optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

      if (btnString1.equals(value)) { 
          JGroupableTable table = attacheFileTable.getTable();
          MyTableModel model = (MyTableModel) table.getModel();
          int row = table.getSelectedRow();
          List viewList = (List) ObjectUtil.deepCopy(ListUtil.convertToTableViewOrderList(model.getList(), table));

          SfEntrust entrust = (SfEntrust) viewList.get(row);
          clearAndHide(); 
          if(dd instanceof SfJdRecordEditPanel){
        	  SfJdRecordEditPanel recordPanel=(SfJdRecordEditPanel)dd;
        	  recordPanel.referenceEntrust(entrust);
          }else if(dd instanceof SfJdReportEditPanel){
        	  SfJdReportEditPanel reportPanel=(SfJdReportEditPanel)dd;
        	  reportPanel.referenceEntrust(entrust);
          }
           

      } else { //user closed dialog or clicked cancel 
        clearAndHide();
      }
    }
  }

  /** This method clears the dialog and hides it. */
  public void clearAndHide() { 
    setVisible(false);
  }

@Override
public void mouseClicked(MouseEvent e) {  
	if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {//双击选择数据，触发确认按钮s
	    optionPane.setValue(btnString1);
	}
}

@Override
public void mousePressed(MouseEvent e) {
}

@Override
public void mouseReleased(MouseEvent e) {
}

@Override
public void mouseEntered(MouseEvent e) {
}

@Override
public void mouseExited(MouseEvent e) {
}
}
