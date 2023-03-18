package mk.ukim.finki.emt.labbackend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class InsufficientNumberOfCopiesException extends RuntimeException {
    public InsufficientNumberOfCopiesException(Long id) {
        super(String.format("Book with id %d does not have enough available copies", id));
    }
}
