package mk.ukim.finki.emt.labbackend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class AuthorDoesNotExistException extends RuntimeException{
    public AuthorDoesNotExistException(Long id) {
        super(String.format("Author with id %d does not exist", id));
    }
}
