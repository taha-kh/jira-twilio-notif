package com.notif.twilio.jira.services;

import com.notif.twilio.jira.shared.dto.ProjectDto;

public interface ProjectService {
	ProjectDto getProjectByKey(String key);
}
