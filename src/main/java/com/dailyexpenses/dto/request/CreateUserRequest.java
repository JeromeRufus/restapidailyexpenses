package com.dailyexpenses.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class CreateUserRequest {
	
	
	@NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Moblie is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be a valid 10-digit number")
	private String mobile;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobilenumber) {
		this.mobile = mobilenumber;
	}

}
