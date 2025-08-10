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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

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
    public Map<Object, Object> getCacheEntries(String cacheName) {
        Map<Object, Object> result = new HashMap<>();
        com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = getNativeCache(cacheName);
        if (nativeCache != null) {
            ConcurrentMap<Object, Object> entries = nativeCache.asMap();
            // Copy all entries to our result map
            result.putAll(entries);
        }

        return result;
    }

    @RequestMapping(value = "/getEntryByKey" , method = RequestMethod.GET)
    public List<Student> getEntryByKey(String cacheName, String key){
        return (List<Student>) cacheManager.getCache(cacheName).get(key).get();
    }

    @RequestMapping(value = "/getCacheSizes", method = RequestMethod.GET)
    public Map<String, Integer> getCacheSizes() {
        Map<String, Integer> cacheSizes = new HashMap<>();
        Collection<String> cacheNames = getAllCaches();

        for (String cacheName : cacheNames) {
            cacheSizes.put(cacheName, 0);
            com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = getNativeCache(cacheName);
            if (nativeCache != null) {
                ConcurrentMap<Object, Object> entries = nativeCache.asMap();
                cacheSizes.put(cacheName, entries.size());
            }
        }

        return cacheSizes;
    }

    private com.github.benmanes.caffeine.cache.Cache<Object, Object> getNativeCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null && cache.getNativeCache() instanceof com.github.benmanes.caffeine.cache.Cache) {
            return (com.github.benmanes.caffeine.cache.Cache<Object, Object>) cache.getNativeCache();
        }

        return null;
    }

}
