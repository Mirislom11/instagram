package com.comapany.repository;

import com.comapany.entity.CommentEntity;
import com.comapany.entity.ProfileEntity;
import com.comapany.mapping.CommentAndProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/*1. Get post comment list (postga yozilgan commentlarni olish) with pagination
    2. Get post list by profile (profile yozgan barcha commentlarni olish)
    3. Get list of (comment id, comment content, profile id, profile name) by profile id
    4. Get Profile by comment id
    5. Get profile comment count
    6. Get post comment count (by post id)
    7. Get post last comment (by post id)
    8. Get  post(id,title),comment(id,content, created date)
        and profile (id,name) by profile id with pagination
    9. Get post list in given period (fromDate, toDate)*/


public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query("SELECT c FROM CommentEntity c INNER JOIN c.post p WHERE p.id = :postId")
    List<CommentEntity> getCommentByPostId(@Param("postId") Integer postId);
    @Query("SELECT  c FROM CommentEntity  c inner join c.profile p WHERE p.id = :profileId")
    List<CommentEntity> getCommentByProfileId(@Param("profileId") Integer profileId);
    @Query("SELECT new com.comapany.mapping.CommentAndProfile(c.id, c.content, p.id, p.name) " +
            "FROM CommentEntity  c INNER JOIN c.profile p WHERE p.id = :profileId")
    List<CommentAndProfile> getCommentAndProfileByProfileId(@Param("profileId") Integer profileId);
    @Query("SELECT p FROM CommentEntity c INNER JOIN c.profile p WHERE c.id = :commentId")
    Optional<ProfileEntity> getProfileByCommentId(@Param("commentId") Integer commentId);
    long countCommentEntitiesByProfile(ProfileEntity profileEntity);
}
