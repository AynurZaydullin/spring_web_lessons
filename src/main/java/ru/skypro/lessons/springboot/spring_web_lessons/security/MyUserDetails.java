package ru.skypro.lessons.springboot.spring_web_lessons.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.spring_web_lessons.dto.UserDTO;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetails implements UserDetails {
    private UserDTO userDTO;

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(userDTO)
                .map(UserDTO::getRole)
                .map(role -> new SimpleGrantedAuthority(role.name()))
                        .map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }

    @Override
    public String getPassword() {
        return Optional.ofNullable(userDTO)
                .map(UserDTO::getPassword)
                .orElse(null);
    }

    @Override
    public String getUsername() {
        return null;
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
        return true;
    }
}
