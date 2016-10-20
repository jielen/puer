/**
 * 
 */
package com.ufgov.zc.client.sf.sb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.AsValComboBox;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.AsValFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.MoneyFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.client.sf.flowconsole.UfidaUtil;
import com.ufgov.zc.client.zc.ZcUtil;
import com.ufgov.zc.common.sf.model.ZcFaCard;
import com.ufgov.zc.common.sf.model.ZcFaCardStyle;
import com.ufgov.zc.common.system.constants.ZcPProBalConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.model.ZcXmcgHt;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class ZcFaCardDeprStylePanel extends JPanel {

//  protected ListCursor<ZcFaCard> listCursor;
  private ZcFaCard card;
  private ArrayList<AbstractFieldEditor> fieldEditors;
  JPanel centerPanel;

  String url="/img/fa";
  String defaultImage=url+"/default.png";
  private IZcEbBaseServiceDelegate zcEbBaseServiceDelegate ;
  
  private ZcFaCardListPanel listPanel;
  private JDialog parentDialog;
  
  private Hashtable cardTypeHst=new Hashtable();
  
  public ZcFaCardDeprStylePanel(ZcFaCardDeprStyleDialog zcFaCardDeprStyleDialog, ZcFaCardListPanel listPanel){
  
    this.listPanel=listPanel;
    this.parentDialog=zcFaCardDeprStyleDialog;
    
    card=new ZcFaCard(); 
//    card.setCaigouHt(new ZcXmcgHt());
    zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");
    init();
    refreshData();
}
  
  private void refreshData() {
  // TCJLODO Auto-generated method stub
  setDefaultValue();
  setEditingObject(card);
}

  private void setDefaultValue() {
    // TCJLODO Auto-generated method stub    
    card.setNew(true);
    card.setIsDept("A");
  }

  void init(){
    setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "选择卡片类型和对应的采购合同",
      TitledBorder.CENTER, TitledBorder.TOP,new Font("宋体", Font.BOLD, 18), Color.BLUE));
    
    JPanel headPanel=createHeadPanel();
    
    
    centerPanel=createCenterPanel();   

    this.setLayout(new BorderLayout());

    this.add(headPanel, BorderLayout.NORTH);

    this.add(centerPanel, BorderLayout.CENTER);
  }

  private JPanel createCenterPanel() {
    // TCJLODO Auto-generated method stub
    if(centerPanel==null){
      centerPanel=new JPanel();
      FlowLayout fl=new  FlowLayout(FlowLayout.CENTER, 40, 60);
      centerPanel.setLayout(fl);
    }else{
      centerPanel.removeAll();
      FlowLayout fl=new  FlowLayout(FlowLayout.CENTER, 40, 60);
      centerPanel.setLayout(fl);
    }
    
    
    if(card.getIsDept()==null || "".equals(card.getIsDept())){
      return centerPanel;
    }
    List<ZcFaCardStyle> styleLst=getCardStyle();
    if(styleLst!=null ){
      
      cardTypeHst.clear();
      for (ZcFaCardStyle zcFaCardStyle : styleLst) {
        centerPanel.add(createCardNode(zcFaCardStyle));
      }
    }
    centerPanel.validate();
    centerPanel.repaint();
    return centerPanel;
  }

  private Component createCardNode(final ZcFaCardStyle zcFaCardStyle) {
    // TCJLODO Auto-generated method stub
    String imageUrl=url+"/"+zcFaCardStyle.getStylImages();
    
    ImageIcon nodeImage=UfidaUtil.getIcon(imageUrl);
    if(nodeImage==null)nodeImage=UfidaUtil.getIcon(defaultImage);
    
 /*   JButton bt=new JButton(nodeImage);
    Dimension d=new Dimension();
    d.setSize(nodeImage.getIconWidth(), nodeImage.getIconHeight());
    bt.setPreferredSize(d);
    bt.setToolTipText(zcFaCardStyle.getCardStylName());
    
    bt.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent arg0) {
        // TCJLODO Auto-generated method stub
        doCreateCard(zcFaCardStyle);
      }
    });
    bt.setOpaque(true);*/
    
    
    
    JLabel imageLb=new JLabel();
    imageLb.setIcon(nodeImage);
    Dimension d=new Dimension();
    d.setSize(nodeImage.getIconWidth(), nodeImage.getIconHeight());
    imageLb.setPreferredSize(d);
    imageLb.setToolTipText(zcFaCardStyle.getCardStylName());
    
    imageLb.addMouseListener(new MouseListener() {
      
      @Override
      public void mouseReleased(MouseEvent arg0) {
        // TCJLODO Auto-generated method stub
        
      }
      
      @Override
      public void mousePressed(MouseEvent arg0) {
        // TCJLODO Auto-generated method stub
        
      }
      
      @Override
      public void mouseExited(MouseEvent arg0) {
        // TCJLODO Auto-generated method stub
        setCursor(Cursor.getDefaultCursor());
      }
      
      @Override
      public void mouseEntered(MouseEvent arg0) {
        // TCJLODO Auto-generated method stub
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      }
      
      @Override
      public void mouseClicked(MouseEvent e) {
        // TCJLODO Auto-generated method stub
        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) { 
          if(cardTypeHst.containsKey(e.getComponent())){
          /*  if(card.getCaigouHt().getZcHtCode()==null){
              JOptionPane.showMessageDialog(parentDialog, "请选择采购合同！", "提示", JOptionPane.INFORMATION_MESSAGE);     
              return;
            }*/
            card.setStyleCode((String) cardTypeHst.get(e.getComponent()));
            List datalst=new ArrayList();
            datalst.add(card);
            parentDialog.dispose();
            new ZcFaCardDialog(listPanel, datalst, 0);
          }
        }
      }
    });
    
    cardTypeHst.put(imageLb, zcFaCardStyle.getCardStylCode());
    
    JLabel lb=new JLabel();
    lb.setText(zcFaCardStyle.getCardStylName());
    
    JPanel p=new JPanel();
    p.setLayout(new BorderLayout());
    p.add(imageLb,BorderLayout.CENTER);
    p.add(lb,BorderLayout.SOUTH);
    
    d.setSize(nodeImage.getIconWidth()*1.2, nodeImage.getIconHeight()*1.2);
    p.setPreferredSize(d);
    return p;
  }

  protected void doCreateCard(ZcFaCardStyle zcFaCardStyle) {
    // TCJLODO Auto-generated method stub
    
  }

  private List<ZcFaCardStyle> getCardStyle() {
    // TCJLODO Auto-generated method stub
    return zcEbBaseServiceDelegate.queryDataForList("ZcFaCard.getCardStyle", card.getIsDept(), WorkEnv.getInstance().getRequestMeta());    
  }

  private JPanel createHeadPanel() {
    // TCJLODO Auto-generated method stub

    fieldEditors = new ArrayList<AbstractFieldEditor>();
    
    AsValFieldEditor cardStyleField=new AsValFieldEditor("资产类型", "isDept", "V_IS_DEPR_STYL"){  
      protected void afterChange(AsValComboBox field) {
        doCardStyleChange();
      }
      };    
    
    
    /**
     * 选择合同与供应商
     */
    String HtColumNames[] = { LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_HT_CODE),
      LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_HT_NAME), LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_MAKE_NAME),
      LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_SU_NAME), LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_HT_NUM) };
    ZcHtCodeHandler handler = new ZcHtCodeHandler(HtColumNames);
    ElementConditionDto htElementCondtiontDto=new ElementConditionDto();
    if (ZcUtil.isYsdw()) {
      htElementCondtiontDto.setCoCode(WorkEnv.getInstance().getRequestMeta().getSvCoCode());
      //这个条件，用于判断合同金额与卡片金额合计是否相等，如果相等，则过滤掉这个合同，不用选择
      htElementCondtiontDto.setZcText4("zcfacard");
    }
    htElementCondtiontDto.setNd(WorkEnv.getInstance().getRequestMeta().getSvNd());
    ForeignEntityFieldEditor htField=new ForeignEntityFieldEditor("ZC_XMCG_HT.selectBalHTList", htElementCondtiontDto, 20, handler, HtColumNames,
      LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_HT_CODE), "caigouHt.zcHtCode");

    TextFieldEditor zcHtName = new TextFieldEditor(LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_HT_NAME), "caigouHt.zcHtName");
    TextFieldEditor zcSuName = new TextFieldEditor(LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_SU_NAME), "caigouHt.zcSuName");
    MoneyFieldEditor zcHtNum = new MoneyFieldEditor(LangTransMeta.translate(ZcPProBalConstants.FIELD_TRANS_ZC_HT_NUM), "caigouHt.zcHtNum");


    fieldEditors.add(cardStyleField);
