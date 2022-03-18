package com.comapany.controller;

import com.comapany.dto.CommentDTO;
import com.comapany.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/create")
    public ResponseEntity<?> createComment (@RequestBody CommentDTO commentDTO) {
        return  ResponseEntity.ok(commentService.createComment(commentDTO));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getComment (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }
    @GetMapping("/get/ALL")
    public ResponseEntity<?> getAllComments () {
        return ResponseEntity.ok(commentService.getAllComments());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commentService.deleteById(id));
    }
    @PutMapping("/change-by-id/{id}")
    public ResponseEntity<?> changeById(@PathVariable("id") Integer id, @RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.changeCommentById(id, commentDTO));
    }
}
