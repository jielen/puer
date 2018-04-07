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
package com.ufgov.zc.client.jftp.gui.hostchooser;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ufgov.zc.client.jftp.JFtp;
import com.ufgov.zc.client.jftp.config.LoadSet;
import com.ufgov.zc.client.jftp.config.SaveSet;
import com.ufgov.zc.client.jftp.config.Settings;
import com.ufgov.zc.client.jftp.gui.framework.HButton;
import com.ufgov.zc.client.jftp.gui.framework.HFrame;
import com.ufgov.zc.client.jftp.gui.framework.HInsetPanel;
import com.ufgov.zc.client.jftp.gui.framework.HPanel;
import com.ufgov.zc.client.jftp.gui.framework.HPasswordField;
import com.ufgov.zc.client.jftp.gui.framework.HTextField;
import com.ufgov.zc.client.jftp.net.FtpConnection;
import com.ufgov.zc.client.jftp.net.wrappers.Sftp2Connection;
import com.ufgov.zc.client.jftp.net.wrappers.SftpConnection;
import com.ufgov.zc.client.jftp.net.wrappers.SftpURLConnection;
import com.ufgov.zc.client.jftp.net.wrappers.StartConnection;
import com.ufgov.zc.client.jftp.system.logging.Log;
import com.ufgov.zc.client.jftp.tools.SshShell;


