package com.ufgov.zc.client.sf.util;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.ImageIcon;

import com.ufgov.zc.common.sf.exception.SfBusinessException;

public class ResourceUtil {

  private ResourceUtil() {
  }

  private static Hashtable<String, ImageIcon> images = new Hashtable<String, ImageIcon>();

  public static ImageIcon getImageIcon(String imageName) throws SfBusinessException {
    if (images.containsKey(imageName)) {
      return images.get(imageName);
    }
    ImageIcon image = getIcon(imageName);
    images.put(imageName, image);
    return image;
  }

  private static ImageIcon getIcon(String name) throws SfBusinessException {
    return new ImageIcon(getImage(name));
  }

  private static Image getImage(String name) throws SfBusinessException {
    try {
      //因applet所在包进行压缩，导致读取bmp文件内容报错，因此暂时从本地读取
      String filePath = System.getenv("APPDATA") + "\\platform\\localResource\\" + name;
      //		  String filePath="C:/sfjd/downfile/"+name;
      File f = new File(filePath);
      if (f.exists()) {
        return BMPDecoder.getBMPImage(new FileInputStream(f));
      }
      return BMPDecoder.getBMPImage(ResourceUtil.class.getResourceAsStream("/img/sf/" + name));
    } catch (IOException e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
      throw new SfBusinessException("找不到文件:" + name, e);
    } catch (Exception e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
      throw new SfBusinessException("读取文件出错:" + name, e);
    }
  }

  /**
   * 图片转为内存图
   * 
   * @param image
   * @return
   */
  public static BufferedImage toBufferedImage(Image image) {
    if (image instanceof BufferedImage) {
      return (BufferedImage) image;
    }
    image = new ImageIcon(image).getImage();
    boolean hasAlpha = false;
    BufferedImage bimage = null;
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
      int transparency = Transparency.OPAQUE;
      if (hasAlpha) {
        transparency = Transparency.BITMASK;
      }
      GraphicsDevice gs = ge.getDefaultScreenDevice();
      GraphicsConfiguration gc = gs.getDefaultConfiguration();
      bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
    } catch (HeadlessException e) {
      e.printStackTrace();
    }
    if (bimage == null) {
      int type = BufferedImage.TYPE_INT_RGB;
      if (hasAlpha) {
        type = BufferedImage.TYPE_INT_ARGB;
      }
      bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
    }
    Graphics g = bimage.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return bimage;
  }

}
