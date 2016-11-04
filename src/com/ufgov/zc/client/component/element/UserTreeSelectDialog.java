/**
 * 
 */
package com.ufgov.zc.client.component.element;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import com.ufgov.zc.client.common.LangTransMeta;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.sf.entrust.SfEntrustEditPanel;
import com.ufgov.zc.client.sf.jddocaudit.SfJdDocAuditEditPanel;
import com.ufgov.zc.common.commonbiz.model.BaseElement;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.zc.publish.IZcEbBaseServiceDelegate;

/**
 * @author Administrator
 * 
 */
public class UserTreeSelectDialog extends GkBaseDialog {

	protected Logger log = Logger.getLogger(this.getClass());

	protected UserTreeSelectDialog self = this;

	// protected JButtonTextField triggerField;

	private JPanel bottomPanel = new JPanel();

	private JPanel centerPanel = new JPanel();

	private JButton okButton = new JButton("确定");

	// private JButton clearButton = new JButton("清空");

	private JButton cancelButton = new JButton("取消");

	private JComponent handler = null;

	protected JTree selectTree = new JTree();

	protected List dataBufferList = new ArrayList();

	protected List numLimDataList = new ArrayList();

	protected String sqlMapSelectedId;

	protected boolean isSelectTailTag;

	protected boolean isMultiSelect;

	protected ElementConditionDto selectedConditonDto=null;
	
  protected JPanel searchBar = new JPanel() {

    {

      // this.setFloatable(false);

      this.setLayout(new FlowLayout(FlowLayout.LEFT));

    }

  };

  private JLabel searchLabel = new JLabel("查找：");

  protected JTextField searchField = new JTextField(30);

  protected JButton searchButton = new JButton("查找");

  protected Map<Object, DefaultMutableTreeNode> treeNodeMap = new HashMap<Object, DefaultMutableTreeNode>();

  private String title="选择用户";
  
  private String sqlId="com.ufgov.zc.server.sf.dao.SfEntrustMapper.selectJdzxUsers";

	/**

	   * 

	   */

	private static final long serialVersionUID = -3594849692436568807L;

  public UserTreeSelectDialog(Dialog owner, boolean modal,
      JComponent handler, boolean isSelectTailTag, boolean isMultiSelect) {

    super(owner, modal);

    this.handler = handler;

    this.isSelectTailTag = isSelectTailTag;

    this.isMultiSelect = isMultiSelect; 

    init();

  }

	public UserTreeSelectDialog(Dialog owner, boolean modal,
			JComponent handler, boolean isSelectTailTag, boolean isMultiSelect,
			String sqlId,ElementConditionDto selectedConditon) {

		super(owner, modal);

		this.handler = handler;

		this.isSelectTailTag = isSelectTailTag;

		this.isMultiSelect = isMultiSelect;

		this.selectedConditonDto = selectedConditon;
		
		this.sqlId=sqlId;

		init();

	}

