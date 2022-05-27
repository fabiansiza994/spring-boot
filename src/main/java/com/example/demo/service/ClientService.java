package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface ClientService {
    List<User> list();
    User findByUsername(String username);
}
