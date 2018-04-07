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
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.ufgov.zc.client.jftp.net.wrappers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.ufgov.zc.client.jftp.JFtp;
import com.ufgov.zc.client.jftp.net.FtpConnection;
import com.ufgov.zc.client.jftp.net.FtpURLConnection;
import com.ufgov.zc.client.jftp.system.logging.Log;


/**
 *
 */
public class SftpURLConnection extends URLConnection
{
    private SftpConnection connection = null;
    private String username = "no";
    private String password = "none@no.no";
    private boolean loginFlag;

    public SftpURLConnection(URL u)
    {
        super(u);

        com.sshtools.j2ssh.configuration.SshConnectionProperties properties = new com.sshtools.j2ssh.configuration.SshConnectionProperties();
        properties.setHost(u.getHost());
        properties.setPort(getPort());
        
        // parse the url for username and password and the remote directory
        String userInfo = u.getUserInfo();

        if(userInfo != null)
        {
            int index = userInfo.indexOf(":");

            if(index != -1)
            {
                username = userInfo.substring(0, index);
                password = userInfo.substring(index + 1);
            }
        }
        System.out.println(u.getPath());
        System.out.println(u.getPort());
        System.out.println(u.getUserInfo());

        Log.debug("Connecting...");

        connection = new SftpConnection(properties);
    }

    public void connect() throws IOException
    {
        loginFlag = connection.login(username, password);

        if(!loginFlag)
        {
            return;
        }
        
        //System.out.println(url.getPath());
        connection.chdir(url.getPath());
    }

    public SftpConnection getSftpConnection()
    {
        return connection;
    }

    public InputStream getInputStream() throws IOException
    {
        return null;
    }

    public OutputStream getOutputStream() throws IOException
    {
        return null;
    }

    public String getUser()
    {
        return username;
    }

    public String getPass()
    {
        return password;
    }

    public String getHost()
    {
        return url.getHost();
    }

    public int getPort()
    {
        int ret = url.getPort();

        if(ret <= 0)
        {
            return 22;
        }
        else
        {
            return ret;
        }
    }

    public boolean getLoginResponse()
    {
        return loginFlag;
    }

    public boolean loginSucceeded()
    {
        return loginFlag;
    }

}
