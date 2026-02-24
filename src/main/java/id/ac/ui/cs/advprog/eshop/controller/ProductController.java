package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(final ProductService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(final Model model){
        final Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute final Product product, final Model model){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(final Model model){
        final List<Product> allProduct = service.findAll();
        model.addAttribute("products", allProduct);
        return "productList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable final String productId, final Model model){
        final Product oldProduct = service.findById(productId);
        model.addAttribute("product", oldProduct);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute final Product newProduct, final Model model){
        service.edit(newProduct);
        return "redirect:list";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable final String productId){
        final Product oldProduct = service.findById(productId);
        service.delete(oldProduct);
        return "redirect:/product/list";
    }
}
