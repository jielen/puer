<%@page contentType="text/html; charset=gb2312" pageEncoding="gb2312" session="false"%>
<%@page import="java.sql.*"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="javax.sql.DataSource"%>
<style type="text/css">
.red {
 color: red;
 background-color: #ffffcc;
 border: #ccc 1px solid;
 line-height: 25px;
 margin: 5px 0;
 font-family: Arial
}

.blue {
 color: blue;
 background-color: #ffffcc;
 border: #ccc 1px solid;
 line-height: 25px;
 margin: 5px 0;
 font-family: Arial
}

div {
 font-size: 16px;
 font-weight: bold;
 padding: 5px 0;
 font-family: Arial
}

table {
 border-collapse: collapse;
 width: 100%;
 font-size: 16px;
 font-family: Arial;
 line-height:25px;
}

table th {
 background-color: #ccc;
 font-weight: bold
}

textarea {
 border: #000 1px solid;
 overflow: hidden;
 font-weight: bold;
 width: 100%;
 height: 100px;
 font-family: Arial
}

input {
 border: #000 1px solid;
 background-color: #fff;
 height: 22px;
 cursor: pointer;
 font-family: Arial
}
</style>
<%
 Connection con = null;
 PreparedStatement pst = null;
 ResultSet rs = null;
 try {
  WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
  DataSource dataSource = (DataSource) context.getBean("myDataSource");
  con = dataSource.getConnection();
  DatabaseMetaData dbmeta = con.getMetaData();
%>
<div>
  Version：<%=dbmeta.getDatabaseProductVersion()%><br>
  URL：<%=dbmeta.getURL() %><br>
  User：<%=dbmeta.getUserName()%>
</div>
<hr size=1>
<div>
 请输入SQL语句：
</div>
<form method=post action="" style="margin: 2px 0;">
 <textarea id="sqlarea" name="sql" id="sql"
  style="font-size: ${ param . fontSize == null ? 12 : param . fontSize}">${param.sql}</textarea>
 <div>
  <select
   onchange="document.getElementById('sqlarea').style.fontSize=this.value;"
   name="fontSize">
   <option value="12" selected>
    12px
   </option>
   <option value="14" ${param.fontSize==14?'selected':''}>
    14px
   </option>
   <option value="16" ${param.fontSize==16?'selected':''}>
    16px
   </option>
   <option value="18" ${param.fontSize==18?'selected':''}>
    18px
   </option>
   <option value="20" ${param.fontSize==20?'selected':''}>
    20px
   </option>
   <option value="24" ${param.fontSize==24?'selected':''}>
    24px
   </option>
  </select>
  <select name="execType" id="execType">
     <option id="select">select</option>
     <option id="update">update</option>
     <option id="ddl">ddl</option>
  </select>
  <input type="button" value=" 执 行 " onclick="document.forms[0].submit()">
 </div>
</form>
<%
 String sql = request.getParameter("sql");
  if (sql == null || "".equals(sql))
   return;
   
 String execType = request.getParameter("execType");
 
 System.out.println(execType);
%>
<hr size="1">
<%
  pst = con.prepareStatement(sql, 1005, 1008);

if("select".equals(execType)){  
  if (pst.execute()) {
   rs = pst.getResultSet();
   rs.last();
   int rowCount = rs.getRow();
   out.println("<div class=blue>"+sql+"查询行数：" + rowCount + "</div>");
   rs.beforeFirst();
   ResultSetMetaData metaData = rs.getMetaData();
   int columnCount = metaData.getColumnCount();
   String fontSize = request.getParameter("fontSize");
   out.print("<table border=1 bordercolor=#000 style='font-size:"+ (fontSize == null?"12":fontSize)+ "px'><tr><th width='100'></th>");
   for (int i = 1; i <= columnCount; i++)
    out.println("<th>" + metaData.getColumnName(i)
      + "</th>");
   out.println(" </tr>");
   int rowscount = 1;
   while (rs.next()) {
    out.println("<tr><th>" + rowscount + "</th>");
    for (int i = 1; i <= columnCount; i++)
     out.println("<td>" + rs.getObject(i) + "</td>");
    out.println("</tr>");
    rowscount++;
   }
   out.println("</table>");
   out.println("<div class=blue>"+sql+"查询行数：" + rowCount + "</div>");
  } else
   out.println("<div class=blue>"+sql+"影响行数：" + pst.getUpdateCount()
     + "</div>");
     
 }else if("ddl".equals(execType)){
    pst.execute();
    out.println("<div class=blue>"+sql+"操作成功：" + pst.getUpdateCount()
     + "</div>");    
 }else{
    int count=pst.executeUpdate();
    con.commit();
    out.println("<div class=blue>"+sql+"影响行数：" + count+ "</div>");
 }    
 } catch (Exception ex) {
    out.println("<div class=red>" + ex.toString() + "</div>");
    con.rollback();
 } finally {
  try {
   if (pst != null)
    pst.close();
   if (rs != null)
    rs.close();    
   if (con != null)
    con.close();
  } catch (Exception ex) {
   out.println("<div class=red>" + ex.toString() + "</div>");
  }
 }
%>

