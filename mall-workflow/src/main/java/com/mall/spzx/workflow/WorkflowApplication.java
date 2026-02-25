package com.mall.spzx.workflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WorkflowApplication {

   public static void main(String[] args) {
       SpringApplication.run(WorkflowApplication.class, args);
       log.info("mall-workflow started successfully");
   }
}
