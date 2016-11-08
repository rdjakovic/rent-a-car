package com.ranko.rent_a_car.repository;

import com.ranko.rent_a_car.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	
	@Query("select ur.role from UserRole ur, User u where u.userName=?1 and ur.user.id=u.id")
    public List<String> findRoleByUserName(String username);

	@Query("select ur.role from UserRole ur, User u where u.userName=?1 and ur.user.id=u.id")
	public Set<UserRole> findRolesByUserName(String username);
	
}