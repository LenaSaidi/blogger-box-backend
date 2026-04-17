package dauphine.Medium.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello World Controller", description = "My first hello world endpoint")

public class helloController {

    @GetMapping ("/hello-world")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hello")
    public String helloName(String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello/{name}")
    @Operation(
            summary = "Hello world endpoint",
            description = "returns Hello 'name' by path variable"
    )
    public String hello(
            @Parameter(description = "Name of the person to greet")
            @PathVariable String name
    ) {
        return "Hello " + name;
    }

}
