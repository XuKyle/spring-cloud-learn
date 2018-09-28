package com.kylexu.servicehi;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
/*服务提供者*/
@EnableEurekaClient
@RestController
/*开启断路器*/
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ServiceHiApplication {

    /*
     *
     * 断路器访问地址
     *
     * http://localhost:8762/actuator/hystrix.stream
     * */

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    /*
    之前的版本
    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "Eureka") String name) {
        return "hi " + name + " i am from port " + port + " !";
    }*/

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "Eureka") String name) {
        return "hi " + name + " i am from port " + port + " !";
    }

    public String hiError(String name) {
        return "hi ," + name + " sorry ,Error!";
    }
}
