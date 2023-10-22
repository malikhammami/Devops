package tn.esprit.rh.achat;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class OperateurTest {
    @Mock
    private OperateurRepository operateurRepository;
    @InjectMocks
    private OperateurServiceImpl operateurServiceImpl;

    @Test
    void testRetrieveOperateur() {
        Operateur operateur = new Operateur(1L, "Amani", "Boussaa", "password", null);
        when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        Operateur operateur1 = operateurServiceImpl.retrieveOperateur(1L);

        Assertions.assertNotNull(operateur1);
        Assertions.assertEquals("Amani", operateur1.getNom()); // Add more assertions as needed.
    }

    @Test
    void testGetAllOperateur() {
        List<Operateur> operatorList = Arrays.asList(
                new Operateur(1L, "John", "Doe", "password", new HashSet<>()),
                new Operateur(2L, "Alice", "Smith", "secure123", new HashSet<>())
                // Add more Operateur objects as needed
        );

        when(operateurRepository.findAll()).thenReturn(operatorList);

        List<Operateur> results = operateurServiceImpl.retrieveAllOperateurs();

        Assertions.assertEquals(operatorList.size(), results.size());
        // Add more assertions as needed.
    }

    @Test
    void testDeleteOperateur() {
        Operateur operateur = new Operateur(1L, "John", "Doe", "password", new HashSet<>());

        operateurServiceImpl.deleteOperateur(operateur.getIdOperateur());

        verify(operateurRepository).deleteById(operateur.getIdOperateur());
    }


    @Test
    void testUpdateOperateur() {
        Operateur operateur = new Operateur(1L, "John", "Doe", "password", new HashSet<>());

        Operateur updatedOperateur = new Operateur(1L, "Updated Name", "Updated Last Name", "updatedpassword", new HashSet<>());

        Operateur result = operateurServiceImpl.updateOperateur(updatedOperateur);

        verify(operateurRepository).save(updatedOperateur);

        Assertions.assertEquals("Updated Name", result.getNom()); // Add more assertions as needed.
    }

    @Test
    void testAddOperateur() {
        Operateur newOperateur = new Operateur(1L, "New Operator", "LastName", "password", new HashSet<>());

        when(operateurRepository.save(newOperateur)).thenReturn(newOperateur);

        Operateur result = operateurServiceImpl.addOperateur(newOperateur);

        verify(operateurRepository).save(newOperateur);

        Assertions.assertEquals("New Operator", result.getNom()); // Add more assertions as needed.
    }
}
