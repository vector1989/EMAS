package com.evmtv.epg.mail;

import java.util.ResourceBundle;

/**
 * @author Lei<helei@evmtv.com>
 * @data 2013-7-11 下午4:52:38
 */

public class SendEmailUtil {
	public static void sendEmail(String emailStr) {
		ResourceBundle rb = ResourceBundle.getBundle("mail");
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(rb.getString("mail.server.host"));
		mailInfo.setMailServerPort(rb.getString("mail.server.port"));
		mailInfo.setValidate(true);
		mailInfo.setUserName(rb.getString("mail.sender.email"));
		mailInfo.setPassword(rb.getString("mail.sender.password"));
		mailInfo.setFromAddress(rb.getString("mail.sender.email"));
		mailInfo.setToAddress(rb.getString("mail.receiver.email"));
		mailInfo.setSubject("合同到期提醒");
		mailInfo.setContent("您好！您的合同编号为" + emailStr
				+ "的合同已经到期，现已给您自动延期三天！请替换合同或相应素材图片，并重新发送码流！");
		SimpleMailSender.sendTextMail(mailInfo);
		// SimpleMailSender.sendHtmlMail(mailInfo);
	}
}
