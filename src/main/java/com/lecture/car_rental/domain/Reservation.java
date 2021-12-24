package com.lecture.car_rental.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car carId;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
    @NotNull(message = "Please enter the pick up time of the reservation")
    @Column(nullable = false)
    private LocalDateTime pickUpTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
    @NotNull(message = "Please enter the drop of time of the reservation")
    @Column(nullable = false)
    private LocalDateTime dropOfTime;
    @NotNull(message = "Please enter the")
    private String pickUpLocation;
}
