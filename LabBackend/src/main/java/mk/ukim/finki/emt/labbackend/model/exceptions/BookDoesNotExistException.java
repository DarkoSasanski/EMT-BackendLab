package mk.ukim.finki.emt.labbackend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class BookDoesNotExistException extends RuntimeException{
    public BookDoesNotExistException(Long id) {
        super(String.format("Book with id %d does not exist", id));
    }
}
