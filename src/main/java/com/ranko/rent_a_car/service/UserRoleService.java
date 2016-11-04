package com.ranko.rent_a_car.service;

import com.ranko.rent_a_car.model.User;
import com.ranko.rent_a_car.model.UserRole;

import java.util.List;


public interface UserRoleService {
	UserRole findRoleByUserName(String userName);
	UserRole findOne(Long id);
	UserRole save(UserRole userRole);
	void remove(Long id) throws IllegalArgumentException;
}
