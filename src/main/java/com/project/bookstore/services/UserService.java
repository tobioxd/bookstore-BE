package com.project.bookstore.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bookstore.components.JwtTokenUtil;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

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

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public String loginUser(String username, String password) throws Exception {
        Optional<User> user= userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new DataNotFoundException("Invalid username/password");
        }

        User existinguser = user.get();

        if(!passwordEncoder.matches(password, existinguser.getPassword())){
            throw new BadCredentialsException("Invalid username/password");
        }

        User existingUser = user.orElseThrow(() -> new DataNotFoundException("Invalid username/password"));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, existingUser.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existinguser);

    }

}
