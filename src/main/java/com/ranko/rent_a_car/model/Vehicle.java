package com.ranko.rent_a_car.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vehicles")
public class Vehicle {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@NotEmpty
	private String name;

	private String brand;


}
