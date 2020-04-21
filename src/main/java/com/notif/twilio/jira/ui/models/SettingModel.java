package com.notif.twilio.jira.ui.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingModel {
	private String accountId;
	private boolean notifyBySms;
	private String projectId;
	private String projectKey;
	private UserModel user;
	
	public SettingModel() {}
	
	public SettingModel(String projectId, String projectKey) {
		this.projectId = projectId;
		this.projectKey = projectKey;
	}
}