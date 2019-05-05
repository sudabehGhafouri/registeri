/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.rest;
import com.behin.toursearchdemo.rs.SentenceRecommenderSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 *
 * @author Hadi
 */
@RestController
public class TourSearchController {
    private static final SentenceRecommenderSystem sentenceRS = new SentenceRecommenderSystem();
    @CrossOrigin(origins = "127.0.0.1:8080")
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }    
    /**
     *
     * @param givenSentence
     * @param numResults
     * @return recommended sentences
     */
    @RequestMapping("/GetSentence")
    public String getSentenceRecommendations(@RequestParam(value = "sentence", defaultValue = "Test sentence") String givenSentence, 
                                                @RequestParam(value = "numResults", defaultValue = "5") int numResults){
        String result = "";
        Pair<List <Map.Entry<String, Double>>, HashMap<String, String>> sortedRecoms = sentenceRS.getSentenceRecom(givenSentence);
        int rank = 1;
        HashMap<String, String> sentence_filename_map = sortedRecoms.getValue();
        for(Map.Entry<String,Double> entry : sortedRecoms.getKey()){
            if(rank > numResults){
                break;
            }
            String filename = "unknown";
            if(sentence_filename_map.get(entry.getKey()) != null){
                filename = sentence_filename_map.get(entry.getKey());
            }
            result += "<b>" + rank + ") <a href=\"file:///Volumes/ING/MyCodes/workspace/ContentBasedRecommenderSystem/src/ui/index_nlingrs_v2.html\" style=\"color:blue\">" + entry.getKey() + "</a></b>"+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&raquo&raquo&raquo&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<b>File name:</b>&nbsp" + "<mark>\"" + filename + "\"</mark>" + "<br><hr style=\"border-top: dashed 1px; color:gray\" />";
            rank++;
        }
        return result;
    }
    /**
     *
     * @param hotelId
     * @return hotel info
     */
    @RequestMapping("/GetHotelInfo")
    public String getHotelInfo(@RequestParam(value = "hotelId", defaultValue = "11111") String hotelID){
        String result = "";
        if(sentenceRS.hotelId_jsonInfo_map.get(hotelID) != null){
            result = sentenceRS.hotelId_jsonInfo_map.get(hotelID);
        }else{
            result = "Unknown Hotel ID";
        }
        return result;
    }
    
    /**
     *
     * @param repPath
     * @return acknowledgment of initialization.
     */
    @RequestMapping("/init")
    public String initialize(@RequestParam(value = "repPath", defaultValue = ".//src//main//resources//StatementRepository") String repPath){
        sentenceRS.initialize(repPath);
        return "init is done!";
    }
}
