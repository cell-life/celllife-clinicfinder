package org.celllife.clinicfinder.application.clinicservice

/**
 * Application Service that interacts with the Cell-Life Clinic Service
 */
interface ClinicServiceApplicationService {

	/**
	 * Retrieves the nearest clinic given a GPS co-ordinate
	 * @param latitude Double
	 * @param longitude Double
	 * @return Clinic data bean
	 */
	Clinic getNearestClinic(Double latitude, Double longitude);
}
