package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostDetails;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository userDao;


    public PostController(PostRepository postDao, UserRepository userDao){
        this.postsDao=postDao;
        this.userDao = userDao;
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

    @GetMapping("/details/{id}")
    public String getHistoryOfDetails(@PathVariable long id,Model model){
        Post post=postsDao.findById(id);
        PostDetails postDetails=post.getPostDetails();
        model.addAttribute("post",post);
        model.addAttribute("postDeets",postDetails);

        return("posts/history");
    }

    @PostMapping("/details/update/{id}")
    public String updatePosts(@PathVariable long id, @RequestParam("post-image") String url,@RequestParam("title")String title){

        Post post= postsDao.findById(id);

        PostImage newImage= new PostImage(title,url,post);

        List<PostImage> images= post.getPost_images();

        images.add(newImage);

        post.setPost_images(images);

        postsDao.save(post);
        return ("redirect:/posts/details/"+id);

    }


}
