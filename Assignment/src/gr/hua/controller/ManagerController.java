package gr.hua.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dao.PersonDAO;
import gr.hua.dao.RightDAO;
import gr.hua.dao.StudentDAO;
import gr.hua.entity.Person;
import gr.hua.entity.Right;
import gr.hua.entity.Student;



@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private RightDAO rightdao;
	
	@Autowired
	private PersonDAO persondao;

	@Autowired
	private StudentDAO studentdao;
	
	private int i;
	
	@RequestMapping("/")
	public String showMyPage() {
		return "manager";
	}
	
	@RequestMapping("/rights")
	public String rights(HttpServletRequest request, Model model) {
		ArrayList<Right> rights = rightdao.getRights();
		rights.toString();
		model.addAttribute("rights", rights);
		return "rights";
	}
	
	@RequestMapping("/rightSaved")
	public String rightSaved(HttpServletRequest request, Model model) {
		String role = request.getParameter("role");
		Boolean action = Boolean.parseBoolean(request.getParameter("action"));
		String service = request.getParameter("service");
		Right right= new Right(role, service,action);
		rightdao.saveRight(right);
		return "rightSaved";
	}
	
	public String dormAssignment () {
		List<Person> it = persondao.getDepartmentStudents("Informatics and Telematics");
		List<Person> geography = persondao.getDepartmentStudents("Geography");
		List<Person> homeEc = persondao.getDepartmentStudents("Home Economics");
		return null;
	}
	
	@RequestMapping("/updateSystem")
	public String update() {
		ArrayList<Student> allstudents = studentdao.getStudents();
		for(i=0; i<allstudents.size(); i++) {
			allstudents.get(i).setAccepted(false);
			allstudents.get(i).setApproval(false);
			allstudents.get(i).setReviewed(false);
			allstudents.get(i).setRanking(0);
			studentdao.saveStudent(allstudents.get(i));
		}
		return "updateSystem";
	}
	
}