package com.daidone.roshambo;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		return "home";
	}
	
	@RequestMapping(value = "/userchoice", method = RequestMethod.POST)
	public String userChoice(Model model, HttpServletRequest request) {
		
		return "userchoice";
	}
	
	@RequestMapping(value = "/match", method = RequestMethod.POST)
	public String match(Model model, HttpServletRequest request) {
		
		
		String str1 = request.getParameter("humanPlayer");
		StringBuffer humanRPS = new StringBuffer();
		humanRPS.append(str1);
		
		
		Human human = new Human();
		
		human.generateRoshambo(humanRPS);
		
		Roshambo hRPS = human.getRoshambo();
		
		model.addAttribute("humanRPS", hRPS);
		
		return "match";
	}
	
}
