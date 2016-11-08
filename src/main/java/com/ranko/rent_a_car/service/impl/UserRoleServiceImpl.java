package com.ranko.rent_a_car.service.impl;

import com.ranko.rent_a_car.model.UserRole;
import com.ranko.rent_a_car.repository.UserRolesRepository;
import com.ranko.rent_a_car.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Override
	public UserRole findOne(Long id) {
		return userRolesRepository.findOne(id);
	}

	@Override
	public Set<UserRole> findRolesByUserName(String userName) {
		return userRolesRepository.findRolesByUserName(userName);
	}

	@Override
	public UserRole save(UserRole userRole) {
		return userRolesRepository.save(userRole);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		UserRole userRole = userRolesRepository.findOne(id);
		if (userRole == null) {
			throw new IllegalArgumentException(String.format(
					"UserRole with id=%d does not exist.", id));
		}
		userRolesRepository.delete(id);
	}
}
