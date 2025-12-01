package com.dailyexpenses.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;



public class UserResponse {
	
	private UUID id;
	private String name;
	private String email;
	private String mobile;
	
	public UserResponse(UUID uuid, String name, String email, String mobile) {
		this.id = uuid;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


}
