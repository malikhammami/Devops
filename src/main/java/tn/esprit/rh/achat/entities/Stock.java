package tn.esprit.rh.achat.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;
    private String libelleStock;
    private Integer qte;
    private Integer qteMin;

    public Stock(String libelleStock, Integer qte, Integer qteMin) {
        this.libelleStock = libelleStock;
        this.qte = qte;
        this.qteMin = qteMin;
    }

    public static StockBuilder builder() {
        return new StockBuilder();
    }

    public static class StockBuilder {
        private String libelleStock;
        private Integer qte;
        private Integer qteMin;

        private StockBuilder() {
        }

        public StockBuilder libelleStock(String libelleStock) {
            this.libelleStock = libelleStock;
            return this;
        }

        public StockBuilder qte(Integer qte) {
            this.qte = qte;
            return this;
        }

        public StockBuilder qteMin(Integer qteMin) {
            this.qteMin = qteMin;
            return this;
        }

        public Stock build() {
            return new Stock(libelleStock, qte, qteMin);
        }
    }
}
