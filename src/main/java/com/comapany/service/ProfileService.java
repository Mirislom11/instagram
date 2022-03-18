package com.comapany.service;

import com.comapany.dto.ProfileContactDTO;
import com.comapany.dto.ProfileDTO;
import com.comapany.entity.ContactEntity;
import com.comapany.entity.ProfileEntity;
import com.comapany.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.sax.SAXResult;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ContactService contactService;

    public ProfileEntity getProfileEntityById (Integer id) {
        Optional<ProfileEntity> optionalProfile= profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            return optionalProfile.get();
        }
        throw new RuntimeException("Profile not found");
    }
    public ProfileDTO createProfile (ProfileDTO profileDTO) {
        ContactEntity contactEntity = contactService.getContactEntityById(profileDTO.getContactId());
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setContact(contactEntity);
        ProfileEntity profile =  profileRepository.save(profileEntity);
        profileDTO.setId(profile.getId());
        return profileDTO;
    }
    public ProfileDTO getProfileByContactId (Integer contactId) {
        ProfileEntity profileEntity = profileRepository.getProfileByContactId(contactId);
        return toDTO(profileEntity);
    }

    public ProfileContactDTO getProfileAndContactByProfileId (Integer profileId) {
         ProfileContactDTO profileContactDTO = profileRepository.getProfileContactByProfileId(profileId);
        return profileContactDTO;
    }
    public ProfileContactDTO getProfileAndContactByContactId (Integer contactId) {
        ProfileContactDTO profileContactDTO = profileRepository.geteProfileContactByContactId(contactId);
        return profileContactDTO;
    }

    public ProfileDTO getProfileDTOById(Integer id) {
        Optional<ProfileEntity> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            return toDTO(optionalProfile.get());
        }
        throw new RuntimeException("Profile not found");
    }
    public List<ProfileDTO> getAllProfile () {
        List<ProfileDTO> profileDTOList = new LinkedList<>();
        List<ProfileEntity> profileEntityList = profileRepository.findAll();
        for (ProfileEntity profileEntity : profileEntityList) {
            profileDTOList.add(toDTO(profileEntity));
        }
        return profileDTOList;
    }

    public String deleteById (Integer id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return "Successfully deleted";
        }
        throw new RuntimeException("Deleted failed");
    }
    public String changeProfileById (Integer profileId, ProfileDTO profileDTO) {
        ContactEntity contactEntity = contactService.getContactEntityById(profileDTO.getContactId());
        Optional<ProfileEntity> optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            ProfileEntity profileEntity = optionalProfile.get();
            profileEntity.setName(profileDTO.getName());
            profileEntity.setSurname(profileDTO.getSurname());
            profileEntity.setContact(contactEntity);
            profileRepository.save(profileEntity);
        }

        return "Successfully changed";
    }

    private ProfileDTO toDTO (ProfileEntity profileEntity) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profileEntity.getId());
        profileDTO.setName(profileEntity.getName());
        profileDTO.setSurname(profileEntity.getSurname());
        profileDTO.setContactId(profileEntity.getContact().getId());
        return profileDTO;
    }
}
