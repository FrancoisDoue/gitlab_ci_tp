package com.example.ex2_todo.service;

import com.example.ex2_todo.config.jwt.JwtProvider;
import com.example.ex2_todo.entity.Role;
import com.example.ex2_todo.entity.User;
import com.example.ex2_todo.exception.NotFoundException;
import com.example.ex2_todo.repository.RoleRepository;
import com.example.ex2_todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       @Lazy AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email+" not found"));
    }

    public void createUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Role> roles = user.getRoles().stream()
                    .map(r -> roleRepository.findByRole(r.getRole())
                    .orElse(r)).toList();
            user.setRoles(roles);
            userRepository.save(user);
        } catch (Exception e) {
            throw new BadCredentialsException("invalid email or password");
        }

    }

    public boolean verifyUser(String email, String password) {
        return userRepository.findByEmail(email).map(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
    }

    public String generateToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

}
