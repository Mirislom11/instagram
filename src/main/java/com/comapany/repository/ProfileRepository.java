package com.comapany.repository;

import com.comapany.dto.ProfileContactDTO;
import com.comapany.entity.PostEntity;
import com.comapany.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    @Query("SELECT p FROM ProfileEntity p INNER JOIN p.contact c WHERE c.id = :contactId")
    ProfileEntity getProfileByContactId(@Param("contactId") Integer contactId);
    @Query("SELECT new com.comapany.dto.ProfileContactDTO(p.id, p.name, c.phone) FROM ProfileEntity  " +
            "p inner join p.contact c WHERE p.id = :profileId")
    ProfileContactDTO getProfileContactByProfileId(@Param("profileId") Integer profileId);
    @Query("SELECT new com.comapany.dto.ProfileContactDTO(p.id, p.name, c.phone) FROM ProfileEntity p INNER JOIN p.contact c WHERE c.id = :contactId")
    ProfileContactDTO geteProfileContactByContactId(@Param("contactId") Integer contactId);
}
