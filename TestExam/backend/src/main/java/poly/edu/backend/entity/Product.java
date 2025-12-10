package poly.edu.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @Column(name = "productId", length = 5)
    private String productId;

    @Column(name = "productName", nullable = false, length = 50)
    private String productName;

    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "expiratin")
    private LocalDate expiratin;

    @Column(name = "images", nullable = false, length = 255)
    private String images;

    @Column(name = "source", nullable = false, length = 10)
    private String source;
}
