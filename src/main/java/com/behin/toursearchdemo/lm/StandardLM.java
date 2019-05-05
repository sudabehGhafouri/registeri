/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.behin.toursearchdemo.lm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hadi
 */
public class StandardLM {
    	 private void generateStandardLanguageModel(Integer dId, String field) throws IOException {
	        HashMap<String, Double> tv;
	        tv = getDocTermFreqVector(dId, field);
			Long dLength = getDocumentLength(dId, field);
			for (Map.Entry<String, Double> e : tv.entrySet()) {
			    Double prob = e.getValue() / dLength;
			    tv.put(e.getKey(), prob);
			}
	    }

	private Long getDocumentLength(Integer dId, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	private HashMap<String, Double> getDocTermFreqVector(Integer dId,
			String field) {
		// TODO Auto-generated method stub
		return null;
	}

	public LanguageModel generateStandardLanguageModel(String content) {
		HashMap<String, Double> tv;
        tv = getContentTermFreqVector(content);
		Long dLength = getContentLength(content);
		for (Map.Entry<String, Double> e : tv.entrySet()) {
		    Double prob = e.getValue() / dLength;
		    tv.put(e.getKey(), prob);
		}
		LanguageModel lm = new LanguageModel(tv);
		return lm;
	}

	private Long getContentLength(String queryDescription) {
		String[] parts = queryDescription.split("\\s");
		return (long) parts.length;
	}

	private HashMap<String, Double> getContentTermFreqVector(
			String queryDescription) {
		HashMap<String, Double> tv = new HashMap<String, Double>();
		HashMap<String, Integer> tf = new HashMap<String, Integer>();
		String[] parts = queryDescription.split("\\s");
		for(int i = 0; i < parts.length; i++){
			if(tf.get(parts[i]) == null){
				tf.put(parts[i], 1);
			}else{
				tf.put(parts[i], tf.get(parts[i]) + 1);
			}
		}
		for(String term : tf.keySet()){
			Long freq = (long) tf.get(term);
			tv.put(term, freq.doubleValue());
		}
		return tv;
	}
}
