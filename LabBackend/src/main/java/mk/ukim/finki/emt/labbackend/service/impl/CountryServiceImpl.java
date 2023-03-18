package mk.ukim.finki.emt.labbackend.service.impl;

import mk.ukim.finki.emt.labbackend.model.Country;
import mk.ukim.finki.emt.labbackend.model.dto.CountryDto;
import mk.ukim.finki.emt.labbackend.model.exceptions.CountryDoesNotExistException;
import mk.ukim.finki.emt.labbackend.repository.CountryRepository;
import mk.ukim.finki.emt.labbackend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        return Optional.of(countryRepository.save(new Country(countryDto.getName(), countryDto.getContinent())));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(()->new CountryDoesNotExistException(id));
        country.setContinent(countryDto.getContinent());
        country.setName(countryDto.getName());
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
