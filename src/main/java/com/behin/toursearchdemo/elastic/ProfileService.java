/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.elastic;

import com.fasterxml.jackson.databind.ObjectMapper;
import static java.lang.Double.TYPE;
import java.util.Map;
import java.util.UUID;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bamika
 */
@Service
//@Slf4j
public class ProfileService {

    private RestHighLevelClient client;

    private ObjectMapper objectMapper;

    @Autowired
    public ProfileService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public String createProfile(ProfileDocument document) throws Exception {

        UUID uuid = UUID.randomUUID();
        document.setHoteh_id(1);

        Map<String, Object> documentMapper = objectMapper.convertValue(document, Map.class);
       
        IndexRequest indexRequest = new IndexRequest("hotel", "-doc", "1")
                .source(documentMapper);
       
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        return indexResponse
                .getResult()
                .name();
    }
    public ProfileDocument findById(String id) throws Exception {

        GetRequest getRequest = new GetRequest("hotel", "-doc", "1");

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();

        return objectMapper
                .convertValue(resultMap, ProfileDocument.class);


    }
}