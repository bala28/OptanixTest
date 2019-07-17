package com.optanix.test.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


/**
 * Get Letter Counts from Words.
 * @author bala
 *
 */
@Component
public class LetterCountService {

    /**
     * 2.   total_letter_count - For each case-insensitive letter, return the number of times that letter appears in the entire text. E.g. the text contains 50 As, 40 Bs, etc.
     * @param word
     * @return
     */
    public Map<Character,Integer> getLetterCount(Map<String,String> inputData){
        
        Map<Character,Integer> returnMap = new HashMap<>();
        inputData.entrySet().forEach(keyset-> {            
            String word = keyset.getValue().toUpperCase();
            if(StringUtils.isBlank(word)) {
                throw new IllegalArgumentException("Word Cannot be empty");
            }
            
            if(word.contains(" ") || word.contains("?") || word.contains("!") || word.contains(".")) {
                throw new IllegalArgumentException("Letter has spaces,if space is available , then it is a sentence");
            }
            for(int i=0;i<word.length();i++) {
               Character ch = word.charAt(i);
               if(returnMap.containsKey(ch)) {
                   returnMap.put(ch,returnMap.get(ch)+1);
               }else {
                   returnMap.put(ch, 1);
               }
            }
            
        });
        return  returnMap;
    }
    
    
}
