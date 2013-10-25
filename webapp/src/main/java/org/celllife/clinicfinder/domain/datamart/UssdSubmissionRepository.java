package org.celllife.clinicfinder.domain.datamart;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;

import java.util.Date;

public interface UssdSubmissionRepository extends PagingAndSortingRepository<UssdSubmission, String> {


}
