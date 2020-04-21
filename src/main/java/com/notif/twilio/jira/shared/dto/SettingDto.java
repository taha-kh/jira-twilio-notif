package com.notif.twilio.jira.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingDto {
	private String accountId;
	private boolean notifyBySms;
	private String projectId;
	private String projectKey;
	private Userdto user;
}