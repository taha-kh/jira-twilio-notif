package com.notif.twilio.jira.services;

import com.notif.twilio.jira.shared.dto.SettingDto;

public interface SettingService {
	void saveSetting(SettingDto settingDto);
	SettingDto getSetting(String clientKey);
}
