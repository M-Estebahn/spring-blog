package com.codeup.springblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;
    @OneToOne(cascade = CascadeType.ALL)
    private PostDetails postDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImage> post_images;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="post_tags",
            joinColumns={@JoinColumn(name="post_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")}
    )
    private List<Tag> tags;





    public Post(){};

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public PostDetails getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(PostDetails postDetails) {
        this.postDetails = postDetails;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public List<PostImage> getPost_images() {
        return post_images;
    }

    public void setPost_images(List<PostImage> post_images) {
        this.post_images = post_images;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
