package dauphine.Medium.services.impl;

import dauphine.Medium.models.Category;
import dauphine.Medium.repositories.CategoryRepository;
import dauphine.Medium.services.CategoryService;
import org.springframework.stereotype.Service;
import dauphine.Medium.exceptions.CategoryNotFoundByIdException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> getAllLikeName(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>();
        }
        return repository.findAllLikeName(name);
    }

    @Override
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }


    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return repository.save(category);
    }

    @Override
    public Category update(UUID id, String name) throws CategoryNotFoundByIdException {
        Category category = getById(id);
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }

}