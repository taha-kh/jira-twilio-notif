package com.notif.twilio.jira.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.services.IssueService;
import com.notif.twilio.jira.services.PhoneVerificationService;
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.shared.dto.IssueDto;
import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.shared.dto.Userdto;

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
	public void notifyUserOnIssueCreated(String baseUrl, String issueKey, String projectKey) {
		if (issueKey != null) {
			List<SettingDto> settings = settingService.getSettings();
			if (settings.size() > 0) {
				List<Userdto> usersToBeNotified = new ArrayList<>();
				for(SettingDto setting : settings) {
					if (setting.getUser().isTelChecked() && setting.isNotifyBySms() && setting.getProjectKey().equals(projectKey)) {
						usersToBeNotified.add(setting.getUser());		
					}
				}
				
				IssueDto issueDto = new IssueDto();		
				issueDto.setIssueKey(issueKey);
				issueDto.setIssueUrl(baseUrl + "/browse/" + issueKey);
				phoneVerificationService.notifyBySmsOnIssueCreated(issueDto, usersToBeNotified);
			}
		}
	}
}
