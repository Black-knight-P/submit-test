package com.kakao.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class KakaopayCafeHwApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaopayCafeHwApplication.class, args);
	}

}
