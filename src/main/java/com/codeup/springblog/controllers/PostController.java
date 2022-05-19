package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
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


    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postsDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping()
    public String allPosts(Model model) {

        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";

    }

    @GetMapping("/{id}")
    public String ShowOnePost(@PathVariable long id, Model model) {

        Post post = postsDao.findById(id);

        model.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/create")

    public String showForm() {
        return "posts/create";
    }


    @PostMapping("/create")

    public String create(@RequestParam("title") String title, @RequestParam("body") String body) {
        User user = userDao.getUserById(1);
        Post post = new Post(title, body);
        post.setUser(user);
        postsDao.save(post);

        return "redirect:/posts";
    }

//    @GetMapping("/details/{id}")
//    public String getHistoryOfDetails(@PathVariable long id,Model model){
//        Post post=postsDao.findById(id);
//        PostDetails postDetails=post.getPostDetails();
//        model.addAttribute("post",post);
//        model.addAttribute("postDeets",postDetails);
//
//        return("posts/history");
//    }

    @PostMapping("/img/update/{id}")
    public String updateImg(@PathVariable long id, @RequestParam("post-image") String url, @RequestParam("title") String title) {

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
                                @RequestParam("isAwesome") boolean isAwesome,
                                @RequestParam("history") String history,
                                @RequestParam("topic") String topic) {

        Post post = postsDao.findById(id);

        if (post.getPostDetails() != null) {
            PostDetails pd = post.getPostDetails();

            pd.setAwesome(isAwesome);
            pd.setHistoryOfPost(history);
            pd.setTopicDescription(topic);

            post.setPostDetails(pd);

            postsDao.save(post);

        }
        if (post.getPostDetails() == null) {
            PostDetails pd = new PostDetails();

            pd.setAwesome(isAwesome);
            pd.setHistoryOfPost(history);
            pd.setTopicDescription(topic);

            post.setPostDetails(pd);

            postsDao.save(post);
        }
        return ("redirect:/posts/" + id);
    }

    @PostMapping("/search-title")
    public String searchResultsTitle(@RequestParam(name = "title") String title, Model model) {
        model.addAttribute("posts", postsDao.searchByTitleLike(title));
        return "posts/index";
    }

    @PostMapping("/search-tag")
    public String searchResultsTag(@RequestParam(name = "tag") String tag, Model model) {



        model.addAttribute("posts" );
        return "posts/index";

    }

}