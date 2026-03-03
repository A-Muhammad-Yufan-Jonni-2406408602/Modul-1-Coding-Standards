package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Repository
public class CarRepository {

    static int id = 0;

    private List<Car> carData = new ArrayList<>();

    public Car create(Car car) {
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String id) {
        for(Car car : carData){
            if(car.getCarId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public Car update(String id, Car updatedCar) {
        Car toUpdate = findById(id);
        if(toUpdate == null) {
            return toUpdate;
        }
        toUpdate.setCarName(updatedCar.getCarName());
        toUpdate.setCarColor(updatedCar.getCarColor());
        toUpdate.setCarQuantity(updatedCar.getCarQuantity());
        return null;
    }

    public void delete(String id) {carData.removeIf(car -> car.getCarId().equals(id));}
}
