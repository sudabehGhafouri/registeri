/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.rest;

import Model.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//    //========================================================================
    public String createProfileDocument() throws Exception {
       IndexRequest request = new IndexRequest(
                    "posts",
                    "doc",
                    "2");
            String jsonString = "{"
                    + "\"user\":\"ghhh\","
                    + "\"postDate\":\"2013-01-30\","
                    + "\"message\":\"trying \""
                    + "}";
            request.source(jsonString, XContentType.JSON);
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            long version = indexResponse.getVersion();
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                System.out.println(index + "created");
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                System.out.println(index + "updated");
            }
            ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            }
            if (shardInfo.getFailed() > 0) {
                for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                    String reason = failure.reason();
                }
            }
//
////        UUID uuid = UUID.randomUUID();
////        document.setId(uuid.toString());
////        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, "3")
////                .source(convertProfileDocumentToMap(document));
////        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.getResult().name();
    }
//    //======================================================================
     public String insertHotel(Hotel hotel) throws Exception {
         List<String> imagePath=new ArrayList<>();
         imagePath.add("http://10.0.2.2:8080/img/2.jpg");
         imagePath.add("http://10.0.2.2:8080/img/test.jpg");
         hotel.setImagePath(imagePath);
        IndexRequest indexRequest = new IndexRequest(INDEX,TYPE,hotel.getHotelId())
                .source(convertProfileDocumentToMap(hotel));
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.getResult().name();
    }
    //======================================================================

    public List<Hotel> findAll() throws Exception {
        SearchRequest searchRequest = buildSearchRequest(INDEX, TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse
                = client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);
    }
//=======================================================================
    private List<Hotel> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<Hotel> profileDocuments = new ArrayList<>();

        for (SearchHit hit : searchHit) {
            profileDocuments
                    .add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), Hotel.class));
        }

        return profileDocuments;
    }
//============================================================================
    private SearchRequest buildSearchRequest(String index, String type) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);

        return searchRequest;
    }
//=========================================================================
    private Map<String, Object> convertProfileDocumentToMap(Hotel profileDocument) {
        return objectMapper.convertValue(profileDocument, Map.class);
    }

    private Hotel convertMapToProfileDocument(Map<String, Object> map) {
        return objectMapper.convertValue(map, Hotel.class);
    }

}
