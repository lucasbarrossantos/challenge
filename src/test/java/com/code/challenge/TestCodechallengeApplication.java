package com.code.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCodechallengeApplication {

	public static void main(String[] args) {
		SpringApplication.from(CodechallengeApplication::main).with(TestCodechallengeApplication.class).run(args);
	}

}
