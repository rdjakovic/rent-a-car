package com.ranko.rent_a_car.web;

import com.ranko.rent_a_car.model.Rental;
import com.ranko.rent_a_car.model.Vehicle;
import com.ranko.rent_a_car.service.CustomerService;
import com.ranko.rent_a_car.service.RentalService;
import com.ranko.rent_a_car.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/rentals")
public class RentalController {

	private final Logger logger = LoggerFactory.getLogger(RentalController.class);

	@Autowired
	private RentalService rentalService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private VehicleService vehicleService;

	@ModelAttribute("vehicles")
	public Collection<Vehicle> populateVehicles() {
		return this.vehicleService.findAll();
	}

	@RequestMapping(method=RequestMethod.GET)
	public String getRentals(@RequestParam(value="rentalDate", required=false) Date rentalDate, Model model) {
		Collection<Rental> rentals = (rentalDate == null || "".equals(rentalDate) ? rentalService.findAll() : rentalService.findByRentalDate(rentalDate));
		model.addAttribute("rentals", rentals);

		return "rentals";
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

	@RequestMapping(method=RequestMethod.POST)
	public String saveRental(Rental rental, Model model) {
			rentalService.save(rental);
			return "redirect:/rentals";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editRental(@PathVariable Long id, Model model) {
		Rental rental = rentalService.findById(id);
		model.addAttribute("rental", rental);
		return "addEditRental";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newRental(Model model) {
		model.addAttribute("customers", customerService.findAll());
		model.addAttribute("rental", new Rental());
		return "addEditRental";
	}
}
