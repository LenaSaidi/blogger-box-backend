package dauphine.Medium.services;

import dauphine.Medium.models.Post;
import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllByCategoryId(UUID categoryId);
    List<Post> getAll();
    List<Post> getAllLikeTitleOrContent(String value);
    Post getById(UUID id);
    Post create(String title, String content, UUID categoryId);
    Post update(UUID id, String title, String content);
    boolean deleteById(UUID id);
}