package com.example.studentms.service;

import com.example.studentms.model.Student;
import com.example.studentms.repo.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    public Student save(Student student) {
        return repo.save(student);
    }

    public void delete(Student student) {
        repo.delete(student);
    }
}
