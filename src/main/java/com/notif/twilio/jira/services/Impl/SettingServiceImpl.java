package com.notif.twilio.jira.services.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.io.entities.Setting;
import com.notif.twilio.jira.io.entities.User;
import com.notif.twilio.jira.io.repositories.SettingRepository;
import com.notif.twilio.jira.io.repositories.UserRepository;
import com.notif.twilio.jira.services.PhoneVerificationService;
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.services.UserService;
import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.shared.dto.Userdto;

@Service
public class SettingServiceImpl implements SettingService{

	// == Fields ==
	@Autowired
	private SettingRepository settingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PhoneVerificationService phoneVerificationService;
	
	// == Public Methods ==
	@Override
	public void saveSetting(SettingDto settingDto, Userdto userDto) {	
		if (settingDto != null) {
			Setting setting = new Setting();				
			BeanUtils.copyProperties(settingDto, setting);
			if (!settingRepository.existsById(settingDto.getAccountId())) {										
				User user = userRepository.findById(setting.getAccountId()).orElse(null);
				if (user != null ) {
					// Save Settings
					setting.setJiraUser(user);
					settingRepository.save(setting);
				}else {
					// Create the new user and save settings
					userDto.setAccountId(settingDto.getAccountId());
					userService.saveUser(userDto);
					handleSetting(settingDto, userDto);					
				}				
			}else {
				// Update Setting
				settingRepository.save(setting);
			}
		}		
	}

	@Override
	public SettingDto getSetting(String userAccountId) {
		SettingDto settingDto = new SettingDto();
		
		if (userAccountId != null) {
			Setting setting = settingRepository.findById(userAccountId).orElse(null);
			BeanUtils.copyProperties(setting, settingDto);
		}
				
		return settingDto;
	}

	@Override
	public void handleSetting(SettingDto settingDto, Userdto userdto) {
		verifyPhoneNumber(userdto);
		userService.updateUser(userdto);		
		saveSetting(settingDto, userdto);
	}

	// == Private Methods ==
	private void verifyPhoneNumber(Userdto userdto) {
		Userdto currentUser = userService.findUserById(userdto.getAccountId());
		if (currentUser != null) {
			if (currentUser.getTel() != userdto.getTel()) {
				phoneVerificationService.callTwilioVerificationService(userdto.getTel());
			}
		}		
	}
}
