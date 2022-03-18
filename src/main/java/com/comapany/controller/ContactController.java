package com.comapany.controller;

import com.comapany.dto.ContactDTO;
import com.comapany.entity.ContactEntity;
import com.comapany.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;
    @PostMapping("/create")
    public ResponseEntity<?> createContact (@RequestBody ContactDTO contactDTO) {
        return ResponseEntity.ok(contactService.createContact(contactDTO));
    }
    @GetMapping("/get/{ID}")
    public ResponseEntity<?> getContactById (@PathVariable("ID") Integer contactId ) {
        return ResponseEntity.ok(contactService.getContactById(contactId));
    }
    @GetMapping("/get/ALL")
    public ResponseEntity<?> getAllContacts () {
        return ResponseEntity.ok(contactService.getAllContact());
    }

    @GetMapping("/get-by-phone/{phone}")
    public ResponseEntity<?> getContactByPhone (@PathVariable("phone") String phone) {
        return ResponseEntity.ok(contactService.getContactByPhone(phone));
    }
    @GetMapping("/get-by-profileId/{profileId}")
    public ResponseEntity<?> getContactByProfileId (@PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(contactService.getContactByProfileId(profileId));
    }
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteById (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactService.deleteById(id));
    }

    @PutMapping("/change-phone-by-id/{id}")
    public ResponseEntity<?> changeById (@RequestBody ContactDTO contactDTO, @PathVariable("id") Integer id) {
        return  ResponseEntity.ok(contactService.changeById(contactDTO, id));
    }
}
