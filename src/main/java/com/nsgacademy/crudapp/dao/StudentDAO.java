package com.nsgacademy.crudapp.dao;

import com.nsgacademy.crudapp.model.Pagination;
import com.nsgacademy.crudapp.model.Student;

import java.util.List;

public interface StudentDAO {
    void insert(Student student);
    void delete(int id);
    Student getStudentById(int id);
    void update(Student student);
    List<Student> getSelectedStudents(Pagination pagination);
    int countStudents();
}
