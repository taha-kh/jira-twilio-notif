package com.notif.twilio.jira.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.services.IssueService;
import com.notif.twilio.jira.shared.dto.WebhookIssueDto;


@RestController
public class IssueController {

	// == Fields ==
	@Autowired
	private IssueService issueService;
	
	// == Rest Controllers ==
	@PostMapping("/issue-created")
	public void onIssueCreated(@RequestBody WebhookIssueDto webhookIssueDto,
		      @AuthenticationPrincipal AtlassianHostUser hostUser) {
		issueService.onIssueCreated(webhookIssueDto, hostUser);
	}
}
