package br.com.planningtravel.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank(message="preencha o email")
	private String usernameOrEmail;
	
	@NotBlank(message="preencha o campo password")
	private String password;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
