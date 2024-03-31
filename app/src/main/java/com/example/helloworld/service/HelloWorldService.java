package com.example.helloworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloworld.domain.entity.Hello;
import com.example.helloworld.domain.repository.HelloWorldMapper;

@Service
public class HelloWorldService {
  @Autowired
  private HelloWorldMapper helloWorldMapper;

  public Hello getHello() {
    Hello hello = helloWorldMapper.getHello();
    return hello;
  }
}
