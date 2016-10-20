package com.ufgov.zc.client.sf.flowconsole;

import java.awt.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.ufgov.zc.client.common.StringToModel;

public class UfidaUtil {
  private static Map<String, ImageIcon> iconCache = new HashMap<String, ImageIcon>();

  public static final String NODE_BACK_GRAND_IMG_PATH = "/img/flow_console/ji_zuo.png";

  public static final String NODE_FLAG_IMG_PATH = "/img/flow_console/ds.png";

  public static final ImageIcon getImageIcon(String paramString) {
    try {
      URL localURL = UfidaUtil.class.getResource(paramString);
//      System.out.println("================================"+paramString);
      URLConnection localURLConnection = localURL.openConnection();
      byte[] arrayOfByte = getStreamBytes(localURLConnection.getInputStream());
      if ((arrayOfByte != null) && (arrayOfByte.length > 0)) {
        ImageIcon localImageIcon = new ImageIcon(arrayOfByte);
        return localImageIcon;
      }
    } catch (MalformedURLException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public static byte[] getStreamBytes(InputStream paramInputStream) {
    try {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramInputStream.available());
      int i = -1;
      while ((i = paramInputStream.read()) != -1)
        localByteArrayOutputStream.write(i);
      return localByteArrayOutputStream.toByteArray();
    } catch (IOException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
      try {
        paramInputStream.close();
      } catch (IOException e1) {
        // TCJLODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    return null;
  }

 /* public static void loadModelNode(String xml, ICanvas canvas, ActionListener nodeAction, ActionListener nodeButtonAction,
    ActionListener shortcutAction) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(UfidaUtil.class.getResource(xml).openStream());

      Element root = doc.getDocumentElement();
      NodeList elements = root.getChildNodes();
      List<String> enableIds = canvas.getEnableElements();
      if (elements != null) {
        for (int i = 0; i < elements.getLength(); i++) {
          org.w3c.dom.Node elementNode = elements.item(i);
          if (elementNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
            if (elementNode.getNodeName().equalsIgnoreCase("node")) {
              String text = getStringAttribute(elementNode, "text");
              int x = getIntAttribute(elementNode, "x");
              int y = getIntAttribute(elementNode, "y");
              String icon = getStringAttribute(elementNode, "icon");
              String genIcon = getStringAttribute(elementNode, "gen_icon");
              String highLightIcon = getStringAttribute(elementNode, "highlight_icon");
              String grayIcon = getStringAttribute(elementNode, "gray_icon");
              String tooltip = getStringAttribute(elementNode, "tooltip");
              String id = getStringAttribute(elementNode, "id");
              String nobusi = getStringAttribute(elementNode, "nodeBusiness");

              Node twaverNode = new Node();
              twaverNode.setX(x);
              twaverNode.setY(y);
              twaverNode.setGenIcon(genIcon);
              twaverNode.setHighLightIcon(highLightIcon);
              twaverNode.setGrayIcon(grayIcon);
              twaverNode.setIcon(icon);
              twaverNode.setName(text);
              twaverNode.setToolTipText(tooltip);
              twaverNode.setAction(nodeAction);
              twaverNode.setId(id);
              if (nobusi != null) {
                twaverNode.setNodeBusiness((INodeBusiness) StringToModel.stringToInstance(nobusi));
              }

              if (enableIds == null || enableIds.contains(id)) {
                readNodeButtons(elementNode, twaverNode, nodeButtonAction);
              } else {
                twaverNode.setStatus(Node.DISENABLE_STATUS);
              }
              canvas.addElement(twaverNode);
            }
            if (elementNode.getNodeName().equalsIgnoreCase("arrow")) {
              int x = getIntAttribute(elementNode, "x");
              int y = getIntAttribute(elementNode, "y");
              String direction = getStringAttribute(elementNode, "direction");
              int rotation = getIntAttribute(elementNode, "rotation");

              Link arrow = new Link();
              arrow.setX(x);
              arrow.setY(y);
              arrow.setRotation(new Double(rotation));
              arrow.setDirection(direction);
              arrow.setDiretionUrl(getArrowDirection(direction));
              canvas.addElement(arrow);
            }
          }
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }*/

  private static String getArrowDirection(String directionInXML) {
    String path = "/img/flow_console/";
    String imagName = null;
    if (directionInXML.equalsIgnoreCase("right")) {
      imagName = "ar_r.png";
    }
    if (directionInXML.equalsIgnoreCase("left")) {
      imagName = "ar_l.png";
    }
    if (directionInXML.equalsIgnoreCase("up")) {
      imagName = "ar_up.png";
    }
    if (directionInXML.equalsIgnoreCase("down")) {
      imagName = "ar_down.png";
    }
    if (directionInXML.equalsIgnoreCase("right_up")) {
      imagName = "ar_rup.png";
    }
    if (directionInXML.equalsIgnoreCase("right_down")) {
      imagName = "ar_rdown.png";
    }
    if (directionInXML.equalsIgnoreCase("left_up")) {
      imagName = "ar_lup.png";
    }
    if (directionInXML.equalsIgnoreCase("left_down")) {
      imagName = "ar_ldown.png";
    }
    return path + imagName;
  }

 /* private static void readNodeButtons(org.w3c.dom.Node moduleNode, Node twaverNode, ActionListener action) {
    NodeList buttons = moduleNode.getChildNodes();
    if (buttons != null) {
      for (int i = 0; i < buttons.getLength(); i++) {
        org.w3c.dom.Node buttonNode = buttons.item(i);
        if (buttonNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

          String tooltip = getStringAttribute(buttonNode, "tooltip");
          String icon = getStringAttribute(buttonNode, "icon");
          String genIcon = getStringAttribute(buttonNode, "gen_icon");
          String highLightIcon = getStringAttribute(buttonNode, "highlight_icon");
          String bId = getStringAttribute(buttonNode, "id");
          String offsetX = getStringAttribute(buttonNode, "offsetX");
          String offsetY = getStringAttribute(buttonNode, "offsetY");
          String param = getStringAttribute(buttonNode, "param");

          Button bt = new Button();
          bt.setToolTipText(tooltip);
          bt.setIcon(icon);
          bt.setGenIcon(genIcon);
          bt.setHighLightIcon(highLightIcon);
          bt.setOffsetX(Integer.parseInt(offsetX));
          bt.setOffsetY(Integer.parseInt(offsetY));
          bt.setId(bId);
          bt.setAction(action);
          bt.setParam(param);
          bt.setParentNode(twaverNode);
          twaverNode.addButton(bt);
        }
      }
    }
  }*/

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

  private static String getHtmlLabelText(String text) {
    if (text != null && !text.isEmpty()) {
      text = text.trim();
      while (text.contains("\\n")) {
        int index = text.indexOf("\\n");
        String leading = text.substring(0, index).trim();
        String tailing = text.substring(index + 2).trim();
        text = leading + "<br>" + tailing;
      }
      text = "<html><center>" + text + "</center></html>";

      return text;
    }
    return null;
  }

  /**
   * 坐标是否在节点内
   * @return
   */
 /* public static boolean isInNodeRect(Node node, int x, int y) {
    int rx = node.getX();
    int ry = node.getY();
    int dx = rx + node.getWidth();
    int dy = ry + node.getHeight();
    if (x >= rx && x <= dx && y >= ry && y <= dy) {
      return true;
    }
    return false;
  }*/

  /**
   * 坐标是否在按钮内
   * @param button
   * @param x
   * @param y
   * @return
   */
 /* public static boolean isInButtonRect(Button button, int x, int y) {
    int rx = button.getX();
    int ry = button.getY();
    int dx = rx + button.getWidth();
    int dy = ry + button.getHeight();
    if (x >= rx && x <= dx && y >= ry && y <= dy) {
      return true;
    }
    return false;
  }*/

  public static ImageIcon getIcon(String url) {
    if (iconCache.containsKey(url)) {
      return iconCache.get(url);
    }
    ImageIcon icon = UfidaUtil.getImageIcon(url);
    if(icon==null)return null;
    iconCache.put(url, icon);
    return icon;
  }

  public static Component getInstanceByStr(String str) {
    Component compo = null;
    try {
      compo = (Component) StringToModel.stringToInstance(str);
    } catch (ClassNotFoundException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    } catch (InstantiationException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    }
    return compo;
  }

}
