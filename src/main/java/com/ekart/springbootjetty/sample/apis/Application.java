package com.ekart.springbootjetty.sample.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableSwagger2
public class Application { // NOSONAR

   public static void main(final String[] args) {

      SpringApplication.run(Application.class, args); // NOSONAR
   }
}
