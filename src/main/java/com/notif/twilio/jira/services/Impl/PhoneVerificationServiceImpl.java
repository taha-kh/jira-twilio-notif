package com.notif.twilio.jira.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.externals.twilio.TwilioVerifyAPI;
import com.notif.twilio.jira.services.PhoneVerificationService;

@Service
public class PhoneVerificationServiceImpl implements PhoneVerificationService {
	
	// == Fields ==
	@Autowired
	private TwilioVerifyAPI twilioVerifyAPI;

	// == Public Methods ==
	@Override
	public void callTwilioVerificationService(String phoneNumber) {		
		twilioVerifyAPI.verificationService(phoneNumber);
	}

}
