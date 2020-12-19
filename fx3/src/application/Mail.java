package application;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private Data data;
	
	private Properties properties;
	private Session session;
	
	private boolean isSetup;

	Mail(Data data){
		super();
		this.data = data;
	}
	
	public void sendMessage(ArrayList<String> to, String subject, String messageText) throws MessagingException{
		try {
			if(!isSetup) {
				setup();
				isSetup = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			isSetup = false;
		}
		
		Message message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(data.getUsername()));
		
		String recipients = "";
		for(int i = 0; i < to.size(); i++) {
			if(to.size() - 1 == i)
				recipients += to.get(i);
			else
				recipients += to.get(i) + ",";
		}
		
		message.setRecipients(
				Message.RecipientType.TO,
				InternetAddress.parse(recipients)
				);
		message.setSubject(subject);
		message.setText(messageText);

		Transport.send(message);
	}
	
	private void setup() {
		properties = new Properties();
		
		properties.put("mail.smtp.host", "smtp."+data.getHost());
		properties.put("mail.smtp.port", Integer.toString(data.getPort()));
		properties.put("mail.smtp.auth", Boolean.toString(data.isDoAuth()));
		if(data.isDoTLS()) {
			properties.put("mail.smtp.starttls.enable", Boolean.toString(data.isDoTLS()));
		}
		if (data.isDoSSL()) {
			properties.put("mail.smtp.socketFactory.port", Integer.toString(data.getPort()));
	        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(data.getUsername(), data.getPassword());
			}
		});
	}
	
	public boolean isSetup() {
		return isSetup;
	}
	public void setSetup(boolean isSetup) {
		this.isSetup = isSetup;
	}
}
