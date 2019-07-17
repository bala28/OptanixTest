package com.optanix.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
@DisplayName("WordCountServiceTest")
public class WordCountServiceTest {

    @InjectMocks
    private WordCountService wordCountService;

    private Map<String,String> inputData;

    Map<String,Integer> actual = new LinkedHashMap<>();

    @BeforeEach
    public void setup() {
        inputData = new HashMap<>();
        inputData.put("sentences", "This is a test. This is another test.");
    }

    @Test
    @DisplayName("WordCountServiceTest : Success word count")
    public void testSuccessfullForWordCount() {
        Map<String,Integer> map = wordCountService.getWordCount(inputData);
        assertTrue(map.containsKey("This is a test."));
        assertTrue(map.containsKey(" This is another test."));
        assertEquals(map.get("This is a test.").intValue(), 4);
        assertEquals(4,map.get(" This is another test.").intValue());
    }
    
    @Test
    @DisplayName("WordCountServiceTest : Test Empty Data")
    public void testEmptyData() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->wordCountService.getWordCount(new HashMap<>()));
    }
    
    
    @Test
    @DisplayName("WordCountServiceTest : Test For Null Map")
    public void testNullMap() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->wordCountService.getWordCount(null));
    }
}
