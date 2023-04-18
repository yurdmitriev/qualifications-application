package com.yurdm.qualifications.config;

import com.yurdm.qualifications.repository.UsersRepository;
import com.yurdm.qualifications.security.UserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class AppConfiguration {
    private final UsersRepository usersRepository;

    public AppConfiguration(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> new UserDetails(usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User is not found")));
    }

}
