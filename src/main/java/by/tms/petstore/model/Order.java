package by.tms.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private Status status;
    private boolean complete;

    public enum Status{
        PLACED, APPROVED, DELIVERED
    }

}
