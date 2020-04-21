package com.notif.twilio.jira.services;

import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.shared.dto.Userdto;

public interface SettingService {
	void saveSetting(SettingDto settingDto);
	SettingDto getSetting(String accountId);
	boolean handleSetting(SettingDto settingDto);
}
