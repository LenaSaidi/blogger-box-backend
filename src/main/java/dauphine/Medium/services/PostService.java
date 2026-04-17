package dauphine.Medium.services;

import dauphine.Medium.models.Post;
import dauphine.Medium.exceptions.PostNotFoundByIdException;
import dauphine.Medium.exceptions.CategoryNotFoundByIdException;
import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllByCategoryId(UUID categoryId);
    List<Post> getAll();
    List<Post> getAllLikeTitleOrContent(String value);
    Post getById(UUID id) throws PostNotFoundByIdException;
    Post create(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException;
    Post update(UUID id, String title, String content) throws PostNotFoundByIdException;
    boolean deleteById(UUID id);
}