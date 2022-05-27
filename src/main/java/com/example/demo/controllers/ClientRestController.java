package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientRestController {

    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> data() {
        var data = clientService.list();
        return  new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/login/{username}")
    public ResponseEntity user(@PathVariable String username){
        var user = this.clientService.findByUsername(username);
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
