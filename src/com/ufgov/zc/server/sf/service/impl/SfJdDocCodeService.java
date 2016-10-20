/**
 * 
 */
package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfJdDocCode;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper;
import com.ufgov.zc.server.sf.service.ISfJdDocCodeService;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

/**
 * @author Administrator
 *
 */
public class SfJdDocCodeService implements ISfJdDocCodeService {
	
	private SfJdDocCodeMapper jdDocCodeMapper;
	private IZcEbBaseService zcEbBaseService;

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#getMainDataLst(com.ufgov.zc.common.system.dto.ElementConditionDto, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public List getMainDataLst(ElementConditionDto dto, RequestMeta requestMeta) {
		
		//return jdDocCodeMapper.getSfJdDocCodeLst(dto);
		
		HashMap  max=(HashMap) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper.getMaxum", dto);
		List rtn=jdDocCodeMapper.getSfJdDocCodeLst(dto);
		/*BigDecimal indx=new BigDecimal(0);
		if(max==null || max.size()==0){
			indx=new BigDecimal(1);
		}else{
			indx=	(BigDecimal) max.get("NUM"); 
		}*/
		int indx=getNextIndex(requestMeta);
		BigDecimal d=new BigDecimal(indx);
		if(rtn!=null ){
			for(int i=0;i<rtn.size();i++){
				SfJdDocCode dc=(SfJdDocCode) rtn.get(i);
				dc.setNum(d);
			}
		}
		return rtn;
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#selectByPrimaryKey(java.lang.String, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public SfJdDocCode selectByPrimaryKey(String pinJieCode,RequestMeta requestMeta) {
		return jdDocCodeMapper.selectByPrimaryKey(pinJieCode);
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#saveFN(com.ufgov.zc.common.sf.model.SfJdDocCode, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public SfJdDocCode saveFN(SfJdDocCode inData, RequestMeta requestMeta) throws SfBusinessException{
		/*SfJdDocCode t=selectByPrimaryKey(inData.getPinJieCode(), requestMeta);
		 
		if(t!=null){
			if(t.getNum().intValue()>=inData.getNum().intValue()){
				throw new SfBusinessException("编号"+inData.getCode()+"已被占用，请重新选择");
			}else{
				jdDocCodeMapper.updateByPrimaryKey(inData);
			}
		}else{
			jdDocCodeMapper.insert(inData);
		}*/
		int indx=getNextIndex(requestMeta);
		if(inData.getNum().intValue()!=indx){
			throw new SfBusinessException("编号"+inData.getNum().intValue()+"已被占用，请重新选择");
		}
		
		SfJdDocCode t=selectByPrimaryKey(inData.getPinJieCode(), requestMeta);
		 
		if(t!=null){ 
				jdDocCodeMapper.updateByPrimaryKey(inData); 
		}else{
			jdDocCodeMapper.insert(inData);
		}
		
		return inData;
	}
	private int getNextIndex(RequestMeta meta){

		ElementConditionDto dto=new ElementConditionDto();
		dto.setNd(meta.getSvNd());
		dto.setCoCode(meta.getSvCoCode());
		HashMap  max=(HashMap) zcEbBaseService.queryObject("com.ufgov.zc.server.sf.dao.SfJdDocCodeMapper.getMaxum", dto);

		int indx=0;
		if(max==null || max.size()==0){
			indx++;
		}else{
			BigDecimal d=	(BigDecimal) max.get("NUM"); 
			if(d==null){
				indx++;
			}else{
				indx=d.intValue();
			}
		} 
		return indx;
	}
	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#deleteByPrimaryKeyFN(java.lang.String, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public void deleteByPrimaryKeyFN(String pinJieCode, RequestMeta requestMeta) {
		jdDocCodeMapper.deleteByPrimaryKey(pinJieCode);
	}

	/* (non-Javadoc)
	 * @see com.ufgov.zc.server.sf.service.ISfJdDocCodeService#updateByPrimaryKeyFN(com.ufgov.zc.common.sf.model.SfJdDocCode, com.ufgov.zc.common.system.RequestMeta)
	 */
	
	public int updateByPrimaryKeyFN(SfJdDocCode inData, RequestMeta requestMeta) throws SfBusinessException{
		jdDocCodeMapper.updateByPrimaryKey(inData);
		return 0;
	}

	public SfJdDocCodeMapper getJdDocCodeMapper() {
		return jdDocCodeMapper;
	}

	public void setJdDocCodeMapper(SfJdDocCodeMapper jdDocCodeMapper) {
		this.jdDocCodeMapper = jdDocCodeMapper;
	}

	public IZcEbBaseService getZcEbBaseService() {
		return zcEbBaseService;
	}

	public void setZcEbBaseService(IZcEbBaseService zcEbBaseService) {
		this.zcEbBaseService = zcEbBaseService;
	}

}
