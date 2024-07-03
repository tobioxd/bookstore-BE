package com.project.bookstore.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project.bookstore.dtos.UserDTO;
import com.project.bookstore.exceptions.DataNotFoundException;
import com.project.bookstore.models.Role;
import com.project.bookstore.models.User;
import com.project.bookstore.repositories.RoleReponsitory;
import com.project.bookstore.repositories.UserReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserReponsitory userRepository;
    private final RoleReponsitory roleRepository;

    @Override
    public User createUser(UserDTO userDTO) throws DataNotFoundException {
        String username = userDTO.getUsername();
        //System.out.println(!userRepository.existsByUsername(username));
        if (userRepository.existsByUsername(username) == true) {
            throw new DataIntegrityViolationException("Username already exists");
        }

        // Convert UserDTO to User
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .fullname(userDTO.getFullname())
                .gender(userDTO.getGender())
                .dob(userDTO.getDob())
                .phonenumber(userDTO.getPhonenumber())
                .address(userDTO.getAddress())
                .photourl(userDTO.getPhotourl())
                .build();

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found."));
        user.setRoleId(role);

        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) throws DataNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginUser'");
    }

}
