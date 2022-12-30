package com.example.apigatewayusingjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.apigatewayusingjwt.jwtSecurity.models.UserDetailsFromDb;

public interface UserDetailsRepositoryDb extends CrudRepository<UserDetailsFromDb,Integer>{
    UserDetailsFromDb findByUsername(String username);
 }
