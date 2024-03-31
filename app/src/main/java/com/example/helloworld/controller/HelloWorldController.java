package com.example.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.domain.entity.Hello;
import com.example.helloworld.service.HelloWorldService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
  @Autowired
  private HelloWorldService helloWorldService;

  @GetMapping("/hello-world")
  public Map<String, String> get() {
    return Map.of("msg", "Hello World.");
  }

  @GetMapping("/hello-world-db")
  public Map<String, String> getFromDB() {
    Hello hello = helloWorldService.getHello();
    
    return Map.of("msg", hello.getMessage());
  }
}