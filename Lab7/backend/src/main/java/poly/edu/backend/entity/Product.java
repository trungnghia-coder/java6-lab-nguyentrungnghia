package poly.edu.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private BigDecimal price; // Giá (Dùng BigDecimal cho dữ liệu tiền tệ)

    private Date date;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
