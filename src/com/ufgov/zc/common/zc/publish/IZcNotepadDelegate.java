package com.ufgov.zc.common.zc.publish;

import java.util.List;

import com.ufgov.zc.common.system.Publishable;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcNotepad;

public interface IZcNotepadDelegate extends Publishable {

  public List getZcNotepads(ElementConditionDto dto, RequestMeta requestMeta);

  public ZcNotepad getZcNotepadById(int notepadId, RequestMeta requestMeta);

  public ZcNotepad doSave(ZcNotepad notepad, RequestMeta requestMeta);

  public void doDelete(int notepadId, RequestMeta requestMeta);

  public void doDelete(String[] ids, RequestMeta requestMeta);

}
