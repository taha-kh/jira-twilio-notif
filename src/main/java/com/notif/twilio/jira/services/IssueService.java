package com.notif.twilio.jira.services;

public interface IssueService {
	void notifyUserOnIssueCreated(String baseUrl, String issueKey, String projectKey);
}
