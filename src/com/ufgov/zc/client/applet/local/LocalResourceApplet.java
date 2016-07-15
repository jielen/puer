/**
 * 
 */
package com.ufgov.zc.client.applet.local;

import java.applet.Applet;
import java.awt.Window;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.JApplet;

/**
 * @author Administrator
 */
public class LocalResourceApplet extends MyBaseApplet {

  private static final Logger LOG = Logger.getLogger("LocalResourceApplet");

  private static boolean haveLoadJar = false;

  private static String oldTorken = "";

  private final String[] paramNames = new String[] { "panelClassName", "token", "clientIP", "transDate", "userId", "coCode", "orgCode", "orgPoCode", "poCode", "webRoot", "webIp", "accountId",
    "empCode", "empName", "urlArray" };

  private Window appletWindow;

  private Map parameterInfo = new HashMap();

  /**
   * 
   */
  private static final long serialVersionUID = 1401479333821197886L;

  public void init() {
    //    System.out.println("2============================================");
    super.init();
    //    System.out.println("==3==========================================");
    try {
      //      System.out.println("==4==========================================");
      initAppletParameters();
      //      System.out.println("===6=========================================");
      if (oldTorken == null || !oldTorken.equals((String) parameterInfo.get("token"))) {
        //        System.out.println("==7==========================================");
        oldTorken = (String) parameterInfo.get("token");

        downloadResource();
        //        System.out.println("==9==========================================");
        loadLocalJars();
      }
      initApplet();
    } catch (Exception e) {
      e.printStackTrace();
      LOG.info(e.getMessage());
    }
  }

  private void loadLocalJars() throws Exception {
    // TCJLODO Auto-generated method stub

    //    System.out.println("==8==========================================");
    /* File f = new File("C:/sql.txt");
     FileReader r = new FileReader(f);
     BufferedReader br = new BufferedReader(r);
     String s;
     while ((s = br.readLine()) != null) {
       System.out.println(s);
     }
     br.close();
     r.close();*/

    ClassLoaderUtil lu = new ClassLoaderUtil();
    lu.loadJarPath(DownLoadManager.localJarUrl);

    haveLoadJar = true;
  }

  private void initApplet() throws Exception {
    // TCJLODO Auto-generated method stub
    String className = "com.ufgov.zc.client.applet.LocalResouceAppletUtil";
    try {
      Class c = Class.forName(className);
      Method m[] = c.getDeclaredMethods();

      Object obj = c.newInstance();

      Object[] args = new Object[] { (Applet) this };
      final Class[] argsClass = new Class[args.length];
      for (int i = 0, j = args.length; i < j; i++) {
        argsClass[i] = args[i].getClass();
      }
      Method method = c.getMethod("initApplet", JApplet.class);
      method.invoke(obj, args);
    } catch (Exception e) {
      // TCJLODO Auto-generated catch block
      throw e;
    }
  }

  private void downloadResource() {
    // TCJLODO Auto-generated method stub
    DownLoadManager.downResource((String) parameterInfo.get("webRoot"), (String) parameterInfo.get("token"));

  }

  private void initAppletParameters() {
    //    System.out.println("==5==========================================");
    for (int i = 0; i < paramNames.length; i++) {
      parameterInfo.put(paramNames[i], this.getParameter(paramNames[i]));
      //      System.out.println(paramNames[i] + "=" + this.getParameter(paramNames[i]));
    }
    this.setProps(parameterInfo);
  }

  public static void main(String[] args) {
    LocalResourceApplet a = new LocalResourceApplet();
    try {
      //      System.out.println("==1==========================================");
      a.init();
    } catch (Exception e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
