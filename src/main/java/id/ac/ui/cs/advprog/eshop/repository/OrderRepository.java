package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    Order findById(String id);
    List<Order> findAllByAuthor(String author);
}
