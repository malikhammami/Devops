package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.IFactureService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FactureTest {

    @Mock
    FactureRepository  factureRepository;

    @InjectMocks
    FactureServiceImpl factureService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testretrieveAllfacture() {

        ArrayList<Facture> factureList = new ArrayList<>();
        when(factureRepository.findAll()).thenReturn(factureList);
        List<Facture> actualRetrieveAllfactureesResult = factureService.retrieveAllFactures();
        assertSame(factureList, actualRetrieveAllfactureesResult);
        assertTrue(actualRetrieveAllfactureesResult.isEmpty());
        verify(factureRepository).findAll();
    }
    @Test
    public void testaddfacture(){

        //arrange
        Date dateCreationFacture = new Date();
        Date dateDerniereModificationFacture = new Date();
        Facture factureToAdd = new Facture(1L, 123.45f, 128.78f,dateCreationFacture, dateDerniereModificationFacture, true, new HashSet<>(), null, new HashSet<>() );
        when(factureRepository.save(any(Facture.class))).thenReturn(factureToAdd);
        //Act
        Facture addedfacture = factureService.addFacture(factureToAdd);
        //Assert
        assertNotNull(addedfacture);
        assertEquals(1L, addedfacture.getIdFacture());
        assertEquals(123.45f,addedfacture.getMontantRemise());
        assertEquals(128.78f, addedfacture.getMontantFacture());
        assertEquals(dateCreationFacture,addedfacture.getDateCreationFacture());
        assertEquals(dateDerniereModificationFacture, addedfacture.getDateDerniereModificationFacture());
        assertTrue(addedfacture.getArchivee());
    }

    @Test
    void retrieveFacture() {
        Date dateCreationFacture = new Date();
        Date dateDerniereModificationFacture = new Date();
        Facture facture = new Facture(1L,123.45f,128.78f,dateCreationFacture,dateDerniereModificationFacture, true, new HashSet<>(), null, new HashSet<>()  );
        when(factureRepository.findById(1L)).thenReturn(Optional.of(facture));
        factureService.retrieveFacture(1L);
        Assertions.assertNotNull(facture);

        System.out.println(facture);
        System.out.println(" testRetrievefacture-> CA MARCHE  !!!!!");
    }
