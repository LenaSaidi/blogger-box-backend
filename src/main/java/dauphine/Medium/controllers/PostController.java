package dauphine.Medium.controllers;

import dauphine.Medium.services.PostService;
import dauphine.Medium.models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import dauphine.Medium.exceptions.CategoryNotFoundByIdException;
import dauphine.Medium.exceptions.PostNotFoundByIdException;
import org.springframework.http.ResponseEntity;
import java.net.URI;



import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API", description = "Post endpoints")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Get all posts",
            description = "Retrieve all posts or filter by title or content"
    )
    public ResponseEntity<List<Post>> getAll(@RequestParam(required = false) String value) {
        List<Post> posts = value == null || value.isBlank()
                ? service.getAll()
                : service.getAllLikeTitleOrContent(value);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Retrieve all posts per a category")
    public ResponseEntity<List<Post>> retrieveByCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(service.getAllByCategoryId(categoryId));
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public ResponseEntity<Post> create(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam UUID categoryId) throws CategoryNotFoundByIdException {
        Post post = service.create(title, content, categoryId);
        return ResponseEntity
                .created(URI.create("/v1/posts/" + post.getId()))
                .body(post);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public ResponseEntity<Post> update(
            @PathVariable UUID id,
            @RequestParam String title,
            @RequestParam String content) throws PostNotFoundByIdException {
        Post post = service.update(id, title, content);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}