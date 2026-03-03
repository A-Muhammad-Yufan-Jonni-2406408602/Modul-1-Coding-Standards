package id.ac.ui.cs.advprog.eshop.model;
import lombok.Setter;
import lombok.Getter;
import java.util.UUID;

@Getter @Setter
public class Car {
    private String carId = UUID.randomUUID().toString();
    private String carName;
    private String carColor;
    private int carQuantity;
}
