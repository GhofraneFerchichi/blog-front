package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.PostDislike;
import com.roky.thunderspi.entities.PostLike;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.exception.PostNotFoundException;
import com.roky.thunderspi.repositories.DislikeRepository;
import com.roky.thunderspi.repositories.LikeRepository;
import com.roky.thunderspi.repositories.PostRepo;
import com.roky.thunderspi.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
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
	private LikeRepository likeRepo;
	@Autowired
	private DislikeRepository dislikeRepo;
	
	@Autowired
	UserRepo utiRepo;


	@Override
	public List<Post> findAllPosts() {
		return postRepo.findAll();
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
	public Post editPost(Post post,Long postid) {
		Post p = postRepo.findById(postid).orElse(null);
		p.setContent(post.getContent());
		return postRepo.save(p);
	}

	@Override
	public void deletePost(Long postId) {
		postRepo.deleteById(postId);

	}

///////////// Like & Dislike//////////
	@Override
	public void addLike(Long idPublicaiton, Long idUser) {
	PostLike lk = new PostLike();
		Post publication = postRepo.findById(idPublicaiton).orElse(null);
		User user = utiRepo.findById(idUser).orElse(null);
		PostLike like = likeRepo.GetLike(idPublicaiton, idUser);
		PostDislike dislike = dislikeRepo.GetDislike(idPublicaiton, idUser);
		lk.setPostlike(publication);
		lk.setUtilis(user);
		if (like == null && dislike == null) {
			likeRepo.save(lk);
		} else if (like == null && dislike != null) {
			likeRepo.save(lk);
			publication.getLikes().remove(lk);
			postRepo.save(publication);
		} else {
			likeRepo.delete(like);
		}
	}

	@Override
	public void addDislike(Long idPublicaiton, Long idUser) {
		com.roky.thunderspi.entities.PostDislike lk = new com.roky.thunderspi.entities.PostDislike();
		Post publication = postRepo.findById(idPublicaiton).orElse(null);
		com.roky.thunderspi.entities.User user = utiRepo.findById(idUser).orElse(null);
		PostLike like = likeRepo.GetLike(idPublicaiton, idUser);
		PostDislike dislike = dislikeRepo.GetDislike(idPublicaiton, idUser);
		lk.setPostdislike(publication);
		lk.setUtilis(user);
		if (like == null && dislike == null) {
			dislikeRepo.save(lk);
		} else if (dislike == null && like != null) {
			dislikeRepo.save(lk);
			publication.getDislikes().remove(lk);
			postRepo.save(publication);
		} else {
			likeRepo.delete(like);
		}
	}

/////////////////////////Nombres Like , Dislikes , Comments////////////////
	@Override
	public int nbrLikeByPub(Long idPublicaiton) {
		Post publication = postRepo.findById(idPublicaiton).orElse(null);
		return publication.getLikes().size();
	}

	@Override
	public int nbrDisLikeByPub(Long idPublicaiton) {
		Post publication = postRepo.findById(idPublicaiton).orElse(null);
		return publication.getDislikes().size();
	}

}
