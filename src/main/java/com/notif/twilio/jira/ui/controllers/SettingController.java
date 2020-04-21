package com.notif.twilio.jira.ui.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.services.UserService;
import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.shared.dto.Userdto;
import com.notif.twilio.jira.ui.models.SettingModel;
import com.notif.twilio.jira.ui.models.UserModel;
import com.notif.twilio.jira.ui.models.VerifyPhoneModel;

@Controller
public class SettingController {

	// == Fields ==
	@Autowired
	private SettingService settingService;


	// == Rest Controllers ==
	
	// Called to get Setting Page
	@GetMapping("/settings")
	public String getSettingsPage(@AuthenticationPrincipal AtlassianHostUser hostUser, Model model,
			@RequestParam("projectId") String projectId, @RequestParam("projectKey") String projectKey) {

		SettingModel settingModel = new SettingModel();		
		SettingDto settingdto = settingService.getSetting(hostUser.getUserAccountId().orElse(null));
		
		if(settingdto != null) {
			BeanUtils.copyProperties(settingdto, settingModel);
			UserModel userModel = new UserModel();
			userModel.setTel(settingdto.getUser().getTel());
			settingModel.setUser(userModel);
		}else {
			settingModel = new SettingModel(projectId, projectKey) ;
			settingModel.setUser(new UserModel());
		}
		
		model.addAttribute("settingModel", settingModel);
		return "settings";
	}

	@PostMapping("/settings")
	public ModelAndView handleSettings(@AuthenticationPrincipal AtlassianHostUser hostUser,
			@ModelAttribute("settingModel") SettingModel settingModel) {
		
		SettingDto settingDto = new SettingDto();
		BeanUtils.copyProperties(settingModel, settingDto);		
		
		Userdto userDto = new Userdto();
		userDto.setAccountId(hostUser.getUserAccountId().orElse(null));
		userDto.setTel(settingModel.getUser().getTel());			
		settingDto.setUser(userDto);				
		settingDto.setAccountId(hostUser.getUserAccountId().orElse(null));
		
		boolean verifyPhoneNumber = settingService.handleSetting(settingDto);
		if(verifyPhoneNumber) {
			ModelAndView modelAndView = new ModelAndView("verifyphone");
		    modelAndView.addObject("verifyPhoneModel", new VerifyPhoneModel());
		    return modelAndView;
		}			
		else {
			ModelAndView modelAndView = new ModelAndView("settingsvalidation");
		    return modelAndView;
		}
	}
	
	@PostMapping("/verifyphone")
	public String verifyPhone(@AuthenticationPrincipal AtlassianHostUser hostUser,
			@ModelAttribute("VerifyPhoneModel") VerifyPhoneModel VerifyPhoneModel) {
				
			return "settingsvalidation";
	}
}
