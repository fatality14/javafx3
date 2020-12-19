package application;

import java.util.ArrayList;

import javax.mail.MessagingException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Controller{
	private Pane parent;
	
	private TextField messageTheme = new TextField();
	private TextField message = new TextField();
	private TextField login = new TextField();
	private PasswordField password = new PasswordField();
	private TextField recipients = new TextField();
	private Label result = new Label();
	private Button sendButton = new Button();
	
	private Data data;
	private Mail mail;
	
	ControllerMouseSendButtonEvent keyEvents;
	Controller(Pane parent){
		super();
		this.parent = parent;
		
		data = new Data();
		mail = new Mail(data);
		
		messageTheme.getStyleClass().add("field");
		messageTheme.setPromptText("Message theme");
		
		message.getStyleClass().add("field");
		message.setPromptText("Message text");
		
		login.getStyleClass().add("field");
		login.setPromptText("Your login");
		
		password.getStyleClass().add("field");
		password.setPromptText("Your password");
		
		recipients.getStyleClass().add("field");
		recipients.setPromptText("Email you want to send to");
		
		result.getStyleClass().add("result");
		
		sendButton.getStyleClass().add("send");
		sendButton.setText("Send message.");
		
		keyEvents = new ControllerMouseSendButtonEvent(this);
	}
	
	public void embed() {
		parent.getChildren().add(messageTheme);
		parent.getChildren().add(message);
		parent.getChildren().add(login);
		parent.getChildren().add(password);
		parent.getChildren().add(recipients);
		parent.getChildren().add(result);
				
		parent.getChildren().add(sendButton);
		
		sendButton.setOnMouseClicked(keyEvents);
	}
	
	public void trySendMessage() {
		if(		messageTheme.getText().isEmpty() ||
				message.getText().isEmpty() ||
				login.getText().isEmpty() ||
				password.getText().isEmpty() ||
				recipients.getText().isEmpty()
				) {
			result.setText("Error filling fields!");

			setDefaultFieldData();
			return;
		}
		
		data.setUsername(login.getText());
		data.setPassword(password.getText());
		
		ArrayList<String> rec = new ArrayList<>();
		rec.add(recipients.getText());
		
//		try {
//			mail.sendMessage(rec, messageTheme.getText(), message.getText());
//		}
//		catch(MessagingException e) {
//			result.setText(e.getMessage());
//		}
		
		result.setText("Message sent!");
	}
	
	public void setDefaultFieldData() {
		messageTheme.setText("Лабораторная №8 по Java");
		message.setText("Сообщение пересылается без проблем");
		login.setText("fatality17nik@gmail.com");
		password.setText("dVopx111");
		recipients.setText("fatality14nik@gmail.com");
	}
}
