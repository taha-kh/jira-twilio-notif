package com.notif.twilio.jira.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.services.UserService;
import com.notif.twilio.jira.shared.dto.Userdto;

@RestController
public class AddOnInstallationController {

	// == Fields ==
	
	@Autowired
	private UserService userService;

	// == Rest Controllers ==

	// Register and save user Account Id on AddOn installation
	@PostMapping("/addon-installed")
	public void onAddOnInstalled(@AuthenticationPrincipal AtlassianHostUser hostUser) {
		Userdto userDto = new Userdto();
		userDto.setAccountId(hostUser.getUserAccountId().orElse(null));
		userService.saveUser(userDto);
	}
}
