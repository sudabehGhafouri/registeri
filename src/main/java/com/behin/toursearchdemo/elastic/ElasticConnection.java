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

/**
 *
 * @author bamika
 */
public class ElasticConnection {
      public Map<String, Object>  elastic() {
        try {
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http"),
                            new HttpHost("localhost", 9201, "http")));
//------------------------------------------------------------------------------------
/*            IndexRequest request = new IndexRequest(
                    "posts",
                    "doc",
                    "1");
            String jsonString = "{"
                    + "\"user\":\"kimchy\","
                    + "\"postDate\":\"2013-01-30\","
                    + "\"message\":\"trying out Elasticsearch\""
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
            }*/
            //----------------------------------------------------------------
            GetRequest getRequest = new GetRequest(
                    "hotel",
                    "_doc",
                    "1");
            getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
            String[] includes = new String[]{"message", "*Date"};
            String[] excludes = Strings.EMPTY_ARRAY;
            FetchSourceContext fetchSourceContext
                    = new FetchSourceContext(true, includes, excludes);
            getRequest.fetchSourceContext(fetchSourceContext);
            
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            String index1 = getResponse.getIndex();
            String type1 = getResponse.getType();
            String id1 = getResponse.getId();
            if (getResponse.isExists()) {
                long version1 = getResponse.getVersion();
                String sourceAsString = getResponse.getSourceAsString();
                Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
                byte[] sourceAsBytes = getResponse.getSourceAsBytes();
                return sourceAsMap;
            } else {

            }

            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ElasticConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
