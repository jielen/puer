package com.ufgov.zc.client.zc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created with IntelliJ IDEA.
 * User: qianmingjin
 * Date: 12-7-24
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
public class FreemarkUtil {
    private static Configuration freemarker_cfg = null;
    
    private static Logger log = Logger.getLogger(FreemarkUtil.class);
    /**
     * 创建多级目录
     *
     * @param path String
     * @return boolean 是否成功
     */
    private static boolean creatDirs(String path) {
        File aFile = new File(path);
        if (!aFile.exists()) {
            return aFile.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * 生成静态文件.
     *
     * @param templateFileName 模板文件名,相对htmlskin路径,例如"/tpxw/view.ftl"
     * @param propMap          用于处理模板的属性Object映射
     * @param fileFullName     要生成的文件全名
     * @param templateFilePath 模板路径
     * @return boolean true代表生成文件成功
     */
    public static void geneTempLateFile(String templateFileName, Object object,
                              String fileFullName, String templateFilePath) throws Exception {

        try {
            Template t = getFreeMarkerCFG(templateFilePath).getTemplate(
                    templateFileName);

            File afile = new File(fileFullName);
            // 如果根路径存在,则递归创建子目录
            creatDirs(afile.getParent());

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(afile),"UTF-8"));
            t.process(object, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            log.error(e);
            throw e;
        } catch (IOException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    /**
     * 获取freemarker的配置. freemarker本身支持classpath,目录和从ServletContext获取.
     *
     * @param templateFilePath 获取模板路径
     * @return Configuration 返回freemaker的配置属性
     * @throws Exception
     */
    private static Configuration getFreeMarkerCFG(String templateFilePath)
            throws Exception {
        if (null == freemarker_cfg) {

            freemarker_cfg = new Configuration();
            freemarker_cfg.setDefaultEncoding("UTF-8");
            try {
                freemarker_cfg.setDirectoryForTemplateLoading(new File(
                        templateFilePath));
            } catch (Exception ex) {
                throw ex;
            }
        }
        return freemarker_cfg;
    }
}
