package dauphine.Medium.services.impl;

import dauphine.Medium.models.Post;
import dauphine.Medium.services.PostService;
import org.springframework.stereotype.Service;
import dauphine.Medium.models.Category;
import dauphine.Medium.repositories.PostRepository;
import dauphine.Medium.repositories.CategoryRepository;
import dauphine.Medium.exceptions.CategoryNotFoundByIdException;
import dauphine.Medium.exceptions.PostNotFoundByIdException;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> getAllLikeTitleOrContent(String value) {
        if (value == null || value.isEmpty()) {
            return List.of();
        }
        return repository.findAllLikeTitleOrContent(value);
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    @Override
    public Post getById(UUID id) throws PostNotFoundByIdException {
        return repository.findById(id)
                .orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post create(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundByIdException(categoryId));
        Post post = new Post(title, content, category);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) throws PostNotFoundByIdException {
        Post post = getById(id);
        post.setTitle(title);
        post.setContent(content);
        return repository.save(post);
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }
}