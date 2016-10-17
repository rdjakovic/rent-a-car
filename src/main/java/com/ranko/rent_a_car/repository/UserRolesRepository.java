package com.ranko.rent_a_car.repository;
import java.util.List;

import com.ranko.rent_a_car.model.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	
	@Query("select a.role from UserRole a, User b where b.userName=:username and a.userid=b.userId")
    public List<String> findRoleByUserName(String username);
	
}