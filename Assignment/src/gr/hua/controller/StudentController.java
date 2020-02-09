package gr.hua.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dao.StudentDAO;
import gr.hua.entity.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentDAO studentdao;
	
	//private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	@RequestMapping("/")
	public String showMyPage() {
		return "student";
	}
	
	@RequestMapping("/form")
	public String showForm() {
	        return "form";
	}
	
	@RequestMapping("/processForm")
	public String processForm(HttpServletRequest request, Model model) {
			String email = request.getParameter("email");
			int id = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
			int yearsOfStudies = Integer.parseInt(request.getParameter("studies"));
			int yearsInAccomodation = Integer.parseInt(request.getParameter("accomodation"));
			int income = Integer.parseInt(request.getParameter("income"));
			int siblings = Integer.parseInt(request.getParameter("siblings"));
			boolean parents = Boolean.parseBoolean(request.getParameter("parents"));
			boolean city = Boolean.parseBoolean(request.getParameter("city"));
			
			Student student = new Student (id, email, city, yearsOfStudies, yearsInAccomodation, siblings, 
					parents, income, false, false);
			if (studentdao.getStudentById(id) != null) {
				studentdao.updateStudent(student);
			} else {
				studentdao.saveStudent(student);
			}
			
	        return "processForm";
	}
	
	@RequestMapping("/contactInfo")
	public String updateContactInfo() {
		return "contactInfo";
	}
	
	@RequestMapping("/updatedInfo")
	public String updatedInfo(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		Student student = new Student();
		student = studentdao.getStudentById(1);
		student.setEmail(email);
		studentdao.updateStudent(student);
		
		student.toString();
		model.addAttribute("student", student);
		return "updatedInfo";
	}
	
	@RequestMapping("/showInfo")
	public String showInfo(HttpServletRequest request, Model model) {
		Student student = new Student();
		student = studentdao.getStudentById(1);
		student.toString();
		model.addAttribute("student", student);
		return "showInfo";
	}
	
}
