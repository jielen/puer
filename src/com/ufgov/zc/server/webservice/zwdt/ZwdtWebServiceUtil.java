package com.ufgov.zc.server.webservice.zwdt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ufgov.zc.common.system.constants.ZcSettingConstants;

public class ZwdtWebServiceUtil {
	// private static String SOAPUrl =
	// "/WebXXFBZtbMis/Pages/WebService/WebDB_Information_JL.asmx";
	private static String SOAPAction = "http://epoint.com.cn/pubStringArticle";

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public static String callZWDTService(String server, String comp,
			String pass, String gglm, String title, String html,
			String InfoDateFrom, String InfoDateTo, String BaoMinFrom,
			String BaoMingTo, String uploadJson) throws Exception {

		URL url = new URL(server);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) connection;

		StringBuffer sb = new StringBuffer();

		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n");

		sb.append("xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n");
		sb.append("<soap:Header>\n");
		sb.append("<WebInfoSoapHeader xmlns=\"http://epoint.com.cn/\">\n");
		sb.append("<CompanyName>").append(comp).append("</CompanyName>\n");
		sb.append("<Password>").append(pass).append("</Password>\n");
		sb.append("</WebInfoSoapHeader>\n");
		sb.append("</soap:Header>\n");
		sb.append("<soap:Body>\n");
		sb.append("<pubStringArticle xmlns=\"http://epoint.com.cn/\">\n");
		sb.append("<gglm>").append(gglm).append("</gglm>\n");
		sb.append("<title>").append(title).append("</title>\n");
		sb.append("<strHtml><![CDATA[");
		List strHtml = readFileByChar(html);
		for (int i = 0; i < strHtml.size(); i++) {
			sb.append((String) strHtml.get(i));
		}
		sb.append("]]></strHtml>\n");
		sb.append("<InfoDateFrom>").append(InfoDateFrom)
				.append("</InfoDateFrom>\n");
		sb.append("<InfoDateTo>").append(InfoDateTo).append("</InfoDateTo>\n");
		sb.append("<BaoMinFrom>").append(BaoMinFrom).append("</BaoMinFrom>\n");
		sb.append("<BaoMingTo>").append(BaoMingTo).append("</BaoMingTo>\n");
		sb.append("<uploadJson>").append(uploadJson).append("</uploadJson>\n");
		sb.append("</pubStringArticle>\n");
		sb.append("</soap:Body>\n");
		sb.append("</soap:Envelope>\n");
		byte[] b = sb.toString().getBytes(ZcSettingConstants.UTF8);
		Logger.getLogger(ZwdtWebServiceUtil.class).error(sb.toString());

		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpConn.setRequestProperty("Content-Type", "  text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", SOAPAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);

		OutputStream out = null;
		try {
			out = httpConn.getOutputStream();
			out.write(b);
		} catch (Exception e) {
			throw new Exception("调用接口出现异常\n" + e.getMessage());
		} finally {
			if (out != null)
				out.close();
		}
		BufferedReader in = null;
		String inputLine;
		try {
			InputStreamReader isr = new InputStreamReader(
					httpConn.getInputStream(), "GBK");
			in = new BufferedReader(isr);
			sb = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine);
		} catch (Exception e) {
			throw new Exception("调用接口出现异常\n" + e.getMessage());
		} finally {
			if (in != null)
				in.close();
			if (httpConn != null)
				httpConn.disconnect();
		}
		inputLine = sb.toString();
		if (inputLine.indexOf("<pubStringArticleResult>") <= 0) {
			String err = "";
			inputLine = new String(inputLine.getBytes("GBK"),
					ZcSettingConstants.UTF8);
			if (inputLine.indexOf("<code><pre>") > 0
					&& inputLine.indexOf("</pre></code>") > 0
					&& inputLine.indexOf("<code><pre>") < inputLine
							.indexOf("</pre></code>")) {
				err = inputLine.substring(inputLine.indexOf("<code><pre>")
						+ "<code><pre>".length(),
						inputLine.indexOf("</pre></code>"));
			}
			throw new Exception("调用接口出现异常\n" + err);
		}

		return inputLine.substring(
				inputLine.indexOf("<pubStringArticleResult>")
						+ "<pubStringArticleResult>".length(),
				inputLine.indexOf("</pubStringArticleResult>"));

	}

	private static List readFileByChar(String html) {

		String s = html.replaceAll("Script", "");
		List ls = new ArrayList();
		StringBuffer res = new StringBuffer();
		boolean flag = true;
		for (int i = 0; i < s.length(); i++) {
			String chr = s.substring(i, i + 1);
			if (chr.matches("[^\u4e00-\u9fa5]")
					&& !chr.matches("[，。？：；（）【】《》{}‘’！“”—……、]|(－{2})")) {
				if (!flag) {
					ls.add(res.toString());
					flag = true;
					res = new StringBuffer();
				}
				res.append(chr);
			} else {
				if (flag) {
					ls.add(res.toString());
					flag = false;
					res = new StringBuffer();
				}
				res.append(chr);
			}
		}
		return ls;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// System.out.println(readFileByChar());

			// String res =
			// callZWDTService("http://222.161.18.228/jlzx/WebXXFBZtbMis/Pages/WebService/WebDB_Information_JL.asmx",
			// "Epoint", "gtig_**##",
			// "004003001", "标题", readFileByChar(), df.format(new Date()) +
			// " 00:00:00.000", df.format(new Date()) + " 17:00:00.000", "", "",
			// "");
			// System.out.println(" Result: " + res.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
