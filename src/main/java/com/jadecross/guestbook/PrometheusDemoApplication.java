package com.jadecross.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RestController
public class PrometheusDemoApplication {

    private final MeterRegistry meterRegistry;
    private Counter counter;

    public PrometheusDemoApplication(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        this.counter = this.meterRegistry.counter("requests_total");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        counter.increment();
        return "Hello " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(PrometheusDemoApplication.class, args);
    }
}
