package com.nacos.test.server1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author visy.wang
 * @date 2022/6/14 11:15
 */
@RefreshScope
@RestController
public class EchoController {
    @Value("${hello:x}")
    private String hello;

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery：" + string+ ", hello="+hello;
    }
}
