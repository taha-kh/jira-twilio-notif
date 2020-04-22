package com.notif.twilio.jira.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.externals.twilio.TwilioVerifyAPI;
import com.notif.twilio.jira.services.PhoneVerificationService;
import com.notif.twilio.jira.services.UserService;
import com.notif.twilio.jira.shared.dto.Userdto;

@Service
public class PhoneVerificationServiceImpl implements PhoneVerificationService {
	
	// == Fields ==
	@Autowired
	private TwilioVerifyAPI twilioVerifyAPI;
	
	@Autowired
	private UserService userService;

	// == Public Methods ==
	@Override
	public void callTwilioVerificationService(String phoneNumber) {		
		twilioVerifyAPI.verificationService(phoneNumber);
	}

	@Override
	public boolean checkVerification(String code, String accountId) {
		Userdto user  = userService.findUserById(accountId);
		boolean isTelCheked = twilioVerifyAPI.checkVerification(user.getTel(), code);
		if (isTelCheked) {
			user.setTelChecked(true);
			userService.updateUser(user);
			return true;
		}else {
			return false;
		}
	}

}
