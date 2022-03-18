package com.comapany.controller;

import com.comapany.dto.ProfileDTO;
import com.comapany.entity.ContactEntity;
import com.comapany.entity.ProfileEntity;
import com.comapany.repository.ProfileRepository;
import com.comapany.service.ContactService;
import com.comapany.service.ProfileService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @PostMapping("/create")
    public ResponseEntity<?> createProfile (@RequestBody ProfileDTO profileDTO) {
       return ResponseEntity.ok(profileService.createProfile(profileDTO));
    }
    @GetMapping("/get-by-id/{profileId}")
    public ResponseEntity<?> getProfileById (@PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(profileService.getProfileDTOById(profileId));
    }
    @GetMapping("/get/ALL")
    public ResponseEntity<?> getAllProfile () {
        return ResponseEntity.ok(profileService.getAllProfile());
    }
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteProfileById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.deleteById(id));
    }
    @GetMapping("/get-by-contactId/{id}")
    public ResponseEntity<?> getProfileByContactId (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.getProfileByContactId(id));
    }
    @GetMapping("/get-profile-phone-by-profileId/{id}")
    public ResponseEntity<?> getProfileAndPhoneById (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.getProfileAndContactByProfileId(id));
    }
    @GetMapping("/get-profile-phone-by-contactId/{id}")
    public ResponseEntity<?> getProfileAndPhoneByCommentId (@PathVariable("id") Integer id) {
        return  ResponseEntity.ok(profileService.getProfileAndContactByContactId(id));
    }
    @PutMapping("change-by-id/{id}")
    public ResponseEntity<?> changeProfileById (@PathVariable("id")Integer id, @RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.changeProfileById(id, profileDTO));
    }


}
