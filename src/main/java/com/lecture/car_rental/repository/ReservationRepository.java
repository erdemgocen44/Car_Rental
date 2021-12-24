package com.lecture.car_rental.repository;
import com.lecture.car_rental.domain.Reservation;
import com.lecture.car_rental.domain.enumeration.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r " +
            "LEFT JOIN fetch r.carId cd " +
            "LEFT JOIN fetch cd.image img " +
            "LEFT JOIN fetch r.userId uid " +
            "WHERE (cd.id = ?1 and r.status <> ?4 and r.status <> ?5 and ?2 BETWEEN r.pickUpTime and r.dropOfTime) or " +
            "(cd.id = ?1 and r.status <> ?4 and r.status <> ?5 and ?3 BETWEEN r.pickUpTime and r.dropOfTime)")
    List<Reservation> checkStatus(Long carId, LocalDateTime pickUpTime, LocalDateTime dropOffTime,
                                  ReservationStatus done, ReservationStatus canceled);
}
