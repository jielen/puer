
<%@page import="weblogic.jdbc.vendor.oracle.OracleThinBlob"%><%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.net.*"%>
<%@ page import="DBstep.*"%>
<%@ page import="oracle.sql.BLOB"%>
<%@ page import="oracle.jdbc.*"%>

<%
	iDBManager2000 ObjConnBean = new iDBManager2000();
	String mCommand;
	String mDocumentID = "";
	String mSignatureID = "";
	String mSignature = "";
	String mSignatures;

	String strSql;
	String mUserName;
	String mExtParam;

	boolean mResult;
	java.lang.String KeyName; //文件名
	java.io.File ObjFile; //文件对象
	java.io.FileReader ObjFileReader; //读文件对象
	char[] ChrBuffer; //缓冲
	int intLength; //实际读出的字符数

	byte[] SignatureBody = null;
	int mSignatureSize;

	String mSignatureName; //印章名称
	String mSignatureUnit; //签章单位
	String mSignatureUser; //持章人
	String mSignatureSN; //签章SN
	String mSignatureGUID; //全球唯一标识符

	String mMACHIP; //机器IP
	String OPType; //操作标志
	String mKeySn; //KEY序列号
	mCommand = request.getParameter("COMMAND");
	mUserName = new String(request.getParameter("USERNAME").getBytes(
			"8859_1"));
	mExtParam = new String(request.getParameter("EXTPARAM").getBytes(
			"8859_1"));

	System.out.println("");
	System.out.println("ReadPackage");
	System.out.println(mCommand);

	if (mCommand.equalsIgnoreCase("SAVESIGNATURE")) {
		mDocumentID = new String(request.getParameter("DOCUMENTID")
				.getBytes("8859_1"));
		mSignatureID = new String(request.getParameter("SIGNATUREID")
				.getBytes("8859_1"));
		mSignature = new String(request.getParameter("SIGNATURE")
				.getBytes("8859_1"));
		SignatureBody = mSignature.getBytes();
		mSignatureSize = SignatureBody.length;
		strSql = "SELECT * from HTMLSignature Where SignatureID='"
				+ mSignatureID + "' and DocumentID='" + mDocumentID
				+ "'";
		try {
			if (ObjConnBean.OpenConnection()) {
				try {
					ResultSet result = ObjConnBean.ExecuteQuery(strSql);
					if (result.next()) {
						strSql = "update HTMLSignature set DocumentID=?,SIGNATUREID=?,SignatureSize=?,Signature=EMPTY_BLOB() Where SignatureID='"
								+ mSignatureID
								+ "' and DocumentID='"
								+ mDocumentID + "'";
					} else {
						strSql = "insert into HTMLSignature (DocumentID,SignatureID,SignatureSize,Signature) values (?,?,?,EMPTY_BLOB()) ";
						java.util.Date dt = new java.util.Date();
						long lg = dt.getTime();
						Long ld = new Long(lg);
						mSignatureID = ld.toString();
					}
					result.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
				java.sql.PreparedStatement prestmt = null;
				try {
					prestmt = ObjConnBean.Conn.prepareStatement(strSql);
					prestmt.setString(1, mDocumentID);
					prestmt.setString(2, mSignatureID);
					prestmt.setInt(3, mSignatureSize);
					ObjConnBean.Conn.setAutoCommit(true);
					prestmt.execute();
					ObjConnBean.Conn.commit();
					prestmt.close();
					Statement stmt = null;
					ObjConnBean.Conn.setAutoCommit(false);
					stmt = ObjConnBean.Conn.createStatement();
					ResultSet rs = stmt
							.executeQuery("select Signature from HTMLSignature Where SignatureID='"
									+ mSignatureID
									+ "' and DocumentID='"
									+ mDocumentID + "'" + " for update");
					oracle.jdbc.OracleResultSet update = (OracleResultSet) rs;

					if (update.next()) {
						try {
							//oracle.sql.BLOB Signature = (oracle.sql.BLOB) update
									//.getBLOB("Signature");
							//System.out.println(Signature);
							System.out.println("开始转化为OracleThinBlob-------------------------------");
							OracleThinBlob blob = (weblogic.jdbc.vendor.oracle.OracleThinBlob) rs.getBlob(1);
							System.out.println("转换成功-------------------------------");
							try {
								int vSize = SignatureBody.length;
								OutputStream outstream = blob.getBinaryOutputStream();
								outstream.write(SignatureBody, 0, vSize);
								outstream.flush();
								outstream.close();
							} catch (SQLException e) {
								System.out.println(e.toString());
							}
							
							
							
							
							
							//ObjConnBean.PutAtBlob(Signature,
							//		SignatureBody);
						} catch (IOException e) {
							System.out.println(e.toString());
						}
					}
					update.close();
					stmt.close();
					ObjConnBean.Conn.commit();
					SignatureBody = null;
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		} finally {
			ObjConnBean.CloseConnection();
		}
		out.clear();
		out.print("SIGNATUREID=" + mSignatureID + "\r\n");
		out.print("RESULT=OK");
	}

	if (mCommand.equalsIgnoreCase("GETNOWTIME")) { //获取服务器时间
		java.sql.Date mDate;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		String mDateTime = formatter.format(cal.getTime());
		out.clear();
		out.print("NOWTIME=" + mDateTime + "\r\n");
		out.print("RESULT=OK");
	}
	if (mCommand.equalsIgnoreCase("DELESIGNATURE")) { //删除签章数据信息
		mDocumentID = request.getParameter("DOCUMENTID");
		mSignatureID = request.getParameter("SIGNATUREID");
		System.out.println("DocuemntID:" + mDocumentID);
		System.out.println("SignatureID:" + mSignatureID);
		if (ObjConnBean.OpenConnection()) {
			strSql = "SELECT * from HTMLSignature Where SignatureID='"
					+ mSignatureID + "' and DocumentID='" + mDocumentID
					+ "'";
			ResultSet rs = null;
			rs = ObjConnBean.ExecuteQuery(strSql);
			if (rs.next()) {
				try {
					strSql = "DELETE from HTMLSignature Where SignatureID='"
							+ mSignatureID
							+ "' and DocumentID='"
							+ mDocumentID + "'";
					ObjConnBean.ExecuteUpdate(strSql);
				} catch (Exception ex) {
					out.println(ex.toString());
				}
			}
			ObjConnBean.CloseConnection();
		}
		out.clear();
		out.print("RESULT=OK");
	}

	if (mCommand.equalsIgnoreCase("LOADSIGNATURE")) { //调入签章数据信息
		mDocumentID = request.getParameter("DOCUMENTID");
		mSignatureID = request.getParameter("SIGNATUREID");
		System.out.println("DocuemntID:" + mDocumentID);
		System.out.println("SignatureID:" + mSignatureID);
		if (ObjConnBean.OpenConnection()) {
			strSql = "SELECT Signature,SignatureSize from HTMLSignature Where SignatureID='"
					+ mSignatureID
					+ "' and DocumentID='"
					+ mDocumentID
					+ "'";
			ResultSet rs = null;
			rs = ObjConnBean.ExecuteQuery(strSql);
			if (rs.next()) {
				mSignatureSize = rs.getInt("SignatureSize");
				mSignature = ObjConnBean.GetAtBlob(rs
						.getBlob("Signature"), mSignatureSize);
				System.out.println(mSignature);

			}
			ObjConnBean.CloseConnection();
		}
		out.clear();
		out.print(mSignature + "\r\n");
		out.print("RESULT=OK");
	}

	if (mCommand.equalsIgnoreCase("SHOWSIGNATURE")) { //获取当前签章SignatureID，调出SignatureID，再自动调LOADSIGNATURE数据
		mDocumentID = request.getParameter("DOCUMENTID");
		System.out.println("DocuemntID:" + mDocumentID);
		mSignatures = "";
		if (ObjConnBean.OpenConnection()) {
			strSql = "SELECT * from HTMLSignature Where DocumentID='"
					+ mDocumentID + "'";
			ResultSet rs = null;
			rs = ObjConnBean.ExecuteQuery(strSql);
			while (rs.next()) {
				mSignatures = mSignatures + rs.getString("SignatureID")
						+ ";";
			}
			ObjConnBean.CloseConnection();
		}
		out.clear();
		out.print("SIGNATURES=" + mSignatures + "\r\n");
		out.print("RESULT=OK");
	}

	//---------------------------------------------------------------------------------------
	if (mCommand.equalsIgnoreCase("GETSIGNATUREDATA")) { //批量签章时，获取所要保护的数据
		String mSignatureData = "";
		mDocumentID = request.getParameter("DOCUMENTID");
		System.out.println(new String(request
				.getParameter("FIELDSLIST").getBytes("8859_1")));
		System.out.println(request.getParameter("FIELDSNAME"));
		if (ObjConnBean.OpenConnection()) {
			strSql = "SELECT XYBH,BMJH,JF,YF,HZNR,QLZR,CPMC,DGSL,DGRQ  from HTMLDocument Where DocumentID='"
					+ mDocumentID + "'";
			ResultSet rs = null;
			rs = ObjConnBean.ExecuteQuery(strSql);
			if (rs.next()) {
				mSignatureData = mSignatureData + "XYBH="
						+ (rs.getString("XYBH")) + "\r\n";
				mSignatureData = mSignatureData + "BMJH="
						+ (rs.getString("BMJH")) + "\r\n";
				mSignatureData = mSignatureData + "JF="
						+ (rs.getString("JF")) + "\r\n";
				mSignatureData = mSignatureData + "YF="
						+ (rs.getString("YF")) + "\r\n";
				mSignatureData = mSignatureData + "HZNR="
						+ (rs.getString("HZNR")) + "\r\n";
				mSignatureData = mSignatureData + "QLZR="
						+ (rs.getString("QLZR")) + "\r\n";
				mSignatureData = mSignatureData + "CPMC="
						+ (rs.getString("CPMC")) + "\r\n";
				mSignatureData = mSignatureData + "DGSL="
						+ (rs.getString("DGSL")) + "\r\n";
				mSignatureData = mSignatureData + "DGRQ="
						+ (rs.getString("DGRQ")) + "\r\n";
			}
			mSignatureData = java.net.URLEncoder.encode(mSignatureData);
			ObjConnBean.CloseConnection();
		}
		out.clear();
		out.print("SIGNATUREDATA=" + mSignatureData + "\r\n");
		out.print("RESULT=OK");
	}

	if (mCommand.equalsIgnoreCase("PUTSIGNATUREDATA")) { //批量签章时，写入签章数据
		mDocumentID = new String(request.getParameter("DOCUMENTID")
				.getBytes("8859_1"));
		mSignature = new String(request.getParameter("SIGNATURE")
				.getBytes("8859_1"));
		SignatureBody = mSignature.getBytes();
		mSignatureSize = SignatureBody.length;
		if (ObjConnBean.OpenConnection()) {
			java.sql.PreparedStatement prestmt = null;
			try {
				//取得唯一值(mSignature)
				java.util.Date dt = new java.util.Date();
				long lg = dt.getTime();
				Long ld = new Long(lg);
				mSignatureID = ld.toString();
				System.out.println(mDocumentID);
				String Sql = "insert into HTMLSignature (DocumentID,SignatureID,SignatureSize,Signature) values (?,?,?,EMPTY_BLOB()) ";
				prestmt = ObjConnBean.Conn.prepareStatement(Sql);
				prestmt.setString(1, mDocumentID);
				prestmt.setString(2, mSignatureID);
				prestmt.setInt(3, mSignatureSize);

				System.out.println(mDocumentID);

				ObjConnBean.Conn.setAutoCommit(true);
				prestmt.execute();
				ObjConnBean.Conn.commit();
				prestmt.close();
				Statement stmt = null;
				ObjConnBean.Conn.setAutoCommit(false);
				stmt = ObjConnBean.Conn.createStatement();
				OracleResultSet update = (OracleResultSet) stmt
						.executeQuery("select Signature from HTMLSignature Where SignatureID='"
								+ mSignatureID
								+ "' and DocumentID='"
								+ mDocumentID + "'" + " for update");
				if (update.next()) {
					try {
						ObjConnBean.PutAtBlob(
								((oracle.jdbc.OracleResultSet) update)
										.getBLOB("Signature"),
								SignatureBody);
					} catch (IOException e) {
						System.out.println(e.toString());
					}
				}
				update.close();
				stmt.close();
				ObjConnBean.Conn.commit();
				SignatureBody = null;
				mResult = true;
			} catch (SQLException e) {
				System.out.println(e.toString());
				mResult = false;
			}
			ObjConnBean.CloseConnection();
		}
		out.clear();
		out.print("SIGNATUREID=" + mSignatureID + "\r\n");
		out.print("RESULT=OK");
	}

	//---------------------------------------------------------------------------------------

	if (mCommand.equalsIgnoreCase("SIGNATUREKEY")) {
		mUserName = new String(request.getParameter("USERNAME")
				.getBytes("8859_1"));
		String RealPath = mUserName + "\\" + mUserName + ".key";
		KeyName = application.getRealPath(RealPath);

		ObjFile = new java.io.File(KeyName); //创建文件对象 
		ChrBuffer = new char[10];
		try {
			if (ObjFile.exists()) {//文件存在 
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(KeyName));
				//ObjFileReader = new java.io.FileReader(ObjFile); 		//创建读文件对象 
				//ObjFileReader.skip(1);
				//ObjFileReader.read(ChrBuffer, 0, 1);
				//System.out.println(ChrBuffer);
				while ((intLength = isr.read(ChrBuffer)) != -1) { //读文件内容 
					out.write(ChrBuffer, 0, intLength);
				}
				out.write("\r\n");
				out.write("RESULT=OK");
				isr.close(); //关闭读文件对象 
			} else {
				out.println("File Not Found" + KeyName); //文件不存在 
			}
		} catch (Exception e) {

			System.out.println(e.toString());
		}
	}

	if (mCommand.equalsIgnoreCase("SAVEHISTORY")) { //保存签章历史信息
		mSignatureName = new String(request.getParameter(
				"SIGNATURENAME").getBytes("8859_1"));//印章名称
		mSignatureUnit = new String(request.getParameter(
				"SIGNATUREUNIT").getBytes("8859_1"));//印章单位
		mSignatureUser = new String(request.getParameter(
				"SIGNATUREUSER").getBytes("8859_1"));//印章用户名
		mSignatureSN = new String(request.getParameter("SIGNATURESN")
				.getBytes("8859_1"));//印章序列号
		mSignatureGUID = new String(request.getParameter(
				"SIGNATUREGUID").getBytes("8859_1"));//全球唯一标识
		mDocumentID = new String(request.getParameter("DOCUMENTID")
				.getBytes("8859_1"));//页面ID
		mSignatureID = new String(request.getParameter("SIGNATUREID")
				.getBytes("8859_1"));//签章序列号
		mMACHIP = new String(request.getParameter("MACHIP").getBytes(
				"8859_1"));//签章机器IP
		OPType = new String(request.getParameter("LOGTYPE").getBytes(
				"8859_1"));//日志标志
		mKeySn = new String(request.getParameter("KEYSN").getBytes(
				"8859_1"));//KEY序列号
		if (ObjConnBean.OpenConnection()) {
			java.sql.PreparedStatement prestmt = null;
			try {
				java.sql.Date mDate;
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				String mDateTime = formatter.format(cal.getTime());

				strSql = "insert into HTMLHistory(SignatureName,SignatureUnit,SignatureUser,SignatureSN,";
				strSql = strSql
						+ "SignatureGUID,DocumentID,SignatureID,IP,LogType,KeySN)";
				strSql = strSql + " values(?,?,?,?,?,?,?,?,?,?)";
				prestmt = ObjConnBean.Conn.prepareStatement(strSql);

				prestmt.setString(1, mSignatureName);
				prestmt.setString(2, mSignatureUnit);
				prestmt.setString(3, mSignatureUser);
				prestmt.setString(4, mSignatureSN);
				prestmt.setString(5, mSignatureGUID);
				prestmt.setString(6, mDocumentID);
				prestmt.setString(7, mSignatureID);
				prestmt.setString(8, mMACHIP);
				//prestmt.setString(9,mDateTime);
				prestmt.setString(9, OPType);
				prestmt.setString(10, mKeySn);
				ObjConnBean.Conn.setAutoCommit(true);
				prestmt.execute();
				ObjConnBean.Conn.commit();
				prestmt.close();
				mResult = true;
			} catch (SQLException e) {
				System.out.println(e.toString());
				mResult = false;
			}
			ObjConnBean.CloseConnection();
		}
		out.clear();
		out.print("SIGNATUREID=" + mSignatureID + "\r\n");
		out.print("RESULT=OK");
	}
%>