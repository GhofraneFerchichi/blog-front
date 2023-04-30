package com.roky.thunderspi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.roky.thunderspi.entities.PostLike;



@Repository
public interface LikeRepository extends JpaRepository<PostLike,Long> {
	@Query("SELECT l from PostLike l where l.utilis.userId= :userId  AND l.postlike.postId= :idPublication"
			)
	PostLike GetLike(@Param("userId") Long idUser,@Param("idPublication") Long idPub) ;

}
