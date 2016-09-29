package com.ranko.rent_a_car.service.impl;

import com.ranko.rent_a_car.model.Customer;
import com.ranko.rent_a_car.repository.CustomerRepository;
import com.ranko.rent_a_car.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer findOne(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Collection<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Collection<Customer> findByLastName(String lastName) {
		return customerRepository.findByLastName(lastName);
	}
}
