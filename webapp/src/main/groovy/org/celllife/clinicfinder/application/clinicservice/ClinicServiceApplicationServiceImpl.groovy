package org.celllife.clinicfinder.application.clinicservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ClinicServiceApplicationServiceImpl implements ClinicServiceApplicationService {
	
	private static String NEAREST_CLINIC_REST_URL = "/service/locateNearestClinic"
	private static String LATITUDE_URL_PARAM = "latitude"
	private static String LONGITUDE_URL_PARAM = "longitude"

	
	@Value('${clinicservice.api}')
	private String apiUrl;
	
	@Value('${clinicservice.username}')
	private String username
	
	@Value('${clinicservice.password}')
	private String password
	

	@Override
	public Clinic getNearestClinic(Double latitude, Double longitude) {
        def client = new groovyx.net.http.RESTClient(getNearestClinicUrl(latitude, longitude))
        client.auth.basic(username, password)
        Map<String, Object> data = client.get([:]).data
		return convertMapToClinic(data);
	}

	private String getNearestClinicUrl(Double latitude, Double longitude) {
		return apiUrl + NEAREST_CLINIC_REST_URL + "?" + LATITUDE_URL_PARAM + "=" + latitude + "&" + LONGITUDE_URL_PARAM + "=" + longitude
	}

	private Clinic convertMapToClinic(Map<String, Object> data) {
		new Clinic( data.findAll { k, v -> k in Clinic.metaClass.properties*.name})
	}
}