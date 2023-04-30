package com.roky.thunderspi.controllers;


import com.roky.thunderspi.entities.LibFile;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.repositories.*;
import com.roky.thunderspi.services.BlogPostServiceImpl;
import com.roky.thunderspi.services.ILibElementService;
import com.roky.thunderspi.services.LibFileServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    private BlogPostServiceImpl postService;
    @Autowired
    PostRepo postRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;

    private ILibElementService iLibElementService;
    private LibFileServiceImpl libFileService;
    @Autowired
    LibElementRepo libElementRepo;

    @Autowired
    LibFileRepository libFileRepository;

    @GetMapping("/getAll")
    public List<Post> findAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/getAll/{postId}")
    public Post findCoursesById(@PathVariable Long postId) {
        return postService.findPostsById(postId);
    }


    @PostMapping("/addPos")
    public ResponseEntity<?> addPost(@RequestParam MultipartFile file, Post post) {
        String message = "";
        try {

            libFileService.store(file);
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            LibFile libFile = new LibFile(filename, file.getContentType(), file.getBytes());
            libFileRepository.save(libFile);
            post.setFileName(filename);
            post.setFileType(file.getContentType());
            post.setIdFile(libFile.getId());
            message = "Uploaded File successfully: " + file.getOriginalFilename();
            postRepo.save(post);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

   /* @PostMapping("/addPost/{iduser}/{idsc}")
    public ResponseEntity<?> addCourse(@RequestParam MultipartFile file, Post post, @PathVariable Long iduser, @PathVariable Long idsc) {
        String message = "";
        try {
            User user = userRepo.findById(iduser).orElse(null);
            Comment comments = commentRepo.findCommentById(idsc).orElse(null);
            post.setComment((List<Comment>) comments);
            post.setUser(user);
            libFileService.store(file);
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            LibFile libFile = new LibFile(filename, file.getContentType(), file.getBytes());
            libFileRepository.save(libFile);
            post.setFileName(filename);
            post.setFileType(file.getContentType());
            post.setIdFile(libFile.getId());
            message = "Uploaded File successfully: " + file.getOriginalFilename();
            postRepo.save(post);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }*/

    @PutMapping("/update/{postId}")
    public Post editCourse(@RequestBody Post post, @PathVariable Long postId) {
        Post ExistantPost = postRepo.findPostByPostId(postId).orElseThrow(null);
        ExistantPost.setTitle((post.getTitle()));
        ExistantPost.setContent(post.getContent());

        return postRepo.saveAndFlush(ExistantPost);
    }

    @DeleteMapping("/delete/{postId}")
    public void deleteCourse(@PathVariable Long postId) {
        postService.deletePost(postId);
    }





}
