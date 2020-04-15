package com.notif.twilio.jira.services.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.io.entities.Setting;
import com.notif.twilio.jira.io.repositories.SettingRepository;
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.shared.dto.SettingDto;

@Service
public class SettingServiceImpl implements SettingService{

	// == Fields ==
	@Autowired
	private SettingRepository settingRepository;
	
	// == Public Methods ==
	@Override
	public void saveSetting(SettingDto settingDto) {
		Setting setting = new Setting();
		
		if (!settingRepository.existsById(settingDto.getClientKey())) {
			BeanUtils.copyProperties(settingDto, setting);
			settingRepository.save(setting);
		}
	}

	@Override
	public SettingDto getSetting(String clientKey) {
		SettingDto settingDto = new SettingDto();
		
		Setting setting = settingRepository.findById(clientKey).orElse(null);
		BeanUtils.copyProperties(setting, settingDto);
		
		return settingDto;
	}

}
