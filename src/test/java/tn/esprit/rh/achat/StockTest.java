package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StockTest {
    @Mock
    StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockService;

    @Test
    void testRetrieveStock() {
        Stock stock = new Stock(1L, "Test Stock", 100, 10);
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
        Stock retrievedStock = stockService.retrieveStock(1L);
        Assertions.assertNotNull(retrievedStock);
    }

    @Test
    void testAllRetrieveStock() {
        List<Stock> stockList = new ArrayList<Stock>() {
            {
                add(new Stock(1L, "first", 10, 5));
                add(new Stock(2L, "second", 200, 10));
            }
        };

        Mockito.when(stockRepository.findAll()).thenReturn(stockList);
        List<Stock> retrievedStocks = stockService.retrieveAllStocks();
        Assertions.assertNotNull(retrievedStocks);
    }

    @Test
    void testAddStock() {
        Stock newStock = new Stock(null, "New Stock", 50, 20);
        Mockito.when(stockRepository.save(newStock)).thenReturn(newStock);
        Stock addedStock = stockService.addStock(newStock);
        Assertions.assertNotNull(addedStock);
    }

    @Test
    void testUpdateStock() {
        Stock existingStock = new Stock(1L, "Existing Stock", 75, 15);
        existingStock.setQteMin(10);

        Mockito.when(stockRepository.save(existingStock)).thenReturn(existingStock);
        Stock updatedStock = stockService.updateStock(existingStock);
        assertEquals(10, updatedStock.getQteMin());
    }

    @Test
    void testDeleteStock() {
        Stock stockToDelete = new Stock(1L, "Stock to Delete", 50, 10);
        stockService.deleteStock(stockToDelete.getIdStock());
        Mockito.verify(stockRepository, Mockito.times(1)).deleteById(stockToDelete.getIdStock());
    }
}
