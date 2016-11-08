package com.ranko.rent_a_car.web.converter;

import com.ranko.rent_a_car.model.UserRole;
import com.ranko.rent_a_car.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Converts string to UserRole object.
 * 
 */
@Component
public class StringToUserRoleConverter implements Converter<String, UserRole> {

	@Autowired
	private UserRoleService userRoleService;

	@Override
	public UserRole convert(String role) {
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		return userRole;
	}
}
