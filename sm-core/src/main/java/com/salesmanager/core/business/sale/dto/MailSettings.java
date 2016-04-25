package com.salesmanager.core.business.sale.dto;

public class MailSettings {

    private String mailServer;
    private String portNumber;
    private String mailUserName;
    private String mailPassword;
    private String starttls;
    private String authentication;
    private String socketFactoryPort;
    private String socketFactoryClass;
    private String fromMailAddress;

    public MailSettings(){
    }
    
    public MailSettings(String mailServer, String serverPort, String userName, String password, String authentication,
	    String starttls, String socketFactoryPort, String socketFactoryClass, String fromMailAddress) {
	this.mailServer = mailServer;
	portNumber = serverPort;
	mailUserName = userName;
	mailPassword = password;
	this.starttls = starttls;
	this.authentication = authentication;
	this.socketFactoryPort = socketFactoryPort;
	this.socketFactoryClass = socketFactoryClass;
	this.fromMailAddress = fromMailAddress;
    }

	public String getMailServer() {
		return mailServer;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public String getMailUserName() {
		return mailUserName;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public String getStarttls() {
		return starttls;
	}

	public String getAuthentication() {
		return authentication;
	}

	public String getSocketFactoryPort() {
		return socketFactoryPort;
	}

	public String getSocketFactoryClass() {
		return socketFactoryClass;
	}

	public String getFromMailAddress() {
		return fromMailAddress;
	}

	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public void setStarttls(String starttls) {
		this.starttls = starttls;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public void setSocketFactoryPort(String socketFactoryPort) {
		this.socketFactoryPort = socketFactoryPort;
	}

	public void setSocketFactoryClass(String socketFactoryClass) {
		this.socketFactoryClass = socketFactoryClass;
	}

	public void setFromMailAddress(String fromMailAddress) {
		this.fromMailAddress = fromMailAddress;
	}
    


}
