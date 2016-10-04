package com.ranko.rent_a_car.service.impl;

import com.ranko.rent_a_car.model.Customer;
import com.ranko.rent_a_car.repository.CustomerRepository;
import com.ranko.rent_a_car.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer findOne(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Collection<Customer> findByLastName(String lastName) {
		return customerRepository.findByLastName(lastName);
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		customerRepository.delete(id);
	}
}
