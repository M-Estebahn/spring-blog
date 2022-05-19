package com.codeup.springblog.repositories;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository <Post,Long> {

    Post findById(long id);

     @Query(" from Post p where p.title like %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);

//     Post findBy

}
