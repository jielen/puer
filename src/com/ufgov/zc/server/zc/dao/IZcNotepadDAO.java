package com.ufgov.zc.server.zc.dao;

import java.util.List;

import com.ufgov.zc.common.zc.model.ZcNotepad;
import com.ufgov.zc.common.zc.model.ZcNotepadExample;

public interface IZcNotepadDAO {

  void insert(ZcNotepad record);

  int updateByPrimaryKey(ZcNotepad record);

  int updateByPrimaryKeySelective(ZcNotepad record);

  List selectByExample(ZcNotepadExample example);

  ZcNotepad selectByPrimaryKey(int notepadId);

  int deleteByExample(ZcNotepadExample example);

  int deleteByPrimaryKey(int notepadId);

  int countByExample(ZcNotepadExample example);

  int updateByExampleSelective(ZcNotepad record, ZcNotepadExample example);

  int updateByExample(ZcNotepad record, ZcNotepadExample example);
}