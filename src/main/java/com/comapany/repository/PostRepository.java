package com.comapany.repository;

import com.comapany.entity.PostEntity;
import com.comapany.entity.ProfileEntity;
import com.comapany.mapping.PostInfo;
import com.comapany.mapping.PostTitle;
import com.comapany.mapping.TitleAndCreatedDatePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/* 1. Get profile post list by profileId with pagination
    2. Get list of profile id, profile name and post title by profile id with pagination
    3. Get post tile and created date by post id
    4. Get post tile list by profile id
    5. Get last 5 posts of profile by profile id.
    6. Get profile post count by profile id
    7. Get Profile today's post count by profile id
    8. Get post id, profile id and post creted date by profile id
    9. Get list of (post id, profile id and post created date)
    10. Get today's post list (only post info)
    11. Get today's post list (post id, post title, profile id, profile name)*/

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    @Query("SELECT ps FROM PostEntity ps INNER JOIN ps.profile p WHERE p.id = :profileId")
    List<PostEntity> getListOfPostEntityByProfileId(@Param("profileId") Integer profileId);
    @Query("SELECT new com.comapany.mapping.TitleAndCreatedDatePost(ps.title, ps.createdDate) FROM PostEntity ps WHERE ps.id = :postId")
    Optional<TitleAndCreatedDatePost> getPostByPostId(@Param("postId") Integer postId);
    @Query("SELECT new com.comapany.mapping.PostTitle(ps.title) FROM PostEntity ps " +
            "INNER JOIN ps.profile p WHERE p.id = :profileId")
    List<PostTitle> getPostTitleListByProfileId(@Param("profileId") Integer profileId);
    @Query(name = "SELECT * FROM posts WHERE profile_id = :profileId ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
    List<PostEntity> getLastPostsByProfileId(@Param("profileId") Integer profileId);
    long countByProfile(ProfileEntity profileEntity);
    @Query(value = "SELECT count(*) FROM posts p WHERE p.profile_id = ?1 AND " +
            "CAST(p.created_date AS DATE) = ?2", nativeQuery = true)
    int countByProfile_IdAndCreatedDate_Date(Integer profileId, LocalDate date);
    @Query("SELECT new com.comapany.mapping.PostInfo(ps.id, p.id, ps.createdDate) FROM PostEntity ps INNER JOIN  ps.profile p WHERE p.id = :profileId")
    List<PostInfo> getPostInfoByProfileId(@Param("profileId") Integer profileId);
    @Query("SELECT new com.comapany.mapping.PostInfo(ps.id, p.id, ps.createdDate) FROM PostEntity ps INNER JOIN ps.profile p")
    List<PostInfo> getAllPostInfo();
    @Query(value = "SELECT * FROM posts p WHERE " +
            "CAST(p.created_date AS DATE) = ?1", nativeQuery = true)
    List<PostEntity> getTodayPosts(LocalDate localDate);
}
