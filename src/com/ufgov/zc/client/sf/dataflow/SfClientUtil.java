/**
 * 
 */
package com.ufgov.zc.client.sf.dataflow;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ufgov.zc.client.common.StringToModel;
import com.ufgov.zc.client.component.ui.fieldeditor.AbstractFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.NewLineFieldEditor;
import com.ufgov.zc.client.component.zc.fieldeditor.TextAreaFieldEditor;
import com.ufgov.zc.client.sf.component.JDragPanel;
import com.ufgov.zc.common.sf.exception.SfBusinessException;

/**
 * @author Administrator
 *
 */
public class SfClientUtil {

  public JDragPanel createPanel(List<AbstractFieldEditor> editorList,int cols, Window parent) {
    // TCJLODO Auto-generated method stub

    JDragPanel panel=new JDragPanel(parent);

    panel.setLayout(new GridBagLayout());
    
    int row = 0;

    int col = 0;

    for (int i = 0; i < editorList.size(); i++) {

      AbstractFieldEditor comp = editorList.get(i);

      if (comp.isVisible()) {

        if (comp instanceof NewLineFieldEditor) {
          row++;
          col = 0;
          continue;
        } else if (comp instanceof TextAreaFieldEditor) {
          // 转到新的一行
          row++;
          col = 0;
          JLabel label = new JLabel(getLabelText(comp));
          if (comp.getMaxContentSize() != 9999) {
            label.setText(comp.getName() + "("+ comp.getMaxContentSize() + "字内)" + "*");
          }
          comp.setPreferredSize(new Dimension(150,  comp.getOccRow() * 26));
          
          panel.add(label, new GridBagConstraints(col,row, 1, 1, 1.0, 1.0, GridBagConstraints.EAST,GridBagConstraints.NONE, new Insets(4, 0, 4, 4), 0,0));

          panel.add(comp, new GridBagConstraints(col + 1,row, comp.getOccCol(), comp.getOccRow(), 1.0, 1.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, new Insets(4, 0, 4,4), 0, 0));

          // 将当前所占的行空间偏移量计算上
          row += comp.getOccRow();
          col = 0;

          continue;
        }

        JLabel label = new JLabel(comp.getName());

        comp.setPreferredSize(new Dimension(200, 23));

        panel.add(label, new GridBagConstraints(col, row, 1,1, 1.0, 1.0, GridBagConstraints.EAST,GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));

        panel.add(comp, new GridBagConstraints(col + 1, row,1, 1, 1.0, 1.0, GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5),0, 0));

        if (col == cols * 2 - 2) {
          row++;
          col = 0;
        } else {
          col += 2;
        }
      }

    }
    return panel;
  }
  public String getLabelText(AbstractFieldEditor comp) {

    StringBuffer buff = new StringBuffer();

    buff.append("<html><a>&nbsp;");

    buff.append(comp.getName());

    if (comp.getMaxContentSize() <= 0) {

      buff.append("</a></html>");

    } else {

      if (comp.getOccRow() >= 2) {

        buff.append("<br>(");

      } else {

        buff.append("(");

      }

      buff.append(comp.getMaxContentSize());

      buff.append("字内)</a></html>");

    }

    return buff.toString();

  }

  public List<SfFlowNode> loadModelNode(String xml) throws SfBusinessException {

    List<SfFlowNode> rtn=new ArrayList<SfFlowNode>();
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
//      System.out.println("==================="+this.getClass().getResource(""));
//      System.out.println("/img/sf/wenju.bmp==================="+this.getClass().getResourceAsStream("/img/sf/wenju.bmp"));
//      System.out.println("/img/sf/sf_flow_node_config.xml==================="+this.getClass().getResourceAsStream("/img/sf/sf_flow_node_config.xml"));
//      System.out.println("/img/sf/sffff.xml==================="+this.getClass().getResourceAsStream("/img/sf/sffff.xml"));
//      System.out.println("sffff.xml==================="+this.getClass().getResourceAsStream("sffff.xml"));
//      System.out.println("sf_flow_node_config.xml==================="+this.getClass().getResourceAsStream("sf_flow_node_config.xml"));
//      System.out.println("/com/ufgov/zc/client/sf/dataflow/sf_flow_node_config.xml==================="+this.getClass().getResourceAsStream("/com/ufgov/zc/client/sf/dataflow/sf_flow_node_config.xml"));
      Document doc = db.parse(this.getClass().getResourceAsStream(xml));
      
      
      Element root = doc.getDocumentElement();
      NodeList elements = root.getChildNodes();
      if (elements != null) {
        for (int i = 0; i < elements.getLength(); i++) {
          org.w3c.dom.Node elementNode = elements.item(i);
          if (elementNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
            if (elementNode.getNodeName().equalsIgnoreCase("node")) {
              String text = getStringAttribute(elementNode, "text");
              String compoId = getStringAttribute(elementNode, "compoId");
              String icon = getStringAttribute(elementNode, "icon");
              String toolTip = getStringAttribute(elementNode, "toolTip");
              String nobusi = getStringAttribute(elementNode, "nodeBusiness");
              String isMutliRecord = getStringAttribute(elementNode, "isMutliRecord");

              SfFlowNode flowNode = new SfFlowNode();
              flowNode.setCompoId(compoId);
              flowNode.setText(text);
              flowNode.setToolTip(toolTip);
              flowNode.setIcon(icon);              
              if (nobusi != null) {
                flowNode.setNodeBusiness((ISfFlowNodeBusiness) StringToModel.stringToInstance(nobusi));
              }
              if(isMutliRecord!=null && "true".equalsIgnoreCase(isMutliRecord)){
                flowNode.setMutliRecord(true);
              }
              rtn.add(flowNode);
            }
          }
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new SfBusinessException("加载图形配置文件出错:"+xml,ex);
    }
    return rtn;
  }
  private static String getStringAttribute(org.w3c.dom.Node node, String name) {
    org.w3c.dom.Node attribute = node.getAttributes().getNamedItem(name);
    if (attribute != null) {
      return attribute.getNodeValue();
    } else {
      return null;
    }
  }

  private static int getIntAttribute(org.w3c.dom.Node node, String name) {
    String value = getStringAttribute(node, name);
    if (value != null && !value.isEmpty()) {
      return Integer.valueOf(value);
    }
    return 0;
  }
}
