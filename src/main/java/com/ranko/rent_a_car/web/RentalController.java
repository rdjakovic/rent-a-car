package com.ranko.rent_a_car.web;

import com.ranko.rent_a_car.model.Customer;
import com.ranko.rent_a_car.model.Rental;
import com.ranko.rent_a_car.service.CustomerService;
import com.ranko.rent_a_car.service.RentalService;
import com.ranko.rent_a_car.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/customers/{customerId}/rentals")
public class RentalController {

	private final Logger logger = LoggerFactory.getLogger(RentalController.class);

	@Autowired
	private RentalService rentalService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private VehicleService vehicleService;

	@ModelAttribute("customer")
	public Customer findCustomer(@PathVariable("customerId") Long id) {
		return customerService.findOne(id);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping(method=RequestMethod.GET)
	public String getRentals(@RequestParam(value="rentalDate", required=false) Date rentalDate, Model model) {
		Collection<Rental> rentals = (rentalDate == null || "".equals(rentalDate) ? rentalService.findAll() : rentalService.findByRentalDate(rentalDate));
		model.addAttribute("rentals", rentals);

		return "rentals";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newRental(Customer customer, Model model) {
		Rental rental = new Rental();
		customer.addRental(rental);
		model.addAttribute("rental", rental);
		model.addAttribute("vehicles", vehicleService.findAll());
		return "addEditRental";
	}

	@RequestMapping(value = "/new", method=RequestMethod.POST)
	public String saveRental(Customer customer, @Valid Rental rental, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("rental", rental);
			return "addEditRental";
		} else {
			rentalService.save(rental);
			customer.addRental(rental);
			return "redirect:/customers/" + customer.getId();
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editRental(@PathVariable Long id, Model model) {
		Rental rental = rentalService.findById(id);
		model.addAttribute("rental", rental);
		return "addEditRental";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewRental(@PathVariable("id") Long id, Model model) {

		logger.debug("showRental() id: {}", id);

		Rental rental = rentalService.findById(id);
		if (rental == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "rental not found");
		}
		model.addAttribute("rental", rental);

		return "showRental";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String removeRental(@PathVariable Long id) {
		rentalService.remove(id);
		return "rentals";
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handle(HttpMessageNotReadableException e) {
		logger.warn("Returning HTTP 400 Bad Request", e);
	}
}
