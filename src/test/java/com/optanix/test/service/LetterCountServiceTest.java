package com.optanix.test.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("junit")
@DisplayName("LetterCountServiceTest")
public class LetterCountServiceTest {

    @InjectMocks
    private LetterCountService letterService;
    
    private Map<String,String> inputData;
    
    @BeforeEach
    public void setup() {
        inputData = new HashMap<>();
       
    }
    
    
    
    @Test
    @DisplayName("Test Null Word Get Illegal Argument Exception")
    public void testNullWord() {
        inputData.put("key", null);
        Assertions.assertThrows(IllegalArgumentException.class, ()->letterService.getLetterCount(inputData));
    }
    
    @Test
    @DisplayName("Test Empty Word Get Illegal Argument Exception")
    public void testEmpty() {
        inputData.put("key", "");
        Assertions.assertThrows(IllegalArgumentException.class, ()->letterService.getLetterCount(inputData));
    }
    
    @Test
    @DisplayName("Test Empty Space Get Illegal Argument Exception")
    public void testSpace() {
        inputData.put("key", " ");
        Assertions.assertThrows(IllegalArgumentException.class, ()->letterService.getLetterCount(inputData));
    }
    
    @Test
    @DisplayName("Test Question Mark Get Illegal Argument Exception")
    public void testQuestionMark() {
        inputData.put("key", "?");
        Assertions.assertThrows(IllegalArgumentException.class, ()->letterService.getLetterCount(inputData));
    }
    
    @Test
    @DisplayName("Test Exclamation Mark Get Illegal Argument Exception")
    public void testExclamationMark() {
        inputData.put("key", "!");
        Assertions.assertThrows(IllegalArgumentException.class, ()->letterService.getLetterCount(inputData));
    }
    
    
    @Test
    @DisplayName("Test Seperator Mark Get Illegal Argument Exception")
    public void testSeperatorMark() {
        inputData.put("key", ".");
        Assertions.assertThrows(IllegalArgumentException.class, ()->letterService.getLetterCount(inputData));
    }
    
    @Test
    @DisplayName("Test Successul counting of letter")
    public void testSuccess() {
        inputData.put("key", "aassddel");
      //  {a=2, s=2, d=2, e=1, l=1}
       letterService.getLetterCount(inputData);
    }
}
