package com.BerearApp.berear.service;

import org.springframework.stereotype.Service;


@Service
public interface UserLoginService {

  String getJwtByCredential(String user, String password);

}
