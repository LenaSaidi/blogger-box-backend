package dauphine.Medium.services;

import dauphine.Medium.models.Category;
import dauphine.Medium.exceptions.CategoryNotFoundByIdException;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    List<Category> getAllLikeName(String name);
    Category getById(UUID id) throws CategoryNotFoundByIdException;
    Category create(String name);
    Category update(UUID id, String name) throws CategoryNotFoundByIdException;
    boolean deleteById(UUID id);
}