package com.BerearApp.berear.service.Impl;

import com.BerearApp.berear.DTO.ActivityResponseDTO;
import com.BerearApp.berear.entity.Activity;
import com.BerearApp.berear.repository.ActivityRepository;
import com.BerearApp.berear.service.FiltersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
//in base ai criteri richiesti restiuisce filtri diversi, nome,data,disponiiblità
@Service
@RequiredArgsConstructor
public class ActivityFiltersServiceImpl implements FiltersService {


    private final ActivityRepository activityRepository;

    @Override
    public List<ActivityResponseDTO> getFilteredActivities(String name, LocalDateTime startDate, LocalDateTime endDate, boolean onlyAvailable) {
        List<Activity> activities = activityRepository.findAll();
        if (name == null && startDate == null && endDate == null && !onlyAvailable) {
            return activities.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }
        return activities.stream()
                .filter(applyNameFilter(name)) // filtro nome
                .filter(applyDateFilter(startDate, endDate))//filtro date
                .filter(applyAvailabilityFilter(onlyAvailable)) // filtro se disponbiiel
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private Predicate<Activity> applyNameFilter(String name) {
        return activity -> (name == null || activity.getNameActivity().equalsIgnoreCase(name));
    }

    private Predicate<Activity> applyDateFilter(LocalDateTime startDate, LocalDateTime endDate) {
        return activity -> (startDate == null || !activity.getStartDateTime().isBefore(startDate)) &&
                (endDate == null || !activity.getEndDateTime().isAfter((endDate)));
    }

    private Predicate<Activity> applyAvailabilityFilter(boolean onlyAvailable) {
        return activity -> !onlyAvailable || (activity.getAvailablePlaces() > 0 && activity.getEndDateTime().isAfter(LocalDateTime.now()));
    }

    private ActivityResponseDTO mapToDTO(Activity activity) {
        return new ActivityResponseDTO(
                activity.getNameActivity(),
                activity.getStartDateTime(),
                activity.getEndDateTime(),
                activity.getAvailablePlaces(),
                activity.getOccupiedPlaces(),
                activity.getImageUrl()
        );
    }

}

