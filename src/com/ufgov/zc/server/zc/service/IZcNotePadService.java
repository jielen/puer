package com.ufgov.zc.server.zc.service;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcNotepad;

public interface IZcNotePadService {

  public List getZcNotepads(ElementConditionDto dto, RequestMeta requestMeta);

  public ZcNotepad getZcNotepadById(int notepadId);

  public ZcNotepad doSave(ZcNotepad notepad, RequestMeta requestMeta);

  public void doDelete(int notepadId);

}
