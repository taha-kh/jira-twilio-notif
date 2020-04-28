package com.notif.twilio.jira.services;

import java.util.List;

import com.notif.twilio.jira.shared.dto.SettingDto;

public interface SettingService {
	void saveSetting(SettingDto settingDto);
	SettingDto getSetting(String accountId);
	List<SettingDto> getSettings();
	boolean handleSetting(SettingDto settingDto);
}
