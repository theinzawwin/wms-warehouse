package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.CreateUserInput;
import com.pearl.warehouse.model.Role;
import com.pearl.warehouse.model.User;
import com.pearl.warehouse.repository.RoleRepository;
import com.pearl.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(CreateUserInput request) {
        if (userRepository.existsByUsername(request.username)) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.email)) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.username);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setEmail(request.email);

        Set<Role> roles = new HashSet<>();
        for (String roleName : request.roles) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                throw new RuntimeException("Role not found: " + roleName);
            }
            roles.add(role);
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
