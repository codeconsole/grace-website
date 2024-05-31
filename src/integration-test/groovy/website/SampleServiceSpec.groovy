package website

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SampleServiceSpec extends Specification {

    SampleService sampleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Sample(...).save(flush: true, failOnError: true)
        //new Sample(...).save(flush: true, failOnError: true)
        //Sample sample = new Sample(...).save(flush: true, failOnError: true)
        //new Sample(...).save(flush: true, failOnError: true)
        //new Sample(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sample.id
    }

    void "test get"() {
        setupData()

        expect:
        sampleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Sample> sampleList = sampleService.list(max: 2, offset: 2)

        then:
        sampleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sampleService.count() == 5
    }

    void "test delete"() {
        Long sampleId = setupData()

        expect:
        sampleService.count() == 5

        when:
        sampleService.delete(sampleId)
        sessionFactory.currentSession.flush()

        then:
        sampleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Sample sample = new Sample()
        sampleService.save(sample)

        then:
        sample.id != null
    }
}
