package com.roky.thunderspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.PostLike;
import com.roky.thunderspi.entities.User;



@Repository
public interface LikeRepository extends JpaRepository<PostLike,Long> {/*
	@Query("SELECT l from PostLike l where l.utilis.id= :userId  AND l.postlike.postId= :idPublication"
			)
	PostLike GetLike(@Param("userId") Long idUser,@Param("idPublication") Long idPub) ;*/

	PostLike findByPostlikeAndUtilis(Post idPublicaiton, User idUser);

}
