package com.ufgov.zc.server.system.service.impl;import java.util.List;import com.ufgov.zc.common.system.Guid;import com.ufgov.zc.common.system.model.AsFile;import com.ufgov.zc.server.system.dao.IAsFileDao;import com.ufgov.zc.server.system.service.IAsFileService;public class AsFileDBService implements IAsFileService {  IAsFileDao asFileDao;  public IAsFileDao getAsFileDao() {    return asFileDao;  }  public void setAsFileDao(IAsFileDao asFileDao) {    this.asFileDao = asFileDao;  }  /**    * 返回文件Id    * @param asFile    * @return    */  public String uploadFile(AsFile asFile) {    if (asFile.getFileId() == null || "".equals(asFile.getFileId())) {      asFile.setFileId(Guid.genID());    }    try {      asFileDao.deleteAsFileById(asFile.getFileId());      asFileDao.insertAsFile(asFile);      return asFile.getFileId();    } catch (Exception e) {      throw new RuntimeException("保存上传文件时出错！", e);    }  }  public AsFile getAsFileById(String fileId) {    return asFileDao.getAsFileById(fileId);  }  public AsFile downloadFile(String fileId) {    try {      AsFile asFile = asFileDao.getAsFileById(fileId);      if(asFile == null){        asFile=getFileFromHistory(fileId);      }      return asFile;    } catch (Exception e) {      throw new RuntimeException("下载文件时出错！", e);    }  }  /**   * 从历史备份中获取文件   * 历史文件存放在不同用户的as_file中，通过视图联合了，可能存在多个相同文件   * 获取时，返回其中一个    * @param fileId   * @return   */  private AsFile getFileFromHistory(String fileId) {    List fileLst=asFileDao.getAsFileFromHistoryById(fileId);    if(fileLst!=null && fileLst.size()>0){      return (AsFile) fileLst.get(0);    }    return null;  }  public void deleteFile(String fileId) {    try {      asFileDao.deleteAsFileById(fileId);    } catch (Exception e) {      throw new RuntimeException("删除文件时出错！", e);    }  }  public boolean uploadFile(AsFile asFile, String savePath, String fileName) {    return false;  }  public void updateAsFileById(AsFile asFile) {    try {      asFileDao.updateAsFileById(asFile);    } catch (Exception e) {      throw new RuntimeException("更新文件时出错！", e);    }  }  public String uploadLargeFile(AsFile asFile) {    // TCJLODO Auto-generated method stub    return null;  }  public AsFile getLargeAsFileById(String fileId) {    // TCJLODO Auto-generated method stub    return null;  }  public List getLargeAsFile(List fileIdList) {    // TCJLODO Auto-generated method stub    return null;  }  public String insertAsFileDirectory(AsFile asFile) {    // TCJLODO Auto-generated method stub    return null;  }  public byte[] getTbylbFromFileMenuById(String fileId, String packCode) {    // TCJLODO Auto-generated method stub    return null;  }  public void uploadFileSavePath(AsFile asFile) {    // TCJLODO Auto-generated method stub  }  public AsFile getAsFileNoContentById(String fileId) {    // TCJLODO Auto-generated method stub    return asFileDao.getAsFileNoContentById(fileId);  }}