//    fieldEditors.add(htField);
//    fieldEditors.add(zcHtName);
//    fieldEditors.add(zcHtNum);
//    fieldEditors.add(zcSuName);
    
    ZcFaCardClientUtil util=new ZcFaCardClientUtil();
    
    return util.createPanel(fieldEditors, 1);
  }
  protected void doCardStyleChange() {
    // TCJLODO Auto-generated method stub
    createCenterPanel();
    this.validate();
    this.repaint();
  }

  /*
   * 选择合同的web实体
   */
  private class ZcHtCodeHandler implements IForeignEntityHandler {
    private String columNames[];

    public ZcHtCodeHandler(String columNames[]) {
      this.columNames = columNames;
    }

    public void excute(List selectedDatas) {
      for (Object object : selectedDatas) {
        ZcXmcgHt zcXmcgHt = (ZcXmcgHt) object;
//         card.setCaigouHt(zcXmcgHt);
        setEditingObject(card);
      }
    }

    public TableModel createTableModel(List showDatas) {

      Object data[][] = new Object[showDatas.size()][columNames.length];
      for (int i = 0; i < showDatas.size(); i++) {
        ZcXmcgHt zcXmcgHt = (ZcXmcgHt) showDatas.get(i);
        int col = 0;
        data[i][col++] = zcXmcgHt.getZcHtCode();
        data[i][col++] = zcXmcgHt.getZcHtName();
        data[i][col++] = zcXmcgHt.getZcPProMake().getZcMakeCode();
        data[i][col++] = zcXmcgHt.getZcSuName();
        data[i][col++] = zcXmcgHt.getZcHtNum();

      }
      MyTableModel model = new MyTableModel(data, columNames) {
        public boolean isCellEditable(int row, int colum) {
          return false;
        }
      };
      return model;
    }

    public boolean isMultipleSelect() {
      return false;
    }
  }
  public void setEditingObject(ZcFaCard card) {
    // TCJLODO Auto-generated method stub
    for (AbstractFieldEditor editor : fieldEditors) {
      editor.setEditObject(card);
    }
  }
}
