package com.notif.twilio.jira.externals.twilio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TwilioVerifyAPI {

	// == Fields ==
	@Value("${twilio.baseurl}")
	private String twilioBaseURL;

	@Value("${twilio.account.sid}")
	private String twilioAccountSID;

	@Value("${twilio.auth.token}")
	private String twilioAuthToken;

	private String sid;

	// == Public Methods ==
	public void verificationService(String phoneNumber) {
		Twilio.init(twilioAccountSID, twilioAuthToken);
		Service service = Service.creator("Twilio Jira Notif Verification").create();

		sid = service.getSid();

		Verification verification = Verification.creator(sid, phoneNumber, "sms").create();

		log.info("Twilio Jira Notif Verification Status" + verification.getStatus());
	}

	public boolean checkVerification(String phoneNumber, String code) {
		Twilio.init(twilioAccountSID, twilioAuthToken);
		VerificationCheck verificationCheck = VerificationCheck.creator(sid, code).setTo(phoneNumber).create();

		if (verificationCheck.getStatus().equals("approved")) {
			return true;
		} else {
			return false;
		}
	}

}
