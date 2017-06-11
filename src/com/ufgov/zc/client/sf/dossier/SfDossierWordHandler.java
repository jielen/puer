/**
 * 
 */
package com.ufgov.zc.client.sf.dossier;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.sf.util.SfUtil;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfDossier;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;

/**
 * @author Administrator
 *
 */
public class SfDossierWordHandler  extends WordHandlerAdapter {

  @Override
  public String getTemplateFileId() {
    // TCJLODO Auto-generated method stub
    return "sf_dossier_template";
  }

  @Override
  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
    Map<String, Object> dataMap = new HashMap<String, Object>();

    SfDossier dossier = (SfDossier) sourceMap.get("dossier");

    SfEntrust entrust = (SfEntrust) sourceMap.get("entrust");

    if (entrust.getEntrustor() == null) {
      entrust.setEntrustor(new SfEntrustor());
    }
    
    RequestMeta meta=WorkEnv.getInstance().getRequestMeta();
    SfUtil su=new SfUtil();

    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
    dataMap.put("JGMC", StringUtil.freeMarkFillWordChar(su.getJdjgInfo().getName()));
    dataMap.put("WDBH", StringUtil.freeMarkFillWordChar("编号：PEFSSD-JL-049-2017 第1版第0次修订"));

    /*String jgxkz=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ);    
    dataMap.put("jgxkz", StringUtil.freeMarkFillWordChar(jgxkz));
    
    String jgdz=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS);    
    dataMap.put("jgdz", StringUtil.freeMarkFillWordChar(jgdz));
    
    String jgyb=AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP);    
    dataMap.put("jgyb", StringUtil.freeMarkFillWordChar(jgyb));*/

    String jgdh = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL);
//    dataMap.put("jgdh", StringUtil.freeMarkFillWordChar(jgdh));

//    dataMap.put("jgdh", "12345678");
    dataMap.put("SLBH", StringUtil.freeMarkFillWordChar(entrust.getAcceptCode()));

//    dataMap.put("wtf", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getName()));
    //  dataMap.put("lxr", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkMan()));
    //  dataMap.put("lxdz", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getAddress()));
    //  dataMap.put("lxdh", StringUtil.freeMarkFillWordChar(entrust.getEntrustor().getLinkTel()));

    SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
    dataMap.put("GDRQ", df.format(SfUtil.getSysDate()));
    dataMap.put("GDR", StringUtil.freeMarkFillWordChar(meta.getSvUserName())); 

    return dataMap;
  }

}
