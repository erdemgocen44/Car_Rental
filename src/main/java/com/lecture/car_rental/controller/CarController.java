package com.lecture.car_rental.controller;


import com.lecture.car_rental.domain.Car;
import com.lecture.car_rental.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping("/car")
public class CarController {

    public CarService carService;

    @PostMapping("/admin/{imageId}/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> addCar(@PathVariable String imageId,
                                                       @Valid @RequestBody Car car) {
        carService.add(car, imageId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Car update successfully!", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }
}
