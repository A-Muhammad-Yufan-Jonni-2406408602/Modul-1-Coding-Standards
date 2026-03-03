package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

@Repository
public class CarRepository implements InterfaceCarRepo {


    private List<Car> carData = new ArrayList<>();

    @Override
    public Car create(Car car) {
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    @Override
    public Car findById(String id) {
        Car foundCar = null;
        for(final Car car : carData){
            if(car.getCarId().equals(id)) {
                foundCar = car;
            }
        }
        return foundCar;
    }

    @Override
    public Car update(String id, Car updatedCar) {
        Car toUpdate = findById(id);
        if(toUpdate == null) {
            return toUpdate;
        }
        toUpdate.setCarName(updatedCar.getCarName());
        toUpdate.setCarColor(updatedCar.getCarColor());
        toUpdate.setCarQuantity(updatedCar.getCarQuantity());
        return toUpdate;
    }

    @Override
    public void delete(String id) {
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
