package com.notif.twilio.jira.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.services.IssueService;

@RestController
public class IssueController {

	// == Fields ==
	@Autowired
	private IssueService issueService;

	// == Rest Controllers ==

	// Webhook catchs issue creation event and call Twilio API to notify user by SMS
	@PostMapping("/issue-created")
	public void onNewIssueCreated(@AuthenticationPrincipal AtlassianHostUser hostUser,
			@RequestParam("issueKey") String issueKey, @RequestParam("projectKey") String projectKey) {
		issueService.notifyUserOnIssueCreated(hostUser.getHost().getBaseUrl(),
				issueKey, projectKey);
	}

}
