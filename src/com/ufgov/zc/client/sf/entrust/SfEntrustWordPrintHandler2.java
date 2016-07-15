/**
 * 
 */
package com.ufgov.zc.client.sf.entrust;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.common.sf.model.SfEntrust;

/**
 * @author Administrator
 *
 */
public class SfEntrustWordPrintHandler2 extends SfEntrustWordPrintBasicHandler {

	/* (non-Javadoc)
	 * @see com.ufgov.zc.client.util.freemark.WordHandlerAdapter#getTemplateFileId()
	 */
	@Override
	public String getTemplateFileId() {
		return "sf_entrust_template";
	}

	@Override
	  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
		 Map<String, Object> dataMap =super.initTemplateData(sourceMap);
		 

		    SfEntrust entrust = (SfEntrust) sourceMap.get("entrust");
		    SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		    dataMap.put("WTSJ", df.format(entrust.getWtDate()));

		    dataMap.put("JDHISTORY", StringUtil.freeMarkFillWordChar(entrust.getJdHistory()));
		 return dataMap;
		
	}
}
