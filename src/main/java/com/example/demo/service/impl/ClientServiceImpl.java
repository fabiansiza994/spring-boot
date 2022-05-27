package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final UserRepository clientRepository;

    @Autowired
    public ClientServiceImpl(UserRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<User> list() {
        return clientRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return this.clientRepository.findAllByUsername(username);
    }
}
