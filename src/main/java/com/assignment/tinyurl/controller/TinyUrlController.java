package com.assignment.tinyurl.controller;

import com.assignment.tinyurl.model.UrlModel;
import org.springframework.beans.factory.annotation.Value;
import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/tiny/url")
public class TinyUrlController {
	
	@Autowired
	private RedisTemplate<String, UrlModel> redisTemplate;
	
	//TTL defines the lifetime of an entry in Redis cache
	@Value("${redis.ttl}") // 86400 in the properties file
	private long ttl;

	/**
	 * Method redirect to LongUrl
	*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
    public void GetLongUrl(HttpServletResponse response,@PathVariable String id)  throws IOException {

		// get the URL Data model from Redis as per its key(tiny url)
        UrlModel urlObj = redisTemplate.opsForValue().get(id);

        // if the url is Null, throw exception with the message
        if (urlObj == null) {
            throw new RuntimeException("There is no shorter URL for : " + id);
        }
        
        // redirect the user to long Url
        response.sendRedirect(urlObj.getlongUrl());
    }
	

	/**
	 * Method to create TinyUrl
	*/
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
    public ResponseEntity CreateTinyUrl(@RequestBody @NotNull UrlModel url) {

		// using common's validator to validate the URL.(comm's validator library)
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );
        
        // if the url is not valid, return error
        if (!urlValidator.isValid(url.getlongUrl())) {
          Error error = new Error(url.getlongUrl());
          return ResponseEntity.badRequest().body(error);
        }
        
        // generating murmur based hash key as Tiny URL using Google Guava library.
        String id = Hashing.murmur3_32().hashString(url.getlongUrl(), StandardCharsets.UTF_8).toString();
        url.setId(id);
        url.setcreationTime(LocalDateTime.now());    
        System.out.println("ShortURL Id generated: "+ id);
        
        //store the Url Data model in redis with ttl
        redisTemplate.opsForValue().set(url.getId(), url, ttl, TimeUnit.SECONDS);
        
        return ResponseEntity.ok(url);
    }
	
}
