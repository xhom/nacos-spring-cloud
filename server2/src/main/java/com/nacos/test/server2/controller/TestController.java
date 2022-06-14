package com.nacos.test.server2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author visy.wang
 * @date 2022/6/14 11:16
 */
@RestController
public class TestController {
    private final RestTemplate restTemplate;

    @Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/echo/{str}")
    public String echo(@PathVariable String str) {
        String response = restTemplate.getForObject("http://nacos-server1/echo/" + str, String.class);
        return "server2: " + response ;
    }
}
