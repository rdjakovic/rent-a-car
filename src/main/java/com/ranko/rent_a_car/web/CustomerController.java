package com.ranko.rent_a_car.web;

import com.ranko.rent_a_car.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method= RequestMethod.GET)
	public String getCustomers(@RequestParam(value="lastName", required=false) String lastName, Model model) {
		Collection customers = (lastName == null || lastName=="" ? customerService.findAll() : customerService.findByLastName(lastName));
		model.addAttribute("customersmodel", customers);

		return "customers";
	}
}
