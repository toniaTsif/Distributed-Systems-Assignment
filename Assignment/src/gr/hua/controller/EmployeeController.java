package gr.hua.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dao.PersonDAO;
import gr.hua.dao.StudentDAO;
import gr.hua.entity.Person;
import gr.hua.entity.Right;
import gr.hua.entity.Student;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private StudentDAO studentdao;
	
	@Autowired
	private PersonDAO persondao;

	@RequestMapping("/")
	public String showMyPage() {
		return "employee";
	}
	
	@RequestMapping("/manageInfo")
	public String manageInfo() {
		return "manageInfo";
	}

	@RequestMapping("/saveStudent")
	public String saveStudent() {
		return "saveStudent";
	}
	
	@RequestMapping("/studentSaved")
	public String studentSaved(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		String department = request.getParameter("department");
		String role = "Student";
		Person person= new Person(name, role, password, id, department);
		persondao.savePerson(person);
		return "studentSaved";
	}
	
	@RequestMapping("/deleteStudent")
	public String deleteStudent() {
		return "deleteStudent";
	}
	
	@RequestMapping("/studentDeleted")
	public String studentDeleted(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = studentdao.getStudentById(id);
		studentdao.deleteStudent(student);
		model.addAttribute("id", id);
		return "studentDeleted";
	}
	
	@RequestMapping("/petition")
	public String petition(HttpServletRequest request, Model model) {
		Person employee = persondao
				.getPersonById(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()));
		
		Student student = studentdao.getPetition(employee.getDepartment());
		if (student == null) {
			return "noPetitions";
		}
		model.addAttribute("student", student);
		model.addAttribute("id", student.getStudent_id());
		return"petition";
	}
	
	@RequestMapping("/approveDisapprovePetition")
	public String approveDisapprovePetition(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("Id"));
		Student student = studentdao.getStudentById(id);
		student.setReviewed(true);
		Boolean approval = Boolean.parseBoolean(request.getParameter("approval"));
		if(approval==true) {
			student.generateRank();
		}
		studentdao.updateStudent(student);
		return("approveDisapprovePetition");
	}
	
	
	@RequestMapping("/allStudentsInfo")
	public String allStudentsInfo(HttpServletRequest request, Model model) {
		ArrayList<Student> students = studentdao.getStudents();
		model.addAttribute("students", students);
		return "allStudentsInfo";
	}
}
