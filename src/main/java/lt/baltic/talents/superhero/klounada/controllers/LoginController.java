package lt.baltic.talents.superhero.klounada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lt.baltic.talents.superhero.klounada.models.User;
import lt.baltic.talents.superhero.klounada.services.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
    private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, 
			@RequestParam(value = "user", required = false) String userParam,
			@RequestParam(value = "pwd", required = false) String pwd, RedirectAttributes redirectAttributes) {
		
		if (userParam == null || pwd == null) { 
			return "login/failure"; 
		}
		
		User user = new User(userParam, pwd.toCharArray());
		
		boolean login = userService.login(user);
		
		if (login) {
			redirectAttributes.addFlashAttribute("user", user);
			return "redirect:/";
		}
		
		return "login/failure";
	}

}
