/**
 * 
 */
package com.ufgov.zc.client.sf.entrust;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ufgov.zc.client.common.AsOptionMeta;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.client.sf.util.SfBookmarkUtil;
import com.ufgov.zc.client.util.ChangeRMB;
import com.ufgov.zc.client.util.freemark.StringUtil;
import com.ufgov.zc.client.util.freemark.WordHandlerAdapter;
import com.ufgov.zc.common.sf.model.SfChargeDetail;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.sf.model.SfXysx;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.util.EmpMeta;

/**
 * 委托相关word freemark处理基类
 * @author Administrator
 *
 */
public abstract class SfEntrustWordPrintBasicHandler  extends WordHandlerAdapter {

	  //  String templateFileId = "sf_agreement_template";

	  

	  @Override
	  public Map<String, Object> initTemplateData(Map<String, Object> sourceMap) {
	    Map<String, Object> dataMap = new HashMap<String, Object>();

	    SfEntrust entrust = (SfEntrust) sourceMap.get("entrust");

	    if (entrust.getEntrustor() == null) {
	      entrust.setEntrustor(new SfEntrustor());
	    }
	    //页眉、页脚
	    /*dataMap.put("bh", StringUtil.freeMarkFillWordChar("编号KPTJ-471-14"));
	    dataMap.put("bb", StringUtil.freeMarkFillWordChar("第1版"));
	    dataMap.put("xd", StringUtil.freeMarkFillWordChar("第0次修订"));  */  

	    //页眉信息
	    setYmInfo(dataMap,entrust);

	    //鉴定机构信息
	    setJdjgInfo(dataMap,entrust);

	    //委托编号
	    setEntrustCode(dataMap,entrust);

	    //委托方信息
	    setEntrustorInfo(dataMap,entrust.getEntrustor());
	    
	    //受理信息
	    setShouLiInfo(dataMap,entrust);
	    
	    //送检人信息 
	    setSongJianRenInfo(dataMap,entrust);
	    
	    //案件信息
	    setAnJianInfo(dataMap,entrust);
	    //检案摘要
	    setJianAnZhaiYao(dataMap,entrust);
	    
	    //鉴定对象
	    setJdTarget(dataMap,entrust.getJdTarget());
	    
	    //是否二次鉴定
	    setIsErCiJianDing(dataMap,entrust);
	    
	    //鉴定材料
	    setMaterialsInfo(dataMap,entrust);

	    //鉴定要求
	    setJianDingYaoQiu(dataMap,entrust);
	    
	    //鉴定费用   
//	    setCharges(dataMap, entrust.getJdChargeDetaillst());
	    
	    

	    //鉴定文件发送方式
	    setReportSendInfo(dataMap,entrust);

	    //协议事项
	    setXysx(dataMap, entrust);

	    //备注
	    setRemarkInfo(dataMap,entrust);
	    return dataMap;
	  }

	   

	  protected void setEntrustCode(Map<String, Object> dataMap, SfEntrust entrust) {
		    dataMap.put("WTBH", StringUtil.freeMarkFillWordChar(entrust.getCode()));
	}

	protected void setRemarkInfo(Map<String, Object> dataMap, SfEntrust entrust) {
		    dataMap.put("REMARK", StringUtil.freeMarkFillWordChar(entrust.getRemark()));
	}

	protected void setReportSendInfo(Map<String, Object> dataMap,			SfEntrust entrust) {
		    String docFh1 = StringUtil.FU_HAO_KUANG, docFh2 = StringUtil.FU_HAO_KUANG, docFh3 = StringUtil.FU_HAO_KUANG;
		    String docDz = "", docFz = "";
		    if (SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE_ZIQU.equalsIgnoreCase(entrust.getJdDocSendType())) {
		      docFh1 = StringUtil.FU_HAO_GOU;
		    } else if (SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE_YOUJI.equalsIgnoreCase(entrust.getJdDocSendType())) {
		      docFh2 = StringUtil.FU_HAO_GOU;
		      docDz = entrust.getJdDocSendTypeFz();
		    } else if (SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE_QITA.equalsIgnoreCase(entrust.getJdDocSendType())) {
		      docFh3 = StringUtil.FU_HAO_GOU;
		      docFz = entrust.getJdDocSendTypeFz();
		    }
		    dataMap.put("DOCFH1", StringUtil.freeMarkFillWordChar(docFh1));
		    dataMap.put("DOCFH2", StringUtil.freeMarkFillWordChar(docFh2));
		    dataMap.put("DOCFH3", StringUtil.freeMarkFillWordChar(docFh3));
		    dataMap.put("DOCDZ", StringUtil.freeMarkFillWordChar(docDz));
		    dataMap.put("DOCFZ", StringUtil.freeMarkFillWordChar(docFz));
	}

