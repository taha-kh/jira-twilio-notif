package com.notif.twilio.jira.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.notif.twilio.jira.io.entities.Setting;

public interface SettingRepository extends CrudRepository<Setting, String>{
}
