package com.notif.twilio.jira.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueDto {
	private String issueKey;
	private String issueUrl;
}
