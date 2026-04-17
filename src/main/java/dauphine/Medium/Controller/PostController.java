package dauphine.Medium.Controller;

import dauphine.Medium.dto.CreationPostRequest;
import dauphine.Medium.dto.UpdatePostRequest;
import dauphine.Medium.Model.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API", description = "Post endpoints")
public class PostController {

    private final List<Post> temporaryPosts = new ArrayList<>();

    @GetMapping
    @Operation(summary = "Retrieve all posts ordered by creation date")
    public List<Post> retrieveAll() {
        return temporaryPosts.stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Retrieve all posts per a category")
    public List<Post> retrieveByCategory(@PathVariable UUID categoryId) {
        return temporaryPosts.stream()
                .filter(p -> p.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post create(@RequestBody CreationPostRequest request) {
        Post post = new Post(
                UUID.randomUUID(),
                request.getTitle(),
                request.getContent(),
                new Timestamp(System.currentTimeMillis()),
                request.getCategoryId()
        );
        temporaryPosts.add(post);
        return post;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post update(@PathVariable UUID id, @RequestBody UpdatePostRequest request) {
        Post post = temporaryPosts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (post != null) {
            post.setTitle(request.getTitle());
            post.setContent(request.getContent());
            post.setCategoryId(request.getCategoryId());
        }
        return post;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public void delete(@PathVariable UUID id) {
        temporaryPosts.removeIf(p -> p.getId().equals(id));
    }
}