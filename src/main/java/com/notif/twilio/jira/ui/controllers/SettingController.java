package com.notif.twilio.jira.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.ui.models.ActivateSMSNotif;
import com.notif.twilio.jira.ui.models.SettingModel;

@Controller
public class SettingController {

	// == Fields ==
	@Autowired
	private SettingService settingService;

	// == Rest Controllers ==
	@GetMapping("/settings")
	public String getSettingsPage(@AuthenticationPrincipal AtlassianHostUser hostUser, Model model) {
		model.addAttribute("settingModel", new SettingModel());
		return "settings";
	}

	@PostMapping("/settings")
	public String handleSettings(@AuthenticationPrincipal AtlassianHostUser hostUser,
			@ModelAttribute("settingModel") SettingModel settingModel) {
		SettingDto settingDto = new SettingDto();
		settingDto.setUserAccountId(hostUser.getUserAccountId().orElse(null));
		settingDto.setClientKey(hostUser.getHost().getClientKey());
		if (settingModel.getActivateSMSNotif() == ActivateSMSNotif.Yes)
			settingDto.setSmsOnIssueCreated(true);
		else
			settingDto.setSmsOnIssueCreated(false);
		
		settingService.saveSetting(settingDto);
		
		return "settingsvalidation";
	}
}
