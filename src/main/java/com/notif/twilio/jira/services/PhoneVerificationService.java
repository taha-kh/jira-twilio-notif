package com.notif.twilio.jira.services;

import java.util.List;

import com.notif.twilio.jira.shared.dto.IssueDto;
import com.notif.twilio.jira.shared.dto.Userdto;

public interface PhoneVerificationService {
	void callTwilioVerificationService(String phoneNumber);
	boolean checkVerification(String code, String accountId);
	void notifyBySmsOnIssueCreated(IssueDto issueDto,List<Userdto> usersToBeNotified);
}
