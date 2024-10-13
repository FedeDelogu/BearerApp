package com.BerearApp.berear.service.Impl;


import com.BerearApp.berear.entity.IUser;
import com.BerearApp.berear.repository.UserLoginRepository;
import com.BerearApp.berear.service.UserLoginService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLoginRepository userLoginRepository;

    @Override
    public String getJwtByCredential(String userName, String password) {
        Optional<IUser> user = userLoginRepository.findByUserName(userName);
        if (user.isEmpty())
            throw new RuntimeException("User non trovato");
        if (password.equals(user.get().getPassword())) {
            return getJwtToken(new IUser(user.get().getUserName(),
                    user.get().getFirstName(),
                    user.get().getLastName(),
                    user.get().getPassword()));
        } else
            throw new RuntimeException("Password errata");
    }

    private String getJwtToken(IUser user) {
        return getJwtClaims(user).sign(Algorithm.HMAC256(""));
    }

    private JWTCreator.Builder getJwtClaims(IUser iuser) {
        return JWT.create()
                .withClaim("username", iuser.getUserName())
                .withClaim("firstName", iuser.getFirstName())
                .withClaim("lastName", iuser.getLastName());

    }
}

