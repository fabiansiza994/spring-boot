package com.example.demo.service;

import com.example.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private ClientService clientService;

    private Logger log = LoggerFactory.getLogger(UserService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.clientService.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("error en login");
        }

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getNombre()))
                .peek(simpleGrantedAuthority -> log.info("rol"+simpleGrantedAuthority.getAuthority()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
