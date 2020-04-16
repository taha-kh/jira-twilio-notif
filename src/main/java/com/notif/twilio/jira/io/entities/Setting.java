package com.notif.twilio.jira.io.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(SettingKey.class)
public class Setting {
	@Id
	private String accountId;
	
	@Id
	private String projectId;
	
	private boolean notifyBySms;
	
	private String projectKey;	
}
