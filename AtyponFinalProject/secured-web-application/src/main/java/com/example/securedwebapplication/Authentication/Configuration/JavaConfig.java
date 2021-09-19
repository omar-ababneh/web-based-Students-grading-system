package com.example.securedwebapplication.Authentication.Configuration;

import com.example.securedwebapplication.Authentication.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JavaConfig{
    @Bean
    public UserDetailsService userDetailsService() {
       return new UserDetailsServiceImp();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
