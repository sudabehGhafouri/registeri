/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.rs;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javafx.util.Pair; 

/**
 *
 * @author Hadi
 */
public class RecommenderEngine {
    public static void main(String[] args) {
        SentenceRecommenderSystem sentenceRS = new SentenceRecommenderSystem();
        String givenSentence = "Test query";
        
        int numResults = 10;
        sentenceRS.initialize(".//src//main//resources//StatementRepository");
        Pair<List <Entry<String, Double>>, HashMap<String, String>> sortedRecoms = sentenceRS.getSentenceRecom(givenSentence);
        int rank = 1;
        String result = "";
        HashMap<String, String> sentence_filename_map = sortedRecoms.getValue();
        for(Entry<String,Double> entry : sortedRecoms.getKey()){
            if(rank > numResults){
                break;
            }
            String filename = "unknown";
            if(sentence_filename_map.get(entry.getKey()) != null){
                filename = sentence_filename_map.get(entry.getKey());
            }
            result += rank + " " + entry.getKey() + "\t" + filename + "\n";
            rank++;
        }
        System.out.println(result);
    }
    
}
