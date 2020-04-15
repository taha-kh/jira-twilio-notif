package com.notif.twilio.jira.services;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.shared.dto.WebhookIssueDto;

public interface IssueService {
	void onIssueCreated(WebhookIssueDto webhookIssueDto, AtlassianHostUser hostUser);
}
