package com.example.apigatewayusingjwt.jwtSecurity.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.apigatewayusingjwt.jwtSecurity.services.MyUserDetailsService;
import com.example.apigatewayusingjwt.jwtSecurity.utility.MyJwtUtil;


/*
 * creating my own custom filter for spring to know that this filter should be used to authenticate and not the defaults
 *      -- Using OncePerRequestFilter because i want my api to authenticate for each and every request  
 *      -- overriding the method to provide custom implementaion and not of spring's 
 */
@Component
public class JwtCustomFilter extends OncePerRequestFilter{

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private MyJwtUtil utility;

    /*
     * To get hold of the token passed in the header of the request 
     *  --doing this by getting header with the name 'Authorization (passed in postman)'
     *  --Now header contains the token 
     * 
     * to get the token extracted from the header
     * to get the username extracted from the token as we need to authenticate it against
     * 
     * Once we get these two now we are ready to authenticate 
     *  --use custom userdetails to get the user with the username captured by token (to know who are registered with the api (typically from the db))
     *  --then once we get the userdetails then i have to validate the token info against userdetails from which the token was made of
     *  --so validating the token and userdetails
     *  --the token is valid and now we have to let spring know that we have a user to be allowed 
     *  --so create a usernamePasswordAuthenticationToken (usually generated by spring to handle request generated tokens)
     *  --this token will have our userdetails as parameter and credentials null for now and for the authorities which we created a list of (empty for now)
     *  --using this upat token and using its setter to set the details in its object body to show spring that heyy i got a user for you
     *  --finally using the security context to set the authenticationtoken provided my me 
     *  --pass the filterchain to next filters in the line with (same request and response parameters)
     * 
     * 
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("in customFilter method() ------------------------------------------");
        String authorization=request.getHeader("Authorization");
        String token=null;
        String username=null;

        if(authorization !=null && authorization.startsWith("Bearer ")){
            token=authorization.substring(7);
            username=utility.getUsernameFromToken(token);
            System.out.println("request body has :"+username+" token value is"+token);
            System.out.println();
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            if(utility.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //SecurityContextHolder.createEmptyContext();
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
