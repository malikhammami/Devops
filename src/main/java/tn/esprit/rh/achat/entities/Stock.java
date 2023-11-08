package tn.esprit.rh.achat.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockTest {
    private Stock stock;

    @BeforeEach
    public void setUp() {
        stock = Stock.builder()
            .libelleStock("Test Stock")
            .qte(100)
            .qteMin(10)
            .build();
    }

    @Test
    public void testGetLibelleStock() {
        assertEquals("Test Stock", stock.getLibelleStock());
    }

    @Test
    public void testGetQte() {
        assertEquals(100, stock.getQte());
    }

    @Test
    public void testGetQteMin() {
        assertEquals(10, stock.getQteMin());
    }
}
