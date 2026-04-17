
package dauphine.Medium.Controller;

import dauphine.Medium.Model.Category;
import dauphine.Medium.dto.CreationCategoryRequest;
import dauphine.Medium.dto.UpdateCategoryRequest;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API", description = "Category endpoints")
public class CategoryController {

    private final List<Category> temporaryCategories;

    public CategoryController() {
        temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories")
    public List<Category> retrieveAllCategories() {
        return temporaryCategories;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by id")
    public Category retrieveById(@PathVariable UUID id) {
        return temporaryCategories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category create(@RequestBody CreationCategoryRequest request) {
        Category category = new Category(UUID.randomUUID(), request.getName());
        temporaryCategories.add(category);
        return category;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a category")
    public Category update(@PathVariable UUID id, @RequestBody UpdateCategoryRequest request) {
        Category category = temporaryCategories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (category != null) {
            category.setName(request.getName());
        }
        return category;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category")
    public void delete(@PathVariable UUID id) {
        temporaryCategories.removeIf(c -> c.getId().equals(id));
    }
}

