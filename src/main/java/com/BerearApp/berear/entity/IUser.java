package com.BerearApp.berear.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="IUser")
public class IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String deletedSubscription;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IUserActivity> registrations = new HashSet<>();

    public IUser(String userName, String firstName, String lastName, String password) {

    }

    public Set<Activity> getActivities() {
        Set<Activity> activities = new HashSet<>();
        for (IUserActivity registration : registrations) {
            activities.add(registration.getActivity());
        }
        return activities;}



    }
