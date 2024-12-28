package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sms.dto.StudentDTO;
import com.sms.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

   @PostMapping("/addStudent")
   public ResponseEntity<?> addStu(@Valid @RequestBody StudentDTO studentDTO,BindingResult result ){
	   if(result.hasErrors()) {
		   return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
	   }
	   StudentDTO savedDTO = studentService.addStudent(studentDTO);
	   return new ResponseEntity<>(savedDTO,HttpStatus.CREATED);
   }

    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStu(@Valid @RequestBody StudentDTO studentDTO,BindingResult result,@RequestParam Long id )
    {
    	if(result.hasErrors()) 
    	{
    		return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
    	}
    	StudentDTO updatedDto =studentService.updateStudent(id, studentDTO);
    	return new ResponseEntity<>(updatedDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStu(@PathVariable Long id){
    	StudentDTO studentDTO =studentService.getStudentById(id);
    	return new ResponseEntity<>(studentDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(
    		@RequestParam(name="pageNo",required=false,defaultValue="0") int pageNo,
    		@RequestParam(name="pageSize",required=false,defaultValue="3")int pageSize,
    		@RequestParam(name="sortBy",required=false,defaultValue="name") String sortBy,
    		@RequestParam(name="sortDir",required=false,defaultValue="asc")String sortDir
    		) {
        List<StudentDTO> students = studentService.getAllStudents(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }
}

