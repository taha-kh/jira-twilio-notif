package com.notif.twilio.jira.io.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Setting {
	@Id
	private String clientKey;
	
	private boolean smsOnIssueCreated;
	
}
