package website

import grails.gorm.services.Service
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsPasswordService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.UserDetailsManager

@Service(User)
abstract class UserService implements UserDetailsManager, UserDetailsPasswordService {

    AuthenticationManager authenticationManager

    abstract User get(Serializable id)

    abstract List<User> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract User save(User user)

    void createUser(UserDetails user) {
        save(user)
    }

    void updateUser(UserDetails user) {
        save(user)
    }

    void deleteUser(String username) {
        delete(loadUserByUsername(username).username)
    }

    void changePassword(String oldPassword, String newPassword) { }

    boolean userExists(String username) {
        loadUserByUsername(username) != null
    }

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User.withTransaction {
            UserDetails userDetails = User.findByUsername(username)
            if (!userDetails) {
                throw new UsernameNotFoundException("No user found with username '${username}'");
            }
            userDetails
        }
    }

    UserDetails updatePassword(UserDetails user, String newPassword) {
        user.password = newPassword
        save(user)
    }
}