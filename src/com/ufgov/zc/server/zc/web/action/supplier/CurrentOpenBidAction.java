package com.ufgov.zc.server.zc.web.action.supplier;

import java.util.Date;
import java.util.Enumeration;
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

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.server.system.SpringContext;
import com.ufgov.zc.server.zc.service.IZcEbBaseService;

public class CurrentOpenBidAction extends Action {
	private final static Log log = LogFactory.getLog(Action.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// SpringContext.setSpringContext(ApplusContext.getApplicationContext("com.ufgov.app.zc.server"));
		IZcEbBaseService zcEbBaseService = (IZcEbBaseService) SpringContext
				.getBean("zcEbBaseService");
		HashMap para = new HashMap();
		para.put("limitTime", "Y");
		para.put("executor", request.getSession().getAttribute("svUserID"));

		para.put("roleId", request.getSession().getAttribute("svRoleId"));

		String roleCode = (String) zcEbBaseService.queryObject(
				"AsRole.getRoleCodeById",
				request.getSession().getAttribute("svRoleId"));

		if ("CGZX_XX_EXTRAC".equals(roleCode)) {

			para.put("days", "" + 4);

		} else {

			para.put("days", "" + 1);

		}

		RequestMeta mt = new RequestMeta();
		if (mt.getTransDate() == null) {
			mt.setTransDate(new Date());
		}
		String nd = (String) request.getSession().getAttribute("svNd");
		try {
			mt.getTransDate().setYear(Integer.parseInt(nd));
		} catch (Exception e) {

		}

		mt.setSvCoCode((String) request.getSession().getAttribute("svCoCode"));
		mt.setSvOrgCode((String) request.getSession().getAttribute("svOrgCode"));
		mt.setSvPoCode((String) request.getSession().getAttribute("svPoCode"));
		mt.setAccountId((String) request.getSession().getAttribute(
				"svAccountId"));
		mt.setSvUserID((String) request.getSession().getAttribute("svUserID"));
//		mt.setSvRoleId((String) request.getSession().getAttribute("svRoleId"));

		List list = zcEbBaseService.getCurrentOpenBidList(para, mt);
		List bidEndList = zcEbBaseService.getCurrentBidEndList(para, mt);
		request.setAttribute("openBidList", list);
		request.setAttribute("bidEndList", bidEndList);

		Enumeration en = request.getSession().getAttributeNames();

		// while (en.hasMoreElements()) {
		// String name = en.nextElement().toString();
		// System.out.println(name + ":" +
		// request.getSession().getAttribute(name));
		// }
		return mapping.findForward("success");

	}
}
