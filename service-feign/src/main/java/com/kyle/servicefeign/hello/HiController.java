package com.kyle.servicefeign.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    ScheduleServiceHi scheduleServiceHi;

    @RequestMapping(value = "/sayHi")
    public String sayHi(@RequestParam(value = "name") String name) {
        return scheduleServiceHi.sayHiFromClientOne(name);
    }
}
