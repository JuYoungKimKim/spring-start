package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    // @ResponseBody 를 적으면 http body 에 이 데이터를 직접 넣어주겠다는 이야기다
    // 그러므로 hello + requestparam 내용이 그냥 바로 내려간다.
    // viewResolver 를 거치지 않고(?) 뷰가 없이 그냥 데이터가 바로 내려간다.
    // 하지만 쓸 일이 거의 없단다 ㅠㅠ
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello1" + name;
    }
    // ㅣㄹㅇㄴ린ㅇ라

    // ResponseBody 에 객체를 전달하면
    // Json 으로 변환되어서 client 에게 전달된다.
    // @ResponseBody가 있고 객체를 전달하면 Spring 입장에서 객체가 왔네?
    // 그럼 default 로 Json으로 주겠다는게 Spring의 설정이다.
    // HttpMessageConverter 가 ViewResolver 대신 동작하여
    // 단순 문자면 httpMessageConverter 가 StringHttpMessageConverter 로
    // 객체면 MappingJackson2HttpMessageConverter 로 응답해준다.
    // MappingJackson2HttpMessageConverter 은 객체를 json 으로 convert 해주는 라이브러리이다.

    // 하지만 client 의 accept header 에 따라 응답하는 타입을 선택할 수 있다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    
    // getter setter는 alt+insert 단축키
    static class Hello {
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name=name;
        }
    }
}
