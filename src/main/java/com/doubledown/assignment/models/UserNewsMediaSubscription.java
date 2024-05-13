package com.doubledown.assignment.models;

import jakarta.persistence.Column;

import java.util.List;

public class UserNewsMediaSubscription extends BaseEntity{

    @Column(name = "media")
    private NewsMediaType newsMediaType;

    @Column(name = "user_id")
    private List<User> users;


}
