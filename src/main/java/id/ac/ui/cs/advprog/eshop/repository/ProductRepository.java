package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData;

    public ProductRepository() {
        this.productData = new ArrayList<>();
    }

    public Product create(final Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(final Product newProduct){
        for(int i = 0; i < productData.size(); i++){
            if(productData.get(i).getProductId().equals(newProduct.getProductId())){
                productData.set(i, newProduct);
            }
        }
        return newProduct;
    }

    public Product findById(final String productId){
        Product foundProduct = null;
        for(final Product product : productData){
            if(product.getProductId().equals(productId)){
                foundProduct = product;
            }
        }
        return foundProduct;
    }

    public void delete(final Product product){
        productData.remove(product);
    }
}
