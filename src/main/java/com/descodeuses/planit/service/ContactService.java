package com.descodeuses.planit.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.ContactDTO;
import com.descodeuses.planit.entity.contact;
import com.descodeuses.planit.repository.ContactRepository;

import jakarta.persistence.EntityNotFoundException;

@Service

public class ContactService {

    private final ContactRepository repository; 
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    private ContactDTO convertToDto(contact contact) {
        return new ContactDTO(
            contact.getId(),
            contact.getNom(), 
            contact.getPrenom()
            ); 

    }

    private contact convertToEntity(ContactDTO contactDto) {
        contact contact = new contact(); 
        contact.setId(contactDto.getId()); 
        contact.setNom(contactDto.getNom()); 
        contact.setPrenom(contactDto.getPrenom()); 

        return contact; 
    }

    public List<ContactDTO> getAllContact() {
        List<contact> contact = repository.findAll(); 
        List<ContactDTO> contactDto = new ArrayList<>(); 

        for(contact item: contact) {
            ContactDTO dto = new ContactDTO();  ///pourquoi le ContactDTO est undefined ?? 
            dto.setId(item.getId());
            dto.setNom(item.getNom());
            dto.setPrenom(item.getPrenom());
            contactDto.add(dto); 
        }

        return contactDto; 
    }

    public ContactDTO getContactById(Long id) {
        Optional<contact> contactID = repository.findById(id);
        if(contactID.isEmpty()) {
            throw new EntityNotFoundException("Contact not found : "+id); 
        }
        return convertToDto(contactID.get());
    }

    public ContactDTO create(ContactDTO contactDto) {
        contact contactCreate = convertToEntity(contactDto);
        contact savedContact = repository.save(contactCreate);

        return convertToDto(savedContact); 
    }

    public ContactDTO update(Long id, ContactDTO contactDTO) {
        Optional<contact> optionalContacts = repository.findById(id); 

        if(optionalContacts.isPresent()) {
            contact updateContact = optionalContacts.get(); 

            updateContact.setId(contactDTO.getId());
            updateContact.setNom(contactDTO.getNom());
            updateContact.setPrenom(contactDTO.getPrenom());

            contact contactUpdate = repository.save(updateContact); 

            return convertToDto(contactUpdate); 

        } else {
            throw new EntityNotFoundException("Contact is not found : "+id); 
        }

    }

    public  void delete(Long id) {
        if(repository.existsById(id)) {
            throw new EntityNotFoundException("Contact is not found : "+id);
        }
        repository.deleteById(id); 
    }
    
}
