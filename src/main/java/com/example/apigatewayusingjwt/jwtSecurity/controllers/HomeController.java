package com.example.apigatewayusingjwt.jwtSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apigatewayusingjwt.jwtSecurity.models.JwtRequest;
import com.example.apigatewayusingjwt.jwtSecurity.models.JwtResponse;
import com.example.apigatewayusingjwt.jwtSecurity.services.MyUserDetailsService;
import com.example.apigatewayusingjwt.jwtSecurity.utility.MyJwtUtil;

@RestController
public class HomeController {

    @Autowired
    private MyJwtUtil utility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping("/msg")
    public String getMessage(){
        return "I'm up and ready to go mate";
    }
    
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest request) throws Exception{
        System.out.println("in authenticate method");
       try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println("in authenticate method username  from request are \""+request.getUsername()+"\" and pass word "+request.getPassword());
       } catch (BadCredentialsException e) {
        e.printStackTrace();
            throw new Exception("CREDENTIALS_ARE_INVALID",e);
       }

       final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUsername());

       final String token=utility.generateToken(userDetails);
       System.out.println("-------------------------");
       System.out.println("token generated here is - "+token);
       System.out.println("-------------------------");     
       return new JwtResponse(token); //via param cnstr of JWTresponse
    }

}
