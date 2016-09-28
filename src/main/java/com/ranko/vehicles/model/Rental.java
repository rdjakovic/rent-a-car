package com.ranko.vehicles.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "rentals")
public class Rental {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name = "rental_date")
	@NotEmpty
	private Date rentalDate;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Rental customer;

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Rental getCustomer() {
		return customer;
	}

	public void setCustomer(Rental customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
