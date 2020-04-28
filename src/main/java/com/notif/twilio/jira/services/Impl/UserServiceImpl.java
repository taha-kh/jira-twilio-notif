package com.notif.twilio.jira.services.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notif.twilio.jira.io.entities.User;
import com.notif.twilio.jira.io.repositories.UserRepository;
import com.notif.twilio.jira.services.UserService;
import com.notif.twilio.jira.shared.dto.Userdto;

@Service
public class UserServiceImpl implements UserService{

	// == Fields ==
	@Autowired
	private UserRepository userRepository;
	
	// == Public Methods ==
	@Override
	public void saveUser(Userdto userDto) {
		if (userDto != null) {
			if (!userRepository.existsById(userDto.getAccountId())) {
				User user = new User();
				BeanUtils.copyProperties(userDto, user);
				userRepository.save(user);
			}
		}
	}

	@Override
	public Userdto findUserById(String accountId) {
		Userdto userDto  = new Userdto();
		User user = userRepository.findById(accountId).orElse(null);
		if (user == null) {
			return null;
		}
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public void updateUser(Userdto userDto) {
		if(userDto != null) {
			User user = userRepository.findById(userDto.getAccountId()).orElse(null);
			if(user != null) {
				user.setTel(userDto.getTel());
				user.setTelChecked(userDto.isTelChecked());
				userRepository.save(user);
			}
		}
	}
}
