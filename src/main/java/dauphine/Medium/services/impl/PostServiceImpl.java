package dauphine.Medium.services.impl;

import dauphine.Medium.models.Post;
import dauphine.Medium.services.PostService;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> temporaryPosts;

    public PostServiceImpl() {
        this.temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post(UUID.randomUUID(), "Mon premier article", "Contenu 1", Timestamp.from(Instant.now()), UUID.randomUUID()));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Actualité sport", "Contenu 2", Timestamp.from(Instant.now()), UUID.randomUUID()));
    }

    @Override
    public List<Post> getAll() {
        // Ordonné par date de création (le plus récent en premier)
        return temporaryPosts.stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return temporaryPosts.stream()
                .filter(p -> p.getCategoryId().equals(categoryId))
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, Timestamp.from(Instant.now()), categoryId);
        temporaryPosts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
        }
        return post;
    }

    @Override
    public boolean deleteById(UUID id) {
        return temporaryPosts.removeIf(p -> p.getId().equals(id));
    }
}