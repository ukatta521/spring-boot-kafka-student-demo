package com.student.controllers;

import com.student.entities.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/getAllStudents" , method = RequestMethod.GET)
    public List<Student> getAllStudents(){

        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/addStudentDetails" , method = RequestMethod.GET )
    public boolean addStudentDetails(){
        return true;
    }

    @RequestMapping(value = "/getStudentById" , method = RequestMethod.GET)
    public Student getStudentById(int id){
        return studentService.getStudentById(id);
    }

    @RequestMapping(value = "/getStudentBySubject" , method = RequestMethod.GET)
    public List<Student> getStudentBySubject(String subject) {
        return studentService.getStudentBySubject(subject);
    }
}
