package poly.edu.lab2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="J6userroles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "username")
    User user;
    @ManyToOne
    @JoinColumn(name ="roleid")
    Role role;
}
