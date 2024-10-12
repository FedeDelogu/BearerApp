package com.BerearApp.berear.controller;


import com.BerearApp.berear.DTO.ActivityResponseDTO;
import com.BerearApp.berear.entity.Activity;
import com.BerearApp.berear.service.ActivityService;
import com.BerearApp.berear.service.FiltersService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ActivityRestController {

    private final ActivityService activityService;



    @GetMapping("/activities")
    public List<Activity> findAll() {

        return activityService.findAll();
    }


    @GetMapping("/activities/{activityId}")
    public Activity getActivity(@PathVariable("activityId") Long id) {

        Activity theActivity = activityService.findById(id);

        if (theActivity == null) {
            throw new RuntimeException("Activity id not found - " + id);
        }

        return theActivity;
    }
    @PostMapping("/activities")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {


        Activity dbActivity = activityService.create(activity);

        return new ResponseEntity<>(dbActivity, HttpStatus.CREATED);
    }


    @PutMapping("/activities")
    public Activity updateActivity(@RequestBody Activity theActivity) {

        Activity dbActivity = activityService.save(theActivity);

        return dbActivity;
    }


    @DeleteMapping("/activities/{id}")
    public String deleteActivity(@PathVariable Long id) {

        Activity tempActivity = activityService.findById(id);


        if (tempActivity == null) {
            throw new RuntimeException("Activity id not found - " + id);
        }

        activityService.deleteByID(id);

        return "Deleted Activity id - " + id;
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ActivityResponseDTO>> getFilteredActivities(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(defaultValue = "false") boolean onlyAvailable) {

        List<ActivityResponseDTO> activities = activityService.getFilteredActivities(name, startDate, endDate, onlyAvailable);

        if (activities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Nessuna attività trovata
        }
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }
    }













