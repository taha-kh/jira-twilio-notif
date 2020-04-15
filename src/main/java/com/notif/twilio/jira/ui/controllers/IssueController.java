package com.notif.twilio.jira.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.services.IssueService;
import com.notif.twilio.jira.shared.dto.IssueDto;


@Controller
public class IssueController {

	// == Fields ==
	@Autowired
	private IssueService issueService;
	
	// == Rest Controllers ==
	@PostMapping("/issue-created")
	public void onIssueCreated(@RequestBody IssueDto issueDto,
		      @AuthenticationPrincipal AtlassianHostUser hostUser) {
		issueService.onIssueCreated(issueDto);
	}
}
