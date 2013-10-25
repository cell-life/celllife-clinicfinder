package org.celllife.clinicfinder.domain.ussd;

import org.celllife.clinicfinder.framework.logging.LogLevel;
import org.celllife.clinicfinder.framework.logging.Loggable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

/**
 * Repository for the USSD request object - used for persistence
 */
@Loggable(LogLevel.DEBUG)
@RestResource(path = "requests")
public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {

    /*@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    Iterable<Request> findByClinicCode(@Param("clinicCode") String clinicCode);*/

}
