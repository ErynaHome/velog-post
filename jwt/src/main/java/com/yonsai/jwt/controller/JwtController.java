package com.yonsai.jwt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.yonsai.jwt.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class JwtController {

    @Autowired
    private JwtService jwtService;

  
    @GetMapping("/token")
    public String createToken() {
        log.info("토큰 생성");
        
        String token = jwtService.createToken("eryna");
        log.info("발급된 토큰: {} 사용자 :{}"
                                 ,token,"eryna");
        return token;
    }
    

    @GetMapping("/getUsername")
    public String getUsername(String token){
        log.info("토큰 검증 시작!");
        // request.getHeader("Authorization")
        //  헤더에서 토큰값을 뽑아라!

        String result = jwtService.getUsername(token);

        log.info("토큰 검증 완료!! {}",result);
        return result;
    }

    @GetMapping("/getHeader")
    public String getHeader(HttpServletRequest request){
        log.info("토큰 검증 시작! - authorization");
        //  헤더에서 토큰값을 뽑아라!
        String authHeader = request.getHeader("Authorization");
        log.info("헤더에서 뽑은 토큰: {}",authHeader);

        // 앞에 들어온 Bearer공백 을 없애야된다. 
        // 그래야 토큰 값이니깐!
        //  7이상부터는 토큰이기 때문에 끝까지 자르면 된다.
        String token = authHeader.substring(7);

        String result = jwtService.getUsername(token);

        log.info("토큰 검증 완료!! {}",result);
        return result;
    }

    @GetMapping("/accessTokenRefreshToken")
    public Map<String,String> accessTokenRefreshToken(String username){
        String accessToken = jwtService.createAccessToken(username);
        String refreshToken = jwtService.createRefreshToken();

        return Map.of(
            "accessToken" , accessToken,
            "refreshToken" , refreshToken
        );
        
    }



}