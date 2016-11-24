/**
 * 
 */
package com.ufgov.zc.client.sf.util;

import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.datacache.AsValDataCache;
import com.ufgov.zc.common.sf.model.SfBookmark;
import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.sf.model.SfEntrustor;
import com.ufgov.zc.common.sf.model.SfJdPerson;
import com.ufgov.zc.common.sf.model.SfJdReport;
import com.ufgov.zc.common.sf.model.SfJdResult;
import com.ufgov.zc.common.sf.model.SfJdTarget;
import com.ufgov.zc.common.sf.model.SfJdjg;
import com.ufgov.zc.common.sf.model.SfMajor;
import com.ufgov.zc.common.sf.model.SfMaterials;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.SfElementConstants;
import com.ufgov.zc.common.system.util.DateUtil;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class SfBookmarkUtil {
	
	public static final List<String> BKMK_NAME_ENTRUST_LST=new ArrayList<String>();
	public static final List<String> BKMK_NAME_ENTRUSTOR_LST=new ArrayList<String>();
	public static final List<String> BKMK_NAME_JDTARGET_LST=new ArrayList<String>();
	
	static{ 
		//---------委托---------------
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_ACCEPTOR);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_ACCEPT_DATE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_BRIEF);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_CODE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_ENTRUSTOR_ID);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_ENTRUSTOR_NAME);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_ENTRUST_ID);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_INPUTOR);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_INPUT_DATE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_IS_ACCEPT);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_IS_CXJD);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_CHARGE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_COMPANY);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_DOC_SEND_TYPE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_DOC_SEND_TYPE_FZ);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_FHR);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_FZR);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_HISTORY);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_ORG);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_REQUIRE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_JD_TARGET_ID); 
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_MAJOR_CODE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_NAME);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_ND);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_NOT_ACCEPT_REASON);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_PROCESS_INST_ID);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_REMARK);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_SJR);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_SJR_ADDRESS);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_SJR_TEL);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_SJR_ZJ_CODE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_SJR_ZJ_TYPE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_STATUS);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_WT_DATE);
		BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_WT_ID_PARENT);
    BKMK_NAME_ENTRUST_LST.add(SfEntrust.COL_ACCEPT_CODE);
		//--------------委托方------
		BKMK_NAME_ENTRUSTOR_LST.add(SfEntrustor.ADDRESS);
		BKMK_NAME_ENTRUSTOR_LST.add(SfEntrustor.CODE);
		BKMK_NAME_ENTRUSTOR_LST.add(SfEntrustor.ENTRUSTOR_ID);
		BKMK_NAME_ENTRUSTOR_LST.add(SfEntrustor.LINK_MAN);
		BKMK_NAME_ENTRUSTOR_LST.add(SfEntrustor.LINK_TEL);
		BKMK_NAME_ENTRUSTOR_LST.add(SfEntrustor.NAME);
		BKMK_NAME_ENTRUSTOR_LST.add(SfEntrustor.ZIP);
		//----------鉴定对象 -------------
		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_ADDRESS);
		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_AGE);
		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_ID_CODE);
		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_ID_NAME);
