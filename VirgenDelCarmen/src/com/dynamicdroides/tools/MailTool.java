package com.dynamicdroides.tools;

import java.io.UnsupportedEncodingException;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.mail.javamail.MimeMessageHelper;

public class MailTool
{
	private org.springframework.mail.javamail.JavaMailSenderImpl mailSender;
 
	
	public org.springframework.mail.javamail.JavaMailSenderImpl getMailSender() {
		return mailSender;
	}


	public void setMailSender(
			org.springframework.mail.javamail.JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String from, String to, String subject, String msg) throws MessagingException, UnsupportedEncodingException {
		sendMail(from, to, subject, msg, null, null);
	}
	public void sendMail(String from, String to, String subject, String msg,String cc,String bbcc) throws MessagingException, UnsupportedEncodingException {
 
		//SimpleMailMessage message = new SimpleMailMessage();
		MimeMessage message = mailSender.createMimeMessage();
		BodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
		MimeMessageHelper helper;
		try {
			message.setContent(message, "text/html; charset=utf-8");
			helper = new MimeMessageHelper(message, true);
	        messageBodyPart.setText(msg);
			
			helper.setFrom(from);
			
			
			
			helper.setSubject(subject);
			if(cc!=null && !cc.equals(""))
				helper.setCc(cc);
			
			if(bbcc!=null && !bbcc.equals(""))
				helper.setBcc(bbcc);
			
			helper.setText(new String(msg.getBytes(),"UTF-8"),true);
			if(to.indexOf(",")>-1){
				String[] tos=to.split(",");
				for(int i=0;i<tos.length;i++){
					helper.setTo(tos[i]);
					mailSender.send(message);	
				}
			}else{
				helper.setTo(to);
				mailSender.send(message);	
				
			}
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		
	}
	
	
	
	
}