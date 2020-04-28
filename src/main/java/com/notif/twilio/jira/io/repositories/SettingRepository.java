package com.notif.twilio.jira.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notif.twilio.jira.io.entities.Setting;

public interface SettingRepository extends JpaRepository<Setting, String>{
}
