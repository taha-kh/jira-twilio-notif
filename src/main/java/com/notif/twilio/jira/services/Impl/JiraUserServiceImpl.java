package com.notif.twilio.jira.services.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.io.entities.JiraUserEntity;
import com.notif.twilio.jira.io.repositories.JiraUserRepository;
import com.notif.twilio.jira.services.JiraUserService;
import com.notif.twilio.jira.shared.dto.JiraUserDto;

@Service
public class JiraUserServiceImpl implements JiraUserService {

	// == Fields ==
	@Autowired
	private JiraUserRepository jiraUserRepository;

	// == Public Methods ==
	@Override
	public void saveJiraUser(JiraUserDto jiraUserDto) {
		JiraUserEntity jiraUserEntity = new JiraUserEntity();
		BeanUtils.copyProperties(jiraUserDto, jiraUserEntity);
		if (jiraUserRepository.findById(jiraUserEntity.getClientKey()) != null) {
			jiraUserRepository.save(jiraUserEntity);
		}
	}

	@Override
	public JiraUserDto getJiraUserByClientKey(String clientKey) {
		JiraUserEntity jiraUserEntity = jiraUserRepository.findById(clientKey).orElse(null);
		JiraUserDto jiraUserDto = new JiraUserDto();
		BeanUtils.copyProperties(jiraUserEntity, jiraUserDto);
		return jiraUserDto;
	}

}
