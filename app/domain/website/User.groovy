package website

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User implements UserDetails {

    Date dateCreated
    Date lastUpdated

    String username
    String password

    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        UserRole.withTransaction {
            Collection<? extends GrantedAuthority> authorities = (UserRole.findAllByUser(this) as List<UserRole>)*.role
                .findAll { it.authority } // force loading proxy
        }
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
        table '`user`'
        password column: '`password`'
    }
}
