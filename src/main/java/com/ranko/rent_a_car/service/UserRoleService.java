package com.ranko.rent_a_car.service;

import com.ranko.rent_a_car.model.User;
import com.ranko.rent_a_car.model.UserRole;

import java.util.List;
import java.util.Set;


public interface UserRoleService {
	Set<UserRole> findRolesByUserName(String userName);
	UserRole findOne(Long id);
	UserRole save(UserRole userRole);
	void remove(Long id) throws IllegalArgumentException;
}
