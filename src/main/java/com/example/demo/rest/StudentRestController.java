package com.example.demo.rest;

import com.example.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    //define @PostConstruct to laod the student data... only once!
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("John", "Paul"));
        theStudents.add(new Student("Mario", "Nail"));
        theStudents.add(new Student("Peter", "Pan"));
    }


    // define endpoint for "/studnets" -return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

}
