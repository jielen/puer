/**
 * 
 */
package com.ufgov.zc.client.component;

import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author Administrator
 *
 */
public class IntField extends JTextField {

  public IntField() {

    this.setDocument(new intDoc(this));

    this.setColumns(25);

  }

  public IntField(int col) {

    this.setDocument(new intDoc(this));

    this.setColumns(col);

  }

  public IntField(boolean minusAllow) {

    this.setDocument(new intDoc(this, minusAllow));

    this.setColumns(25);

  }

  public void setMoney(BigDecimal d) {

    if(d==null){
      super.setText(null);
    }else {
      NumberFormat numberFormat;
      numberFormat = NumberFormat.getInstance();

      numberFormat.setMinimumFractionDigits(0);

      numberFormat.setMaximumFractionDigits(0);

      String s = numberFormat.format(d);

      super.setText(s);

    }

  }

  public BigDecimal getMoney() {

    String s = this.getText();

    if (s == null || "".equals(s.trim())) {

      return null;

    } else {

      return new BigDecimal(s);

    }

  }

}

class intDoc extends PlainDocument {

  private String regex;

  boolean minusAllow = false;

  private Toolkit toolkit = Toolkit.getDefaultToolkit();

  private JTextField textField;

  public intDoc(JTextField t) {

    super();

    this.textField = t;

    init();

  }

  public intDoc(JTextField t, boolean minusAllow) {

    super();

    this.textField = t;

    this.minusAllow = minusAllow;

    init();

  }

  private void init() {

    textField.setHorizontalAlignment(JTextField.RIGHT);

    textField.addFocusListener(new FocusListener() {

      public void focusGained(FocusEvent e) {

        String s = textField.getText();

        s = s.replace(",", "");

        textField.setText(s);

      }

      public void focusLost(FocusEvent e) {

        SwingUtilities.invokeLater(new Runnable() {

          public void run() {

            String s = textField.getText();

            if (s == null || "".equals(s.trim())) {

              textField.setText("");

              return;

            }

            s = s.replace(",", "");

            NumberFormat numberFormat;

            numberFormat = NumberFormat.getInstance();

            numberFormat.setMinimumFractionDigits(0);

            numberFormat.setMaximumFractionDigits(0);

            s = numberFormat.format(new BigDecimal(s));

            textField.setText(s);

          }

        });

      }

    });

    regex = "[0-9]*";

    if (minusAllow) {

      regex = "-?" + regex;

    }

  }

  public void insertString(int offs, String str, AttributeSet a)

  throws BadLocationException, NumberFormatException {

    if (this.textField.hasFocus()) {

      if (str == null) {

        return;

      }

      StringBuffer oldStr = new StringBuffer(this.getText(0, this.getLength()));

      StringBuffer newStr = oldStr.insert(offs, str);

      if (this.regex != null && !this.regex.trim().equals("")) {

        if (!newStr.toString().matches(this.regex)) {

          toolkit.beep();

          return;

        }

      }

      if (oldStr.length() > 15) {

        toolkit.beep();

        return;

      }

    }

    super.insertString(offs, str, a);

  }

  public void insertString2(int offs, String str, AttributeSet a)

  throws BadLocationException, NumberFormatException {

    super.insertString(offs, str, a);

  }

  public String getText(int offset, int length) throws BadLocationException {

    String s = super.getText(offset, length);

    s = s.replace(",", "");

    return s;

  }

}