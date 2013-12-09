package org.celllife.clinicfinder.domain.ussd.json;

import java.io.Serializable;

import org.celllife.clinicfinder.domain.ussd.User;
import org.celllife.clinicfinder.domain.ussd.UssdRequest;

public class UssdSubmissionRequest implements Serializable {

	private static final long serialVersionUID = -2509724691685798269L;

	private UssdRequest ussdRequest;

    private User user;

    private LocationData locationData;

	public UssdSubmissionRequest() {
	}

    public UssdSubmissionRequest(UssdRequest ussdRequest, User user, LocationData locationData) {
        this.ussdRequest = ussdRequest;
        this.user = user;
        this.locationData = locationData;
    }

    public UssdRequest getUssdRequest() {
        return ussdRequest;
    }

    public void setUssdRequest(UssdRequest ussdRequest) {
        this.ussdRequest = ussdRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    @Override
    public String toString() {
        return "Request [ussdRequest=" + ussdRequest + ", user="
                + user + ", locationData=" + locationData + "]";
    }
}