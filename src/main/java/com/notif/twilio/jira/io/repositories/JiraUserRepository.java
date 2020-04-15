package com.notif.twilio.jira.io.repositories;

import org.springframework.data.repository.CrudRepository;
import com.notif.twilio.jira.io.entities.JiraUserEntity;

public interface JiraUserRepository extends CrudRepository<JiraUserEntity, String>{

}
