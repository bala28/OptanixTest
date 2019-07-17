package com.optanix.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optanix.test.service.LetterCountService;
import com.optanix.test.service.WordCountService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Word Count API", description = "API to word count")
public class SampleController {

    @Autowired
    private WordCountService wordCountService;

    @Autowired
    private LetterCountService letterCountService;

    @ApiOperation(httpMethod = "POST", value = "Get Letter counts for Words", response = Map.class, notes = "Get Test",produces = MediaType.APPLICATION_JSON_VALUE,consumes= MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "OK", response = Map.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = String.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR") })
    @PostMapping("/total_letter_count")
    public ResponseEntity<Map<?,?>> getTotalLetterCount(@RequestBody(required=true) @Valid @ApiParam(value="inputData")  Map<String,String> inputData) {

        try {
            Map<Character,Integer> returnMap = letterCountService.getLetterCount(inputData);
            return  new ResponseEntity<>(returnMap,HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            return  new ResponseEntity<>(getErrorMap(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return  new ResponseEntity<>(getErrorMap(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(httpMethod = "POST", value = "Get Word Count for sentences", response = Map.class, notes = "Get Word Count for a sentence",produces = MediaType.APPLICATION_JSON_VALUE,consumes= MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "OK", response = Map.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = String.class),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR") })
    @PostMapping("/word_count_per_sentence")
    public ResponseEntity<Map<?,?>> getTotalWordCount(@RequestBody(required=true) @Valid @ApiParam(value="inputData")  Map<String,String> inputData) {

        try {
            Map<String,Integer> returnMap = wordCountService.getWordCount(inputData);
            return  new ResponseEntity<>(returnMap,HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            return  new ResponseEntity<>(getErrorMap(e.getMessage()),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return  new ResponseEntity<>(getErrorMap(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get Error Message Map
     * @param errorMessage
     * @return
     */
    private Map<String,String> getErrorMap(String errorMessage){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorDesc", errorMessage);
        return errorMap;
    }

}
