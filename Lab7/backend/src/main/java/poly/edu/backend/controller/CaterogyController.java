package poly.edu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.edu.backend.entity.Category;
import poly.edu.backend.service.CategoryService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class CaterogyController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("category")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("category/{id}")
    public Category findById(@PathVariable("id") Integer id){
        return categoryService.findById(id);
    }

    @PostMapping("category")
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }
    @PutMapping("category/{id}") //=> student
    public Category update(@PathVariable("id") String id, @RequestBody Category category){
        return categoryService.update(category);
    }
    @DeleteMapping("category/{id}")
    public void delete(@PathVariable("id") Integer id){
        categoryService.deleteById(id);
    }
}
