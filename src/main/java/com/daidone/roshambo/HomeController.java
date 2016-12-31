package com.daidone.roshambo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) {

		return "login";
	}
	
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String signIn(Model model, HttpServletRequest request) {

		return "createaccount";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profile(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("fullName") == null) {

			StringBuffer firstName = new StringBuffer(request.getParameter("firstName"));
			StringBuffer lastName = new StringBuffer(request.getParameter("lastName"));
			StringBuffer fullName = new StringBuffer(firstName + " " + lastName);

			session.setAttribute("fullName", fullName);
			
		}
		
		model.addAttribute("fullName", session.getAttribute("fullName"));

		return "profile";
	}

	@RequestMapping(value = "/userchoice", method = RequestMethod.POST)
	public String userChoice(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("fullName") == null) {
			return "login";
		}

		String opponent = request.getParameter("opponent");
		if (opponent.equals("rockPlayer")) {

			StringBuffer rockRPS = new StringBuffer("Rock");

			Rock rock = new Rock();
			rock.generateRoshambo(rockRPS);
			Roshambo rRPS = rock.getRoshambo();

			session.setAttribute("opponentChoice", rRPS);

		} else {

			StringBuffer randomRPS = new StringBuffer("");

			RandomPlayer randomPlayer = new RandomPlayer();
			randomPlayer.generateRoshambo(randomRPS);
			Roshambo rRPS = randomPlayer.getRoshambo();

			session.setAttribute("opponentChoice", rRPS);

		}

		return "userchoice";
	}

	@RequestMapping(value = "/match", method = RequestMethod.POST)
	public String match(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("fullName") == null) {
			return "login";
		}
		
		if (session.getAttribute("opponentChoice") == null) {
			return "profile";
		}

		String humanPlayer = request.getParameter("humanPlayer");
		StringBuffer humanRPS = new StringBuffer(humanPlayer);

		Human human = new Human();
		human.generateRoshambo(humanRPS);

		Roshambo hRPS = human.getRoshambo();
		Roshambo oRPS = (Roshambo) session.getAttribute("opponentChoice");

		StringBuffer outcome = new StringBuffer(GameMatch.gameOutcome(hRPS, oRPS));

		model.addAttribute("humanRPS", hRPS);
		model.addAttribute("opponentRPS", oRPS);
		model.addAttribute("outcome", outcome);

		return "match";
	}

}
