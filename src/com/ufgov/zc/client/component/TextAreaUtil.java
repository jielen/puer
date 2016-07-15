package com.ufgov.zc.client.component;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

import com.ufgov.zc.client.common.AsOptionMeta;

public class TextAreaUtil {

  public static void AddRightMouseUtil(final JTextComponent textComponent) {
    if (AsOptionMeta.getOptVal("OPT_ZC_TEXTAREA_RIGHT_MOUSEEVENT").equals("Y")) {

      final JPopupMenu pop = new JPopupMenu(); // 弹出菜单

      final JMenuItem copy = new JMenuItem("复制");

      final JMenuItem paste = new JMenuItem("粘贴");

      final JMenuItem cut = new JMenuItem("剪切");
      textComponent.addMouseListener(new MouseListener() {
        public void mouseReleased(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
          if (e.getButton() == MouseEvent.BUTTON3) {
            copy.setEnabled(isCanCopy(textComponent));
            paste.setEnabled(isClipboardString(textComponent) && textComponent.isEditable());
            cut.setEnabled(isCanCopy(textComponent) && textComponent.isEditable());
            pop.show(textComponent, e.getX(), e.getY());
          }
        }

        public void mouseExited(MouseEvent e) {
          // TCJLODO Auto-generated method stub

        }

        public void mouseEntered(MouseEvent e) {
          // TCJLODO Auto-generated method stub

        }

        public void mouseClicked(MouseEvent e) {
          // TCJLODO Auto-generated method stub

        }
      });

      pop.add(copy);
      pop.add(paste);
      pop.add(cut);
      copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
      paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
      cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
      copy.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          action(e, textComponent);
        }
      });
      paste.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          action(e, textComponent);
        }
      });
      cut.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          action(e, textComponent);
        }
      });
      textComponent.add(pop);

    }

  }

  /**
   * 菜单动作
   * @param e
   */
  public static void action(ActionEvent e, JTextComponent textComponent) {
    String str = e.getActionCommand();
    if (str.equals("复制")) { // 复制
      textComponent.copy();
    } else if (str.equals("粘贴")) { // 粘贴
      textComponent.paste();
    } else if (str.equals("剪切")) { // 剪切
      textComponent.cut();
    }
  }

  /**
   * 剪切板中是否有文本数据可供粘贴
   * 
   * @return true为有文本数据
   */
  public static boolean isClipboardString(JTextComponent textComponent) {
    boolean b = false;
    Clipboard clipboard = textComponent.getToolkit().getSystemClipboard();
    Transferable content = clipboard.getContents(textComponent);
    try {
      if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
        b = true;
      }
    } catch (Exception e) {
    }
    return b;
  }

  /**
   * 文本组件中是否具备复制的条件
   * 
   * @return true为具备
   */
  public static boolean isCanCopy(JTextComponent textComponent) {
    boolean b = false;
    int start = textComponent.getSelectionStart();
    int end = textComponent.getSelectionEnd();
    if (start != end)
      b = true;
    return b;
  }

}
