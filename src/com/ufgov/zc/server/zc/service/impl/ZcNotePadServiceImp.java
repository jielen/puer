package com.ufgov.zc.server.zc.service.impl;

import java.util.Date;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcNotepad;
import com.ufgov.zc.common.zc.model.ZcNotepadExample;
import com.ufgov.zc.common.zc.model.ZcNotepadExample.Criteria;
import com.ufgov.zc.server.system.util.NumLimUtil;
import com.ufgov.zc.server.zc.dao.IZcNotepadDAO;
import com.ufgov.zc.server.zc.service.IZcNotePadService;

public class ZcNotePadServiceImp implements IZcNotePadService {
  private IZcNotepadDAO zcNotepadDAO = null;

  public List getZcNotepads(ElementConditionDto condition, RequestMeta requestMeta) {
    String numlimString = NumLimUtil.getInstance().getNumLimCondByCoType(condition.getWfcompoId(), NumLimConstants.FWATCH);
    ZcNotepadExample example = new ZcNotepadExample();
    Criteria c = example.createCriteria();
    //    if (null == numlimString || "".equals(numlimString)) {
    //      c.andEditUserIdEqualTo(RequestMetaUtil.getSvUserID());
    //    } else {
    //      example.setNumLimitStr(numlimString);
    //    }
    if (condition.getUserId() != null) {
      c.andEditUserIdEqualTo(condition.getUserId());
    }
    if (condition.getZcText0() != null) {
      c.andEntrustCodeEqualTo(condition.getZcText0());
    }
    example.setOrderByClause("EDIT_DATE");
    return zcNotepadDAO.selectByExample(example);
  }

  public ZcNotepad getZcNotepadById(int notepadId) {
    // TCJLODO Auto-generated method stub
    return zcNotepadDAO.selectByPrimaryKey(notepadId);
  }

  public ZcNotepad doSave(ZcNotepad notepad, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    notepad.setEditUserId(requestMeta.getSvUserID());
    notepad.setEditUserName(requestMeta.getSvUserName());
    notepad.setCoCode(requestMeta.getSvCoCode());
    notepad.setOrgCode(requestMeta.getSvOrgCode());
    notepad.setEditDate(new Date());
    if (notepad.getNotepadId() != 0) {
      zcNotepadDAO.updateByPrimaryKeySelective(notepad);
    } else {
      zcNotepadDAO.insert(notepad);
    }

    return getZcNotepadById(notepad.getNotepadId());
  }

  public void doDelete(int notepadId) {
    // TCJLODO Auto-generated method stub
    zcNotepadDAO.deleteByPrimaryKey(notepadId);
  }

  public IZcNotepadDAO getZcNotepadDAO() {
    return zcNotepadDAO;
  }

  public void setZcNotepadDAO(IZcNotepadDAO zcNotepadDAO) {
    this.zcNotepadDAO = zcNotepadDAO;
  }

}
