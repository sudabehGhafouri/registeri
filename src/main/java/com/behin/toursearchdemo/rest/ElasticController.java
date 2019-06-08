/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.rest;

import Model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//
    @RequestMapping("/test")
    public String test() {

        return "Success";
    }

    @GetMapping("/test2")
    public String test2() {

        return "full";
    }

//    //=================================================================
//    @PostMapping
//    public void createProfileDocument() throws Exception {
//        service.createProfileDocument();
//    }
//
////    //==================================================================
    @PutMapping("/insertProfile")
//    public ResponseEntity insertProfile(@RequestBody Hotel document) throws Exception {
        public void insertProfile(@RequestBody Hotel document) throws Exception {

        service.insertHotel(document);
//        return new ResponseEntity(service.insertHotel(document), HttpStatus.CREATED);
//        service.insertHotel(document);
    }
//
//    //==================================================================
    @GetMapping("/find")
    public List<Hotel> findAll() throws Exception {

        return service.findAll();
    }
////    //==================================================================
//    public Hotel HotelSearch(String id) throws Exception {
//
//            GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
//
//            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//            Map<String, Object> resultMap = getResponse.getSource();
//
//            return convertMapToProfileDocument(resultMap);
//////
//////    }
}
