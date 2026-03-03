package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.InterfaceCarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class CarServiceImpl implements CarService {

    private final InterfaceCarRepo carRepository;

    @Autowired
    public CarServiceImpl(InterfaceCarRepo carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String id) {
        return carRepository.findById(id);
    }

    @Override
    public void update(String id, Car car) {
        carRepository.update(id, car);
    }

    @Override
    public void deleteCarById(String id) {
        carRepository.delete(id);
    }
}
