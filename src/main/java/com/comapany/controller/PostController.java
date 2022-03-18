package com.comapany.controller;

import com.comapany.dto.PostDTO;
import com.comapany.mapping.PostInfo;
import com.comapany.service.PostService;
import org.apache.catalina.LifecycleState;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/create")
    public ResponseEntity<?> createPost (@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.createNewPost(postDTO));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.getPostDTOById(id));
    }
    @GetMapping("/get-by-profile-id/{id}")
    public ResponseEntity<?> getListOfPostByProfileId (@PathVariable("id") Integer id) {
        return  ResponseEntity.ok(postService.getListOfPostDTOByProfileId(  id));
    }
    @GetMapping("/get/ALL")
    public ResponseEntity<?> getAll () {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/get-postTitle-by-profileId/{id}")
    public ResponseEntity<?> getPostTitleByProfileId (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.getPostTitleByProfileId(id));
    }
    @GetMapping("/get-last-post-by-profileId/{id}")
    public ResponseEntity<?> getLastPostsByProfileId (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.getLastPostsByProfileId(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.deletePostById(id));
    }
    @GetMapping("/get-title-createdDate-postId/{id}")
    public ResponseEntity<?> getByTitleAndCreatedDateByPostId (@PathVariable("id") Integer postId) {
        return ResponseEntity.ok(postService.getTitleAndCreatedDateByPostId(postId));
    }
    @GetMapping("/get-count-post-by-profileId/{id}")
    public ResponseEntity<?> getCountOfPostByProfileId (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(postService.getCountOfPostsByProfileId(id));
    }
    @GetMapping("/get-post-info-by-profileId/{profileId}")
    public ResponseEntity<?> getPostInfo (@PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(postService.getPostInfoByProfileId(profileId));
    }
    @GetMapping("/get-by-profileId-today/{id}/{createdDate}")
    public ResponseEntity<?> getCountOfPostTodayByProfileId(@PathVariable("id") Integer id, @PathVariable("createdDate")String createdDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(createdDate, formatter);
    Long count = postService.getTodayPostByProfile(id, localDate);
    return ResponseEntity.ok(count);
    }
    @GetMapping("/get-today-posts/{createdDate}")
    public ResponseEntity<?> getTodayPosts (@PathVariable("createdDate") String createdDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(createdDate, formatter);
        return ResponseEntity.ok(postService.getTodayPosts(localDate));
    }
    @GetMapping("/get-all-post-info")
    public ResponseEntity<?> getAllPostInfo () {
        List<PostInfo> postInfoList = postService.getAllPostInfo();
        return ResponseEntity.ok(postInfoList);
    }
    @PutMapping("/change-by-id/{id}")
    public ResponseEntity<?> changeById (@PathVariable("id") Integer id, @RequestBody PostDTO postDTO) {
     return ResponseEntity.ok(postService.changePostById(id, postDTO));
    }
}
