/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.rest;

import Model.Hotel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bamika
 */
@RestController
public class ElasticController {
   private final ElasticService service;
//
    @Autowired
    public ElasticController(ElasticService service) {
       this.service = service;
    }
    @RequestMapping("/test")
     public String test() {

        return "Success";
    }
     @GetMapping("/test2")
      public String test2() {

        return "Success";
    }
      @GetMapping
    public List<Hotel> findAll() throws Exception {

        return service.findAll();
    } 
}
