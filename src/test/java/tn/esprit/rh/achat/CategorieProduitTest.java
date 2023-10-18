package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CategorieProduitTest {

    @Mock
    CategorieProduitRepository categorieRepository;

    @InjectMocks
    CategorieProduitServiceImpl categorieService;

    @Test
    public void createCategorieProduitTest() {
        CategorieProduit categorie2 = new CategorieProduit(null, "test1.0.0", "test1.0.0", null);
        categorie2.setIdCategorieProduit(2L);

        categorieService.addCategorieProduit(categorie2);

        // Verify that the 'save' method of the repository is called with the correct argument
        verify(categorieRepository, times(1)).save(categorie2);
        System.out.println(categorie2);
        System.out.println("createCategorieProduitTest -> CA MARCHE  !!!!!");
    }
}
