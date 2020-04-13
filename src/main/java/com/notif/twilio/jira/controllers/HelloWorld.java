package com.notif.twilio.jira.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atlassian.connect.spring.AtlassianHostUser;

@Controller
public class HelloWorld {
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getHelloPage(@AuthenticationPrincipal AtlassianHostUser hostUser) {
		return "hello";
	}
}
