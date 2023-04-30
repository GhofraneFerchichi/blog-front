package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.CategoryProduct;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> retrieveAllCommentaires();

    Comment addPost(Comment c,Long postId);
    public Comment addComment(Comment comment);


    void deleteCommentaire(Long id);
    Comment updateCommentaire(Comment u);
    Comment retrieveCommentaire(Long id);
}
