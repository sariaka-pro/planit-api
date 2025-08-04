package com.descodeuses.planit.service;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.ActionDTO;
import com.descodeuses.planit.entity.Action;
import com.descodeuses.planit.entity.contact;
import com.descodeuses.planit.repository.ActionRepository;
import com.descodeuses.planit.repository.ContactRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ActionService {

    private final ActionRepository repository; 
    private final ContactRepository contactRepository; 

    public ActionService(ActionRepository repository, ContactRepository contactRepository) {
        this.repository = repository; 
        this.contactRepository = contactRepository;
    }

    private ActionDTO convertToDto(Action action) {
        ActionDTO actionsDo = new ActionDTO(
            action.getId(), 
            action.getTitle(), 
            action.getCompleted(),
            action.getDueDate(),
            action.getPriorities()
        ); 
        
        Set<Long> memberIds = action.getMembers().stream() 
            .map(contact::getId)
            .collect(Collectors.toSet());

        actionsDo.setMemberIds(memberIds);

        return actionsDo;

    }

    private Action convertToEntity(ActionDTO actionDTO, Set<contact> members) {
        Action action = new Action(); 
        action.setId(actionDTO.getId());
        action.setTitle(actionDTO.getTitle());
        action.setCompleted(actionDTO.getCompleted());
        action.setDueDate(actionDTO.getDueDate());
        action.setPriorities(actionDTO.getPriorities());
        action.setMembers(members);

        return action; 
    }

    public List<ActionDTO> getAll() {
        List<Action> actions = repository.findAll(); 
        List<ActionDTO> actionsDo = new ArrayList<>(); 

        for(Action a: actions){
            actionsDo.add(convertToDto(a)) ; 
         }

        return actionsDo; 
    }

    public ActionDTO getActionById(Long id) {
    Action action = 
    repository
    .findById(id)
    .orElseThrow(() -> new EntityNotFoundException("Action not found with id : "+id)); /// qu'il retourne un mesage si le champ ID est vide. 
    
    return convertToDto(action);
    }   

    public ActionDTO create(ActionDTO actionsDo) {
        // Convertir le DTO en entité
        Set<contact> contacts = new HashSet<>(contactRepository.findAllById(actionsDo.getMemberIds()));
        Action actionCreate = convertToEntity(actionsDo, contacts); 
    
        // Sauvegarder l'entité dans la base de données
        Action savedAction = repository.save(actionCreate); 
        
        // Convertir l'entité enregistrée en DTO et retourner
        return convertToDto(savedAction); 
    }

    public ActionDTO update(Long id, ActionDTO actionsDo) {
        // Rechercher l'entité par son identifiant
        Optional<Action> optionalAction = repository.findById(id); 
        //    sinon lever une exception "Ressource non trouvée"
            if(optionalAction.isPresent()) {
                Action actionUpdate = optionalAction.get();
                
                actionUpdate.setTitle(actionsDo.getTitle()); 
                actionUpdate.setCompleted(actionsDo.getCompleted()); 
                actionUpdate.setDueDate(actionsDo.getDueDate()); 
                actionUpdate.setPriorities(actionsDo.getPriorities());

                Set<contact> contacts = new HashSet<>(contactRepository.findAllById(actionsDo.getMemberIds()));
                actionUpdate.setMembers(contacts);

                Action updateAction = repository.save(actionUpdate);

                return convertToDto(updateAction); 

            } else {
                throw new EntityNotFoundException("Action not found : "+id); 
            }

        // Sauvegarder l'entité mise à jour dans la base de données
        //entitéMiseAJour = référentiel.sauvegarder(entitéExistante)

        // Convertir l'entité mise à jour en DTO et retourner
        //retourner convertirVersDTO(entitéMiseAJour)
    }

    public void delete(Long id) {

        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Action not found with this : "+id);
        } 
        
        repository.deleteById(id); 
        // Vérifier si une entité avec l'identifiant donné existe
        //si référentiel.n'existePasParId(id) alors
            //lever une exception "Ressource non trouvée avec cet id"

        // Supprimer l'entité par son identifiant
        //référentiel.supprimerParId(id)
    }
    
}
