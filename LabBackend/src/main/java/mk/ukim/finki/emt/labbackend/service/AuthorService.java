package mk.ukim.finki.emt.labbackend.service;

import mk.ukim.finki.emt.labbackend.model.Author;
import mk.ukim.finki.emt.labbackend.model.dto.AuthorDto;

import java.util.*;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto authorDto);
    Optional<Author> edit(Long id, AuthorDto authorDto);
    void deleteById(Long id);
}
