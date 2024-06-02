package website

class BootStrap {

    def init = { servletContext ->
        if (!User.count()) {
            User.withTransaction {
                def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
                def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
                def testUser = new User(username:'user', password:'{noop}password').save(flush: true, failOnError: true)
                UserRole.create testUser, userRole, true
                def adminUser = new User(username:'admin', password:'{noop}password').save(flush: true, failOnError: true)
                UserRole.create adminUser, userRole, true
                UserRole.create adminUser, adminRole, true
            }
        }
    }

    def destroy = {
    }

}
