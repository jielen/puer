package com.ufgov.zc.server.zc.web.action.supplier;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class SupplierWillOpenBidPorjAction extends Action {
  private final static Log log = LogFactory.getLog(SupplierWillOpenBidPorjAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    //    SpringContext.setSpringContext(ApplusContext.getApplicationContext("com.ufgov.app.zc.server"));
    IZcEbBaseService zcEbBaseService = (IZcEbBaseService) SpringContext.getBean("zcEbBaseService");
    HashMap para = new HashMap();
    String userCode = (String) request.getSession().getAttribute("svUserID");
    para.put("limitTime", "Y");
    para.put("providerCode", userCode);

    List list = zcEbBaseService.queryDataForList("ZcEbSupplierPack.getPojectWillOpenBid", para);
    request.setAttribute("list", list);

    List dlyProjs = zcEbBaseService.queryDataForList("ZcEbSupplierPack.getDlyBidProj", userCode);
    request.setAttribute("dlyProjs", dlyProjs);

    List chgProjs = zcEbBaseService.queryDataForList("ZcEbSupplierPack.getChgBidProj", userCode);
    request.setAttribute("chgProjs", chgProjs);

    return mapping.findForward("success");
  }
}
