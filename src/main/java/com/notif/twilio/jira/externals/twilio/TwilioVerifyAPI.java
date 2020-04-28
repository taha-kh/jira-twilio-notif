package com.notif.twilio.jira.externals.twilio;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.notif.twilio.jira.shared.dto.IssueDto;
import com.notif.twilio.jira.shared.dto.Userdto;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TwilioVerifyAPI {

	// == Fields ==	
	@Value("${twilio.account.sid}")
	private String twilioAccountSID;

	@Value("${twilio.auth.token}")
	private String twilioAuthToken;
	
	@Value("${twilio.phonenumber}")
	private String twilioPhoneNumber;

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
	
	public void smsOnIssueCreated(IssueDto issueDto, List<Userdto> usersToBeNotified) {
		String smsOnIssueCreated = "An issue has been created in your Jira Project " + issueDto.getIssueUrl();
		
		Twilio.init(twilioAccountSID, twilioAuthToken);
		
		if (usersToBeNotified.size() > 0) {
			for(Userdto user : usersToBeNotified) {
				Message message = Message.creator(
						new PhoneNumber(user.getTel()),
						new PhoneNumber(twilioPhoneNumber), 
						smsOnIssueCreated).create();
						
				log.info("New Issue Was Created --> " + issueDto.getIssueUrl() + "Notify " + user.getTel());
				log.info("Message Sid --> " + message.getSid());
			}
		}
	}

}
