package com.notif.twilio.jira.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.io.entities.Setting;
import com.notif.twilio.jira.io.entities.User;
import com.notif.twilio.jira.io.repositories.SettingRepository;
import com.notif.twilio.jira.services.PhoneVerificationService;
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.services.UserService;
import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.shared.dto.Userdto;

@Service
public class SettingServiceImpl implements SettingService {

	// == Fields ==
	@Autowired
	private SettingRepository settingRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PhoneVerificationService phoneVerificationService;

	// == Public Methods ==

	// Saves and Updates Settings
	@Override
	public void saveSetting(SettingDto settingDto) {
		if (settingDto != null && settingDto.getUser().getAccountId() != null) {
			Setting setting = new Setting();
			BeanUtils.copyProperties(settingDto, setting);
			User user = new User();
			user.setAccountId(settingDto.getUser().getAccountId());
			user.setTel(settingDto.getUser().getTel());
			setting.setJiraUser(user);
			settingRepository.save(setting);
		}
	}

	// Return a Setting of a specific User.
	@Override
	public SettingDto getSetting(String accountId) {
		SettingDto settingDto = new SettingDto();

		if (accountId != null) {
			Setting setting = settingRepository.findById(accountId).orElse(null);

			if (setting != null) {
				BeanUtils.copyProperties(setting, settingDto);
				settingDto.setUser(userService.findUserById(accountId));
			} else {
				settingDto = null;
			}
		}

		return settingDto;
	}

	// Saves User and Settings if not exists
	// Return true if we must check the phone number.
	@Override
	public boolean handleSetting(SettingDto settingDto) {

		if (settingDto.getUser().getAccountId() != null) {
			// Check if user exists, if not save it.
			Userdto user = userService.findUserById(settingDto.getUser().getAccountId());
			if (user == null) {
				userService.saveUser(settingDto.getUser());				
			}else if(user.getAccountId() != null && user.getTel() == null) {
				userService.updateUser(settingDto.getUser());
			}
			
			// Refactor to avoid double call to DB to retrieve user. 
			// Change return of findUserById to UserDto instead of void.
			user = userService.findUserById(settingDto.getUser().getAccountId());

			// Save User Settings
			saveSetting(settingDto);

			// Check if the user has changes his phone number
			// Or he has not yet verified his telephone number
			String newPhoneNumber = settingDto.getUser().getTel();
			if (!(user.getTel().equals(newPhoneNumber)) || (user.isTelChecked() == false)) {
				userService.updateUser(settingDto.getUser());
				phoneVerificationService.callTwilioVerificationService(newPhoneNumber);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<SettingDto> getSettings() {
		List<SettingDto> settingsDto = new ArrayList<>();
		
		List<Setting> settings = settingRepository.findAll();
		
		for (Setting setting : settings) {
			SettingDto settingDto = new SettingDto();
			BeanUtils.copyProperties(setting, settingDto);
			settingDto.setUser(userService.findUserById(setting.getAccountId()));
			settingsDto.add(settingDto);
		}
		
		return settingsDto;
	}
}
