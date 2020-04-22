package com.notif.twilio.jira.services;

public interface PhoneVerificationService {
	void callTwilioVerificationService(String phoneNumber);
	boolean checkVerification(String code, String accountId);
	void notifyBySmsOnIssueCreated();
}
