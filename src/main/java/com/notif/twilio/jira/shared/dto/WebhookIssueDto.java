package com.notif.twilio.jira.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebhookIssueDto {
	private String timestamp;
	private Userdto user;
	private IssueDto issue;
}
