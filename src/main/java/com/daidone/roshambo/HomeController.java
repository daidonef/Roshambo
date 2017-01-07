package com.daidone.roshambo;

import java.util.ArrayList;
import java.util.List;

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
		
		HttpSession session = request.getSession(true);
		session.invalidate();

		return "login";
	}
	
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String signIn(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		session.invalidate();

		return "createaccount";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profile(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("account") == null) {
			
			Account account = new Account();
			Scores scores = new Scores();
			StringBuffer fullName = new StringBuffer();
			StringBuffer userName = new StringBuffer(request.getParameter("userName"));
			
			if (request.getParameter("firstName") == null) { //From login page
				
				StringBuffer password = new StringBuffer(request.getParameter("password"));
				
				//Getting the account from the database.
				String query = "FROM Account WHERE (username = '" + userName + "') and (password "
						+ "= '" + password + "') ";
				List<Account> accounts = new ArrayList<Account>();
				accounts = DAOAccount.getAccount(query);
				
				if (accounts.size() == 0) {
					return "login";
				}
				
				account = accounts.get(0);
				fullName.append(account.getFirstName() + " " + account.getLastName());
				
			} else { //From createaccount page
				
				StringBuffer firstName = new StringBuffer(request.getParameter("firstName"));
				StringBuffer lastName = new StringBuffer(request.getParameter("lastName"));
				fullName.append(firstName + " " + lastName);
				
				//used to see if username is already in the database.
				String query = "FROM Account WHERE (username = '" + userName + "')";
				List<Account> accounts = new ArrayList<Account>();
				accounts = DAOAccount.getAccount(query);
				
				if (accounts.size() == 1) {
					return "createaccount";
				}
				
				//Adding the account to the Database.
				account = account.createAccount(request.getParameter("userName"), firstName.toString(), 
						lastName.toString(), request.getParameter("password"));
				DAOAccount.addAccount(account);
				
				//get account just created in order to get the ID
				query = "FROM Account WHERE (username = '" + request.getParameter("userName") + "')";
				accounts = new ArrayList<Account>();
				account = DAOAccount.getAccount(query).get(0);
				
			}

			session.setAttribute("account", account);
			session.setAttribute("fullName", fullName);
			
		}
		
		model.addAttribute("fullName", session.getAttribute("fullName"));

		return "profile";
	}

	@RequestMapping(value = "/userchoice", method = RequestMethod.POST)
	public String userChoice(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("account") == null) {
			return "login";
		}

		String opponent = request.getParameter("opponent");
		
		if (opponent == null) {
			return "profile";
		}
		
		session.setAttribute("opponent", opponent);
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
		
		if (session.getAttribute("account") == null) {
			return "login";
		}
		
		if (session.getAttribute("opponentChoice") == null) {
			return "profile";
		}

		StringBuffer humanRPS = new StringBuffer(request.getParameter("humanPlayer"));

		Human human = new Human();
		human.generateRoshambo(humanRPS);

		Roshambo hRPS = human.getRoshambo();
		Roshambo oRPS = (Roshambo) session.getAttribute("opponentChoice");

		StringBuffer outcome = new StringBuffer(GameMatch.gameOutcome(hRPS, oRPS));
		
		Account account = (Account) session.getAttribute("account");
		int accountID = account.getID();
		String opponent = (String) session.getAttribute("opponent");
		
		String query = "FROM Scores Where (accountid = '" + accountID + "') and ("
				+ "opponent = '" + opponent + "')";
		List<Scores> scores = DAOScores.getScores(query);
		
		if (scores.size() == 0) { //If accountID and opponent is not in database
			
			Scores newScores = new Scores();
			
			newScores.setAccountID(account.getID());
			newScores.setOpponent(opponent);
			newScores = Scores.newWinLoseTie(newScores, outcome);
			DAOScores.addScores(newScores);
			
		} else { //If it already is in database
			
			Scores score = scores.get(0);
			score = Scores.addingWinLoseTie(score, outcome);
			DAOScores.updateScores(score);
			
		}
		
		model.addAttribute("humanRPS", hRPS);
		model.addAttribute("opponentRPS", oRPS);
		model.addAttribute("outcome", outcome);

		return "match";
	}

}
