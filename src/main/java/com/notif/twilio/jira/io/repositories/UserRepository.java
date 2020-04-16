package com.notif.twilio.jira.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.notif.twilio.jira.io.entities.User;

public interface UserRepository extends CrudRepository<User, String> {
}
