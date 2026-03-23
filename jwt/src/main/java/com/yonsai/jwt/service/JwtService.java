package com.yonsai.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonsai.jwt.jwt.JwtProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
    @Autowired
    private JwtProvider jwtProvider; //토큰 생성 담당

    // 토큰을 만드는 객체를 가지고 와서 메서드로 호출해서 처리한다.
    public String createToken(String username){
        return jwtProvider.createToken(username);        
    }

    // 토큰 검증하는 메서드 
    public String getUsername(String token){
        log.info("service - getUsername()");

        return jwtProvider.getUsername(token);
    }

    public String createAccessToken(String username){
        return jwtProvider.createAccessToken(username);
    }

    
    public String createRefreshToken(){
        return jwtProvider.createRefreshToken();
    }

}
