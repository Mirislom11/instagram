package com.comapany.service;

import com.comapany.dto.PostDTO;
import com.comapany.entity.PostEntity;
import com.comapany.entity.ProfileEntity;
import com.comapany.mapping.PostInfo;
import com.comapany.mapping.PostTitle;
import com.comapany.mapping.TitleAndCreatedDatePost;
import com.comapany.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfileService profileService;
    public PostDTO createNewPost (PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setContent(postDTO.getContent());
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setCreatedDate(LocalDate.now());
        postEntity.setProfile(profileService.getProfileEntityById(postDTO.getProfileId()));
        postRepository.save(postEntity);
        postDTO.setId(postEntity.getId());
        postDTO.setCreatedDate(postEntity.getCreatedDate());
        return postDTO;
    }

    public PostDTO getPostDTOById (Integer id) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);
        if (optionalPostEntity.isPresent()) {
            return toDTO(optionalPostEntity.get());
        }
        throw  new RuntimeException("Post not found");
    }
    public PostEntity getPostEntityById (Integer id) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);
        if (optionalPostEntity.isPresent()) {
            return optionalPostEntity.get();
        }
        throw new RuntimeException("Post entity not found");
    }

    public List<PostDTO> getListOfPostDTOByProfileId (Integer id) {
        List<PostEntity> postEntityList = postRepository.getListOfPostEntityByProfileId(id);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity : postEntityList) {
            postDTOList.add(toDTO(postEntity));
        }
        return postDTOList;
    }
    public TitleAndCreatedDatePost getTitleAndCreatedDateByPostId(Integer id){
        Optional<TitleAndCreatedDatePost> titleAndCreatedDatePost = postRepository.getPostByPostId(id);
        if (titleAndCreatedDatePost.isPresent()) {
            return titleAndCreatedDatePost.get();
        }
        throw new RuntimeException("Post entity not found ");
    }
    public List<PostTitle> getPostTitleByProfileId (Integer profileId) {
        List<PostTitle> postTitleList = postRepository.getPostTitleListByProfileId(profileId);
        return postTitleList;
    }
    public List<PostDTO> getLastPostsByProfileId (Integer id) {
        List<PostEntity> postEntityList = postRepository.getLastPostsByProfileId(id);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity : postEntityList) {
            postDTOList.add(toDTO(postEntity));
        }
        return postDTOList;
    }
    public long getCountOfPostsByProfileId(Integer id){
        Optional<ProfileEntity> optionalProfileEntity = Optional.ofNullable(profileService.getProfileEntityById(id));
        long count =  postRepository.countByProfile(optionalProfileEntity.get());
        return count;
    }

    public long getTodayPostByProfile (Integer id, LocalDate localDate) {
      long count = postRepository.countByProfile_IdAndCreatedDate_Date(id, localDate);
      return count;
    }
    public List<PostInfo> getPostInfoByProfileId (Integer id) {
        List<PostInfo> postInfoList = postRepository.getPostInfoByProfileId(id);
        return postInfoList;
    }

    public List<PostInfo> getAllPostInfo(){
        List<PostInfo> postInfoList = postRepository.getAllPostInfo();
        return postInfoList;
    }

    public List<PostDTO> getTodayPosts (LocalDate localDate) {
        List<PostEntity> postEntityList = postRepository.getTodayPosts(localDate);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity : postEntityList) {
            postDTOList.add(toDTO(postEntity));
        }
        return postDTOList;
    }
    public List<PostDTO> getAllPosts () {

        List<PostEntity> postEntityList = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        for (PostEntity postEntity : postEntityList) {
            postDTOList.add(toDTO(postEntity));
        }
        return postDTOList;
    }

    public String deletePostById (Integer postId) {
        postRepository.deleteById(postId);
        return "Successfully deleted";
    }

    public String changePostById (Integer id, PostDTO postDTO) {
        Optional<PostEntity>  optionalPostEntity = postRepository.findById(id);
        if (optionalPostEntity.isPresent()) {
            PostEntity postEntity = optionalPostEntity.get();
            postEntity.setTitle(postDTO.getTitle());
            postEntity.setContent(postDTO.getContent());
            postRepository.save(postEntity);
        }
        return "Successfully changed";
    }


    private PostDTO toDTO (PostEntity postEntity) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postEntity.getId());
        postDTO.setTitle(postEntity.getTitle());
        postDTO.setContent(postEntity.getContent());
        postDTO.setCreatedDate(postEntity.getCreatedDate());
        postDTO.setProfileId(postEntity.getProfile().getId());
        return postDTO;
    }
}
