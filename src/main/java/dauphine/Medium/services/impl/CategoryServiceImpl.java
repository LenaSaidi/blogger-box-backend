package dauphine.Medium.services.impl;

import dauphine.Medium.models.Category;
import dauphine.Medium.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> temporaryCategories;

    public CategoryServiceImpl() {
        this.temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "sport"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "politique"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "sante"));
    }

    @Override
    public List<Category> getAll() {
        return temporaryCategories;
    }

    @Override
    public Category getById(UUID id) {
        return temporaryCategories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);
        temporaryCategories.add(category);
        return category;
    }

    @Override
    public Category update(UUID id, String name) {
        Category category = temporaryCategories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (category != null) {
            category.setName(name);
        }
        return category;
    }

    @Override
    public UUID deleteById(UUID id) {
        temporaryCategories.removeIf(c -> c.getId().equals(id));
        return id;
    }

}