package poly.edu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.edu.backend.entity.Product;
import poly.edu.backend.service.ProductService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("product")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("product/{id}")
    public Product findById(@PathVariable("id") Integer id){
        return productService.findById(id);
    }

    @PostMapping("product")
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }
    @PutMapping("product/{id}")
    public Product update(@PathVariable("id") String id, @RequestBody Product product){
        return productService.update(product);
    }
    @DeleteMapping("product/{id}")
    public void delete(@PathVariable("id") Integer id){
        productService.deleteById(id);
    }
}
