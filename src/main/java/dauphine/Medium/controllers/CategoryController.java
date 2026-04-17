
package dauphine.Medium.controllers;

import dauphine.Medium.models.Category;
import dauphine.Medium.services.CategoryService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API", description = "Category endpoints")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories")
    public List<Category> retrieveAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by id")
    public Category retrieveById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category create(@RequestBody String name) {
        return service.create(name);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a category")
    public Category update(@PathVariable UUID id, @RequestBody String name) {
        return service.update(id,name);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category")
    public boolean delete(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}

