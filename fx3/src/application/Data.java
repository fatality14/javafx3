package application;

public class Data {
	private String username = "replaceme@replace.me";
	private String password = "replaceme";
	
	private String host = "yandex.ru";
	private int port = 587;
	private boolean doAuth = true;
	private boolean doTLS = true;
	private boolean doSSL = false;
	
	public Data() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
		setHostUsingCurrMail();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isDoAuth() {
		return doAuth;
	}
	public void setDoAuth(boolean doAuth) {
		this.doAuth = doAuth;
	}
	public boolean isDoTLS() {
		return doTLS;
	}
	public void setDoTLS(boolean doTLS) {
		this.doTLS = doTLS;
		if(this.doTLS) {
			doSSL = false;
		}
	}
	public boolean isDoSSL() {
		return doSSL;
	}
	public void setDoSSL(boolean doSSL) {
		this.doSSL = doSSL;
		if(this.doSSL) {
			doTLS = true;
		}
	}
	
	private void setHostUsingCurrMail() {
		int atIndex;
		
		atIndex = username.indexOf("@");		
		host = username.substring(atIndex+1);
	}
}
