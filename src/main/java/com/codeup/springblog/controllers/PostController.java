package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {
    @GetMapping
    @ResponseBody
    public String allPosts(){
        return"posts index page";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String ShowOnePost(@PathVariable long id){
        return "view an individual post";
    }

    @GetMapping("/create")
    @ResponseBody
    public String showForm(){
        return "view form for creating a post";
    }
    @PostMapping("/create")
    @ResponseBody
    public String create(){
        return "create post";
    }


}
