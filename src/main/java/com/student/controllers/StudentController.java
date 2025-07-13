package com.student.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

    @RequestMapping(value = "/getStudentDetails" , method = RequestMethod.GET)
    public String getStudetnDetails(){
        return "Name  : umapathy";
    }

    @RequestMapping(value = "/addStudentDetails" , method = RequestMethod.GET )
    public boolean addStudentDetails(){
        return true;
    }
}
