package com.student.controllers;

import com.student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class CacheController {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    CaffeineCacheManager caffeineCacheManager;

    @RequestMapping(value = "/getAllCaches" ,method =  RequestMethod.GET)
    public Collection<String> getAllCaches(){

        return caffeineCacheManager.getCacheNames();
    }

    @RequestMapping(value = "/getCacheEntries" , method =  RequestMethod.GET)
    public String getCacheEntries(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        return  cache.toString();
    }

    @RequestMapping(value = "/getEntryByKey" , method = RequestMethod.GET)
    public List<Student> getEntryByKey(String cacheName, String key){
        return (List<Student>) cacheManager.getCache(cacheName).get(key).get();
    }

    @RequestMapping(value = "/getCaffieneCaches", method = RequestMethod.GET)
    public Cache getCaffineCaches(String cacheName) {
        return caffeineCacheManager.getCache(cacheName);
    }


}