public class SftpHostChooser extends HFrame implements ActionListener,
                                                       WindowListener, ChangeListener
{
    public HTextField host = new HTextField("Host:", "localhost");
    public HTextField user = new HTextField("Username:", "guest");
    public HTextField port = new HTextField("Port:", "22");
    public HPasswordField pass = new HPasswordField("Password/Phrase:",
                                                    "nopasswd");
    public JComboBox enc = new JComboBox();
    public JComboBox cs = new JComboBox();
    public JComboBox keys = new JComboBox();
    public JLabel encL = new JLabel("Pref. Encryption");
    public JLabel csL = new JLabel("Pref. Message Auth.");
    public JLabel keysL = new JLabel("Pref. Public Key");
    public JLabel keyfileL = new JLabel("(No File)");
    private HPanel okP = new HPanel();
    private HPanel keyP = new HPanel();
    private HButton ok = new HButton("Connect");
    private HButton keyfile = new HButton("Choose Key File");
    private ComponentListener listener = null;
    private boolean useLocal = false;
    private boolean shell = false;
    private String keyfileName = null;
    private JCheckBox useJSch = new JCheckBox("Use JSch instead of j2ssh");
    
    public SftpHostChooser(ComponentListener l, boolean local)
    {
        listener = l;
        useLocal = local;
        init();
    }

    public SftpHostChooser(ComponentListener l)
    {
        listener = l;
        init();
    }

    public SftpHostChooser()
    {
        init();
    }

    public SftpHostChooser(boolean shell)
    {
        this.shell = shell;
        init();
    }

    public void init()
    {
        //setSize(500, 200);
        setLocation(100, 150);
        setTitle("Sftp Connection...");
        setBackground(okP.getBackground()); 

        //***MY CHANGES
        try
        {
            File f = new File(Settings.appHomeDir);
            f.mkdir();

            File f1 = new File(Settings.login);
            f1.createNewFile();

            File f2 = new File(Settings.login_def_sftp);
            f2.createNewFile();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        } 

        String[] login = LoadSet.loadSet(Settings.login_def_sftp);

        if((login[0] != null) && (login.length > 1))
        {
            host.setText(login[0]);
            user.setText(login[1]);
        }

        /*
        else {
                host.setText("localhost");
                user.setText("guest");

        }
        */
        if(Settings.getStorePasswords())
        {
            if((login != null) && (login.length > 2) && (login[2] != null))
            {
                pass.setText(login[2]);
            }
        }
        else
        {
            pass.setText("");
        }

        enc.addItem("3des-cbc");
        enc.addItem("blowfish-cbc");

        cs.addItem("hmac-sha1");
        cs.addItem("hmac-sha1-96");
        cs.addItem("hmac-md5");
        cs.addItem("hmac-md5-96");

        keys.addItem("ssh-rsa");
        keys.addItem("ssh-dss");

        HInsetPanel root = new HInsetPanel();
        root.setLayout(new GridLayout(7, 2, 5, 3));
        root.add(host);
        root.add(port);
        root.add(user);
        root.add(pass);
        root.add(encL);
        root.add(enc);
        root.add(csL);
        root.add(cs);
        root.add(keysL);
        root.add(keys);
        root.add(keyP);
        root.add(new JLabel("Keyfiles are usually located ~/.ssh/ on UNIX"));
        root.add(useJSch);
        root.add(okP);
        
        //useJSch.setSelected(true);

        keyP.add(keyfileL);
        keyP.add(keyfile);
        okP.add(new JLabel("        "));
        okP.add(ok);
        
        getContentPane().setLayout(new BorderLayout(10,10));
        getContentPane().add("Center", root);
        
        ok.addActionListener(this);
        keyfile.addActionListener(this);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        pass.text.addActionListener(this);       
        useJSch.addChangeListener(this);
     

        pack();
        setModal(false);
        setVisible(false);
        addWindowListener(this);
    }

    public void stateChanged(ChangeEvent e) {
    	if(useJSch.isSelected()) {
    		enc.setEnabled(false);
    		cs.setEnabled(false);
    		keys.setEnabled(false);
    	}
    	else {
    		keyfile.setEnabled(true);
    		enc.setEnabled(true);
    		cs.setEnabled(true);
    		keys.setEnabled(true);
    	}
    }

    public void update()
    {
    	fixLocation();
    	setVisible(true);
    	toFront();
    	host.requestFocus();
    }

    public void update(String url)
    {
    	try
    	{
    		SftpURLConnection uc = new SftpURLConnection(new java.net.URL(url));
    		SftpConnection con = uc.getSftpConnection();

    		JFtp.statusP.jftp.addConnection(url, con);

    		uc.connect();

    		if(!uc.getLoginResponse())
    		{
    			setTitle("Wrong password!");
    			host.setText(uc.getHost());
    			port.setText(Integer.toString(uc.getPort()));
    			user.setText(uc.getUser());
    			pass.setText(uc.getPass());
    			setVisible(true);
    			toFront();
    			host.requestFocus();
    		}
    		else
    		{
    			this.dispose();

    			if(listener != null)
    			{
    				listener.componentResized(new ComponentEvent(this, 0));
    			}

    			JFtp.mainFrame.setVisible(true);
    			JFtp.mainFrame.toFront();
    		}
    	}

    	catch(IOException ex)
    	{
    		Log.debug("Error!");
    		ex.printStackTrace();
    	}
    }

    public void setShell(boolean shell)
    {
        this.shell = shell;
    }

    public boolean getShell()
    {
        return shell;
    }

    public void actionPerformed(ActionEvent e)
    {
        if((e.getSource() == ok) || (e.getSource() == pass.text))
        {
            // Switch windows
            //this.setVisible(false);
            //this.setModal(false);
            //JFtp.mainFrame.setVisible(true);
            //JFtp.mainFrame.toFront();
            setCursor(new Cursor(Cursor.WAIT_CURSOR));

            SftpConnection con = null;

            String htmp = host.getText().trim();
            String utmp = user.getText().trim();
            String ptmp = pass.getText();

            //***port number: to be initialized in a future version? 
            int potmp = 22;

            try
            {
            	potmp = Integer.parseInt(port.getText());
            }
            catch(Exception ex)
            {
            	Log.debug("Error: Not a number!");
            }

            String potmpString = new String("" + potmp);

            com.sshtools.j2ssh.configuration.SshConnectionProperties properties = new com.sshtools.j2ssh.configuration.SshConnectionProperties();
            properties.setHost(htmp);
            //Log.debug(htmp+":"+properties.getHost());
            properties.setPort(potmp);
            properties.setPrefSCEncryption((String) enc.getSelectedItem());
            properties.setPrefCSMac((String) cs.getSelectedItem());
            properties.setPrefPublicKey((String) keys.getSelectedItem());

            if(shell)
            {
            	SshShell s = new SshShell(properties, utmp, ptmp, potmp);
            	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            	this.dispose();
            	s.toFront();

            	return;
            }
            else
            {
            	try
            	{
            		boolean status;

            		SaveSet s = new SaveSet(Settings.login_def_sftp, htmp,
            				utmp, ptmp, potmpString, "null", "null");

            		if(!useJSch.isSelected()) {
            			StartConnection.setSshProperties(properties);
            			StartConnection.setSshKeyfile(keyfileName);
            			status = StartConnection.startCon("SFTP", htmp, utmp, ptmp,
            					potmp, "", useLocal);
            		}
            		else { 
            			Sftp2Connection con2 = new Sftp2Connection(htmp, ""+potmp, keyfileName);

            			if(con2.login(utmp, ptmp))
            			{
            				if(useLocal)
            				{
            					JFtp.statusP.jftp.addLocalConnection(htmp, con2);
            				}
            				else
            				{
            					JFtp.statusP.jftp.addConnection(htmp, con2);
            				}

            				if(con2.chdir(con2.getPWD()) || con2.chdir("/"))
            				{
            					;
            				}
            			}
            		}
            	}
            	catch(Exception ex)
            	{
            		ex.printStackTrace();
            		Log.debug("Could not create SftpConnection, does this distribution come with j2ssh?");
            	}
            }

            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            this.dispose();
            JFtp.mainFrame.setVisible(true);
            JFtp.mainFrame.toFront();

            if(listener != null)
            {
                listener.componentResized(new ComponentEvent(this, 0));
            }
        }
        else if(e.getSource() == keyfile)
        {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);

            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                keyfileName = chooser.getSelectedFile().getPath();

                if(keyfileName != null)
                {
                    keyfileL.setText("(File present)");
                }
            }
            else {
            	keyfileName = null;
            	
                if(keyfileName != null)
                {
                    keyfileL.setText("(No File)");
                }
            }
        }
    }

    public void windowClosing(WindowEvent e)
    {
        //System.exit(0);
        this.dispose();
    }

    public void windowClosed(WindowEvent e)
    {
    }

    public void windowActivated(WindowEvent e)
    {
    }

    public void windowDeactivated(WindowEvent e)
    {
    }

    public void windowIconified(WindowEvent e)
    {
    }

    public void windowDeiconified(WindowEvent e)
    {
    }

    public void windowOpened(WindowEvent e)
    {
    }

    public void pause(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(Exception ex)
        {
        }
    }
}
