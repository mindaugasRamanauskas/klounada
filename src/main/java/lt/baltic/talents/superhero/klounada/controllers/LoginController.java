package lt.baltic.talents.superhero.klounada.controllers;

import lt.baltic.talents.superhero.klounada.daos.Errors;
import lt.baltic.talents.superhero.klounada.models.LocalUser;
import lt.baltic.talents.superhero.klounada.models.User;
import lt.baltic.talents.superhero.klounada.services.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Scope("session")
@Controller
public class LoginController {
	
	@Autowired
    private UserService userService;

	@Autowired
	private LocalUser localUser;

	@RequestMapping(method = RequestMethod.GET, path = "/login")
	public String showLogin(Model model) {
		return initLoginOrRegister(model, Action.LOGIN);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/register")
	public String showRegister(Model model) {
		return initLoginOrRegister(model, Action.REGISTER);
	}

	private String initLoginOrRegister(Model model, Action action) {
		model.addAttribute("user", new ModelUser());
		model.addAttribute("action", action);

		return "login/login";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public String login(@Valid @ModelAttribute("user") ModelUser inputUser, BindingResult result) {
		
		if (result.hasErrors()) {
			return "login/failure"; 
		}
		
		User user = new User(inputUser.getName(), inputUser.getPwd().toCharArray());
		boolean login = userService.login(user);

			localUser.setName(user.getLogin());
			if (login) {
			localUser.setLogged(true);

			return "redirect:/";
		}
		
		return "login/failure";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/register")
	public String register(Model model, @Valid @ModelAttribute("user") ModelUser inputUser, BindingResult result) {

		if (result.hasErrors()) {
			return "register/failure";
		}

		User user = new User(inputUser.getName(), inputUser.getPwd().toCharArray());

		try {
			boolean registered = userService.create(user);

			if (registered) {
				localUser.setName(user.getLogin());
				localUser.setLogged(true);

				return "redirect:/";
			}
		} catch (Errors errors) {
			model.addAttribute("message", errors.getMessage());
		}

		return "register/failure";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/logout")
	public String logout(HttpSession session, SessionStatus status) {
		status.setComplete();
		session.invalidate();

		while (! status.isComplete()) {
			// wait for it to complete.
		}

		return "redirect:/";
	}

	private class ModelUser {
		@NotEmpty
		private String name;

		@NotEmpty
		private String pwd;

		public ModelUser() {}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
	}

	public enum Action {
		LOGIN("login"), REGISTER("register");

		private String path;

		Action(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}
	}
}
