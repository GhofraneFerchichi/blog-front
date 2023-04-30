package com.roky.thunderspi.repositories;


import com.roky.thunderspi.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo  extends JpaRepository<Post,Long> {

    @Query("SELECT p FROM Post p  WHERE p.deleted_at = 0 ORDER BY p.created_At DESC")
    List<Post> findAllByDeleted_atIsNull();
    Post findByTitleLike(String title);
    //Post findByEmail(String email);
    Optional<Post> findPostByPostId(Long postId);
    @Query("SELECT COUNT(c), c.content FROM Post p JOIN p.comment c GROUP BY c.content")
    List<Object[]> getCommentByPost();
}
