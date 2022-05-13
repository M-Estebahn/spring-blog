package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@RequestMapping("/hello")
public class HelloController {
    private final String[] names = {"Omar", "Mateo", "Liam", "Emma", "Reem", "Sofia", "James", "Agustin", "Antonella", "Youssef", "Abigail", "Zahra"};

    @GetMapping()
    @ResponseBody
    public String hello(){
        return "Hello World!";
    }
    @GetMapping("/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name){
        return "Hello "+name+" !";
    }
    @GetMapping("/random")
    @ResponseBody
    public String randomGreeting(){
        int rnd = new Random().nextInt(names.length);
        return "hello " + names[rnd]+"!";
    }
    @GetMapping("/{name1}/{name2}")
    @ResponseBody
    public String greetTwo(@PathVariable String name1,@PathVariable String name2){
        return "Hello, "+ name1+" and "+name2+"!";
    }
    @GetMapping("{name1}/to/{name2}")
    @ResponseBody
    public String oneGreetsAnother(@PathVariable String name1,@PathVariable String name2){
        return name1+" says Hello to "+name2+"!";
    }

}
