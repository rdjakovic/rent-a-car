package com.ranko.rent_a_car.service;

import com.ranko.rent_a_car.model.Customer;

import java.util.Collection;

/**
 * Created by r.djakovic on 9/29/2016.
 */
public interface CustomerService {

	Customer findOne(Long id);
	Collection<Customer> findAll();
	Collection<Customer> findByLastName(String lastName);
}
