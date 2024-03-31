package com.example.helloworld.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.helloworld.domain.entity.Hello;

@Mapper
public interface HelloWorldMapper {
  @Select("""
      SELECT message FROM hello
  """)
  Hello getHello();
}
