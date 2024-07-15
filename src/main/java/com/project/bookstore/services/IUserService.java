package com.project.bookstore.services;

import com.project.bookstore.dtos.UserDTO;
import com.project.bookstore.exceptions.DataNotFoundException;
import com.project.bookstore.models.User;

public interface IUserService {

    User createUser(UserDTO userDTO) throws DataNotFoundException;

    String loginUser(String username, String password) throws Exception;

    User getUserDetailsFromToken(String token) throws Exception;

}
