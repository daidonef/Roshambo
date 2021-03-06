package com.daidone.roshambo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.StrongPasswordEncryptor;
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
		
		//For when user deletes their own account.
		if (request.getParameter("delete") != null) {
			
			Account account = (Account) session.getAttribute("account");
			Account deleteAcc = DAOAccount.deleteAccount(account.getID());
			
			//Deletes all the scores for all players the user faced.
			List<Scores> scores = DAOScores.getScores(Query.gettingScoresAccID(account.getID()));
			for (Scores score : scores) {
				DAOScores.deleteScores(score.getScoresID());
			}
			
			model.addAttribute("deleteAcc", deleteAcc.getUserName() 
					+ " account has been deleted!");
			
		}
		
		session.invalidate();

		return "login";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String signIn(Model model, HttpServletRequest request) {

		request.getSession(true).invalidate();

		return "createaccount";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profile(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		Account account = new Account();
		StringBuffer fullName = new StringBuffer();
		
		//If you come from login or create account
		if (session.getAttribute("account") == null) {
			
			if (request.getParameter("userName") == null) {
				return "home";
			}

			StringBuffer userName = new StringBuffer(request.getParameter("userName"));
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			
			//From login page
			if (request.getParameter("firstName") == null) { 

				// Getting the account from the database.
				List<Account> accounts = new ArrayList<Account>();
				accounts = DAOAccount.getAccount(Query.gettingAccountUN(
						userName));
				
				//User input wrong username and/or password
				if (accounts.size() == 0) {
					model.addAttribute("wrongLogin", "Username or password is wrong.<br>"
							+ "Please try again.");
					return "login";
				}
				
				account = accounts.get(0);
				
				//Check the encrypted password in the database to one user inputs.
				if (passwordEncryptor.checkPassword(request.getParameter("password"), 
						account.getPassword())) {
					fullName.append(account.getFirstName() + " " + account.getLastName());
				} else {
					model.addAttribute("wrongLogin", "Username or password is wrong.<br>"
							+ "Please try again.");
					return "login";
				}

			// From createaccount page
			} else { 

				StringBuffer firstName = new StringBuffer(request.getParameter("firstName"));
				StringBuffer lastName = new StringBuffer(request.getParameter("lastName"));
				fullName.append(firstName + " " + lastName);

				// used to see if username is already in the database.
				List<Account> accounts = new ArrayList<Account>();
				accounts = DAOAccount.getAccount(Query.gettingAccountUN(userName));

				if (accounts.size() == 1) {
					model.addAttribute("accountExist", "Username is already created.<br>"
							+ "Please choose another username.");
					return "createaccount";
				}

				// Adding the account to the Database.
				StringBuffer encryptedPassword = new StringBuffer(
						passwordEncryptor.encryptPassword(request.getParameter("password")));
				account = account.createAccount(request.getParameter("userName"), 
						firstName.toString(), lastName.toString(), encryptedPassword.toString());
				DAOAccount.addAccount(account);

				// get account just created in order to get the ID
				accounts = new ArrayList<Account>();
				account = DAOAccount.getAccount(Query.gettingAccountUN(userName)).get(0);

			}

			session.setAttribute("account", account);
			session.setAttribute("fullName", fullName);

		} else { // When user goes back to profile it will now show scores
			account = (Account) session.getAttribute("account");
		}
		
		//Listing the scores of the current user
		List<Scores> scores = DAOScores.getScores(Query.gettingScoresAccID(account.getID()));
		if (scores != null) {
			model.addAttribute("scores", scores);
		}
		
		//For updating an Account either by user or owner
		if (request.getParameter("accountID") != null) {
			Account updateAccount = DAOAccount.getAccount(Query.gettingAccountID(
					Integer.parseInt(request.getParameter("accountID")))).get(0);
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			
			updateAccount.setFirstName((String)request.getParameter("firstName"));
			updateAccount.setLastName((String)request.getParameter("lastName"));
			updateAccount.setPassword((String)passwordEncryptor.encryptPassword(
					request.getParameter("password")));
			
			DAOAccount.updateAccount(updateAccount);
			model.addAttribute("updateAccount", "<br>Account has been updated");
			
			//For when user updates their own account
			if (account.getID() == updateAccount.getID()) {
				session.setAttribute("account", updateAccount);
				fullName.append(updateAccount.getFirstName() + " " + updateAccount.getLastName());
				session.setAttribute("fullName", fullName);
			}
		}

		model.addAttribute("fullName", session.getAttribute("fullName"));
		model.addAttribute("userName", account.getUserName());
		
		//For the Owner Account only
		if (account.getUserName().equals("Admin")) {
			model.addAttribute("owner", Owner.formOwnerPage());
		}

		return "profile";
	}

	@RequestMapping(value = "/userchoice", method = RequestMethod.POST)
	public String userChoice(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("account") == null) {
			return "login";
		}

		String opponent;
		if (request.getParameter("opponent") != null) { // For new opponent
			opponent = request.getParameter("opponent");
		} else if (session.getAttribute("opponent") != null) { // For last opponent chosen
			opponent = (String) session.getAttribute("opponent");
		} else { // For no opponent chosen
			return "profile";
		}

		session.setAttribute("opponent", opponent);
		if (opponent.equals("rockPlayer")) {

			StringBuffer rockRPS = new StringBuffer("Rock");

			Rock rock = new Rock();
			rock.generateRoshambo(rockRPS);

			session.setAttribute("opponentChoice", rock.getRoshambo());
			
		} else if (opponent.equals("smartPlayer")) {
			
			StringBuffer smartRPS = new StringBuffer();
			
			//Inputs the last choice of human player
			if (session.getAttribute("humanRPS") != null) {
				smartRPS.append(session.getAttribute("humanRPS"));
			}
			
			SmartPlayer smartPlayer = new SmartPlayer();
			smartPlayer.generateRoshambo(smartRPS);
			session.setAttribute("opponentChoice", smartPlayer.getRoshambo());
			
		} else { //Opponent is the random player

			StringBuffer randomRPS = new StringBuffer("");

			RandomPlayer randomPlayer = new RandomPlayer();
			randomPlayer.generateRoshambo(randomRPS);

			session.setAttribute("opponentChoice", randomPlayer.getRoshambo());

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
		session.setAttribute("humanRPS", humanRPS);

		Roshambo hRPS = human.getRoshambo();
		Roshambo oRPS = (Roshambo) session.getAttribute("opponentChoice");
		
		//Gives the outcome of the Roshambo game
		StringBuffer outcome = new StringBuffer(GameMatch.gameOutcome(hRPS, oRPS));

		Account account = (Account) session.getAttribute("account");
		String opponent = (String) session.getAttribute("opponent");

		List<Scores> scores = DAOScores.getScores(Query.gettingScoresAccIDOpp(account.getID(), opponent));

		if (scores.size() == 0) { // If accountID and opponent is not in database

			Scores newScores = new Scores();

			newScores.setAccountID(account.getID());
			newScores.setOpponent(opponent);
			newScores = Scores.newWinLoseTie(newScores, outcome);
			DAOScores.addScores(newScores);

		} else { // If opponent is already in database

			Scores score = scores.get(0);
			score = Scores.addingWinLoseTie(score, outcome);
			DAOScores.updateScores(score);

		}

		model.addAttribute("humanRPS", hRPS);
		model.addAttribute("opponentRPS", oRPS);
		model.addAttribute("outcome", outcome);

		return "match";
	}

	@RequestMapping(value = "/ownerpage", method = RequestMethod.POST)
	public String ownerPage(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		Account owner = (Account) session.getAttribute("account");
		
		//Only the Admin or Owner can get to this page
		if (owner.getUserName().equals("Admin") == false) {
			return "login";
		}
		
		//For searching for accounts my either first or last name
		if (request.getParameter("name") != null) {
			StringBuffer name = new StringBuffer((String) request.getParameter("name"));
			List<Account> accounts = DAOAccount.getAccount(Query.searchName(name));
			model.addAttribute("accounts", accounts);
		}
		
		//For when owner deletes an account.
		if (request.getParameter("delete") != null) {
			int accountID = Integer.parseInt(request.getParameter("delete"));
			Account account = DAOAccount.deleteAccount(accountID);
			List<Scores> scores = DAOScores.getScores(Query.gettingScoresAccID(accountID));
			for (Scores score : scores) {
				DAOScores.deleteScores(score.getScoresID());
			}
			model.addAttribute("accountDelete", account.getUserName() + " has been deleted.");
		}

		return "ownerpage";
	}

	@RequestMapping(value = "/updateaccount", method = RequestMethod.POST)
	public String updateAccount(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);

		if (session.getAttribute("account") == null) {
			return "login";
		}
			
			Account account = new Account();
			
			if (request.getParameter("updateOwner") != null) {
				int accountID = Integer.parseInt(request.getParameter("updateOwner"));
				account = DAOAccount.getAccount(Query.gettingAccountID(accountID)).get(0);
			} else {
				account = (Account) session.getAttribute("account");
			}
			
			model.addAttribute("accountUpdate", account.getUserName() + "has been updated.");
			model.addAttribute("accountID", account.getID());
			model.addAttribute("firstName", account.getFirstName());
			model.addAttribute("lastName", account.getLastName());

		return "updateaccount";
	}

}
