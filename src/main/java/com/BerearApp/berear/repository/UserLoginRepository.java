package com.BerearApp.berear.repository;

import com.BerearApp.berear.entity.IUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<IUser, Long > {
    Optional<IUser> findByUserName(String userName);
}
