package com.ufgov.zc.server.zc.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ufgov.zc.common.system.util.DigestUtil;

public class DigestCheckServlet extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -6869572889139391726L;

  // jdk1.4不支持ConcurrentHashMap
  // private ConcurrentHashMap cache = new ConcurrentHashMap();
  private HashMap cache2 = new HashMap();

  private synchronized Object getCache(String key) {
    return cache2.get(key);
  }

  private synchronized void addCache(String key, Object val) {
    cache2.put(key, val);
  }

  public DigestCheckServlet() {
    // TCJLODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // String resourceName = request.getParameter("resname");

    String resourcePath = request.getRealPath("") + "/resource";
    String jarPath = request.getRealPath("") + "/applet";

    String resourceResult = getFilesDigist(resourcePath, "*", request);
    String jarResutl = getFilesDigist(jarPath, "jar", request);

    StringBuffer rtn = new StringBuffer();
    if (resourceResult.length() > 0) {
      rtn.append(resourceResult);
    }
    if (jarResutl.length() > 0) {
      if (rtn.length() > 0) {
        rtn.append(",").append(jarResutl);
      } else {
        rtn.append(jarResutl);
      }
    }

    //		result = result.length() > 0 ? result.substring(1) : result;
    response.getWriter().write(rtn.toString());
    response.getWriter().flush();
    response.getWriter().close();
  }

  private String getFilesDigist(String resourcePath, String type, HttpServletRequest request) {
    // TCJLODO Auto-generated method stub
    StringBuffer rtn = new StringBuffer();
    File f = new File(resourcePath);
    if (f.exists() && f.isDirectory()) {
      File[] files = f.listFiles();
      String digistStr = "";
      for (int i = 0; i < files.length; i++) {
        //        System.out.println(files[i].getName());
        if ("*".equals(type)) {
          digistStr = getDigest(files[i].getName(), resourcePath + "/" + files[i].getName());
        } else if ("jar".equals(type) && files[i].getName().endsWith(".jar")) {
          //          System.out.println("kkkk=" + files[i].getName());
          digistStr = getDigest(files[i].getName(), resourcePath + "/" + files[i].getName());
        }
        if (digistStr != null && digistStr.trim().length() > 0) {
          if (rtn.length() > 0) {
            rtn.append(",");
          }
          rtn.append(files[i].getName()).append(":").append(digistStr);
          digistStr = "";
        }
      }
    }
    return rtn.toString();
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  private String getDigest(String resourceName, String path) {
    String result = "0";// -1: 资源不存在
    if (false) {
      result = (String) getCache(resourceName);
    } else {
      try {
        InputStream source = new FileInputStream(path);
        if (source == null) {
          result = "-1";
        } else {
          ByteArrayOutputStream bo = new ByteArrayOutputStream();
          BufferedInputStream bSource = new BufferedInputStream(source);
          byte[] buffer = new byte[1024];
          int length = bSource.read(buffer);
          while (length > 0) {
            bo.write(buffer, 0, length);
            length = bSource.read(buffer);
          }
          bSource.close();
          result = DigestUtil.digest(bo.toByteArray());
          addCache(resourceName, result);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
        result = "-2";
      }
    }
    return result;
  }
}
