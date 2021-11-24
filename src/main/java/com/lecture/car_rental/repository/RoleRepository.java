package com.lecture.car_rental.repository;

import com.lecture.car_rental.domain.Role;
import com.lecture.car_rental.domain.enumerition.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(UserRole name);
}
