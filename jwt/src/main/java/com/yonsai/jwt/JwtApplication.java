package com.yonsai.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}
/*
JWT(Json Web Token)
 - 로그인한 사용자를 서버가 기억하지 않고도 인증할 수있게 해주는 토큰
 - 세션의 문제점의 문제점인 사용자 정보를 저장하지 않는다.
 - 신분증(토큰) 만 보고 검증 
 - 사용자가 직접 들고 다니는 구조!

JWT 만드는 구조 3가지
 HEADER (헤더) - 알고리즘 정보(주민증/여권/운전면허증/모바일 신분증)
  - 이 토큰 어떻게 만들었냐?

 PAYLOAD(제일 중요) - 사용자 정보
  - 누구냐?
  - 아이디,권한,만료시간  홍길동
  - 누구나 볼 수있기 때문에 절대 민감한 정보나 비밀번호 X

 SIGNATURE
   - 위조 방지 
   - 서버만 알고 있는 키로 생성됨


세션의 실행 순서 
 - 사용자 로그인 -> 서버가 세션 생성 -> 세션ID 쿠키로 저장 

세션 방식의 문제점
 - 서버가 사용자 상태를 계속 저장해야함 (메모리 부담)
 - 서버 여러 개면 세션 공유 필요(Redis)
 - 서버가 사용자를 일일이 기억을 해야 한다(부담)
 - 확장성 안 좋음




*/