package com.cognato.cmms.service;

import com.cognato.cmms.entity.User;
import com.cognato.cmms.model.UserRequest;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    User save(UserRequest userRequest);

    List<User> getAll();

    User findUserById(Long id);

    User update(User user);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
