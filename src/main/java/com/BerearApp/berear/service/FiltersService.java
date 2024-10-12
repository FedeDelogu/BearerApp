package com.BerearApp.berear.service;

import com.BerearApp.berear.DTO.ActivityResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

//questa classe ha lo scopo di gestire i filtri per le attività. questo fa si che ogni filtro sia indipendente e modificabile facilmente.
//posso testarli singolarmente e ho tutto centralizzato in un punto.
public interface FiltersService {
    List<ActivityResponseDTO> getFilteredActivities(String nameActivity, LocalDateTime startDate,LocalDateTime endDate, boolean onlyAvailable);
}
