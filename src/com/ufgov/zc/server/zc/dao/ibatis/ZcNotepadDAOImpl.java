package com.ufgov.zc.server.zc.dao.ibatis;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ufgov.zc.common.zc.model.ZcNotepad;
import com.ufgov.zc.common.zc.model.ZcNotepadExample;
import com.ufgov.zc.server.zc.dao.IZcNotepadDAO;

public class ZcNotepadDAOImpl extends SqlMapClientDaoSupport implements IZcNotepadDAO {

  public ZcNotepadDAOImpl() {
    super();
  }

  public void insert(ZcNotepad record) {
    getSqlMapClientTemplate().insert("ZC_NOTEPAD.abatorgenerated_insert", record);
  }

  public int updateByPrimaryKey(ZcNotepad record) {
    int rows = getSqlMapClientTemplate().update("ZC_NOTEPAD.abatorgenerated_updateByPrimaryKey", record);
    return rows;
  }

  public int updateByPrimaryKeySelective(ZcNotepad record) {
    int rows = getSqlMapClientTemplate().update("ZC_NOTEPAD.abatorgenerated_updateByPrimaryKeySelective", record);
    return rows;
  }

  public List selectByExample(ZcNotepadExample example) {
    List list = getSqlMapClientTemplate().queryForList("ZC_NOTEPAD.abatorgenerated_selectByExample", example);
    return list;
  }

  public ZcNotepad selectByPrimaryKey(int notepadId) {
    ZcNotepad key = new ZcNotepad();
    key.setNotepadId(notepadId);
    ZcNotepad record = (ZcNotepad) getSqlMapClientTemplate().queryForObject("ZC_NOTEPAD.abatorgenerated_selectByPrimaryKey", key);
    return record;
  }

  public int deleteByExample(ZcNotepadExample example) {
    int rows = getSqlMapClientTemplate().delete("ZC_NOTEPAD.abatorgenerated_deleteByExample", example);
    return rows;
  }

  public int deleteByPrimaryKey(int notepadId) {
    ZcNotepad key = new ZcNotepad();
    key.setNotepadId(notepadId);
    int rows = getSqlMapClientTemplate().delete("ZC_NOTEPAD.abatorgenerated_deleteByPrimaryKey", key);
    return rows;
  }

  public int countByExample(ZcNotepadExample example) {
    Integer count = (Integer) getSqlMapClientTemplate().queryForObject("ZC_NOTEPAD.abatorgenerated_countByExample", example);
    return count.intValue();
  }

  public int updateByExampleSelective(ZcNotepad record, ZcNotepadExample example) {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("ZC_NOTEPAD.abatorgenerated_updateByExampleSelective", parms);
    return rows;
  }

  public int updateByExample(ZcNotepad record, ZcNotepadExample example) {
    UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
    int rows = getSqlMapClientTemplate().update("ZC_NOTEPAD.abatorgenerated_updateByExample", parms);
    return rows;
  }

  private static class UpdateByExampleParms extends ZcNotepadExample {
    private Object record;

    public UpdateByExampleParms(Object record, ZcNotepadExample example) {
      super(example);
      this.record = record;
    }

    public Object getRecord() {
      return record;
    }
  }
}