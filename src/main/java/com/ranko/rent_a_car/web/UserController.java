package com.ranko.rent_a_car.web;

import com.ranko.rent_a_car.model.Role;
import com.ranko.rent_a_car.model.User;
import com.ranko.rent_a_car.model.UserRole;
import com.ranko.rent_a_car.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@Transactional
@RequestMapping("/admin/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * This method will provide Role list to views
	 */
	@ModelAttribute("roles")
	public Map<Role, String > initializeRoles() {
		Map<Role, String> roles = new LinkedHashMap<>();
		roles.put(Role.ADMIN, Role.ADMIN.getRole());
		roles.put(Role.USER, Role.USER.getRole());
		roles.put(Role.GUEST, Role.GUEST.getRole());

		return roles;
	}

	@InitBinder("roles")
	public void initVehicleBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}

	@RequestMapping(method= RequestMethod.GET)
	public String getUsers(@RequestParam(value="userName", required=false) String userName, Model model) {
		Collection<User> users;
		if ((userName == null || "".equals(userName))) {
			users = userService.findAll();
		} else {
			users = new HashSet<>();
			User user = userService.findByUserName(userName);
			users.add(user);
		}
		model.addAttribute("users", users);

		return "users";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "addEditUser";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "addEditUser";
		}
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User saved successfully!");

		userService.save(user);

		return "redirect:/admin/users/" + user.getId();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable Long id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "addEditUser";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewUser(@PathVariable("id") Long id, Model model) {

		logger.debug("showUser() id: {}", id);

		User user = userService.findOneWithRoles(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "user not found");
		}
		model.addAttribute("user", user);

		return "showUser";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String removeUser(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
		logger.debug("delete user: {}", id);

		userService.remove(id);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "User is deleted!");

		return "redirect:/users";
	}

}
