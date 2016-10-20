/**
 * 
 */
package com.ufgov.zc.client.component.sf.fieldeditor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;

import com.ufgov.zc.client.component.JButtonTextField;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.common.sf.model.ZcFaCardType;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

/**
 * @author Administrator
 *
 */
public class ZcFaCardTypeTreeSelectField extends JButtonTextField {
  
  private static Logger log=Logger.getLogger(ZcFaCardTypeTreeSelectField.class);
  private ZcFaCardType zcFaCardType;

  private IForeignEntityHandler handler;
  
  private ElementConditionDto selectConditionDto;
  
  public ZcFaCardTypeTreeSelectField(boolean selectedTailFlag,ElementConditionDto dto,IForeignEntityHandler handler) {
    super(20);
    this.handler=handler;
    this.selectConditionDto=dto;
    selectDialog = new ZcFaTypeSelectDialog(owner, true, this, selectedTailFlag,dto,handler);
  }

  public void setValue(Object value) {

    this.value = value;

    if (value != null) {

      ZcFaCardType data = (ZcFaCardType) value;

      this.setText("[" + data.getCode() + "]" + data.getName());

      this.setToolTipText("[" + data.getCode() + "]" + data.getName());

    } else {

      this.setText("");

      this.setToolTipText(null);

    }

    this.fireValueChanged();

  }

  public void handleClick(JButtonTextField jButtonTextField) {
    await();
    Boolean isShow = true;
    try {

      // 反射调用beforeSelect方法

      isShow = (Boolean) MethodUtils.invokeMethod(this.handler, "beforeSelect", new Object[] { this.selectConditionDto });

    } catch (Exception ex) {
      log.error("执行handler的beforeSelect方法出错"+ex.getMessage(),ex);
    }

    if(isShow){
      ((ZcFaTypeSelectDialog) selectDialog).initDataBufferList();//每次重新取数，解决不同条件时显示对应的数据。
      selectDialog.setVisible(true);
    }
  }

  public static void main(String[] args) {

    JFrame f = new JFrame();

    TextFieldEditor name = new TextFieldEditor("名称", "zcCatalogueName");

    ElementConditionDto dto=new ElementConditionDto();
    dto.setZcText1("FA_CARD_000000003");
    IForeignEntityHandler handler=new IForeignEntityHandler() {
      
      @Override
      public boolean isMultipleSelect() {
        // TCJLODO Auto-generated method stub
        return false;
      }
      
      @Override
      public void excute(List selectedDatas) {
        // TCJLODO Auto-generated method stub
        if(selectedDatas.size()>0){
        ZcFaCardType type=(ZcFaCardType) selectedDatas.get(0);        
        System.out.println(type.getFatypeCode()+type.getFatypeName());
        }else{
          System.out.println("no data selected");
        }
      }
      
      @Override
      public TableModel createTableModel(List showDatas) {
        // TCJLODO Auto-generated method stub
        return null;
      }
    };
    ZcFaCardTypeTreeSelectField code = new ZcFaCardTypeTreeSelectField(true, dto,handler);

    code.setValueByCode("2010900");

    code.setEditable(false);

    code.setEnabled(true);


    name.setEnabled(true);

    //    textField.setCompany(company);

    //    textField.setLevelCtrl(true);

    //    textField.setCtrlLevelNum(1);

    //    textField.setRandomEdit(false);

    //    textField.setPrefix("0001");

    JPanel panel = new JPanel(new GridLayout(1, 2));

    panel.add(code);

    panel.add(name);

    f.getContentPane().add(panel, BorderLayout.NORTH);

    // f.pack();

    // SwingUtilities.updateComponentTreeUI(panel);

    f.setSize(400, 300);

    f.setLocationRelativeTo(null);

    f.setVisible(true);

    f.addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent e) {

        System.exit(0);

      }

    });

  }

  public ZcFaCardType getZcFaCardType() {
    return (ZcFaCardType) this.getValue();
  }

  public void setZcFaCardType(ZcFaCardType zcFaCardType) {
    this.setValue(zcFaCardType);
  }

}
