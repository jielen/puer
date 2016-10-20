package com.ufgov.zc.client.sf.jdresult;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.sf.report.SfJdReportEditPanel;

public class ChangeNameDialog  extends GkBaseDialog implements MouseListener, PropertyChangeListener {
	  /**
	   * 
	   */
	  private static final long serialVersionUID = -8855523688976184357L;
	 
	  private SfJdRecordEditPanel dd;

	  private JOptionPane optionPane;

	  private String btnString1 = "确认";

	  private String btnString2 = "取消";


	  private JTextField nameField=new JTextField();
	  
	  private boolean isNew=false;
	  
	  private String name;
	  private String oldName;
	  private String menuTxt;
	  
	  private BigDecimal entrustId;
	   
	 

	  /**
	   * Returns null if the typed string was invalid; otherwise, returns the string
	   * as the user entered it.
	   */
	  public String getValidatedText() {
	    return "";
	  }

	  /** Creates the reusable dialog. */
	  public ChangeNameDialog(Dialog owner,SfJdRecordEditPanel p,boolean isNew,String oldTitle,String menuTxt) {
	    super(owner, true);
	    dd = p; 
	    this.isNew=isNew;
	    this.name=oldTitle;
	    this.oldName=oldTitle;
	    this.menuTxt=menuTxt;
	    setTitle("文档名称"); 
	    init(); 
	  }

	  private void init() {
 
		  
		    String msgString1 = "名称:";
		    nameField.setText(name);
		    nameField.setPreferredSize(new Dimension(100, 25));
		    
//		    String msgString2 = "\n";
//		    Object[] array = { msgString1, msgString2, attacheFileTable };

		    Object[] array = { msgString1, nameField };
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
		    

		    //Register an event handler that reacts to option pane state changes.
		    optionPane.addPropertyChangeListener(this);

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
	    	  
	    	  if(dd.changeName(nameField.getText(),this.oldName,this.menuTxt,isNew)){
		          clearAndHide(); 
	    	  }else{
	    		  JOptionPane.showMessageDialog(this, "请重新输入名称.", "提示", JOptionPane.INFORMATION_MESSAGE); 
	    		  nameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(251, 0, 0)));
	    		  return;
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

