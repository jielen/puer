package com.ufgov.zc.client.jftp.net.wrappers;

import java.util.Vector;

import com.sshtools.j2ssh.configuration.SshConnectionProperties;
import com.ufgov.zc.client.jftp.net.Transfer;


public class SftpTransfer implements Runnable
{
    private String host;
    private String localPath;
    private String remotePath;
    private String file;
    private String user;
    private String pass;
    private SftpConnection con = null;
    private String type;
    public Thread runner;
    private Vector listeners;
    private SshConnectionProperties props;
    private String keyfile;

    public SftpTransfer(SshConnectionProperties props, String localPath, String remotePath,
                        String file, String user, String pass,
                        Vector listeners, String type, String keyfile)
    {
        this.props = props;
        this.localPath = localPath;
        this.remotePath = remotePath;
        this.file = file;
        this.user = user;
        this.pass = pass;
        this.type = type;
        this.listeners = listeners;
        this.keyfile = keyfile;

        prepare();
    }

    public void prepare()
    {
        runner = new Thread(this);
        runner.setPriority(Thread.MIN_PRIORITY);
        runner.start();
    }

    public void run()
    {
        con = new SftpConnection(props, keyfile);
        con.setConnectionListeners(listeners);
        con.login(user, pass);
        con.setLocalPath(localPath);
        con.chdir(remotePath);

        if(type.equals(Transfer.DOWNLOAD))
        { 
            con.download(file);
        }
        else if(type.equals(Transfer.UPLOAD))
        {
            con.upload(file);
        }
    }

    public SftpConnection getSftpConnection()
    {
        return con;
    }
}
