package mk.ukim.finki.emt.labbackend.service.impl;

import mk.ukim.finki.emt.labbackend.model.Author;
import mk.ukim.finki.emt.labbackend.model.Country;
import mk.ukim.finki.emt.labbackend.model.dto.AuthorDto;
import mk.ukim.finki.emt.labbackend.model.exceptions.AuthorDoesNotExistException;
import mk.ukim.finki.emt.labbackend.model.exceptions.CountryDoesNotExistException;
import mk.ukim.finki.emt.labbackend.repository.AuthorRepository;
import mk.ukim.finki.emt.labbackend.repository.CountryRepository;
import mk.ukim.finki.emt.labbackend.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = countryRepository.findById(authorDto.getCountryId())
                .orElseThrow(()->new CountryDoesNotExistException(authorDto.getCountryId()));
        Author author = new Author(
                authorDto.getName(),
                authorDto.getSurname(),
                country
        );
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()->new AuthorDoesNotExistException(id));
        Country country = countryRepository.findById(authorDto.getCountryId())
                .orElseThrow(()->new CountryDoesNotExistException(authorDto.getCountryId()));
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
