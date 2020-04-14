package com.notif.twilio.jira.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.ui.models.SettingModel;

@Controller
public class JiraTwilioNotifSettingsController {

	//== Rest Controllers ==
	@GetMapping("/settings")
	public String getSettingsPage(@AuthenticationPrincipal AtlassianHostUser hostUser, Model model) {
		model.addAttribute("settingModel", new SettingModel());
		return "settings";
	}
	
	@PostMapping("/settings")
	public String handleSettings(@ModelAttribute SettingModel settingModel) {
		return "settingsvalidation";
	}
}
