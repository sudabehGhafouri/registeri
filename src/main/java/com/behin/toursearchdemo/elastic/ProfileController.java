/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bamika
 */
@RestController("/api/v1/profiles")
public class ProfileController {

    private ProfileService service;

    @Autowired
    public ProfileController(ProfileService service) {

        this.service = service;
    }

//    @PostMapping
//    public ResponseEntity createProfile(
//        @RequestBody ProfileDocument document) throws Exception {
//
//        return 
//            new ResponseEntity(service.createProfile(document), HttpStatus.CREATED);
//    }
    
    @GetMapping("/{id}")
    public ProfileDocument findById(@PathVariable String id) throws Exception {

        return service.findById(id);
    }
}