package com.BerearApp.berear.service;
import com.BerearApp.berear.DTO.ActivityResponseDTO;
import com.BerearApp.berear.entity.Activity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ActivityService {
List<Activity> findAll();
Activity create(Activity activity);

Activity findById(Long id);
Activity save(Activity theActivity);

void deleteByID(Long id);

    List<ActivityResponseDTO> getFilteredActivities(String name, LocalDateTime startDate, LocalDateTime endDate, boolean onlyAvailable);
}
