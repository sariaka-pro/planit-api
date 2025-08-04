package com.descodeuses.planit.controller;

import com.descodeuses.planit.service.ActionService;
import com.descodeuses.planit.dto.ActionDTO;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/api/action")
public class Actioncontroller {

    @GetMapping("/hello")  
    public String HelloAction() {
        return "Hello World !"; 
    }; 

    /// Il s'agit de la méthode CRUD de angular 
    private final ActionService service;

    /// Constructeur 
    /// Recoit le service 
    /// Comme c'est un service, donc il est injecté. Tous les injectables sont reçus depuis le constructeur. 
    public Actioncontroller(ActionService service) {
        this.service = service; 
    }

    @GetMapping /// GET pour récupérer tout le tableau  
    public ResponseEntity<List<ActionDTO>> getAll() {
        List<ActionDTO> items = service.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK); /// le OK signifie que la requete a bien été faite.  
        /* 
        Action action1 = new Action(); 
        action1.setTitle("Envoyer un mail");

        Action action2 = new Action(); 
        action2.setTitle("Appel téléphonique");

        ArrayList<Action> list = new ArrayList<>(); 
        list.add(action1); 
        list.add(action2); 
        */  

    }

    @GetMapping("/{id}") /// GET pour récupérer un seul élément 
    public ResponseEntity<ActionDTO> getActionById(@PathVariable long id) {
        ActionDTO actiondto = service.getActionById(id);
        return new ResponseEntity<ActionDTO>(actiondto, HttpStatus.OK);
    }

    @PostMapping /// POST – pour envoyer des données
    public ResponseEntity<ActionDTO> createActions(@RequestBody ActionDTO resquestDTO) {
        ActionDTO createDTO = service.create(resquestDTO);
        return new ResponseEntity<>(createDTO, HttpStatus.CREATED); 
    }
    
    @PutMapping("/{id}")  //PATCH – Modifier partiellement une ressource
    public ResponseEntity<ActionDTO> updateAction(@PathVariable long id, @RequestBody ActionDTO actionDTO) {
        ActionDTO updateDTO = service.update(id, actionDTO);
        return ResponseEntity.ok(updateDTO);
    }

    @DeleteMapping("/{id}") ///DELETE – Supprimer une ressource
    public ResponseEntity<Void> deleteAction(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();  /// le build() retourne un ResponseEntity<Void> prêt à être renvoyé.  
        /// le RETURN DIT = Suppression réussie, je ne renvoie aucune donnée (ni corps, ni JSON), seulement le code 204.
        /// autre possibilité de return >>>  return RespnseEntity<>(HttpStatus.NO_CONTENT); 
    }

}
