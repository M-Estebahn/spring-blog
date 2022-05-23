package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.services.StringService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository userDao;
    private StringService stringService;
    private EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao,StringService stringService,EmailService emailService) {
        this.postsDao = postDao;
        this.userDao = userDao;
        this.stringService=stringService;
        this.emailService=emailService;
    }

    @GetMapping()
    public String allPosts(Model model) {

        List<Post> posts = postsDao.findAll();
        model.addAttribute("stringService",stringService);
        model.addAttribute("posts", posts);
        return "posts/index";

    }

    @GetMapping("/{id}")
    public String ShowOnePost(@PathVariable long id, Model model) {

        Post post = postsDao.findById(id);

        PostDetails pd= new PostDetails();

        if(post.getPostDetails()!=null){
            pd= post.getPostDetails();
        }
        model.addAttribute("postDetails",pd);
        model.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/create")

    public String showForm(Model model) {

        model.addAttribute("post",new Post());

        return "posts/create";
    }


    @PostMapping("/create")

    public String create(@ModelAttribute Post post) {
        User user = userDao.getUserById(1);
        post.setUser(user);
        postsDao.save(post);
        emailService.prepareAndSend(post,post.getTitle(),post.getBody());


        return "redirect:/posts";
    }



    @PostMapping("/img/update/{id}")
    public String updateImg(@PathVariable long id, @RequestParam("post-image") String url, @RequestParam("image-title") String title) {

        Post post = postsDao.findById(id);

        PostImage newImage = new PostImage(title, url, post);

        List<PostImage> images = post.getPost_images();

        images.add(newImage);

        post.setPost_images(images);

        postsDao.save(post);
        return ("redirect:/posts/" + id);

    }

    @PostMapping("/details/update/{id}")
    public String updateDetails(@PathVariable long id,
                                @ModelAttribute PostDetails postDetails){

        Post post = postsDao.findById(id);
        post.setPostDetails(postDetails);

            postsDao.save(post);

        return ("redirect:/posts/" + id);
    }

    @PostMapping("/search-title")
    public String searchResultsTitle(@RequestParam(name = "title") String title, Model model) {
        model.addAttribute("posts", postsDao.searchByTitleLike(title));
        return "posts/index";
    }

    @PostMapping("/search-tag")
    public String searchResultsTag(@RequestParam(name = "tag") String tagsearch, Model model) {

        List<Post> posts= postsDao.findAll();

        List<Post> newPosts= new ArrayList<>();

        for (Post post:posts) {
            if(post.getTags()!=null){
                List<Tag> tagsList=post.getTags();
                for (Tag tag:tagsList) {
                    if(tag.getName().equalsIgnoreCase(tagsearch)){
                        newPosts.add(post);
                        model.addAttribute("posts",newPosts );
                    }
                }
            }

        }

        return "posts/index";

    }
    @PostMapping("/delete")
    public String deletePost(@RequestParam("id")long id){
        postsDao.deleteById(id);
        return ("redirect:/posts");
    }
    @PostMapping("/update")
    public String updatePost(@ModelAttribute Post post){
        postsDao.save(post);
        return ("redirect:/posts");

    }

}