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
package com.ufgov.zc.client.jftp.gui.base.dir;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import com.ufgov.zc.client.jftp.config.Settings;
import com.ufgov.zc.client.jftp.gui.base.UITool;
import com.ufgov.zc.client.jftp.gui.framework.*;
import com.ufgov.zc.client.jftp.gui.tasks.PathChanger;
import com.ufgov.zc.client.jftp.net.FilesystemConnection;


public class DirCanvas extends JPanel implements MouseListener
{
    JLabel text = new JLabel(" ");
    private Dir target;
    boolean active = false;
    private String curPathString="";

    public DirCanvas(Dir target)
    {
        this.target = target;
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(text);
        addMouseListener(this);
        setVisible(true);
    }

    public void mouseClicked(MouseEvent e)
    {
        if(target.getType().equals("local") || target.getCon() instanceof FilesystemConnection)
        {
        	if(curPathString!=null && curPathString.length()>0){
        		curPathString=UITool.getPathFromDialog(curPathString);
        	}else{
        		curPathString= UITool.getPathFromDialog(Settings.defaultWorkDir);
        	}
//            String tmp = UITool.getPathFromDialog(Settings.defaultWorkDir);

            if(curPathString != null)
            {
                target.setPath(curPathString);
                target.fresh();
            }
        }
        else
        {
            /* 不能更改远程的目录，chenjl 20180331
             PathChanger p = new PathChanger("remote");
            target.fresh();*/
        }
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    	setCursor(new Cursor(Cursor.HAND_CURSOR));
    	active = true;
    	repaint();
    }

    public void mouseExited(MouseEvent e)
    {
    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	active = false;
    	repaint();
    }

    public void setText(String msg)
    {
        text.setText(msg);
        validate();
    }

    public void paintComponent(Graphics g)
    {
    	if(!active) {
    		g.setColor(GUIDefaults.light);
    	}
    	else {
    		g.setColor(GUIDefaults.lightActive);
    	}
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(GUIDefaults.front);
        g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
        g.drawRect(0, 0, getSize().width - 2, getSize().height - 2);
    }
}
