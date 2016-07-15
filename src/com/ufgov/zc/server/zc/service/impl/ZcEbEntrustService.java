package com.ufgov.zc.server.zc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kingdrive.workflow.context.WorkflowContext;
import com.ufgov.zc.common.cp.model.CpPlanAgentList;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.dto.MainSubBill;
import com.ufgov.zc.common.system.dto.PrintObject;
import com.ufgov.zc.common.system.model.AsWfDraft;
import com.ufgov.zc.common.zc.model.DataExchangeRedo;
import com.ufgov.zc.common.zc.model.ZcEbAuditSheet;
import com.ufgov.zc.common.zc.model.ZcEbEntrust;
import com.ufgov.zc.common.zc.model.ZcEbEntrustDetail;
import com.ufgov.zc.common.zc.model.ZcPProMake;
import com.ufgov.zc.common.zc.model.ZcPProMitemBi;
import com.ufgov.zc.server.system.dao.IWorkflowDao;
import com.ufgov.zc.server.system.print.PrintManager;
import com.ufgov.zc.server.system.util.NumUtil;
import com.ufgov.zc.server.system.workflow.WFEngineAdapter;
import com.ufgov.zc.server.zc.ZcSUtil;
import com.ufgov.zc.server.zc.dao.IBaseDao;
import com.ufgov.zc.server.zc.dao.IZcEbEntrustDao;
import com.ufgov.zc.server.zc.dao.IZcPProMakeDao;
import com.ufgov.zc.server.zc.service.IZcEbEntrustService;

public class ZcEbEntrustService implements IZcEbEntrustService {

	private IBaseDao baseDao;

	private IZcEbEntrustDao zcEbEntrustDao;

	private IZcPProMakeDao zcPProMakeDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public IZcPProMakeDao getZcPProMakeDao() {
		return zcPProMakeDao;
	}

	public void setZcPProMakeDao(IZcPProMakeDao zcPProMakeDao) {
		this.zcPProMakeDao = zcPProMakeDao;
	}

	private WFEngineAdapter wfEngineAdapter;

	private IWorkflowDao workflowDao;

	public IWorkflowDao getWorkflowDao() {
		return workflowDao;
	}

	public void setWorkflowDao(IWorkflowDao workflowDao) {
		this.workflowDao = workflowDao;
	}

	public WFEngineAdapter getWfEngineAdapter() {
		return wfEngineAdapter;
	}

	public void setWfEngineAdapter(WFEngineAdapter wfEngineAdapter) {
		this.wfEngineAdapter = wfEngineAdapter;
	}

	public IZcEbEntrustDao getZcEbEntrustDao() {
		return zcEbEntrustDao;
	}

	public void setZcEbEntrustDao(IZcEbEntrustDao zcEbEntrustDao) {
		this.zcEbEntrustDao = zcEbEntrustDao;
	}

	public List getZcEbEntrust(ElementConditionDto dto, RequestMeta meta) {
		List list = zcEbEntrustDao.getZcEbEntrust(dto, meta);
		return list;
	}

	public List getZcEbEntrustDetailBySn(String sn) {
		return zcEbEntrustDao.getZcEbEntrustDetailBySn(sn);
	}

	public ZcEbEntrust updateZcEbEntrustStatus(ZcEbEntrust entrust) {
		zcEbEntrustDao.updateZcEbEntrust(entrust);
		return entrust;
	}

	private void createWfDraft(ZcEbEntrust entrust, RequestMeta meta) {

		Long draftid = workflowDao.createDraftId();
		entrust.setProcessInstId(draftid);

		AsWfDraft asWfDraft = new AsWfDraft();
		asWfDraft.setCompoId(meta.getCompoId());
		asWfDraft.setWfDraftName(entrust.getTitleField());
		asWfDraft.setUserId(meta.getSvUserID());
		asWfDraft.setMasterTabId("ZC_EB_ENTRUST");
		asWfDraft.setWfDraftId(BigDecimal.valueOf(draftid.longValue()));

		workflowDao.insertAsWfdraft(asWfDraft);
	}

	public void updateZcEbEntrustStatus(List entrustList) {
		zcEbEntrustDao.updateZcEbEntrustStatus(entrustList);
	}

