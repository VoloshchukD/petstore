package by.tms.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private String name;
//    private List<String> photoUrls;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;
    private Status status;

    public enum Status{
        AVAILABLE, PENDING, SOLD
    }

}
