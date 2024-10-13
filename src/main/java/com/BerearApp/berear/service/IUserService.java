package com.BerearApp.berear.service;

import com.BerearApp.berear.entity.Activity;

import java.util.Set;

public interface IUserService {

   void registerToActivity(Long activityId, Long UserId);
    void unregisterFromActivity(Long activityId, Long UserId);

    Set<Activity> getUserActivities(Long userId);
}
