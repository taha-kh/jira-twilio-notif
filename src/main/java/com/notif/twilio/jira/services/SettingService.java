package com.notif.twilio.jira.services;

import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.shared.dto.Userdto;

public interface SettingService {
	void saveSetting(SettingDto settingDto, Userdto userDto);

	SettingDto getSetting(String clientKey);

	void handleSetting(SettingDto settingDto, Userdto userdto);
}
