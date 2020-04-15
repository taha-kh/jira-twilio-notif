package com.notif.twilio.jira.io.entities;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class JiraUserEntity {
	@Id
	private String clientKey;
	
	private String baseUrl;
	
}
