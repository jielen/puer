/**
 * ForeignEntityTreeDialog.java
 * com.ufgov.gk.client.component.zc.fieldeditor
 * Administrator
 * 2010-5-6
 */
package com.ufgov.zc.client.component.zc.fieldeditor;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.JButtonTextField;
import com.ufgov.zc.client.component.JTreeSelectDialog;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.foreignentity.IForeignEntityTreeHandler;
import com.ufgov.zc.common.zc.model.TreeNodeValueObject;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class ForeignEntityTreeDialog extends JTreeSelectDialog {

  private static final Object ROOT_NAME = "请选择";

  private IForeignEntityTreeHandler handler;

  private String title;

  public ForeignEntityTreeDialog(Dialog dialog, boolean modal, JButtonTextField triggerField, IForeignEntityTreeHandler handler,
    String sqlMapSelectedId, String title) {
    super(dialog, modal, triggerField, sqlMapSelectedId, title);
    //    System.out.println("the title is "+title);
    this.handler = handler;
    this.title = title;
    // TCJLODO Auto-generated constructor stub
  }

  @Override
  protected void initTitle() {
    LangTransMeta.init("ZC%");
  }

  @Override
  protected void initDataBufferList() {

    IZcEbBaseServiceDelegate baseDataServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class,
      "zcEbBaseServiceDelegate");
    RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();
    ElementConditionDto dto = new ElementConditionDto();
    int nd = WorkEnv.getInstance().getTransNd();
    dto.setNd(nd);
    this.dataBufferList = baseDataServiceDelegate.getForeignEntitySelectedData(this.sqlMapSelectedId, dto, requestMeta);

    if (dto.getNumLimitStr() == null && dto.getDataRuleCondiStr() == null) {
      numLimDataList = dataBufferList;
    } else {
      numLimDataList = baseDataServiceDelegate.getForeignEntitySelectedData(this.sqlMapSelectedId, dto, requestMeta);
    }
  }

  private List genTreeData() {

    List filteredList = genFilterDataList();

    Map map = new HashMap();

    for (Object o : filteredList) {
      TreeNodeValueObject cpy = (TreeNodeValueObject) o;
      map.put(cpy.getCode(), cpy);
    }

    List rootNodeList = new ArrayList();
    List childrenNodeList = new ArrayList();
    for (Object o : filteredList) {
      TreeNodeValueObject cpy = (TreeNodeValueObject) o;
      if (map.get(cpy.getParentCode()) == null) {
        rootNodeList.add(cpy);
      } else {
        childrenNodeList.add(cpy);
      }
    }

    Map childrenMap = new HashMap();

    for (int i = 0; i < childrenNodeList.size(); i++) {
      TreeNodeValueObject child = (TreeNodeValueObject) childrenNodeList.get(i);

      List childrenList = (List) childrenMap.get(child.getParentCode());
      if (childrenList != null) {
        childrenList.add(child);
      } else {
        List tempList = new ArrayList();
        tempList.add(child);
        childrenMap.put(child.getParentCode(), tempList);
      }
    }
    for (int i = 0; i < rootNodeList.size(); i++) {
      TreeNodeValueObject node = (TreeNodeValueObject) rootNodeList.get(i);
      this.setNodeChildren(node, childrenMap);
    }
    return rootNodeList;
  }

  private List genFilterDataList() {
    return this.dataBufferList;
  }

  private void setNodeChildren(TreeNodeValueObject node, Map childrenMap) {

    List childrenList = (List) childrenMap.get(node.getCode());
    if (childrenList != null) {
      node.setChildrenList(childrenList);
      for (int i = 0; i < childrenList.size(); i++) {
        TreeNodeValueObject c = (TreeNodeValueObject) childrenList.get(i);
        setNodeChildren(c, childrenMap);
      }
    }
  }

  @Override
  protected void initSelectTree() {
    isSelectTailTag = true;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.ROOT_NAME);
    this.triggerField.filteredDataList = this.genFilterDataList();
    List objTreeList = genTreeData();

    treeNodeMap.clear();

    for (Object o : objTreeList) {
      TreeNodeValueObject nodeValueObj = (TreeNodeValueObject) o;

      DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeValueObj);
      root.add(node);
      treeNodeMap.put(nodeValueObj, node);
      this.setChildNode(nodeValueObj, node);

    }
    this.getSelectTree().setModel(new DefaultTreeModel(root));
  }

  /* (non-Javadoc)
   * @see com.ufgov.gk.client.component.JTreeSelectDialog#isNumLimContain(com.ufgov.gk.common.commonbiz.model.BaseElement)
   */
  @Override
  protected boolean isNumLimContain(BaseElement element) {
    // TCJLODO Auto-generated method stub
    return true;
  }

  private void setChildNode(TreeNodeValueObject nodeValueObj, DefaultMutableTreeNode node) {
    //    System.out.println(nodeValueObj.getCode()+"\t"+nodeValueObj.getName()+"\t"+nodeValueObj.getChildrenList().size());
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

  public void doOK() {

    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectTree.getLastSelectedPathComponent();
    if (node == null) {
      return;
    }

    int selected = selectTree.getSelectionCount();

    if (selected == 0) {
      JOptionPane.showMessageDialog(self, "请选择数据!", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    } else if (selected > 1 && !this.handler.isMultipleSelect()) {
      JOptionPane.showMessageDialog(self, "只能选择一条数据！", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    //根节点
    if (node.getUserObject() instanceof String && this.ROOT_NAME.equals(node.getUserObject())) {
      JOptionPane.showMessageDialog(self, "请选择数据!", "提示", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    if (!node.isLeaf() && this.handler.isSelectLeaf()) {
      //      JOptionPane.showMessageDialog(self, "只能选择叶子节点!", "提示",
      //        JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    TreePath[] tps = selectTree.getSelectionPaths();
    ArrayList selectDatas = new ArrayList();
    for (TreePath tp : tps) {
      DefaultMutableTreeNode nd = (DefaultMutableTreeNode) tp.getLastPathComponent();
      selectDatas.add(nd.getUserObject());
    }
    //    l.add(node.getUserObject());
    this.handler.excute(selectDatas);
    //    triggerField.setValue(node.getUserObject());
    this.closeDialog();
  }
}
