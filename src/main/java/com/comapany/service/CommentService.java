package com.comapany.service;

import com.comapany.dto.CommentDTO;
import com.comapany.dto.ContactDTO;
import com.comapany.entity.CommentEntity;
import com.comapany.entity.PostEntity;
import com.comapany.entity.ProfileEntity;
import com.comapany.repository.CommentRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private ProfileService profileService;
    public CommentDTO createComment (CommentDTO commentDTO) {
        PostEntity postEntity = postService.getPostEntityById(commentDTO.getPostId());
        ProfileEntity profileEntity = profileService.getProfileEntityById(commentDTO.getProfileId());
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setPost(postEntity);
        commentEntity.setProfile(profileEntity);
        commentEntity.setCreatedDate(LocalDate.now());
        commentRepository.save(commentEntity);
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCreatedDate(commentEntity.getCreatedDate());
        return commentDTO;
    }
    public CommentDTO getCommentById (Integer id) {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            return toDTO(optionalComment.get());
        }
        throw  new RuntimeException("Comment not found");
    }
    public List<CommentDTO> getAllComments  () {
        List<CommentEntity> commentEntityList = commentRepository.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            commentDTOList.add(toDTO(commentEntity));
        }
        return commentDTOList;
    }
    public String deleteById (Integer id) {
        commentRepository.deleteById(id);
        return "Successfully deleted";
    }
    public String changeCommentById (Integer  id, CommentDTO commentDTO) {
        Optional<CommentEntity> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            CommentEntity commentEntity = optionalComment.get();
            commentEntity.setContent(commentDTO.getContent());
            commentRepository.save(commentEntity);
        }
        return "Successfully chaged";
    }

    private CommentDTO toDTO (CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setContent(commentEntity.getContent());
        commentDTO.setCreatedDate(commentEntity.getCreatedDate());
        commentDTO.setPostId(commentEntity.getPost().getId());
        commentDTO.setProfileId(commentEntity.getProfile().getId());
        return commentDTO;
    }
}
