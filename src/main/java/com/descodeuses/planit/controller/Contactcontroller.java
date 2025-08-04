package com.descodeuses.planit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.descodeuses.planit.dto.ContactDTO;
import com.descodeuses.planit.service.ContactService;

@RestController
@RequestMapping ("/api/contact")

public class Contactcontroller {

    private final ContactService service; 
    public Contactcontroller(ContactService service) {
        this.service = service; 
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAll() {
        List<ContactDTO> items = service.getAllContact(); 
        return new ResponseEntity<>(items, HttpStatus.OK); 
        /*contact contact1 = new contact(); 
        contact1.setNom("Dupont Marie"); 

        contact contact2 = new contact();  
        contact2.setNom("Wilson James");

        ArrayList<contact> list = new ArrayList<>(); 
        list.add(contact1); 
        list.add(contact2);  */
        
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long id) {  /// le @PathVariable sert à récupérer le numéro de l'id. 
        ContactDTO contactDto = service.getContactById(id);
        return new ResponseEntity<ContactDTO>(contactDto, HttpStatus.OK); 

    }

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) {
        ContactDTO createContactDTO = service.create(contactDTO);
        return new ResponseEntity<>(createContactDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTo) {
        ContactDTO updateContactDTO = service.update(id, contactDTo); 
        return ResponseEntity.ok(updateContactDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable long id) {
        service.delete(id); 
        return ResponseEntity.noContent().build(); 
    }
}
