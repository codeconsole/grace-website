package website

import grails.gorm.services.Service

@Service(Sample)
interface SampleService {

    Sample get(Serializable id)

    List<Sample> list(Map args)

    Long count()

    void delete(Serializable id)

    Sample save(Sample sample)

}