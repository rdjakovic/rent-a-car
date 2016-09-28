package com.ranko.rent_a_car.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="firstname")
	@NotEmpty
	private String firstName;

	@Column(name="lastname")
	@NotEmpty
	private String lastName;

	//if omit @Column(name="phone") it will be automatically mapped to field name
	@Digits(fraction = 0, integer = 10)
	@NotEmpty
	private String phone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.EAGER)
	private Set<Rental> rentals;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return String.format(
				"Customer[id=%d, firstName='%s', lastName='%s', phone='%s']",
				id, firstName, lastName, phone);
	}


}