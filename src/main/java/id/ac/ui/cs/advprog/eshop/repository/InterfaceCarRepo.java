package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.Iterator;

public interface InterfaceCarRepo {
    Car create(Car car);
    Iterator<Car> findAll();
    Car findById(String carId);
    Car update(String id, Car car);
    void delete(String carId);
}
