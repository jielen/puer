package com.ufgov.zc.server.sf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ufgov.zc.common.sf.model.SfAssistFile;
import com.ufgov.zc.common.sf.model.SfCharge;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.server.sf.dao.SfAssistFileMapper;
import com.ufgov.zc.server.sf.service.ISfAssistFileService;
import com.ufgov.zc.server.system.dao.IAsFileDao;
import com.ufgov.zc.server.zc.ZcSUtil;

public class SfAssistFileService implements ISfAssistFileService {

  private SfAssistFileMapper assistFileMapper;
  private IAsFileDao asFileDao;
  
  public List getMainDataLst(ElementConditionDto elementConditionDto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return assistFileMapper.getMainDataLst(elementConditionDto);
  }

  
  public List saveFN(List fileLst, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    if(fileLst==null)return fileLst;
    
    List oldLst=getMainDataLst(new ElementConditionDto(), requestMeta);
    
    oldLst=oldLst==null?new ArrayList():oldLst;

    boolean find=false;
    for(int j=0;j<oldLst.size();j++){
      SfAssistFile oldF=(SfAssistFile)oldLst.get(j);
      find=false;
      for(int i=0;i<fileLst.size();i++){
        SfAssistFile af=(SfAssistFile)fileLst.get(i);
        if(oldF.getAssistFileId().equals(af.getAssistFileId())){
          find=true;
          break;
        }
      }
      if(!find){
        assistFileMapper.deleteByPrimaryKey(oldF.getAssistFileId());
        asFileDao.deleteAsFileById(oldF.getFileId());
      }
    }

    ZcSUtil su=new ZcSUtil();
    for(int i=0;i<fileLst.size();i++){
      SfAssistFile af=(SfAssistFile)fileLst.get(i);
      if(af.getAssistFileId()==null){
        BigDecimal id=new BigDecimal(su.getNextVal(SfAssistFile.SEQ_SF_ASSIST_FILE_ID));
        af.setAssistFileId(id);
        assistFileMapper.insert(af);
      }else{
        assistFileMapper.updateByPrimaryKey(af);
      }
      
    }
    return fileLst;
  }


  public SfAssistFileMapper getAssistFileMapper() {
    return assistFileMapper;
  }


  public void setAssistFileMapper(SfAssistFileMapper assistFileMapper) {
    this.assistFileMapper = assistFileMapper;
  }


  public IAsFileDao getAsFileDao() {
    return asFileDao;
  }


  public void setAsFileDao(IAsFileDao asFileDao) {
    this.asFileDao = asFileDao;
  }

}
