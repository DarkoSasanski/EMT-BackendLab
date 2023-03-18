package mk.ukim.finki.emt.labbackend.web;

import mk.ukim.finki.emt.labbackend.model.Author;
import mk.ukim.finki.emt.labbackend.model.dto.AuthorDto;
import mk.ukim.finki.emt.labbackend.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll()
    {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id)
    {
        return authorService.findById(id)
                .map(a->ResponseEntity.ok().body(a))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto)
    {
        return authorService.save(authorDto)
                .map(a->ResponseEntity.ok().body(a))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> edit(@PathVariable Long id, @RequestBody AuthorDto authorDto)
    {
        return authorService.edit(id, authorDto)
                .map(a->ResponseEntity.ok().body(a))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        authorService.deleteById(id);
        if(authorService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
