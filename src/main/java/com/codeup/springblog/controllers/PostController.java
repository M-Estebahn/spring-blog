package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostDetails;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postDao){
        this.postsDao=postDao;
    }

    @GetMapping()
    public String allPosts(Model model){

        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";

    }

    @GetMapping("/{id}")
    public String ShowOnePost(@PathVariable long id,Model model){

      Post  post=postsDao.findById(id);

      model.addAttribute("post",post);

        return "posts/show";
    }

    @GetMapping("/create")

    public String showForm(){
        return "posts/create";
    }


    @PostMapping("/create")

    public String create(@RequestParam("title")String title,@RequestParam("body")String body){
        Post post=new Post(title,body);
        postsDao.save(post);

        return "redirect:/posts";
    }

    @GetMapping("/history/{id}")
    public String getHistoryOfDetails(@PathVariable long id,Model model){
        Post post=postsDao.findById(id);
        PostDetails postDetails=post.getPostDetails();
        model.addAttribute("post",post);
        model.addAttribute("postDeets",postDetails);
        return("posts/history");
    }


}
