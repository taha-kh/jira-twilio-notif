package com.notif.twilio.jira.services;

import com.notif.twilio.jira.shared.dto.IssueDto;

public interface IssueService {
	void onIssueCreated(IssueDto issueDto);
}
