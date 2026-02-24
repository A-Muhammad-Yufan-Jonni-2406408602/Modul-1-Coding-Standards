package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    final private ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(final Product product){
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll(){
        final Iterator<Product> productIterator = productRepository.findAll();
        final List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product edit(final Product newProduct){
        productRepository.edit(newProduct);
        return newProduct;
    }

    @Override
    public Product findById(final String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void delete(final Product product){
        productRepository.delete(product);
    }
}
