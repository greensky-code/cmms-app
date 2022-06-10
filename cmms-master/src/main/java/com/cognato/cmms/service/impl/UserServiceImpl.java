package com.cognato.cmms.service.impl;

import com.cognato.cmms.entity.User;
import com.cognato.cmms.exception.UserNotExistsException;
import com.cognato.cmms.model.UserRequest;
import com.cognato.cmms.repository.UserRepository;
import com.cognato.cmms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User save(UserRequest userRequest) {
       User user = new User();
       user.setName(userRequest.getName());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotExistsException("User Not Exists"));
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return org.springframework.security.core.userdetails.User
          .withUsername(username)
          .password("test")
          .authorities("admin")
          .accountExpired(false)
          .accountLocked(false)
          .credentialsExpired(false)
          .disabled(false)
          .build();
    }
}
