package com.ranko.rent_a_car.web;

import com.ranko.rent_a_car.model.Customer;
import com.ranko.rent_a_car.model.Rental;
import com.ranko.rent_a_car.service.CustomerService;
import com.ranko.rent_a_car.service.RentalService;
import com.ranko.rent_a_car.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customers/{customerId}/rentals")
public class RentalController {

	private final Logger logger = LoggerFactory.getLogger(RentalController.class);

	private final RentalService rentalService;

	private final CustomerService customerService;

	private final VehicleService vehicleService;

	@Autowired
	public RentalController(RentalService rentalService, CustomerService customerService, VehicleService vehicleService) {
		this.rentalService = rentalService;
		this.customerService = customerService;
		this.vehicleService = vehicleService;
	}

	@ModelAttribute("customer")
	public Customer findCustomer(@PathVariable("customerId") Long id) {
		return customerService.findOneWithRentals(id);
	}

	@InitBinder("customer")
	public void initCustomerBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}

	@InitBinder("vehicle")
	public void initVehicleBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}


	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newRental(Customer customer, Model model) {
		Rental rental = new Rental();
		customer.addRental(rental);
		model.addAttribute("rental", rental);
		model.addAttribute("vehicles", vehicleService.findAll());
		return "addEditRental";
	}

	@RequestMapping(/*value = "/new",*/ method=RequestMethod.POST)
	public String saveNewRental(Customer customer, @Valid Rental rental, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("rental", rental);
			return "addEditRental";
		} else {
			customer.addRental(rental);
			rentalService.save(rental);
			return "redirect:/customers/{customerId}";
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editRental(@PathVariable Long id, Model model) {
		Rental rental = rentalService.findOne(id);
		model.addAttribute("rental", rental);
		model.addAttribute("vehicles", vehicleService.findAll());
		return "addEditRental";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewRental(@PathVariable("id") Long id, Model model) {

		logger.debug("showRental() id: {}", id);

		Rental rental = rentalService.findOne(id);
		if (rental == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "rental not found");
		}
		model.addAttribute("rental", rental);

		return "showRental";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String removeRental(@PathVariable Long id, final RedirectAttributes redirectAttributes) { //, HttpRequest request
		logger.debug("delete rental: {}", id);
//		logger.debug("referer: {}", request.getURI());
		rentalService.remove(id);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Rental is deleted!");

		return "redirect:/customers/{customerId}";
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(HttpMessageNotReadableException e) {
		logger.warn("Returning HTTP 400 Bad Request", e);
	}
}
