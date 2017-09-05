package com.kyle.servicefeign.hello;

import org.springframework.stereotype.Component;

@Component
public class ScheduleServiceHiHystric implements ScheduleServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "Sorry " + name + " ,Shit Happen!";
    }
}
