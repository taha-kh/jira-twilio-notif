package com.notif.twilio.jira.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.services.JiraUserService;
import com.notif.twilio.jira.services.ProjectService;
import com.notif.twilio.jira.shared.dto.JiraUserDto;
import com.notif.twilio.jira.shared.dto.ProjectDto;
import com.notif.twilio.jira.ui.models.SettingModel;
import com.notif.twilio.jira.ui.models.SettingsValidationModel;

@Controller
public class JiraTwilioNotifSettingsController {
	
	// == Fields ==
	//@Autowired
	//private ProjectService projectService;
	
	@Autowired
	private JiraUserService jiraUserService;

	//== Rest Controllers ==
	@GetMapping("/settings")
	public String getSettingsPage(@AuthenticationPrincipal AtlassianHostUser hostUser, Model model) {		
		model.addAttribute("settingModel", new SettingModel());
		
		JiraUserDto jiraUserDto = new JiraUserDto();
		jiraUserDto.setClientKey(hostUser.getHost().getClientKey());
		jiraUserDto.setBaseUrl(hostUser.getHost().getBaseUrl());
		jiraUserService.saveJiraUser(jiraUserDto);
		
		return "settings";
	}
	
	@PostMapping("/settings")
	public String handleSettings() {	
		return "settingsvalidation";
	}
}
