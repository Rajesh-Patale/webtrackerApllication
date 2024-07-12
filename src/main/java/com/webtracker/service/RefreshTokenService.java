package com.webtracker.service;

import com.webtracker.Util.RefreshToken;
import com.webtracker.entity.User;
import com.webtracker.repository.RefreshTokenRepository;
import com.webtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    public long refreshTokenValidity = 2*60*1000;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(String username) {

        User user = userRepository.findByUsername(username);
        RefreshToken token = user.getRefreshTokenId();

        if (token == null){
            token = RefreshToken
                        .builder()
                        .refreshTokenId(UUID.randomUUID().toString())
                        .expiry(Instant.now().plusMillis(refreshTokenValidity))
                        .userData(user)
                        .build();
        }else{
            token.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }

        user.setRefreshTokenId(token);

        refreshTokenRepository.save(token);

        return token;
    }

    public RefreshToken verifyRefreshToken(String refreshTokenId){

        RefreshToken freshToken = refreshTokenRepository.findByRefreshTokenId(refreshTokenId).orElseThrow(() -> new RuntimeException("Token expired."));

        if (freshToken.getExpiry().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(freshToken);
            throw new RuntimeException("Refresh token expired");
        }
        return freshToken;
    }
}
