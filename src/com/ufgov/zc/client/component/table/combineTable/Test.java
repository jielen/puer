/**   
* @(#) project: MyTestCode
* @(#) file: Test.java
* 
* Copyright 2013 UFGOV, Inc. All rights reserved.
* UFGOV PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
* 
*/
package com.ufgov.zc.client.component.table.combineTable;

/**
* @ClassName: Test
* @Description: TCJLODO(这里用一句话描述这个类的作用)
* @date: Jan 30, 2013 9:16:51 AM
* @version: V1.0 
* @since: 1.0
* @author: Administrator
* @modify: 
*/
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Test {

  public static void main(String args[]) {
    JFrame jf = new JFrame("Cell Combine Table");
    JTable cTable = getTable1();

    jf.getContentPane().add(new JScrollPane(cTable));
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setSize(500, 500);
    jf.setVisible(true);
  }

  private static CombineTable getTable1() {
    String[][] datas = new String[10][6];
    for (int i = 0; i < datas.length; i++) {
      String[] data = datas[i];
      for (int j = 0; j < data.length; j++) {
        data[j] = "";
      }
      data[0] = String.valueOf((int) (i / 3));
    }

    ArrayList<Integer> combineColumns = new ArrayList<Integer>();
    combineColumns.add(0);
    CombineData m = new CombineData(datas, combineColumns);
    DefaultTableModel tm = new DefaultTableModel(datas, new String[] { "1", "2", "3", "4", "5" });

    CombineTable cTable = new CombineTable(m, tm);

    //    TableColumn column = cTable.getColumnModel().getColumn(0);
    //    column.setCellRenderer(new CombineColumnRender());
    //    column.setWidth(50);
    //    column.setMaxWidth(50);
    //    column.setMinWidth(50);
    //    cTable.setCellSelectionEnabled(true);
    return cTable;
  }

  private static CombineTable getTable2() {
    String[][] datas = new String[10][6];
    for (int i = 0; i < datas.length; i++) {
      String[] data = datas[i];
      for (int j = 0; j < data.length; j++) {
        data[j] = "";
      }
      data[0] = String.valueOf((int) (i / 4));
      data[1] = String.valueOf((int) (i / 2));
    }

    CombineData m = new CombineData(datas, 0, 1);
    DefaultTableModel tm = new DefaultTableModel(datas, new String[] { "1", "2", "3", "4", "5" });
    CombineTable cTable = new CombineTable(m, tm);

    TableColumnModel columnModel = cTable.getColumnModel();
    for (Integer integer : m.combineColumns) {
      TableColumn column = columnModel.getColumn(integer);
      column.setCellRenderer(new CombineColumnRender());
      column.setWidth(50);
      column.setMaxWidth(50);
      column.setMinWidth(50);
    }

    cTable.setCellSelectionEnabled(true);
    return cTable;
  }
}
