package mk.ukim.finki.emt.labbackend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class CountryDoesNotExistException extends RuntimeException{
    public CountryDoesNotExistException(Long id) {
        super(String.format("Country with id %d does not exist", id));
    }
}
