package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad,Long> {
    Object findByTitle(String title);

    @Query("from Ad ads where ads.title like %:term%")
    List<Ad> searchByTitleLike(@Param("term") String term);
}
