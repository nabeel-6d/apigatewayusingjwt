package com.example.apigatewayusingjwt.jwtSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.apigatewayusingjwt.jwtSecurity.filter.JwtCustomFilter;
import com.example.apigatewayusingjwt.jwtSecurity.services.MyUserDetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class JwtConfigurer extends WebSecurityConfigurerAdapter{

    
    @Autowired
    private MyUserDetailsService userService;

    @Autowired
    private JwtCustomFilter jwtCustomFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("in configure AuthenticationManagerBuilder() ------------------------------>");
        auth.userDetailsService(userService);
        System.out.println("my user service is "+userService);
        System.out.println();
    }
     
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        System.out.println("in configure authenticationManager-Bean() ------------------------------>");
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("in configure HttpSecurity() ------------------------------>");
        http.csrf().disable()
        .authorizeRequests().antMatchers("/authenticate").permitAll()
        .anyRequest().authenticated().and().formLogin().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(jwtCustomFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    
}
