package com.roky.thunderspi.controllers;

import com.roky.thunderspi.dto.PostDto;
import com.roky.thunderspi.entities.*;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.repositories.*;
import com.roky.thunderspi.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private CommentServiceImpl commentaireService;
    private IBlogPostService iBlogPostService;
    private UserServiceImpl userService;

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

    @PostMapping("/addPost/{iduser}/{idsc}")
    public ResponseEntity<?> addCourse( Comment comment, @PathVariable Long iduser, @PathVariable Long idsc) {
        String message = "";
        try {
            User user = userRepo.findById(iduser).orElse(null);
            Post post = postRepo.findById(idsc).orElse(null);
            comment.setPost(post);
            comment.setUser(user);

            commentRepo.save(comment);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }



    @GetMapping("/getall")
    @ResponseBody
    public List<Comment> getAll() {
        List<Comment> comments = commentaireService.retrieveAllCommentaires();
        return comments;
    }
    @PostMapping("/add/{postId}")

    public ResponseEntity<Comment> addComment(@RequestBody Comment c, @PathVariable Long postId) {
        Comment comment = commentaireService.addPost(c, postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/retrieve-commentaire/{commentaire-id}")
    @ResponseBody
    public Comment retrieveCommentaire(@PathVariable("commentaire-id") Long commentaireId) {
        return commentaireService.retrieveCommentaire(commentaireId);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment c) {
        Comment updateComment = commentaireService.updateCommentaire(c);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }
    // http://localhost:8081/remove-commentaire/{commentaire-id}
    @DeleteMapping("/remove-client/{commentaire-id}")
    @ResponseBody
    public void removeCommentaire(@PathVariable("commentaire-id") Long commentaireId) {
        commentaireService.deleteCommentaire(commentaireId);
    }

    // http://localhost:8081/modify-commentaire

}
