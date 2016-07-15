package com.ufgov.zc.server.zc.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.model.ZcBdSppc;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IZcBdSppcDao;
import com.ufgov.zc.server.zc.service.IZcBdSppcService;

public class ZcBdSppcService implements IZcBdSppcService {

	private IZcBdSppcDao zcBdSppcDao;

	public IZcBdSppcDao getZcBdSppcDao() {
		return zcBdSppcDao;
	}

	public void setZcBdSppcDao(IZcBdSppcDao zcBdSppcDao) {
		this.zcBdSppcDao = zcBdSppcDao;
	}

	public List getZcBdSppcList(ElementConditionDto dto, RequestMeta meta)
			throws SQLException {

		List list = zcBdSppcDao.getZcBdSppcList(dto, meta);

		ZcSUtil.setBillDBDigest(list);

		return list;

	}

	public ZcBdSppc selectByPrimaryKey(String primKey, RequestMeta requestMeta)
			throws SQLException {

		ZcBdSppc bill = zcBdSppcDao.selectByPrimaryKey(primKey);

		bill.setDbDigest(bill.digest());

		return bill;
	}

	public int deleteByPrimaryKey(String zcPrimKey, RequestMeta requestMeta)
			throws SQLException {
		return zcBdSppcDao.deleteByPrimaryKey(zcPrimKey);
	}

	public ZcBdSppc updateZcBdSppcByPrimaryKey(ZcBdSppc zcBdSppc,
			RequestMeta meta) throws SQLException {
		String code = "";

		String userId = meta.getSvUserID();

		if ("".equals(ZcSUtil.safeString(zcBdSppc.getZcSppcID()))
				|| zcBdSppc.getZcSppcID().equals("自动编号")) {

			zcBdSppc.setNd(new Integer(meta.getSvNd()));

			code = NumUtil.getInstance().getNo("ZC_XYGH_SPPC", "ZC_SPPC_ID",
					zcBdSppc);

			zcBdSppc.setZcSppcID(code);

			zcBdSppc.setZcInputCode(userId);

			zcBdSppc.setZcInputDate(meta.getSysDate());

			zcBdSppcDao.insertZcBdSppc(zcBdSppc);

		} else {

			code = zcBdSppc.getZcSppcID();

			zcBdSppc.setZcOperCode(userId);

			zcBdSppc.setZcOperDate(meta.getSysDate());

			ZcBdSppc originalBean = this.selectByPrimaryKey(code, meta);

			ZcSUtil.checkDigest(zcBdSppc, originalBean, "zcSppcID");// 一致性校验

			zcBdSppcDao.updateZcBdSppcByPrimaryKey(zcBdSppc);
		}

		return this.selectByPrimaryKey(code, meta);
	}

}
