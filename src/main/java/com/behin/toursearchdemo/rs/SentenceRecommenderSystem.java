/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.rs;

import com.behin.toursearchdemo.lm.LanguageModel;
import com.behin.toursearchdemo.lm.StandardLM;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Comparator;
import javafx.util.Pair;

/**
 *
 * @author Hadi
 */
public class SentenceRecommenderSystem {
    private static HashMap<String, Integer> sentence_countAppearance_map = new HashMap<>();
    private static int embeddingsDim = 100;
    private static String wordEmbeddingPath = ".//src//main//resources//glove.6B.100d.txt";
    private static HashMap<String, double[]> word_vector_map = new HashMap<>();
    public static HashMap<String, String> hotelId_jsonInfo_map = new HashMap<>();
    private static HashMap<String, double[]> sentence_vector_map = new HashMap<>();
    private static HashMap<String, String> sentence_filename_map = new HashMap<>();
    public Pair<List <Entry<String, Double>>, HashMap<String, String>> getSentenceRecom(String givenSentence) {
        StandardLM slm = new StandardLM();
        LanguageModel givenSentenceLM = slm.generateStandardLanguageModel(givenSentence);
        double[] givenSentence_vec = getSentenceEmbeddings(givenSentenceLM);
        HashMap<String, Double> sentence_score_map = new HashMap<>();
        for(String sentenceCandidate : sentence_vector_map.keySet()){
            sentence_score_map.put(sentenceCandidate, cosineSimilarity(givenSentence_vec, sentence_vector_map.get(sentenceCandidate)));
        }
        List<Map.Entry<String, Double>> sortedSentences = sortMap(sentence_score_map);
        Pair pair = new Pair(sortedSentences,sentence_filename_map);
        return pair;
     }

    public void initialize(String sentenceRepository) {
        loadSentences(sentenceRepository);
        loadWordEmbeddings();
        loadSentenceEmbeddings();
        loadHotelInfo();
    }

    public static List<Entry<String,Double>> sortMap(
			HashMap<String, Double> docID_finalScore_map) {
	Set<Entry<String, Double>> set = docID_finalScore_map.entrySet();
        List<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Double>>()
        {
        public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 ){
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
    	return list;
	}
	
    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
	double dotProduct = 0.0;
	double normA = 0.0;
	double normB = 0.0;
	for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
	    normA += Math.pow(vectorA[i], 2);
	    normB += Math.pow(vectorB[i], 2);
	}   
	return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
	
    public static double[] sumDoubleArray(double[] arr1, double[] arr2) {
	double[] arrResult = new double[arr2.length];
	for (int i = 0; i < arr2.length; ++i) {
            arrResult[i] = arr1[i] + arr2[i];
            }
        return arrResult;
	}
	
    static double[] subtractDoubleArray(double[] arr1, double[] arr2) {
	double[] arrResult = new double[arr2.length];
	for (int i = 0; i < arr2.length; ++i) {
            arrResult[i] = arr1[i] - arr2[i];
	}
	return arrResult;
	}
	
    public static double[] multiplyWeightToDoubleArray(double weight, double[] arr) {
        double[] arrResult = new double[arr.length];
	for (int i = 0; i < arr.length; ++i) {
            arrResult[i] = weight * arr[i];
            }
	return arrResult;
	}
    
    private void loadSentences(String sentenceRepository) {
        File repositoryFolder = new File(sentenceRepository);
        for(File sentencesFile : repositoryFolder.listFiles()){
            String line = "";
            try {
                BufferedReader br = new BufferedReader(new FileReader(sentencesFile));
                while((line = br.readLine()) != null){
                    String[] parts = line.split("\t");
                    String sentence = parts[0];
                    int isSentence = Integer.parseInt(parts[1]);
                    int isRelevant = Integer.parseInt(parts[2]);
                    if(isRelevant == 1 && isSentence == 1){
                        if(!sentence_countAppearance_map.keySet().contains(sentence)){
                            sentence_countAppearance_map.put(sentence, 1);
                        }else{
                            sentence_countAppearance_map.put(sentence, sentence_countAppearance_map.get(sentence) + 1);
                        }
                        sentence_filename_map.put(sentence, sentencesFile.getName().replaceAll(".pdf", ""));
                    }
                }
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(SentenceRecommenderSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadWordEmbeddings() {
        String line = "";
	try {
            BufferedReader br = new BufferedReader(new FileReader(wordEmbeddingPath));
            while((line = br.readLine()) != null){
                double[] vec = new double[embeddingsDim];
		String[] parts = line.split("\\s");
		String word = parts[0];
		for(int i = 1; i <= embeddingsDim; i++){
                    double vi = Double.parseDouble(parts[i]);
                    vec[i-1] = vi;
		}
		word_vector_map.put(word, vec);
		}
            br.close();
            } catch (IOException ex) {
                Logger.getLogger(SentenceRecommenderSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void loadSentenceEmbeddings() {
        for(String sentence : sentence_countAppearance_map.keySet()){
            StandardLM slm = new StandardLM();
            LanguageModel sentenceLM = slm.generateStandardLanguageModel(sentence);
            double[] sentence_vec = getSentenceEmbeddings(sentenceLM);
            sentence_vector_map.put(sentence, sentence_vec);
        }   
    }

    private double[] getSentenceEmbeddings(LanguageModel lm) {
        double[] sentence_vec = new double[embeddingsDim];
        for(String term : lm.getModel().keySet()){
            if(word_vector_map.get(term) != null){
                sentence_vec = sumDoubleArray(sentence_vec, multiplyWeightToDoubleArray(lm.getModel().get(term), word_vector_map.get(term)));
            }
        }
        return sentence_vec;
    }

    private void loadHotelInfo() {
        hotelId_jsonInfo_map.put("11111", "{\"hotel_name\":\"Esteghlal\", \"hotel_star\":\"5\"}");
        hotelId_jsonInfo_map.put("11112", "{\"hotel_name\":\"Hotel2\", \"hotel_star\":\"2\"}");
        hotelId_jsonInfo_map.put("11113", "{\"hotel_name\":\"Hotel3\", \"hotel_star\":\"5\"}");
        hotelId_jsonInfo_map.put("11114", "{\"hotel_name\":\"Hotel4\", \"hotel_star\":\"5\"}");
    }
    
}
