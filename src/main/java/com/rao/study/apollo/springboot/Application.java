package com.rao.study.apollo.springboot;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.rao.study.apollo.springboot.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@EnableApolloConfig
@SpringBootApplication
public class Application {
    public static void main(String[] args){//-Denv=FAT -Dfat_meta=http://localhost:8088
        ApplicationContext context = SpringApplication.run(Application.class,args);
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println(demoService.getName());
    }

}
