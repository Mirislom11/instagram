package com.comapany.service;



import com.comapany.dto.ContactDTO;
import com.comapany.entity.ContactEntity;
import com.comapany.entity.ProfileEntity;
import com.comapany.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public ContactDTO createContact (ContactDTO contactDTO) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setPhone(contactDTO.getPhone());
        contactEntity = contactRepository.save(contactEntity);
        contactDTO.setId(contactEntity.getId());
        return contactDTO;
    }

    public ContactDTO getContactById (Integer contactId ) {
         Optional<ContactEntity> optionalContact = contactRepository.findById(contactId);
        if (optionalContact.isPresent()) {
            return toDTO(optionalContact.get());
        }
        throw  new RuntimeException("Contact not found");
    }

    public ContactEntity getContactEntityById (Integer contactId) {
        Optional<ContactEntity> optionalContact = contactRepository.findById(contactId);
        if (optionalContact.isPresent()) {
            return optionalContact.get();
        }
        throw new RuntimeException("Contact Entity not found");
    }
    public ContactEntity getContactEntityByProfile (ProfileEntity profile) {
        Optional<ContactEntity> optionalContact = contactRepository.getContactByProfile(profile);
        if (optionalContact.isPresent()) {
            return optionalContact.get();
        }
        throw new RuntimeException("Contact not found");
    }

    public List<ContactDTO> getAllContact () {
        List<ContactEntity> contactEntityList = contactRepository.findAll();
        List<ContactDTO> contactDTOList = new LinkedList<>();
        for (ContactEntity contactEntity : contactEntityList) {
            contactDTOList.add(toDTO(contactEntity));
        }
        return contactDTOList;
    }
    public ContactDTO getContactByPhone (String phone) {
        Optional<ContactEntity> optionalContact = contactRepository.findByPhone(phone);
        if (optionalContact.isPresent()) {
            return  toDTO(optionalContact.get());
        }
        throw new RuntimeException("Contact not found");
    }

    public String deleteById (Integer id) {
        contactRepository.deleteById(id);
        return "Successfully deleted";
    }
    public ContactDTO changeById(ContactDTO contactDTO, Integer id){
        ContactEntity contact = contactRepository.getById(id);
        contact.setPhone(contactDTO.getPhone());
        ContactEntity responseContactEntity = contactRepository.save(contact);
        ContactDTO response = toDTO(responseContactEntity);
        return response;
    }
    public ContactDTO getContactByProfileId (Integer profileId) {
        Optional<ContactEntity> optionalContact = contactRepository.getContactByProfileId(profileId);
        if (optionalContact.isPresent()) {
            return toDTO(optionalContact.get());
        }
        throw  new RuntimeException("Contact not found");
    }

    private ContactDTO toDTO (ContactEntity contactEntity) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contactEntity.getId());
        contactDTO.setPhone(contactEntity.getPhone());
        return contactDTO;
    }


}
