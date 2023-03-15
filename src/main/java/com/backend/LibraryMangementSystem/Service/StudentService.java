package com.backend.LibraryMangementSystem.Service;

import com.backend.LibraryMangementSystem.DTO.StudentRequestDTO;
import com.backend.LibraryMangementSystem.DTO.StudentResponseDTO;
import com.backend.LibraryMangementSystem.DTO.StudentUpdateEmailNoDTO;
import com.backend.LibraryMangementSystem.Entity.LibraryCard;
import com.backend.LibraryMangementSystem.Entity.Student;
import com.backend.LibraryMangementSystem.Enum.CardStatus;
import com.backend.LibraryMangementSystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {

        //pass the attributes of Dto to Student object
        Student student = new Student();

        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setEmail(studentRequestDTO.getEmail());
        student.setDepartment(studentRequestDTO.getDepartment());

        //set the value of card
        LibraryCard card = new LibraryCard();
        card.setStatus(CardStatus.ACTIVE);
        card.setStudent(student);

        //set card in student
        student.setCard(card);

        //save the student into database
        studentRepository.save(student);

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setName(student.getName());

        return studentResponseDTO;
    }

    public Student getStudentByMail(String email) {
        return studentRepository.findByEmail(email);
    }

    public StudentResponseDTO updateEmailNo(StudentUpdateEmailNoDTO studentUpdateEmailNoDTO) {

        Student student = studentRepository.findById(studentUpdateEmailNoDTO.getId()).get();
        student.setEmail(studentUpdateEmailNoDTO.getEmail());

        //update the student
        Student updatedStudent = studentRepository.save(student);

        //set the response DTO
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(updatedStudent.getId());
        studentResponseDTO.setName(updatedStudent.getName());
        studentResponseDTO.setEmail(updatedStudent.getEmail());

        return studentResponseDTO;
    }

    public List<String> studentsOfAge(int age) {
        List<Student> students = studentRepository.findByAge(age);
        List<String> names = new ArrayList<>();
        for(Student student:students){
            names.add(student.getName());
        }
        return names;
    }
}
