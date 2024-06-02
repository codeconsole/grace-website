package website

import grails.core.GrailsApplication
import groovy.transform.CompileDynamic
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.authentication.AuthenticationTrustResolver
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils

class SpringSecurityService {

    AuthenticationTrustResolver authenticationTrustResolver
    GrailsApplication grailsApplication

    static Authentication getAuthentication() {
        return SecurityContextHolder.context.authentication;
    }

    boolean isLoggedIn() {
        def authentication = getAuthentication();
        authentication && !authenticationTrustResolver.isAnonymous(authentication)
    }

    static List<GrantedAuthority> parseAuthoritiesString(String roleNames) {
        List<GrantedAuthority> requiredAuthorities = []
        for (String auth in StringUtils.commaDelimitedListToStringArray(roleNames)) {
            auth = auth.trim()
            if (auth) {
                requiredAuthorities << new SimpleGrantedAuthority(auth)
            }
        }
        requiredAuthorities
    }

    boolean ifAnyGranted(String roles) {
        ifAnyGranted parseAuthoritiesString(roles)
    }

    boolean ifAnyGranted(Collection<? extends GrantedAuthority> roles) {
        retainAll principalAuthorities, roles
    }

    static Set<String> retainAll(granted, required) {
        Set<String> grantedRoles = authoritiesToRoles(granted)
        grantedRoles.retainAll authoritiesToRoles(required)
        grantedRoles
    }

    static List asList(o) { o ? o as List : [] }

    static Set<String> authoritiesToRoles(authorities) {
        Set<String> roles = new HashSet<String>()
        for (authority in asList(authorities)) {
            String authorityName = ((GrantedAuthority)authority).authority
            assert authorityName != null,
                    "Cannot process GrantedAuthority objects which return null from getAuthority() - attempting to process $authority"
            roles << authorityName
        }

        roles
    }

    @CompileDynamic
    static Collection<GrantedAuthority> getPrincipalAuthorities() {
        Authentication authentication = getAuthentication()
        if (!authentication) {
            return Collections.emptyList()
        }

        Collection<? extends GrantedAuthority> authorities = authentication.authorities
        if (authorities == null) {
            return Collections.emptyList()
        }
        authorities
    }

    private Collection<? extends GrantedAuthority> findInferredAuthorities(Collection<GrantedAuthority> granted) {
        getBean('roleHierarchy', RoleHierarchy).getReachableGrantedAuthorities(granted) ?: ( Collections.emptyList() as Collection<? extends GrantedAuthority> )
    }

    @SuppressWarnings('unchecked')
    private <T> T getBean(String name, Class<T> c = null) {
        (T)grailsApplication.mainContext.getBean(name, c)
    }
}
