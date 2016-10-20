/**
 * 
 */
package com.ufgov.zc.client.component.sf.fieldeditor;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.JButtonTextField;
import com.ufgov.zc.client.component.JTreeSelectDialog;
import com.ufgov.zc.client.component.zc.fieldeditor.ForeignEntityDataCache;
import com.ufgov.zc.client.component.zc.fieldeditor.TextFieldEditor;
import com.ufgov.zc.common.sf.model.ZcFaCardType;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityHandler;
import com.ufgov.zc.common.zc.model.TreeNodeValueObject;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class ZcFaTypeSelectDialog extends JTreeSelectDialog {

  private static Logger log=Logger.getLogger(ZcFaTypeSelectDialog.class);
  protected TextFieldEditor triggerFieldName;

  protected boolean selectedTailFlag = false;

  private ElementConditionDto dto;
  
  IForeignEntityHandler handler;



  public ZcFaTypeSelectDialog(Dialog dialog, boolean modal, JButtonTextField triggerField, boolean selectedTailFlag,ElementConditionDto dto, IForeignEntityHandler handler) {

    super(dialog, modal, triggerField, selectedTailFlag,dto);

    this.triggerFieldName = triggerFieldName;

    this.selectedTailFlag = selectedTailFlag;   
    
    this.handler=handler;
   

  }


  private static final long serialVersionUID = -4407933154954926841L;

  public void initTitle() {

    LangTransMeta.init("ZC%");

    this.setTitle("资产类别");

  }

  public void initDataBufferList() {

    IZcEbBaseServiceDelegate delegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");

    RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();
  
    //因为这个数据是动态的，根据不同的条件获取类别，所以缓存时，加上了条件
    dataBufferList = (List) ForeignEntityDataCache.getDataMap().get(ForeignEntityDataCache.CACHE_ZC_FA_TYPE+selectedConditon.getZcText1());

    if (dataBufferList == null || dataBufferList.size() == 0) {    
        dataBufferList = delegate.queryDataForList("ZcFaCard.getCardTypeLst", selectedConditon, requestMeta);
        ForeignEntityDataCache.setData(ForeignEntityDataCache.CACHE_ZC_FA_TYPE+selectedConditon.getZcText1(), dataBufferList);
    }

    for (int i = 0; i < dataBufferList.size(); i++) {

      ZcFaCardType rowData = (ZcFaCardType) dataBufferList.get(i);

      this.triggerField.dataMap.put(rowData.getFatypeCode(), rowData);

    }

    this.triggerField.filteredDataList = dataBufferList;

    initSelectTree();

  }

  public void doClear(){
  
    triggerField.setValue(null);
  
    selectTree.clearSelection();
    try {
  
      // 反射调用handler实现类的afterClear方法，为了兼容以前的接口!
  
      // afterClear方法主要是在实现类中做清空后的操作。
  
      MethodUtils.invokeMethod(this.handler, "afterClear", null);
  
    } catch (Exception ex) {
      log.error("执行handler的afterClear方法出错"+ex.getMessage(),ex);
    }
    closeDialog();
  }

  public void doOK() {

    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectTree.getLastSelectedPathComponent();

    int selected = selectTree.getSelectionCount();

    if (selected == 0) {

      JOptionPane.showMessageDialog(self, "请选择数据!", "提示", JOptionPane.INFORMATION_MESSAGE);

      return;

    } else if (selected > 1) {

      JOptionPane.showMessageDialog(self, "只能选择一条数据！", "提示", JOptionPane.INFORMATION_MESSAGE);

      return;

    }

    if (node.getUserObject() instanceof String) {

      return;

    }

    if (node.getChildCount() > 0 && isSelectTailTag) {

      JOptionPane.showMessageDialog(self, "请选择末级节点!", "提示", JOptionPane.INFORMATION_MESSAGE);

      return;

    }

    triggerField.setValue(node.getUserObject());
    List lst=new ArrayList();
    lst.add(node.getUserObject());
    this.handler.excute(lst);

    closeDialog();

  }
  private List genTreeData() {

    List filteredList = dataBufferList;

    Map map = new HashMap();

    for (Object o : filteredList) {

      ZcFaCardType faType = (ZcFaCardType) o;

      map.put(faType.getCode(), faType);

    }

    List rootFaTypeList = new ArrayList();

    List childrenFaTypeList = new ArrayList();

    for (Object o : filteredList) {

      TreeNodeValueObject cpy = (TreeNodeValueObject) o;

      if (map.get(cpy.getParentCode()) == null) {

        rootFaTypeList.add(cpy);

      } else {

        childrenFaTypeList.add(cpy);

      }

    }

    Map childrenMap = new HashMap();

    for (int i = 0; i < childrenFaTypeList.size(); i++) {

      TreeNodeValueObject child = (TreeNodeValueObject) childrenFaTypeList.get(i);

      List childrenList = (List) childrenMap.get(child.getParentCode());

      if (childrenList != null) {

        childrenList.add(child);

      } else {

        List tempList = new ArrayList();

        tempList.add(child);

        childrenMap.put(child.getParentCode(), tempList);

      }

    }

    for (int i = 0; i < rootFaTypeList.size(); i++) {

      ZcFaCardType faType = (ZcFaCardType) rootFaTypeList.get(i);

      this.setFaTypeChildren(faType, childrenMap);

    }

    return rootFaTypeList;

  }

  protected void initSelectTree() {

    DefaultMutableTreeNode root = new DefaultMutableTreeNode("资产类别");

    this.triggerField.filteredDataList = dataBufferList;//this.genFilterDataList()

    List companyTreeList = genTreeData();

    treeNodeMap.clear();

    for (Object o : companyTreeList) {

      TreeNodeValueObject nodeValueObj = (TreeNodeValueObject) o;

      DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeValueObj);

      root.add(node);

      treeNodeMap.put(nodeValueObj, node);

      this.setChildNode(nodeValueObj, node);

    }

    this.getSelectTree().setModel(new DefaultTreeModel(root));

  }

  private void setFaTypeChildren(ZcFaCardType company, Map childrenMap) {

    List childrenList = (List) childrenMap.get(company.getCode());

    if (childrenList != null) {

      company.setChildrenList(childrenList);

      for (int i = 0; i < childrenList.size(); i++) {

        ZcFaCardType c = (ZcFaCardType) childrenList.get(i);

        setFaTypeChildren(c, childrenMap);

      }

    }

  }

  private void setChildNode(TreeNodeValueObject nodeValueObj, DefaultMutableTreeNode node) {

    if (nodeValueObj.getChildrenList().size() > 0) {

      for (Object o : nodeValueObj.getChildrenList()) {

        TreeNodeValueObject c = (TreeNodeValueObject) o;

        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(c);

        node.add(childNode);

        treeNodeMap.put(c, childNode);

        setChildNode(c, childNode);

      }

    }

  }

}
