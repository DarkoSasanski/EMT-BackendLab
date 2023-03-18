package mk.ukim.finki.emt.labbackend.web;

import mk.ukim.finki.emt.labbackend.model.Country;
import mk.ukim.finki.emt.labbackend.model.dto.CountryDto;
import mk.ukim.finki.emt.labbackend.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll()
    {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id)
    {
        return countryService.findById(id)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto countryDto)
    {
        return countryService.save(countryDto)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> edit(@PathVariable Long id, @RequestBody CountryDto countryDto)
    {
        return countryService.edit(id, countryDto)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        countryService.deleteById(id);
        if(countryService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
