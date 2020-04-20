package com.notif.twilio.jira.services;

public interface PhoneVerificationService {
	void callTwilioVerificationService(String phoneNumber);
}
