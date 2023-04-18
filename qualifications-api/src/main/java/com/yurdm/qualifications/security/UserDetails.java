package com.yurdm.qualifications.security;

import com.yurdm.qualifications.model.users.Admin;
import com.yurdm.qualifications.model.users.Employee;
import com.yurdm.qualifications.model.users.Lecturer;
import com.yurdm.qualifications.model.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final User user;

    public UserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SecurityRole role;

        if (getUser() instanceof Employee) {
            role = SecurityRole.COMPANY;
        } else if (getUser() instanceof Admin) {
            role = SecurityRole.ADMIN;
        } else if (getUser() instanceof Lecturer) {
            role = SecurityRole.LECTURER;
        } else {
            role = SecurityRole.STUDENT;
        }

        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO: user verification
        return true;
    }

    public User getUser() {
        return user;
    }
}
