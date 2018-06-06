package com.crp.qa.qaGateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class QaGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaGatewayApplication.class, args);
	}
}
