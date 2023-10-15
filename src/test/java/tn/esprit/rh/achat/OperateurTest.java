package tn.esprit.rh.achat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = {OperateurServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class OperateurTest {
    @MockBean
   private OperateurRepository operateurRepository;
    @Autowired
    private OperateurServiceImpl operateurServiceImpl;



    @Test
    void getAllOperateur()
    {
        operateurServiceImpl.retrieveAllOperateurs();
        verify(operateurRepository).findAll();
    }
    @Test
    void DeleteOperateur() {
        doNothing().when(operateurRepository).deleteById((Long) any());
        operateurServiceImpl.deleteOperateur(123L);
        verify(operateurRepository).deleteById((Long) any());
    }
    @Test
    void testRetrieveOperateur() {
        // Create a sample Operateur object
        Operateur sampleOperateur = new Operateur();
        Long sampleId = 1L;

        // Set up the behavior of the operateurRepository mock
        when(operateurRepository.findById(sampleId)).thenReturn(Optional.of(sampleOperateur));

        // Call the method to be tested
        Operateur retrievedOperateur = operateurServiceImpl.retrieveOperateur(sampleId);

        // Verify that the method interacted with the mock as expected
        verify(operateurRepository).findById(sampleId);

        // Assert the result
        assertNotNull(retrievedOperateur);
        // You can add more specific assertions based on your requirements
    }
    @Test
    void testUpdateOperateur() {
        // Create a sample Operateur object
        Operateur sampleOperateur = new Operateur();

        // Set up the behavior of the operateurRepository mock
        when(operateurRepository.save(sampleOperateur)).thenReturn(sampleOperateur);

        // Call the method to be tested
        Operateur updatedOperateur = operateurServiceImpl.updateOperateur(sampleOperateur);

        // Verify that the method interacted with the mock as expected
        verify(operateurRepository).save(sampleOperateur);

        // Assert the result or verify other conditions based on your requirements
    }
    @Test
    void testAddOperateur() {
        // Create a sample Operateur object
        Operateur sampleOperateur = new Operateur();

        // Set up the behavior of the operateurRepository mock
        when(operateurRepository.save(sampleOperateur)).thenReturn(sampleOperateur);

        // Call the method to be tested
        Operateur addedOperateur = operateurServiceImpl.addOperateur(sampleOperateur);

        // Verify that the method interacted with the mock as expected
        verify(operateurRepository).save(sampleOperateur);

        // Assert the result or verify other conditions based on your requirements
    }
}
