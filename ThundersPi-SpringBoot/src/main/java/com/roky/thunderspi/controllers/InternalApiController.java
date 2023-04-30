package com.roky.thunderspi.controllers;

import com.roky.thunderspi.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
public class InternalApiController {

    @Autowired
    private UserServiceImpl userService;

    @PutMapping("/make-admin/{email}")
    public ResponseEntity<?> makeAdmin(@PathVariable String email){
         userService.makeAdmin(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
