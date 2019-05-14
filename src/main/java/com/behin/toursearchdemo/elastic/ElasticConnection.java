/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.elastic;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author bamika
 */
public class ElasticConnection {
    @Configuration
    public class ElasticsearchConfig {

        @Value("${elasticsearch.host}")
        private final String host;

        @Value("${elasticsearch.port}")
        private final int port;

        public ElasticsearchConfig() {
            this.port = 9200;
            this.host = "localhost";
        }
        @Bean(destroyMethod = "close")
        public RestHighLevelClient client() {
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(host, port)));
            return client;
        }
    }
    // use the dependency in your other components/services, using dependency injection
    @Autowired
    private RestClient restClient;
}
