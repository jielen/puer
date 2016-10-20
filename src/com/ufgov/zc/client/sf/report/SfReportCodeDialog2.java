/**
 * 
 */
package com.ufgov.zc.client.sf.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.ufgov.smartclient.component.table.JGroupableTable;
import com.ufgov.zc.client.common.MyTableModel;
import com.ufgov.zc.client.common.ServiceFactory;
import com.ufgov.zc.client.common.WorkEnv;
import com.ufgov.zc.client.common.converter.sf.SfReportCodeToTableModelConverter;
import com.ufgov.zc.client.component.GkBaseDialog;
import com.ufgov.zc.client.component.JTablePanel;
import com.ufgov.zc.client.util.ListUtil;
import com.ufgov.zc.common.sf.exception.SfBusinessException;
import com.ufgov.zc.common.sf.model.SfJdDocCode;
import com.ufgov.zc.common.sf.publish.ISfJdDocCodeServiceDelegate;
import com.ufgov.zc.common.system.RequestMeta;
import com.ufgov.zc.common.system.dto.ElementConditionDto;
import com.ufgov.zc.common.system.util.ObjectUtil;

/**
 * 这个支持统一编号，即全所一个编号
 * @author Administrator
 * 
 */
public class SfReportCodeDialog2 extends GkBaseDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3561415031309188270L;

	JRadioButton oldGroupButton = null;

	JRadioButton newGroupButton = null;

	JButton confirmBtn = null;
	JButton cancelBtn = null;

	JTextField gongTxt = new JTextField();
	JTextField jianTxt = new JTextField();
	JTextField ziTxt = new JTextField();
	JTextField ndTxt = new JTextField();
	JTextField numTxt=new JTextField("1");
	
	Border defaultTxtBord=gongTxt.getBorder();

	JTablePanel codeTable = new JTablePanel();
	
	RequestMeta meta=null;
	SfJdReportEditPanel sfJdReportEditPanel=null;
	
	ISfJdDocCodeServiceDelegate sfJdDocCodeServiceDelegate=null;

	public SfReportCodeDialog2(Window parent,SfJdReportEditPanel reportPanel) {
		super(parent,"鉴定意见书/报告编号",Dialog.ModalityType.APPLICATION_MODAL);
		sfJdReportEditPanel=reportPanel;
		sfJdDocCodeServiceDelegate=(ISfJdDocCodeServiceDelegate) ServiceFactory	.create(ISfJdDocCodeServiceDelegate.class,"sfJdDocCodeServiceDelegate");
		init();
		refreshData();
		updateEditable();
	}

	private void updateEditable() {
		ndTxt.setEnabled(false);
	}

	private void refreshData() {
		ndTxt.setText(""+meta.getSvNd());
		ElementConditionDto dto=new ElementConditionDto();
		dto.setCoCode(meta.getSvCoCode());
		dto.setNd(meta.getSvNd());
		
		List codeLst=sfJdDocCodeServiceDelegate.getMainDataLst(dto, meta);
		
		codeTable.setTableModel(SfReportCodeToTableModelConverter.convertMainLst(codeLst==null?new ArrayList():codeLst));
		codeTable.getTable().getColumn("全名").setWidth(200); 
		if(codeLst!=null && codeLst.size()>0){
			SfJdDocCode dc=(SfJdDocCode) codeLst.get(0);
			numTxt.setText(dc.getNum().intValue()+"");
		}
	}

	private void init() {
		meta=WorkEnv.getInstance().getRequestMeta();
		initSwing();
	}

	private void initSwing() {

		
		oldGroupButton = new JRadioButton("选择编号");
		newGroupButton = new JRadioButton("新建编号");
		ButtonGroup btgp = new ButtonGroup();
		btgp.add(newGroupButton);
		btgp.add(oldGroupButton);

		JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "选择文书编号",
			      TitledBorder.CENTER, TitledBorder.TOP, new Font("宋体", Font.BOLD, 15), Color.BLUE));
		// this.getContentPane().
		p.add(createNewCodePanel(), BorderLayout.NORTH);
		p.add(createOldCodePanel(), BorderLayout.CENTER);
		p.add(createBtnPanel(), BorderLayout.SOUTH);
		
		p.setPreferredSize(new Dimension(800, 400));
		add(p);
	}

	// 创建

	private Component createBtnPanel() {
		confirmBtn = new JButton("确定");
		confirmBtn.setPreferredSize(new Dimension(60, 26));
		cancelBtn = new JButton("取消");
		cancelBtn.setPreferredSize(new Dimension(60, 26));

		confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doConfirm();
			}
		});

		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				doCancel();
			}
		});

		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(confirmBtn);
		p.add(cancelBtn);

		return p;
	}

	protected void doCancel() {
		dispose();
	}

	protected void doConfirm() {
		String code="";
		boolean sucess=false;
		if(newGroupButton.isSelected()){
			sucess=createNewCode(code);
		}else if(oldGroupButton.isSelected()){
			sucess=selectNewCode(code); 
		}else{
			JOptionPane.showMessageDialog(this, "请点击选中 新建编号 或 选中编号 圆圈.", "提示",JOptionPane.INFORMATION_MESSAGE);
		}

		if(sucess){ 
			dispose();			
		} 
	}

	private boolean selectNewCode(String code) {
		JGroupableTable table = codeTable.getTable();
		MyTableModel model = (MyTableModel) table.getModel();
	    //Modal的数据
		List<SfJdDocCode> beanList = new ArrayList<SfJdDocCode>();
	    List list = model.getList();
	    Integer[] checkedRows = table.getCheckedRows();
	    if(checkedRows.length!=1){
	    	JOptionPane.showMessageDialog(this, "请选择或者新建一个编号.", "提示",JOptionPane.INFORMATION_MESSAGE);
	    	return false;
	    }
	     
	      int accordDataRow = table.convertRowIndexToModel(checkedRows[0]);
	      SfJdDocCode docCode = (SfJdDocCode) list.get(accordDataRow);

	      int num = JOptionPane.showConfirmDialog(this, "确定使用编号:  "+docCode.getCode()+"  ?", "确认", 0);
			if (num == JOptionPane.YES_OPTION) {
				boolean save=saveCode(docCode);
				if(save){
					code=docCode.getCode();	
					sfJdReportEditPanel.setNo(code);		
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			} 
	}

	private boolean createNewCode(String code) {
		boolean check=checkNewCode();
		if(!check){
			return false;
		} 
		
		SfJdDocCode docCode=new SfJdDocCode();
		docCode.setGongZi(gongTxt.getText());
		docCode.setJianZi1(jianTxt.getText());
		docCode.setJianZi2(ziTxt.getText());
		docCode.setNd(Integer.decode(ndTxt.getText()));
		docCode.setNum(new BigDecimal(numTxt.getText()));
		docCode.setCoCode(meta.getSvCoCode());
		docCode.buildPinjie();
		
		int num = JOptionPane.showConfirmDialog(this, "确定使用编号:  "+docCode.getCode()+"  ?", "确认", 0);
		if (num == JOptionPane.YES_OPTION) {
			boolean save=saveCode(docCode);
			if(save){
				code=docCode.getCode();	
				sfJdReportEditPanel.setNo(code);
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	private boolean saveCode(SfJdDocCode docCode) {
		try {
			sfJdDocCodeServiceDelegate.saveFN(docCode, meta);
			return true;
		} catch (SfBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "提示",JOptionPane.INFORMATION_MESSAGE);
			refreshData();
		}
		return false;
	}

	private boolean checkNewCode() {
		String gStr=gongTxt.getText();
		String jStr=jianTxt.getText();
		String zStr=ziTxt.getText();
		String nd=ndTxt.getText();
		 
		boolean error=false;
		
		if(gStr==null || gStr.trim().length()==0){
			error=true;
			gongTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		}

		if(jStr==null || jStr.trim().length()==0){
			error=true;
			jianTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		}

		if(zStr==null || zStr.trim().length()==0){
			error=true;
			ziTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if(nd==null || nd.trim().length()==0){
			error=true;
			ndTxt.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		if(error){
			JOptionPane.showMessageDialog(this, "请填写对应的字号!.", "提示",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}else{
			gongTxt.setBorder(defaultTxtBord);
			jianTxt.setBorder(defaultTxtBord);
			ziTxt.setBorder(defaultTxtBord);
		}
		
		return true;
	}

	private JPanel createOldCodePanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new TitledBorder(null, "可选编号",
				TitledBorder.CENTER, TitledBorder.TOP));
		contentPanel.setLayout(new BorderLayout());

		// newGroupButton.setPreferredSize(new Dimension(150, 26));

		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		p.add(oldGroupButton);
		contentPanel.add(p, BorderLayout.NORTH);

		codeTable.init();

		codeTable.setPanelId(this.getClass().getName() + "_tablePanel");

		codeTable.getSearchBar().setVisible(false);

		codeTable
				.setTablePreferencesKey(this.getClass().getName() + "__table");

		codeTable.getTable().setShowCheckedColumn(true);

		codeTable.getTable().getTableRowHeader()
				.setPreferredSize(new Dimension(60, 0));
		
		final JGroupableTable table = codeTable.getTable();
        table.addMouseListener(new MouseAdapter() {

          public void mouseClicked(MouseEvent e) {

            if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
 

              MyTableModel model = (MyTableModel) table.getModel();

              int row = table.getSelectedRow();

              List viewList = (List) ObjectUtil.deepCopy(ListUtil.convertToTableViewOrderList(model.getList(), table));

              SfJdDocCode docCode = (SfJdDocCode) viewList.get(row); 
              doubleClickTable(docCode);
             
            }

          }

        });

		contentPanel.add(codeTable, BorderLayout.CENTER);

		return contentPanel;

	}

	protected void doubleClickTable(SfJdDocCode docCode) {
		 oldGroupButton.setSelected(true);
   		int num = JOptionPane.showConfirmDialog(this, "确定使用编号:  "+docCode.getCode()+"  ?", "确认", 0);
   		if (num == JOptionPane.YES_OPTION) {
   			boolean save=saveCode(docCode);
   			if(save){
   				sfJdReportEditPanel.setNo(docCode.getCode());
   				dispose(); 
   			} 
   		} 
	}

	private JPanel createNewCodePanel() {
		// TCJLODO Auto-generated method stub

		// fieldLst.add(sellerGroupField);

		JPanel contentPanel = new JPanel(); 
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(600, 60));
		int x, y, w, h = 0;
		int reg = 10;
		int tab = 40;
		int rowReg = 1;
		int rowHeight = 35;

		// newGroupButton.setPreferredSize(new Dimension(150, 26));
		
		w = 100;
		x = reg;
		y = reg;
		h = rowHeight;
		newGroupButton.setBounds(x, y, w, h);
		contentPanel.add(newGroupButton);
		x = x + w +reg;
		
		JLabel l = new JLabel();
		l.setText("(");
		FontMetrics fm = Toolkit.getDefaultToolkit()
				.getFontMetrics(l.getFont());
		w = fm.stringWidth(l.getText()); 
		y = reg;
		h = rowHeight;
		l.setBounds(x, y, w, h);
		contentPanel.add(l);
		x = x + w ;

		w = 50;
		gongTxt.setBounds(x, y, w, h);
		gongTxt.setHorizontalAlignment(JTextField.CENTER);
		contentPanel.add(gongTxt);
		x = x + w ;

		l = new JLabel();
		l.setText(")公(");
		w = fm.stringWidth(l.getText());
		l.setBounds(x, y, w, h);
		contentPanel.add(l);
		x = x + w;

		w = 50;
		jianTxt.setBounds(x, y, w, h);
		jianTxt.setHorizontalAlignment(JTextField.CENTER);
		contentPanel.add(jianTxt);
		x = x + w ;

		l = new JLabel();
		l.setText(")鉴(");
		w = fm.stringWidth(l.getText());
		l.setBounds(x, y, w, h);
		contentPanel.add(l);
		x = x + w ;

		w = 50;
		ziTxt.setBounds(x, y, w, h);
		ziTxt.setHorizontalAlignment(JTextField.CENTER);
		contentPanel.add(ziTxt);
		x = x + w ;

		l = new JLabel();
		l.setText(")字[");
		w = fm.stringWidth(l.getText());
		l.setBounds(x, y, w, h);
		contentPanel.add(l);
		x = x + w ;

		w = 50;
		ndTxt.setBounds(x, y, w, h);
		ndTxt.setHorizontalAlignment(JTextField.CENTER);
		contentPanel.add(ndTxt);
		x = x + w ;

		l = new JLabel();
		l.setText("]");
		w = fm.stringWidth(l.getText());
		l.setBounds(x, y, w, h);
		contentPanel.add(l);
		x = x + w;
		
		w=50;
		numTxt.setBounds(x, y, w, h);
		numTxt.setHorizontalAlignment(JTextField.CENTER);
		contentPanel.add(numTxt);
		x = x + w ;

		l = new JLabel();
		l.setText("号");
		w = fm.stringWidth(l.getText());
		l.setBounds(x, y, w, h);
		contentPanel.add(l);
		x = x + w + reg;
 
//		
//		JPanel p = new JPanel();
//		p.setLayout(new FlowLayout(FlowLayout.LEFT));
//		p.add(newGroupButton);
		
		JPanel pp=new JPanel();
		pp.setLayout(new BorderLayout()); 
		pp.add(contentPanel,BorderLayout.CENTER);
		pp.setBorder(new TitledBorder(null, "新编号",TitledBorder.CENTER, TitledBorder.TOP));
		return pp;
	}

}
