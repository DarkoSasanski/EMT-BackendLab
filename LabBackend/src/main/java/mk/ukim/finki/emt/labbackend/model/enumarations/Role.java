package mk.ukim.finki.emt.labbackend.model.enumarations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_LIBRARIAN;

    @Override
    public String getAuthority() {
        return name();
    }
}
