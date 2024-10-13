package com.BerearApp.berear.service.Impl;


import com.BerearApp.berear.entity.Activity;
import com.BerearApp.berear.entity.IUser;
import com.BerearApp.berear.entity.IUserActivity;
import com.BerearApp.berear.repository.ActivityRepository;
import com.BerearApp.berear.repository.IUserRepository;
import com.BerearApp.berear.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IUserServiceImpl implements IUserService {

    private final IUserRepository iUserRepository;
    private final ActivityRepository activityRepository;

@Override
    public void registerToActivity(Long activityId, Long userId){
        Optional<IUser> userOptional= iUserRepository.findById(userId);
        Optional<Activity> activityOptional= activityRepository.findById(activityId);

        if(activityOptional.isEmpty() || userOptional.isEmpty()){
            throw new RuntimeException("acitivty or user not found");
        }

        Activity activity=activityOptional.get();
        IUser iuser =userOptional.get();

        if(activity.getAvailablePlaces()>0){
            IUserActivity registration= new IUserActivity();
            registration.setUser(iuser);
            registration.setActivity(activity);

            iuser.getRegistrations().add(registration);
            activity.getRegistrations().add(registration);

            activity.setOccupiedPlaces(activity.getOccupiedPlaces()+1);
            activity.setAvailablePlaces(activity.getAvailablePlaces()-1);

            activityRepository.save(activity);
            iUserRepository.save(iuser);
        }
        else{
            throw new RuntimeException("no places available");
        }
    }


        @Override
        public void unregisterFromActivity(Long activityId, Long userId) {
            Optional<Activity> activityOptional = activityRepository.findById(activityId);
            Optional<IUser> userOptional = iUserRepository.findById(userId);

            if (activityOptional.isEmpty() || userOptional.isEmpty()) {
                throw new RuntimeException("Activity or User not found");
            }

            Activity activity = activityOptional.get();
            IUser user = userOptional.get();

            IUserActivity registrationToRemove = null;
            for (IUserActivity registration : user.getRegistrations()) {
                if (registration.getActivity().getId().equals(activityId)) {
                    registrationToRemove = registration;
                    break;
                }
            }

            if (registrationToRemove != null) {

                user.getRegistrations().remove(registrationToRemove);
                activity.getRegistrations().remove(registrationToRemove);


                activity.setOccupiedPlaces(activity.getOccupiedPlaces() - 1);


                activityRepository.save(activity);
                iUserRepository.save(user);
            } else {
                throw new RuntimeException("User is not registered for this activity");
            }
        }


    @Override //recupero con id , uso di optional per evitare le null pointer neel caso in cui l'utente con id non sia nel db
    public Set<Activity> getUserActivities(Long userId) {
    Optional<IUser> userOptional = iUserRepository.findById(userId);
    if(userOptional.isEmpty()) {
        throw new RuntimeException("user not found");
    }
    IUser iUser = userOptional.get();
    return iUser.getRegistrations().stream()
            .map(IUserActivity::getActivity)
            .collect(Collectors.toSet());
    }

}
