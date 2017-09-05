package com.kyle.serviceribbon.hello;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    /*熔断器的功能*/
    @HystrixCommand(fallbackMethod = "hiServiceError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    public String hiServiceError(String name) {
        return "hi," + name + "sorry , Error Happen!";
    }
}
