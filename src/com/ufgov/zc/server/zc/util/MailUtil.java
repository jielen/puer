package com.ufgov.zc.server.zc.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.ufgov.zc.common.zc.model.MailInfo;

public class MailUtil {
	public boolean sendTextMail(MailInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			String[] to = mailInfo.getToAddress().split(",");
			InternetAddress[] sendTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				sendTo[i] = new InternetAddress(to[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.BCC, sendTo);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean sendHtmlMail(MailInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			String[] to = mailInfo.getToAddress().split(",");
			InternetAddress[] sendTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				sendTo[i] = new InternetAddress(to[i]);
			}
			mailMessage.setRecipients(Message.RecipientType.BCC, sendTo);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 附件
			for (int i = 0; i < mailInfo.getAttachFileNames().size(); i++) {
				MimeBodyPart mbpFile = new MimeBodyPart();
				// 暂时注释掉，jdk1.4没有FileDataSource -cjl20130629
				// FileDataSource fds = new FileDataSource(mailInfo
				// .getAttachFileNames().get(i).toString());
				File fds = new File(mailInfo.getAttachFileNames().get(i)
						.toString());
				// 暂时注释掉，jdk1.4没有DataHandler -cjl20130629
				// mbpFile.setDataHandler(new DataHandler(fds));

				String filename = "公告附件";
				try {
					filename = new String(fds.getName().getBytes(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				mbpFile.setFileName(filename);
				// 向MimeMessage添加（Multipart代表附件）
				mainPart.addBodyPart(mbpFile);

			}
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} finally {
			if (mailInfo.getAttachFileNames() != null) {
				for (int i = 0; i < mailInfo.getAttachFileNames().size(); i++) {
					File f = new File(mailInfo.getAttachFileNames().get(i)
							.toString());
					if (f.exists()) {
						f.delete();
					}
				}
			}
		}
		return false;
	}

	public static void main(String str[]) {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailServerHost("smtp.vip.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("jlszfcg@vip.163.com");
		mailInfo.setPassword("*********");// 您的邮箱密码
		mailInfo.setFromAddress("jlszfcg@vip.163.com");

		mailInfo.setToAddress("12342343234342323@qq.com,123@163.com");
		mailInfo.setSubject("设置邮箱标题");

		mailInfo.setContent("dsfsd");
		// mailInfo.getAttachFileNames().add("E:\\工作资料\\ip.txt");
		// 这个类主要来发送邮件
		MailUtil m = new MailUtil();
		m.sendHtmlMail(mailInfo);// 发送文体格式
		// m.sendHtmlMail(mailInfo);// 发送html格式
	}
}