	private List getOriginZcEbEntrust(List currBeanList) {
		// List idList = new ArrayList();
		// for (int i = 0; i < currBeanList.size(); i++) {
		//
		// idList.add(((ZcEbEntrust) currBeanList.get(i)).getSn());
		// }
		// return this.zcEbEntrustDao.getOriginZcEbEntrustByIdList(idList);
		List beanList = new ArrayList();
		for (int i = 0; i < currBeanList.size(); i++) {
			beanList.add(this.getZcEbEntrustBySn(((ZcEbEntrust) currBeanList
					.get(i)).getSn()));
		}
		return beanList;
	}

	private ZcEbEntrust getOriginZcEbEntrust(ZcEbEntrust entrust) {
		// List idList = new ArrayList();
		// idList.add(entrust.getSn());
		// ZcEbEntrust rtn=
		// return (ZcEbEntrust)
		// this.zcEbEntrustDao.getOriginZcEbEntrustByIdList(idList).get(0);
		return this.getZcEbEntrustBySn(entrust.getSn());
	}

	public ZcEbEntrust getZcEbEntrustBySn(String sn) {
		ZcEbEntrust entrust = this.zcEbEntrustDao.getZcEbEntrustBySn(sn);
		List detailList = this.getZcEbEntrustDetailBySn(entrust.getSn());
		entrust.setDetailList(detailList == null ? new ArrayList() : detailList);
		entrust.setDbDigest(entrust.digest());
		return entrust;
	}

	private void checkConsistency(List currBeanList) {
		ZcSUtil.checkConsistency(currBeanList,
				getOriginZcEbEntrust(currBeanList), "sn");
	}

	private void checkConsistency(ZcEbEntrust entrust) {
		// List currBeanList = new ArrayList();
		// currBeanList.add(entrust);
		// this.checkConsistency(currBeanList);
		ZcEbEntrust old = this.getZcEbEntrustBySn(entrust.getSn());
		ZcSUtil.checkConsistencyDirect(entrust, old, "sn");
	}

	public ZcEbEntrust audit(ZcEbEntrust entrust, RequestMeta requestMeta) {
		wfEngineAdapter.commit(entrust.getComment(), entrust, requestMeta);
		return this.getZcEbEntrustBySn(entrust.getSn());
	}

	public ZcEbEntrust newCommit(ZcEbEntrust entrust, RequestMeta meta) {
		wfEngineAdapter.newCommit(entrust.getComment(), entrust, meta);
		return this.getZcEbEntrustBySn(entrust.getSn());
	}

	/*
	 * 销审
	 */
	public ZcEbEntrust unaudit(ZcEbEntrust entrust, RequestMeta requestMeta) {
		checkConsistency(entrust);
		zcEbEntrustDao.updateZcEbEntrustStatus(entrust);
		if (entrust.getProcessInstId() != null
				&& entrust.getProcessInstId().longValue() > 0) {
			// 如果流程已经启动，先终止流程
			wfEngineAdapter.interrupt(entrust.getComment(), entrust,
					requestMeta);
		}
		// 判断批办单是否终审
		Map parameter = new HashMap();
		parameter.put("sn", entrust.getSn());
		parameter.put("status", ZcEbAuditSheet.STATUS_EXEC);
		int countNum = ((Integer) baseDao.read("ZcEbAuditSheet.countrow",
				parameter)).intValue();
		if (countNum != 0) {
			throw new RuntimeException("[任务单] 对应的 [批办单] 已经终审,不能进行销审操作！");
		} else {
			baseDao.delete("ZcEbAuditSheet.deleteBySn", parameter);
		}

		// wfEngineAdapter.unAudit(entrust.getComment(), entrust);
		// zcEbEntrustDao.updateZcEbEntrust(entrust);
		return this.getZcEbEntrustBySn(entrust.getSn());
	}

	public ZcEbEntrust untread(ZcEbEntrust entrust, RequestMeta requestMeta) {
		wfEngineAdapter.untreadGk(entrust.getComment(), entrust, requestMeta);
		return this.getZcEbEntrustBySn(entrust.getSn());
	}

	public PrintObject genMainSubPrintObject(List zcEbEntrustList) {
		Map subDataMap = getZcEbEntrustDetailListMap(zcEbEntrustList);
		List mainSubBillList = new ArrayList();
		for (int i = 0; i < zcEbEntrustList.size(); i++) {
			ZcEbEntrust bill = (ZcEbEntrust) zcEbEntrustList.get(i);
			List subDataList = new ArrayList();
			if (subDataMap.get(bill.getSn()) != null) {
				subDataList = (List) subDataMap.get(bill.getSn());
			}
			MainSubBill msb = new MainSubBill();
			msb.setMainBill(bill);
			msb.setSubBillList(subDataList);
			mainSubBillList.add(msb);
		}
		return PrintManager.genMainSubPrintObject(mainSubBillList);
	}

