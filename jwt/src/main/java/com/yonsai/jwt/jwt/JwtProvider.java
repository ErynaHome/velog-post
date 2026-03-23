package com.yonsai.jwt.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {

    private final String secret = "12345678901234567890123456789012"; // 32자


    // 토큰 생성 
    public String createToken(String username) {
        log.info("토큰 생성");
        
        String token = Jwts
                    .builder()
                    .setSubject(username)   //JWT의 주인 보통 userId
                    .setIssuedAt(new Date())  // 발급 시간 추후 유효성 검사
                    .signWith(Keys.hmacShaKeyFor( // 문자열을 서명으로 변경(도장)
                        secret.getBytes(StandardCharsets.UTF_8)))
                    .compact();   //토큰 완성!(3개를 묶어서 하나의 토큰 완성)
            
        log.info("발급된 토큰: {}",token);
    
        return token;
    }

    // 토큰에서 username을 꺼내서 검증
    public  String getUsername(String token){
        
        // getBytes()로 바꾼 이유는 비밀키를 컴퓨터가 이해 할 수있는 바이트 데이터로
        //             바꾼다. 

        // parseClaimsJws() 토큰을 진짜 검사하는 부분
        //  검증은 여기서 일어난다. 
        //  예외 터지는 상황 
        //  - 토큰 문자열이 이상할 때, payload 누가 수정함 
        //  - 토큰 시간이 만료되서,serct key 다를 수도있음
        log.info("JwtProvider - getUsername 실행");

        try{
        String result = Jwts //읽는 도구 만들기
                            .parserBuilder()        // JWT 읽고 검증하는 객체만들기
                            .setSigningKey(Keys.hmacShaKeyFor( // 문자열을 서명으로 변경(도장)
                                secret.getBytes(StandardCharsets.UTF_8)))
                            .build()                //검증 도구 세팅 끝!
                            .parseClaimsJws(token)  // 검증(실제 핵심)
                            .getBody()              // 검증 끝났으니 안에 내용들 꺼내기
                            .getSubject();

                            log.info("토큰 검증 완료!! username: {}",result);
            return result;
        }catch(ExpiredJwtException e){
            log.warn("토큰 만료에러!");
            return "토큰 만료 되었습니다.!";
        }
        catch(JwtException e){
            log.warn("위조 또는 잘못된 토큰입니다. 검증 실패!");
            return "토큰 검증 실패! 되었습니다.!";
        }
    }

    // access token이랑 refresh token을 같이 발급 하는 메서드 
    public String createAccessToken(String username){
        log.info("토큰 생성 - createAccessToken()");
        
        String token = Jwts
                    .builder()
                    .setSubject(username)   //JWT의 주인 보통 userId
                    .setIssuedAt(new Date())  // 발급 시간 추후 유효성 검사
                    
                    // 만료 시간이 30분!설정
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) 
                    .signWith(Keys.hmacShaKeyFor( // 문자열을 서명으로 변경(도장)
                        secret.getBytes(StandardCharsets.UTF_8)))
                    .compact();   //토큰 완성!(3개를 묶어서 하나의 토큰 완성)
            
        log.info("발급된 토큰: {}",token);

        return token;
    }

    // refresh token을 발급하는 메서드 
    public String createRefreshToken(){
        return UUID.randomUUID().toString();
    }




}
