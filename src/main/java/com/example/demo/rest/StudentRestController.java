package com.example.demo.rest;

import com.example.demo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    //define @PostConstruct to load the student data... only once!
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("John", "Paul"));
        theStudents.add(new Student("Mario", "Nail"));
        theStudents.add(new Student("Peter", "Pan"));
    }


    // define endpoint for "/students" -return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }
    // define endpoint or "/students/{studentId}" -return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudents(@PathVariable int studentId) {

        // check the studentId against list size
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFundException(("Student id: ") + studentId + (" not found"));
        }
        return theStudents.get(studentId);
    }
    //Add an exception handler using @ExceptionHandler

    @ExceptionHandler

    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFundException exception) {

        //create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        //return a ResponseEntity.
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    // add another exception handler... to catch any exception(catch all)

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exception) {

        //create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        //return a ResponseEntity.
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
