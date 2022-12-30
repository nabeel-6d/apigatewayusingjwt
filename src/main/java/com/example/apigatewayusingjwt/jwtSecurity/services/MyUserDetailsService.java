package com.example.apigatewayusingjwt.jwtSecurity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.apigatewayusingjwt.jwtSecurity.models.UserDetailsFromDb;
import com.example.apigatewayusingjwt.repository.UserDetailsRepositoryDb;

@Service
public class MyUserDetailsService implements UserDetailsService{

    private User userObjectForTestingOnly;
    @Autowired
    private UserDetailsRepositoryDb userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserDetailsFromDb udb=userDetailsRepository.findByUsername(username);
            List<SimpleGrantedAuthority> grantedAuth=new ArrayList<SimpleGrantedAuthority>();
            grantedAuth.add(new SimpleGrantedAuthority(udb.getRole()));

            userObjectForTestingOnly=new User(udb.getUsername(),udb.getPassword(),grantedAuth);

        return userObjectForTestingOnly;
    }
    @Override
    public String toString() {
        return "MyUserDetailsService [u=" + userObjectForTestingOnly + "]";
    }
    
}
