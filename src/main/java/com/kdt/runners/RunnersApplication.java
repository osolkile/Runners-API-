package com.kdt.runners;                       // ① 이 클래스의 폴더 주소

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication                         // ② "여기가 스프링 앱의 시작점"이라는 표시
public class RunnersApplication {

    public static void main(String[] args) {   // ③ 실행 버튼을 누르면 여기서 시작
        SpringApplication.run(RunnersApplication.class, args);  // ④ 서버를 켜고 요청을 기다림
    }
}