/*
package com.ufgov.zc.client.jftp.util;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.event.*;


public class JHostChooser extends HFrame implements ActionListener
{
    private JLabel hostL = new JLabel("Host:");
    private JLabel portL = new JLabel("Port:");
    private JTextField host = new JTextField(20);
    private JTextField port = new JTextField(5);
    private JPanel p1 = new JPanel();
    private JPanel okP = new JPanel();
    private JButton ok = new JButton("Use these settings");

    public JHostChooser()
    {
        setSize(400, 120);
        setLocation(200, 250);
        setTitle("Connection...");
        getContentPane().setLayout(new BorderLayout());
        setBackground(Color.lightGray);

        p1.add(hostL);
        p1.add(host);
        p1.add(portL);
        p1.add(port);

        host.setText(RawConnection.host.getText());
        port.setText(RawConnection.port.getText());

        getContentPane().add("Center", p1);
        getContentPane().add("South", okP);
        okP.add(ok);
        ok.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == ok)
        {
            RawConnection.host.setText(host.getText());
            RawConnection.port.setText(port.getText());
            RawConnection.established = false;
            RawConnection.mayDispose = true;
            this.dispose();
        }
    }

    public Insets getInsets()
    {
        Insets std = super.getInsets();

        return new Insets(std.top + 5, std.left + 5, std.bottom + 5,
                          std.right + 5);
    }
}