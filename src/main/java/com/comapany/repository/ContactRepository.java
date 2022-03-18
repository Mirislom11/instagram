package com.comapany.repository;


import com.comapany.entity.ContactEntity;
import com.comapany.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/*    1. Get contact by profileId.
    2. Get contact by profileEntity (Only in service and repository)*/

public interface ContactRepository  extends JpaRepository<ContactEntity, Integer> {
    @Override
    Optional<ContactEntity> findById(Integer integer);
    Optional<ContactEntity> findByPhone(String phone);

    @Query("SELECT c FROM ContactEntity c INNER JOIN c.profile p WHERE p.id = :profileId")
    Optional<ContactEntity> getContactByProfileId(@Param("profileId") Integer profileId);

    @Query("SELECT c FROM ContactEntity c WHERE c.profile = :requestProfile")
    Optional<ContactEntity> getContactByProfile(@Param("requestProfile")ProfileEntity profileEntity);
}
