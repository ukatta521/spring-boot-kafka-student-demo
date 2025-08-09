package com.student.service;

import com.student.Repository.StudentRepository;
import com.student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Cacheable(cacheNames = "studentCache" , key = "#id") // Specify the cache name
    public Student getStudentById(int id) {
        System.out.println((new Timestamp(System.currentTimeMillis())) + ":::  Student is fetched from db.");
        Optional<Student> student = studentRepository.findById(id);
        return student.get();
    }

    @Cacheable(cacheNames = "subjectCache" , key = "#subject") // Specify the cache name
    public List<Student> getStudentBySubject(String subject) {
        System.out.println((new Timestamp(System.currentTimeMillis())) + ":::  Student details are fetched from db.");
        List<Student> students = studentRepository.findBySubject(subject);
        return students;
    }


    @CachePut("tradeCache") // Specify the cache name
    public String updateCachedData(String key, String newData) {
        return newData;
    }

    @CacheEvict(cacheNames = "studentCache" , key="#id")
    public void removeTradeById(String id){
        System.out.println("Trade is removed from cache.");
    }



    @Cacheable(cacheNames = "studentCache")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
