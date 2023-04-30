package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.CategoryProduct;
import com.roky.thunderspi.entities.Comment;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.repositories.CommentRepo;
import com.roky.thunderspi.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService{

    @Autowired
    CommentRepo commentRepo;

    PostRepo postRepo;

    @Override
    public List<Comment> retrieveAllCommentaires() {
        return commentRepo.findAll();
    }

    @Override
    public Comment addPost(Comment c, Long postId) {
        return null;
    }


    @Override
    public Comment addComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Override
    public void deleteCommentaire(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public Comment updateCommentaire(Comment u) {
        commentRepo.save(u);
        return u;
    }

    @Override
    public Comment retrieveCommentaire(Long id) {
        Comment commentaire = commentRepo.findById(id).orElse(null);
        return commentaire;
    }
}
