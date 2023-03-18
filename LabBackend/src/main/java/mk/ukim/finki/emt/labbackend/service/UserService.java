package mk.ukim.finki.emt.labbackend.service;

import mk.ukim.finki.emt.labbackend.model.enumarations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import mk.ukim.finki.emt.labbackend.model.User;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