//		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_JD_TARGET_ID);
		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_NAME);
		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_PHONE);
		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_SEX);
		BKMK_NAME_JDTARGET_LST.add(SfJdTarget.COL_ZIP);
	}
	
	public List<SfBookmark> getEntrustBookValueLst(SfEntrust bill){
		List<SfBookmark> rtn=new ArrayList<SfBookmark>();
		SfBookmark bk=new SfBookmark();
		bk.setName(SfEntrust.COL_CODE);
		bk.setValue(bill.getCode());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_CODE+"2");
		bk.setValue(bill.getCode());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_NAME);
		bk.setValue(bill.getName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_NAME+"2");
		bk.setValue(bill.getName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_BRIEF);
		bk.setValue(bill.getBrief());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_ACCEPTOR);
		bk.setValue(bill.getAcceptor()==null?"":bill.getAcceptorName());
		rtn.add(bk);

    bk=new SfBookmark();
    bk.setName(SfEntrust.COL_ACCEPT_CODE);
    bk.setValue(bill.getAcceptCode()==null?"":bill.getAcceptCode());
    rtn.add(bk);
    
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_ACCEPT_DATE);
		bk.setValue(bill.getAcceptDate() == null ? "  年    月   日" : DateUtil.dateToChinaString(bill.getAcceptDate()));
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_INPUTOR);
		bk.setValue(bill.getInputor()==null?"":bill.getInputorName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_INPUT_DATE);
		bk.setValue(bill.getInputDate() == null ? "  年    月   日" : DateUtil.dateToChinaString(bill.getInputDate()));
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_IS_ACCEPT);
		bk.setValue(AsValDataCache.getName("VS_Y/N", bill.getIsAccept()));
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_IS_CXJD);
		bk.setValue(AsValDataCache.getName("VS_Y/N", bill.getIsCxjd()));
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_JD_DOC_SEND_TYPE);
		bk.setValue(AsValDataCache.getName(SfEntrust.SF_VS_ENTRUST_DOC_SEND_TYPE, bill.getIsCxjd()));
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_JD_DOC_SEND_TYPE_FZ);
		bk.setValue(bill.getJdDocSendTypeFz());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_JD_FZR);
		bk.setValue(bill.getJdFzrName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_JD_FHR);
		bk.setValue(bill.getJdFhrName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_JD_HISTORY);
		bk.setValue(bill.getJdHistory());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_JD_REQUIRE);
		bk.setValue(bill.getJdRequire());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_MAJOR_NAME);
		bk.setValue(AsValDataCache.getName(SfMajor.SF_VS_MAJOR_pur, bill.getMajorCode()));
