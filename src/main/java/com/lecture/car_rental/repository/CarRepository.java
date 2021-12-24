package com.lecture.car_rental.repository;

import com.lecture.car_rental.domain.Car;
import com.lecture.car_rental.domain.dto.CarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT new com.lecture.car_rental.dto.CarDTO(c) FROM Car c")
    List<CarDTO> findAllCar();
}
