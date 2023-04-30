package com.roky.thunderspi.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roky.thunderspi.entities.Comment;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.CommentRepo;
import com.roky.thunderspi.repositories.PostRepo;
import com.roky.thunderspi.repositories.UserRepo;


@Service
public class CommentServiceImpl implements ICommentService{
	@Autowired
	CommentRepo cmtRepo;
	@Autowired
	PostRepo postrepo;
	@Autowired
	UserRepo userrepo;
	@Override
	public List<Comment> retrieveAllCommentaires() {
		return cmtRepo.findAll();
	}

	@Override
	public Comment addComment(Comment comment, Long postId,Long iduser) {
		cmtRepo.save(comment);
		Post p =postrepo.findById(postId).orElse(null);
		User u = userrepo.findById(iduser).orElse(null);
		comment.setPost(p);
		comment.setUser(u);
		return cmtRepo.save(comment);
	}

	@Override
	public void deleteCommentaire(Long id) {

		cmtRepo.deleteById(id);
	}

	@Override
	public Comment updateCommentaire(Comment cmt,Long cmtid) {
		Comment c =cmtRepo.findById(cmtid).orElse(null);
		c.setContent(cmt.getContent());
		return cmtRepo.save(c);
	}

	@Override
	public Comment retrieveCommentaire(Long id) {
		return cmtRepo.findById(id).orElse(null);
	}

	
}
