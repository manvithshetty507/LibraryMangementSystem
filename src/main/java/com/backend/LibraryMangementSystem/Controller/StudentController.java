package com.backend.LibraryMangementSystem.Controller;

import com.backend.LibraryMangementSystem.DTO.StudentRequestDTO;
import com.backend.LibraryMangementSystem.DTO.StudentResponseDTO;
import com.backend.LibraryMangementSystem.DTO.StudentUpdateEmailNoDTO;
import com.backend.LibraryMangementSystem.Entity.Student;
import com.backend.LibraryMangementSystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody StudentRequestDTO studentRequestDTO){
        StudentResponseDTO studentResponseDTO = studentService.addStudent(studentRequestDTO);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/findByEmail")
    public String getStudentByMail(@RequestParam("email") String email){
        Student student =  studentService.getStudentByMail(email);
        return student.getName();
    }

    @GetMapping("/ofAge")
    public ResponseEntity<List<String>> studentsOfAge(@RequestParam("age") int age){
        List<String> students = studentService.studentsOfAge(age);
        return new ResponseEntity<>(students,HttpStatus.CREATED);
    }

    @PutMapping("/updateEmail")
    public ResponseEntity<String> updateEmailNo(@RequestBody StudentUpdateEmailNoDTO studentUpdateEmailNoDTO){
        StudentResponseDTO studentResponseDTO = studentService.updateEmailNo(studentUpdateEmailNoDTO);
        return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
    }
}
