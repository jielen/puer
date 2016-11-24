/**   * @(#) project: Gk* @(#) file: ZcEbEvalExpertServiceDelegate.java* * Copyright 2010 UFGOV, Inc. All rights reserved.* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.* */package com.ufgov.zc.server.zc.publish.impl;import java.util.List;import java.util.Map;import com.ufgov.zc.common.system.RequestMeta;import com.ufgov.zc.common.zc.model.ZcEbEvalExpertComment;import com.ufgov.zc.common.zc.publish.IZcEbEvalExpertCommentServiceDelegate;import com.ufgov.zc.server.zc.ZcSUtil;import com.ufgov.zc.server.zc.service.IZcEbEvalExpertCommentService;/*** @ClassName: ZcEbEvalExpertServiceDelegate* @Description: TCJLODO(这里用一句话描述这个类的作用)* @date: 2010-7-15 下午03:11:49* @version: V1.0 * @since: 1.0* @author: Administrator* @modify: */public class ZcEbEvalExpertCommentServiceDelegate implements IZcEbEvalExpertCommentServiceDelegate {  public IZcEbEvalExpertCommentService getZcEbEvalExpertCommentService() {    return zcEbEvalExpertCommentService;  }  public void setZcEbEvalExpertCommentService(IZcEbEvalExpertCommentService zcEbEvalExpertCommentService) {    this.zcEbEvalExpertCommentService = zcEbEvalExpertCommentService;  }  private IZcEbEvalExpertCommentService zcEbEvalExpertCommentService;  public void saveExpertComment(ZcEbEvalExpertComment zcEbEvalExpertComment, RequestMeta requestMeta) {    zcEbEvalExpertComment.setCreateDate(ZcSUtil.getSysDate());    zcEbEvalExpertCommentService.saveExpertComment(zcEbEvalExpertComment);  }  public void updateExpertComment(ZcEbEvalExpertComment zcEbEvalExpertComment, RequestMeta requestMeta) {    zcEbEvalExpertCommentService.updateExpertComment(zcEbEvalExpertComment);  }  public void deleteExpertComment(ZcEbEvalExpertComment zcEbEvalExpertComment, RequestMeta requestMeta) {    zcEbEvalExpertCommentService.deleteExpertComment(zcEbEvalExpertComment);  }  public List getExpertCommentList(Map map, RequestMeta requestMeta) {    return zcEbEvalExpertCommentService.getExpertCommentList(map);  }  public void deleteExpertCommentByExpert(Map map, RequestMeta requestMeta) {    zcEbEvalExpertCommentService.deleteExpertCommentByExpert(map);  }}