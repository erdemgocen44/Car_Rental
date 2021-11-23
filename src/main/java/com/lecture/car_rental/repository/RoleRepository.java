package com.lecture.car_rental.repository;

import com.lecture.car_rental.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
