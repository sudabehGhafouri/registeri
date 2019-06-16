/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.elastic;

//import org.apache.http.HttpHost;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.settings.Settings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
///**
// *
// * @author bamika
// */
//public class ElasticConnection {
//    @Configuration
//    @EnableElasticsearchRepositories(basePackages = "com.behin.toursearchdemo.repository")
//    @ComponentScan(basePackages = {"com.behin.toursearchdemo.service"})
//    public class ElasticsearchConfig {
//        @Value("${elasticsearch.home:/usr/local/Cellar/elasticsearch/5.6.0}")
//        private String elasticsearchHome;
//
//        @Value("${elasticsearch.cluster.name:elasticsearch}")
//        private String clusterName;
//
//        @Value("${elasticsearch.host}")
//        private final String host;
//
//        @Value("${elasticsearch.port}")
//        private final int port;
//
//        public ElasticsearchConfig() {
//            this.port = 9200;
//            this.host = "localhost";
//        }
//
//        @Bean(destroyMethod = "close")
//        public RestHighLevelClient client() {
//
//
//            RestHighLevelClient client = new RestHighLevelClient(
//                    RestClient.builder(new HttpHost(host, port)));
//            Settings elasticsearchSettings = Settings.builder()
//                    .put("path.home", elasticsearchHome)
//                    .put("cluster.name", clusterName).build();
//            return client;
//
//        }
//
//        @Bean
//        public ElasticsearchOperations elasticsearchTemplate() {
//            return new ElasticsearchTemplate((Client) client());
//        }
//
//        // use the dependency in your other components/services, using dependency injection
//        @Autowired
//        private RestClient restClient;
//    }
//}
