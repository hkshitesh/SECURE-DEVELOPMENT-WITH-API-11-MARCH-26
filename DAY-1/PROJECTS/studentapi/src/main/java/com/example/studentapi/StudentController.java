package com.example.studentapi;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>();

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody Student student) {
        students.add(student);
        return "Student added successfully";
    }
}
