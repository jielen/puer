package com.ufgov.zc.common.zc.filemstools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 判断操作系统
 * @author liupengchao
 * @version 1.0
 */
public class fileDecrypt {

  public static final String CLASS_PATH;

  public static final boolean isLinux;
  static {
    URL resource = fileDecrypt.class.getResource("fileDecrypt.class");
    String classPath = resource.getPath();
    String className = fileDecrypt.class.getName().replace('.', '/') + ".class";
    String classesPath = classPath.substring(0, classPath.indexOf(className));
    if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1 && classesPath.startsWith("/")) {
      classesPath = classesPath.substring(1);
      isLinux = false;
    } else {
      isLinux = true;
    }
    CLASS_PATH = classesPath;
  }

  /**
   * crypto文件必须放在weblogic运行的目录下，和applications平级
   * @param execName
   * @param method
   * @param password
   * @param srcFile
   * @param dstFile
   * @return
   * @throws Exception
   */
  public static boolean cryptoFile(String execName, String method, String password, String srcFile, String dstFile) throws Exception {
    Runtime runtime = Runtime.getRuntime();//得到jvm的运行环境
    Process process;

    String cmd = execName + " " + method + " " + password + " " + srcFile + " " + dstFile;
    System.out.println(cmd);

    try {
      process = runtime.exec(cmd);//开启新进程执行命令
      try {
        process.waitFor();//当前进程等待新开启的进程执行完毕
        //        if (res != 0) {
        //          System.out.println("execute failed pleache check the system!");
        //          throw new Exception("服务器端标书原文件解密执行失败！");
        //        } else {
        //获取并打印结果
        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
          System.out.println(line);
        }
        //}
      } catch (InterruptedException e) {
        e.printStackTrace();
        throw new Exception(e.getMessage());
      } catch (IOException e) {
        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
        throw new Exception(e.getMessage());
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new Exception(e.getMessage());
    }
    return true;
  }

  public static void main(String arg[]) {
    if (!fileDecrypt.isLinux)
      return;
    //cmd :
    //  ./crypto enc password srcfile dstfile
    //  ./crypto dec password srcfile dstfile
    try {
      fileDecrypt.cryptoFile("./crypto", "enc", "12345678", "test", "test.des");
      fileDecrypt.cryptoFile("./crypto", "dec", "12345678", "test.des", "test.out");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
