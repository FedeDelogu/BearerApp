package com.BerearApp.berear.controller;

import com.BerearApp.berear.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserLoginController {


    private  UserLoginService userLoginService;
    @PostMapping("/dblogin")
      public ResponseEntity<?> dbLogin(@RequestParam ("user") String user, @RequestParam("password") String password)   {
            String jwtToken= userLoginService.getJwtByCredential(user,password);
        return ResponseEntity.ok(jwtToken);
        }
    }