	private Map getZcEbEntrustDetailListMap(List zcEbEntrustList) {
		List idList = new ArrayList();
		for (int i = 0; i < zcEbEntrustList.size(); i++) {
			idList.add(((ZcEbEntrust) zcEbEntrustList.get(i)).getSn());
		}
		List subDataList = this.zcEbEntrustDao.getZcEbEntrustDetailList(idList);
		Map dataMap = new HashMap();
		for (int i = 0; i < subDataList.size(); i++) {
			CpPlanAgentList cpPlanAgentList = (CpPlanAgentList) subDataList
					.get(i);
			List oldList = (List) dataMap.get(cpPlanAgentList
					.getPlanAgentBillId());
			if (oldList != null) {
				oldList.add(cpPlanAgentList);
			} else {
				List newList = new ArrayList();
				newList.add(cpPlanAgentList);
				dataMap.put(cpPlanAgentList.getPlanAgentBillId(), newList);
			}
		}
		return dataMap;
	}

	/*
	 * 不受理
	 */
	public void doBack(ZcEbEntrust entrust, RequestMeta requestMeta) {
		checkConsistency(entrust);
		zcEbEntrustDao.updateZcEbEntrustStatus(entrust);
		if (entrust.getProcessInstId() != null
				&& entrust.getProcessInstId().longValue() > 0) {
			// 如果流程已经启动，先终止流程
			wfEngineAdapter.interrupt(entrust.getComment(), entrust,
					requestMeta);
		}
		ZcPProMake zcPProMake = zcPProMakeDao.selectByPrimaryKey(entrust
				.getZcMakeCode());
		// 采购计划工作流销审
		WorkflowContext workflowContext = wfEngineAdapter.genCommonWFC(
				entrust.getComment(), zcPProMake, "ZC_P_PRO_MAKE", requestMeta);
		wfEngineAdapter.reworkNoCheck(workflowContext);
	}

	public ZcEbEntrust doSave(ZcEbEntrust entrust) {
		ZcEbEntrust tempEntrust = this.zcEbEntrustDao
				.getZcEbEntrustBySn(entrust.getSn());
		if (tempEntrust == null || tempEntrust.getSn().equals("")) {
			// entrust.setZcMakeCode(entrust.getSn());
			String entrust_code = NumUtil.getInstance().getNo("ZC_P_PRO_MAKE",
					"ZC_TEMP_MAKE_CODE", entrust);
			// entrust.setZcMakeCode(entrust_code);
			entrust.setSn(entrust_code);
			for (int i = 0; i < entrust.getDetailList().size(); i++) {
				ZcEbEntrustDetail detail = (ZcEbEntrustDetail) entrust
						.getDetailList().get(i);
				detail.setSn(entrust.getSn());
				detail.setSnd(entrust.getSn() + "_" + (i + 1));
			}
			for (int i = 0; i < entrust.getBiList().size(); i++) {
				ZcPProMitemBi bi = (ZcPProMitemBi) entrust.getBiList().get(i);
				bi.setZcProBiSeq(entrust.getZcMakeCode() + "_" + (i + 1));
				bi.setZcMakeCode(entrust.getZcMakeCode());
			}
			this.zcEbEntrustDao.deleteDetalBySn(entrust.getSn());
			baseDao.delete("ZC_P_PRO_MITEM_BI.deleteByZcMakeCode",
					entrust.getZcMakeCode());
			this.zcEbEntrustDao.insertZcEbEntrust(entrust);
		} else {
			// 生成新的任务单编号
			if (ZcEbEntrust.STATUS_ACCEPTED.equals(entrust.getStatus())
					&& (entrust.getSnCode() == null || "".equals(entrust
							.getSnCode()))) {
				String snCode = NumUtil.getInstance().getNo("ZC_EB_ENTRUST",
						"SN_CODE", entrust);
				entrust.setSnCode(snCode);
			}

			zcEbEntrustDao.updateZcEbEntrust(entrust);
		}
		return this.getZcEbEntrustBySn(entrust.getSn());
	}

	public int getCountData(String sn) {
		return zcEbEntrustDao.getCountData(sn);
	}