	  protected void setJianDingYaoQiu(Map<String, Object> dataMap,			SfEntrust entrust) {
		    dataMap.put("JDYQ", StringUtil.freeMarkFillWordChar(entrust.getJdRequire()));
	}

	protected void setMaterialsInfo(Map<String, Object> dataMap, SfEntrust entrust) {
		    /*StringBuffer sb = new StringBuffer();
		    if (entrust.getMaterials() == null) {
		      entrust.setMaterials(new ArrayList());
		    }
		    StringBuffer jcSb=new StringBuffer();
		    StringBuffer ybSb=new StringBuffer(); 
		    for (int i = 0; i < entrust.getMaterials().size(); i++) {
		    	//先分出检材和样本，如果没有，就混在一起
		      SfMaterials material = (SfMaterials) entrust.getMaterials().get(i);
		      
		      if(material.getMaterialType()!=null && material.getMaterialType().equalsIgnoreCase("1")){//检材
		    	  if(jcSb.length()>0){
		    		  jcSb.append("\n");
		    	  }
		    	  jcSb.append(material.toString());	    	  
		      }else if(material.getMaterialType()!=null && material.getMaterialType().equalsIgnoreCase("2")){//样本
		    	  if(ybSb.length()>0){
		    		  ybSb.append("\n");
		    	  }
		    	  ybSb.append(material.toString());
		      }else{
		    	 if(sb.length()>0){
		    		 sb.append("\n");
		    	 }
		    	 sb.append(material.toString());
		      }
	     
		    }
		   	 if(sb.length()>0){
				 sb.append("\n");
			 }
			 sb.append(jcSb.toString());
			 if(sb.length()>0){
				 sb.append("\n");
			 }
		     sb.append(ybSb.toString()); */	
		    dataMap.put("JDCL", StringUtil.freeMarkFillWordChar(SfBookmarkUtil.getJdclString(entrust)));
	}

	protected void setIsErCiJianDing(Map<String, Object> dataMap,			SfEntrust entrust) {
		    String cxjd = "否";
		    String scjdwh = "";
		    if (entrust.getIsCxjd() != null && entrust.getIsCxjd().trim().equalsIgnoreCase("y")) {
		      cxjd = "是";
		      scjdwh=getScjdWh(entrust.getWtIdParent());
		    }
		    dataMap.put("SFCXJD", cxjd);
		    dataMap.put("SCJDJGWH", scjdwh);
	}

	protected void setJianAnZhaiYao(Map<String, Object> dataMap, SfEntrust entrust) {
		    dataMap.put("JAZY", StringUtil.freeMarkFillWordChar(entrust.getBrief()));
	}

	protected void setAnJianInfo(Map<String, Object> dataMap, SfEntrust entrust) {
		    dataMap.put("ASJMC", StringUtil.freeMarkFillWordChar(entrust.getName()));
		    dataMap.put("AJBH", StringUtil.freeMarkFillWordChar(""));
	}

	protected void setSongJianRenInfo(Map<String, Object> dataMap,			SfEntrust entrust) {
		    dataMap.put("SJR1", StringUtil.freeMarkFillWordChar(entrust.getSjr()));
		    dataMap.put("ZJMC1", StringUtil.freeMarkFillWordChar(entrust.getSjrZjType()));
		    dataMap.put("ZJHM1", StringUtil.freeMarkFillWordChar(entrust.getSjrZjCode()));
		    dataMap.put("SJR2", StringUtil.freeMarkFillWordChar(""));
		    dataMap.put("ZJMC2", StringUtil.freeMarkFillWordChar(""));
		    dataMap.put("ZJHM2", StringUtil.freeMarkFillWordChar(""));
		    dataMap.put("SJRDH", StringUtil.freeMarkFillWordChar(entrust.getSjrTel()==null?entrust.getEntrustor().getLinkTel():entrust.getSjrTel()));
		    dataMap.put("WTFCZ", StringUtil.freeMarkFillWordChar(""));
	}

