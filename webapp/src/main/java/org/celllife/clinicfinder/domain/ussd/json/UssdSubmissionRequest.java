package org.celllife.clinicfinder.domain.ussd.json;

import org.celllife.clinicfinder.domain.ussd.ClosestLandmark;
import org.celllife.clinicfinder.domain.ussd.User;
import org.celllife.clinicfinder.domain.ussd.UssdRequest;

import java.io.Serializable;
import java.util.List;

public class UssdSubmissionRequest implements Serializable {

	private static final long serialVersionUID = -2509724691685798269L;

	private UssdRequest ussdRequest;

    private User user;

    private LocationData locationData;

    private List<ClosestLandmark> closestLandmarks;

    private String smsText;

	public UssdSubmissionRequest() {
	}

    public UssdSubmissionRequest(UssdRequest ussdRequest, User user, LocationData locationData, List<ClosestLandmark> closestLandmarks, String smsText) {
        this.ussdRequest = ussdRequest;
        this.user = user;
        this.locationData = locationData;
        this.closestLandmarks = closestLandmarks;
        this.smsText = smsText;
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

    public List<ClosestLandmark> getClosestLandmarks() {
        return closestLandmarks;
    }

    public void setClosestLandmarks(List<ClosestLandmark> closestLandmarks) {
        this.closestLandmarks = closestLandmarks;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    @Override
    public String toString() {
        return "Request [ussdRequest=" + ussdRequest + ", user="
                + user + ", locationData=" + locationData + ", closestLandmarks=" + closestLandmarks
                + ", smsText=" + smsText + "]";
    }
}