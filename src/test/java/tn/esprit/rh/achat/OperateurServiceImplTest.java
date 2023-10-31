package tn.esprit.rh.achat;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import tn.esprit.rh.achat.entities.Operateur;
//import tn.esprit.rh.achat.services.IOperateurService;
//
//import java.util.List;
//
//@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
public class OperateurServiceImplTest {
//    @Autowired
//    IOperateurService operateurService;
//    @Test
//    @Order(1)
//    public void testRetrieveAllOperateurs() {
//        List<Operateur> operateurList = operateurService.retrieveAllOperateurs();
//        Assertions.assertEquals(0, operateurList.size());
//    }
//    @Test
//    @Order(2)
//    public void testAddOperateur() {
//        Operateur operateur = new Operateur();
//        operateur.setNom("amani");
//        operateur.setPrenom("boussaa");
//        operateur.setPassword("123");
//        operateur.setFactures(null);
//        operateurService.addOperateur(operateur);
//        List<Operateur> operateurList = operateurService.retrieveAllOperateurs();
//        Assertions.assertEquals(1, operateurList.size());
//    }
//
//    @Test
//    @Order(3)
//    public void testRetrieveOperateur() {
//        Operateur retrievedOperateur = operateurService.retrieveOperateur(1L);
//        Assertions.assertNotNull(retrievedOperateur);
//        Assertions.assertEquals("amani", retrievedOperateur.getNom());
//    }

//    @Test
//    @Order(4)
//    public void testUpdateOperateur() {
//        Operateur operateur = operateurService.retrieveOperateur(1L);
//        operateur.setNom("Alice");
//        operateurService.updateOperateur(operateur);
//        Operateur updatedOperateur = operateurService.retrieveOperateur(1L);
//        Assertions.assertEquals("Alice", updatedOperateur.getNom());
//    }
//
//    @Test
//    @Order(5)
//    public void testDeleteOperateur() {
//        operateurService.deleteOperateur(1L);
//        List<Operateur> operateurList = operateurService.retrieveAllOperateurs();
//        Assertions.assertEquals(0, operateurList.size());
//    }
}