	protected void setShouLiInfo(Map<String, Object> dataMap, SfEntrust entrust) {
	    dataMap.put("SLR", StringUtil.freeMarkFillWordChar(entrust.getAcceptor()==null?"":EmpMeta.getEmpName(entrust.getAcceptor())));	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    dataMap.put("SLRQ", df.format(entrust.getAcceptDate()));
	}

	protected void setEntrustorInfo(Map<String, Object> dataMap,	SfEntrustor entrustor) {

	    dataMap.put("WTF", StringUtil.freeMarkFillWordChar(entrustor.getName()));
	    dataMap.put("LXR", StringUtil.freeMarkFillWordChar(entrustor.getLinkMan()));
	    dataMap.put("WTFDZ", StringUtil.freeMarkFillWordChar(entrustor.getAddress()));
	    dataMap.put("WTFYB", StringUtil.freeMarkFillWordChar(entrustor.getZip()));
	    dataMap.put("LXDH", StringUtil.freeMarkFillWordChar(entrustor.getLinkTel()));
	}

	protected void setJdjgInfo(Map<String, Object> dataMap, SfEntrust entrust) {
	    String jgmc = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME);
	    dataMap.put("JGMC", StringUtil.freeMarkFillWordChar(jgmc));

	    String jgxkz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ);
	    dataMap.put("XKZH", StringUtil.freeMarkFillWordChar(jgxkz));

	    String jgdz = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS);
	    dataMap.put("JGDZ", StringUtil.freeMarkFillWordChar(jgdz));

	    String jgyb = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP);
	    dataMap.put("JGYB", StringUtil.freeMarkFillWordChar(jgyb));

	    String jgdh = AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL);
	    dataMap.put("JGDH", StringUtil.freeMarkFillWordChar(jgdh));
	    
	}

	protected void setYmInfo(Map<String, Object> dataMap, SfEntrust entrust) {
	    dataMap.put("bh", StringUtil.freeMarkFillWordChar(""));
	    dataMap.put("bb", StringUtil.freeMarkFillWordChar(""));
	    dataMap.put("xd", StringUtil.freeMarkFillWordChar(""));	    
	}

	protected void setJdTarget(Map<String, Object> dataMap, SfJdTarget jdTarget) {
		if(jdTarget==null)jdTarget=new SfJdTarget();
		dataMap.put("BJDRXM", jdTarget.getName());
		if(jdTarget.getSex()!=null){
			dataMap.put("XB", AsValDataCache.getName(SfElementConstants.VS_SEX, jdTarget.getSex()));
		}else{
			dataMap.put("XB","");
		}
		if(jdTarget.getAge()!=null){
			dataMap.put("NN", ""+jdTarget.getAge().intValue());
		}else{
			dataMap.put("NN", "");
		}
		if(jdTarget.getPhone()!=null){
			dataMap.put("BJDRDH", ""+jdTarget.getPhone());
		}else{
			dataMap.put("BJDRDH", "");
		}
		if(jdTarget.getAddress()!=null){
			dataMap.put("BDJRDZ", ""+jdTarget.getAddress());
		}else{
			dataMap.put("BDJRDZ", "");
		}
		dataMap.put("BJDRDW", "");
	}

	/**
	   * 获取上次鉴定的文号
	   * @param bigDecimal
	   * @return
	   */
	  private String getScjdWh(BigDecimal bigDecimal) {
		return null;
	}

	  protected void setXysx(Map<String, Object> dataMap, SfEntrust entrust) {
	    // TCJLODO Auto-generated method stub
	    HashMap<BigDecimal, String> xysxMaps = new HashMap<BigDecimal, String>();
	    if (entrust.getXysxLst() != null) {
	      for (int i = 0; i < entrust.getXysxLst().size(); i++) {
	        SfXysx sx = (SfXysx) entrust.getXysxLst().get(i);
	        xysxMaps.put(sx.getXysxTypeId(), sx.getInputContent());
	      }
	    }
	    for (int i = 1; i <= 17; i++) {
	      String valName = "SX" + i;
	      dataMap.put(valName, StringUtil.freeMarkFillWordChar(getXysxVal(i, xysxMaps)));
	    }
	  }

	  private String getXysxVal(int i, HashMap<BigDecimal, String> xysxMaps) {
	    // TCJLODO Auto-generated method stub
	    String val = xysxMaps.get(new BigDecimal(i));
	    if (isCheckBox(i)) {
	      if ("Y".equalsIgnoreCase(val)) {
	        val = StringUtil.FU_HAO_GOU;
	      } else {
	        val = StringUtil.FU_HAO_KUANG;
	      }
	    }
	    return val;
	  }

	  private boolean isCheckBox(int i) {
	    if (i == 1 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i == 14) {
	      return true;
	    }
	    return false;
	  }

	  /**
	   * 鉴定费用   
	   * @param dataMap
	   * @param sourceMap
	   */
	  protected void setCharges(Map<String, Object> dataMap, List<SfChargeDetail> jdChargeDetailLst) {

	    //鉴定费用    
	    BigDecimal totalCharge = new BigDecimal(0);
	    BigDecimal txjeTotal = new BigDecimal(0);

	    List sfmxLst = new ArrayList();

	    if (jdChargeDetailLst != null || jdChargeDetailLst.size() > 0) {
	      for (SfChargeDetail detail : jdChargeDetailLst) {
	        totalCharge = totalCharge.add(detail.getTotalPrice());
	        SFMX sfmx = new SFMX();
	        String mc = detail.getChargeStandardName();
	        if (mc == null || mc.trim().length() == 0) {//不是标准收费，是特殊项目
	          txjeTotal = txjeTotal.add(detail.getTotalPrice() == null ? new BigDecimal(0) : detail.getTotalPrice());
	          continue;
	        }
	        sfmx.setSfxm(StringUtil.freeMarkFillWordChar(mc));
	        if (SfChargeDetail.PRICE_TYPE_BIAOZHUN.equalsIgnoreCase(detail.getPriceType())) {
	          sfmx.setBzfh(StringUtil.FU_HAO_GOU);
	          sfmx.setXyfh(StringUtil.FU_HAO_KUANG);
	        } else {
	          sfmx.setBzfh(StringUtil.FU_HAO_KUANG);
	          sfmx.setXyfh(StringUtil.FU_HAO_GOU);
	        }
	        double je = detail.getTotalPrice() == null ? 0.0 : detail.getTotalPrice().doubleValue();
	        sfmx.setSfje("" + je);

	        sfmxLst.add(sfmx);

	      }
	    }

	    if (sfmxLst.size() == 0) {//为空时，建立空白的数据，以便替换模板上的变量
	      SFMX sfmx = new SFMX();
	      sfmx.setSfxm("");
	      sfmx.setBzfh(StringUtil.FU_HAO_KUANG);
	      sfmx.setXyfh(StringUtil.FU_HAO_KUANG);
	      sfmx.setSfje("");
	      sfmxLst.add(sfmx);
	    }

	    dataMap.put("jdsf", sfmxLst);
	    if (txjeTotal.doubleValue() > 0) {
	      dataMap.put("txfh", StringUtil.FU_HAO_GOU);
	      dataMap.put("txjdsf", "" + txjeTotal.doubleValue());
	    } else {
	      dataMap.put("txfh", StringUtil.FU_HAO_KUANG);
	      dataMap.put("txjdsf", "");
	    }

	    dataMap.put("zje", "" + totalCharge.doubleValue());
	    dataMap.put("zjedx", "" + ChangeRMB.doChange("" + totalCharge.doubleValue()));
	  }

	  public class SFMX {
	    String sfxm;//收费项目

	    String bzfh;//标准符号

	    String xyfh;//协议符号

	    String sfje;//收费金额

	    public String getSfxm() {
	      return sfxm;
	    }

	    public void setSfxm(String sfxm) {
	      this.sfxm = sfxm;
	    }

	    public String getBzfh() {
	      return bzfh;
	    }

	    public void setBzfh(String bzfh) {
	      this.bzfh = bzfh;
	    }

	    public String getXyfh() {
	      return xyfh;
	    }

	    public void setXyfh(String xyfh) {
	      this.xyfh = xyfh;
	    }

	    public String getSfje() {
	      return sfje;
	    }

	    public void setSfje(String sfje) {
	      this.sfje = sfje;
	    }
	  }
	}