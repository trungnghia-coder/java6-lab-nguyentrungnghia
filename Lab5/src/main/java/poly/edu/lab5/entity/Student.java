package poly.edu.lab5.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {
    private String id;
    private String name;
    private boolean gender;
    private double mark;
}
