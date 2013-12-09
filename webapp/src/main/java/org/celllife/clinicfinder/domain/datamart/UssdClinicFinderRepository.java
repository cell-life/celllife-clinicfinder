package org.celllife.clinicfinder.domain.datamart;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UssdClinicFinderRepository extends PagingAndSortingRepository<UssdClinicFinder, String> {

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    UssdClinicFinder findOneByUssdRequestId(String ussdRequestId);

}
