package website

import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.core.GrantedAuthority

class SecurityTagLib {
    static defaultEncodeAs = [taglib:'none']
    static namespace = "sec"

    SpringSecurityService springSecurityService

    def ifLoggedIn = { attrs, body ->
        if (springSecurityService.isLoggedIn()) {
            out << body()
        }
    }

    def ifNotLoggedIn = { attrs, body ->
        if (!springSecurityService.isLoggedIn()) {
            out << body()
        }
    }

    def username = { attrs ->
        if (springSecurityService.isLoggedIn()) {
            out << springSecurityService.authentication.name.encodeAsHTML()
        }
    }

    def ifAnyGranted = { attrs, body ->
        String roles = assertAttribute('roles', attrs, 'ifAnyGranted')
        if (springSecurityService.ifAnyGranted(roles)) {
            out << body()
        }
    }

    protected assertAttribute(String name, attrs, String tag) {
        if (!attrs.containsKey(name)) {
            throwTagError "Tag [$tag] is missing required attribute [$name]"
        }
        attrs.remove name
    }
}
