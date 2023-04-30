package com.roky.thunderspi.services;

import com.roky.thunderspi.dto.PostDto;
import com.roky.thunderspi.entities.CategoryProduct;
import com.roky.thunderspi.entities.Comment;
import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.Post;

import java.util.List;

public interface IBlogPostService {

    List<Post> findAllPosts();
    PostDto readSinglePost(Long postId);
    Post getSinglePost(Long postId);

    Post findPostsById(Long postId);
    Post addPost(Post post);
    Post editPost(Post post);
    void deletePost(Long postId);


}
