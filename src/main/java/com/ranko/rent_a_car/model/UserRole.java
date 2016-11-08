package com.ranko.rent_a_car.model;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="role")
	private String role = Role.USER.getRole();



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return role;
	}
}
