package com.codeup.springblog.models;


import javax.persistence.*;

@Entity
@Table(name="post_details")
public class PostDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean isAwesome;
    @Column(columnDefinition = "text")
    private String historyOfPost;

    @Column
    private String topicDescription;


}
