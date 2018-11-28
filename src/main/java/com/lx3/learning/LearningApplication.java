package com.lx3.learning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author mic
 */
@SpringBootApplication
@MapperScan({"com.lx3.learning.dao","com.lx3.learning.pojo"})
public class LearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
	}
}
