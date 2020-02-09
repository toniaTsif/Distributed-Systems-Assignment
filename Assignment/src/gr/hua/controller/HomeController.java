package gr.hua.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/login")
	public String showMyPage() {
		return "login";
	}
		
	@RequestMapping("/")
	public String home() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "mainPage";
	}
	
}
