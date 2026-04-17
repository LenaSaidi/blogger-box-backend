package dauphine.Medium.controllers;

import dauphine.Medium.services.PostService;
import dauphine.Medium.models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


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
    @Operation(summary = "Retrieve all posts ordered by creation date")
    public List<Post> retrieveAll() {
        return service.getAll();
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Retrieve all posts per a category")
    public List<Post> retrieveByCategory(@PathVariable UUID categoryId) {
        return service.getAllByCategoryId(categoryId);
    }

    @PostMapping
    @Operation(summary = "Create a new post")
    public Post create(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam UUID categoryId) {
        return service.create(title, content, categoryId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post")
    public Post update(
            @PathVariable UUID id,
            @RequestParam String title,
            @RequestParam String content) {
        return service.update(id, title, content);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post")
    public boolean delete(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}