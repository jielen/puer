package com.ufgov.zc.server.zc.service.impl;

import java.util.List;

import com.ufgov.zc.common.zc.model.SupplierPriceDetail;
import com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail;
import com.ufgov.zc.server.zc.dao.ISupplierPriceDao;
import com.ufgov.zc.server.zc.service.ISupplierPriceService;

public class SupplierPriceService implements ISupplierPriceService {
	private ISupplierPriceDao priceDao;

	public ISupplierPriceDao getPriceDao() {
		return priceDao;
	}

	public void setPriceDao(ISupplierPriceDao priceDao) {
		this.priceDao = priceDao;
	}

	public List selectPriceDetail(SupplierPriceDetail detail) {
		return priceDao.selectPriceDetail(detail);
	}

	public void deletePriceDetial(SupplierPriceDetail detail) {
		priceDao.deletePriceDetail(detail);
	}

	public void insertPriceDetail(List details) {
		if (details != null) {
			for (int i = 0; i < details.size(); i++) {
				priceDao.insertPriceDetail((SupplierPriceDetail) details.get(i));
			}
		}
	}

	public void updateSingUpPackPriceDetail(ZcEbSignupPackDetail packDetail) {
		SupplierPriceDetail priceDetail = new SupplierPriceDetail();
		priceDetail.setSingupPackId(packDetail.getSignupPackId());
		this.deletePriceDetial(priceDetail);
		List priceDetails = packDetail.getSupplierPriceDetail();
		for (int i = 0; i < priceDetails.size(); i++) {
			SupplierPriceDetail sd = (SupplierPriceDetail) priceDetails.get(i);
			sd.setSingupPackId(packDetail.getSignupPackId());
		}
		// for (SupplierPriceDetail detail : priceDetails) {
		// detail.setSingupPackId(packDetail.getSignupPackId());
		// }
		this.insertPriceDetail(packDetail.getSupplierPriceDetail());
		priceDao.updateSignPackMoney(packDetail);
	}

}
