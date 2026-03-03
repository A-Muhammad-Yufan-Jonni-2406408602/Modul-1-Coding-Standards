package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements InterfaceProductRepo{
    private List<Product> productData;

    public ProductRepository() {
        this.productData = new ArrayList<>();
    }

    @Override
    public Product create(final Product product){
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    @Override
    public Product update(final String id, final Product updatedProduct) {
        Product toUpdate = findById(id);
        if (toUpdate == null) {
            return toUpdate;
        }
        toUpdate.setProductName(updatedProduct.getProductName());
        toUpdate.setProductQuantity(updatedProduct.getProductQuantity());
        return toUpdate;
    }

    @Override
    public Product findById(final String productId){
        Product foundProduct = null;
        for(final Product product : productData){
            if(product.getProductId().equals(productId)){
                foundProduct = product;
            }
        }
        return foundProduct;
    }

    @Override
    public void delete(String id){
        productData.removeIf(car -> car.getProductId().equals(id));
    }
}
