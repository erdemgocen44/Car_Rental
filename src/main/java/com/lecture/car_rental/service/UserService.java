package com.lecture.car_rental.service;

import com.lecture.car_rental.domain.Role;
import com.lecture.car_rental.domain.User;
import com.lecture.car_rental.domain.enumeration.UserRole;
import com.lecture.car_rental.dto.UserDTO;
import com.lecture.car_rental.exception.AuthException;
import com.lecture.car_rental.exception.BadRequestException;
import com.lecture.car_rental.exception.ConflictException;
import com.lecture.car_rental.exception.ResourceNotFoundException;
import com.lecture.car_rental.projection.ProjectUser;
import com.lecture.car_rental.repository.RoleRepository;
import com.lecture.car_rental.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final static String USER_NOT_FOUND_MSG = "user with id %d not found";

    public List<ProjectUser> fetchAllUsers() {
        return userRepository.findAllBy();
    }

    public UserDTO findById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));

        UserDTO userDTO = new UserDTO();
        userDTO.setRoles(user.getRole());

        return new UserDTO(user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail(),
                user.getAddress(), user.getZipCode(), userDTO.getRoles(), user.getBuiltIn());
    }

    public void register(User user) throws BadRequestException {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ConflictException("Error: Email is already in use!");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setBuiltIn(false);

        Set<Role> roles = new HashSet<>();
        Role customerRole = roleRepository.findByName(UserRole.ROLE_CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        roles.add(customerRole);

        user.setRoles(roles);
        userRepository.save(user);
    }

    public void login(String email, String password) throws AuthException {
        try {
            Optional<User> user = userRepository.findByEmail(email);

            if (!BCrypt.checkpw(password, user.get().getPassword()))
                throw new AuthException("invalid credentials");
        } catch (Exception e) {
            throw new AuthException("invalid credentials");
        }
    }

    public void updateUser(Long id, UserDTO userDTO) throws BadRequestException {

        boolean emailExists = userRepository.existsByEmail(userDTO.getEmail());
        Optional<User> userDetails = userRepository.findById(id);

        if (userDetails.get().getBuiltIn()) {
            throw new BadRequestException("You dont have permission to update user info!");
        }

        if (emailExists && !userDTO.getEmail().equals(userDetails.get().getEmail())) {
            throw new ConflictException("Error: Email is already in use!");
        }

        userRepository.update(id, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPhoneNumber(),
                userDTO.getEmail(), userDTO.getAddress(), userDTO.getZipCode());
    }
}
