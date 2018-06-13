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

import lt.baltic.talents.superhero.klounada.helpers.MessageHelper;
import lt.baltic.talents.superhero.klounada.models.User;

@Controller
public class BaseController {
	
	@Autowired
	private MessageHelper helper;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start(@RequestParam(value = "name", required = false) String name, Model model) {
		LocalDateTime date = LocalDateTime.now();
		model.addAttribute("now", Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));

		String operatingSystem = System.getProperty("os.name");
		model.addAttribute("operatingSystem", operatingSystem);
		
		String javaVersion = System.getProperty("java.version");
		model.addAttribute("javaVersion", javaVersion);
		
		User user = new User("Mindaugas", null);
		
		model.addAttribute("user", user);
		
		System.out.println(helper.getMessage("message.hello"));
		
		return "hello/base";
	}

}
