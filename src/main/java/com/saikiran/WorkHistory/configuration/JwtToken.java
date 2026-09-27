package com.saikiran.WorkHistory.configuration;

import com.saikiran.WorkHistory.service.JwtTokenService;
import com.saikiran.WorkHistory.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtToken extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private ApplicationContext context;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeadeer=request.getHeader("Authorization");
        String username=null;
        String token=null;
        if(authHeadeer!=null && authHeadeer.startsWith("Bearer ")){
            token=authHeadeer.substring(7);
            username=jwtTokenService.extractUsername(token);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            if(jwtTokenService.validateToken(userDetails,token)){
                UsernamePasswordAuthenticationToken authenToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenToken);
            }
        }
        filterChain.doFilter(request,response);



    }
}
