package com.ufgov.zc.client.util.freemark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.zc.WordFileUtil;
import com.ufgov.zc.common.commonbiz.publish.IBaseDataServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.model.AsFile;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class WordHandlerAdapter implements IWordHandler {

  protected IZcEbBaseServiceDelegate baseService = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
    "zcEbBaseServiceDelegate");

  protected RequestMeta meta = WorkEnv.getInstance().getRequestMeta();

  protected SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

  public String createDocumnet(Hashtable userDatas) {
    // TCJLODO Auto-generated method stub
    String targetFilePath = "";

    AsFile asf = getTemplateFile(getTemplateFileId(), getRequestMeta());
    asf.setFileName(WordFileUtil.getDir() + getTemplateFileId() + ".doc");
    String modname = getTemplateFileId() + "_mod.doc";

    // 先保存模版临时文件   
    String targetFileName = (String) userDatas.get(IWordHandler.FILE_NAME);
    if (targetFileName == null) {
      targetFileName = System.currentTimeMillis() + "_rlt.doc";
    } else {
      targetFileName = targetFileName + "_rlt.doc";
    }

    boolean isSucceed = WordFileUtil.createFile(WordFileUtil.getDir(), WordFileUtil.getDir() + modname, null, asf.getFileContent());
    File srcFile = new File(WordFileUtil.getDir() + modname);
    //      File deFile=new File(WordFileUtil.getDir()+modname_new);

    //      createNewFile(srcFile, deFile, agreement.getPackList().size());

    // 要填入模本的数据文件
    Map<String, Object> dataMap = initTemplateData(userDatas);

    targetFilePath = WordFileUtil.getDir() + targetFileName;
    File f = new File(WordFileUtil.getDir());
    File agreementDocFile = new File(targetFilePath);

    Configuration configuration = new Configuration();
    configuration.setDefaultEncoding("utf-8");

    OutputStream out = null;
    Writer writer = null;
    try {
      configuration.setDirectoryForTemplateLoading(f);
      Template template = configuration.getTemplate(modname);
      out = new FileOutputStream(agreementDocFile);
      writer = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
      template.process(dataMap, writer);
      writer.flush();
    } catch (Exception e) {
      // TCJLODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {
      }
    }
    return targetFilePath;
  }

  protected AsFile getTemplateFile(String temoplateFIleId, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    IBaseDataServiceDelegate baseService = (IBaseDataServiceDelegate) ServiceFactory
      .create(IBaseDataServiceDelegate.class, "baseDataServiceDelegate");

    AsFile asfile = baseService.getAsFileById(temoplateFIleId, meta);

    return asfile;
  }

  protected IZcEbBaseServiceDelegate getBaseService() {
    return baseService;
  }

  protected RequestMeta getRequestMeta() {
    return meta;
  }

  public abstract String getTemplateFileId();

  public abstract Map<String, Object> initTemplateData(Map<String, Object> sourceMap);

  protected String formatDate(Date d) {
    if (d == null)
      return null;
    return sd.format(d);
  }
}
