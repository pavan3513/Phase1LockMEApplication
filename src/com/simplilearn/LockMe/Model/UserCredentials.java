package com.simplilearn.LockMe.Model;

public class UserCredentials {
	
	private String siteName;
	private String loginUser;
	private String username;
	private String password;
	
	public UserCredentials() {}	


	public UserCredentials(String siteName, String loginUser, String username, String password) {
		super();
		this.siteName = siteName;
		this.loginUser = loginUser;
		this.username = username;
		this.password = password;
	}


	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserCredentials [siteName=" + siteName + ", loginUser=" + loginUser + ", username=" + username
				+ ", password=" + password + "]";
	}

}
