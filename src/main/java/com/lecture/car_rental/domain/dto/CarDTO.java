package com.lecture.car_rental.domain.dto;


import com.lecture.car_rental.domain.FileDB;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO {
    private Long id;

    private String model;

    private Integer doors;

    private Integer seats;

    private Integer luggage;

    private String transmission;

    private Boolean airConditioning;

    private Integer age;

    private Double pricePerHour;

    private String fuelType;
    private Boolean builtIn;

    private Set<String> image;

    public Set<String> getImageId(Set<FileDB> images) {

        Set<String> img = new HashSet<>();
        FileDB[] fileDB = images.toArray(new FileDB[images.size()]);

        for (int i = 0; i < images.size(); i++) {
            img.add(fileDB[i].getId());
        }
        return img;
    }
}
