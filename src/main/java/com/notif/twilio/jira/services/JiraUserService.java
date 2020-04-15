package com.notif.twilio.jira.services;

import com.notif.twilio.jira.shared.dto.JiraUserDto;

public interface JiraUserService {
	void saveJiraUser(JiraUserDto jiraUserDto);
	JiraUserDto getJiraUserByClientKey(String clientKey);
}
