package com.kosa.kojaping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kosa.kojaping.mapper")
public class KojapingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KojapingApplication.class, args);
	}

}
