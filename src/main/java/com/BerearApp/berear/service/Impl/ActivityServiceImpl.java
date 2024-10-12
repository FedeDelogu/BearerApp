package com.BerearApp.berear.service.Impl;

import com.BerearApp.berear.DTO.ActivityResponseDTO;
import com.BerearApp.berear.entity.Activity;
import com.BerearApp.berear.repository.ActivityRepository;
import com.BerearApp.berear.service.ActivityService;
import com.BerearApp.berear.service.FiltersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activitiyRepository;
    private final FiltersService filtersService;

    @Override
    public List<Activity> findAll() {
        return activitiyRepository.findAll();
    }

    @Override
    public Activity create(Activity activity) {

        return activitiyRepository.save(activity);
    }

    @Override
    public Activity findById(Long id) {

        return activitiyRepository.findById(id).orElse(null);
    }

    @Override
    public Activity save(Activity theActivity) {
        return activitiyRepository.save(theActivity);
    }

    @Override
    public void deleteByID(Long id) {
        activitiyRepository.deleteById(id);
    }

    @Override
    public List<ActivityResponseDTO> getFilteredActivities(String name, LocalDateTime startDate, LocalDateTime endDate, boolean onlyAvailable) {
        return filtersService.getFilteredActivities(name, startDate, endDate, onlyAvailable);
    }



}



