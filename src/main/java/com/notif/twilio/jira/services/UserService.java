package com.notif.twilio.jira.services;

import com.notif.twilio.jira.shared.dto.Userdto;

public interface UserService {
	void saveUser(Userdto userDto);
}
