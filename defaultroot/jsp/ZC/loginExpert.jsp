<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@page import ="com.anyi.gp.pub.SessionUtils"%>
public static void insertExpertSession(String token,String expertName,String expertID){
    String sql="insert into ZC_EB_EXPERT_SESSION(token,Expert_Id_No,Expert_Name) values(?,?,?)";
    Connection conn=null;
    PreparedStatement stmt = null;
    try {
			conn = com.anyi.gp.pub.DAOFactory.getInstance().getConnection();
			stmt = conn.prepareStatement(sql);
			int index=1;
			stmt.setString(index++,token);
			stmt.setString(index++,expertName);
			stmt.setString(index++,expertID);
		  stmt.executeUpdate();
		}catch(SQLException e){
		  throw new RuntimeException("写入专家账号信息时发生错误!");
		} finally {
			com.anyi.gp.pub.DBHelper.closeConnection(conn, stmt, null);
		}
}

public static void deleteExpertSession(String token)throws SQLException{
    String sql="DELETE FROM ZC_EB_EXPERT_SESSION T WHERE T.TOKEN=? ";
    Connection conn=null;
    PreparedStatement stmt = null;
    try {
			conn = com.anyi.gp.pub.DAOFactory.getInstance().getConnection();
			stmt = conn.prepareStatement(sql);
			int index=1;
			stmt.setString(index++,token);
		  stmt.executeUpdate();
		}catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException("删除专家账号信息时发生错误!");
		} finally {
			com.anyi.gp.pub.DBHelper.closeConnection(conn, stmt, null);
		}
}

public static int expertEvalPacks(String projCode,String expertName){
    String sql="select count(*) C from v_zc_eb_expert t where t.PROJ_CODE=?  and t.EXPERT_NAME=?";
    Connection conn=null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int counts=0;
    try {
			conn = com.anyi.gp.pub.DAOFactory.getInstance().getConnection();
			stmt = conn.prepareStatement(sql);
			int index=1;
			stmt.setString(index++,projCode);
			stmt.setString(index++,expertName);
		  rs = stmt.executeQuery();
		  if (rs.next()) {
		  	counts= rs.getInt(1);
		  }
		}catch(SQLException e){
		  e.printStackTrace();
		  throw new RuntimeException("删除专家账号信息时发生错误!");
		} finally {
			com.anyi.gp.pub.DBHelper.closeConnection(conn, stmt, rs);
		}
	  return counts;
}
%>
<%
   String panelClassName = request.getParameter("className");
   if(panelClassName == null || "".equals(panelClassName.trim())){
%>
    <b>菜单配置错误，没有添加【className】参数！<b/>
<%} else {   
   //String className = request.getParameter("className");
   String tokenID = request.getParameter("token");
   String projCode = (String)request.getParameter("projCode");
   String expertName = (String)request.getParameter("expertName");
   String expertIDNo =(String) request.getParameter("expertIDNo");       
   try {
      expertName = new String(expertName.getBytes("ISO8859_1"),"GBK");
      expertIDNo= new String(expertIDNo.getBytes("ISO8859_1"),"GBK");
      System.out.println(expertName);
   } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
   }  
    //System.out.println("token:"+ tokenID+ "projCode:"+ projCode+ "expertName:"+ expertName+ "expertIDNo:"+ expertIDNo+ "panelClassName:"+ panelClassName);
    int packs=expertEvalPacks(projCode,expertName);
    if(packs==0){
   	%>
   	 <b>你无权评审所选项目,请确认是否选择了正确的项目!<b/>
   <%}else{   
     deleteExpertSession(tokenID);
     insertExpertSession(tokenID,expertIDNo,expertName);
    %>
    <%@include file="/jsp/GK/GkApplet.jsp"%>
   <%  
    } 
   %>   
<%    
  }
%>
