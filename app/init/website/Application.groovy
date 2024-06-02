package website

import grails.boot.Grails
import groovy.transform.CompileStatic
import org.springframework.context.annotation.Import
import website.config.SecurityConfig

@CompileStatic
@Import(SecurityConfig)
class Application {

    static void main(String[] args) {
        Grails.run(Application, args)
    }

}
