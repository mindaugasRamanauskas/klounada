package lt.baltic.talents.superhero.klounada.controllers;

import lt.baltic.talents.superhero.klounada.helpers.DateHelper;
import lt.baltic.talents.superhero.klounada.helpers.SystemHelper;
import lt.baltic.talents.superhero.klounada.models.LocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Scope("session")
@Controller
public class BaseController {

	@Autowired
	private LocalUser localUser;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start(@RequestParam(value = "name", required = false) String name, Model model) {
		if (! localUser.isLogged()) {
			return "redirect:/login";
		}

		model.addAttribute("now", DateHelper.convertToDate(LocalDateTime.now()));
		model.addAttribute("operatingSystem", SystemHelper.getSystemInformation("os.name"));
		model.addAttribute("javaVersion", SystemHelper.getSystemInformation("java.version"));
		model.addAttribute("user", localUser);

		return "hello/base";
	}

}