//		bk.setValue(bill.getMajor()==null?bill.getMajorCode():bill.getMajor().getMajorName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_ND);
		bk.setValue(bill.getNd()+"");
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_NOT_ACCEPT_REASON);
		bk.setValue(bill.getNotAcceptReason());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_REMARK);
		bk.setValue(bill.getRemark());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_SJR);
		StringBuffer sb=new StringBuffer();
		if(bill.getSjr()!=null &&bill.getSjr().trim().length()>0){
		  sb.append(bill.getSjr());
		  if(bill.getSjr2()!=null && bill.getSjr2().trim().length()>0){
		    sb.append(" ").append(bill.getSjr2());
		  }
		}else if(bill.getSjr2()!=null && bill.getSjr2().trim().length()>0){
		  sb.append(bill.getSjr2());
		} 
		bk.setValue(sb.toString());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_SJR_ADDRESS);
		bk.setValue(bill.getSjrAddress());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_SJR_TEL);
		bk.setValue(bill.getSjrTel());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_SJR_ZJ_CODE);
		bk.setValue(bill.getSjrZjCode());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_SJR_ZJ_TYPE);
		bk.setValue(bill.getSjrZjType());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_STATUS);
		bk.setValue(AsValDataCache.getName(SfEntrust.SF_VS_ENTRUST_STATUS, bill.getStatus()));
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrust.COL_WT_DATE);
		bk.setValue(bill.getWtDate() == null ? "  年    月   日" : DateUtil.dateToChinaString(bill.getWtDate()));
		rtn.add(bk);

		bk=new SfBookmark();
		bk.setName(SfEntrust.BKMK_MATERIALS);
		bk.setValue(getJdclString(bill));
		rtn.add(bk);
		
		SfJdPerson fzr=getJdPerson(bill.getJdFzr());
		String zfbh="";
		if(fzr!=null && fzr.getCertificateNo()!=null){
			zfbh=fzr.getCertificateNo();
		}
		bk=new SfBookmark();
		bk.setName(SfEntrust.BKMK_SF_ENTRUST_JD_FZR_ZSBH);
		bk.setValue(zfbh);
		rtn.add(bk);
		
		SfJdPerson fhr=getJdPerson(bill.getJdFhr());
		zfbh="";
		if(fhr!=null && fhr.getCertificateNo()!=null){
			zfbh=fhr.getCertificateNo();
		}		
		bk=new SfBookmark();
		bk.setName(SfEntrust.BKMK_SF_ENTRUST_JD_FHR_ZSBH);
		bk.setValue(zfbh);
		rtn.add(bk);
		
		
		return rtn;
	}
	

	public List<SfBookmark> getEntrustorBookValueLst(SfEntrustor bill){
		List<SfBookmark> rtn=new ArrayList<SfBookmark>();

		SfBookmark bk=new SfBookmark();
		bk.setName("WTF_ADDRESS");
		bk.setValue(bill.getAddress());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("WTF_LINK_MAN");
		bk.setValue(bill.getLinkMan());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("WTF_LINK_TEL");
		bk.setValue(bill.getLinkTel());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("WTF_NAME");
		bk.setValue(bill.getName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("WTF_NAME2");
		bk.setValue(bill.getName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfEntrustor.ZIP);
		bk.setValue(bill.getZip());
		rtn.add(bk);
		
		return rtn;
	}
	public List<SfBookmark> getJdTargetBookValueLst(SfJdTarget bill){
		List<SfBookmark> rtn=new ArrayList<SfBookmark>();

		SfBookmark bk=new SfBookmark();
		bk.setName(SfJdTarget.COL_ADDRESS);
		bk.setValue(bill.getAddress());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfJdTarget.COL_AGE);
		bk.setValue(bill.getAge()+"");
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfJdTarget.COL_ID_CODE);
		bk.setValue(bill.getIdCode());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfJdTarget.COL_ID_NAME);
		bk.setValue(bill.getIdName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfJdTarget.COL_NAME);
		bk.setValue(bill.getName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfJdTarget.COL_PHONE);
		bk.setValue(bill.getPhone());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfJdTarget.COL_SEX);
		bk.setValue(bill.getSex()==null?"":AsValDataCache.getName(SfElementConstants.VS_SEX, bill.getSex()));
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName(SfJdTarget.COL_ZIP);
		bk.setValue(bill.getZip());
		rtn.add(bk);

    bk=new SfBookmark();
    bk.setName(SfJdTarget.COL_COMPANY);
    bk.setValue(bill.getCompany());
    rtn.add(bk);
		
		return rtn;
	}

	public List<SfBookmark> getJdjgBookValueLst(String coCode){
		List<SfBookmark> rtn=new ArrayList<SfBookmark>();

		/*
		SfBookmark bk=new SfBookmark();
		bk.setName("JGMC");
		bk.setValue(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME)==null?"":AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_NAME));
		rtn.add(bk);
		bk=new SfBookmark();
		bk.setName("XKZH");
		bk.setValue(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ)==null?"":AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_XKZ));
		rtn.add(bk);
		bk=new SfBookmark();
		bk.setName("JGDH");
		bk.setValue(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL)==null?"":AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_TEL));
		rtn.add(bk);
		bk=new SfBookmark();
		bk.setName("JG_LINK_MAN");
		bk.setValue(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_LINK_MAN)==null?"":AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_LINK_MAN));
		rtn.add(bk);
		bk=new SfBookmark();
		bk.setName("JGDZ");
		bk.setValue(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS)==null?"":AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ADDRESS));
		rtn.add(bk);
		bk=new SfBookmark();
		bk.setName("JGYB");
		bk.setValue(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP)==null?"":AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_ZIP));
		rtn.add(bk);
		bk=new SfBookmark();
		bk.setName("JG_FAX");
		bk.setValue(AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_FAX)==null?"":AsOptionMeta.getOptVal(SfElementConstants.OPT_SF_JD_COMPANY_FAX));
		rtn.add(bk);
		*/

		SfJdjg jdjg=getJdjg();
		SfBookmark bk=new SfBookmark();
		bk.setName("JGMC");
		bk.setValue(jdjg.getName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JGMC2");
		bk.setValue(jdjg.getName());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JGMC_ENG");
		bk.setValue(jdjg.getEnName());
		rtn.add(bk); 
		
		bk=new SfBookmark();
		bk.setName("XKZH");
		bk.setValue(jdjg.getXkzh());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JGDH");
		bk.setValue(jdjg.getTel());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JG_LINK_MAN");
		bk.setValue(jdjg.getLinkMan());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JGDZ");
		bk.setValue(jdjg.getAddress());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JGYB");
		bk.setValue(jdjg.getZip());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JG_FAX");
		bk.setValue(jdjg.getFax());
		rtn.add(bk);
		
		return rtn;
	}

  private SfJdjg getJdjg() {
    /*IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate");
    ElementConditionDto dto=new ElementConditionDto();
    RequestMeta meta=WorkEnv.getInstance().getRequestMeta();
    dto.setCoCode(meta.getSvCoCode());
    SfJdjg jdjg=(SfJdjg) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdjgMapper.selectMainDataLst", dto, meta);
    if(jdjg==null){
      jdjg=new SfJdjg();
    }
    return jdjg;*/
   SfUtil su=new SfUtil();
   SfJdjg jg=su.getJdjgInfo();
   if(jg==null){
     jg=new SfJdjg();
   }
   return jg;
  }


  public static String getJdclString(List materials){

    StringBuffer sb = new StringBuffer();
    if(materials==null){
      return null;
    } 
      StringBuffer jcSb=new StringBuffer();
      StringBuffer ybSb=new StringBuffer(); 
      for (int i = 0; i < materials.size(); i++) {
        //先分出检材和样本，如果没有，就混在一起
        SfMaterials material = (SfMaterials) materials.get(i);
        
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
       sb.append(ybSb.toString()); 
       
       return sb.toString();
  }
	public static String getJdclString(SfEntrust entrust){
	  if(entrust==null )return null; 
	  return getJdclString(entrust.getMaterials());
	}
	public List<SfBookmark> getJdRecordBookValueLst(SfJdResult bill){
	  if(bill==null){
	    bill=new SfJdResult();
	  }
		List<SfBookmark> rtn=new ArrayList<SfBookmark>();


		SfBookmark bk=new SfBookmark();
		bk.setName("JDRECORD_JD_DATE");
		bk.setValue(bill.getJdDate() == null ? "  年    月   日" : DateUtil.dateToChinaString(bill.getJdDate()));
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JDRECORD_JD_OPINION");
		bk.setValue(bill.getJdOpinion());
		rtn.add(bk); 
		
		bk=new SfBookmark();
		bk.setName("JDRECORD_JD_PROCESS");
		bk.setValue(bill.getJdProcess());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JDRECORD_JD_RESULT");
		bk.setValue(bill.getJdResult());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JDRECORD_ZC_PERSONS");
		bk.setValue(bill.getZcPersons());
		rtn.add(bk); 
		
		bk=new SfBookmark();
		bk.setName("JDRECORD_JD_ADDRESS");
		bk.setValue(bill.getJdAddress()==null?getJdjg().getName():bill.getJdAddress());
		rtn.add(bk);  
		
		bk=new SfBookmark();
		bk.setName("JDRECORD_JD_METHOD");
		bk.setValue(bill.getJdMethod());
		rtn.add(bk); 
		
		return rtn;
	} 
	public List<SfBookmark> getJdReportBookValueLst(SfJdReport bill){
		List<SfBookmark> rtn=new ArrayList<SfBookmark>();


		SfBookmark bk=new SfBookmark();
		bk.setName("JDREPORT_REPORT_CODE");//JDREPORT_REPORT_CODE
		bk.setValue(bill.getReportCode());
		rtn.add(bk);
		
		bk=new SfBookmark();
		bk.setName("JDREPORT_NAME");
		bk.setValue(bill.getName());
		rtn.add(bk);
		 //
		
		return rtn;
	} 
	
	SfJdPerson getJdPerson(String account){

		IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,"zcEbBaseServiceDelegate"); 
		RequestMeta meta=WorkEnv.getInstance().getRequestMeta(); 
		SfJdPerson person=(SfJdPerson) zcEbBaseServiceDelegate.queryObject("com.ufgov.zc.server.sf.dao.SfJdPersonMapper.selectByAccount", account, meta);
		return person;
	}
}
