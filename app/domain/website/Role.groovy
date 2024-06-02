package website

import org.springframework.security.core.GrantedAuthority

class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1

    String authority

    static constraints = {
        authority nullable: false, blank: false, unique: true
    }
}
