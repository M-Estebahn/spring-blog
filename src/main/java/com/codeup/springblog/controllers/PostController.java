package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/posts")
public class PostController {



    @GetMapping()
    public String allPosts(Model model){
        Post post1=new Post("first post", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad architecto blanditiis cupiditate dolor dolore eligendi eveniet, ex fuga illo incidunt labore nam natus numquam qui quis, quo sed tempore! Amet.");
        Post post2= new Post("second post","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad architecto blanditiis cupiditate dolor dolore eligendi eveniet, ex fuga illo incidunt labore nam natus numquam qui quis, quo sed tempore! Amet.");
        ArrayList<Post> posts=new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        model.addAttribute("posts",posts);



        return"posts/index";
    }




    @GetMapping("/{title}")
    public String ShowOnePost(@PathVariable String title,Model model){
        Post newPost= new Post(title,"Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad architecto blanditiis cupiditate dolor dolore eligendi eveniet, ex fuga illo incidunt labore nam natus numquam qui quis, quo sed tempore! Amet.");
        model.addAttribute("post",newPost);

        return "posts/show";
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
