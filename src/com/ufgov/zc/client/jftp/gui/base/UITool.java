/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.ufgov.zc.client.jftp.gui.base;

import java.io.*;

import javax.swing.*;


public class UITool
{
    public static boolean askToDelete(JComponent parent)
    {
        int res = JOptionPane.showConfirmDialog(parent,
                                                "确认删除吗?");

        if((res == JOptionPane.OK_OPTION))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static boolean askToRun(JComponent parent)
    {
        int res = JOptionPane.showConfirmDialog(parent,
                                                "确认打开文件吗?");

        if((res == JOptionPane.OK_OPTION))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String getPathFromDialog(String path)
    {
        JFileChooser chooser = new JFileChooser(path);
        chooser.setDialogTitle("选择目录");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = chooser.showOpenDialog(new JDialog());

        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            File f = chooser.getSelectedFile();

            return f.getPath();
        }

        return null;
    }
}