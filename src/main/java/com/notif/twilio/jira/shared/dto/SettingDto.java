package com.notif.twilio.jira.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingDto  {
	private String clientKey;
	private boolean smsOnIssueCreated;
}
