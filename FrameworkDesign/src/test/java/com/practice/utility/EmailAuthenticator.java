package com.practice.utility;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {
	private String userName;
	private String password;
	
	public EmailAuthenticator(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public PasswordAuthentication passwordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
}
