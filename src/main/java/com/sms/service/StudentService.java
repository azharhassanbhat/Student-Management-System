package com.sms.service;


import java.util.List;

import com.sms.dto.StudentDTO;

public interface StudentService {
    StudentDTO addStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    void  deleteStudentById(Long id);
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir);
}

