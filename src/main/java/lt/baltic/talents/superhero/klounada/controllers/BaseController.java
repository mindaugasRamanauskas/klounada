package lt.baltic.talents.superhero.klounada.controllers;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lt.baltic.talents.superhero.klounada.helpers.MessageHelper;
import lt.baltic.talents.superhero.klounada.models.User;

@Controller
public class BaseController {
	
	@Autowired
	private MessageHelper helper;
	
	private User user;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start(@RequestParam(value = "name", required = false) String name, Model model,
			RedirectAttributes redirectAttributes) {
		LocalDateTime date = LocalDateTime.now();
		model.addAttribute("now", Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));

		String operatingSystem = System.getProperty("os.name");
		model.addAttribute("operatingSystem", operatingSystem);
		
		String javaVersion = System.getProperty("java.version");
		model.addAttribute("javaVersion", javaVersion);
		
		User user = (User) model.asMap().get("user");
		System.out.println("***** " + (user == null ? "" : user.getLogin()));
		
		if (user != null) {
			this.user = user;
		}
		
		model.addAttribute("user", this.user);
		
		System.out.println(helper.getMessage("message.hello"));
		
		return "hello/base";
	}

}
