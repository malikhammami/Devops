package tn.esprit.rh.achat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
//@ContextConfiguration(classes = {OperateurServiceImpl.class})
//@ExtendWith(SpringExtension.class)
public class OperateurTest {
    @Mock
   private OperateurRepository operateurRepository;
    @InjectMocks
    private OperateurServiceImpl operateurServiceImpl;


    Operateur operateur = new Operateur(1L, "Amani", "Boussaa", "password", new HashSet<Facture>(){
            {
                add(new Facture(2L, 50.0f, 300.0f, new Date(), new Date(), false, null, null, null));
            }
        }
    );
    List<Operateur> operatorList = new ArrayList<Operateur>(){
        {
            add(new Operateur(1L, "John", "Doe", "password", new HashSet<>()));
            add(new Operateur(2L, "Alice", "Smith", "secure123", new HashSet<>()));
            add(new Operateur(3L, "Bob", "Johnson", "secret", new HashSet<>()));
            add(new Operateur(4L, "Eva", "Brown", "pass123", new HashSet<>()));
            add(new Operateur(5L, "Michael", "Wilson", "letmein", new HashSet<>()));
        }
    };


    @Test
    void testRetrieveOperateur() {
        Mockito.when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(operateur));
        Operateur operateur1 = operateurServiceImpl.retrieveOperateur(2L);
        Assertions.assertNotNull(operateur1);
    }
    @Test
    void getAllOperateur()
    {
        Mockito.when(operateurRepository.findAll()).thenReturn(operatorList);

        // Perform the action that uses operateurRepository.findAll(), e.g., calling a service method
        List<Operateur> result = operateurServiceImpl.retrieveAllOperateurs(); // Replace with the actual service method call

        // Verify that the operateurRepository.findAll() method was called
        Mockito.verify(operateurRepository).findAll();

        // Perform assertions or verifications based on the results
        assertEquals(operatorList.size(), result.size());
    }
    @Test
    void testDeleteOperateur() {
        // Create a sample Operateur instance
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L); // Replace with the actual ID

        // Mock the behavior of operateurRepository.findById to return the sample Operateur
        Mockito.when(operateurRepository.findById(operateur.getIdOperateur())).thenReturn(Optional.of(operateur));

        // Mock the behavior of operateurRepository.existsById to return true, indicating that the Operateur exists
        Mockito.when(operateurRepository.existsById(operateur.getIdOperateur())).thenReturn(true);

        // Perform the delete operation
        operateurServiceImpl.deleteOperateur(operateur.getIdOperateur());

        // Verify that operateurRepository.deleteById was called with the provided ID
        Mockito.verify(operateurRepository).deleteById(operateur.getIdOperateur());



    }

    @Test
    void testUpdateOperateur() {
        // Create a sample Operateur instance with updated values
        Operateur updatedOperateur = new Operateur();
        updatedOperateur.setIdOperateur(1L); // Replace with the actual ID
        updatedOperateur.setNom("Updated Name"); // Set the updated name

        // Mock the behavior of operateurRepository.findById to return the sample Operateur
        Mockito.when(operateurRepository.findById(updatedOperateur.getIdOperateur())).thenReturn(Optional.of(updatedOperateur));

        // Perform the update operation
        Operateur result = operateurServiceImpl.updateOperateur(updatedOperateur);

        // Verify that operateurRepository.save was called with the updated Operateur
        Mockito.verify(operateurRepository).save(updatedOperateur);

        // You can also verify other aspects of the update operation, e.g., comparing the returned Operateur.
        assertEquals(updatedOperateur, result);

        // Additional assertions and verifications can be added based on your specific requirements.
    }
    @Test
    void testAddOperateur() {
        // Create a sample Operateur instance to be added
        Operateur newOperateur = new Operateur();
        newOperateur.setNom("New Operator"); // Set the name or other properties

        // Mock the behavior of operateurRepository.save to return the new Operateur
        Mockito.when(operateurRepository.save(newOperateur)).thenReturn(newOperateur);

        // Perform the add operation
        Operateur result = operateurServiceImpl.addOperateur(newOperateur);

        // Verify that operateurRepository.save was called with the new Operateur
        Mockito.verify(operateurRepository).save(newOperateur);

        // Additional assertions and verifications can be added based on your specific requirements.
        assertNotNull(result); // Ensure that the result is not null
        assertEquals(newOperateur.getNom(), result.getNom()); // Ensure the properties match
    }
}
