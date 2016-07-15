package com.ufgov.zc.client.sf.dataflow;

import java.math.BigDecimal;

import javax.swing.JComponent;

import com.ufgov.zc.common.sf.model.SfEntrust;
import com.ufgov.zc.common.system.RequestMeta;

public interface ISfFlowNodeBusiness {

  void excute(SfDataFowPanel flowPanel,SfFlowNode node,SfEntrust entrust, RequestMeta meta);
  boolean isEnable(SfEntrust entrust,RequestMeta meta);
}
