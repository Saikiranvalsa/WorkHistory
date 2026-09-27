package com.saikiran.WorkHistory.service;

import com.saikiran.WorkHistory.dto.LoginDto;
import com.saikiran.WorkHistory.dto.SignupDto;
import com.saikiran.WorkHistory.exception.UserAlreadyFound;
import com.saikiran.WorkHistory.model.Customer;
import com.saikiran.WorkHistory.model.Owner;
import com.saikiran.WorkHistory.model.User;
import com.saikiran.WorkHistory.repository.CustomerRepository;
import com.saikiran.WorkHistory.repository.OwnerRepository;
import com.saikiran.WorkHistory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    public User signup(SignupDto signupDto) {
        User user=new User();
        user.setUsername(signupDto.getNumber());
        user.setPassword(signupDto.getPassword());
        user.setName(signupDto.getName());
        user.setNumber(signupDto.getNumber());
        try{
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyFound("user already registered");
        }
        Customer customer=new Customer();
        customer.setUser(user);
        customerRepository.save(customer);
        Owner owner=new Owner();
        owner.setUser(user);
        ownerRepository.save(owner);
        return user;
    }

    public String verify(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtTokenService.genearateJwtToken(loginDto.getUsername());
        }
        return "fail";
    }
}
