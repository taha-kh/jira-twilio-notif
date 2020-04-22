package com.notif.twilio.jira.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.services.IssueService;
import com.notif.twilio.jira.services.PhoneVerificationService;
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.shared.dto.SettingDto;

@Service
public class IssueServiceImpl implements IssueService {

	// == Fiels ==
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private PhoneVerificationService phoneVerificationService;

	// == Public Methods ==

	// Checks if the user want to be notified by SMS and call Twilio API to notify
	// him by SMS.
	@Override
	public void notifyUserOnIssueCreated(String accountId) {
		if (accountId != null) {
			SettingDto setting = settingService.getSetting(accountId);
			if (setting != null) {
				if (setting.getUser().isTelChecked() && setting.isNotifyBySms()) {
					phoneVerificationService.notifyBySmsOnIssueCreated();
				}
			}
		}

	}

}
