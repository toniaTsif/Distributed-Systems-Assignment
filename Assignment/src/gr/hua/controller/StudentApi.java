package gr.hua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dao.StudentDAO;
import gr.hua.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentApi {
	
	@Autowired
	private StudentDAO studentdao;
	
	@GetMapping("/petition/{id}")
	public Student getPetition (@PathVariable int id) {
		Student student = studentdao.getStudentById(id);
		return student;
	}
	
	@PostMapping("/savePetition")
	public void saveStudent (@RequestBody Student student) {
		studentdao.saveStudent(student);
	}

	@PutMapping("/updateInfo")
	public void updateContactInfo(@RequestBody Student student) {
		studentdao.updateStudent(student);
	}
	
	@GetMapping("/email/{id}")
	public String getEmail (@PathVariable int id) {
		String email = studentdao.getStudentById(id).getEmail();
		return email;
	}
}
