package com.lecture.car_rental.repository;
import com.lecture.car_rental.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