	protected void init() {

/*		new Thread() {

			public void run() {

				initDataBufferList();

				// getTriggerField().countDown();

			}

		}.start();*/
		initDataBufferList();

		this.initTitle();

		this.initSearchBar();

		this.initCenterPanel();

		this.initBottomPanel();

		this.getContentPane().setLayout(new BorderLayout());

		this.add(searchBar, BorderLayout.NORTH);

		this.add(centerPanel, BorderLayout.CENTER);

		this.add(bottomPanel, BorderLayout.SOUTH);

		selectTree.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					int rowLocation = selectTree.getRowForLocation(e.getX(),
							e.getY());

					TreePath treepath = selectTree.getPathForRow(rowLocation);

					if (treepath == null) {

						return;

					}

					DefaultMutableTreeNode node = (DefaultMutableTreeNode) treepath
							.getLastPathComponent();

					if (!isSelectTailTag) {

						if (node.getUserObject() instanceof String) {

							return;

						}

						// triggerField.setValue(node.getUserObject());
						List lst=new ArrayList();
						lst.add(node.getUserObject());

						setValueToHandler(lst);

						closeDialog();

					}

					// DefaultMutableTreeNode node = (DefaultMutableTreeNode)
					// selectTree.getLastSelectedPathComponent();

					if (node.getChildCount() > 0) {

					} else {

						doOK();

					}

				}

			}

		});

		this.setSize(550, 500);

		this.moveToScreenCenter();

	}

	void setValueToHandler(List userObjLst) {
		this.dispose();
		if(handler instanceof SfEntrustEditPanel){
			SfEntrustEditPanel entrustPanel=(SfEntrustEditPanel)handler;
			entrustPanel.auditWithNextExcuter(userObjLst);
		}else if(handler instanceof SfJdDocAuditEditPanel){
		  SfJdDocAuditEditPanel docPanel=(SfJdDocAuditEditPanel)handler;
		  docPanel.auditWithNextExcuter(userObjLst);
		}
	}


	private void initSearchBar() {

		searchBar.add(searchLabel);

		searchBar.add(searchField);

		searchBar.add(searchButton);

		searchField.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					doSearch();

				}

			}

		});

		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				doSearch();

			}

		});

	}

	protected void doSearch() {

		int rowCount = selectTree.getRowCount();

		String searchString = this.searchField.getText().trim();

		if (searchString.equals("") || searchString.equals("[")
				|| searchString.equals("]")) {

			return;

		}

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectTree
				.getLastSelectedPathComponent();

		if (node == null || node.getNextNode() == null) {

			node = (DefaultMutableTreeNode) selectTree.getModel().getRoot();

		}

		DefaultMutableTreeNode currentNode = node;

		node = node.getNextNode();

		if (node == null)

			return;

		while (node != currentNode) {

			Object object = node.getUserObject();

			if (object.toString().indexOf(searchString) >= 0) {

				for (int i = rowCount - 1; i > 0; i--) {

					selectTree.collapseRow(i);

				}

				selectTree.setSelectionPath(new TreePath(node.getPath()));

				// /

				int[] selectedRows = selectTree.getSelectionRows();

				if (selectedRows.length > 0) {

					selectTree.scrollRowToVisible(selectedRows[0]);

				}

				return;

			}

			node = node.getNextNode();

			if (node == null)

				node = (DefaultMutableTreeNode) selectTree.getModel().getRoot();

		}

		// 在采购目录中输入关键字，如果没有匹配数据，弹出没有记录提示框—guoss

		if (node.getUserObject().toString().indexOf(searchString) <= 0) {

			JOptionPane.showMessageDialog(self, "没有记录!", "提示",
					JOptionPane.INFORMATION_MESSAGE);

		}

		// if (node == currentNode) {

		// JOptionPane.showMessageDialog(self, "没有记录!", "提示",

		// JOptionPane.INFORMATION_MESSAGE);

		// }

	}

	// protected void doSearch2() {

	//

	// int rowCount = selectTree.getRowCount();

	// for (int i = rowCount - 1; i > 0; i--) {

	// selectTree.collapseRow(i);

	// }

	// selectTree.clearSelection();

	//

	// String searchString = this.searchField.getText().trim();

	// if (searchString.equals("") || searchString.equals("[")

	// || searchString.equals("]")) {

	// return;

	// }

	// DefaultMutableTreeNode

	// rootNode=(DefaultMutableTreeNode)selectTree.getModel().getRoot();

	// Enumeration enu=rootNode.preorderEnumeration();

	// Set<Object> keyset = treeNodeMap.keySet();

	// List<DefaultMutableTreeNode> matchNodes = new

	// ArrayList<DefaultMutableTreeNode>();

	//

	// while (enu.hasMoreElements()) {

	// DefaultMutableTreeNode node=(DefaultMutableTreeNode)enu.nextElement();

	// Object object=node.getUserObject();

	// if (object.toString().indexOf(searchString) >= 0) {

	// matchNodes.add(node);

	// }

	// }

	//

	// if(matchNodes.size()>500){

	// matchNodes=matchNodes.subList(0, 500);

	// }

	//

	// TreePath[] matchTreePaths = new TreePath[matchNodes.size()];

	// for (int i = 0; i < matchTreePaths.length; i++) {

	// matchTreePaths[i] = new TreePath(matchNodes.get(i).getPath());

	// }

	// selectTree.setSelectionPaths(matchTreePaths);

	//

	// int[] selectedRows=selectTree.getSelectionRows() ;

	//

	// if(selectedRows.length>0){

	// selectTree. scrollRowToVisible(selectedRows[0]);

	// }

	// }

	protected JTree getSelectTree() {

		return selectTree;

	}

	protected void setSelectTree(JTree selectTree) {

		this.selectTree = selectTree;

	}

	protected void initTitle() {

		LangTransMeta.init("SF%");

		this.setTitle(title);
	}

	protected void initDataBufferList() { 

	    IZcEbBaseServiceDelegate zcEbBaseServiceDelegate = (IZcEbBaseServiceDelegate) ServiceFactory.create(IZcEbBaseServiceDelegate.class, "zcEbBaseServiceDelegate");
	    
	    int nd = WorkEnv.getInstance().getTransNd();

	    RequestMeta requestMeta = WorkEnv.getInstance().getRequestMeta();
 
//	    dto.setNd(nd);
//	    dto.setCoCode(requestMeta.getSvCoCode());
	    if(selectedConditonDto==null){
	      selectedConditonDto=new ElementConditionDto();
	      selectedConditonDto.setNd(requestMeta.getSvNd());
	      selectedConditonDto.setCoCode(requestMeta.getSvCoCode());	      
	    }
	    
	    this.dataBufferList=zcEbBaseServiceDelegate.queryDataForList(sqlId, selectedConditonDto, requestMeta); 
	    dataBufferList=dataBufferList==null?new ArrayList():dataBufferList;
 
	}

	protected void initSelectTree() {

	    DefaultMutableTreeNode root = new DefaultMutableTreeNode("人员"); 
	    HashMap<String, List<HashMap>>  userTreeMaps = genTreeData();

	    treeNodeMap.clear();

	    Iterator<String> orgs=userTreeMaps.keySet().iterator();
	    
	    while(orgs.hasNext()){
	    	String orgCode=orgs.next();
	    	List<HashMap> orgUsers=userTreeMaps.get(orgCode);
	    	BaseElement e=new BaseElement();
	    	e.setCode(orgCode);
	    	HashMap row=orgUsers.get(0);
	    	e.setName((String) row.get("ORG_NAME"));
	    	DefaultMutableTreeNode node = new DefaultMutableTreeNode(e);
	        root.add(node);
	        
	        setNodeChilds(node,e,orgUsers);
	    	
	    } 

	    this.getSelectTree().setModel(new DefaultTreeModel(root));

	}
	 private void setNodeChilds(DefaultMutableTreeNode node, BaseElement e,List<HashMap> orgUsers) {
		 for(int i=0;i<orgUsers.size();i++){
			 HashMap row=orgUsers.get(i);
			 BaseElement user=new BaseElement();
			 user.setCode((String) row.get("EMP_CODE"));
			 user.setName((String) row.get("USER_NAME"));
			 DefaultMutableTreeNode child = new DefaultMutableTreeNode(user);
			 node.add(child);
		 }
	}

	private HashMap<String, List<HashMap>> genTreeData() {
 
		    
		    HashMap<String, List<HashMap>> usersMap=new HashMap<String, List<HashMap>>(); 
		    
		 for(int i=0;i<dataBufferList.size();i++){
			 HashMap row=(HashMap) dataBufferList.get(i);
			 String orgCode=(String) row.get("ORG_CODE");
			 if(usersMap.containsKey(orgCode)){
				 usersMap.get(orgCode).add(row);
			 }else{
				 List childs=new ArrayList<HashMap>();
				 childs.add(row);
				 usersMap.put(orgCode, childs);
			 }
		 } 

		    return usersMap;

		  }
	public synchronized void setVisible(boolean b) {

		if (b) {

			initSelectTree();

			initSelection();

		}

		super.setVisible(b);

	}

	private void initSelection() {
		/*
		 * Object oldValue = this.getTriggerField().getValue();
		 * 
		 * TreePath path = null;
		 * 
		 * if (oldValue != null && treeNodeMap.get(oldValue) != null) {
		 * 
		 * path = new TreePath((treeNodeMap.get(oldValue)).getPath());
		 * 
		 * this.getSelectTree().setSelectionPath(path);
		 * 
		 * }
		 */

	}

	private void initCenterPanel() {

		centerPanel.setLayout(new BorderLayout());

		centerPanel.add(new JScrollPane(selectTree), BorderLayout.CENTER);

	}

	private void initBottomPanel() {

		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				self.closeDialog();

			}

		});

		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				self.doOK();

			}

		});

		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		bottomPanel.add(okButton);

		bottomPanel.add(cancelButton);

	}

	public void doOK() {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectTree.getLastSelectedPathComponent();

		int selected = selectTree.getSelectionCount();

		if (selected == 0) {
			JOptionPane.showMessageDialog(self, "请选择人员!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		} else if (selected > 1 && !isMultiSelect) {
			JOptionPane.showMessageDialog(self, "只能选择一个人！", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		if (selected==1 && node.getChildCount() > 0 && isSelectTailTag) {

			JOptionPane.showMessageDialog(self, "请选择人员!", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (node.getUserObject() instanceof String) {
			return;
		}
		List users=new ArrayList();
		if(isMultiSelect){
			TreePath[] selectPaths=selectTree.getSelectionPaths();
			for(int i=0;i<selectPaths.length;i++){
				DefaultMutableTreeNode nd = (DefaultMutableTreeNode)selectPaths[i].getLastPathComponent();
				if (node.getChildCount() > 0 && isSelectTailTag) {
					continue;
				}
				users.add(nd.getUserObject());
			}
		}
		
		if(users.size()==0){
			JOptionPane.showMessageDialog(self, "请选择人员!", "提示",JOptionPane.INFORMATION_MESSAGE);
			return;			
		}

		// triggerField.setValue(node.getUserObject());

		setValueToHandler(users);

		closeDialog();

	}

	protected boolean isNumLimContain(BaseElement element) {

		boolean flag = false;

		if (this.numLimDataList.contains(element)) {

			flag = true;

		} else {

			for (Object o : element.getChildren()) {

				BaseElement c = (BaseElement) o;

				if (isNumLimContain(c)) {

					flag = true;

				}

			}

		}

		return flag;

	}

	public Map<Object, DefaultMutableTreeNode> getTreeNodeMap() {

		return treeNodeMap;

	}

	public void setTreeNodeMap(Map<Object, DefaultMutableTreeNode> treeNodeMap) {

		this.treeNodeMap = treeNodeMap;

	}

}