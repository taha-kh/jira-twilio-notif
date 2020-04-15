package com.notif.twilio.jira.shared.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto implements Serializable {
	private String id;
	private String key;
	private String description;	
}
