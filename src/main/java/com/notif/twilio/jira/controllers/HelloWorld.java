package com.notif.twilio.jira.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.ui.models.Greeting;


@Controller
public class HelloWorld {

	
	@GetMapping("/greeting")
	  public String greetingForm(@AuthenticationPrincipal AtlassianHostUser hostUser, Model model) {
	    model.addAttribute("greeting", new Greeting());
	    return "hello";
	  }

	  @PostMapping("/greeting")
	  public String greetingSubmit(@ModelAttribute Greeting greeting) {
		  Greeting g = greeting;
		return "form";
	}
}
