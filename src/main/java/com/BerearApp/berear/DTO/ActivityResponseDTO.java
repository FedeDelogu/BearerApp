package com.BerearApp.berear.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDTO {

    private String nameActivity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int availablePlaces;
    private int occupiedPlaces;
    private String imageUrl;

}
