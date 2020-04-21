package com.notif.twilio.jira.io.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name="jiraUser")
public class User {
	@Id
	private String accountId;
	
	private String username;
	
	private String key;
	
	private String tel;
	
	private boolean telChecked;
	
	@OneToOne(mappedBy = "jiraUser", cascade= CascadeType.ALL)
	private Setting setting;
}
