package com.optanix.test.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.optanix.test.service.LetterCountService;
import com.optanix.test.service.WordCountService;

@ExtendWith(MockitoExtension.class)
@Tag("junit")
@DisplayName("SampleControllerTest")
public class SampleControllerTest {

    @InjectMocks
    SampleController sampleController;

    @Mock
    private WordCountService wordCountService;

    @Mock
    private LetterCountService letterCountService;



    @Test
    @DisplayName("SampleControllerTest : Test Illegal Argument Exception word count")
    public void testWordCountIllegalArgumentException() {
        doThrow(IllegalArgumentException.class).when(wordCountService).getWordCount(Mockito.any());
        Map<String, String> inputData = new HashMap<>();
        inputData.put("Key", "This is test.");
        ResponseEntity<Map<?,?>> response = sampleController.getTotalWordCount(inputData);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    
    @Test
    @DisplayName("SampleControllerTest : Test Exception word count")
    public void testWordCountException() {
        doThrow(RuntimeException.class).when(wordCountService).getWordCount(Mockito.any());
        Map<String, String> inputData = new HashMap<>();
        inputData.put("Key", "This is test.");
        ResponseEntity<Map<?,?>> response = sampleController.getTotalWordCount(inputData);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
    @Test
    @DisplayName("SampleControllerTest : Test Illegal Argument Exception word count")
    public void testLetterCountIllegalArgumentException() {
        doThrow(IllegalArgumentException.class).when(letterCountService).getLetterCount(Mockito.any());
        Map<String, String> inputData = new HashMap<>();
        inputData.put("Key", "This is test.");
        ResponseEntity<Map<?,?>> response = sampleController.getTotalLetterCount(inputData);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    
    @Test
    @DisplayName("SampleControllerTest : Test Exception letter count")
    public void testLetterCountException() {
        doThrow(RuntimeException.class).when(letterCountService).getLetterCount(Mockito.any());
        Map<String, String> inputData = new HashMap<>();
        inputData.put("Key", "This is test.");
        ResponseEntity<Map<?,?>> response = sampleController.getTotalLetterCount(inputData);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
    @Test
    @DisplayName("SampleControllerTest : Test Word Count")
    public void testWordCountSuccess() {
        Map<String, Integer> outputMap = new HashMap<>();
        outputMap.put("This is test.", 3);
        outputMap.put(" This is another test", 4);
        when(wordCountService.getWordCount(Mockito.any())).thenReturn(outputMap);
        Map<String, String> inputData = new HashMap<>();
        inputData.put("Key", "This is test. This is another test");
        ResponseEntity<Map<?,?>> response = sampleController.getTotalWordCount(inputData);
        Assertions.assertEquals(3,response.getBody().get("This is test."));
        Assertions.assertEquals(4,response.getBody().get(" This is another test"));
    }
    
    @Test
    @DisplayName("SampleControllerTest : Test Letter Count")
    public void testLetterCountSuccess() {
        Map<Character, Integer> outputMap = new HashMap<>();
        outputMap.put('A', 2);
        outputMap.put('B', 3);
        when(letterCountService.getLetterCount(Mockito.any())).thenReturn(outputMap);
        Map<String, String> inputData = new HashMap<>();
        inputData.put("Key", "aabbb");
        ResponseEntity<Map<?,?>> response = sampleController.getTotalLetterCount(inputData);
        Assertions.assertEquals(2,response.getBody().get('A'));
        Assertions.assertEquals(3,response.getBody().get('B'));
    }
}
