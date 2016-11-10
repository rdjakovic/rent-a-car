package com.ranko.rent_a_car.web;

import com.ranko.rent_a_car.model.User;
import com.ranko.rent_a_car.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Transactional
@RequestMapping("/password")
public class PasswordController {

	private final Logger logger = LoggerFactory.getLogger(PasswordController.class);

	@Autowired
	private UserService userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPassword(@PathVariable Long id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "changePassword";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String updatePassword(@RequestParam(value="password", required=false) String password, User user, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("password", password);
			return "changePassword";
		}

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Password updated successfully!");
		/*user = userService.findByUserName(user.getUsername());
		model.addAttribute("user", user);*/
		userService.save(user);

		return "redirect:/password/edit/" + user.getId();
	}

}
