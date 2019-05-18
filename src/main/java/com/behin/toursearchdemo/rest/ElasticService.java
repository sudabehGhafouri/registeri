/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.rest;

import Model.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static util.Constant.INDEX;
import static util.Constant.TYPE;



/**
 *
 * @author bamika
 */
@Service
@Slf4j
public class ElasticService {
     private RestHighLevelClient client;
//
    private ObjectMapper objectMapper;
//
    @Autowired
    public ElasticService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }
    
    
     public List<Hotel> findAll() throws Exception {
        SearchRequest searchRequest = buildSearchRequest(INDEX,TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
       
        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);
    }
     
      private List<Hotel> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<Hotel> profileDocuments = new ArrayList<>();

        for (SearchHit hit : searchHit){
            profileDocuments
                    .add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), Hotel.class));
        }

        return profileDocuments;
    }
       private SearchRequest buildSearchRequest(String index, String type) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);

        return searchRequest;
    }
    
}
