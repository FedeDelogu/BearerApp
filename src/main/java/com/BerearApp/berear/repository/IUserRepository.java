package com.BerearApp.berear.repository;

import com.BerearApp.berear.entity.IUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<IUser, Long> {
}
