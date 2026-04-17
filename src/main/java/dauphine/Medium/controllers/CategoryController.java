
package dauphine.Medium.controllers;

import dauphine.Medium.models.Category;
import dauphine.Medium.services.CategoryService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import dauphine.Medium.exceptions.CategoryNotFoundByIdException;
import org.springframework.http.ResponseEntity;
import java.net.URI;


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
    @Operation(
            summary = "Get all categories",
            description = "Retrieve all categories or filter like name"
    )
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? service.getAll()
                : service.getAllLikeName(name);
        return ResponseEntity.ok(categories);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by id")
    public ResponseEntity<Category> retrieveById(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        Category category = service.getById(id);
        return ResponseEntity.ok(category);
    }


    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<Category> create(@RequestBody String name) {
        Category category = service.create(name);
        return ResponseEntity
                .created(URI.create("/v1/categories/" + category.getId()))
                .body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable UUID id, @RequestBody String name) throws CategoryNotFoundByIdException {
        Category category = service.update(id, name);
        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

