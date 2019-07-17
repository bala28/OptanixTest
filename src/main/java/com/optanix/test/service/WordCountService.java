package com.optanix.test.service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Get Count of words for a sentence
 * @author bala
 *
 */
@Component
public class WordCountService {

    public static final char SEPERATOR_SPACE = ' ';
    
    List<Character> seperators = Arrays.asList('.','!','?');


    /**
     * 1.   word_count_per_sentence - Return individual sentences along with a word count for the sentence. For the purposes of this exercise, 
     * a sentence is any grouping of letters and words ending in a period, 
     * question mark or exclamation point. A word is text delimited by spaces. 
     */
    public Map<String, Integer> getWordCount(Map<String,String> inputData) {
        Map<String,Integer> resultMap = new LinkedHashMap<>();
       
        if(CollectionUtils.isEmpty(inputData)) {
            throw new IllegalArgumentException("Input Data is Empty");
        }

        inputData.entrySet().forEach(entrySet->{
            String sentence = entrySet.getValue();            
           /* if(sentence.contains(SEPERATOR_PERIOD)) {
                String[] splits = sentence.split("\\"+SEPERATOR_PERIOD);               
                for (int i = 0; i < splits.length; i++) {
                    String eachSentence = splits[i].trim();
                    if(eachSentence.contains(SEPERATOR_SPACE)) {
                        returnMap.put(eachSentence, eachSentence.split(SEPERATOR_SPACE).length);
                    }
                }
            }
*/            
            char[] allChars = sentence.toCharArray();
            int wordCount=1;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < allChars.length; i++) {
                boolean isDoubleSpace = false;
                Character ch = allChars[i];
                if(i+1 < allChars.length && allChars[i] == SEPERATOR_SPACE && allChars[i+1] == SEPERATOR_SPACE ) {
                        isDoubleSpace = true;
                }
                builder.append(ch);
                if(ch == SEPERATOR_SPACE && !isDoubleSpace) {
                    wordCount++;
                }else if(seperators.contains(ch)) {
                    resultMap.put(builder.toString(), wordCount);
                    wordCount = 0;
                    builder.setLength(0);
                    if(i+1 < allChars.length && allChars[i+1] != SEPERATOR_SPACE ) {
                        throw new IllegalArgumentException("Space should be followed after using '"+ch+"'");
                    }
                }   
                
            }
        });

        return resultMap;
    }
    
    

}
