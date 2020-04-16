package com.notif.twilio.jira.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlassian.connect.spring.AtlassianHostUser;
import com.notif.twilio.jira.services.IssueService;
import com.notif.twilio.jira.services.SettingService;
import com.notif.twilio.jira.shared.dto.SettingDto;
import com.notif.twilio.jira.shared.dto.WebhookIssueDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IssueServiceImpl implements IssueService {
	
	// == Fields ==
	@Autowired
	private SettingService settingService;

	// == Public Methods ==
	@Override
	public void onIssueCreated(WebhookIssueDto webhookssueDto, AtlassianHostUser hostUser) {
//		SettingDto settingdto = settingService.getSetting(hostUser.getUserAccountId().orElse(null));
//		if (settingdto != null && settingdto.isSmsOnIssueCreated()) {
//			log.info("Issue Created. Id : " + webhookssueDto.getIssue().getId() + " Key : " + webhookssueDto.getIssue().getKey());
//			log.info("Username : " + webhookssueDto.getUser().getUsername());
//		}		
	}

}
