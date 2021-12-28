package com.lecture.car_rental.service;

import com.lecture.car_rental.domain.User;
import com.lecture.car_rental.helper.ExcelHelper;
import com.lecture.car_rental.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@AllArgsConstructor
@Service
public class ExcelService {

    UserRepository userRepository;

    public ByteArrayInputStream loadUser() {
        List<User> users = userRepository.findAll();

        return ExcelHelper.usersExcel(users);
    }
}
