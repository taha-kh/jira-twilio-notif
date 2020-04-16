package com.notif.twilio.jira.ui.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingModel {
	private String tel;
	private boolean notifyUserbySms;
	private String projectId;
	private String projectKey;
	
	public SettingModel(String projectId, String projectKey) {
		this.projectId = projectId;
		this.projectKey = projectKey;
	}
}