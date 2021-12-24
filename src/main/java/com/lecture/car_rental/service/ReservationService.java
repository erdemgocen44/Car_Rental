package com.lecture.car_rental.service;


import com.lecture.car_rental.domain.Car;
import com.lecture.car_rental.domain.Reservation;
import com.lecture.car_rental.exception.BadRequestException;
import com.lecture.car_rental.repository.CarRepository;
import com.lecture.car_rental.repository.ReservationRepository;
import com.lecture.car_rental.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    public void addReservation(Reservation reservation, Long userId, Car carId) throws BadRequestException {

    }
}
