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
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.ui.models.SettingModel;

@Controller
public class SettingController {

	// == Fields ==
	@Autowired
	private SettingService settingService;

	// == Rest Controllers ==
	@GetMapping("/settings")
	public String getSettingsPage(@AuthenticationPrincipal AtlassianHostUser hostUser, Model model,
			@RequestParam("projectId") String projectId, @RequestParam("projectKey") String projectKey) {
		model.addAttribute("settingModel", new SettingModel(projectId, projectKey));
		return "settings";
	}

	@PostMapping("/settings")
	public String handleSettings(@AuthenticationPrincipal AtlassianHostUser hostUser,
			@ModelAttribute("settingModel") SettingModel settingModel) {
		
		SettingDto settingDto = new SettingDto();
		settingDto.setAccountId(hostUser.getUserAccountId().orElse(null));
		settingDto.setNotifyBySms(settingModel.isNotifyUserbySms());
		settingDto.setProjectId(settingModel.getProjectId());
		settingDto.setProjectKey(settingModel.getProjectKey());
		settingService.saveSetting(settingDto);
		
		return "settingsvalidation";
	}
}
