package com.ranko.rent_a_car.model;

public enum Role {
	USER("USER"),
	GUEST("GUEST"),
	ADMIN("ADMIN");

	private String role;

	private Role(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

}
