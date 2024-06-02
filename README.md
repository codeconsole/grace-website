# website

This project is generated by Grace v2023.0.0-M6.

# Demo Securing a Grace Web Application

- [Securing a Web Application](https://spring.io/guides/gs/securing-web)
- [Security How To](https://docs.spring.io/spring-boot/how-to/security.html)
- [Spring Security Reference](https://docs.spring.io/spring-boot/reference/web/spring-security.html)
- [Spring Boot Common Application Properties](https://docs.spring.io/spring-boot/appendix/application-properties/index.html)
- [Spring Security Java Configuration](https://docs.spring.io/spring-security/reference/servlet/configuration/java.html)


- [LoginPageGeneratingWebFilter.java](https://github.com/spring-projects/spring-security/blob/main/web/src/main/java/org/springframework/security/web/server/ui/LoginPageGeneratingWebFilter.java)


- [SecurityAutoConfiguration.java](https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/security/servlet/SecurityAutoConfiguration.java)
- [SpringBootWebSecurityConfiguration.java](https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/security/servlet/SpringBootWebSecurityConfiguration.java)
- [OAuth2WebSecurityConfiguration.java](https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/security/oauth2/client/servlet/OAuth2WebSecurityConfiguration.java)

```
create-app website
create-domain-class Sample
generate-controller Sample
generate-views Sample
install-templates
create-domain-class User
generate-all User
create-domain-class UserRole
create-scaffold-controller UserRole
create-domain-class Role
create-scaffold-controller Role
```