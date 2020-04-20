package com.notif.twilio.jira.io.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Setting {
	@Id
	private String accountId;
		
	private String projectId;
	
	private boolean notifyBySms;
	
	private String projectKey;	
	
	@OneToOne
	@MapsId
	private User jiraUser;
}
