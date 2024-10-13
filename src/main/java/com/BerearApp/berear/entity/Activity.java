package com.BerearApp.berear.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@SQLDelete(sql = "UPDATE activity SET deleted = 1 WHERE id=?") potremmo implementarlo se vogliamo una cancellazione logica delle attività(manca campo boolean isdeleted;
//@Where(clause = "deleted = 0")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nameActivity;
    private String location;
    private String sala;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int availablePlaces;
    private int occupiedPlaces;


    @ManyToMany
    @JoinTable(
            name = "activity_category",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )

    private Set<Category> categories = new HashSet<>();

    private String imageUrl;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IUserActivity> registrations = new HashSet<>();
}