	public int deleteByPrimaryKey(ZcEbEntrust zcEbEntrust) {
		if (zcEbEntrust != null && zcEbEntrust.getSn() != null) {
			ZcPProMake zcPProMake = this.zcPProMakeDao
					.selectByPrimaryKey(zcEbEntrust.getZcMakeCode());

			if (zcPProMake != null) {
				// 删除采购计划的信息
				zcPProMakeDao.deleteByPrimaryKey(zcPProMake.getZcMakeCode());
				// 删除资金明细
				baseDao.delete("ZC_P_PRO_MITEM_BI.deleteByZcMakeCode",
						zcPProMake.getZcMakeCode());
				// 删除采购明细
				baseDao.delete("ZC_P_PRO_MITEM.deleteMitemByZcMakeCode",
						zcPProMake.getZcMakeCode());
			}
			// 删除任务单明细
			baseDao.delete("ZcEbEntrust.deleteZcEbEntrustDetaiBi",
					zcEbEntrust.getZcMakeCode());
			zcEbEntrustDao.deleteDetalBySn(zcEbEntrust.getSn());
			return zcEbEntrustDao.deleteByPrimaryKey(zcEbEntrust.getSn());
		}
		return 0;
	}

	public List findTransData(ElementConditionDto dto, RequestMeta meta) {
		List list = new ArrayList();
		if (dto.getPmAdjustCodeList() == null) {
			return list;
		}
		for (int i = 0; i < dto.getPmAdjustCodeList().size(); i++) {
			DataExchangeRedo redo = (DataExchangeRedo) dto
					.getPmAdjustCodeList().get(i);
			String id = redo.getRecordID();
			Map map = new HashMap();
			map.put("sheetId", id);
			ZcEbEntrust trustPo = zcEbEntrustDao.getZcEbEntrustBySn(id);
			list.add(trustPo);
			List detailList = zcEbEntrustDao.getZcEbEntrustDetailBySn(id);
			trustPo.setDetailList(detailList);
		}
		return list;
	}

	public void impEntrust(ZcEbEntrust entrust, RequestMeta meta) {
		zcEbEntrustDao.insertZcEbEntrust(entrust);
	}

	public boolean checkUniqueZcMakeCode(ZcEbEntrust zeas, RequestMeta meta) {
		return zcEbEntrustDao.checkUniqueZcMakeCode(zeas, meta);
	}

	public List getTodoListByUser(List compId, String userId) {
		// TCJLODO Auto-generated method stub
		List todo = new ArrayList();
		Map para = new HashMap();
		para.put("userId", userId);
		for (int i = 0; i < compId.size(); i++) {
			String table = (String) baseDao.read(
					"consoleChart.getCompoTableById", compId.get(i));
			if (table == null) {
				continue;
			}
			para.put("table", table);
			para.put("compo_id", compId.get(i));
			String compoid = (String) baseDao.read(
					"consoleChart.getCompoCurrentTaskByCompoAndUserId", para);
			if (compoid != null && !"".equals(compoid)) {
				todo.add(compoid);
			}
		}
		return todo;
	}

   
  public List getZcEbEntrustExportData(List entrustIdLst, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    List list = new ArrayList();
    
    for(int i=0;i<entrustIdLst.size();i++){
      ZcEbEntrust trustPo = zcEbEntrustDao.getZcEbEntrustBySn(""+entrustIdLst.get(i));
      if(trustPo==null)continue;
      List detailList = zcEbEntrustDao.getZcEbEntrustDetailBySn(""+entrustIdLst.get(i));
      trustPo.setDetailList(detailList);
      list.add(trustPo);      
    }
    return list;
  }

  
  public String importTransDatasFN(ZcEbEntrust bill, RequestMeta meta) {
    // TCJLODO Auto-generated method stub
    baseDao.delete("ZcEbEntrust.deleteBySn", bill.getSn());
    baseDao.delete("ZcEbEntrust.deleteZcEbEntrustDetail", bill.getSn());
    
    baseDao.insert("ZcEbEntrust.insertZcEbEntrust", bill);
    if(bill.getDetailList()!=null && bill.getDetailList().size()>0){
      for(int i=0;i<bill.getDetailList().size();i++){
        ZcEbEntrustDetail d=(ZcEbEntrustDetail)bill.getDetailList().get(i);
        baseDao.insert("ZcEbEntrust.insertZcEbEntrustDetail", d);
      }
    }
    return "导入采购任务成功";
  }
}
