package com.blog.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendEmailTest {

	private MimeMessage mimeMsg;
	private Session session;
	private Properties props;
	
	private String username = "";
	private String password = "";
	
	private Multipart mp;
	
	public SendEmailTest(){ 
		createMimeMessage();
	}
	
	public SendEmailTest(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}
	
	public void setSmtpHost(String hostName){
		if(props == null) props = System.getProperties();
		props.put("mail.smtp.host", hostName);
	}
	
	public boolean createMimeMessage(){
		
		try {
			session = Session.getDefaultInstance(props,null);
		} catch (Exception e) {
			return false;
		}
		
		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setNeedAuth(boolean need){
		if(props == null) props=System.getProperties();
		if(need){
			props.put("mail.smtp.auth", "true");
		}else{
			props.put("mail.smtp.auth", "false");
		}
	}
	
	public boolean setBody(String mailbody){
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent(mailbody, "text/html;charset=gb2312");
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setNamePass (String name , String pass){
		username = name;
		password = pass;
	}

	public boolean setSubject(String mailSubject){
		try {
			
			mimeMsg.setSubject(mailSubject);
			mimeMsg.setSubject( MimeUtility.encodeText( mailSubject,"gb2312","B"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean addFlieAffix(String filename){
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean setFrom(String from){
		try {
			mimeMsg.setFrom(new InternetAddress(from));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean setTo (String to){
	
			if(to==null) 
				return false;
		try {	
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); //??????
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean setCopyTo (String copyto){
		if(copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[])InternetAddress.parse(copyto));  //?????
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean sendOut(){
		try {
				mimeMsg.setContent(mp);
				mimeMsg.saveChanges();
				
				Session mailSession=Session.getInstance(props,null);
				Transport transport = mailSession.getTransport("smtp");
				transport.connect("smtp.163.com", username, password);
				transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));				
				transport.close();
				return true;
		} catch (Exception e) {
			return false;
		}
	}
}
