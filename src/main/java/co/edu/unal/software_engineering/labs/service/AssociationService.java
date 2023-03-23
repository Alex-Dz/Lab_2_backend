package co.edu.unal.software_engineering.labs.service;

import co.edu.unal.software_engineering.labs.model.*;
import co.edu.unal.software_engineering.labs.repository.AssociationRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AssociationService {

    private AssociationRepository associationRepository;

    public AssociationService(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    public void save(Association association) {
        associationRepository.save(association);
    }

    public void associate(User user, Role role, Course course, Period period) {
        Association association = new Association(user, role, course, period);
        save(association);
    }

    public List<Association> getAssociationsByUser(User user) {
        return associationRepository.getAssociationsByUserAndRoles(user, user.getRoles());
    }
}
