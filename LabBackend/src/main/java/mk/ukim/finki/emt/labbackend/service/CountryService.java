package mk.ukim.finki.emt.labbackend.service;

import mk.ukim.finki.emt.labbackend.model.Country;
import mk.ukim.finki.emt.labbackend.model.dto.CountryDto;

import java.util.*;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    Optional<Country> edit(Long id, CountryDto countryDto);
    void deleteById(Long id);
}
