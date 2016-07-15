/**
 * 
 */
package com.ufgov.zc.client.component.element;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.JButtonTextField;
import com.ufgov.zc.client.component.JTreeSelectDialog;
import com.ufgov.zc.client.util.NumLimUtil;
import com.ufgov.zc.common.commonbiz.model.EAcc;
import com.ufgov.zc.common.commonbiz.publish.IBaseDataServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.constants.NumLimConstants;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 *
 */
public class EAccTreeSelectDialog extends JTreeSelectDialog {
  
  private String parentCode=null;

  public EAccTreeSelectDialog(Dialog owner, boolean modal,

  JButtonTextField triggerField) {

    super(owner, modal, triggerField);

  }
  public EAccTreeSelectDialog(Dialog owner, boolean modal,

    JButtonTextField triggerField,String parentCode) {

      super(owner, modal, triggerField);
      this.parentCode=parentCode;

    }
  protected void initTitle() {

    LangTransMeta.init("GK%");

    this.setTitle(LangTransMeta.translate("GK_TITLE_BACC"));

  }

  protected void initDataBufferList() {

    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory

    .create(IBaseDataServiceDelegate.class, "zcEbBaseServiceDelegate");

    int nd = WorkEnv.getInstance().getTransNd();

    RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();

    ElementConditionDto dto = new ElementConditionDto();

    dto.setNd(nd);
    
    dto.setZcText0(parentCode);

    dto.setNumLimitStr(

    NumLimUtil.getInstance().getNumLimCondByCoType(triggerField.compoId, NumLimConstants.FWATCH, triggerField.elementCode));

    
      this.dataBufferList = zcEbBaseServiceDelegate.getForeignEntitySelectedData("EAcc.getEAcc", dto, requestMeta);
      
      this.numLimDataList=dataBufferList;
    

    for (Object o : dataBufferList) {

      EAcc temp = (EAcc) o;

      this.triggerField.dataMap.put(temp.getCode(), temp);

    }

    this.triggerField.filteredDataList = dataBufferList;

  }

  private List genTreeData() {

    List filteredList = genFilteredDataList();

    Map map = new HashMap();

    for (Object o : filteredList) {

      EAcc temp = (EAcc) o;

      map.put(temp.getCode(), temp);

    }

    List rootBAccList = new ArrayList();

    List childrenBAccList = new ArrayList();

    for (Object o : filteredList) {

      EAcc temp = (EAcc) o;

      if (map.get(temp.getParentCode()) == null) {

        rootBAccList.add(temp);

      } else {

        childrenBAccList.add(temp);

      }

    }

    Map childrenMap = new HashMap();

    for (int i = 0; i < childrenBAccList.size(); i++) {

      EAcc child = (EAcc) childrenBAccList.get(i);

      List childrenList = (List) childrenMap.get(child.getParentCode());

      if (childrenList != null) {

        childrenList.add(child);

      } else {

        List tempList = new ArrayList();

        tempList.add(child);

        childrenMap.put(child.getParentCode(), tempList);

      }

    }

    for (int i = 0; i < rootBAccList.size(); i++) {

      EAcc root = (EAcc) rootBAccList.get(i);

      this.setBAccChildren(root, childrenMap);

    }

    return rootBAccList;

  }

  private List genFilteredDataList() {

    List prefixFilteredBAccList = new ArrayList();

    List levelCtrlFilteredBAccList = new ArrayList();

    if (!this.triggerField.isRandomEdit()) {

      if (this.triggerField.getPrefix() == null) {

        this.triggerField.setPrefix("");

      }

      for (Object o : dataBufferList) {

        EAcc temp = (EAcc) o;

        if (temp.getCode().startsWith(this.triggerField.getPrefix())) {

          prefixFilteredBAccList.add(temp);

        }

      }

    }

    if (this.triggerField.isLevelCtrl()) {

      for (Object o : dataBufferList) {

        EAcc temp = (EAcc) o;

        if (temp.getCode().length() <= this.triggerField.getCtrlLen()) {

          levelCtrlFilteredBAccList.add(temp);

        }

      }

    }

    List filteredList = null;

    if (this.triggerField.isRandomEdit() && !this.triggerField.isLevelCtrl()) {

      filteredList = this.dataBufferList;

    } else if (!this.triggerField.isRandomEdit() && !this.triggerField.isLevelCtrl()) {

      filteredList = prefixFilteredBAccList;

    } else if (this.triggerField.isRandomEdit() && this.triggerField.isLevelCtrl()) {

      filteredList = levelCtrlFilteredBAccList;

    } else {

      filteredList = new ArrayList();

      for (Object o : prefixFilteredBAccList) {

        if (levelCtrlFilteredBAccList.contains(o)) {

          filteredList.add(o);

        }

      }

    }

    return filteredList;

  }

  private void setBAccChildren(EAcc bAcc, Map childrenMap) {

    List childrenList = (List) childrenMap.get(bAcc.getCode());

    if (childrenList != null) {

      bAcc.setChildren(childrenList);

      for (int i = 0; i < childrenList.size(); i++) {

        EAcc c = (EAcc) childrenList.get(i);

        setBAccChildren(c, childrenMap);

      }

    }

  }

  protected void initSelectTree() {

    DefaultMutableTreeNode root = new DefaultMutableTreeNode("经济分类");

    this.triggerField.filteredDataList = this.genFilteredDataList();

    List bAccList = genTreeData();

    treeNodeMap.clear();

    for (Object o : bAccList) {

      EAcc bAcc = (EAcc) o;

      if (isNumLimContain(bAcc)) {

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(bAcc);

        root.add(node);

        treeNodeMap.put(bAcc, node);

        this.setChildNode(bAcc, node);

      }

    }

    this.getSelectTree().setModel(new DefaultTreeModel(root));

  }

  private void setChildNode(EAcc bAcc, DefaultMutableTreeNode node) {

    if (bAcc.getChildren().size() > 0) {

      for (Object o : bAcc.getChildren()) {

        EAcc c = (EAcc) o;

        if (isNumLimContain(c)) {

          DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(c);

          node.add(childNode);

          treeNodeMap.put(c, childNode);

          setChildNode(c, childNode);

        }

      }

    }

  }

  public void doOK() {

    if (this.triggerField.isLevelCtrl()) {

      DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectTree

      .getLastSelectedPathComponent();

      if (node == null) {

        return;

      }

      if (!node.isLeaf()) {

        JOptionPane.showMessageDialog(self, "只能选择叶子节点!", "提示",

        JOptionPane.INFORMATION_MESSAGE);

        return;

      }

    }

    super.doOK();

  }

}
