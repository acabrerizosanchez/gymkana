package com.springfilms.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/")
    @ResponseBody
    public String sayHelloWorld() {
        return "Hello World in your SpringBoot Application!";
    }
}
