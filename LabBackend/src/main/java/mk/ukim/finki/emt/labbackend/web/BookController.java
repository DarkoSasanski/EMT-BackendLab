package mk.ukim.finki.emt.labbackend.web;

import mk.ukim.finki.emt.labbackend.model.Book;
import mk.ukim.finki.emt.labbackend.model.dto.BookDto;
import mk.ukim.finki.emt.labbackend.model.enumarations.Category;
import mk.ukim.finki.emt.labbackend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll()
    {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id)
    {
        return bookService.findById(id)
                .map(b->ResponseEntity.ok().body(b))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto)
    {
        return bookService.save(bookDto)
                .map(b->ResponseEntity.ok().body(b))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto)
    {
        return bookService.edit(id, bookDto)
                .map(b->ResponseEntity.ok().body(b))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        bookService.deleteById(id);
        if(bookService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/markAsTaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id)
    {
        return bookService.markAsTaken(id)
                .map(b->ResponseEntity.ok().body(b))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping("/pagination")
    public List<Book> findAllByPagination(Pageable pageable)
    {
        return bookService.findAllByPagination(pageable).getContent();
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories()
    {
        return Arrays.stream(Category.values()).toList();
    }
}
