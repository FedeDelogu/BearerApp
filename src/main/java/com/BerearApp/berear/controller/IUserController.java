package com.BerearApp.berear.controller;

import com.BerearApp.berear.entity.Activity;
import com.BerearApp.berear.entity.IUser;
import com.BerearApp.berear.service.ActivityService;
import com.BerearApp.berear.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class IUserController {

    private final ActivityService activityService;
    private final IUserService iUserService;

    @PostMapping("/{userId}/activities/{activityId}/register")
    public ResponseEntity<String> registerToActivity(@PathVariable Long userId, @PathVariable Long activityId) {
        iUserService.registerToActivity(activityId, userId);
        return ResponseEntity.ok("User registered to activity successfully");
    }

    @DeleteMapping("/{userId}/activities/{activityId}/unregister")
    public ResponseEntity<String> unregisterFromActivity(@PathVariable Long userId, @PathVariable Long activityId) {
        iUserService.unregisterFromActivity(activityId, userId);
        return ResponseEntity.ok("User unregistered from activity successfully");
    }

    @GetMapping("/{userId}/activities")
    public ResponseEntity<Set<Activity>> getUserActivities(@PathVariable Long userId) {
        Set<Activity> activities = iUserService.getUserActivities(userId);
        return ResponseEntity.ok(activities);
    }
}


