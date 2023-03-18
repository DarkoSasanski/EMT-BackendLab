package mk.ukim.finki.emt.labbackend.service.impl;

import mk.ukim.finki.emt.labbackend.model.Author;
import mk.ukim.finki.emt.labbackend.model.Book;
import mk.ukim.finki.emt.labbackend.model.dto.BookDto;
import mk.ukim.finki.emt.labbackend.model.exceptions.AuthorDoesNotExistException;
import mk.ukim.finki.emt.labbackend.model.exceptions.BookDoesNotExistException;
import mk.ukim.finki.emt.labbackend.model.exceptions.InsufficientNumberOfCopiesException;
import mk.ukim.finki.emt.labbackend.repository.AuthorRepository;
import mk.ukim.finki.emt.labbackend.repository.BookRepository;
import mk.ukim.finki.emt.labbackend.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()->new AuthorDoesNotExistException(bookDto.getAuthorId()));
        Book book = new Book(
                bookDto.getName(),
                bookDto.getCategory(),
                author,
                bookDto.getAvailableCopies()
        );
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new BookDoesNotExistException(id));
        Author author = authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()->new AuthorDoesNotExistException(bookDto.getAuthorId()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    @Transactional
    public Optional<Book> markAsTaken(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new BookDoesNotExistException(id));
        if(book.getAvailableCopies()<=0)
        {
            throw new InsufficientNumberOfCopiesException(id);
        }
        int copies = book.getAvailableCopies();
        book.setAvailableCopies(copies-1);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Page<Book> findAllByPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
