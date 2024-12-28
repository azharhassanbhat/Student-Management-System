package com.sms.service.impl;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sms.dto.StudentDTO;
import com.sms.entity.Student;
import com.sms.exception.StudentNotFoundException;
import com.sms.repository.StudentRepository;
import com.sms.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = mapToStudent(studentDTO);
        Student stu = studentRepository.save(student);
        StudentDTO savedDTO =mapToStudentDTO(stu);
        return savedDTO;
    }

   @Override
   public StudentDTO updateStudent(Long id,StudentDTO studentDTO) {
	   Student studentExist = studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Student Not Found"));
	   Student student = mapToStudent(studentDTO);
	   student.setId(id);
	   Student stu = studentRepository.save(student);
	   StudentDTO updatedDto = mapToStudentDTO(stu);
	   return updatedDto;
   }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student =studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException("Student Not Found"));
        StudentDTO studentDTO =mapToStudentDTO(student);
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAllStudents(int pageNo, int pageSize, String sortBy, String sortDir) {
    	 Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();	
    	 PageRequest p = PageRequest.of(pageNo, pageSize,sort);
         Page<Student> pages = studentRepository.findAll(p);
         List<Student> students = pages.getContent();
         List<StudentDTO> studentsDto = students.stream().map(e->mapToStudentDTO(e)).collect(Collectors.toList());
         return studentsDto;
                
    }
    Student mapToStudent(StudentDTO dto) {
    	Student student =modelMapper.map(dto, Student.class);
    	return student;
    }
    StudentDTO mapToStudentDTO(Student student) {
    	StudentDTO studentDTO =modelMapper.map(student, StudentDTO.class);
    	return studentDTO;
    }
}

