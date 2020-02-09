package gr.hua.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Request;

import gr.hua.entity.Person;
import gr.hua.entity.Student;
import gr.hua.request.*;

@Controller
@RequestMapping("/api")
public class StudentController {
	
	private ObjectMapper mapper;
	private OkHttpClient client;

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/")
	public String showMyPage() {
		return "student";
	}

	@RequestMapping("/form")
	public String showForm() {
		return "form";
	}

	@RequestMapping("/processForm")
	public String processForm(HttpServletRequest request, Model model) throws IOException {
		String email = request.getParameter("email");
		int id = 2;
		int yearsOfStudies = Integer.parseInt(request.getParameter("studies"));
		int yearsInAccomodation = Integer.parseInt(request.getParameter("accomodation"));
		int income = Integer.parseInt(request.getParameter("income"));
		int siblings = Integer.parseInt(request.getParameter("siblings"));
		boolean parents = Boolean.parseBoolean(request.getParameter("parents"));
		boolean city = Boolean.parseBoolean(request.getParameter("city"));

		Student student = new Student(id, email, city, yearsOfStudies, yearsInAccomodation, siblings, parents, income,
				false, false);

		ServerCommunication.postStudent(student);

		return "processForm";
	}

	@RequestMapping("/contactInfo")
	public String updateContactInfo(ModelMap model) throws IOException {
		Student student = ServerCommunication.getStudent(2);
		String email = student.getEmail();
		model.addAttribute("email", email);
		model.put("student", student);
		return "contactInfo";
	}

	@RequestMapping("/updatedInfo")
	public String updatedInfo(HttpServletRequest request, Model model) throws IOException {
		String email = request.getParameter("email");
		Student student = (Student) request.getAttribute("student");
		ServerCommunication.putStudent(student);
		student.toString();
		model.addAttribute("student", student);
		return "updatedInfo";
	}

	@RequestMapping("/showInfo")
	public String showInfo(HttpServletRequest request, Model model) throws IOException {
		Student student = ServerCommunication.getStudent(2);
		model.addAttribute("ranking", student.getRanking());
		model.addAttribute("accepted", student.isAccepted());
		return "showInfo";
	}
	
	@RequestMapping("/yee")
	public String yee() throws IOException {
		List<Person> students = ServerCommunication.getStudents();
		for (Person person : students) {
			System.out.println(person.getName());
		}
		return "yee";
	}
	
	@RequestMapping("/login")
	public String logIn() {
		return "login";
	}
}
 