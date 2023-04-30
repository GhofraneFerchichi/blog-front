package com.roky.thunderspi.services;

import com.roky.thunderspi.dto.PostDto;
import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.exception.PostNotFoundException;
import com.roky.thunderspi.repositories.CourseRepo;
import com.roky.thunderspi.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BlogPostServiceImpl implements IBlogPostService {


    @Autowired
    private PostRepo postRepo;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public List<Post> findAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public PostDto readSinglePost(Long id) {
        return null ;
    }

    @Override
    public Post getSinglePost(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new PostNotFoundException("For id" + id));


        return post;
    }

    @Override
    public Post findPostsById(Long postId) {
        return postRepo.findById(postId).orElse(null);
    }

    @Override
    public Post addPost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public Post editPost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepo.deleteById(postId);

    }


}
