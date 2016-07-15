package com.ufgov.zc.server.zc.publish.impl;

import java.util.List;

import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.zc.model.SupplierPriceDetail;
import com.ufgov.zc.common.zc.model.ZcEbSignup;
import com.ufgov.zc.common.zc.model.ZcEbSignupPackDetail;
import com.ufgov.zc.common.zc.publish.ISupplierPriceDelegate;
import com.ufgov.zc.server.zc.service.ISupplierPriceService;

public class SupplierPriceDelegate implements ISupplierPriceDelegate {
	private ISupplierPriceService priceService;

	public ISupplierPriceService getPriceService() {
		return priceService;
	}

	public void setPriceService(ISupplierPriceService priceService) {
		this.priceService = priceService;
	}

	public List selectPriceDetail(SupplierPriceDetail detail, RequestMeta meta) {
		return priceService.selectPriceDetail(detail);
	}

	public void deletePriceDetial(SupplierPriceDetail detail, RequestMeta meta) {
		priceService.deletePriceDetial(detail);
	}

	public void insertPriceDetail(List details, RequestMeta meta) {
		priceService.insertPriceDetail(details);
	}

	public void updateSingUpPackPriceDetail(ZcEbSignupPackDetail packDetail,
			RequestMeta meta) {
		priceService.updateSingUpPackPriceDetail(packDetail);
	}

	public void updateSignUpPackPrice(ZcEbSignup signup, RequestMeta meta) {
		List packDetails = signup.getSignupPacks();
		if (packDetails != null) {
			for (int i = 0; i < packDetails.size(); i++) {
				ZcEbSignupPackDetail detail = (ZcEbSignupPackDetail) packDetails
						.get(i);
				updateSingUpPackPriceDetail(detail, meta);
			}
		}
		// for (ZcEbSignupPackDetail detail: packDetails) {
		// updateSingUpPackPriceDetail(detail, meta);
		// }
	}

}
