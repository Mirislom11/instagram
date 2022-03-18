package com.comapany;

import com.comapany.entity.ContactEntity;
import com.comapany.entity.ProfileEntity;
import com.comapany.service.ContactService;
import com.comapany.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InstagramApplicationTests {
    @Autowired
    ContactService contactService;
    @Autowired
    ProfileService profileService;
    @Test
    void contextLoads() {
        ProfileEntity profile = profileService.getProfileEntityById(2);
        ContactEntity contactEntity = contactService.getContactEntityByProfile(profile);
        System.out.println(contactEntity);
    }

}
