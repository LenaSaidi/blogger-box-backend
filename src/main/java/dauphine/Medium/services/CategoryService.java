package dauphine.Medium.services;

import dauphine.Medium.models.Category;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category getById(UUID id);
    Category create(String name);
    Category update(UUID id, String name);
    UUID deleteById(UUID id);
}