package com.notif.twilio.jira.services.Impl;

import org.springframework.stereotype.Service;

import com.notif.twilio.jira.services.IssueService;
import com.notif.twilio.jira.shared.dto.IssueDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IssueServiceImpl implements IssueService {

	// == Public Methods ==
	@Override
	public void onIssueCreated(IssueDto issueDto) {
		log.info("Issue Created. Id : " + issueDto.getId() + " Key : " + issueDto.getKey());
	}

}
