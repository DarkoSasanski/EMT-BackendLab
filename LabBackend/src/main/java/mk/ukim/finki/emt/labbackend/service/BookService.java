package mk.ukim.finki.emt.labbackend.service;

import mk.ukim.finki.emt.labbackend.model.Book;
import mk.ukim.finki.emt.labbackend.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, BookDto bookDto);
    void deleteById(Long id);
    Optional<Book> markAsTaken(Long id);
    Page<Book> findAllByPagination(Pageable pageable);

}
