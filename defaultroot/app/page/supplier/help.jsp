<%@ page language="java" import="java.util.*,java.net.URLEncoder;" pageEncoding="utf-8"%>

<%
	
	response.setContentType("application/x-download");
	String filedownload = "/portal/page/supplier/help.doc";//即将下载的文件的相对路径
	String filedisplay = "帮助文档.doc";//下载文件时显示的文件保存名称
	String filenamedisplay = URLEncoder.encode(filedisplay,"UTF-8");
	response.addHeader("Content-Disposition","attachment;filename=" + filenamedisplay);
	System.out.print(filedownload);
	try
	{
		RequestDispatcher dis = application.getRequestDispatcher(filedownload);
		if(dis!= null)
		{
			dis.forward(request,response);
		}
		response.flushBuffer();
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
	
	}


%>

