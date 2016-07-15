package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcNotepad;
import com.ufgov.zc.common.zc.publish.IZcNotepadDelegate;
import com.ufgov.zc.server.zc.service.IZcNotePadService;

public class ZcNotepadDelegate implements IZcNotepadDelegate {
  private IZcNotePadService zcNotePadService = null;

  public List getZcNotepads(ElementConditionDto dto, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcNotePadService.getZcNotepads(dto, requestMeta);
  }

  public ZcNotepad getZcNotepadById(int notepadId, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcNotePadService.getZcNotepadById(notepadId);
  }

  public ZcNotepad doSave(ZcNotepad notepad, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    return zcNotePadService.doSave(notepad, requestMeta);
  }

  public void doDelete(int notepadId, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    zcNotePadService.doDelete(notepadId);
  }

  public IZcNotePadService getZcNotePadService() {
    return zcNotePadService;
  }

  public void setZcNotePadService(IZcNotePadService zcNotePadService) {
    this.zcNotePadService = zcNotePadService;
  }

  public void doDelete(String[] ids, RequestMeta requestMeta) {
    // TCJLODO Auto-generated method stub
    for (int i = 0; i < ids.length; i++) {
      this.zcNotePadService.doDelete(Integer.parseInt(ids[i]));
    }
  }

}
