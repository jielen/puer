package com.ufgov.zc.client.component.zc.fieldeditor;

import com.ufgov.zc.client.component.zc.AbstractMainSubEditPanel;
import com.ufgov.zc.client.component.zc.ZcEbProviderHandler;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;

public class ProviderFieldEditor extends ForeignEntityFieldEditor {

  public ProviderFieldEditor(AbstractMainSubEditPanel editPanel, String name, String fieldName){
    super("ZcEbSupplier.getSupplierListForHandler", new ElementConditionDto(), 20, new ZcEbProviderHandler(editPanel), ZcEbProviderHandler.getColumNames(), name, fieldName);
  }

  public ProviderFieldEditor(AbstractMainSubEditPanel editPanel,ElementConditionDto elementCondtiontDto, String name, String fieldName){
    super("ZcEbSupplier.getSupplierListForHandler", elementCondtiontDto, 20, new ZcEbProviderHandler(editPanel), ZcEbProviderHandler.getColumNames(), name, fieldName);
  }

  public ProviderFieldEditor(String sqlMapSelectedId, ElementConditionDto elementCondtiontDto, int col, IForeignEntityHandler handler,

  String[] columNames, String name, String fieldName) {

    super(sqlMapSelectedId, elementCondtiontDto, col, handler, columNames, name, fieldName);

  }
  public ProviderFieldEditor(String sqlMapSelectedId, int col, IForeignEntityHandler handler, String[] columNames, String name, String fieldName) {
    super(sqlMapSelectedId, col, handler, columNames, name, fieldName);
  }

}
